package com.cryptocurrencies.demo.service.impl;

import com.cryptocurrencies.demo.exception.CurrencyNotFoundException;
import com.cryptocurrencies.demo.model.Cryptocurrency;
import com.cryptocurrencies.demo.repository.CryptocurrencyRepository;
import com.cryptocurrencies.demo.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    private final CryptocurrencyRepository cryptocurrencyRepository;

    @Autowired
    public CryptocurrencyServiceImpl(CryptocurrencyRepository cryptocurrencyRepository) {
        this.cryptocurrencyRepository = cryptocurrencyRepository;
    }

    @Override
    public Cryptocurrency getCryptocurrencyWithLowestPrice(String currency) {
        Cryptocurrency result = cryptocurrencyRepository.getCryptocurrencyWithLowestPrice(currency);
        if (result != null) return result;
        throw new CurrencyNotFoundException("Currency Not Found");
    }

    @Override
    public Cryptocurrency getCryptocurrencyWithHighestPrice(String currency) {
        Cryptocurrency result = cryptocurrencyRepository.getCryptocurrencyWithHighestPrice(currency);
        if (result != null) return result;
        throw new CurrencyNotFoundException("Currency Not Found");
    }

    @Override
    public Page<Cryptocurrency> findByCurrencyOneOrderByLastPrice(String name, Pageable pageable) {
        Page<Cryptocurrency> result = cryptocurrencyRepository.findByCurrencyOneOrderByLastPrice(name, pageable);
        if (!result.isEmpty()) return result;
        throw new CurrencyNotFoundException("Currency Not Found");
    }

    @Override
    public List<Cryptocurrency> listAll() {
        return cryptocurrencyRepository.findAll(Sort.by("currency1"));
    }

    @Override
    public List<String> getDistinctCurrencyOne() {
        return cryptocurrencyRepository.getDistinctCurrencyOne();
    }

}
