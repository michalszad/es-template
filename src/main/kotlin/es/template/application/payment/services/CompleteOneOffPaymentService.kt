package es.template.application.payment.services


import es.template.application.payment.domain.PaymentAggregate
import es.template.application.payment.domain.PaymentChanges
import es.template.application.payment.domain.PaymentHistory
import es.template.application.payment.port.`in`.CompleteOneOffPaymentUseCase
import es.template.application.payment.port.out.PaymentRepository

class CompleteOneOffPaymentService(
        private val paymentRepository: PaymentRepository
) : CompleteOneOffPaymentUseCase {
    override fun completePayment(command: CompleteOneOffPaymentUseCase.CompletePaymentCommand): PaymentHistory {
        val payment = paymentRepository.findCompletable(command.id)
        val completed = payment.complete((command.id))
        // Approach with aggregate
//        paymentRepository.store(payment)

        // Or
        paymentRepository.storeAlternative(PaymentAggregate(command.id), PaymentChanges(completed.pullChanges()))
        return PaymentHistory(completed.getAggregateHistory())
    }
}
