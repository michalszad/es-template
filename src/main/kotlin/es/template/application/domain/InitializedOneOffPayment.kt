package es.template.application.domain

// We could avoid probably aggregate root, or only have rehydrate there
class InitializedOneOffPayment : Completable, AggregateRoot<PaymentEvent>() {
    override fun rehydrate(event: PaymentEvent) {
        TODO("Not yet implemented")
    }

    override fun complete(): PaymentChanges {
        TODO("Not yet implemented")
    }
}