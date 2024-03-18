package es.template.adapter.out.persistance

import es.template.application.domain.Payment
import es.template.application.domain.PaymentAggregate
import es.template.application.domain.PaymentEvent
import es.template.application.port.out.AggregateRepository

class InMemoryPaymentRepository(
        private val eventStore: EventStore,
        private val paymentProjection: PaymentProjection) : AggregateRepository<Payment> {

    companion object {
        private val PAYMENT_NOT_FOUND: Payment? = null
    }

    override fun findById(id: String): Payment? {
        val history = eventStore
                .findByAggregate(PaymentAggregate(id))
                .filterIsInstance<PaymentEvent>()

        return if (history.isNotEmpty()) {
            val payment = Payment()
            payment.rehydrateFrom(history)
            payment
        } else PAYMENT_NOT_FOUND
    }

    override fun store(aggregate: Payment) {
        aggregate.pullChanges().forEach { eventStore.store(PaymentAggregate(it.paymentId), it) }
    }

    override fun create(): Payment {
        return Payment()
    }
}
