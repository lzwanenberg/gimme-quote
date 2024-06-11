package com.zwanenberg.gimmequote.quote_sources;

public interface QuoteSource {
    String getName();
    FetchQuoteResult fetchQuote();
}
