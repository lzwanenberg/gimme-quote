package com.zwanenberg.gimmequote.quote_sources;

/**
 * Represents a source from which to fetch a quote.
 */
public interface QuoteSource {
    /**
     * Retrieves the name of the quote source.
     *
     * @return The name of the quote source.
     */
    String getName();

    /**
     * Fetches a quote from the source.
     *
     * @return A {@link  FetchQuoteResult} representing the result of the fetch operation,
     * which encapsulates either a successfully retrieved quote or an error.
     */
    FetchQuoteResult fetchQuote();
}
