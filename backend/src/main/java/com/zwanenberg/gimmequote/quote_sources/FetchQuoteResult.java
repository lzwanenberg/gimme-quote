package com.zwanenberg.gimmequote.quote_sources;

import com.zwanenberg.gimmequote.models.Quote;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;

public class FetchQuoteResult {
    private final Either<FetchQuoteError, Quote> result;

    private FetchQuoteResult(Either<FetchQuoteError, Quote> result) {
        this.result = result;
    }

    public static FetchQuoteResult createSuccess(Quote quote) {
        return new FetchQuoteResult(Either.right(quote));
    }

    public static FetchQuoteResult createError(ResponseEntity<String> response) {
        return createError(response, null);
    }

    public static FetchQuoteResult createError(ResponseEntity<String> response, Exception e) {
        FetchQuoteError error = new FetchQuoteError();

        error.setResponse(response);
        error.setException(e);

        return new FetchQuoteResult(Either.left(error));
    }

    public boolean isSuccessful() {
        return result.isRight();
    }

    public Quote getQuote() {
        return result.isRight() ? result.get() : null;
    }

    public FetchQuoteError getError() {
        return result.isLeft() ? result.getLeft() : null;
    }
}
