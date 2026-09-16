# Notifications Project

A microservices-based notification platform built with Java, Spring Boot, Kafka, PostgreSQL, and a React frontend. The solution is designed to manage users, process notifications, and deliver them through multiple channels such as email, SMS, and push notifications.

## Overview

The project is organized into separate services that work together:

- `ms-security-users`: user authentication, authorization, and security flows
- `ms-notification-management`: notification APIs and persistence layer
- `ms-kafka-notifications`: Kafka-based message processing and delivery adapters
- `notifications-web`: frontend application for interacting with the platform

```text
Client / Browser
       |
       v
notifications-web (React + Vite)
       |
       v
ms-security-users (8081)
       |
       +--> PostgreSQL (users database)
       |
       v
ms-notification-management (8082)
       |
       +--> PostgreSQL (notifications database)
       |
       v
ms-kafka-notifications (8083)
       |
       +--> Kafka broker
       +--> MailPit (email testing)
       +--> SMSPit (SMS testing)
```

## Architecture

### 1. Security Users Service
Responsible for:
- registration and login
- JWT authentication
- user management
- password recovery and validation flows

### 2. Notification Management Service
Responsible for:
- creating and tracking notifications
- handling notification metadata and status
- exposing REST endpoints for notification operations
- communicating with Kafka for async processing

### 3. Kafka Notifications Service
Responsible for:
- consuming and producing Kafka events
- sending notifications through email and SMS channels
- integrating with external notification simulators for testing

### 4. Frontend
A React + TypeScript + Vite app used to interact with the notification system.

## Tech Stack

- Java 21
- Spring Boot 4 / Spring Cloud
- Gradle
- PostgreSQL
- Kafka
- React + TypeScript + Vite
- Docker Compose
- MailPit
- SMSPit

## Prerequisites

Before starting the project, make sure you have:

- Java 21 or newer
- Gradle
- Docker and Docker Compose
- Node.js 20+ and npm
- access to the repository's local Maven dependency setup if needed

## Configuration

The project uses an environment file named `.env` at the root. It contains database credentials, Kafka settings, mail settings, and JWT secret values.

Key values include:

- `SECRET_KEY`
- `USERS_DB_URL`, `USERS_DB_USER`, `USERS_DB_PASSWORD`
- `NOTIFICATIONS_DB_URL`, `NOTIFICATIONS_DB_USER`, `NOTIFICATIONS_DB_PASSWORD`
- `KAFKA_BOOTSTRAP_SERVERS`
- `MAIL_HOST`, `MAIL_PORT`
- `SMS_API_URL`

## Run with Docker

From the project root:

```bash
docker compose up -d
```

This starts the infrastructure services:

- PostgreSQL for notifications
- Kafka broker
- Kafka UI
- MailPit
- SMSPit

Ports:

- Kafka UI: http://localhost:8080
- MailPit UI: http://localhost:8025
- SMSPit UI: http://localhost:4301
- Notifications database: localhost:5433
- Kafka broker: localhost:29092

## Run the Backend Services

Each Spring Boot service can be started independently with Gradle.

```bash
./gradlew :ms-security-users:bootRun
./gradlew :ms-notification-management:bootRun
./gradlew :ms-kafka-notifications:bootRun
```

Service ports:

- `ms-security-users`: http://localhost:8081
- `ms-notification-management`: http://localhost:8082
- `ms-kafka-notifications`: http://localhost:8083

## Run the Frontend

```bash
cd notifications-web
npm install
npm run dev
```

The frontend usually runs on:

- http://localhost:5173

## Project Structure

```text
notifications-project/
├── .env
├── build.gradle
├── docker-compose.yml
├── gradlew
├── gradlew.bat
├── settings.gradle
├── ms-security-users/
├── ms-notification-management/
├── ms-kafka-notifications/
├── notifications-web/
├── init_notifications.sql
├── notifications-query.sql
├── users-query.sql
└── README.md
```

## Useful Commands

Build the backend modules:

```bash
./gradlew build
```

Run a specific module:

```bash
./gradlew :ms-security-users:bootRun
```

Stop all Docker services:

```bash
docker compose down
```

## Notes

This project is structured as a learning and prototype notification system. Some infrastructure services are intentionally mocked or test-oriented, including MailPit and SMSPit, which make local development easier without depending on real external providers.

## License

This project is provided as-is for development and learning purposes. Update or replace this section if you intend to publish the repository under a specific license.
