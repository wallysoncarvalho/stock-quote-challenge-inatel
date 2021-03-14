package info.wallyson.sqm.stock;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
    name = "quote",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "unique_stock_date",
          columnNames = {"date", "stockId"})
    })
public class StockQuote {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull private LocalDate date;

  @NotNull private BigDecimal value;

  @NotBlank private String stockId;

  public StockQuote() {}

  public StockQuote(@NotNull LocalDate date, @NotNull BigDecimal value, @NotBlank String stockId) {
    this.date = date;
    this.value = value;
    this.stockId = stockId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public String getStockId() {
    return stockId;
  }

  public void setStockId(String stockId) {
    this.stockId = stockId;
  }
}
