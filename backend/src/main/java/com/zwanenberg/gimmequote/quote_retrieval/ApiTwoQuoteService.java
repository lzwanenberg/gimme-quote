package com.zwanenberg.gimmequote.quote_retrieval;

import com.zwanenberg.gimmequote.models.Quote;
import org.springframework.stereotype.Service;

@Service
public class ApiTwoQuoteService implements QuoteService {
    @Override
    public Quote fetchQuote() {
        String text = "Quote from website two";
        String author = "Author Two";
        return new Quote(author, text);
    }
}
