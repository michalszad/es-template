package es.template.application.services

import es.template.application.domain.PaymentChanges
import es.template.application.domain.PaymentFactory
import es.template.application.domain.PaymentHistory
import es.template.application.port.`in`.ChargePaymentUseCase
import es.template.application.port.out.PaymentRepository

class ChargePaymentServiceAlternative2(
    private val paymentRepository: PaymentRepository
) : ChargePaymentUseCase {
    override fun initializePayment(command: ChargePaymentUseCase.ChargePaymentCommand): PaymentHistory {

        // Create should be earlier
        // we could use combination of use cases/services
        // or we could copy the code from services/use cases
        val payment = paymentRepository.findInitializableAlternative(command.id) ?: throw RuntimeException("Ups, improper state")
        val initialized = payment.initialize(command.id)
        // maybe we can also pass changes to save it only once
        paymentRepository.storeAlternative(PaymentChanges(initialized.pullChanges()))

        val completed = initialized.complete(command.id)
        paymentRepository.storeAlternative(PaymentChanges(completed.pullChanges()))

        return PaymentHistory(completed.getAggregateHistory())
    }
}