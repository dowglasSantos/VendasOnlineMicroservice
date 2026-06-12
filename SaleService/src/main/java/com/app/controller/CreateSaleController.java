package com.app.controller;

import com.app.domain.Sale;
import com.app.service.CreateSaleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sale")
@RequiredArgsConstructor
public class CreateSaleController {
    @Autowired
    CreateSaleService createSaleService;

    @GetMapping("/{productCode}/{clientId}/{quantity}")
    @Operation(summary = "Cria uma venda")
    public ResponseEntity<Sale> createSale(@PathVariable(value = "productCode", required = true) Long productCode, @PathVariable(value = "clientId", required = true) Long clientId, @PathVariable(value = "quantity", required = true) Long quantity) {
        try{
            return ResponseEntity.ok(createSaleService.createSale(productCode, clientId, quantity));
        } catch (Exception e) {
            throw new RuntimeException("Error in create sale" + e);
        }
    }
}
