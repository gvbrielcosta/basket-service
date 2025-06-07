package costa.gabriel.ecommerce.basketservice.controller.request;

import costa.gabriel.ecommerce.basketservice.entity.Product;

import java.util.List;

public record BasketRequest(Long client, List<ProductRequest> products) {
}
