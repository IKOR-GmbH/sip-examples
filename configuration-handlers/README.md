# Configuration Handlers Demo

This adapter demonstrates how to add configuration and exception handlers 
to different components and levels of SIP Framework.

## Configuration

Configuration handlers are added to two SIP components, scenarios and connectors.

There are 3 Configuration Defined in the adapter.
(Names are just for demonstration purposes)

1. ScenarioLevelErrorHandler - placed on the integration scenario, 
containing onException definition for RuntimeException and UnknownHostException
2. ConnectorLevelErrorHandler - placed on all connectors found in the adapter, 
containing onException definition for IllegalArgumentException
3. InterceptEndpointConfiguration - placed on the integration scenario, 
containing interceptSendToEndpoint definition

**OnException**
In case of exception handlers only one will be triggered. The priority is based on the Exception it handles,
starting with the most concrete one. 
Priority is not affected by the component they are placed on or the order in which they are defined in annotation.

## Connector Exception Handler

This handler overrides onException handler in general configuration if they handle the same exception type.
This is demonstrated in ConcreteHandlerOutConnector where IllegalArgumentException is handled 
and will take priority over ConnectorLevelErrorHandler.

## Usage

Each inbound connector exposes one REST GET endpoint which shows the effects of different handlers.

- `/adapter/success` will not produce an error, but it will invoke InterceptEndpointConfiguration
- `/adapter/exception/runtime` will produce a UnknownHostException which will be handled by ScenarioLevelErrorHandler
- `/adapter/exception/generic` will produce a IllegalArgumentException which will be handled by ConnectorLevelErrorHandler
- `/adapter/exception/concrete` will produce a IllegalArgumentException which will be handled inside the connector itself 
(ConcreteHandlerOutConnector)

