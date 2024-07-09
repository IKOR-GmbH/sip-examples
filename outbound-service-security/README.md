# Outbound Service Security Demo

## Description

This adapter demonstrates how to implement keycloak (OIDC) flow to fetch a JWT token
and later use it in requests sent from the adapter.
*FetchTokenScenario* prepares a request and gets a token from a keycloak server.
The scenario is invoked via process orchestration.

***Note:** The adapter is designed to work in combination with 
[Inbound Service Security Demo](../inbound-service-security/README.md).*

## Scenarios

**GetNobelDataScenario** - This scenario consists of an inbound connector which exposes 
`GET /adapter/nobelprize/{category}/{year}` endpoint.
It is the starting point of the request.

**CallSecuredServiceScenario** - Here the outbound connector which calls secured server is found.

**FetchTokenScenario** - This integration scenario is the main focus of this adapter.
The scenario is used to prepare a request to a keycloak server and retrieve a token from it.

## Flow

Via the *FetchTokenProcess* the three scenarios are orchestrated.
When a request is sent to the adapter, the first step is to get the token from the keycloak server.
After the token is successfully fetched, it will be set as header and used in the request to the secured server.
Finally, the response from the secured server will be the response of the original request.

## Usage

Before running the adapter a few steps should be taken.

1. Set up a test keycloak server. This can be done using the following guide or in another preferred way.
   https://www.keycloak.org/getting-started/getting-started-docker

2. Make sure to apply values to following configuration properties:
```yaml
KEYCLOAK_USERNAME: # username
KEYCLOAK_CLIENT_ID: # id of keycloak client
KEYCLOAK_PASSWORD: # user password
KEYCLOAK_GRANT_TYPE: # grant type of keycloak issuer
KEYCLOAK_ISSUER_URI: # URI of keycloak issuer
EXTERNAL_URI: # URI of secured server called by the adapter
```

3. Run Secured Demo Adapter from sip-examples which may act as the secured server called by the adapter.

4. To test the flow the following REST request can be sent: `GET /adapter/nobelprize/{category}/{year}`

5. If successful, response will match the one from Secured Demo Adapter