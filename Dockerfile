# Use Java 17
FROM eclipse-temurin:17-jdk-alpine

# App directory
WORKDIR /app

# Copy files
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose Render port
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/*.jar"]
