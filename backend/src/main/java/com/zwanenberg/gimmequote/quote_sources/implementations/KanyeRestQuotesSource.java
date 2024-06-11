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
public class KanyeRestQuotesSource implements QuoteSource {
    public static final String URL = "https://api.kanye.rest/";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public KanyeRestQuotesSource(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getName() {
        return "kanye.rest";
    }

    @Override
    public FetchQuoteResult fetchQuote() {
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.getForEntity(URL, String.class);

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null)
                return FetchQuoteResult.createError(response);

            KanyeRestQuote kanyeRestQuote = objectMapper
                    .readValue(response.getBody(), KanyeRestQuote.class);

            Quote quote = new Quote("Kanye West", kanyeRestQuote.getQuote());

            return FetchQuoteResult.createSuccess(quote);
        } catch (Exception exception) {
            return FetchQuoteResult.createError(response, exception);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class KanyeRestQuote {
        private String quote;
    }
}
