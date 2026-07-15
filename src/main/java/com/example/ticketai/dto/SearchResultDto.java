package com.example.ticketai.dto;

import lombok.Builder;

@Builder
public record SearchResultDto(

        Long ticketId,

        String description,

        double score

) {
}