package org.fibi.usos.model.payment;

import org.fibi.usos.annotation.RegisterEnum;

@RegisterEnum
public enum PaymentType {
    PBL,
    CARD_TOKEN,
    INSTALLMENTS
}
