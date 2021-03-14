package info.wallyson.sqm.stock;

import info.wallyson.sqm.mother.StockQuoteMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterStockQuote extends ITBaseClass {

  @Test
  @DisplayName("Should register a new stock quote")
  void shouldRegisterNewStockQuote() throws Exception {
    var stockQuote = StockQuoteMother.getStockQuoteDto();
    mockMvc
        .perform(
            post("/api/quote")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stockQuote)))
        .andExpect(status().isOk())
        .andReturn();

    verify(checkStock, times(1)).stockExists(stockQuote.getId());
  }
}
