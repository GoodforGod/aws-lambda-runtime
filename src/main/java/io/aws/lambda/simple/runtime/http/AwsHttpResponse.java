package io.aws.lambda.simple.runtime.http;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 7.11.2020
 */
public interface AwsHttpResponse {

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
     * @return body as {@link java.nio.charset.StandardCharsets#UTF_8} String
     */
    @NotNull
    String bodyAsString();

    /**
     * @return http header multi map
     */
    @NotNull
    Map<String, List<String>> headers();

    /**
     * @return http header flat map
     */
    @NotNull
    Map<String, String> headerFirstValues();

    /**
     * @param name header name
     * @return header value or {@link Optional#empty()}
     */
    Optional<String> headerFirst(@NotNull String name);
}
