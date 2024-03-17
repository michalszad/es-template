package es.template.application.domain

class CompletedOneOff: CreditableOneOff, AggregateRoot<PaymentEvent>() {
    override fun credit(paymentId: String) {
        TODO("Not yet implemented")
    }

    override fun rehydrate(event: PaymentEvent) {
        TODO("Not yet implemented")
    }


}