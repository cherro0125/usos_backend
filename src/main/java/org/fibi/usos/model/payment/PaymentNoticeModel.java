package org.fibi.usos.model.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.payment.notice.PaymentNoticeDto;
import org.fibi.usos.model.base.BaseIdentityModel;
import org.fibi.usos.model.user.UserModel;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "PaymentNotice")
@Table(name = "payments_notices")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class PaymentNoticeModel extends BaseIdentityModel {
    private String details;
    @Enumerated(EnumType.STRING)
    private PaymentPurposeType purposeType;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserModel payer;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private Date payableTo;

    public PaymentNoticeDto mapToDto(){
        PaymentNoticeDto dto = new PaymentNoticeDto();
        dto.setId(getId());
        dto.setDetails(details);
        dto.setPayableTo(payableTo);
        dto.setPayer(payer.mapToDto());
        dto.setPurposeType(purposeType);
        dto.setStatus(status);
        return dto;
    }
}
