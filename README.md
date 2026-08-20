# LeetTrack

A full-stack REST API application for tracking LeetCode problem-solving progress, built with Spring Boot, PostgreSQL, JWT authentication, and a vanilla JavaScript frontend.

**Live app:** https://leettrack-frontend-keue.onrender.com
**API:** https://leettrack-backend-ckzz.onrender.com

## Features

- User registration and login with JWT-based authentication
- Password hashing via BCrypt
- Full CRUD for tracked problems (title, difficulty, pattern, status, URL)
- Filter problems by difficulty, status, or pattern
- Stats endpoint showing solve counts by difficulty and pattern
- Protected API routes — all endpoints except `/api/auth/**` require a valid token
- Deployed with Docker on Render, using a managed PostgreSQL database

## Tech Stack

**Backend:** Java 21, Spring Boot 3, Spring Security, Spring Data JPA, Hibernate, JWT (jjwt)
**Database:** PostgreSQL (production), H2 (local development)
**Frontend:** HTML, CSS, vanilla JavaScript (fetch API)
**Testing:** JUnit 5, Mockito
**DevOps:** Docker, Maven, Render (hosting), GitHub Actions-ready structure

## Architecture

The backend follows a layered architecture:


Authentication uses stateless JWT tokens. On login, the server issues a signed token; the client attaches it as a `Bearer` token on every subsequent request. A custom `JwtAuthFilter` validates the token before Spring Security's authorization checks run.

## API Endpoints

| Method | Endpoint | Auth required | Description |
|---|---|---|---|
| POST | `/api/auth/register` | No | Create a new user |
| POST | `/api/auth/login` | No | Log in, returns JWT |
| GET | `/api/problems` | Yes | List all problems (supports `?difficulty=`, `?status=`, `?pattern=` filters) |
| GET | `/api/problems/{id}` | Yes | Get one problem |
| POST | `/api/problems` | Yes | Create a problem |
| PUT | `/api/problems/{id}` | Yes | Update a problem |
| DELETE | `/api/problems/{id}` | Yes | Delete a problem |
| GET | `/api/problems/stats` | Yes | Get solve statistics |

## Running Locally

**Prerequisites:** Java 21, Maven, Git

```bash
git clone https://github.com/mhd-thameem/leettrack.git
cd leettrack
mvn spring-boot:run
```

The app runs on `http://localhost:8080` using a local H2 file database (no setup needed). Open `frontend/index.html` with a local server (e.g. VS Code's Live Server extension) to use the UI, or test the API directly with `curl`.

## Running Tests

```bash
mvn test
```

## Deployment

The backend is containerized with a multi-stage Dockerfile (Maven build stage → lightweight JRE runtime stage) and deployed to Render. Configuration is environment-driven via Spring profiles — `application.properties` for local development (H2), `application-prod.properties` for production (PostgreSQL), selected via the `SPRING_PROFILES_ACTIVE` environment variable.

## What I Learned Building This

This project was built end-to-end as a learning exercise covering the full software engineering lifecycle: Git branching and PR workflows, REST API design, Spring Security and JWT authentication, database migrations between H2 and PostgreSQL, Docker containerization, and cloud deployment — including debugging real production issues like CORS configuration, JDBC connection string formatting, and JWT key security requirements.