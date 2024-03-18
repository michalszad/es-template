package es.template.application.payment.port.`in`

import es.template.application.domain.PaymentHistory

interface InitializeOneOffPaymentUseCase {

    fun initializePayment(command: InitializePaymentCommand): PaymentHistory

    data class InitializePaymentCommand(val id: String)
}