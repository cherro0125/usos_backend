package org.fibi.usos.dto.payment.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fibi.usos.dto.user.UserDto;
import org.fibi.usos.model.payment.PaymentPurposeType;
import org.fibi.usos.model.payment.PaymentStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentNoticeDto {
    private Long id;
    private String details;
    private PaymentPurposeType purposeType;
    private UserDto payer;
    private PaymentStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "dd-MM-YYYY hh:mm:ss" , timezone="UTC")
    private Date payableTo;
}
