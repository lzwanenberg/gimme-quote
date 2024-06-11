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

class QuoteGardenQuotesSourceTest {
    @Mock
    private RestTemplate restTemplate;

    private QuoteGardenQuotesSource quoteGardenQuotesService;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        quoteGardenQuotesService = new QuoteGardenQuotesSource(restTemplate, new ObjectMapper());
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (closeable == null) return;
        closeable.close();
    }

    @Test
    public void testGetName() {
        assertEquals("quote-garden.onrender.com", quoteGardenQuotesService.getName());
    }

    @Test
    public void testFetchQuoteSuccess() {
        String url = "https://quote-garden.onrender.com/api/v3/quotes/random";
        String responseBody = """
                    {
                        "statusCode": 200,
                        "message": "Random quotes",
                        "pagination": {
                            "currentPage": 1,
                            "nextPage": null,
                            "totalPages": 1
                        },
                        "totalQuotes": 1,
                        "data": [
                            {
                                "_id": "5eb17ab2b69dc744b4e7ec26",
                                "quoteText": "To be, or not to be, that is the question.",
                                "quoteAuthor": "William Shakespeare",
                                "quoteGenre": "existentialism",
                                "__v": 0
                            }
                        ]
                    }
                """;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(responseEntity);

        FetchQuoteResult result = quoteGardenQuotesService.fetchQuote();

        assertTrue(result.isSuccessful());
        assertEquals("William Shakespeare", result.getQuote().getAuthor());
        assertEquals("To be, or not to be, that is the question.", result.getQuote().getContent());
    }

    @Test
    public void testFetchQuoteEmptyBody() {
        String responseBody = "";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(responseEntity);

        FetchQuoteResult result = quoteGardenQuotesService.fetchQuote();

        assertFalse(result.isSuccessful());
        assertNotNull(result.getError().getResponse());
    }

    @Test
    public void testFetchQuoteErrorResponse() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(responseEntity);

        FetchQuoteResult result = quoteGardenQuotesService.fetchQuote();

        assertFalse(result.isSuccessful());
        assertNotNull(result.getError().getResponse());
    }
}