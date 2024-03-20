package es.template.application.payment.domain

class CompletedOneOffPayment : Creditable, AggregateRoot<PaymentEvent>() {
    override fun credit(paymentId: String) {
        TODO("Not yet implemented")
    }

    override fun rehydrate(event: PaymentEvent) {
        TODO("Not yet implemented")
    }


}