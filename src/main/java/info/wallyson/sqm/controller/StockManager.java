package info.wallyson.sqm.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StockManager {

  @CacheEvict(cacheNames = "stock_id", allEntries = true)
  @DeleteMapping("stockcache")
  public void cleanStockCache() {}
}
