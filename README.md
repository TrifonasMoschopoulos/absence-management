# Absence Management System
 
> A Spring Boot backend application designed to help instructors manage student absences.  

## About the Project
Traditional paper-based attendance tracking is error-prone and fails to notify instructors in time when a student approaches the failure threshold. 

This system provides a tool to manage student absences across various courses, ensuring data integrity while also alerting instructors of students on the verge of failure.

### Key Features
- **Course & Enrollment Management**: Provides a unified environment to manage courses, students and enrollments.
- **Attendance Tracking**: Records student absences per module's session.
- **Absence Justification**: Supports an absence justification workflow
- **Reporting & Analytics**: 
    - Per-student per-module summary such as total sessions, absence rate.
    - A list of students exceeding absence threshold.
    - Module-level statistics such as sessions with highest absence rate, student with most absences.

## Tech Stack
- **Backend**: Java 21, Spring Boot 3.5.16
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Documentation**: OpenAPI / Swagger UI (SpringDoc)

## Prerequisites
- **Java Development Kit (JDK) 21**
- **Docker & Docker Compose**
- **WSL 2 (Recommended for Windows Users)**