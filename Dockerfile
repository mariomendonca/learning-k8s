# Use uma imagem base com Java 21
FROM eclipse-temurin:21-jdk-alpine

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado pelo Maven para dentro da imagem
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
