package org.fibi.usos.model.payment;

import org.fibi.usos.annotation.RegisterEnum;

@RegisterEnum
public enum PaymentType {
    BLIK,
    CARD,
    BANK_ACCOUNT,
    PAYU
}
