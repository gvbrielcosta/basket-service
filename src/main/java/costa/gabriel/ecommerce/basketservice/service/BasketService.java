package costa.gabriel.ecommerce.basketservice.service;

import costa.gabriel.ecommerce.basketservice.client.response.PlatziProductResponse;
import costa.gabriel.ecommerce.basketservice.controller.request.BasketRequest;
import costa.gabriel.ecommerce.basketservice.entity.Basket;
import costa.gabriel.ecommerce.basketservice.entity.Product;
import costa.gabriel.ecommerce.basketservice.entity.Status;
import costa.gabriel.ecommerce.basketservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public Basket createBasket(BasketRequest request) {

        basketRepository.findByClientAndStatus(request.client(), Status.OPEN)
                .ifPresent(basket -> {
                    throw new IllegalArgumentException("Basket already exists");
                });

        List<Product> products = new ArrayList<>();

        request.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = productService.getProductById(productRequest.id());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());
        });

        Basket basket = Basket.builder()
                .client(request.client())
                .status(Status.OPEN)
                .products(products)
                .build();
        basket.calculateTotalPrice();
        return basketRepository.save(basket);
    }
}
