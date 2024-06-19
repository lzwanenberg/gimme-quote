package com.zwanenberg.gimmequote.controllers;

import com.zwanenberg.gimmequote.quote_aggregator.QuoteAggregatorResult;
import com.zwanenberg.gimmequote.quote_aggregator.QuoteSingleAggregatorService;
import com.zwanenberg.gimmequote.quote_sources.FetchQuoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
    private final QuoteSingleAggregatorService quoteSingleAggregatorService;

    @Autowired
    public QuoteController(QuoteSingleAggregatorService quoteSingleAggregatorService) {
        this.quoteSingleAggregatorService = quoteSingleAggregatorService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuote() throws Exception {
        QuoteAggregatorResult aggregatorResult = quoteSingleAggregatorService.getQuote();
        FetchQuoteResult result = aggregatorResult.getResult();

        return (result.isSuccessful() ? ResponseEntity.ok() : ResponseEntity.internalServerError())
                .header("Access-Control-Allow-Origin", "*")
                .body(result.isSuccessful() ? result.getQuote() : aggregatorResult);
    }

    @GetMapping("/parallel")
    public ResponseEntity<?> getParallelQuote() throws Exception {
        QuoteAggregatorResult aggregatorResult = quoteSingleAggregatorService.getQuote();
        FetchQuoteResult result = aggregatorResult.getResult();

        return (result.isSuccessful() ? ResponseEntity.ok() : ResponseEntity.internalServerError())
                .header("Access-Control-Allow-Origin", "*")
                .body(result.isSuccessful() ? result.getQuote() : aggregatorResult);
    }

}
