package es.template.application.domain

interface Event

abstract class Aggregate(val type: String, val id: String)

data class PaymentAggregate(val identifier: String) : Aggregate(AGGREGATE_TYPE, identifier) {
    companion object {
        const val AGGREGATE_TYPE = "Payment"
    }
}

data class PaymentHistory(val events: List<Event>)

interface Storable {
    // we need interface for aggregate root, which can be extended by other interfaces, to be able to store model, or extract changes
}

// new approach with ... model

// We could also synchronize adding to read model in DB, flat structure of order, and get it later in Controller
interface Initializable {
    fun initialize(): PaymentHistory
}

interface Completable {
    fun complete(): PaymentHistory
}