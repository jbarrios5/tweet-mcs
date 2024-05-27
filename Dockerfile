FROM openjdk:11
COPY target/mcs.auth.jar mcs.tweet-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","mcs-tweet.jar"]
EXPOSE 8080