package es.template.adapter.out.persistance

import es.template.application.domain.*
import es.template.application.port.out.PaymentRepository

// we could use here PaymentFactory from domain
class MemoryBasedPaymentRepository(
        private val paymentFactory: PaymentFactory,
        private val paymentFactoryAlternative: PaymentFactoryAlternative,
        private val eventStore: EventStore,
        private val paymentProjection: PaymentProjection
) : PaymentRepository {


    override fun store(payment: Storable<PaymentEvent>) {
        TODO("Not yet implemented")
    }

    override fun storeAlternative(events: PaymentChanges) {
        TODO("Not yet implemented")
    }

    override fun findInitializable(id: String): Initializable? {
        val history = eventStore
                .findByAggregate(PaymentAggregate(id))
                .filterIsInstance<PaymentEvent>()

        return paymentFactory.createInitializableFrom(history)
    }

    override fun findInitializableAlternative(id: String): InitializableOneOff? {
        val history = eventStore
                .findByAggregate(PaymentAggregate(id))
                .filterIsInstance<PaymentEvent>()

        return paymentFactoryAlternative.createInitializableFrom(history)
    }

    override fun findCompletable(id: String): Completable? {
        val history = eventStore
                .findByAggregate(PaymentAggregate(id))
                .filterIsInstance<PaymentEvent>()

        return paymentFactory.createCompletableFrom(history)
    }
}