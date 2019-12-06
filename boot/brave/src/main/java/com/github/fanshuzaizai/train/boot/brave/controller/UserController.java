package com.github.fanshuzaizai.train.boot.brave.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiangzy
 * @date 2019/12/3
 */
@RequestMapping("/user")
@RestController
public class UserController {


    @GetMapping("/login")
    public String login() {
        return "success";
    }

}
