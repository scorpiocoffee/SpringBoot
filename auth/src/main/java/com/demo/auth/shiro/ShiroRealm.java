package com.demo.auth.shiro;

import com.demo.auth.bean.Permission;
import com.demo.auth.bean.Role;
import com.demo.auth.bean.User;
import com.demo.auth.service.UserPermissionService;
import com.demo.auth.service.UserRoleService;
import com.demo.auth.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserPermissionService userPermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUserName();

        System.out.println("User: " + userName + " has authorizations -- ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        List<Role> roleList = userRoleService.findRolesByUserName(userName);
        System.out.println("Roles: " + roleList);
        Set<String> roleSet = new HashSet<String>();
        for(Role r : roleList) {
            roleSet.add(r.getName());
        }
        simpleAuthorizationInfo.setRoles(roleSet);

        List<Permission> permissionList = userPermissionService.findPermissionsByUserName(userName);
        System.out.println("Permissions: " + permissionList);
        Set<String> permissionSet = new HashSet<String>();
        for(Permission p : permissionList) {
            permissionSet.add(p.getName());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        System.out.println("User: " + userName + " certification -- ShiroRealm.doGetAuthenticationInfo");

        User user = userService.findUserByUserName(userName);
        if(null == user) {
            throw new UnknownAccountException("Wrong User Name or Password");
        }
        if(!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("Wrong Password for this User Name");
        }
        if(user.getStatus().equals("1")) {
            throw new LockedAccountException("This account has been locked, please contact your administrator.");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
