package com.zwanenberg.gimmequote.quote_sources;
import com.zwanenberg.gimmequote.models.Quote;
import io.vavr.control.Either;

public interface QuoteSource {
    String getName();
    Either<QuoteFetchError, Quote> fetchQuote();
}
