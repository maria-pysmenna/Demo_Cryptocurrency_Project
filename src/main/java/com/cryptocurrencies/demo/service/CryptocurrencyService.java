package com.cryptocurrencies.demo.service;

import com.cryptocurrencies.demo.model.Cryptocurrency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CryptocurrencyService {

    Cryptocurrency getCryptocurrencyWithLowestPrice(String currency);

    Cryptocurrency getCryptocurrencyWithHighestPrice(String currency);

    Page<Cryptocurrency> findByCurrencyOneOrderByLastPrice(String name, Pageable pageable);

    List<Cryptocurrency> listAll();

    List<String> getDistinctCurrencyOne();
}
