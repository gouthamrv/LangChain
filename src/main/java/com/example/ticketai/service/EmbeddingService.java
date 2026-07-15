package com.example.ticketai.service;

import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    private final EmbeddingStore<TextSegment> embeddingStore;

    public void saveTicketEmbedding(
            Long ticketId,
            String description) {

        Embedding embedding =
                embeddingModel.embed(description)
                        .content();

        Metadata metadata = new Metadata();

        metadata.put("ticketId", ticketId.toString());

        TextSegment segment =
                TextSegment.from(description, metadata);

        embeddingStore.add(
                embedding,
                segment
        );

        log.info(
                "Embedding stored for ticket {}",
                ticketId
        );
    }

}