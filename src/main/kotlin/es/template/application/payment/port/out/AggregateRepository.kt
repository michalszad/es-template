package es.template.application.payment.port.out

interface AggregateRepository<T> {
    fun findById(id: String): T?
    fun store(aggregate: T)
    fun create(): T
}