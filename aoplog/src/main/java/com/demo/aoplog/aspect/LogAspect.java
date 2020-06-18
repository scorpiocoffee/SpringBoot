package com.demo.aoplog.aspect;

import com.demo.aoplog.annotation.Log;
import com.demo.aoplog.dao.SysLogDao;
import com.demo.aoplog.domain.SysLog;
import com.demo.aoplog.util.HttpContextUtils;
import com.demo.aoplog.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private SysLogDao sysLogDao;

    @Pointcut("@annotation(com.demo.aoplog.annotation.Log)")
    public void pointcut() {}

    @Around("pointcut()")
    public void around(ProceedingJoinPoint point) {
        long beginTime = System.currentTimeMillis();
        try {
            point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis();
        saveLog(point, time);
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog  = new SysLog();
        Log logAnnotation = method.getAnnotation(Log.class);
        if(null != logAnnotation) {
            sysLog.setOperation(logAnnotation.value());
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName +  "()");
        Object[] args = joinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if(null != args && null != paramNames) {
            String params = "";
            for(int i = 0; i < args.length; i++) {
                params += " " + paramNames[i] + ": " + args[i];
            }
            sysLog.setParams(params);
        }
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        sysLog.setIp(IPUtils.getIpAddr(request));
        sysLog.setUsername("mrbird");
        sysLog.setTime((int) time);
        Date date = new Date();
        sysLog.setCreateTime(date);
        sysLogDao.saveSysLog(sysLog);
    }
}
