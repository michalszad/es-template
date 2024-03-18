package es.template.application.domain

class PaymentFactoryAlternative {

    // Not sure if we will have create, initialize, or we join it together
    fun createOneOff(): InitializableOneOff = OneOffPaymentAlternative()
    fun createInitializableFrom(events: List<PaymentEvent>): InitializableOneOff = OneOffPaymentAlternative().apply { rehydrateFrom(events) }
    fun createCompletableFrom(events: List<PaymentEvent>): CompletableOneOff = InitializedOneOffPaymentAlternative().apply { rehydrateFrom(events) }
}