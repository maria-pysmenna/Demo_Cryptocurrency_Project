package com.cryptocurrencies.demo.repository;

import com.cryptocurrencies.demo.model.Cryptocurrency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Long> {

    @Query(value = "select * from cryptocurrency where currency1 = ?1 " +
            "and last_price = (select MIN(last_price) from cryptocurrency where currency1 = ?1) limit 1", nativeQuery = true)
    Cryptocurrency getCryptocurrencyWithLowestPrice(String currency);

    @Query(value = "select * from cryptocurrency where currency1 = ?1 " +
            "and last_price = (select MAX(last_price) from cryptocurrency where currency1 = ?1) limit 1", nativeQuery = true)
    Cryptocurrency getCryptocurrencyWithHighestPrice(String currency);

    Page<Cryptocurrency> findByCurrencyOneOrderByLastPrice(String name, Pageable pageable);

    @Query(value = "select distinct currency1 from cryptocurrency ", nativeQuery = true)
    List<String> getDistinctCurrencyOne();
}
