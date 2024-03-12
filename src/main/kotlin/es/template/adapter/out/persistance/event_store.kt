package es.template.adapter.out.persistance

import es.template.application.domain.Aggregate
import es.template.application.domain.Event

interface EventStore {
    fun store(aggregate: Aggregate, event: Event)
    fun findByAggregate(aggregate: Aggregate): List<Event>
}

class InMemoryBasedEventStore : EventStore {

    private val inMemoryDb: MutableMap<String, List<Event>> = mutableMapOf()

    override fun store(aggregate: Aggregate, event: Event) {
        val eventStream = inMemoryDb[aggregate.id]

        if (eventStream.isNullOrEmpty()) {
            inMemoryDb[aggregate.id] = listOf(event)
        } else {
            inMemoryDb[aggregate.id] = eventStream.plus(event)
        }
    }

    override fun findByAggregate(aggregate: Aggregate): List<Event> {
        return inMemoryDb.getOrDefault(aggregate.id, emptyList())
    }
}

