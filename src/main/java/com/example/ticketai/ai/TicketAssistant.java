package com.example.ticketai.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface TicketAssistant {

    @SystemMessage("""
        You are an experienced support engineer.

        Answer ONLY using the provided ticket history.

        If the answer is not found,
        clearly state that.
        """)
    String answer(
            @UserMessage String prompt
    );
}