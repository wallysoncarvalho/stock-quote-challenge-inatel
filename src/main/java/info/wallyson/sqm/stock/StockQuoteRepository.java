package info.wallyson.sqm.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
interface StockQuoteRepository extends JpaRepository<StockQuote, Long> {
    Optional<StockQuote> findByDateAndStockId(LocalDate date, String stockId);
    List<StockQuote> findByStockId(String stockId);
}
