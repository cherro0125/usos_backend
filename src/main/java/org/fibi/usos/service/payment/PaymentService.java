package org.fibi.usos.service.payment;


import org.fibi.usos.dto.payment.notice.PaymentNoticeDto;
import org.fibi.usos.entity.payment.payu.OrderCallback;
import org.fibi.usos.model.payment.PaymentModel;
import org.fibi.usos.model.payment.PaymentNoticeModel;
import org.fibi.usos.model.payment.PaymentStatus;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Optional<List<PaymentNoticeDto>> getAllPaymentNotices();
    Optional<List<PaymentNoticeDto>> getAllPaymentNoticesByPayerId(Long payerId);
    Optional<PaymentNoticeModel> getPaymentNoticeById(Long id);
    void changePaymentNoticeStatus(long paymentNoticeId, PaymentStatus newStatus);
    PaymentModel createPaymentHistory(PaymentModel paymentModel);
    Optional<PaymentModel> preparePaymentHistory(OrderCallback orderCallback);
}
