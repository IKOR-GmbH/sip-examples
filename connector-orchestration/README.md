# Connector Orchestration Demo


## Description
This adapter is designed to demonstrate how to order and use 
different response and request processors inside connectors.

- **DemoInboundConnector** - contains examples of path/query parameter mapping when using RestInboundConnectorBase,
as well as a response processor

- **DemoOutboundConnector** - contains examples of request processors and different possible method signatures 
and how they can be ordered. Moreover, *ExternalProcessor* is also bound to it as another possible implementation.


## Usage

To invoke a request send a request to `POST /adapter/demo/{pathValue}?query={queryValue}`.

The *Content-Type* should be **plain/text** and request body a simple message.
Both *pathValue* and *queryValue* are mandatory for demonstration purposes.
The final response will be a string message showing the input request body with appended messages from each processor,
demonstrating in which order they were executed.

