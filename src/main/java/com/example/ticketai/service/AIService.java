package com.example.ticketai.service;

import com.example.ticketai.ai.TicketAssistant;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {


    private final TicketAssistant assistant;

    public String analyze(String description){

        return assistant.answer(description);

    }

}