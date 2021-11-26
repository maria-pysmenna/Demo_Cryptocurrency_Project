package com.cryptocurrencies.demo.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Configuration
@ConfigurationProperties(prefix = "cex")
public class CexAPI {

    private String username;
    private String apiKey;
    private String apiSecret;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }


    private Cryptocurrency apiCall(String method, String pair) throws JSONException, IOException {
        JSONObject json = JsonReader.readJsonFromUrl("https://cex.io/api/" + method + "/" + pair);
        Cryptocurrency result = new Cryptocurrency();
        result.setCurrencyOne(json.getString("curr1"));
        result.setCurrencyTwo(json.getString("curr2"));
        result.setLastPrice(json.getDouble("lprice"));
        return result;
    }

    public Cryptocurrency lastPrice(String major, String minor) throws JSONException, IOException {
        return this.apiCall("last_price", (major + "/" + minor));
    }

}
