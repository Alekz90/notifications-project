# Get the image
FROM gradle:9.4-jdk21-alpine AS build

ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

# Convert them into environment variables inside the image
ENV GITHUB_USERNAME=$GITHUB_USERNAME
ENV GITHUB_TOKEN=$GITHUB_TOKEN

# Create the directory
WORKDIR /app

# Copy the necesary files
COPY build.gradle settings.gradle ./

# Copy child projects
COPY ms-security-users ./ms-security-users/
COPY ms-notification-management ./ms-notification-management/
COPY ms-kafka-notifications ./ms-kafka-notifications/

# Download the dependencies and build the project
RUN gradle :ms-kafka-notifications:build -x test --no-daemon || true

# Create the final image
FROM eclipse-temurin:21-jdk-alpine-3.20

# Create the directory
WORKDIR /app

# Copy the generated jar in the building
COPY --from=build /app/ms-kafka-notifications/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

# Move to the directory
# cd C:/Projects/Notifications/notifications-project

# Construir la imagen # "--no-cache" without caching
# docker build \
#  --build-arg GITHUB_USERNAME="your_github_username" \
#  --build-arg GITHUB_TOKEN="your_github_token" \
#  -t "ms-kafka-notifications-img:1.0.0" -f ms-kafka-notifications.dockerfile .

# Ejecutar el contenedor
# docker run --name "ms-kafka-notifications-container" "ms-kafka-notifications-img:1.0.0"

# Delete the container
# docker container rm -f "ms-kafka-notifications-container"

# Delete the image
# docker image rm "ms-kafka-notifications-img:1.0.0"