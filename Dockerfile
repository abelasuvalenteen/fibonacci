FROM alpine/git as clone
WORKDIR /app
RUN git clone

FROM maven:3.5-jdk-8-alpine as build
ARG project (3)
WORKDIR /app
COPY --from=clone /app/${project} /app
RUN mvn install

FROM openjdk:8-jre-alpine
ARG artifactid
ARG version
ENV artifact ${artifactid}-${version}.jar (4)
WORKDIR /app
COPY --from=build /app/target/${artifact} /app
EXPOSE 8080
CMD ["java -jar ${artifact}"] (5)