package es.template.adapter.`in`.web

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import es.template.application.payment.port.`in`.CompleteOneOffPaymentUseCase
import es.template.application.payment.port.`in`.CreateOneOffPaymentUseCase
import es.template.application.payment.port.`in`.InitializeOneOffPaymentUseCase
import java.util.*

// Validation could be added here, or in specific resource
@JsonIgnoreProperties(ignoreUnknown = true)
class CreateRequest {
    var description: String? = null

    fun toCommand() =
        CreateOneOffPaymentUseCase.CreatePaymentCommand(paymentId = UUID.randomUUID().toString(), description = description!!)
}

@JsonIgnoreProperties(ignoreUnknown = true)
class InitializeRequest {
    var paymentId: String? = null

    fun toCommand() = InitializeOneOffPaymentUseCase.InitializePaymentCommand(id = paymentId!!)
}

@JsonIgnoreProperties(ignoreUnknown = true)
class CompleteRequest {
    var paymentId: String? = null

    fun toCommand() = CompleteOneOffPaymentUseCase.CompletePaymentCommand(id = paymentId!!)
}

data class PaymentResponse(
    val status: String,
    val paymentId: String? = null,
    val parameters: Map<String, Any> = emptyMap(),
    val errorContext: Map<String, Any> = emptyMap()
)
