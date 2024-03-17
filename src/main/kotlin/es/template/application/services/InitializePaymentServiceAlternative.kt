package es.template.application.services

import es.template.application.domain.PaymentHistory
import es.template.application.port.`in`.InitializePaymentUseCase
import es.template.application.port.out.PaymentExternalService
import es.template.application.port.out.PaymentRepository
import org.springframework.stereotype.Service

@Service
class InitializePaymentServiceAlternative(
    private val paymentRepository: PaymentRepository,
    private val paymentExternalService: PaymentExternalService,
) : InitializePaymentUseCase {

    override fun initializePayment(command: InitializePaymentUseCase.InitializePaymentCommand): PaymentHistory {
        // should we distinguish specific payments, in separate repo or methods?
        val payment = paymentRepository.findInitializable(command.id) ?: throw RuntimeException("Ups, improper state")
        val paymentChanges = payment.initialize(command.id)

        // Approach with aggregate
        paymentRepository.store(payment)

        // Or
        paymentRepository.storeAlternative(paymentChanges)
        return PaymentHistory(listOf())
    }
}
