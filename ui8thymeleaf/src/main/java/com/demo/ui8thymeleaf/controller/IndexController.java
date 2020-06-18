package com.demo.ui8thymeleaf.controller;

import com.demo.ui8thymeleaf.bean.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping(value = "/account")
    public String index(Model m) {
        List<Account> list = new ArrayList<Account>();
        list.add(new Account("Wanying Xie", "Scrum Master", "CIA", "Female", "17777777777"));
        list.add(new Account("Dean Li", "Product Owner", "CIA", "Male", "17777777777"));
        list.add(new Account("Mike Xia", "Architect & Developer", "CIA", "Male", "17777777777"));
        list.add(new Account("Sherry Shao", "Architect & Developer", "CIA", "Female", "17777777777"));
        list.add(new Account("Joy Liu", "Developer", "CIA", "Female", "17777777777"));
        list.add(new Account("Kumara Luo", "Developer", "CIA", "Female", "17777777777"));
        list.add(new Account("Allen Feng", "Developer", "CIA", "Male", "17777777777"));
        list.add(new Account("Lianyue Li", "Developer", "CIA", "Male", "17777777777"));
        m.addAttribute("account", list);
        return "account";
    }
}
