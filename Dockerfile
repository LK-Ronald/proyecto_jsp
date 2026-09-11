# ==============================================================================
# Etapa 1: Compilación de la aplicación con Maven y OpenJDK 17
# ==============================================================================
FROM maven:3.9-eclipse-temurin-17 AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml para descargar dependencias primero (aprovecha la caché de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar el archivo WAR omitiendo las pruebas unitarias
COPY src ./src
RUN mvn clean package -DskipTests

# ==============================================================================
# Etapa 2: Ejecución de la aplicación con Apache Tomcat 10.1
# ==============================================================================
FROM tomcat:10.1-jdk17-temurin

# Establecer el directorio base de Tomcat
WORKDIR /usr/local/tomcat

# Limpiar las aplicaciones predeterminadas que vienen en Tomcat (ROOT, docs, examples, etc.)
RUN rm -rf webapps/*

# Copiar el archivo WAR generado en la etapa de compilación y desplegarlo como ROOT.war
COPY --from=build /app/target/*.war webapps/ROOT.war

# Definir la variable de entorno del puerto (Render provee la variable $PORT)
ENV PORT=8080

# Informar que el contenedor escuchará en el puerto 8080 (por defecto)
EXPOSE 8080

# Ajustar el puerto de escucha en conf/server.xml según la variable $PORT y arrancar Tomcat
CMD ["sh", "-c", "sed -i \"s/port=\\\"8080\\\"/port=\\\"${PORT}\\\"/g\" conf/server.xml && catalina.sh run"]
