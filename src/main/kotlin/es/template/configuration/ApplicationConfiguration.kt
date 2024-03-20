package es.template.configuration

import es.template.adapter.`in`.web.PaymentQueryProjection
import es.template.adapter.`in`.web.Projection
import es.template.adapter.out.persistance.*
import es.template.adapter.out.psp.TestPaymentProviderService
import es.template.application.payment.domain.PaymentFactory
import es.template.application.payment.port.`in`.CompleteOneOffPaymentUseCase
import es.template.application.payment.port.`in`.CreateOneOffPaymentUseCase
import es.template.application.payment.port.`in`.InitializeOneOffPaymentUseCase
import es.template.application.payment.port.out.PaymentExternalService
import es.template.application.payment.port.out.PaymentRepository
import es.template.application.payment.services.CompleteOneOffPaymentService
import es.template.application.payment.services.CreateOneOffPaymentService
import es.template.application.payment.services.InitializeOneOffOneOffPaymentService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

    @Bean
    fun paymentQueryProjection(): Projection {
        return PaymentQueryProjection()
    }

    @Bean
    fun paymentProjection(): PaymentProjection {
        return DbBasedPaymentProjection()
    }

    @Bean
    fun paymentIntegrationEvents(): PaymentIntegrationEvents {
        return DbBasedPaymentIntegrationEvents()
    }

    @Bean
    fun paymentExternalService(): PaymentExternalService {
        return TestPaymentProviderService()
    }

    @Bean
    fun paymentFactory(paymentExternalService: PaymentExternalService): PaymentFactory {
        return PaymentFactory(paymentExternalService)
    }

    @Bean
    fun eventStore(): EventStore {
        return InMemoryBasedEventStore()
    }

    @Bean
    fun paymentRepository(
            paymentFactory: PaymentFactory,
            eventStore: EventStore,
            paymentProjection: PaymentProjection,
            paymentIntegrationEvents: PaymentIntegrationEvents): PaymentRepository {
        return MemoryBasedPaymentRepository(paymentFactory, eventStore, paymentProjection, paymentIntegrationEvents)
    }

    @Bean
    fun createOneOffPaymentUseCase(
            paymentRepository: PaymentRepository,
            paymentFactory: PaymentFactory): CreateOneOffPaymentUseCase {
        return CreateOneOffPaymentService(paymentRepository, paymentFactory)
    }

    @Bean
    fun initializeOneOffPaymentUseCase(paymentRepository: PaymentRepository): InitializeOneOffPaymentUseCase {
        return InitializeOneOffOneOffPaymentService(paymentRepository)
    }

    @Bean
    fun completeOneOffPaymentUseCase(paymentRepository: PaymentRepository): CompleteOneOffPaymentUseCase {
        return CompleteOneOffPaymentService(paymentRepository)
    }
}