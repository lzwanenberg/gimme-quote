package com.zwanenberg.gimmequote.controllers;

import com.zwanenberg.gimmequote.quote_aggregator.QuoteAggregatorResult;
import com.zwanenberg.gimmequote.quote_aggregator.QuoteAggregatorService;
import com.zwanenberg.gimmequote.quote_sources.FetchQuoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
    private final QuoteAggregatorService quoteAggregatorService;

    @Autowired
    public QuoteController(QuoteAggregatorService quoteAggregatorService) {
        this.quoteAggregatorService = quoteAggregatorService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuote() throws Exception {
        QuoteAggregatorResult aggregatorResult = quoteAggregatorService.getQuote();
        FetchQuoteResult result = aggregatorResult.getResult();

        return (result.isSuccessful() ? ResponseEntity.ok() : ResponseEntity.internalServerError())
                .header("Access-Control-Allow-Origin", "*")
                .body(result.isSuccessful() ? result.getQuote() : aggregatorResult);
    }
}
