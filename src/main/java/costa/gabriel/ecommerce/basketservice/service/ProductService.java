package costa.gabriel.ecommerce.basketservice.service;

import costa.gabriel.ecommerce.basketservice.client.PlatziStoreClient;
import costa.gabriel.ecommerce.basketservice.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "products")
    public List<PlatziProductResponse> getAllProducts() {
        log.info("Getting all products");
        return platziStoreClient.getAllProducts();
    }

    @Cacheable(value = "product", key = "#productid")
    public PlatziProductResponse getProductById(Long productid) {
        log.info("Getting product by id: {}", productid);
        return platziStoreClient.getProductById(productid);
    }
}
