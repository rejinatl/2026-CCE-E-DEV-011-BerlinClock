# Berlin Clock

A Berlin Clock application with a Spring Boot backend and a React frontend. The backend exposes the current clock state through a versioned REST API, and the frontend renders it in the browser.

| Component | Tech Stack                                   | Port |
| --------  | -------------------------------------------- | ---- |
| Backend   | Java 25, Spring Boot 4.1.1, Maven            | 8088 |
| Frontend  | React, TypeScript, Vite                      | 5173 |

## Prerequisites

- Java 25 (JDK)
- Node.js (v24.18.0 or later) and npm
- IntelliJ IDEA (backend) and Visual Studio Code (frontend), or any editor you prefer

## Quick start
Clone the repository and run the backend and frontend in separate terminals.
**Backend**
Run each part in its own terminal.
```bash
cd backend
mvnw spring-boot:run
```

**Frontend**

```bash
cd frontend
npm install
npm run dev
```

Then open <http://localhost:5173/> in your browser. The Berlin Clock should be displayed.

## API

The backend runs on `http://localhost:8088`.

Every request must include the API version header:

```http
X-Api-Version: 1.0
```

Example:

```http
GET http://localhost:8088/api/berlin-clock
X-Api-Version: 1.0
```

A successful response contains the current Berlin Clock state.

## How It Works

The frontend does not call the backend directly. The browser talks to Vite, and the Vite dev proxy forwards `/api/berlin-clock` requests (with the `X-Api-Version: 1.0` header) to the Spring Boot API.

```text
Browser (localhost:5173)
        |  /api/berlin-clock
        |  X-Api-Version: 1.0
        ↓
Vite proxy (5173)
        |
        ↓
Spring Boot API (8088)
```

Make sure both applications are running at the same time.

## Backend setup (IntelliJ)

1. Open IntelliJ IDEA and choose **File -> Open**, then select the `backend` directory. IntelliJ detects the Maven project from `backend/pom.xml`.
2. Make sure Java 25 configured inside IntelliJ. Check **File -> Project Structure -> Project** and confirm the **Project SDK** is set to a Java 25 JDK.
4. Dependencies load automatically. If they don't, open the Maven tool and click Sync dependencies.
5. Open `BerlinClockApiApplication.java` and run its `main()` method, or run `mvnw spring-boot:run` from the IntelliJ terminal.

To check that it works, open the any HTTP client and send a request to the API 
endpoint http://localhost:8088/api/berlin-clock. The response should contain the current Berlin Clock state.

## Frontend setup (Visual Studio Code)

1. Open the `frontend` folder with **File -> Open Folder**.
2. In the VS Code terminal, run `npm install`.
3. Start the dev server with `npm run dev`.
4. Open <http://localhost:5173/>.

## Running tests

### Backend

In IntelliJ, right click `src/test/<package>` and choose Run Tests in **package**, OR run this from the `backend` directory:

```bash
mvnw test
```

### Frontend

```bash
npm run test:run
npx vitest run src/api/berlinClockApi.test.ts   # to test a single test file
```

Frontend tests use Vitest, React Testing Library, jsdom and jest-dom.

## Suggested workflow

1. Open `backend` in IntelliJ and confirm Java 25 is selected.
2. Start the Spring Boot application.
3. Open `frontend` in VS Code and run `npm install` if you haven't already.
4. Start Vite React with `npm run dev`.
5. Open <http://localhost:5173/>.
