package es.template.application.payment.services

import es.template.application.payment.domain.PaymentAggregate
import es.template.application.payment.domain.PaymentChanges
import es.template.application.payment.domain.PaymentHistory
import es.template.application.payment.port.`in`.ChargeRecurringPaymentUseCase
import es.template.application.payment.port.out.PaymentRepository

class ChargeRecurringPaymentService(
        private val paymentRepository: PaymentRepository
) : ChargeRecurringPaymentUseCase {
    override fun initializePayment(command: ChargeRecurringPaymentUseCase.ChargePaymentCommand): PaymentHistory {

        // Create should be earlier
        // we could use combination of use cases/services
        // or we could copy the code from services/use cases
        val payment = paymentRepository.findInitializable(command.id)
        val initialized = payment.initialize(command.id)
        // maybe we can also pass changes to save it only once
        paymentRepository.storeAlternative(PaymentAggregate(command.id), PaymentChanges(initialized.pullChanges()))

        val completed = initialized.complete(command.id)
        paymentRepository.storeAlternative(PaymentAggregate(command.id), PaymentChanges(completed.pullChanges()))

        return PaymentHistory(completed.getAggregateHistory())
    }
}