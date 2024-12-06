FROM oraclelinux:9
EXPOSE 8080/tcp
RUN mkdir "/app"
COPY target/app-petclinic /app/app-petclinic
RUN chmod +x /app/app-petclinic
WORKDIR "/app"
ENTRYPOINT ["top", "-b"]
