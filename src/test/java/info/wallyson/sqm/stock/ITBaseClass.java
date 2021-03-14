package info.wallyson.sqm.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.wallyson.sqm.stockmanager.CheckStock;
import info.wallyson.sqm.stockmanager.RegisterNotification;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITBaseClass {
  ObjectMapper mapper = new ObjectMapper();
  @Autowired MockMvc mockMvc;

  @MockBean RegisterNotification registerNotification;
  @MockBean CheckStock checkStock;

  @Autowired StockQuoteRepository stockQuoteRepository;

  @AfterEach
  void setup() {
    when(checkStock.stockExists(anyString())).thenReturn(true);
    stockQuoteRepository.deleteAll();
  }
}
