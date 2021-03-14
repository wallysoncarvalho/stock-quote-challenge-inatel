package info.wallyson.sqm.controller.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockQuoteDto {
  @NotBlank private String id;

  @NotEmpty private Map<LocalDate, BigDecimal> quotes;

  public StockQuoteDto() {}

  public StockQuoteDto(@NotBlank String id, @NotEmpty Map<LocalDate, BigDecimal> quotes) {
    this.id = id;
    this.quotes = quotes;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Map<LocalDate, BigDecimal> getQuotes() {
    return quotes;
  }

  @JsonAnySetter
  public void setQuotes(String key, String value) {
    var date = LocalDate.parse(key);
    var cost = new BigDecimal(value);
    quotes.put(date, cost);
  }

  @Override
  public String toString() {
    return "StockDto{" + "id='" + id + '\'' + ", quotes=" + quotes + '}';
  }
}
