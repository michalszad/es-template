package es.template.application.domain

abstract class AggregateRoot<E : Event> {

    private val changes = mutableListOf<E>()
    private val aggregateHistory = mutableListOf<E>()

    fun pullChanges(): List<E> {
        val changes = changes.toList()
        this.changes.clear()
        return changes
    }

    fun getAggregateHistory(): List<E> {
        return aggregateHistory
    }

    // Load from history
    fun rehydrateFrom(history: List<E>) {
        history.forEach {
            apply(it)
            aggregateHistory.add(it)
        }
    }

    protected fun applyEvent(event: E) {
        apply(event)
        add(event)
    }

    private fun apply(event: E) {
        rehydrate(event)
    }

    protected abstract fun rehydrate(event: E)

    private fun add(event: E) {
        changes.add(event)
        aggregateHistory.add(event)
    }
}
