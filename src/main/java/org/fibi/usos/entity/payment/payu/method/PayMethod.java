package org.fibi.usos.entity.payment.payu.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.model.payment.PaymentType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayMethod {
    private PaymentType type;
}
