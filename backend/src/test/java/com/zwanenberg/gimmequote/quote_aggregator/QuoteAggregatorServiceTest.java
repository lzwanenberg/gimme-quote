package com.zwanenberg.gimmequote.quote_aggregator;

import com.zwanenberg.gimmequote.quote_sources.FetchQuoteResult;
import com.zwanenberg.gimmequote.quote_sources.QuoteSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class QuoteAggregatorServiceTest {
    @Mock
    private QuoteSource randomSource;

    @Mock
    private FetchQuoteResult fetchQuoteResult;

    @Mock
    private RandomQuoteSourceProvider randomQuoteSourceProvider;

    @Test
    public void testGetQuoteReturnsSourceName() {
        QuoteAggregatorService quoteAggregatorService =
                new QuoteAggregatorService(randomQuoteSourceProvider);

        Mockito.when(randomQuoteSourceProvider.get()).thenReturn(randomSource);
        Mockito.when(randomSource.getName()).thenReturn("some-random-source.example");
        Mockito.when(randomSource.fetchQuote()).thenReturn(fetchQuoteResult);

        QuoteAggregatorResult aggregatorResult = quoteAggregatorService.getQuote();

        assertEquals("some-random-source.example", aggregatorResult.getSourceName());
    }

    @Test
    public void testGetQuoteReturnsFetchQuoteResult() {
        QuoteAggregatorService quoteAggregatorService =
                new QuoteAggregatorService(randomQuoteSourceProvider);

        Mockito.when(randomQuoteSourceProvider.get()).thenReturn(randomSource);
        Mockito.when(randomSource.getName()).thenReturn("some-random-source.example");
        Mockito.when(randomSource.fetchQuote()).thenReturn(fetchQuoteResult);

        QuoteAggregatorResult aggregatorResult = quoteAggregatorService.getQuote();

        assertEquals(fetchQuoteResult, aggregatorResult.getResult());
    }
}