# Use Alpine Linux with OpenJDK 21
FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /app

# Install necessary packages
RUN apk add --no-cache \
    gcompat \
    curl \
    bash

# Create non-root user
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# Copy the JAR file
COPY build/agents/aspectjweaver.jar aspectjweaver.jar
COPY build/agents/opentelemetry.jar opentelemetry.jar
COPY build/libs/app.jar app.jar

# Change ownership to non-root user
RUN chown -R appuser:appgroup /app


# Switch to non-root user
USER appuser

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-javaagent:/app/aspectjweaver.jar", "-javaagent:/app/opentelemetry.jar", "-jar", "/app/app.jar"]