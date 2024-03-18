package es.template.adapter.`in`.web

import es.template.application.payment.port.`in`.CompleteOneOffPaymentUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/command")
class CompletePaymentController(
    private val completeOneOffPaymentUseCase: CompleteOneOffPaymentUseCase,
    private val projection: Projection
) {

    @PostMapping("/complete-payment/v1")
    fun complete(@RequestBody request: CompleteRequest): ResponseEntity<PaymentResponse> {
        val paymentHistory = completeOneOffPaymentUseCase.completePayment(request.toCommand())
        return ResponseEntity.ok(projection.createFrom(paymentHistory))
    }
}