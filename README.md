# SIP Adapter Examples

This repository contains examples of SIP Adapters, 
which demonstrate main features of the SIP Framework and how to use them.

## Adapters
[Configuration Handlers Demo](configuration-handlers/README.md) - Adapter which demonstrates how to add and use configuration handlers
(exception handlers, intercepts).

[Connector Orchestration Demo](connector-orchestration/README.md) - Adapter which demonstrates how to add and use processors which transform/handle
request or response inside connectors and how to order them.

[Inbound Service Security Demo](inbound-service-security/README.md) - Adapter which shows how to add spring security to an adapter.
Secured with OIDC (keycloak).

[Outbound Service Security Demo](outbound-service-security/README.md) - Adapter which demonstrates how to fetch a token from a secured server
and later use it in a request to an external service.

