package com.zwanenberg.gimmequote.services;

import com.zwanenberg.gimmequote.models.Quote;
import com.zwanenberg.gimmequote.quote_retrieval.QuoteRetrievalError;
import com.zwanenberg.gimmequote.quote_retrieval.QuoteService;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteAggregatorService {

    private final QuoteServiceProvider quoteServiceProvider;

    @Autowired
    public QuoteAggregatorService(QuoteServiceProvider quoteServiceProvider) {
        this.quoteServiceProvider = quoteServiceProvider;
    }

    public Quote getQuote() {
        QuoteService randomService = quoteServiceProvider.getRandom();
        Either<QuoteRetrievalError, Quote> result = randomService.fetchQuote();

        if (result.isLeft()) {
            String serviceName = randomService.getName();
            QuoteRetrievalError error = result.getLeft();

            // TODO: handle error properly
            return new Quote("ERROR " + serviceName, error.getMessage());
        }

        return result.get();
    }
}
