package es.template.application.domain

// We could use simplified version of AggregateRoot in this case
class OneOffPaymentAlternative: InitializableOneOff, AggregateRoot<PaymentEvent>() {

    override fun initialize(paymentId: String): CompletableOneOff {
        // some funny logic
        apply(PaymentInitializedEvent(paymentId = paymentId))
        return InitializedOneOffPaymentAlternative().apply { rehydrateFrom(getAggregateHistory()) }
    }

    override fun rehydrate(event: PaymentEvent) {
        // In case of more specific domain objects, we could avoid rehydrate here
        TODO("Not yet implemented")
    }
}