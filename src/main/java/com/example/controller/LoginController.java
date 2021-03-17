package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class LoginController {
    //ログイン画面への遷移
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    //ログイン成功時のメニュー画面への遷移
    @PostMapping("/top")
    public String postLogin() {
        return "top";
    }
}
