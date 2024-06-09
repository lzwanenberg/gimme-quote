package com.zwanenberg.gimmequote.quote_aggregator;

import com.zwanenberg.gimmequote.quote_retrieval.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteServiceProvider {
    private final List<QuoteService> quoteServices;
    private final Random random;

    @Autowired
    public QuoteServiceProvider(List<QuoteService> quoteServices) {
        this.quoteServices = quoteServices;
        this.random = new Random();
    }

    public QuoteService getRandom() {
        int index = random.nextInt(quoteServices.size());
        return quoteServices.get(index);
    }
}
