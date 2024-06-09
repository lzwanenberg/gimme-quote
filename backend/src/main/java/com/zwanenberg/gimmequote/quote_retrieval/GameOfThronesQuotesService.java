package com.zwanenberg.gimmequote.quote_retrieval;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private final RestTemplate restTemplate;

    @Autowired
    public GameOfThronesQuotesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return "gameofthronesquotes.xyz";
    }

    @Override
    public Either<QuoteRetrievalError, Quote> fetchQuote() {
        String url = "https://api.gameofthronesquotes.xyz/v1/random";

        ResponseEntity<GameOfThronesQuote> response = restTemplate.getForEntity(url, GameOfThronesQuote.class);

        try {
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                GameOfThronesQuote gameOfThronesQuote = response.getBody();
                Quote quote = new Quote(gameOfThronesQuote.getCharacter().getName(), gameOfThronesQuote.getSentence());
                return Either.right(quote);
            } else {
                QuoteRetrievalError error = new QuoteRetrievalError();
                error.setResponse(response);
                return Either.left(error);
            }
        } catch (Exception e) {
            QuoteRetrievalError error = new QuoteRetrievalError();
            error.setException(e);
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
