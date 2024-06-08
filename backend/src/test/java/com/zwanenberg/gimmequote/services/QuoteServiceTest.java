package com.zwanenberg.gimmequote.services;
import com.zwanenberg.gimmequote.models.Quote;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuoteServiceTest {

    @Test
    public void testGetQuote() {
        QuoteService quoteService = new QuoteService();
        String expectedAuthor = "Gandalf";
        String expectedContent = "All we have to decide is what to do with the time that is given us.";

        Quote quote = quoteService.getQuote();

        assertEquals(expectedAuthor, quote.getAuthor());
        assertEquals(expectedContent, quote.getContent());
    }
}