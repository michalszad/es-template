package es.template.application.port.`in`

import es.template.application.domain.PaymentHistory

interface ChargePaymentUseCase {

    fun initializePayment(command: ChargePaymentCommand): PaymentHistory

    // We could reuse the same command as in initialize, or it will be better to distinguish to avoid problem
    // when there will be differences
    data class ChargePaymentCommand(val id: String)
}