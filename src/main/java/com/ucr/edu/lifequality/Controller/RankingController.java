package com.ucr.edu.lifequality.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankingController {
    @RequestMapping("/")
    public String getPage(){
        return "Welcome to the ranking!";
    }
    @RequestMapping("/test")
    public String test(){
        return "test!";
    }
}
