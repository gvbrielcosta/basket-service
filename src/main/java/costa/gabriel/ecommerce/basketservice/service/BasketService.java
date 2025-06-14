package costa.gabriel.ecommerce.basketservice.service;

import costa.gabriel.ecommerce.basketservice.client.response.PlatziProductResponse;
import costa.gabriel.ecommerce.basketservice.controller.request.BasketRequest;
import costa.gabriel.ecommerce.basketservice.controller.request.PaymentRequest;
import costa.gabriel.ecommerce.basketservice.entity.Basket;
import costa.gabriel.ecommerce.basketservice.entity.Product;
import costa.gabriel.ecommerce.basketservice.entity.Status;
import costa.gabriel.ecommerce.basketservice.exceptions.BusinessException;
import costa.gabriel.ecommerce.basketservice.exceptions.DataNotFoundException;
import costa.gabriel.ecommerce.basketservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
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
                    throw new BusinessException("Basket already exists for this client");
                });

        List<Product> products = getProducts(request);

        Basket basket = Basket.builder()
                .client(request.client())
                .status(Status.OPEN)
                .products(products)
                .build();
        basket.calculateTotalPrice();
        return basketRepository.save(basket);
    }

    public Basket getBasket(String id) {
        return basketRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Basket not found"));
    }

    public Basket updateBasket(String id, BasketRequest request) {
        Basket savedBasket = getBasket(id);
        List<Product> products = getProducts(request);

        savedBasket.setProducts(products);
        savedBasket.calculateTotalPrice();
        return basketRepository.save(savedBasket);
    }

    public Basket payBasket(String id, PaymentRequest request) {
        Basket savedBasket = getBasket(id);
        savedBasket.setPaymentMethod(request.getPaymentMethod());
        savedBasket.setStatus(Status.SOLD);
        return basketRepository.save(savedBasket);
    }

    public void deleteBasket(String id) {
        basketRepository.delete(getBasket(id));
    }

    private List<Product> getProducts(BasketRequest request) {
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
        return products;
    }
}
