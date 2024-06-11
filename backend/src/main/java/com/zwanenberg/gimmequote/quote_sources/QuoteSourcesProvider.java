package com.zwanenberg.gimmequote.quote_sources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides access to a list of {@link QuoteSource} instances.
 */
@Service
public class QuoteSourcesProvider {
    private final List<QuoteSource> quoteSources;

    @Autowired
    public QuoteSourcesProvider(List<QuoteSource> quoteSources) {
        this.quoteSources = quoteSources;
    }

    /**
     * @return List of available quote source instances.
     */
    public List<QuoteSource> get() {
        return this.quoteSources;
    }
}
