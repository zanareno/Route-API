FROM openjdk:8-jre-alpine

ADD /demo-services/target/app.jar /home/route-app/app.jar

WORKDIR /home/route-app/

ENTRYPOINT [ "/bin/sh", "-c", "java $SERVICE_JVM_ARGS -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom -jar /home/route-app/app.jar" ]
