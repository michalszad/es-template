package es.template.adapter.out.persistance

import es.template.application.domain.*
import es.template.application.port.out.PaymentRepository

// we could use here PaymentFactory from domain
class MemoryBasedPaymentRepository : PaymentRepository {


    override fun store(payment: Storable<PaymentEvent>) {
        TODO("Not yet implemented")
    }

    override fun storeAlternative(events: PaymentChanges) {
        TODO("Not yet implemented")
    }

    override fun findInitializable(id: String): Initializable? {
        TODO("Not yet implemented")
    }

    override fun findInitializableAlternative(id: String): InitializableOneOff? {
        TODO("Not yet implemented")
    }

    override fun findCompletable(id: String): Completable? {
        TODO("Not yet implemented")
    }
}