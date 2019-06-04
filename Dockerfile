FROM maven:alpine AS build
ADD src /usr/src/app/src
ADD pom.xml /usr/src/app/
RUN mvn -f /usr/src/app/pom.xml clean package -Dmaven.test.skip=true

FROM etiennek/spring-boot
USER root
RUN mkdir /sepomex-indices/
RUN chown -R bootapp /sepomex-indices/
RUN chmod 744 /sepomex-indices/
USER bootapp
COPY --from=build /usr/src/app/target/app.jar /
EXPOSE 8007