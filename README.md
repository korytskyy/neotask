# NEO task

## Assumptions

- Generated token
  - Should have 12 url friendly characters 
  - Isn't supposed to be user friendly (easily readable or dictated)
  - Expired token is available for further usage
- Every generateToken service call should generate new not used token even for the same url
- Authentication/authorizaton is out of scope
- Traffic encryption is out of scope
- Production "readiness" (deployment, scalability etc.) is out of scope
- Input data validation is out of scope (for simplification)
- No url validation (could be in Url.of(String) and somewhere around RequestHandler.generateToken)
- No object caching (could be in factory methods of classes Token, Url etc.)
- No separated serialization/deserialization of input parameters and answers
- Server returns only OK(200) or InternalServerError(500) status 

## Design desicion

- Nonblocking service with Spring Webflux
- Apache Cassandra as NoSQL db (mostly because of out of the box TTL support) with reactive spring-data-cassandra
- Random 4 byte token generation and encoding by Base64 for URL (in case of collision)

