package com.app.controller;

import com.app.domain.Product;
import com.app.dto.ProductDTO;
import com.app.service.CreateProductService;
import com.app.service.SearchProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
@Tag(name = "Clientes", description = "Operações de Clientes")
public class ProductController {
    private CreateProductService createProductService;
    private SearchProductService searchProductService;

    @Autowired
    public ProductController(CreateProductService createProductService, SearchProductService searchProductService) {
        this.createProductService = createProductService;
        this.searchProductService = searchProductService;
    }

    // Todos os métodos da classe CreateProductService

    @PostMapping
    @Operation(summary = "Salva um produto no banco")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(this.createProductService.createProduct(productDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um produto")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id", required = true) Long id,@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(createProductService.updateProduct(id, productDTO));
    }

    @PutMapping("/inactiveIsProduct/{id}")
    @Operation(summary = "Atualiza o STATUS de um produto para INACTIVE")
    public ResponseEntity<Product> inactiveIsProduct(@PathVariable(value = "id", required = true) Long id) {
        createProductService.inactiveIsProduct(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/activeIsProduct/{id}")
    @Operation(summary = "Atualiza o STATUS de um produto para ACTIVE")
    public ResponseEntity<Product> activeIsProduct(@PathVariable(value = "id", required = true) Long id) {
        createProductService.activeIsProduct(id);

        return ResponseEntity.ok().build();
    }

    // Todos os métodos da classe SearchProductService

    @GetMapping()
    @Operation(summary = "Lista todos os produtos por páginas")
    public ResponseEntity<Page<Product>> findAllProduct() {
        return ResponseEntity.ok(searchProductService.findAllPageProduct());
    }

    @GetMapping("/{pageNumber}/{pageSize}/{status}")
    @Operation(summary = "Lista os produtos com base no STATUS por páginas")
    public ResponseEntity<Page<Product>> findAllByStatus(@PathVariable(value = "pageNumber", required = true) Integer pageNumber,@PathVariable(value = "pageSize", required = true) Integer pageSize, @PathVariable(value = "status", required = true) Product.Status status) {
        return ResponseEntity.ok(searchProductService.findAllByStatus(pageNumber, pageSize,status));
    }

    @GetMapping("/{code}")
    @Operation(summary = "Filtra os produtos através do código")
    public ResponseEntity<Product> findByCode(@PathVariable(value = "code", required = true) Long code) {
        return ResponseEntity.ok(searchProductService.findByCode(code));
    }

    @GetMapping("/isActive")
    @Operation(summary = "Filtra os produtos através do código")
    public ResponseEntity<Product> findByStatus() {
         searchProductService.isActive();
         return ResponseEntity.ok().build();
    }
}
