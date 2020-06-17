package org.fibi.usos.repository.payment;

import org.fibi.usos.model.payment.PaymentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentModel,Long> {
}
