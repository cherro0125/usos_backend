package org.fibi.usos.dto.payment.notice.payu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedirectDto {
    private String redirectUrl;
}
