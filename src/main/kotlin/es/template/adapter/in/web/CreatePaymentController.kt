package es.template.adapter.`in`.web

import es.template.application.payment.port.`in`.CreateOneOffPaymentUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/command")
class CreatePaymentController(
    private val createOneOffPaymentUseCase: CreateOneOffPaymentUseCase,
    private val projection: Projection
) {

    @PostMapping("/create-payment/v1")
    fun create(@RequestBody request: CreateRequest): ResponseEntity<PaymentResponse> {
        val createCommand = request.toCommand()
        val paymentHistory = createOneOffPaymentUseCase.createPayment(createCommand)

        // we should have more granulary endpoints, to avoid situation where we need to check or retrieve inforamtion from db
        // and rehydrate events, we could think about read model, generated from domain events, or stored in use cases
        // buissnes validation in services

        // use specific implementation of interface here, each controller could use different projection
        // projection should be result and we could call it PaymentResultProjection or something
//        val result = paymentGetPaymentStateUseCase.getPaymentResult(createCommand.paymentId)
        return ResponseEntity.ok(projection.createFrom(paymentHistory))
    }
}