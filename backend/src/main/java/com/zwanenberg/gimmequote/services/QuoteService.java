package com.zwanenberg.gimmequote.services;

import com.zwanenberg.gimmequote.models.Quote;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {
    public Quote getQuote() {
        // TODO: implement this
        return new Quote("Gandalf", "All we have to decide is what to do with the time that is given us.");
    }
}
