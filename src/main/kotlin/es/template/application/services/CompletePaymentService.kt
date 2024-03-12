package es.template.application.services

import es.template.application.domain.Payment
import es.template.application.domain.PaymentHistory
import es.template.application.port.`in`.CompletePaymentUseCase
import es.template.application.port.out.AggregateRepository
import org.springframework.stereotype.Service

@Service
class CompletePaymentService(private val paymentRepository: AggregateRepository<Payment>) : CompletePaymentUseCase {
    override fun completePayment(command: CompletePaymentUseCase.CompletePaymentCommand): PaymentHistory {
        val payment = paymentRepository.findById(command.id) ?: throw RuntimeException("Error")
        payment.complete()
        paymentRepository.store(payment)
        return PaymentHistory(payment.getAggregateHistory())
    }
}
