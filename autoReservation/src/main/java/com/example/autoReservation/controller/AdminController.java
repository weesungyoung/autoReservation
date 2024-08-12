package com.example.autoReservation.controller;

import com.example.autoReservation.entity.Ticket;
import com.example.autoReservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPage() {
        return "admin"; // admin.html을 반환
    }

    @Autowired
    private TicketService ticketService;

    @PostMapping("/create_ticket")
    public String createTicket(
            @RequestParam("name") String name,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("capacity") Integer capacity,
            @RequestParam("price") Double price) {

        Ticket ticket = new Ticket();
        ticket.setName(name);
        ticket.setStartDate(LocalDate.parse(startDate));
        ticket.setEndDate(LocalDate.parse(endDate));
        ticket.setCapacity(capacity);
        ticket.setPrice(price);

        ticketService.saveTicket(ticket);

        return "redirect:/addTicket_success";
    }

    @GetMapping("/addTicket_success")
    public String signupSuccess() {
        return "addTicket_success";
    }

}
