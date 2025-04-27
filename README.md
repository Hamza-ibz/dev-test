# HMCTS Dev Test

## Overview
This project is a simple Case Management System with separate Backend and Frontend applications. It was created as part of the HMCTS developer technical assessment.

## Technologies Used
- **Backend**: Java 17, Spring Boot, Gradle
- **Frontend**: Node.js, Express.js, Nunjucks, TypeScript
- **Database**: In-memory (HashMap)
- **Testing**: JUnit, Jest, Supertest, Chai

## Project Structure
```
hmcts-dev-test-backend/   // Java Spring Boot backend
hmcts-dev-test-frontend/  // Node.js + Express frontend
```

## How to Run

### Backend
```bash
cd hmcts-dev-test-backend
./gradlew bootRun
```
Backend will start on: `http://localhost:4000`

### Frontend
```bash
cd hmcts-dev-test-frontend
yarn install
yarn build
yarn start:dev
```
Frontend will start on: `https://localhost:3100` (ignore SSL warning)

## Features
- Create, View, Update, Delete Tasks
- Display tasks in a user-friendly GOV.UK Design System style
- API Validation and Error Handling
- Unit and Integration Testing (backend and frontend)

## API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /tasks   | Get all tasks |
| GET    | /tasks/{id} | Get task by ID |
| POST   | /tasks   | Create a new task |
| PUT    | /tasks/{id}/status | Update task status |
| DELETE | /tasks/{id} | Delete a task |

## Author
Mohammad Hamza Ibrahim
