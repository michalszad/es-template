package es.template.adapter.`in`.web

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import es.template.application.port.`in`.CompletePaymentUseCase
import es.template.application.port.`in`.CreatePaymentUseCase
import es.template.application.port.`in`.InitializePaymentUseCase
import java.util.*

// Validation could be added here, or in specific resource
@JsonIgnoreProperties(ignoreUnknown = true)
class CreateRequest {
    var description: String? = null

    fun toCommand() =
        CreatePaymentUseCase.CreatePaymentCommand(paymentId = UUID.randomUUID().toString(), description = description!!)
}

@JsonIgnoreProperties(ignoreUnknown = true)
class InitializeRequest {
    var paymentId: String? = null

    fun toCommand() = InitializePaymentUseCase.InitializePaymentCommand(id = paymentId!!)
}

@JsonIgnoreProperties(ignoreUnknown = true)
class CompleteRequest {
    var paymentId: String? = null

    fun toCommand() = CompletePaymentUseCase.CompletePaymentCommand(id = paymentId!!)
}

data class PaymentResponse(
    val status: String,
    val paymentId: String? = null,
    val parameters: Map<String, Any> = emptyMap(),
    val errorContext: Map<String, Any> = emptyMap()
)
