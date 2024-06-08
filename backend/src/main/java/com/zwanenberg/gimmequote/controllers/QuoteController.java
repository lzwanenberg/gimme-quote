package com.zwanenberg.gimmequote.controllers;

import com.zwanenberg.gimmequote.models.Quote;
import com.zwanenberg.gimmequote.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/")
    public Quote index() {
        return quoteService.getQuote();
    }
}
