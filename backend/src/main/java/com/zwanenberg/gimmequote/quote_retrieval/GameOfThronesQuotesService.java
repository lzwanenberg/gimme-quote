package com.zwanenberg.gimmequote.quote_retrieval;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwanenberg.gimmequote.models.Quote;
import io.vavr.control.Either;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameOfThronesQuotesService implements QuoteService {
    public static final String URL = "https://api.gameofthronesquotes.xyz/v1/random";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public GameOfThronesQuotesService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getName() {
        return "gameofthronesquotes.xyz";
    }

    @Override
    public Either<QuoteRetrievalError, Quote> fetchQuote() {
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.getForEntity(URL, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                GameOfThronesQuote gameOfThronesQuote = objectMapper.readValue(response.getBody(), GameOfThronesQuote.class);

                Quote quote = new Quote(gameOfThronesQuote.getCharacter().getName(), gameOfThronesQuote.getSentence());
                return Either.right(quote);
            }

            QuoteRetrievalError error = new QuoteRetrievalError();
            error.setResponse(response);
            return Either.left(error);
        } catch (Exception e) {
            QuoteRetrievalError error = new QuoteRetrievalError();
            error.setException(e);
            error.setResponse(response);
            return Either.left(error);
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
