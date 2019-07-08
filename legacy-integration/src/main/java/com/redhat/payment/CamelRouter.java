package com.redhat.payment;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // @formatter:off

        from("jms:queue:DEV.QUEUE.1")
            .log("${body}")
            //TODO transformation here
            .setBody(constant("Hello, IBM MQ!"))
            .to("jms:queue:DEV.QUEUE.2");

        // @formatter:on
    }

}