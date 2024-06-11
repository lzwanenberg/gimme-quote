package com.zwanenberg.gimmequote.quote_sources;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteFetchError {
    private Exception exception;
    private ResponseEntity<?> response;
}
