package org.fibi.usos.entity.payment.payu.buyer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBuyer {
    private String customerId;
    private String email;
    private String firstName;
    private String lastName;
}
