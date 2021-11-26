package com.cryptocurrencies.demo.service;

import com.cryptocurrencies.demo.exception.CurrencyNotFoundException;
import com.cryptocurrencies.demo.model.Cryptocurrency;
import com.cryptocurrencies.demo.repository.CryptocurrencyRepository;
import com.cryptocurrencies.demo.service.impl.CryptocurrencyServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CryptocurrencyServiceTest {

    @Mock
    private CryptocurrencyRepository repository;

    @InjectMocks
    private CryptocurrencyServiceImpl service;

    private Cryptocurrency expected;

    @BeforeEach
    public void setUp() {
        expected = new Cryptocurrency();
        expected.setCurrencyOne("BTC");
        expected.setCurrencyTwo("USD");
        expected.setLastPrice(58626.6);
    }

    @AfterEach
    public void tearDown() {
        expected = null;
    }

    @Test
    public void correctGetCryptocurrencyWithLowestPriceTest() {
        when(repository.getCryptocurrencyWithLowestPrice(anyString())).thenReturn(expected);
        Cryptocurrency actual = service.getCryptocurrencyWithLowestPrice(anyString());

        assertEquals(expected, actual);
        verify(repository, times(1)).getCryptocurrencyWithLowestPrice(anyString());
    }

    @Test
    public void errorGetCryptocurrencyWithLowestPriceTest() {
        Exception exception = assertThrows(CurrencyNotFoundException.class, ()
                -> service.getCryptocurrencyWithLowestPrice("12345678909876544321"));

        assertEquals("Currency Not Found", exception.getMessage());
        verify(repository, times(1)).getCryptocurrencyWithLowestPrice("12345678909876544321");
    }


}
