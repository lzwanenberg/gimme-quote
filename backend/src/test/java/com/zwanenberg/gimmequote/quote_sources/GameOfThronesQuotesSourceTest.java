package com.zwanenberg.gimmequote.quote_sources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwanenberg.gimmequote.models.Quote;
import io.vavr.control.Either;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class GameOfThronesQuotesSourceTest {
    @Mock
    private RestTemplate restTemplate;

    private GameOfThronesQuotesSource gameOfThronesQuotesService;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        gameOfThronesQuotesService = new GameOfThronesQuotesSource(restTemplate, new ObjectMapper());
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (closeable == null) return;
        closeable.close();
    }

    @Test
    public void testGetName() {
        assertEquals("gameofthronesquotes.xyz", gameOfThronesQuotesService.getName());
    }

    @Test
    public void testFetchQuoteSuccess() {
        String url = "https://api.gameofthronesquotes.xyz/v1/random";
        String responseBody = """
            {
                "sentence": "A Lannister always pays his debts.",
                "character": {
                    "name": "Tyrion Lannister",
                    "slug": "tyrion",
                    "house": {
                        "name": "House Lannister of Casterly Rock",
                        "slug": "lannister"
                    }
                }
            }
        """;

        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(responseEntity);

        Either<QuoteFetchError, Quote> result = gameOfThronesQuotesService.fetchQuote();

        assertTrue(result.isRight());
        assertEquals("Tyrion Lannister", result.get().getAuthor());
        assertEquals("A Lannister always pays his debts.", result.get().getContent());
    }

    @Test
    public void testFetchQuoteEmptyBody() {
        String responseBody = "";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(responseEntity);

        Either<QuoteFetchError, Quote> result = gameOfThronesQuotesService.fetchQuote();

        assertTrue(result.isLeft());
        assertNotNull(result.getLeft().getResponse());
    }

    @Test
    public void testFetchQuoteErrorResponse() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(responseEntity);

        Either<QuoteFetchError, Quote> result = gameOfThronesQuotesService.fetchQuote();

        assertTrue(result.isLeft());
        assertNotNull(result.getLeft().getResponse());
    }
}