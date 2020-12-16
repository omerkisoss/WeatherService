Instructions for consuming apis:

GET REQUEST:
```
/api/devices/{deviceId}
```

where deviceId shall be the id of the device the Employee wants to check weather on.

for example:
```
http://localhost:8080/api/devices/USA-027
```

prerequisites:
* Docker
* docker-compose installed

in order to run all services run command:
```
mvn clean install -DskipTests
```

```
docker-compose up -d
```

