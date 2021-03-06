FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/vote-of-confidence/meeting.git

FROM maven:3.6-openjdk-11 AS maven
COPY --from=clone /app/meeting/ /tmp/
WORKDIR /tmp/
RUN mvn clean package -q
RUN find /tmp/target/ -name '*jar' -exec bash -c 'mv $0 app.jar' {} \;
ENTRYPOINT /tmp

FROM openjdk:11-slim
VOLUME /tmp
COPY --from=maven /tmp/app.jar app.jar
ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions", "-XX:+UseJVMCICompiler","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8000