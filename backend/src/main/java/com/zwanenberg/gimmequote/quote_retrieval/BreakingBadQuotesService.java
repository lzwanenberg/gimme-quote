package com.zwanenberg.gimmequote.quote_retrieval;

import com.zwanenberg.gimmequote.models.Quote;
import io.vavr.control.Either;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Service
public class BreakingBadQuotesService implements QuoteService {
    private final RestTemplate restTemplate;

    @Autowired
    public BreakingBadQuotesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getName() {
        return "breakingbadquotes.xyz";
    }

    @Override
    public Either<QuoteRetrievalError, Quote> fetchQuote() {
        String url = "https://api.breakingbadquotes.xyz/v1/quotes";

        ResponseEntity<BreakingBadQuote[]> response = restTemplate.getForEntity(url, BreakingBadQuote[].class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && response.getBody().length > 0) {
            BreakingBadQuote breakingBadQuote = response.getBody()[0];
            Quote quote = new Quote(breakingBadQuote.getAuthor(), breakingBadQuote.getQuote());
            return Either.right(quote);
        } else {
            return Either.left(new QuoteRetrievalError(response));
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class BreakingBadQuote {
        @JsonProperty("quote")
        private String quote;

        @JsonProperty("author")
        private String author;
    }
}
