package com.example.ticketai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TicketRequest {


    @NotBlank
    private String customerName;


    @NotBlank
    private String description;

}