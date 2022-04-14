package io.goodforgod.aws.lambda.simple;

import io.goodforgod.aws.lambda.simple.handler.impl.BodyEventHandler;
import io.goodforgod.aws.lambda.simple.runtime.RuntimeContext;
import io.goodforgod.aws.lambda.simple.runtime.SimpleRuntimeContext;
import java.util.function.Consumer;

/**
 * Abstract Simple Lambda Entrypoint for {@link BodyEventHandler}
 *
 * @author Anton Kurako (GoodforGod)
 * @since 09.03.2022
 */
public abstract class AbstractBodyLambdaEntrypoint extends AbstractLambdaEntrypoint {

    /**
     * @return consumer to setup context in runtime
     */
    protected Consumer<SimpleRuntimeContext> setupInRuntime() {
        return context -> {};
    }

    /**
     * @return consumer to setup context in compile time
     */
    protected Consumer<SimpleRuntimeContext> setupInCompileTime() {
        return context -> {};
    }

    @Override
    public RuntimeContext initializeRuntimeContext() {
        return new SimpleRuntimeContext(setupInRuntime(), setupInCompileTime());
    }

    @Override
    public String getEventHandlerQualifier() {
        return BodyEventHandler.QUALIFIER;
    }
}
