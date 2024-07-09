package de.ikor.sip.adapter.config.config.exception;

import de.ikor.sip.foundation.core.declarative.configuration.ConfigurationDefinition;
import org.apache.camel.model.OutputDefinition;
import org.apache.camel.model.RouteConfigurationDefinition;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 * Implementation of {@link ConfigurationDefinition} which is used to handle
 * {@link RuntimeException} and {@link UnknownHostException}
 */
@Configuration
public class ScenarioLevelErrorHandler implements ConfigurationDefinition {

    @Override
    public OutputDefinition define(RouteConfigurationDefinition routeConfigurationDefinition) {
        return routeConfigurationDefinition
                .onException(RuntimeException.class, UnknownHostException.class)
                .process(new ErrorHandlerProcessor(this.getClass()))
                .handled(true);
    }
}