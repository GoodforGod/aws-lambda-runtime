package io.aws.lambda.simple.runtime;

import io.aws.lambda.events.gateway.APIGatewayV2HTTPEvent;
import io.aws.lambda.simple.runtime.convert.Converter;
import io.aws.lambda.simple.runtime.handler.EventHandler;
import io.aws.lambda.simple.runtime.handler.impl.BodyEventHandler;
import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * @author GoodforGod
 * @since 27.10.2020
 */
class BodyEventHandlerTests extends Assertions {

    @Test
    void handled() {
        try (final ApplicationContext context = ApplicationContext.run()) {
            final EventHandler handler = context.getBean(BodyEventHandler.class);
            final Converter converter = context.getBean(Converter.class);

            final String body = "{\"name\":\"Steeven King\"}";
            final APIGatewayV2HTTPEvent requestEvent = new APIGatewayV2HTTPEvent().setBody(body);
            final String json = converter.convertToJson(requestEvent);

            final String response = handler.handle(json, LambdaContext.ofHeaders(Collections.emptyMap()));
            assertNotNull(response);
            assertTrue(response.contains("Hello - Steeven King"));
        }
    }
}