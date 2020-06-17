package org.fibi.usos.entity.payment.payu;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.entity.payment.payu.buyer.OrderBuyer;
import org.fibi.usos.entity.payment.payu.method.PayMethod;
import org.fibi.usos.entity.payment.payu.product.OrderProduct;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private Date orderCreateDate;
    private PayMethod payMethod;
    private OrderBuyer buyer;
    private String notifyUrl;
    private String customerIp;
    private String merchantPosId;
    private String description;
    private String currencyCode;
    private long totalAmount;
    private String status;
    private List<OrderProduct> products;
}
