package org.fibi.usos.controller.payment;

import org.fibi.usos.annotation.RequireRole;
import org.fibi.usos.dto.payment.notice.PaymentNoticeDto;
import org.fibi.usos.model.user.UserRole;
import org.fibi.usos.service.payment.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger logger  = Logger.getLogger(PaymentController.class.getName());
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
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

//    @PostMapping(path = "/notify",consumes = "application/json", produces = "application/json")
//    public @ResponseBody String payuCallback(@RequestParam String mihpayid, @RequestParam String status, @RequestParam PayUPaymentMode mode, @RequestParam String txnid, @RequestParam String hash){
//        return "good";
//    }

    @PostMapping(path = "/notify",consumes = "application/json", produces = "application/json")
    public @ResponseBody String payuCallback123(){
        logger.info("ENTER");
        return "good";
    }
}
