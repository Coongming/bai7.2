# ---- Stage 1: Build WAR bằng Maven ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -B -DskipTests clean package

# ---- Stage 2: Tomcat 11 (JDK 21) ----
FROM tomcat:11.0-jdk21-temurin

# Xóa apps mặc định
RUN rm -rf /usr/local/tomcat/webapps/*

# Đặt WAR thành ROOT.war để truy cập tại "/"
COPY --from=build /target/download-app-1.0.0.war /Users/vanduccongminh/Downloads/apache-tomcat-11/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
