FROM openjdk:17-alpine
COPY target/kafka-producer-consumer-metrics-0.0.1-SNAPSHOT.jar /evil/kafka-producer-consumer-metrics.jar
RUN addgroup -S -g 2023 evil && \
    adduser -S -g evil -u 2023 evil
RUN mkdir -p /evil/
RUN mkdir -p /evil/logs
RUN chown -R evil:evil /evil
RUN find /evil/ -type f -exec chmod 644 {} \; && chmod 775 /evil/logs
USER evil:evil
ENV KAFKA_BOOTSTRAP_SERVERS=broker:29092
EXPOSE 8080
EXPOSE 8081
EXPOSE 9010
WORKDIR /evil
ENTRYPOINT [ "java",\
"-jar",\
"./kafka-producer-consumer-metrics.jar"]
#CMD ["--spring.profiles.active=docker"]
