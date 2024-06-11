package com.zwanenberg.gimmequote.quote_aggregator;

import com.zwanenberg.gimmequote.quote_sources.QuoteSource;
import com.zwanenberg.gimmequote.quote_sources.QuoteSourcesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Service that provides a random quote source
 */
@Service
public class RandomQuoteSourceProvider {
    private final QuoteSourcesProvider quoteSourcesProvider;
    private final Random random;

    @Autowired
    public RandomQuoteSourceProvider(QuoteSourcesProvider quoteSourcesProvider, Random random) {
        this.quoteSourcesProvider = quoteSourcesProvider;
        this.random = random;
    }

    /**
     * Get a random quote source
     *
     * @return Random quote source
     */
    public QuoteSource get() {
        List<QuoteSource> quoteSources = quoteSourcesProvider.get();
        int index = random.nextInt(quoteSources.size());
        return quoteSources.get(index);
    }
}
