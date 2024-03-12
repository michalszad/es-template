package es.template.application.domain

class OneOffPayment: Initializable, AggregateRoot<PaymentEvent>() {

    override fun initialize(): PaymentHistory {
        TODO("Not yet implemented")

    }

    override fun rehydrate(event: PaymentEvent) {
        // In case of more specific domain objects, we could avoid rehydrate here
        TODO("Not yet implemented")
    }
}