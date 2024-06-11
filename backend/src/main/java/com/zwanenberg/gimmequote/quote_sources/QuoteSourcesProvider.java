package com.zwanenberg.gimmequote.quote_sources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteSourcesProvider {
    private final List<QuoteSource> quoteSources;

    @Autowired
    public QuoteSourcesProvider(List<QuoteSource> quoteSources) {
        this.quoteSources = quoteSources;
    }

    public List<QuoteSource> get() {
        return this.quoteSources;
    }
}
