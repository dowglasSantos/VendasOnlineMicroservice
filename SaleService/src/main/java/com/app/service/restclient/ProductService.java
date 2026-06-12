package com.app.service.restclient;

import com.app.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductService {
    private final RestClient restClient;

    public ProductDTO findById(Long code) {
        return restClient.get()
                .uri("http://localhost:8081/product/{id}", code)
                .retrieve()
                .body(ProductDTO.class);
    }

    public ProductDTO isActive(String status) {
        return restClient.get()
                .uri("http://localhost:8081/product/{id}", status)
                .retrieve()
                .body(ProductDTO.class);
    }
}
