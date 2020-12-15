package io.aws.lambda.runtime.handler.impl;

import io.aws.lambda.runtime.Lambda;
import io.aws.lambda.runtime.convert.Converter;
import io.aws.lambda.runtime.logger.LambdaLogger;
import io.aws.lambda.runtime.model.AwsGatewayRequest;
import io.aws.lambda.runtime.model.AwsGatewayResponse;
import io.aws.lambda.runtime.model.AwsRequestContext;
import io.aws.lambda.runtime.utils.TimeUtils;
import io.micronaut.core.annotation.Introspected;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * AWS Lambda Gateway Handler for handling requests coming from AWS Gateway
 *
 * @author Anton Kurako (GoodforGod)
 * @since 7.11.2020
 */
@Singleton
@Introspected
public class AwsGatewayEventHandler extends AwsEventHandler {

    @Inject
    public AwsGatewayEventHandler(Lambda function, Converter converter, LambdaLogger logger) {
        super(function, converter, logger);
    }

    @Override
    public String handle(@NotNull String event, @NotNull AwsRequestContext context) {
        logger.debug("Gateway Request Event conversion started...");
        final long requestStart = TimeUtils.getTime();
        final AwsGatewayRequest requestEvent = converter.convertToType(event, AwsGatewayRequest.class)
                .setContext(context);
        logger.debug("Gateway Request Event conversion took: %s", TimeUtils.timeSpent(requestStart));

        final Object functionOutput = super.handle(requestEvent.getBody(), context);

        logger.debug("Gateway Response Event conversion started...");
        final long outputStart = TimeUtils.getTime();
        final AwsGatewayResponse responseEvent = getFunctionResponseEvent(functionOutput);
        logger.debug("Gateway Response Event conversion took: %s", TimeUtils.timeSpent(outputStart));
        logger.debug("Gateway Response Event body: %s", responseEvent.getBody());
        return converter.convertToJson(responseEvent);
    }

    private @NotNull AwsGatewayResponse getFunctionResponseEvent(Object functionOutput) {
        if (functionOutput == null)
            return new AwsGatewayResponse();

        if (functionOutput instanceof String)
            return new AwsGatewayResponse().setBody((String) functionOutput);

        if (functionOutput instanceof AwsGatewayResponse)
            return (AwsGatewayResponse) functionOutput;

        final String json = converter.convertToJson(functionOutput);
        return new AwsGatewayResponse().setBody(json);
    }
}
