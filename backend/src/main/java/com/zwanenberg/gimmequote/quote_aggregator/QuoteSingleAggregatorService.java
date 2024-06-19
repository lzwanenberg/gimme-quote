package com.zwanenberg.gimmequote.quote_aggregator;

import com.zwanenberg.gimmequote.quote_sources.FetchQuoteResult;
import com.zwanenberg.gimmequote.quote_sources.QuoteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that aggregates all configured quote sources by fetching a quote from a random source
 */
@Service
public class QuoteSingleAggregatorService {

    private final RandomQuoteSourceProvider randomQuoteSourceProvider;

    @Autowired
    public QuoteSingleAggregatorService(RandomQuoteSourceProvider randomQuoteSourceProvider) {
        this.randomQuoteSourceProvider = randomQuoteSourceProvider;
    }

    /**
     * Fetch quote from a random source
     *
     * @return {@link QuoteAggregatorResult} containing the used quote source and the actual quote or error information.
     */
    public QuoteAggregatorResult getQuote() {
        QuoteSource randomSource = randomQuoteSourceProvider.get();
        FetchQuoteResult result = randomSource.fetchQuote();
        return new QuoteAggregatorResult(randomSource.getName(), result);
    }
}
