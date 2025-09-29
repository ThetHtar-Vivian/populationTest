FROM openjdk:17
COPY ./target/populationTest-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "populationTest-0.1.0.1-jar-with-dependencies.jar"]