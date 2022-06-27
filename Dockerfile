FROM  adoptopenjdk/openjdk11

COPY  *.jar /app.jar

CMD ["--server.port=9090"]

EXPOSE 9090

ENTRYPOINT ["java","-jar","/app.jar"]
