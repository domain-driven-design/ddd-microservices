# ddd-microservices
Example project for microservices with DDD.

# Modules

- commons: SDK for common utils or base classes.
- bff: Application gateway.
- demo: Domain service for **example** and team **specification**. A set of CQRUD/Tests examples which team should follow up and could be template as auto script. 
- base: Some basic functions, e.g. Schedule\User\Branch.
- calculation: A lib for calculation features.

# Specification

## App project Layers

Layers:

- adapter: Present input/output for Rest API and MQ listener.
- application: Deal business use case, should define DTOs.
- domain: Common business case features, optional for business.
- infrastructure: APIs\Database access\MQ produce.
  - Data access: Repository(Aggregate) → Mybatis Mapper(Sigle table)
  - API access: Client(Feign provide, if we use RestTemplate, should implement one Client in project)
  - MQ produce: Producer → MQTemplate.
  
Q: PO(alone) and Model(1 to many)?
A: It dependes ORM selection. JPA can treat them same, but for Mybatis keep both PO and Model could be better.

Q: do we really need IOC for domain? and keep domain clean? (Some interfaces keep in domain but all in one project).
A: It's an open problem, currently we chose [TBD]. 

## Package and naming 
  - Package name should start with "com.example.[module]"
  - @Ning
## Exception design @Zebing
  - 
## Testing
  - @Wanglu 


# troubleshooting

## libssl.1.0.0.dylib' (no such file) problem 

> brew install rbenv/tap/openssl@1.0

> ln -sfn /usr/local/Cellar/openssl@1.0/1.0.2u /usr/local/opt/openssl

Hints: 1.0.2u could be different, put your right version.

https://stackoverflow.com/questions/59155991/downgrade-to-openssl-version-1-0-from-1-1-on-mac
https://stackoverflow.com/questions/59006602/dyld-library-not-loaded-usr-local-opt-openssl-lib-libssl-1-0-0-dylib
