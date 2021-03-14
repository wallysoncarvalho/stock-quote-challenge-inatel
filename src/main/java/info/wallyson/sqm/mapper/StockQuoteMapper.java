package info.wallyson.sqm.mapper;

import info.wallyson.sqm.controller.dto.StockQuoteDto;
import info.wallyson.sqm.stock.StockQuote;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockQuoteMapper {

  public static List<StockQuote> getStockQuotesFromDto(StockQuoteDto stockQuoteDto) {
    var stockQuotes = new ArrayList<StockQuote>();

    for (var entry : stockQuoteDto.getQuotes().entrySet()) {
      stockQuotes.add(new StockQuote(entry.getKey(), entry.getValue(), stockQuoteDto.getId()));
    }

    return stockQuotes;
  }

  public static StockQuoteDto getDtoFromStockQuotes(String stockId, List<StockQuote> stockQuotes) {
    var map = new HashMap<LocalDate, BigDecimal>(stockQuotes.size());

    for (var stockQuote : stockQuotes) {
      map.put(stockQuote.getDate(), stockQuote.getValue());
    }

    return new StockQuoteDto(stockId, map);
  }
}
