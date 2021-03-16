package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {
    //メニュー画面への遷移
    @GetMapping("top")
    String index() {
        return "top";
    }
}
