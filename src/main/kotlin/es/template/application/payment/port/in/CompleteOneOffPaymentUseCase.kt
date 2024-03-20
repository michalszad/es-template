package es.template.application.payment.port.`in`

import es.template.application.payment.domain.PaymentHistory

interface CompleteOneOffPaymentUseCase {

    fun completePayment(command: CompletePaymentCommand): PaymentHistory

    data class CompletePaymentCommand(val id: String)
}