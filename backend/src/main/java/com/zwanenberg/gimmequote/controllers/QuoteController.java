package com.zwanenberg.gimmequote.controllers;

import com.zwanenberg.gimmequote.models.Quote;
import com.zwanenberg.gimmequote.quote_aggregator.QuoteAggregatedRetrievalError;
import com.zwanenberg.gimmequote.quote_aggregator.QuoteAggregatorService;
import io.vavr.control.Either;
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
        Either<QuoteAggregatedRetrievalError, Quote> result = quoteAggregatorService.getQuote();

        if (result.isLeft()) {
            QuoteAggregatedRetrievalError error = result.getLeft();

            return ResponseEntity.internalServerError()
                    .header("Access-Control-Allow-Origin", "*")
                    .body(error);
        }

        Quote quote = result.get();

        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .body(quote);
    }
}
