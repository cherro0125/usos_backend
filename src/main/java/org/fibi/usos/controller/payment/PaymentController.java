package org.fibi.usos.controller.payment;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.payment.notice.PaymentNoticeDto;
import org.fibi.usos.dto.payment.notice.payu.RedirectDto;
import org.fibi.usos.entity.payment.payu.OrderCallback;
import org.fibi.usos.entity.response.standard.StandardMessageResponse;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.pool.RegisteredEnumPool;
import org.fibi.usos.service.payment.PaymentService;
import org.fibi.usos.service.payment.payu.PayUService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger logger  = LoggerFactory.getLogger(PaymentController.class);
    private PaymentService paymentService;
    private PayUService payUService;

    public PaymentController(PaymentService paymentService, PayUService payUService) {
        this.paymentService = paymentService;
        this.payUService = payUService;
    }

    @RequireRole({UserRole.DEAN,UserRole.RECTOR})
    @GetMapping(value = "/all")
    public ResponseEntity<List<PaymentNoticeDto>> getAllPaymentNotice() {
        Optional<List<PaymentNoticeDto>> dtos = paymentService.getAllPaymentNotices();
        return dtos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new ArrayList<>()));
    }

    @GetMapping(value = "/{payerId}")
    public ResponseEntity<List<PaymentNoticeDto>> getAllPaymentNoticeForUser(@PathVariable("payerId") Long payerId) {
        Optional<List<PaymentNoticeDto>> dtos = paymentService.getAllPaymentNoticesByPayerId(payerId);
        return dtos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new ArrayList<>()));
    }

    @PostMapping(path = "/notify",consumes = "application/json", produces = "application/json")
    public ResponseEntity<StandardMessageResponse> payuCallback(@RequestBody OrderCallback orderCallback){
        logger.info(orderCallback.toString());
        StandardMessageResponse standardMessageResponse = new StandardMessageResponse();
        standardMessageResponse.setMessage("Done");
        return ResponseEntity.ok(standardMessageResponse);
    }

    @GetMapping(path = "/pay/{paymentNoticeId}")
    public ResponseEntity<RedirectDto> getPayURedirectUrl(@PathVariable("paymentNoticeId") Long paymentNoticeId){
        Optional<String> redirectUrl = payUService.getRedirectUrl(paymentNoticeId);
        return redirectUrl.map(s -> ResponseEntity.ok(new RedirectDto(s))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
