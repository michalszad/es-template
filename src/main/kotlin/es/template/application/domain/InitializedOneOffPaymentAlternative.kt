package es.template.application.domain

// We could avoid probably aggregate root, or only have rehydrate there
class InitializedOneOffPaymentAlternative : CompletableOneOff, AggregateRoot<PaymentEvent>() {
    override fun rehydrate(event: PaymentEvent) {
        TODO("Not yet implemented")
    }

    override fun complete(paymentId: String): CreditableOneOff {
        apply(PaymentCompletedEvent(paymentId= paymentId))
        return CompletedOneOff().apply { rehydrateFrom(getAggregateHistory()) }
    }
}