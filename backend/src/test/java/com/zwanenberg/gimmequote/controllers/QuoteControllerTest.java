package com.zwanenberg.gimmequote.controllers;

import com.zwanenberg.gimmequote.models.Quote;
import com.zwanenberg.gimmequote.quote_aggregator.QuoteAggregatorResult;
import com.zwanenberg.gimmequote.quote_aggregator.QuoteAggregatorService;
import com.zwanenberg.gimmequote.quote_sources.FetchQuoteResult;
import com.zwanenberg.gimmequote.quote_sources.QuoteSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = QuoteController.class)
@AutoConfigureMockMvc
public class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteAggregatorService quoteAggregatorService;

    @Mock
    private QuoteSource quoteSource;

    @Test
    public void testIndexSuccessful() throws Exception {
        Quote quote = new Quote("Gandalf", "All we have to decide is what to do with the time that is given us.");
        FetchQuoteResult fetchQuoteResult = FetchQuoteResult.createSuccess(quote);
        QuoteAggregatorResult result = new QuoteAggregatorResult(quoteSource, fetchQuoteResult);

        when(quoteAggregatorService.getQuote()).thenReturn(result);

        mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(jsonPath("$.author").value("Gandalf"))
                .andExpect(jsonPath("$.content").value("All we have to decide is what to do with the time that is given us."));
    }
}
