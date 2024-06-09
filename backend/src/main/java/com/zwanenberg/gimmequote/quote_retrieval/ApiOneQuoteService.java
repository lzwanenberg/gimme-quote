package com.zwanenberg.gimmequote.quote_retrieval;

import com.zwanenberg.gimmequote.models.Quote;
import org.springframework.stereotype.Service;
import io.vavr.control.Either;

@Service
public class ApiOneQuoteService implements QuoteService {
    @Override
    public String getName() {
        return "API #1";
    }

    @Override
    public Either<QuoteRetrievalError, Quote> fetchQuote() {
        String text = "Quote from website one";
        String author = "Author One";
        Quote quote = new Quote(author, text);

        return Either.right(quote);
    }
}
