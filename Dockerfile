FROM maven:3.8.1-openjdk-17-slim as build-image
WORKDIR "/task"
COPY src src/
COPY pom.xml ./
RUN mvn -q clean package

FROM public.ecr.aws/docker/library/amazoncorretto:17.0.7
COPY --from=public.ecr.aws/awsguru/aws-lambda-adapter:0.7.0 /lambda-adapter /opt/extensions/lambda-adapter
EXPOSE 8080
WORKDIR /opt
COPY --from=build-image /task/target/covapi-0.0.1-SNAPSHOT.jar /opt
#COPY /target/covapi-0.0.1-SNAPSHOT.jar /opt
CMD ["java", "-jar", "covapi-0.0.1-SNAPSHOT.jar"]