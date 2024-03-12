package es.template.application.port.`in`

import es.template.application.domain.PaymentHistory

interface CompletePaymentUseCase {

    fun completePayment(command: CompletePaymentCommand): PaymentHistory

    data class CompletePaymentCommand(val id: String)
}