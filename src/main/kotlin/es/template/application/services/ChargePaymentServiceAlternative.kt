package es.template.application.services

import es.template.application.domain.PaymentFactory
import es.template.application.domain.PaymentHistory
import es.template.application.port.`in`.ChargePaymentUseCase
import es.template.application.port.out.PaymentRepository

class ChargePaymentServiceAlternative(
    private val paymentRepository: PaymentRepository,
    private val paymentFactory: PaymentFactory
) : ChargePaymentUseCase {
    override fun initializePayment(command: ChargePaymentUseCase.ChargePaymentCommand): PaymentHistory {

        // Create should be earlier
        // we could use combination of use cases/services
        // or we could copy the code from services/use cases
        val payment = paymentRepository.findInitializable(command.id) ?: throw RuntimeException("Ups, improper state")
        val changes = payment.initialize(command.id)
        paymentRepository.storeAlternative(changes)

        val initializedPayment = paymentRepository.findCompletable(command.id) ?: throw RuntimeException("Ups, Improper state!")
        val paymentChanges = initializedPayment.complete(command.id)
        paymentRepository.storeAlternative(paymentChanges)

        return PaymentHistory(payment.getAggregateHistory())
    }
}