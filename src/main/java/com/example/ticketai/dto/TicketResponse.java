package com.example.ticketai.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TicketResponse {

    private Long id;

    private String customerName;

    private String description;

    private String status;

}