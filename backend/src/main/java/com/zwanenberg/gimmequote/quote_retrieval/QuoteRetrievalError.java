package com.zwanenberg.gimmequote.quote_retrieval;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteRetrievalError {
    private int httpStatus;
    private String message;
}
