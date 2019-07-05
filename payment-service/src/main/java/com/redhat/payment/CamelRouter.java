package com.redhat.payment;

import com.redhat.payment.greetings.Greetings;
import com.redhat.payment.service.Payment;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * A simple Camel REST DSL route that implements the greetings service.
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // @formatter:off
        restConfiguration()
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Payment REST API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiProperty("base.path", "camel/")
                .apiProperty("api.path", "/")
                .apiProperty("host", "")
                .apiContextRouteId("doc-api")
            .component("servlet")
            .bindingMode(RestBindingMode.json);
        
        rest("/payment").description("Payment submission")
            .post()
                .type(Payment.class)
            .route().routeId("payment-api")
            .to("direct:paymentImpl");

        from("direct:paymentImpl").description("Payment REST service implementation route")
            .streamCaching()
            .log("${body}")
            // TODO transformation
            .setHeader(KafkaConstants.KEY, constant("payment"))
            .to("kafka:payment?brokers={{kafka.broker}}");

        rest("/greetings").description("Greeting to {name}")
            .get("/{name}").outType(Greetings.class)
                .route().routeId("greeting-api")
                .to("direct:greetingsImpl");

        from("direct:greetingsImpl").description("Greetings REST service implementation route")
            .streamCaching()
            .to("bean:greetingsService?method=getGreetings");
        // @formatter:on
    }

}