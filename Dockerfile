# ---- Stage 1: Build WAR bằng Maven ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy file cấu hình Maven trước để cache dependency
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline

# Copy source code và build
COPY src ./src
RUN mvn -B -DskipTests clean package

# ---- Stage 2: Tomcat 11 (JDK 21) ----
FROM tomcat:11.0-jdk21-temurin

# Xóa các ứng dụng mặc định của Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy file WAR từ stage build sang Tomcat, đổi tên thành ROOT.war
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Mở cổng 8080
EXPOSE 8080

# Chạy Tomcat
CMD ["catalina.sh", "run"]
