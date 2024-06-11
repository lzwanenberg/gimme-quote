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

class QuotableQuotesSourceTest {
    @Mock
    private RestTemplate restTemplate;

    private QuotableQuotesSource quotableQuotesService;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        quotableQuotesService = new QuotableQuotesSource(restTemplate, new ObjectMapper());
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (closeable == null) return;
        closeable.close();
    }

    @Test
    public void testGetName() {
        assertEquals("quotable.io", quotableQuotesService.getName());
    }

    @Test
    public void testFetchQuoteSuccess() {
        String url = "https://api.quotable.io/quotes/random";
        String responseBody = """
                    [
                        {
                            "_id": "0pIzPhrPW2",
                            "content": "You'll see it when you believe it.",
                            "author": "Wayne Dyer",
                            "tags": ["Famous Quotes"],
                            "authorSlug": "wayne-dyer",
                            "length": 34,
                            "dateAdded": "2020-12-17",
                            "dateModified": "2023-04-14"
                        }
                    ]
                """;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(responseEntity);

        FetchQuoteResult result = quotableQuotesService.fetchQuote();

        assertTrue(result.isSuccessful());
        assertEquals("Wayne Dyer", result.getQuote().getAuthor());
        assertEquals("You'll see it when you believe it.", result.getQuote().getContent());
    }

    @Test
    public void testFetchQuoteEmptyBody() {
        String responseBody = "[]";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(responseEntity);

        FetchQuoteResult result = quotableQuotesService.fetchQuote();

        assertFalse(result.isSuccessful());
        assertNotNull(result.getError().getResponse());
    }

    @Test
    public void testFetchQuoteErrorResponse() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(responseEntity);

        FetchQuoteResult result = quotableQuotesService.fetchQuote();

        assertFalse(result.isSuccessful());
        assertNotNull(result.getError().getResponse());
    }
}
