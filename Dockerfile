# ---- Stage 1: Build WAR bằng Maven ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Cache dependency
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline

# Copy source và build
COPY src ./src
RUN mvn -B -DskipTests clean package

# ---- Stage 2: Chạy với Tomcat 11 (JDK 21) ----
FROM tomcat:11.0-jdk21-temurin
# Xóa apps mặc định
RUN rm -rf /usr/local/tomcat/webapps/*
# Deploy app thành ROOT để truy cập tại "/"
COPY --from=build /app/target/download-app-1.0.0.war /usr/local/tomcat/webapps/ROOT/

EXPOSE 8080
CMD ["catalina.sh", "run"]
