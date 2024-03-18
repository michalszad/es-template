package es.template.application.domain

import es.template.application.payment.domain.*

class PaymentFactory {

    // Not sure if we will have create, initialize, or we join it together
    fun createOneOff(): Initializable = OneOffPayment()

    // throw exception when can't rehydrate
    fun createInitializableFrom(events: List<PaymentEvent>): Initializable =
        OneOffPayment().apply { rehydrateFrom(events) }

    // throw exception when can't rehydrate
    fun createCompletableFrom(events: List<PaymentEvent>): Completable =
        InitializedOneOffPayment().apply { rehydrateFrom(events) }
}