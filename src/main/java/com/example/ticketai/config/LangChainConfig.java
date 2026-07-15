package com.example.ticketai.config;

import com.example.ticketai.ai.TicketAssistant;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LangChainConfig {

    @Bean
    public ChatModel chatModel() {

        return OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("llama3.2")
                .temperature(0.2)
                .build();
    }

    @Bean
    public TicketAssistant ticketAssistant(ChatModel model) {

        return AiServices.builder(TicketAssistant.class)
                .chatModel(model)
                .build();
    }
}