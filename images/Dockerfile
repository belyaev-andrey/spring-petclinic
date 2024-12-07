FROM oraclelinux:9-slim
EXPOSE 8080/tcp
RUN mkdir -p "/app"
COPY target/app-petclinic /app/app-petclinic
RUN chmod +x /app/app-petclinic
WORKDIR "/app"
ENTRYPOINT ["./app-petclinic"]
