package com.zwanenberg.gimmequote.quote_aggregator;

import com.zwanenberg.gimmequote.quote_sources.QuoteSource;
import com.zwanenberg.gimmequote.quote_sources.QuoteSourcesProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RandomQuoteSourceProviderTest {
    @Mock
    QuoteSourcesProvider quoteSourcesProvider;

    @Mock
    Random random;

    @Test
    public void testGetReturnsRandomQuoteSource() {
        QuoteSource source0 = Mockito.mock(QuoteSource.class);
        QuoteSource source1 = Mockito.mock(QuoteSource.class);
        QuoteSource source2 = Mockito.mock(QuoteSource.class);

        List<QuoteSource> quoteSources = List.of(source0, source1, source2);

        when(quoteSourcesProvider.get()).thenReturn(quoteSources);

        RandomQuoteSourceProvider provider = new RandomQuoteSourceProvider(quoteSourcesProvider, random);

        when(random.nextInt(3)).thenReturn(1);
        assertEquals(source1, provider.get());

        when(random.nextInt(3)).thenReturn(0);
        assertEquals(source0, provider.get());

        when(random.nextInt(3)).thenReturn(2);
        assertEquals(source2, provider.get());
    }
}