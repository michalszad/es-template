package es.template.adapter.out.persistance

import es.template.application.payment.domain.*
import es.template.application.payment.port.out.PaymentRepository

// we could use here PaymentFactory from domain
class MemoryBasedPaymentRepository(
        private val paymentFactory: PaymentFactory,
        private val eventStore: EventStore,
        private val paymentProjection: PaymentProjection,
        private val paymentIntegrationEvents: PaymentIntegrationEvents
) : PaymentRepository {

    override fun store(aggregateId: PaymentAggregate, history: History<PaymentEvent>) {
        history.getAggregateHistory().forEach { paymentEvent ->
            eventStore.store(aggregateId, paymentEvent)

            // Additional stuff, like adding projections, integration events etc.
            paymentProjection.store(paymentEvent)
            paymentIntegrationEvents.store(paymentEvent)
        }
    }

    override fun storeAlternative(aggregateId: PaymentAggregate, changes: PaymentChanges) {
        changes.events.forEach { paymentEvent ->
            eventStore.store(aggregateId, paymentEvent)

            // Additional stuff, like adding projections, integration events etc.
            paymentProjection.store(paymentEvent)
            paymentIntegrationEvents.store(paymentEvent)
        }
    }

    override fun findInitializable(id: String): Initializable {
        val history = eventStore
                .findByAggregate(PaymentAggregate(id))
                .filterIsInstance<PaymentEvent>()

        return paymentFactory.createInitializableFrom(history)
    }

    override fun findCompletable(id: String): Completable {
        val history = eventStore
                .findByAggregate(PaymentAggregate(id))
                .filterIsInstance<PaymentEvent>()

        return paymentFactory.createCompletableFrom(history)
    }
}