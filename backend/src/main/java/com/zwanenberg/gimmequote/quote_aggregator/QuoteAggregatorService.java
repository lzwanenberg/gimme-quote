package com.zwanenberg.gimmequote.quote_aggregator;

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

    public Either<QuoteAggregatedRetrievalError, Quote> getQuote() {
        QuoteService randomService = quoteServiceProvider.getRandom();
        Either<QuoteRetrievalError, Quote> result = randomService.fetchQuote();

        if (result.isLeft()) {
            String serviceName = randomService.getName();
            QuoteRetrievalError error = result.getLeft();

            return Either.left(new QuoteAggregatedRetrievalError(serviceName, error));
        }

        return Either.right(result.get());
    }
}
