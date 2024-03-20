package es.template.application.payment.port.`in`

import es.template.application.payment.domain.PaymentHistory

interface CreateOneOffPaymentUseCase {

    fun createPayment(command: CreatePaymentCommand): PaymentHistory

    data class CreatePaymentCommand(val paymentId: String, val description: String)
}