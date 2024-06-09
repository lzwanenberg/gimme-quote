package com.zwanenberg.gimmequote.services;

import com.zwanenberg.gimmequote.models.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteAggregatorService {

    private final QuoteServiceProvider quoteServiceProvider;

    @Autowired
    public QuoteAggregatorService(QuoteServiceProvider quoteServiceProvider) {
        this.quoteServiceProvider = quoteServiceProvider;
    }

    public Quote getQuote() {
        return quoteServiceProvider.getRandom().fetchQuote();
    }
}
