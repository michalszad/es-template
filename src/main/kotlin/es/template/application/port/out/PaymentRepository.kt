package es.template.application.port.out

import es.template.application.domain.AggregateRoot
import es.template.application.domain.Completable
import es.template.application.domain.Initializable
import es.template.application.domain.PaymentEvent

interface PaymentRepository {


    // store whole aggregate, and retrieve events
    fun store(payment: AggregateRoot<PaymentEvent>)

    // store events
    fun storeAlternative(events: List<PaymentEvent>)
    fun findInitializable(id: String): Initializable?
    fun findCompletable(id: String): Completable?
}