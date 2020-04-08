# quarkus-birthdays project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Start Postgres db with docker
- docker run --rm --name postgres-docker -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -d -p 5432:5432 -v /var/lib/docker_volumes/postgressql/data:/var/lib/postgresql/data postgres
- docker exec -it postgres-docker psql -U root

## Testing
#### Insert record
- curl --header "Content-Type: application/json" --request POST --data '{"name":"peer","description":"conference","barcode":"3456"}' http://localhost:8080/grocery/item/edit


## Packaging and running the application

The application is packageable using `./mvnw package`.
It produces the executable `quarkus-birthdays-1.0.0-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/quarkus-birthdays-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or you can use Docker to build the native executable using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your binary: `./target/quarkus-birthdays-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide .