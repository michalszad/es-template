package es.template.adapter.out.persistance

import es.template.application.domain.AggregateRoot
import es.template.application.domain.Completable
import es.template.application.domain.Initializable
import es.template.application.domain.PaymentEvent
import es.template.application.port.out.PaymentRepository

// we could use here PaymentFactory from domain
class MemoryBasedPaymentRepository : PaymentRepository {
    override fun store(payment: AggregateRoot<PaymentEvent>) {
        TODO("Not yet implemented")
    }

    override fun storeAlternative(events: List<PaymentEvent>) {
        TODO("Not yet implemented")
    }

    override fun findInitializable(id: String): Initializable? {
        TODO("Not yet implemented")
    }

    override fun findCompletable(id: String): Completable? {
        TODO("Not yet implemented")
    }
}