package es.template.application.services

import es.template.application.domain.PaymentHistory
import es.template.application.port.`in`.CompletePaymentUseCase
import es.template.application.port.out.PaymentRepository
import org.springframework.stereotype.Service

@Service
class CompletePaymentServiceAlternative(
    private val paymentRepository: PaymentRepository
) : CompletePaymentUseCase {
    override fun completePayment(command: CompletePaymentUseCase.CompletePaymentCommand): PaymentHistory {
        val payment = paymentRepository.findCompletable(command.id) ?: throw RuntimeException("Ups, Improper state!")
        val paymentChanges = payment.complete((command.id))
        // Approach with aggregate
//        paymentRepository.store(payment)

        // Or
        paymentRepository.storeAlternative(paymentChanges)
        return PaymentHistory(payment.getAggregateHistory())
    }
}
