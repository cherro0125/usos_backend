package org.fibi.usos.service.payment;

import org.fibi.usos.dto.payment.notice.PaymentNoticeDto;
import org.fibi.usos.model.payment.PaymentNoticeModel;
import org.fibi.usos.repository.payment.PaymentNoticeRepository;
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

    public PaymentStandardService(PaymentNoticeRepository paymentNoticeRepository) {
        this.paymentNoticeRepository = paymentNoticeRepository;
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
}
