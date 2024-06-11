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

class KanyeRestQuotesSourceTest {
    @Mock
    private RestTemplate restTemplate;

    private KanyeRestQuotesSource kanyeRestQuotesService;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        kanyeRestQuotesService = new KanyeRestQuotesSource(restTemplate, new ObjectMapper());
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (closeable == null) return;
        closeable.close();
    }

    @Test
    public void testGetName() {
        assertEquals("kanye.rest", kanyeRestQuotesService.getName());
    }

    @Test
    public void testFetchQuoteSuccess() {
        String url = "https://api.kanye.rest/";
        String responseBody = """
                    {
                        "quote": "I am one of the most famous people on the planet"
                    }
                """;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(responseEntity);

        FetchQuoteResult result = kanyeRestQuotesService.fetchQuote();

        assertTrue(result.isSuccessful());
        assertEquals("Kanye West", result.getQuote().getAuthor());
        assertEquals("I am one of the most famous people on the planet", result.getQuote().getContent());
    }

    @Test
    public void testFetchQuoteEmptyBody() {
        String responseBody = "";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(responseEntity);

        FetchQuoteResult result = kanyeRestQuotesService.fetchQuote();

        assertFalse(result.isSuccessful());
        assertNotNull(result.getError().getResponse());
    }

    @Test
    public void testFetchQuoteErrorResponse() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(responseEntity);

        FetchQuoteResult result = kanyeRestQuotesService.fetchQuote();

        assertFalse(result.isSuccessful());
        assertNotNull(result.getError().getResponse());
    }
}
