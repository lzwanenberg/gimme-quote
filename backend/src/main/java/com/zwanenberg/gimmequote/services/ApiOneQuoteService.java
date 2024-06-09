package com.zwanenberg.gimmequote.services;

import com.zwanenberg.gimmequote.models.Quote;
import org.springframework.stereotype.Service;

@Service
public class ApiOneQuoteService implements QuoteService {
    @Override
    public Quote fetchQuote() {
        String text = "Quote from website one";
        String author = "Author One";
        return new Quote(author, text);
    }
}
