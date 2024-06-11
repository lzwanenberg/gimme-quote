package com.zwanenberg.gimmequote.quote_aggregator;

import com.zwanenberg.gimmequote.quote_sources.FetchQuoteResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuoteAggregatorResult {
    private final String sourceName;
    private final FetchQuoteResult result;
}
