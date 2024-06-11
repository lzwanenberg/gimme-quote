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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

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

    @Test
    public void testIndexSuccessfulContainsQuote() throws Exception {
        Quote quote = new Quote("Gandalf", "All we have to decide is what to do with the time that is given us.");
        FetchQuoteResult fetchQuoteResult = FetchQuoteResult.createSuccess(quote);
        QuoteAggregatorResult result = new QuoteAggregatorResult("some-api.example", fetchQuoteResult);

        when(quoteAggregatorService.getQuote()).thenReturn(result);

        mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(jsonPath("$.author").value("Gandalf"))
                .andExpect(jsonPath("$.content").value("All we have to decide is what to do with the time that is given us."));
    }

    @Test
    public void testIndexUnsuccessfulContainsRelevantDebuggingInformation() throws Exception {
        Exception exception = new Exception("Error message provided by service");
        ResponseEntity<String> response =
                new ResponseEntity<>("Error message provided by API", HttpStatus.INTERNAL_SERVER_ERROR);

        FetchQuoteResult fetchQuoteResult = FetchQuoteResult.createError(response, exception);
        QuoteAggregatorResult result = new QuoteAggregatorResult("some-api.example", fetchQuoteResult);
        when(quoteAggregatorService.getQuote()).thenReturn(result);

        mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(jsonPath("$.sourceName").value("some-api.example"))
                .andExpect(jsonPath("$.result.error.exception.message").value("Error message provided by service"))
                .andExpect(jsonPath("$.result.error.response.body").value("Error message provided by API"))
                .andExpect(jsonPath("$.result.error.response.statusCode").value("INTERNAL_SERVER_ERROR"))
                .andExpect(jsonPath("$.result.error.response.statusCodeValue").value(500));
    }
}
