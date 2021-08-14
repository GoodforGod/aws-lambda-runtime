package io.aws.lambda.simple.runtime.context;

import org.jetbrains.annotations.NotNull;

/**
 * Runtime Context interface so whole code can be used with other DI framework
 * or even with some plain java written realisation
 *
 * @author Anton Kurako (GoodforGod)
 * @since 1.2.2021
 */
public interface RuntimeContext extends AutoCloseable {

    /**
     * @param beanType class or interface of bean to look for
     * @param <T>      type of bean to instantiate
     * @return return bean instance
     */
    <T> T getBean(@NotNull Class<T> beanType);
}