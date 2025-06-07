package costa.gabriel.ecommerce.basketservice.controller.request;

import costa.gabriel.ecommerce.basketservice.entity.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private PaymentMethod paymentMethod;

}
