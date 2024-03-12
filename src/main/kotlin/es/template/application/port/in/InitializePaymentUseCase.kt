package es.template.application.port.`in`

import es.template.application.domain.PaymentHistory

interface InitializePaymentUseCase {

    fun initializePayment(command: InitializePaymentCommand): PaymentHistory

    data class InitializePaymentCommand(val id: String)
}