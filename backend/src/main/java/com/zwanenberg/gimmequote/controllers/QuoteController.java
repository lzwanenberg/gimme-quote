package com.zwanenberg.gimmequote.controllers;

import com.zwanenberg.gimmequote.models.Quote;
import com.zwanenberg.gimmequote.services.QuoteAggregatorService;
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
    public ResponseEntity<Quote> getQuote() throws Exception {
        Quote quote = quoteAggregatorService.getQuote();

        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .body(quote);
    }
}
