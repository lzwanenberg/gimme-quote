package com.zwanenberg.gimmequote.quote_aggregator;
import com.zwanenberg.gimmequote.models.Quote;
import com.zwanenberg.gimmequote.quote_retrieval.QuoteRetrievalError;
import com.zwanenberg.gimmequote.quote_retrieval.QuoteService;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuoteAggregatorServiceTest {
    private QuoteAggregatorService quoteAggregatorService;

    @BeforeEach
    public void setUp() {
        QuoteServiceProvider quoteServiceProvider = Mockito.mock(QuoteServiceProvider.class);
        QuoteService randomService = Mockito.mock(QuoteService.class);
        Quote quote = new Quote("Gandalf", "All we have to decide is what to do with the time that is given us.");
        Either<QuoteRetrievalError, Quote> result = Either.right(quote);

        Mockito.when(randomService.fetchQuote()).thenReturn(result);
        Mockito.when(quoteServiceProvider.getRandom()).thenReturn(randomService);

        quoteAggregatorService = new QuoteAggregatorService(quoteServiceProvider);
    }

    @Test
    public void testGetQuote() {
        String expectedAuthor = "Gandalf";
        String expectedContent = "All we have to decide is what to do with the time that is given us.";

        Either<QuoteAggregatedRetrievalError, Quote> result = quoteAggregatorService.getQuote();
        Quote quote = result.get();

        assertTrue(result.isRight());
        assertEquals(expectedAuthor, quote.getAuthor());
        assertEquals(expectedContent, quote.getContent());
    }
}