# Get the image
FROM gradle:9.4-jdk21-alpine AS build

# Create the directory
WORKDIR /app

# Copy the necesary files
COPY build.gradle settings.gradle ./

# Copy child projects
COPY ms-security-users ./ms-security-users/
COPY ms-notifications-management ./ms-notifications-management/

# Download the dependencies and build the project
RUN gradle :ms-security-users:build -x test --no-daemon || true

# Create the final image
FROM eclipse-temurin:21-jdk-alpine-3.20

# Create the directory
WORKDIR /app

# Copy the generated jar in the building
COPY --from=build /app/ms-security-users/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

# Move to the directory
# cd C:/Projects/Notifications/notifications-project

# Construir la imagen # "--no-cache" without caching
# docker build -t "ms-security-users-img:1.0.3" -f ms-security-users.dockerfile .

# Ejecutar el contenedor
# docker run --name "ms-security-users-container" "ms-security-users-img:1.0.3"

# Delete the container
# docker container rm -f "ms-security-users-container"

# Delete the image
# docker image rm "ms-security-users-img:1.0.3"