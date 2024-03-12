package es.template.application.domain

interface Event

abstract class Aggregate(val type: String, val id: String)

data class PaymentAggregate(val identifier: String) : Aggregate(AGGREGATE_TYPE, identifier) {
    companion object {
        const val AGGREGATE_TYPE = "Payment"
    }
}

data class PaymentHistory(val events: List<PaymentEvent>)
data class PaymentChanges(val events: List<PaymentEvent>)

// In alternative payment changes approach, we could remove it
interface Storable<E : Event> {
    // we need interface for aggregate root, which can be extended by other interfaces, to be able to store model, or extract changes
    // I would like to have changes only
    fun pullChanges(): List<E>
    fun getAggregateHistory(): List<E>
}

// new approach with ... model

// We could also synchronize adding to read model in DB, flat structure of order, and get it later in Controller

interface Creatable: Storable<PaymentEvent> {

    fun create(): PaymentChanges
}

interface Initializable: Storable<PaymentEvent> {
    fun initialize(): PaymentChanges
}

interface Completable: Storable<PaymentEvent> {
    fun complete(): PaymentChanges
}