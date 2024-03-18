package es.template.application.payment.services

import es.template.application.domain.PaymentFactory
import es.template.application.payment.domain.PaymentChanges
import es.template.application.payment.domain.PaymentHistory
import es.template.application.payment.port.`in`.CreateOneOffPaymentUseCase
import es.template.application.payment.port.out.PaymentRepository
import org.springframework.stereotype.Service

@Service
class CreateOneOffPaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentFactory: PaymentFactory
) : CreateOneOffPaymentUseCase {
    override fun createPayment(command: CreateOneOffPaymentUseCase.CreatePaymentCommand): PaymentHistory {
        // Should I have something like creatable
        val oneOffPayment = paymentFactory.createOneOff()
        // That's a big argument to store whole aggregate, not only changes as a separate object
//        paymentRepository.store(oneOffPayment)
        paymentRepository.storeAlternative(PaymentChanges(oneOffPayment.getAggregateHistory()))
        return PaymentHistory(oneOffPayment.getAggregateHistory())
    }
}
