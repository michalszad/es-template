package es.template.application.services

import es.template.application.domain.PaymentFactory
import es.template.application.domain.PaymentHistory
import es.template.application.port.`in`.ChargePaymentUseCase
import es.template.application.port.`in`.CompletePaymentUseCase
import es.template.application.port.`in`.InitializePaymentUseCase
import es.template.application.port.out.PaymentRepository

class ChargePaymentService(
    private val initializeUseCase: InitializePaymentUseCase,
    private val completeUseCase: CompletePaymentUseCase
) : ChargePaymentUseCase {
    override fun initializePayment(command: ChargePaymentUseCase.ChargePaymentCommand): PaymentHistory {

        // Create should be earlier
        // we could use combination of use cases/services
        // or we could copy the code from services/use cases
       initializeUseCase.initializePayment(InitializePaymentUseCase.InitializePaymentCommand(command.id))

        // Not sure if that will be able to do, with charge command, maybe only id will be enough
        return completeUseCase.completePayment(CompletePaymentUseCase.CompletePaymentCommand(command.id))
    }
}