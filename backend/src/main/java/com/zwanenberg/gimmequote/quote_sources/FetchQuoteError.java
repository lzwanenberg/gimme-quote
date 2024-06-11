package com.zwanenberg.gimmequote.quote_sources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

/**
 * Represents an error that occurred during the retrieval of a quote.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchQuoteError {
    /**
     * The exception that is associated with the unsuccessful attempt to fetch and parse the quote, if any.
     */
    private Exception exception;

    /**
     * The response that is associated with the unsuccessful attempt to fetch and parse the quote.
     */
    private ResponseEntity<?> response;
}
