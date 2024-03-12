package es.template.application.services

import es.template.application.domain.Payment
import es.template.application.domain.PaymentHistory
import es.template.application.port.`in`.InitializePaymentUseCase
import es.template.application.port.out.AggregateRepository
import es.template.application.port.out.PaymentExternalService
import es.template.application.port.out.PaymentRepository
import org.springframework.stereotype.Service

@Service
class InitializeCommandServiceAlternative(
    private val paymentRepository: PaymentRepository,
    private val paymentExternalService: PaymentExternalService,
) : InitializePaymentUseCase {

    override fun initializePayment(command: InitializePaymentUseCase.InitializePaymentCommand): PaymentHistory {
        // should we distinguish specific payments, in separate repo or methods?
        val payment = paymentRepository.findInitializable(command.id) ?: throw RuntimeException("Ups, improper state")
//        payment.initialize()
//        paymentRepository.storeAlternative(payment.)
        return PaymentHistory(listOf())
    }
}
