package org.fibi.usos.service.payment.payu;

import java.util.Optional;

public interface PayUService {
    Optional<String> getRedirectUrl(Long paymentNoticeId);
}
