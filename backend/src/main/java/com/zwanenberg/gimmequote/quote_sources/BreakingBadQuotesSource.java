package com.zwanenberg.gimmequote.quote_sources;

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
public class BreakingBadQuotesSource implements QuoteSource {
    public static final String URL = "https://api.breakingbadquotes.xyz/v1/quotes";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public BreakingBadQuotesSource(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getName() {
        return "breakingbadquotes.xyz";
    }

    @Override
    public Either<QuoteFetchError, Quote> fetchQuote() {
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.getForEntity(URL, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                BreakingBadQuote[] breakingBadQuotes = objectMapper.readValue(response.getBody(), BreakingBadQuote[].class);

                if (breakingBadQuotes.length > 0) {
                    BreakingBadQuote breakingBadQuote = breakingBadQuotes[0];
                    Quote quote = new Quote(breakingBadQuote.getAuthor(), breakingBadQuote.getQuote());

                    return Either.right(quote);
                }
            }

            QuoteFetchError error = new QuoteFetchError();
            error.setResponse(response);
            return Either.left(error);
        } catch (Exception e) {
            QuoteFetchError error = new QuoteFetchError();
            error.setException(e);
            error.setResponse(response);
            return Either.left(error);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class BreakingBadQuote {
        private String quote;
        private String author;
    }
}
