package com.zwanenberg.gimmequote.quote_retrieval;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteRetrievalError {
    private ResponseEntity<?> response;
}
