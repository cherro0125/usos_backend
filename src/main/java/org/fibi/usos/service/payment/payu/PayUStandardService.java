package org.fibi.usos.service.payment.payu;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.fibi.usos.entity.payment.payu.Order;
import org.fibi.usos.entity.payment.payu.product.OrderProduct;
import org.fibi.usos.model.payment.PaymentNoticeModel;
import org.fibi.usos.service.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@Service
@Component
public class PayUStandardService implements PayUService {

    private static final Logger logger  = LoggerFactory.getLogger(PayUStandardService.class);

    private PaymentService paymentService;
    @Value("${usos.app.payu.tokenRequestUrl}")
    private String tokenRequestUrl;
    @Value("${usos.app.payu.orderRequestUrl}")
    private String orderRequestUrl;
    @Value("${usos.app.payu.clientId}")
    private String clientId;
    @Value("${usos.app.payu.secret}")
    private String secret;
    @Value("${usos.app.payu.notifyUrl}")
    private String notifyUrl;
    @Value("${usos.app.payu.merchantPosId}")
    private String merchantPosId;
    @Value("${usos.app.payu.continueUrl}")
    private String continueUrl;

    public PayUStandardService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public Optional<String> getRedirectUrl(Long paymentNoticeId) {
        Optional<PaymentNoticeModel> paymentNoticeModel = paymentService.getPaymentNoticeById(paymentNoticeId);
        if(paymentNoticeModel.isPresent()){
            RestTemplate rest = new RestTemplate();
            try {
                String token = getToken();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                Order order = prepareOrder(paymentNoticeModel.get());
                HttpEntity<Order> entity = new HttpEntity<>(order, headers);
                ResponseEntity<String> response = rest.postForEntity(orderRequestUrl,entity,String.class);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());
                return Optional.of(root.path("redirectUri").asText());
            } catch (JsonProcessingException e) {
                logger.trace(e.getLocalizedMessage(),e);
            }
        }
        return Optional.empty();
    }


    private Order prepareOrder(PaymentNoticeModel paymentNoticeModel){
        Order order = new Order();
        order.setNotifyUrl(notifyUrl);
        order.setContinueUrl(continueUrl);
        order.setCustomerIp("127.0.0.1");
        order.setMerchantPosId(merchantPosId);
        order.setDescription(paymentNoticeModel.getDetails());
        order.setCurrencyCode("PLN");
        order.setTotalAmount(paymentNoticeModel.getValue());
//        order.setExtOrderId(paymentNoticeModel.getId());
        order.setProducts(Collections.singletonList(new OrderProduct(paymentNoticeModel.getId().toString(),paymentNoticeModel.getValue(),1)));

        return order;
    }

    private String getToken() throws JsonProcessingException {
        RestTemplate rest = new RestTemplate();
        ResponseEntity<String> response = rest.postForEntity(buildTokenUrl(),null,String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode token = root.path("access_token");
        return token.asText();
    }

    private String buildTokenUrl(){
        return String.format("%s?grant_type=client_credentials&client_id=%s&client_secret=%s",tokenRequestUrl,clientId,secret);
    }
}
