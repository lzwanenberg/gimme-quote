package com.zwanenberg.gimmequote.quote_retrieval;
import com.zwanenberg.gimmequote.models.Quote;
import io.vavr.control.Either;

public interface QuoteService {
    String getName();
    Either<QuoteRetrievalError, Quote> fetchQuote();
}
