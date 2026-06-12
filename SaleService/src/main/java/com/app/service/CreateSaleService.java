package com.app.service;

import com.app.domain.Client.StatusClient;
import com.app.domain.Product;
import com.app.domain.Sale;
import com.app.domain.Sale.Status;
import com.app.dto.ClientDTO;
import com.app.dto.ProductDTO;
import com.app.repository.ISaleRepository;
import com.app.service.beanservice.BeanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSaleService {
    @Autowired
    ISaleRepository saleRepository;
    private final BeanService beanService;

    public Sale createSale(Long productCode, Long clientId, Long quantity) {
        Sale sale = new Sale();
        sale.setStatus(Status.STARTED);

        ProductDTO productDTO = beanService.productFindByCode(productCode);
        validateProduct(productDTO, sale);

        Product product = new Product();
        product.setId(productDTO.id());
        product.setCode(productDTO.code());
        product.setName(productDTO.name());
        product.setValue(productDTO.value());
        product.setDescription(productDTO.description());
        product.setValue(productDTO.value());

        ClientDTO client = beanService.clientFindById(clientId);

        validateClient(client, sale);

        sale.setCode(System.currentTimeMillis());
        sale.setClient_id(client.id());
        sale.setProduct_id(productDTO.id());
        sale.addProduct(product, quantity);

        sale.setStatus(Status.COMPLETED);

        return saleRepository.save(sale);
    }

    private void validateProduct(ProductDTO product, Sale sale) {
        if (product.status() == "INACTIVE") {
            sale.setStatus(Status.CANCELED);
            throw new IllegalStateException("Product is not active");
        }
    }

    private void validateClient(ClientDTO client, Sale sale) {
        if (client.status() == StatusClient.INACTIVE) {
            sale.setStatus(Status.CANCELED);
            throw new IllegalStateException("Client is not active");
        }
    }
}
