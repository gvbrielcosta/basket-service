package costa.gabriel.ecommerce.basketservice.controller.request;

import lombok.Builder;

@Builder
public record ProductRequest(Long id, Integer quantity) {
}
