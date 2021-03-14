package info.wallyson.sqm.mother;

import info.wallyson.sqm.controller.dto.StockQuoteDto;
import info.wallyson.sqm.stock.StockQuote;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class StockQuoteMother {

  public static StockQuoteDto getStockQuoteDto() {
    var mapa = new HashMap<LocalDate, BigDecimal>();
    mapa.put(LocalDate.now(), new BigDecimal("89.0"));
    return new StockQuoteDto("JAVA", mapa);
  }

  public static List<StockQuote> getStockQuoteList() {

    return List.of(
        new StockQuote(LocalDate.now(), new BigDecimal("9.23"), "TEST"),
        new StockQuote(LocalDate.now().plusDays(1), new BigDecimal("1.2"), "TEST"),
        new StockQuote(LocalDate.now().plusDays(2), new BigDecimal("0.23"), "TEST"),
        new StockQuote(LocalDate.now().plusDays(3), new BigDecimal("290.11"), "TEST"));
  }
}
