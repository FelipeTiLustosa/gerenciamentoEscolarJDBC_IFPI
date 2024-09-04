# SCHOOL MANAGEMENT IFPI <img src="https://skillicons.dev/icons?i=java" alt="Java Icon" style="vertical-align: middle; height: 35px;"/>

## 1. Overview

The School Management IFPI project is a Java-based system designed to manage information related to students, teachers, courses, enrollments, and grades. The system allows different types of users (students, teachers, and coordinators) to log in and access functionalities specific to their profile.

The goal of this project is to provide an integrated platform to efficiently manage school processes, focusing on simplicity and usability.

## 2. Features

Below is a list of the system's features, organized in a table for better visualization:

| Feature                                    | Description                                                                                       |
|--------------------------------------------|---------------------------------------------------------------------------------------------------|
| **Student, Teacher, and Coordinator Login**| Allows users to log in with ID and password, each with specific access rights.                   |
| **Student Management**                    | Insert, update, delete, and search for student information.                                      |
| **Teacher Management**                    | Insert, update, delete, and search for teacher information.                                      |
| **Course Management**                     | Insert, update, delete, and search for available courses.                                        |
| **Enrollment Management**                 | Perform, update, cancel, and search for student enrollments in specific courses.                 |
| **Grade Management**                      | Insert and update grades for specific enrollments.                                                |
| **Various Queries**                       | Query grades, enrollments, courses, and related teachers.                                         |

## 3. Technologies Used

- **Programming Language**: [Java](https://www.java.com/)
- **IDE**: [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- **Database**: [MySQL](https://www.mysql.com/)
- **Version Control**: [Git](https://git-scm.com/)
- **Development Environment**: [Docker](https://www.docker.com/)

## 4. Links to Additional Resources

- **Java JDBC**: [JDBC Documentation](https://docs.oracle.com/javase/tutorial/jdbc/)
- **MySQL**: [MySQL Documentation](https://dev.mysql.com/doc/)
- **Git**: [Git Documentation](https://git-scm.com/doc)

## 5. Setup and Running

To set up and run the project locally, follow the steps below:

### 5.1. Prerequisites

- Ensure you have [Java](https://www.java.com/) installed on your machine.
- Install [MySQL](https://www.mysql.com/) and create the `GerenciamentoEscolarIFPI` database.
- Install [IntelliJ IDEA](https://www.jetbrains.com/idea/) to manage the project.
- Configure the `db.properties` file with your database credentials.

### 5.2. Clone the Repository

```bash
git clone https://github.com/FelipeTiLustosa/gerenciamentoEscolarJDBC_IFPI.git
cd GerenciamentoEscolarIFPI
```
### 5.3. Configure the Database

1. Create the database using the provided SQL script.
2. Ensure the `db.properties` file contains the correct credentials for database connection.

### 5.4. Compile and Run

In IntelliJ IDEA:

1. Open the project.
2. Compile the project.
3. Run the main class to start the application.
## 6. Contribution

Contributions are welcome! If you would like to contribute to the project, follow these steps:

1. Fork the repository.
2. Create a branch for your feature or fix.
3. Make your changes and test.
4. Submit a pull request for review.

## 7. Folder Structure

Below is the organization of the project folders, with a brief description of each:
```bash
GerenciamentoEscolarIFPI/
│
├── src/
│   ├── model/
│   │   ├── entities/
│   │   │   ├── Student.java              # Class representing a student
│   │   │   ├── Course.java               # Class representing a course
│   │   │   ├── Teacher.java              # Class representing a teacher
│   │   │   ├── Enrollment.java           # Class representing an enrollment
│   │   │   ├── Grade.java                # Class representing a grade
│   │   └── dao/
│   │       ├── StudentDAO.java           # Interface for CRUD operations with students
│   │       ├── CourseDAO.java            # Interface for CRUD operations with courses
│   │       ├── TeacherDAO.java           # Interface for CRUD operations with teachers
│   │       ├── EnrollmentDAO.java        # Interface for CRUD operations with enrollments
│   │       ├── GradeDAO.java             # Interface for CRUD operations with grades
│   └── application/
│       ├── Main.java                     # Main class that executes the application
│
├── resources/
│   └── db.properties                     # Database connection configuration file
│
├── lib/
│   └── jdbc-driver.jar                   # JDBC driver for MySQL connection
│
├── README.md                             # Project documentation file
└── .gitignore                            # File to ignore unnecessary files in Git


```

