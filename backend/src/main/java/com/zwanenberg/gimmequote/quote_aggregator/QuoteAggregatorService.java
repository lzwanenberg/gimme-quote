package com.zwanenberg.gimmequote.quote_aggregator;

import com.zwanenberg.gimmequote.models.Quote;
import com.zwanenberg.gimmequote.quote_sources.FetchQuoteError;
import com.zwanenberg.gimmequote.quote_sources.FetchQuoteResult;
import com.zwanenberg.gimmequote.quote_sources.QuoteSource;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteAggregatorService {

    private final RandomQuoteSourceProvider randomQuoteSourceProvider;

    @Autowired
    public QuoteAggregatorService(RandomQuoteSourceProvider randomQuoteSourceProvider) {
        this.randomQuoteSourceProvider = randomQuoteSourceProvider;
    }

    public Either<QuoteAggregatedRetrievalError, Quote> getQuote() {
        QuoteSource randomService = randomQuoteSourceProvider.get();
        FetchQuoteResult result = randomService.fetchQuote();

        if (!result.isSuccessful()) {
            String serviceName = randomService.getName();
            FetchQuoteError error = result.getError();

            return Either.left(new QuoteAggregatedRetrievalError(serviceName, error));
        }

        return Either.right(result.getQuote());
    }
}
