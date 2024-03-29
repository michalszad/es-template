package es.template.application.domain

// We could use simplified version of AggregateRoot in this case
class OneOffPayment: Initializable, AggregateRoot<PaymentEvent>() {

    override fun initialize(paymentId: String): PaymentChanges {
        // some funny logic
        apply(PaymentInitializedEvent(paymentId = paymentId))
        return PaymentChanges(pullChanges())
    }

    override fun rehydrate(event: PaymentEvent) {
        // In case of more specific domain objects, we could avoid rehydrate here
        TODO("Not yet implemented")
    }
}