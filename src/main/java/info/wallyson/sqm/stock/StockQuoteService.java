package info.wallyson.sqm.stock;

import info.wallyson.sqm.stockmanager.CheckStock;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StockQuoteService {
  private final StockQuoteRepository repository;
  private final CheckStock checkStock;

  public StockQuoteService(StockQuoteRepository repository, CheckStock checkStock) {
    this.repository = repository;
    this.checkStock = checkStock;
  }

  @Transactional
  public StockQuote saveStock(StockQuote stock) {
    if (!checkStock.stockExists(stock.getStockId())) return null;

    var optStock = repository.findByDateAndStockId(stock.getDate(), stock.getStockId());
    return optStock.orElseGet(() -> repository.save(stock));
  }

  public List<StockQuote> getAllStockQuote() {
    return repository.findAll();
  }

  public List<StockQuote> getStockQuoteByStockId(String stockId) {
    return repository.findByStockId(stockId);
  }
}
