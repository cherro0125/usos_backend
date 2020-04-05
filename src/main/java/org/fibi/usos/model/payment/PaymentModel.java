package org.fibi.usos.model.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.user.UserModel;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Payment")
@Table(name = "payments_history")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class PaymentModel extends BaseIdentityModel {
    @ManyToOne
    private UserModel payer;
    @Enumerated(EnumType.STRING)
    private PaymentPurposeType purposeType;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private float paymentValue;
}
