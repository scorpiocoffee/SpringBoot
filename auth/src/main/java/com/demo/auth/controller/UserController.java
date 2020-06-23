package com.demo.auth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequiresPermissions("user")
    @RequestMapping("info")
    public String userInfo(Model model) {
        model.addAttribute("value", "Get User Info");
        return "user";
    }

    @RequiresPermissions("user:add")
    @RequestMapping("add")
    public String userAdd(Model model) {
        model.addAttribute("value", "Add New User");
        return "user";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("delete")
    public String userDelete(Model model) {
        model.addAttribute("value", "Delete a User");
        return "user";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("update")
    public String userUpdate(Model model) {
        model.addAttribute("value", "Update a User");
        return "user";
    }
}
