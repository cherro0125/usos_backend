package org.fibi.usos.entity.payment.payu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCallback {
    private Order order;
    private List<OrderProperty> properties;
}
