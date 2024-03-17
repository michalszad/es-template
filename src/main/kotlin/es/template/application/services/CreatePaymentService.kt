package es.template.application.services

import es.template.application.domain.Payment
import es.template.application.domain.PaymentHistory
import es.template.application.port.`in`.CreatePaymentUseCase
import es.template.application.port.out.AggregateRepository
import org.springframework.stereotype.Service

@Service
class CreatePaymentService(private val paymentRepository: AggregateRepository<Payment>) : CreatePaymentUseCase {
    override fun createPayment(command: CreatePaymentUseCase.CreatePaymentCommand): PaymentHistory {
        val payment = Payment.create(command.paymentId, command.description)
        paymentRepository.store(payment)
        return PaymentHistory(payment.getAggregateHistory())
    }
}
