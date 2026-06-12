package com.app.controller;

import com.app.domain.Sale;
import com.app.service.CreateSaleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sale")
@RequiredArgsConstructor
public class CreateSaleController {
    @Autowired
    CreateSaleService createSaleService;

    @PostMapping("/{productCode}/{clientId}/{quantity}")
    @Operation(summary = "Cria uma venda")
    public ResponseEntity<Sale> createSale(@PathVariable(value = "productCode", required = true) Long productCode, @PathVariable(value = "clientId", required = true) Long clientId, @PathVariable(value = "quantity", required = true) Long quantity) {
        try{
            return ResponseEntity.ok(createSaleService.createSale(productCode, clientId, quantity));
        } catch (Exception e) {
            throw new RuntimeException("Error in create sale" + e);
        }
    }

    @DeleteMapping("/delete/{saleId}")
    @Operation(summary = "Deleta uma vanda do banco")
    public void deleteSale(@PathVariable Long saleId) {
        createSaleService.deleteSale(saleId);
    }
}
