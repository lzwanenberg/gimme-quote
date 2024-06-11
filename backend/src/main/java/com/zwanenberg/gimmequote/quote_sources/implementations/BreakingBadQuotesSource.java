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
    public FetchQuoteResult fetchQuote() {
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.getForEntity(URL, String.class);

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null)
                return FetchQuoteResult.createError(response);

            BreakingBadQuote[] breakingBadQuotes = objectMapper
                    .readValue(response.getBody(), BreakingBadQuote[].class);

            if (breakingBadQuotes.length == 0)
                return FetchQuoteResult.createError(response);

            BreakingBadQuote breakingBadQuote = breakingBadQuotes[0];
            Quote quote = new Quote(breakingBadQuote.getAuthor(), breakingBadQuote.getQuote());

            return FetchQuoteResult.createSuccess(quote);
        } catch (Exception exception) {
            return FetchQuoteResult.createError(response, exception);
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
