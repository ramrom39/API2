# Usa una imagen base con OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR desde la máquina local al contenedor
COPY out/artifacts/API2_jar/API2.jar .

# Expón el puerto 4567 (puerto predeterminado de Spark)
EXPOSE 4567

# Comando para ejecutar la aplicación
CMD ["sh", "-c", "java -jar API2.jar --server.port=${PORT:-4567}"]