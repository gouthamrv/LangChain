package com.example.ticketai.service;

import com.example.ticketai.dto.SearchResultDto;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final EmbeddingModel embeddingModel;

    private final EmbeddingStore<TextSegment> embeddingStore;

    public List<SearchResultDto> search(String query) {

        Embedding queryEmbedding =
                embeddingModel.embed(query).content();

        EmbeddingSearchRequest request =
                EmbeddingSearchRequest.builder()
                        .queryEmbedding(queryEmbedding)
                        .maxResults(5)
                        .build();

        EmbeddingSearchResult<TextSegment> result =
                embeddingStore.search(request);

        return result.matches()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private SearchResultDto toDto(
            EmbeddingMatch<TextSegment> match) {

        TextSegment segment = match.embedded();

        Long ticketId = Long.parseLong(
                segment.metadata().getString("ticketId")
        );

        return SearchResultDto.builder()
                .ticketId(ticketId)
                .description(segment.text())
                .score(match.score())
                .build();
    }
}