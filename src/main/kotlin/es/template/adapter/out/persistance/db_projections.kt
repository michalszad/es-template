package es.template.adapter.out.persistance

import es.template.application.domain.PaymentEvent

// Name could be different, here we put code to create flat projections in DB

interface PaymentProjection {

    fun store(event: PaymentEvent)
}

class DbBasedPaymentProjection: PaymentProjection {
    override fun store(event: PaymentEvent) {
        // store data in db, based on specific events
        TODO("Not yet implemented")
    }
}

interface PaymentIntegrationEvents {

    fun store(event: PaymentEvent)
}

class DbBasedPaymentIntegrationEvents: PaymentIntegrationEvents {
    override fun store(event: PaymentEvent) {
        // convert to integration event and store in DB, later custom job will send it on his merry way
        TODO("Not yet implemented")
    }
}

// we could introduce here facade, to avoid using more than one class for storing different projections