FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x scripts/*.sh

CMD ["./scripts/docker-hot-reload.sh"]
