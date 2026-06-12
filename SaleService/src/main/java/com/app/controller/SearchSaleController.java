package com.app.controller;

import com.app.domain.Sale;
import com.app.service.SearchSaleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SearchSaleController {
    @Autowired
    SearchSaleService searchSaleService;

    @GetMapping("/{page}/{size}")
    @Operation(summary = "Devolve uma paginação das vendas do db")
    public ResponseEntity<Page<Sale>> searchSale(@PathVariable(value = "page", required = true) Integer page,@PathVariable(value = "size", required = true) Integer size) {
        return ResponseEntity.ok(searchSaleService.searchSale(page, size));
    }
}
