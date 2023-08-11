FROM openjdk:17 as build

WORKDIR /src

ADD . .

RUN chmod +x gradlew

RUN ./gradlew build

FROM openjdk:17 as user-auth-micro-service

EXPOSE 8081

COPY --from=0 /src/UserAuthenticationMicroService/build/libs/UserAuthenticationMicroService-v.0.1.jar .

ENTRYPOINT ["java","-jar","UserAuthenticationMicroService-v.0.1.jar"]

FROM openjdk:17 as vehicle-micro-service

EXPOSE 8082

COPY --from=0 /src/VehicleMicroService/build/libs/VehicleMicroService-v.0.1.jar .

ENTRYPOINT ["java","-jar","VehicleMicroService-v.0.1.jar"]
