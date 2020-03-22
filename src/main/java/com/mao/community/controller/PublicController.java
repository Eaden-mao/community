package com.mao.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author maoea
 */
@Controller
public class PublicController {

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
