package se.checkoutspring.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import se.checkoutspring.app.dto.ProductResponse;

import java.util.List;

@Service
public class ProductService {

    private final WebClient webClient;

    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<ProductResponse> fetchRealData(List<Long> productIds) {
        return webClient.post()
                .uri("/products/batch")
                .bodyValue(productIds)
                .retrieve()
                .bodyToFlux(ProductResponse.class)
                .collectList()
                .block();
    }


}