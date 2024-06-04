
# Take Home Assignment

This is a project created to carry out the practical interview.  The project aims to test the ability to create and display API.


## Assignment

You need to develop an API for sleep logger that will later be integrated into the Noom web interface. The functional requirements API needs to support are:

 1. Create the sleep log for the last night
    1. Sleep data contains
      1. The date of the sleep (today)
      1. The time in bed interval
      1. Total time in bed
      1. How the user felt in the morning: one of [BAD, OK, GOOD]

 1. Fetch information about the last night's sleep
 1. Get the last 30-day averages
    1. Data that needs to be included in the response
        1. The range for which averages are shown
        1. Average total time in bed
        1. The average time the user gets to bed and gets out of bed
        1. Frequencies of how the user felt in the morning
    1. The user can switch back to the single sleep log view (goes to requirement #1)

The assignment is to:

 1. Create tables required to support the functionality above (PostgreSQL)
    1. The Spring project includes Flyway, which you should use to manage your DB migrations.
 1. Build required functionality in the REST API service (Kotlin/Java + Spring)
    1. Ignore authentication and authorization, but make the REST API aware of the concept of a user.
 1. Write a simple script or create Postman collection that can be used to test the API


## How to Run

Download the project.

On the Back-end

Run:

```bash
  cd .\take-home-assignment\sleep
```

Install docker and run:

```bash
   docker-compose up --build
```

Run the project with an IDEA (IntelliJ, Eclipse or VSCode) which will create the tables in the database.

Will need docker and ports 5432 (Postgres) and 8080 (API)

On the Front-end

```bash
  cd .\take-home-assignment\sleep-frontend
```

Download the dependencies with:

```bash
  yarn
```

Or

```bash
  npm Install
```

Run the project with:

```bash
  yar dev
```

Or

```bash
  npm run dev
```

Will be display in http://localhost:5173/
