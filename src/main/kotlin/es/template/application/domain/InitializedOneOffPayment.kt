package es.template.application.domain

class InitializedOneOffPayment : Completable, AggregateRoot<PaymentEvent>() {
    override fun rehydrate(event: PaymentEvent) {
        TODO("Not yet implemented")
    }

    override fun complete(): PaymentHistory {
        TODO("Not yet implemented")
    }
}