# SERB-Backend

## Changes log
* normal servlet web project -> spring boot project
* adding the random objects generator by [@Abdo](https://github.com/abdo1819) ,[here](https://github.com/A-Siam/SERB-backend/pull/1)


## database configuration

###  install ojdbc driver for maven
>`mvn install:install-file -DgroupId=oracle -DartifactId=ojdbc6 -Dpackaging=jar -Dfile=lib/ojdbc6.jar -DgeneratePom=true -Dversion=10.2.0.3.0 `

###  configure conection variables at [application.properties](src/main/resources/application.properties)

```
database.jdbc-url=<url>
database.username=<username>
database.password=<password>
database.hikari.cores=<n_cores>
```
* those are temporary configration for hikari DATASOURCE [here](src/main/java/com/serb_backend/dal/util/ConnectionProviderWithPoolImpl.java)

as official configuration had problem in driver


### 3. make sure database is up and running 
follow instruction at [serb-db](https://github.com/serb-book/serb-db)

## run spring app
> ` mvn spring-boot:run`
* currently there are no controller this will only check connection with database
* can be used to fill data