package org.fibi.usos.entity.payment.payu.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {
    private String name;
    private long unitPrice;
    private int quantity;
}
