package com.example.ticketai.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name="tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String customerName;


    @Column(length = 2000)
    private String description;


    private String category;


    private String priority;

    @Column(length = 4000)
    private String aiResponse;

    private String status;

    private LocalDateTime createdAt;

    @PrePersist
    public void createDate(){
        createdAt = LocalDateTime.now();
    }

    @Column(name = "embedding_generated")
    private Boolean embeddingGenerated = false;

}