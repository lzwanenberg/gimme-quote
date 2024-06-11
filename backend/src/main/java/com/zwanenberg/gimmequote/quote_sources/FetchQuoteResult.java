package com.zwanenberg.gimmequote.quote_sources;

import com.zwanenberg.gimmequote.models.Quote;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;

/**
 * Represents the result of fetching a quote.
 * This class encapsulates either a successful quote or an error.
 */
public class FetchQuoteResult {
    private final Either<FetchQuoteError, Quote> result;

    /**
     * Constructs a new instance of FetchQuoteResult with the specified result.
     *
     * @param result The result of fetching a quote.
     */
    private FetchQuoteResult(Either<FetchQuoteError, Quote> result) {
        this.result = result;
    }

    /**
     * Creates a FetchQuoteResult representing a successful quote retrieval.
     *
     * @param quote The retrieved quote.
     * @return A FetchQuoteResult representing a successful quote retrieval.
     */
    public static FetchQuoteResult createSuccess(Quote quote) {
        return new FetchQuoteResult(Either.right(quote));
    }


    /**
     * Creates a FetchQuoteResult representing a quote retrieval error.
     *
     * @param response The response entity indicating the error.
     * @return A FetchQuoteResult representing a quote retrieval error.
     */
    public static FetchQuoteResult createError(ResponseEntity<String> response) {
        return createError(response, null);
    }

    /**
     * Creates a FetchQuoteResult representing a quote retrieval error with the given exception.
     *
     * @param response The response entity indicating the error.
     * @param e        The exception associated with the error.
     * @return A FetchQuoteResult representing a quote retrieval error with the given exception.
     */
    public static FetchQuoteResult createError(ResponseEntity<String> response, Exception e) {
        FetchQuoteError error = new FetchQuoteError();

        error.setResponse(response);
        error.setException(e);

        return new FetchQuoteResult(Either.left(error));
    }

    /**
     * Checks if the quote retrieval was successful.
     *
     * @return {@code true} if the quote retrieval was successful, {@code false} otherwise.
     */
    public boolean isSuccessful() {
        return result.isRight();
    }

    /**
     * Retrieves the fetched quote, if available.
     *
     * @return The fetched quote if the retrieval was successful, or {@code null} otherwise.
     */
    public Quote getQuote() {
        return result.isRight() ? result.get() : null;
    }

    /**
     * Retrieves the error associated with the quote retrieval, if any.
     *
     * @return The error associated with the quote retrieval, or {@code null} if no error occurred.
     */
    public FetchQuoteError getError() {
        return result.isLeft() ? result.getLeft() : null;
    }
}
