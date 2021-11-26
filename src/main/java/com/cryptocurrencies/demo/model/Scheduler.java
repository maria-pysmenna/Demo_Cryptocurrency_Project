package com.cryptocurrencies.demo.model;

import com.cryptocurrencies.demo.repository.CryptocurrencyRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Scheduler {

    private final CryptocurrencyRepository repository;

    private final CexAPI api;

    @Autowired
    public Scheduler(CryptocurrencyRepository repository, CexAPI api) {
        this.repository = repository;
        this.api = api;
    }

    @Scheduled(cron="*/10 * * * * *")
    public void saveCryptoBTC() throws JSONException, IOException {
        repository.save(api.lastPrice("BTC", "USD"));
    }

    @Scheduled(cron="*/10 * * * * *")
    public void saveCryptoETH() throws JSONException, IOException {
        repository.save(api.lastPrice("ETH", "USD"));
    }

    @Scheduled(cron="*/10 * * * * *")
    public void saveCryptoXRP() throws JSONException, IOException {
        repository.save(api.lastPrice("XRP", "USD"));
    }
}

