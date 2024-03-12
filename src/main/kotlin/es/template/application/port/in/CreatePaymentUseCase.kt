package es.template.application.port.`in`

import es.template.application.domain.PaymentHistory

interface CreatePaymentUseCase {

    fun createPayment(command: CreatePaymentCommand): PaymentHistory

    data class CreatePaymentCommand(val paymentId: String, val description: String)
}