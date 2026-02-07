FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy everything
COPY . .

# ✅ Give execute permission to mvnw
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Render provides PORT env variable
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/hrmslite-0.0.1-SNAPSHOT.jar"]
