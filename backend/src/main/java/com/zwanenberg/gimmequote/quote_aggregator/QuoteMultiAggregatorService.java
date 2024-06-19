package com.zwanenberg.gimmequote.quote_aggregator;

import com.zwanenberg.gimmequote.quote_sources.FetchQuoteResult;
import com.zwanenberg.gimmequote.quote_sources.QuoteSource;
import com.zwanenberg.gimmequote.quote_sources.QuoteSourcesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;

/**
 * Service that aggregates all configured quote sources by fetching a quote from a multiple sources
 * and returning with the fastest response
 */
@Service
public class QuoteMultiAggregatorService {

    private final QuoteSourcesProvider quoteSourceProvider;

    @Autowired
    public QuoteMultiAggregatorService(QuoteSourcesProvider quoteSourceProvider) {
        this.quoteSourceProvider = quoteSourceProvider;
    }

    /**
     * Fetch quote from a random source
     *
     * @return {@link QuoteAggregatorResult} containing the used quote source which responded the fastest and the actual quote or error information.
     */
    public QuoteAggregatorResult getQuote() {
        List<QuoteSource> sources = quoteSourceProvider.get();

        CompletionService<FetchQuoteResult> service
                = new ExecutorCompletionService<>(Executors.newCachedThreadPool());

        for (QuoteSource source : sources) {
            service.submit(source::fetchQuote);
        }

        try {
            FetchQuoteResult fetchQuoteResult = service.take().get();
             return new QuoteAggregatorResult(
                    "source",
                    fetchQuoteResult
            );
        } catch (Exception e) {
            // too bad
        } finally {
            // cancel all pending stuff
        }

        return null;
    }
}
