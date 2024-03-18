package es.template.application.payment.port.out

import es.template.application.payment.domain.*

interface PaymentRepository {

    // store whole aggregate, and retrieve events
    fun store(payment: History<PaymentEvent>)

    // store events
    fun storeAlternative(events: PaymentChanges)
    fun findInitializable(id: String): Initializable
    fun findCompletable(id: String): Completable
}