package org.fibi.usos.service.payment;


import org.fibi.usos.dto.payment.notice.PaymentNoticeDto;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Optional<List<PaymentNoticeDto>> getAllPaymentNotices();
    Optional<List<PaymentNoticeDto>> getAllPaymentNoticesByPayerId(Long payerId);
}
