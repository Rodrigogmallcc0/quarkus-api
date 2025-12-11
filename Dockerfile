# =======================
# 1) Build stage
# =======================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml .
RUN mvn -q -e -DskipTests package || true

COPY src src
RUN mvn -q -DskipTests package

# =======================
# 2) Runner stage
# =======================
FROM registry.access.redhat.com/ubi9/openjdk-17:1.23

WORKDIR /deployments

COPY --from=build /workspace/target/quarkus-app/lib/ ./lib/
COPY --from=build /workspace/target/quarkus-app/*.jar ./
COPY --from=build /workspace/target/quarkus-app/app/ ./app/
COPY --from=build /workspace/target/quarkus-app/quarkus/ ./quarkus/

EXPOSE 8080
CMD ["java", "-jar", "/deployments/quarkus-run.jar"]
