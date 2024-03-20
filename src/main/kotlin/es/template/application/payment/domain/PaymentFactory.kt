package es.template.application.payment.domain

import es.template.application.payment.port.out.PaymentExternalService

class PaymentFactory(
        private val paymentExternalService: PaymentExternalService
) {

    // Not sure if we will have create, initialize, or we join it together
    fun createOneOff(): Initializable = OneOffPayment()

    // throw exception when can't rehydrate
    fun createInitializableFrom(events: List<PaymentEvent>): Initializable =
            when (events.lastOrNull()) {
                is PaymentCreatedEvent -> OneOffPayment().apply { rehydrateFrom(events) }
                else -> throw java.lang.RuntimeException("Payment in an improper state!")
            }

    // throw exception when can't rehydrate
    fun createCompletableFrom(events: List<PaymentEvent>): Completable =
            when (events.lastOrNull()) {
                is PaymentInitializedEvent -> InitializedOneOffPayment().apply { rehydrateFrom(events) }
                else -> throw java.lang.RuntimeException("Payment in an improper state!")
            }
}