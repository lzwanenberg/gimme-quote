package com.zwanenberg.gimmequote.quote_sources;

import com.zwanenberg.gimmequote.models.Quote;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class FetchQuoteResultTest {
    @Test
    public void testCreateSuccess() {
        Quote quote = new Quote("Test Quote", "Test Author");
        FetchQuoteResult result = FetchQuoteResult.createSuccess(quote);

        assertTrue(result.isSuccessful());
        assertEquals(quote, result.getQuote());
        assertNull(result.getError());
    }

    @Test
    public void testCreateErrorWithoutException() {
        ResponseEntity<String> response = new ResponseEntity<>("Error response", HttpStatus.BAD_REQUEST);
        FetchQuoteResult result = FetchQuoteResult.createError(response);

        assertFalse(result.isSuccessful());
        assertNull(result.getQuote());

        FetchQuoteError error = result.getError();
        assertNotNull(error);
        assertEquals(response, error.getResponse());
        assertNull(error.getException());
    }

    @Test
    public void testCreateErrorWithException() {
        ResponseEntity<String> response = new ResponseEntity<>("Error response", HttpStatus.BAD_REQUEST);
        Exception exception = new RuntimeException("Test Exception");

        FetchQuoteResult result = FetchQuoteResult.createError(response, exception);

        assertFalse(result.isSuccessful());
        assertNull(result.getQuote());

        FetchQuoteError error = result.getError();
        assertNotNull(error);
        assertEquals(response, error.getResponse());
        assertEquals(exception, error.getException());
    }
}
