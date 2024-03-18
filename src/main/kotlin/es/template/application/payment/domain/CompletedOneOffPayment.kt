package es.template.application.payment.domain

import es.template.application.domain.AggregateRoot

class CompletedOneOffPayment : Creditable, AggregateRoot<PaymentEvent>() {
    override fun credit(paymentId: String) {
        TODO("Not yet implemented")
    }

    override fun rehydrate(event: PaymentEvent) {
        TODO("Not yet implemented")
    }


}