package com.cryptocurrencies.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cryptocurrency")
public class Cryptocurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency1")
    private String currencyOne;

    @Column(name = "currency2")
    private String currencyTwo;

    @Column(name = "last_price")
    private double lastPrice;

    public Cryptocurrency() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyOne() {
        return currencyOne;
    }

    public void setCurrencyOne(String currency1) {
        this.currencyOne = currency1;
    }

    public String getCurrencyTwo() {
        return currencyTwo;
    }

    public void setCurrencyTwo(String currency2) {
        this.currencyTwo = currency2;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cryptocurrency that = (Cryptocurrency) o;
        return Double.compare(that.lastPrice, lastPrice) == 0 && currencyOne.equals(that.currencyOne) && currencyTwo.equals(that.currencyTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyOne, currencyTwo, lastPrice);
    }

    @Override
    public String toString() {
        return "Cryptocurrency{" +
                "currency1='" + currencyOne + '\'' +
                ", currency2='" + currencyTwo + '\'' +
                ", lastPrice=" + lastPrice +
                '}';
    }
}
