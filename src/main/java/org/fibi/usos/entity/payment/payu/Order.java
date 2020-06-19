package org.fibi.usos.entity.payment.payu;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.entity.payment.payu.buyer.OrderBuyer;
import org.fibi.usos.entity.payment.payu.method.PayMethod;
import org.fibi.usos.entity.payment.payu.product.OrderProduct;
import org.fibi.usos.model.payment.PaymentNoticeModel;
import org.fibi.usos.model.payment.PaymentStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private Long extOrderId;
    private Date orderCreateDate;
    private PayMethod payMethod;
    private OrderBuyer buyer;
    private String notifyUrl;
    private String continueUrl;
    private String customerIp;
    private String merchantPosId;
    private String description;
    private String currencyCode;
    private long totalAmount;
    private PaymentStatus status;
    private List<OrderProduct> products;

    public Optional<Long> getPaymentNoticeId(){
        if(!products.isEmpty()){
            OrderProduct orderProduct = products.get(0);
            return Optional.of(Long.parseLong(orderProduct.getName()));
        }
        return Optional.empty();
    }
}
