# Ocean Scope

Ocean Scope is a real-time ship monitoring web application. It displays live ship positions on a map and updates them dynamically using WebSocket events.

Note: All ship data is simulated for demonstration purposes. Positions are randomly updated in the backend to mimic real-time movement.

Current stage: POC.

## Technical stack

- Java 21
- Spring Boot 3.x
- Hibernate
- H2 database
- Vue.js 3

## Further steps
1. Shedlocks if running backend in multiple replicas.
2. Deployment (k8s, helm).
3. Ship metrics simulation might be moved to a separate microservice so that the ocean-scope-api service is listening to ship movements via kafka events.
4. Define UI to register new ships (maybe a separate route accessible to admins only).
5. H2 was chosen for simplicity, but in a production environment, it should be replaced with a persistent database.
6. For MVP, we should consider radars to be configured so that we don't load all existing ships to the user's browser (load testing + optimizations to support huge data sets as the project grows).
