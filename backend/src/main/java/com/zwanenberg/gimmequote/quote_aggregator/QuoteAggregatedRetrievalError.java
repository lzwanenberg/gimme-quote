package com.zwanenberg.gimmequote.quote_aggregator;

import com.zwanenberg.gimmequote.quote_sources.FetchQuoteError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteAggregatedRetrievalError {
    private String serviceName;
    private FetchQuoteError error;
}