package org.fibi.usos.listener.seeder.payment;

import org.fibi.usos.listener.seeder.Seeder;
import org.fibi.usos.model.payment.PaymentNoticeModel;
import org.fibi.usos.model.payment.PaymentPurposeType;
import org.fibi.usos.model.payment.PaymentStatus;
import org.fibi.usos.model.user.UserModel;
import org.fibi.usos.repository.payment.PaymentNoticeRepository;
import org.fibi.usos.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class PaymentNoticeSeeder implements Seeder {

    private static final Logger logger  = LoggerFactory.getLogger(PaymentNoticeModel.class);
    private PaymentNoticeRepository paymentNoticeRepository;
    private UserRepository userRepository;

    public PaymentNoticeSeeder(PaymentNoticeRepository paymentNoticeRepository, UserRepository userRepository) {
        this.paymentNoticeRepository = paymentNoticeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void init() {

    }

    @Override
    public void seed() {
        Optional<UserModel> student = userRepository.findUserModelByUsername("student");
        if(student.isPresent()){
            PaymentNoticeModel paymentNotice = new PaymentNoticeModel();
            paymentNotice.setPayer(student.get());
            paymentNotice.setPayableTo(Date.valueOf(LocalDate.parse("2020-07-07")));
            paymentNotice.setDetails("Opłata za semestr");
            paymentNotice.setPurposeType(PaymentPurposeType.SEMESTER_PAYMENT);
            paymentNotice.setStatus(PaymentStatus.CREATED);
            paymentNotice.setValue(1000);
            paymentNoticeRepository.save(paymentNotice);
            logger.info("Create new PaymentNotice#1");

            PaymentNoticeModel paymentNotice2 = new PaymentNoticeModel();
            paymentNotice2.setPayer(student.get());
            paymentNotice2.setPayableTo(Date.valueOf(LocalDate.parse("2020-07-02")));
            paymentNotice2.setDetails("Opłata za pościg");
            paymentNotice2.setPurposeType(PaymentPurposeType.COURSE_CHACE);
            paymentNotice2.setStatus(PaymentStatus.REJECTED);
            paymentNotice2.setValue(10000);
            paymentNoticeRepository.save(paymentNotice2);
            logger.info("Create new PaymentNotice#2");
        }else{
            logger.error("User with username: student not found. PaymentNoticeSeeder not started.");
        }

    }
}
