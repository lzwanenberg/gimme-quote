package com.zwanenberg.gimmequote.quote_sources.implementations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwanenberg.gimmequote.models.Quote;
import com.zwanenberg.gimmequote.quote_sources.FetchQuoteResult;
import com.zwanenberg.gimmequote.quote_sources.QuoteSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameOfThronesQuotesSource implements QuoteSource {
    public static final String URL = "https://api.gameofthronesquotes.xyz/v1/random";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public GameOfThronesQuotesSource(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getName() {
        return "gameofthronesquotes.xyz";
    }

    @Override
    public FetchQuoteResult fetchQuote() {
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.getForEntity(URL, String.class);

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null)
                return FetchQuoteResult.createError(response);

            GameOfThronesQuote gameOfThronesQuote = objectMapper.readValue(response.getBody(), GameOfThronesQuote.class);
            Quote quote = new Quote(gameOfThronesQuote.getCharacter().getName(), gameOfThronesQuote.getSentence());

            return FetchQuoteResult.createSuccess(quote);
        } catch (Exception exception) {
            return FetchQuoteResult.createError(response, exception);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    private static class Character {
        private String name;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    private static class GameOfThronesQuote {
        private String sentence;
        private Character character;
    }
}
