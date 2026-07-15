package com.example.ticketai.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.MetadataStorageConfig;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorStoreConfig {

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore(
            EmbeddingModel embeddingModel) {

        return PgVectorEmbeddingStore.builder()

                .host("localhost")
                .port(5432)

                .database("tickets")

                .user("postgres")
                .password("postgres")

                .table("ticket_embeddings")

                .dimension(embeddingModel.dimension())

                .createTable(true)

                .build();
    }
}