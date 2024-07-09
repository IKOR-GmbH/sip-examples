package de.ikor.sip.adapter.config.config.intercept;

import de.ikor.sip.foundation.core.declarative.configuration.ConfigurationDefinition;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.model.OutputDefinition;
import org.apache.camel.model.RouteConfigurationDefinition;
import org.springframework.context.annotation.Configuration;

/**
 * Implementation of {@link ConfigurationDefinition} which is used to define
 * interceptSendToEndpoint define which intercepts all log endpoints
 */
@Configuration
@Slf4j
public class InterceptEndpointConfiguration implements ConfigurationDefinition {

    @Override
    public OutputDefinition define(RouteConfigurationDefinition routeConfigurationDefinition) {
        return routeConfigurationDefinition
                .interceptSendToEndpoint("log*")
                .process(exchange -> {
                    log.info("Finalised message");
                    exchange.getMessage().setBody("Intercepted");
                });
    }
}