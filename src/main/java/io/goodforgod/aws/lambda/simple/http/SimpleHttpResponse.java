package io.goodforgod.aws.lambda.simple.http;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 7.11.2020
 */
public interface SimpleHttpResponse {

    /**
     * @return http response status code
     */
    int statusCode();

    /**
     * @return body as {@link InputStream}
     */
    @NotNull
    InputStream body();

    /**
     * @return body as String
     */
    @NotNull
    String bodyAsString();

    /**
     * @return http header multi map
     */
    @NotNull
    Map<String, List<String>> headersMultiValues();

    /**
     * @return http header flat map
     */
    @NotNull
    Map<String, String> headers();

    /**
     * @param name header name
     * @return header value or {@link Optional#empty()}
     */
    Optional<String> headerFirst(@NotNull String name);
}