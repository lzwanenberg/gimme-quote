package com.zwanenberg.gimmequote.quote_aggregator;

import com.zwanenberg.gimmequote.quote_sources.QuoteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteServiceProvider {
    private final List<QuoteSource> quoteSources;
    private final Random random;

    @Autowired
    public QuoteServiceProvider(List<QuoteSource> quoteSources) {
        this.quoteSources = quoteSources;
        this.random = new Random();
    }

    public QuoteSource getRandom() {
        int index = random.nextInt(quoteSources.size());
        return quoteSources.get(index);
    }
}
