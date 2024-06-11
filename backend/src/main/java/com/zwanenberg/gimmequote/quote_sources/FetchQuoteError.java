package com.zwanenberg.gimmequote.quote_sources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchQuoteError {
    private Exception exception;
    private ResponseEntity<?> response;
}
