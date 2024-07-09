# Inbound Service Security Demo

## Description

This adapter demonstrates how Spring Security can be implemented.
It was built using sip-archetype. Spring Security was added to it.
To access an endpoint exposed by this adapter a JWT token is required, provided by a keycloak server.
All configuration is done in **AdapterSecurityConfig**.

## Usage

1. Set up a test keycloak server. This can be done using the following guide or in another preferred way.
   https://www.keycloak.org/getting-started/getting-started-docker

2. Make sure to apply values to following configuration properties before running the adapter:
```yaml
ISSUER_URI: # URI of keycloak issuer realm
```

3. Fetch the token from test keycloak server

4. Attach the token to the request and send it to `GET /adapter/nobelprize/{category}/{year}`.

    - if the request was successful and token was valid the Nobel Prize data will be the response
    - if the token was invalid status *Unauthorized* will be returned