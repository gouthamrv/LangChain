package com.example.ticketai.service;

import com.example.ticketai.entity.Ticket;
import com.example.ticketai.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;

    private final AIService aiService;

    private final EmbeddingService embeddingService;

    /**
     * Create a new support ticket.
     */

    @Transactional
    public Ticket create(Ticket ticket) {

        String aiResponse =
                aiService.analyze(
                        ticket.getDescription());

        ticket.setAiResponse(aiResponse);

        ticket.setStatus("OPEN");

        ticket.setPriority("MEDIUM");

        Ticket saved =
                ticketRepository.save(ticket);

        embeddingService.saveTicketEmbedding(

                saved.getId(),

                saved.getDescription()

        );

        return saved;
    }


    /**
     * Get all tickets.
     */
    @Transactional(readOnly = true)
    public List<Ticket> findAll() {

        return ticketRepository.findAll();
    }

    /**
     * Find ticket by id.
     */
    @Transactional(readOnly = true)
    public Ticket findById(Long id) {

        return ticketRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Ticket not found : " + id));
    }

    /**
     * Delete ticket.
     */
    public void delete(Long id) {

        ticketRepository.deleteById(id);
    }

}