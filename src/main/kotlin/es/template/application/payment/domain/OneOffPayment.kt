package es.template.application.payment.domain

// We could use simplified version of AggregateRoot in this case
class OneOffPayment: Initializable, AggregateRoot<PaymentEvent>() {

    override fun initialize(paymentId: String): Completable {
        // some funny logic
        apply(PaymentInitializedEvent(paymentId = paymentId))
        return InitializedOneOffPayment().apply { rehydrateFrom(getAggregateHistory()) }
    }

    override fun rehydrate(event: PaymentEvent) {
        // In case of more specific domain objects, we could avoid rehydrate here
        TODO("Not yet implemented")
    }
}