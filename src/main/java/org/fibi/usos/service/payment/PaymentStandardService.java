package org.fibi.usos.service.payment;

import org.fibi.usos.dto.payment.notice.PaymentNoticeDto;
import org.fibi.usos.entity.payment.payu.Order;
import org.fibi.usos.entity.payment.payu.OrderCallback;
import org.fibi.usos.entity.payment.payu.buyer.OrderBuyer;
import org.fibi.usos.model.payment.PaymentModel;
import org.fibi.usos.model.payment.PaymentNoticeModel;
import org.fibi.usos.model.payment.PaymentStatus;
import org.fibi.usos.repository.payment.PaymentNoticeRepository;
import org.fibi.usos.repository.payment.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PaymentStandardService implements PaymentService {

    private PaymentNoticeRepository paymentNoticeRepository;
    private PaymentRepository paymentRepository;
    private static final Logger logger  = LoggerFactory.getLogger(PaymentStandardService.class);

    public PaymentStandardService(PaymentNoticeRepository paymentNoticeRepository, PaymentRepository paymentRepository) {
        this.paymentNoticeRepository = paymentNoticeRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<List<PaymentNoticeDto>> getAllPaymentNotices() {
        Iterable<PaymentNoticeModel> paymentNotices = paymentNoticeRepository.findAll();
        List<PaymentNoticeDto> paymentNoticeDtos = StreamSupport.stream(paymentNotices.spliterator(), false)
                .map(PaymentNoticeModel::mapToDto)
                .collect(Collectors.toList());
        return Optional.of(paymentNoticeDtos);
    }

    @Override
    public Optional<List<PaymentNoticeDto>> getAllPaymentNoticesByPayerId(Long payerId) {
        Optional<Collection<PaymentNoticeModel>> allByPayerId = paymentNoticeRepository.findAllByPayerId(payerId);
        return allByPayerId.map(paymentNoticeModels -> paymentNoticeModels.stream().map(PaymentNoticeModel::mapToDto).collect(Collectors.toList()));
    }

    @Override
    public Optional<PaymentNoticeModel> getPaymentNoticeById(Long id) {
        return paymentNoticeRepository.findById(id);
    }

    @Override
    public void changePaymentNoticeStatus(long paymentNoticeId, PaymentStatus newStatus) {
        Optional<PaymentNoticeModel> paymentNoticeModel= paymentNoticeRepository.findById(paymentNoticeId);

        paymentNoticeModel.ifPresent( p -> {
            PaymentStatus oldStatus = p.getStatus();
            p.setStatus(newStatus);
            paymentNoticeRepository.save(p);
            logger.info(String.format("Change status for PaymentNotice(ID: %d) from %s to %s.",p.getId(),oldStatus.toString(),newStatus.toString()));
        });
    }

    @Override
    public PaymentModel createPaymentHistory(PaymentModel paymentModel) {
        return paymentRepository.save(paymentModel);
    }

    @Override
    public Optional<PaymentModel> preparePaymentHistory(OrderCallback orderCallback) {

        PaymentModel paymentModel = new PaymentModel();
        Order order = orderCallback.getOrder();
        if(order == null){
            return Optional.empty();
        }
        OrderBuyer buyer = order.getBuyer();
        if(buyer == null){
            return Optional.empty();
        }
        order.getPaymentNoticeId().ifPresent( p -> {
            paymentModel.setEmail(buyer.getEmail());
            paymentModel.setFirstName(buyer.getFirstName());
            paymentModel.setLastName(buyer.getLastName());
            paymentNoticeRepository.findById(p).ifPresent(paymentModel::setPaymentNotice);
            paymentModel.setPaymentValue(order.getTotalAmount());
            paymentModel.setStatus(order.getStatus());
            paymentModel.setPaymentType(order.getPayMethod() != null ? order.getPayMethod().getType() : null);
        });
        return Optional.of(paymentModel);
    }

}
