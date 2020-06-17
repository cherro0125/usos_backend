package org.fibi.usos.repository.payment;

import org.fibi.usos.model.news.NewsModel;
import org.fibi.usos.model.payment.PaymentNoticeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentNoticeRepository extends CrudRepository<PaymentNoticeModel, Long> {
    Optional<Collection<PaymentNoticeModel>> findAllByPayerId(Long payerId);
}
