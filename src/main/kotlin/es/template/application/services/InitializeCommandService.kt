package es.template.application.services

import es.template.application.domain.Payment
import es.template.application.domain.PaymentHistory
import es.template.application.port.`in`.InitializePaymentUseCase
import es.template.application.port.out.AggregateRepository
import es.template.application.port.out.PaymentExternalService
import org.springframework.stereotype.Service

@Service
class InitializeCommandService(
    private val paymentRepository: AggregateRepository<Payment>,
    private val paymentExternalService: PaymentExternalService,
) : InitializePaymentUseCase {

    override fun initializePayment(command: InitializePaymentUseCase.InitializePaymentCommand): PaymentHistory {
        val payment = paymentRepository.findById(command.id) ?: paymentRepository.create()
        payment.initialize()
        paymentRepository.store(payment)
        return PaymentHistory(payment.getAggregateHistory())
    }
}
