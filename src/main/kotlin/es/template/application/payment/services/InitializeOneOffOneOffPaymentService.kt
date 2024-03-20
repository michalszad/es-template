package es.template.application.payment.services

import es.template.application.payment.domain.PaymentAggregate
import es.template.application.payment.domain.PaymentChanges
import es.template.application.payment.domain.PaymentHistory
import es.template.application.payment.port.`in`.InitializeOneOffPaymentUseCase
import es.template.application.payment.port.out.PaymentRepository

// One off or sale and recurring
class InitializeOneOffOneOffPaymentService(
        private val paymentRepository: PaymentRepository
) : InitializeOneOffPaymentUseCase {

    override fun initializePayment(command: InitializeOneOffPaymentUseCase.InitializePaymentCommand): PaymentHistory {
        // should we distinguish specific payments, in separate repo or methods?
        val payment = paymentRepository.findInitializable(command.id)
        val initialized = payment.initialize(command.id)

        // Approach with aggregate
//        paymentRepository.store(initialized)

        // Or
        paymentRepository.storeAlternative(PaymentAggregate(command.id), PaymentChanges(initialized.pullChanges()))
        return PaymentHistory(initialized.getAggregateHistory())
    }
}
