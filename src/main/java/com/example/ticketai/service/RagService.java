package com.example.ticketai.service;

import com.example.ticketai.dto.SearchResultDto;
import com.example.ticketai.service.SearchService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RagService {

    private final SearchService searchService;

    private final AIService aiService;

    public String ask(String question) {

        List<SearchResultDto> tickets =
                searchService.search(question);

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                Previous support tickets:

                """);

        for (SearchResultDto ticket : tickets) {

            prompt.append(ticket.description())
                    .append("\n\n");

        }

        prompt.append("""
                Customer Question:

                """);

        prompt.append(question);

        return aiService.answer(prompt.toString());

    }

}