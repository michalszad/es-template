package es.template.adapter.`in`.web

import es.template.application.port.`in`.InitializePaymentUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/command")
class InitializePaymentController(
    private val initializePaymentUseCase: InitializePaymentUseCase,
    private val projection: Projection
) {

    @PostMapping("/initialize-payment/v1")
    fun initialize(@RequestBody request: InitializeRequest): ResponseEntity<PaymentResponse> {
        val paymentHistory = initializePaymentUseCase.initializePayment(request.toCommand())
        return ResponseEntity.ok(projection.createFrom(paymentHistory))
    }
}