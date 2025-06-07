package costa.gabriel.ecommerce.basketservice.client;

import costa.gabriel.ecommerce.basketservice.client.response.PlatziProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PlatziStoreClient", url = "${basket.client.platzi}")
public interface PlatziStoreClient {

    @GetMapping("/products")
    public List<PlatziProductResponse> getAllProducts();

    @GetMapping("/products/{id}")
    public PlatziProductResponse getProductById(@PathVariable Long id);
}
