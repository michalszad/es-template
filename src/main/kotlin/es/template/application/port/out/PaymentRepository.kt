package es.template.application.port.out

import es.template.application.domain.*

interface PaymentRepository {


    // store whole aggregate, and retrieve events
    fun store(payment: Storable<PaymentEvent>)

    // store events
    fun storeAlternative(events: PaymentChanges)
    fun findInitializable(id: String): Initializable?
    fun findInitializableAlternative(id: String): InitializableOneOff?
    fun findCompletable(id: String): Completable?
}