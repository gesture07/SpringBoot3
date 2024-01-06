package com.example.firstproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")  //url요청 접수
    public String niceToMeetYou(Model model){ //model 객체 받아오기

        //model 객체가 "다혜"값을 "username"에 연결해 웹 브라두저로 보냄
        model.addAttribute("username", "emma");
        //greetings.mustache 파일 변환
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","곰븨");
        return "goodbye";
    }
}
