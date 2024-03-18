package es.template.application.payment.services


import es.template.application.payment.domain.PaymentChanges
import es.template.application.payment.domain.PaymentHistory
import es.template.application.payment.port.`in`.CompleteOneOffPaymentUseCase
import es.template.application.payment.port.out.PaymentRepository
import org.springframework.stereotype.Service

@Service
class CompleteOneOffPaymentService(
    private val paymentRepository: PaymentRepository
) : CompleteOneOffPaymentUseCase {
    override fun completePayment(command: CompleteOneOffPaymentUseCase.CompletePaymentCommand): PaymentHistory {
        val payment = paymentRepository.findCompletable(command.id)
        val completed = payment.complete((command.id))
        // Approach with aggregate
//        paymentRepository.store(payment)

        // Or
        paymentRepository.storeAlternative(PaymentChanges(completed.pullChanges()))
        return PaymentHistory(completed.getAggregateHistory())
    }
}
