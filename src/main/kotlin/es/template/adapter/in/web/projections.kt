package es.template.adapter.`in`.web

import es.template.application.domain.*
import org.springframework.stereotype.Component

// Payment history could be sealed class with different types, history of events or othet
interface Projection {
    fun createFrom(paymentHistory: PaymentHistory): PaymentResponse
}

@Component
class PaymentProjection : Projection {

    lateinit var id: String
    lateinit var description: String
    lateinit var state: Payment.State

    private fun on(event: PaymentCreatedEvent) {
        id = event.paymentId
        state = Payment.State.CREATED
        description = event.description
    }

    private fun on(event: PaymentInitializedEvent) {
        state = Payment.State.INITIALIZED
    }

    private fun on(event: PaymentCompletedEvent) {
        state = Payment.State.COMPLETED
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
            Payment.State.CREATED, Payment.State.INITIALIZED, Payment.State.COMPLETED ->
                PaymentResponse(
                    status = "SUCCESS",
                    paymentId = id,
                    parameters = mapOf("description" to description)
                )
            Payment.State.COMPLETED_UNKNOWN ->
                PaymentResponse(
                    status = "UNKNOWN",
                    paymentId = id
                )
            Payment.State.INITIALIZE_FAILED, Payment.State.COMPLETED_FAILED ->
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
