package com.example.autoReservation.controller;

import com.example.autoReservation.entity.Member;
import com.example.autoReservation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/submit_signup")
    public String submitSignup(@ModelAttribute Member member) {
        memberRepository.save(member);
        return "redirect:/signup_success";
    }

    @GetMapping("/signup_success")
    public String signupSuccess() {
        return "signup_success";
    }
}
