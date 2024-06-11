package com.zwanenberg.gimmequote.quote_sources;

import com.zwanenberg.gimmequote.quote_sources.implementations.BreakingBadQuotesSource;
import com.zwanenberg.gimmequote.quote_sources.implementations.GameOfThronesQuotesSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuoteSourcesProviderTest {
    @Autowired
    private QuoteSourcesProvider quoteSourcesProvider;

    @Test
    public void testProvidesQuoteSources() {
        List<Class<? extends QuoteSource>> expected = List.of(
                BreakingBadQuotesSource.class,
                GameOfThronesQuotesSource.class
        );

        List<Class<?>> actual = quoteSourcesProvider.get().stream()
                .map(QuoteSource::getClass)
                .collect(Collectors.toList());

        assertEquals(expected.size(), actual.size());
        assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    }
}