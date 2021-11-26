package com.cryptocurrencies.demo.controller;

import com.cryptocurrencies.demo.model.CSVHelper;
import com.cryptocurrencies.demo.model.Cryptocurrency;
import com.cryptocurrencies.demo.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/cryptocurrencies")
public class CryptocurrencyController {

    private final CryptocurrencyService cryptocurrencyService;

    private final CSVHelper csvHelper;

    @Autowired
    public CryptocurrencyController(CryptocurrencyService cryptocurrencyService, CSVHelper csvHelper) {
        this.cryptocurrencyService = cryptocurrencyService;
        this.csvHelper = csvHelper;
    }

    @GetMapping("/minprice")
    public ResponseEntity<Cryptocurrency> showMinPriceByCurrencyName(@RequestParam String name) {
        return new ResponseEntity<>(cryptocurrencyService.getCryptocurrencyWithLowestPrice(name), HttpStatus.OK);
    }

    @GetMapping("/maxprice")
    public ResponseEntity<Cryptocurrency> showMaxPriceByCurrencyName(@RequestParam String name) {
        return new ResponseEntity<>(cryptocurrencyService.getCryptocurrencyWithHighestPrice(name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Cryptocurrency>> showPageByCurrencyName(
            @RequestParam String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
            Pageable paging = PageRequest.of(page, size);
            Page<Cryptocurrency> pageCrypto = cryptocurrencyService.findByCurrencyOneOrderByLastPrice(name, paging);
            List<Cryptocurrency> cryptocurrencies = pageCrypto.getContent();

            return new ResponseEntity<>(cryptocurrencies, HttpStatus.OK);
    }

    @GetMapping("/csv")
    public ResponseEntity<Resource> exportToCSV() {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());
        String fileName = "cryptocurrencies_" + currentDateTime + ".csv";
        InputStreamResource file = new InputStreamResource(csvHelper.load());

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

}
