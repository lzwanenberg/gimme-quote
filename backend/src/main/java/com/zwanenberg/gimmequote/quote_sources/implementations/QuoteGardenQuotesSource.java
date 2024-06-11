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

import java.util.List;

@Service
public class QuoteGardenQuotesSource implements QuoteSource {
    public static final String URL = "https://quote-garden.onrender.com/api/v3/quotes/random";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public QuoteGardenQuotesSource(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getName() {
        return "quote-garden.onrender.com";
    }

    @Override
    public FetchQuoteResult fetchQuote() {
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.getForEntity(URL, String.class);

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null)
                return FetchQuoteResult.createError(response);

            QuoteGardenResponse quoteGardenResponse = objectMapper.readValue(response.getBody(), QuoteGardenResponse.class);

            if (quoteGardenResponse.getData().isEmpty()) return FetchQuoteResult.createError(response);

            QuoteGardenQuote quoteGardenQuote = quoteGardenResponse.getData().getFirst();
            Quote quote = new Quote(quoteGardenQuote.getQuoteAuthor(), quoteGardenQuote.getQuoteText());

            return FetchQuoteResult.createSuccess(quote);
        } catch (Exception exception) {
            return FetchQuoteResult.createError(response, exception);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class QuoteGardenResponse {
        private List<QuoteGardenQuote> data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class QuoteGardenQuote {
        private String quoteText;
        private String quoteAuthor;
    }
}
