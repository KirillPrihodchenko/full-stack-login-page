FROM openjdk:19
COPY target/full-stack-login-page-0.0.1-SNAPSHOT.jar full-stack-login-page.jar
ENTRYPOINT ["java", "-jar", "full-stack-login-page.jar"]