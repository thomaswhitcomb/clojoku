FROM java:8-alpine
RUN mkdir -p /app /app/resources
WORKDIR /app
COPY target/uberjar/*-standalone.jar .
CMD java -jar clojoku-0.1.0-standalone.jar 3000
EXPOSE 3000
