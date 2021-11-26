package com.cryptocurrencies.demo.model;

import com.cryptocurrencies.demo.service.CryptocurrencyService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Component
public class CSVHelper {

    private final CryptocurrencyService cryptocurrencyService;

    @Autowired
    public CSVHelper(CryptocurrencyService cryptocurrencyService) {
        this.cryptocurrencyService = cryptocurrencyService;
    }

    public ByteArrayInputStream cryptocurrenciesToCSV(List<String> currNames) {

        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            List<String> header = Arrays.asList("Cryptocurrency Name", "Min Price", "Max Price");
            csvPrinter.printRecord(header);

            for (String name : currNames) {
                List<String> data = Arrays.asList(
                        name,
                        String.valueOf(cryptocurrencyService.getCryptocurrencyWithLowestPrice(name).getLastPrice()),
                        String.valueOf(cryptocurrencyService.getCryptocurrencyWithHighestPrice(name).getLastPrice())
                );
                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<String> currNames = cryptocurrencyService.getDistinctCurrencyOne();
        return cryptocurrenciesToCSV(currNames);
    }
}
