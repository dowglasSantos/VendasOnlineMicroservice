package com.app.service.beanservice;

import com.app.dto.ClientDTO;
import com.app.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class BeanService {
    private final RestClient restClient;

    public ClientDTO clientFindById(Long id) {
        return restClient.get()
                .uri("http://localhost:8081/client/{id}", id)
                .retrieve()
                .body(ClientDTO.class);

    }

    public ProductDTO productFindByCode(Long code) {
        return restClient.get()
                .uri("http://localhost:8082/product/{code}", code)
                .retrieve()
                .body(ProductDTO.class);
    }
}
