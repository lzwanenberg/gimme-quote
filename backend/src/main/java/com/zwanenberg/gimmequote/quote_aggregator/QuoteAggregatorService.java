package com.zwanenberg.gimmequote.quote_aggregator;

import com.zwanenberg.gimmequote.quote_sources.FetchQuoteResult;
import com.zwanenberg.gimmequote.quote_sources.QuoteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteAggregatorService {

    private final RandomQuoteSourceProvider randomQuoteSourceProvider;

    @Autowired
    public QuoteAggregatorService(RandomQuoteSourceProvider randomQuoteSourceProvider) {
        this.randomQuoteSourceProvider = randomQuoteSourceProvider;
    }

    public QuoteAggregatorResult getQuote() {
        QuoteSource randomSource = randomQuoteSourceProvider.get();
        FetchQuoteResult result = randomSource.fetchQuote();
        return new QuoteAggregatorResult(randomSource, result);
    }
}
