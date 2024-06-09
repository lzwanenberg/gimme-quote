package com.zwanenberg.gimmequote.services;

import com.zwanenberg.gimmequote.quote_retrieval.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteServiceProvider {
    private final List<QuoteService> quoteServices;
    private final Random random;

    public QuoteServiceProvider(List<QuoteService> quoteServices) {
        this.quoteServices = quoteServices;
        this.random = new Random();
    }

    @Autowired
    public QuoteService getRandom() {
        int index = random.nextInt(quoteServices.size());
        return quoteServices.get(index);
    }
}
