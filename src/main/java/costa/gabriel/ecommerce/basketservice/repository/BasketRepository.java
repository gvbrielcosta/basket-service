package costa.gabriel.ecommerce.basketservice.repository;

import costa.gabriel.ecommerce.basketservice.entity.Basket;
import costa.gabriel.ecommerce.basketservice.entity.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket, String> {
    Optional<Basket> findByClientAndStatus(Long client, Status status);
}
