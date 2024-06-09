package com.zwanenberg.gimmequote.quote_retrieval;

import com.zwanenberg.gimmequote.models.Quote;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

@Service
public class ApiFaultyService implements QuoteService {
    @Override
    public String getName() {
        return "Faulty API";
    }

    @Override
    public Either<QuoteRetrievalError, Quote> fetchQuote() {
        QuoteRetrievalError error = new QuoteRetrievalError(500, "Internal server error.");

        return Either.left(error);
    }
}
