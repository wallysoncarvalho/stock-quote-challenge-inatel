package info.wallyson.sqm.stock;

import com.fasterxml.jackson.core.type.TypeReference;
import info.wallyson.sqm.controller.dto.StockQuoteDto;
import info.wallyson.sqm.mother.StockQuoteMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetStockQuote extends ITBaseClass {

  @Test
  @DisplayName("Should get all stock quote")
  void shouldGetAllStockQuote() throws Exception {
    var stockQuoteList = StockQuoteMother.getStockQuoteList();
    stockQuoteRepository.saveAll(stockQuoteList);

    mockMvc.perform(get("/api/quote")).andExpect(status().isOk()).andReturn();
  }

  @Test
  @DisplayName("Should get a stock quote by ID")
  void shouldGetStockQuoteByID() {}

  @Test
  @DisplayName("Should not find stock quote")
  void shouldNotFoundStockQuote() throws Exception {
    mockMvc.perform(get("/api/quote/INVALID_ID")).andExpect(status().isNotFound()).andReturn();
  }
}
