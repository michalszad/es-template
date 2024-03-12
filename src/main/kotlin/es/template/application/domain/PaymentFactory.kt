package es.template.application.domain

class PaymentFactory {

    // Not sure if we will have create, initialize, or we join it together
    fun createOneOff(): Initializable = OneOffPayment()
    fun createInitializableFrom(events: List<PaymentEvent>): Initializable = OneOffPayment().apply { rehydrateFrom(events) }
    fun createCompletableFrom(events: List<PaymentEvent>): Completable = InitializedOneOffPayment().apply { rehydrateFrom(events) }
}