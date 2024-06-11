package com.zwanenberg.gimmequote.quote_sources.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwanenberg.gimmequote.quote_sources.FetchQuoteResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

class BreakingBadQuotesSourceTest {
    @Mock
    private RestTemplate restTemplate;

    private BreakingBadQuotesSource breakingBadQuotesService;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        breakingBadQuotesService = new BreakingBadQuotesSource(restTemplate, new ObjectMapper());
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (closeable == null) return;
        closeable.close();
    }

    @Test
    public void testGetName() {
        assertEquals("breakingbadquotes.xyz", breakingBadQuotesService.getName());
    }

    @Test
    public void testFetchQuoteSuccess() {
        String url = "https://api.breakingbadquotes.xyz/v1/quotes";
        String responseBody = """
                    [
                        {
                            "quote": "I am the one who knocks!",
                            "author": "Walter White"
                        }
                    ]
                """;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(responseEntity);

        FetchQuoteResult result = breakingBadQuotesService.fetchQuote();

        assertTrue(result.isSuccessful());
        assertEquals("Walter White", result.getQuote().getAuthor());
        assertEquals("I am the one who knocks!", result.getQuote().getContent());
    }

    @Test
    public void testFetchQuoteEmptyBody() {
        String responseBody = "";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(responseEntity);

        FetchQuoteResult result = breakingBadQuotesService.fetchQuote();

        assertFalse(result.isSuccessful());
        assertNotNull(result.getError().getResponse());
    }

    @Test
    public void testFetchQuoteErrorResponse() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(responseEntity);

        FetchQuoteResult result = breakingBadQuotesService.fetchQuote();

        assertFalse(result.isSuccessful());
        assertNotNull(result.getError().getResponse());
    }
}