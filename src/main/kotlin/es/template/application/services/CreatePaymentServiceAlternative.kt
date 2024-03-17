package es.template.application.services

import es.template.application.domain.PaymentFactory
import es.template.application.domain.PaymentHistory
import es.template.application.port.`in`.CreatePaymentUseCase
import es.template.application.port.out.PaymentRepository
import org.springframework.stereotype.Service

@Service
class CreatePaymentServiceAlternative(
    private val paymentRepository: PaymentRepository,
    private val paymentFactory: PaymentFactory
) : CreatePaymentUseCase {
    override fun createPayment(command: CreatePaymentUseCase.CreatePaymentCommand): PaymentHistory {
        // Should I have something like creatable
        val oneOffPayment = paymentFactory.createOneOff()
        // That's a big argument to store whole aggregate, not only changes as a separate object
        paymentRepository.store(oneOffPayment)
//        paymentRepository.storeAlternative(oneOffPayment.)
        return PaymentHistory(oneOffPayment.getAggregateHistory())
    }
}
