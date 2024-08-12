package com.example.autoReservation.service;

import com.example.autoReservation.entity.Ticket;
import com.example.autoReservation.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket findTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public List<TicketDTO> findAllTicketsDTO() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ticketRepository.findAll().stream()
                .map(ticket -> new TicketDTO(
                        ticket.getName(),
                        ticket.getStartDate().format(formatter),
                        ticket.getEndDate().format(formatter),
                        ticket.getCapacity(),
                        ticket.getPrice()))
                .collect(Collectors.toList());
    }

    public static class TicketDTO {
        private String name;
        private String startDate;
        private String endDate;
        private int capacity;
        private double price;

        public TicketDTO(String name, String startDate, String endDate, int capacity, double price) {
            this.name = name;
            this.startDate = startDate;
            this.endDate = endDate;
            this.capacity = capacity;
            this.price = price;
        }

        // Getters
        public String getName() { return name; }
        public String getStartDate() { return startDate; }
        public String getEndDate() { return endDate; }
        public int getCapacity() { return capacity; }
        public double getPrice() { return price; }
    }
}
