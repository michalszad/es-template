package es.template.application.payment.port.out

import es.template.application.payment.domain.*

interface PaymentRepository {

    // store whole aggregate, and retrieve events
    fun store(aggregateId: PaymentAggregate, history: History<PaymentEvent>)

    // store events
    fun storeAlternative(aggregateId: PaymentAggregate, changes: PaymentChanges)
    fun findInitializable(id: String): Initializable
    fun findCompletable(id: String): Completable
}