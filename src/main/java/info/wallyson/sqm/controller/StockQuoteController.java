package info.wallyson.sqm.controller;

import info.wallyson.sqm.controller.dto.StockQuoteDto;
import info.wallyson.sqm.mapper.StockQuoteMapper;
import info.wallyson.sqm.stock.StockQuote;
import info.wallyson.sqm.stock.StockQuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

@RestController
@RequestMapping("api/quote")
public class StockQuoteController {
  private final StockQuoteService stockQuoteService;

  public StockQuoteController(StockQuoteService stockQuoteService) {
    this.stockQuoteService = stockQuoteService;
  }

  @PostMapping
  public ResponseEntity<?> registerStockQuote(@RequestBody @Valid StockQuoteDto stockQuoteDto) {
    var stockQuotes = StockQuoteMapper.getStockQuotesFromDto(stockQuoteDto);

    stockQuotes.forEach(stockQuoteService::saveStock);

    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<List<StockQuoteDto>> getAllStockQuotes() {
    var stockQuotes = new ArrayList<StockQuoteDto>();

    var stocks =
        stockQuoteService.getAllStockQuote().stream().collect(groupingBy(StockQuote::getStockId));

    for (var stock : stocks.entrySet()) {
      stockQuotes.add(StockQuoteMapper.getDtoFromStockQuotes(stock.getKey(), stock.getValue()));
    }

    return ResponseEntity.ok(stockQuotes);
  }

  @GetMapping("/stock/{id}")
  public ResponseEntity<StockQuoteDto> getStockQuoteById(@PathVariable(name = "id") String id) {
    var stockQuoteList = stockQuoteService.getStockQuoteByStockId(id);
    var stockQuoteDto = new StockQuoteDto();

    if (stockQuoteList.isEmpty()) return ResponseEntity.notFound().build();

    stockQuoteDto =
        StockQuoteMapper.getDtoFromStockQuotes(stockQuoteList.get(0).getStockId(), stockQuoteList);

    return ResponseEntity.ok(stockQuoteDto);
  }
}
