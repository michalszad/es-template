package es.template.adapter.`in`.web

import es.template.application.payment.domain.*

// Payment history could be sealed class with different types, history of events or othet
interface Projection {
    fun createFrom(paymentHistory: PaymentHistory): PaymentResponse
}

class PaymentQueryProjection : Projection {

    lateinit var id: String
    lateinit var description: String
    lateinit var state: String

    private fun on(event: PaymentCreatedEvent) {
        id = event.paymentId
        state = "CREATED"
        description = event.description
    }

    private fun on(event: PaymentInitializedEvent) {
        state = "INITIALIZED"
    }

    private fun on(event: PaymentCompletedEvent) {
        state = "COMPLETED"
    }

    private fun rehydrate(event: Event) {
        when (event) {
            is PaymentCreatedEvent -> on(event)
            is PaymentInitializedEvent -> on(event)
            is PaymentCompletedEvent -> on(event)
            else -> {
                // Omit event
            }
        }
    }

    override fun createFrom(paymentHistory: PaymentHistory): PaymentResponse {
        paymentHistory.events.forEach { event -> this.rehydrate(event) }
        return when (state) {
            "CREATED", "INITIALIZED", "COMPLETED" ->
                PaymentResponse(
                        status = "SUCCESS",
                        paymentId = id,
                        parameters = mapOf("description" to description)
                )
            "COMPLETED_UNKNOWN" ->
                PaymentResponse(
                        status = "UNKNOWN",
                        paymentId = id
                )
            "INITIALIZE_FAILED", "COMPLETED_FAILED" ->
                PaymentResponse(
                        status = "FAILED",
                        paymentId = id,
                )
            else ->
                PaymentResponse(
                        status = "FAILED",
                        paymentId = id,
                        errorContext = mapOf("errorMessage" to "Unexpected error")
                )
        }
    }
}
