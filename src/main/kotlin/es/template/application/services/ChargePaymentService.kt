package es.template.application.services

import es.template.application.domain.PaymentFactory
import es.template.application.port.out.PaymentRepository

class ChargePaymentService(
    private val paymentRepository: PaymentRepository,
    private val paymentFactory: PaymentFactory
) {


}