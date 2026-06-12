package com.app.service;

import com.app.domain.Sale;
import com.app.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchSaleService {
    @Autowired
    ISaleRepository saleRepository;

    public Page<Sale> searchSale(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Sale> salePage = saleRepository.findAll(pageable);
        return salePage;
    }
}
