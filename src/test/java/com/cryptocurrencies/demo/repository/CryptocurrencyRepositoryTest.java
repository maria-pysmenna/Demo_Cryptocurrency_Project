package com.cryptocurrencies.demo.repository;

import com.cryptocurrencies.demo.model.Cryptocurrency;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CryptocurrencyRepositoryTest {
    private final CryptocurrencyRepository repository;

    @Autowired
    public CryptocurrencyRepositoryTest(CryptocurrencyRepository repository) {
        this.repository = repository;
    }

    private Cryptocurrency expected;

    @AfterEach
    public void tearDown() {
        expected = null;
    }

    @BeforeEach
     void setUp() {
        Cryptocurrency crypto1 = new Cryptocurrency();
        crypto1.setCurrencyOne("BTC");
        crypto1.setCurrencyTwo("USD");
        crypto1.setLastPrice(54016.2);
        repository.save(crypto1);

        Cryptocurrency crypto2 = new Cryptocurrency();
        crypto1.setCurrencyOne("BTC");
        crypto1.setCurrencyTwo("USD");
        crypto1.setLastPrice(54022.9);
        repository.save(crypto2);

        Cryptocurrency crypto3 = new Cryptocurrency();
        crypto1.setCurrencyOne("BTC");
        crypto1.setCurrencyTwo("USD");
        crypto1.setLastPrice(58626.6);
        repository.save(crypto3);

        Cryptocurrency crypto4 = new Cryptocurrency();
        crypto1.setCurrencyOne("BTC");
        crypto1.setCurrencyTwo("USD");
        crypto1.setLastPrice(54025.8);
        repository.save(crypto4);

        Cryptocurrency crypto5 = new Cryptocurrency();
        crypto1.setCurrencyOne("BTC");
        crypto1.setCurrencyTwo("USD");
        crypto1.setLastPrice(55007.3);
        repository.save(crypto5);
    }

    @Test
    public void getCryptocurrencyWithLowestPriceTest() {
        expected.setCurrencyOne("BTC");
        expected.setCurrencyTwo("USD");
        expected.setLastPrice(54016.2);
        Cryptocurrency actual = repository.getCryptocurrencyWithLowestPrice("BTC");
        assertEquals(expected, actual);
    }

    @Test
    public void getCryptocurrencyWithHighestPriceTest() {
        expected.setCurrencyOne("BTC");
        expected.setCurrencyTwo("USD");
        expected.setLastPrice(58626.6);
        Cryptocurrency actual = repository.getCryptocurrencyWithHighestPrice("BTC");
        assertEquals(expected, actual);
    }
}
