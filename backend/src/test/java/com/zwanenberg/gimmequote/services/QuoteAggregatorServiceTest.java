package com.zwanenberg.gimmequote.services;
import com.zwanenberg.gimmequote.models.Quote;
import com.zwanenberg.gimmequote.quote_retrieval.QuoteRetrievalError;
import com.zwanenberg.gimmequote.quote_retrieval.QuoteService;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        Quote quote = quoteAggregatorService.getQuote();

        assertEquals(expectedAuthor, quote.getAuthor());
        assertEquals(expectedContent, quote.getContent());
    }
}