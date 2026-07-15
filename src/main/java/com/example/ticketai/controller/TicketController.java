package com.example.ticketai.controller;


import com.example.ticketai.entity.Ticket;
import com.example.ticketai.service.TicketService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {


    private final TicketService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket create(
            @RequestBody Ticket ticket) {

        return service.create(ticket);
    }


    @GetMapping
    public List<Ticket> getAll() {

        return service.findAll();

    }


    @GetMapping("/{id}")
    public Ticket getById(
            @PathVariable Long id) {

        return service.findById(id);

    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);

    }

}