# Usa una imagen base con OpenJDK 17
FROM openjdk:17-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR desde la máquina local al contenedor
COPY target/API2.jar /app/app.jar

# Expón el puerto 4567 (puerto predeterminado de Spark)
EXPOSE 4567

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]