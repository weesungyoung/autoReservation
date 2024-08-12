package com.example.autoReservation.controller;

import com.example.autoReservation.entity.Member;
import com.example.autoReservation.service.MemberService;
import com.example.autoReservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final MemberService memberService;
    private final TicketService ticketService;

    @Autowired
    public IndexController(MemberService memberService, TicketService ticketService) {
        this.memberService = memberService;
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Member member = memberService.findMemberByMemberId(username)
                .orElse(null);

        model.addAttribute("user", member);
        model.addAttribute("tickets", ticketService.findAllTicketsDTO());
        return "index";
    }
}
