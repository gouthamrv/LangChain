package com.example.ticketai.repository;


import com.example.ticketai.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository
        extends JpaRepository<Ticket, Long> {


}