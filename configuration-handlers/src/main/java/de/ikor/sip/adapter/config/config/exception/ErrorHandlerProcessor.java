package de.ikor.sip.adapter.config.config.exception;

import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.util.ClassUtils;

/**
 * Default processor for exception handling
 */
@RequiredArgsConstructor
public class ErrorHandlerProcessor implements Processor {

    private final Class<?> source;
    @Override
    public void process(Exchange exchange) throws Exception {
        Exception caught = exchange
                .getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        exchange.getMessage().setBody(
                String.format("Exception %s was handled by %s",
                        caught.getClass().getSimpleName(),
                        ClassUtils.getShortName(source)));
        exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
    }
}
