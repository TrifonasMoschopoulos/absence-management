# API DESIGN - ENDPOINTS

## Notations
- [] = Optional

## HTTP Status Codes/Responses
| Status                   | Description |
| ------------------------ | ---------------------- |
| 200 OK                   | Request has succeeded  |
| 201 Created              | Successful creation of a resource |
| 204 No Content           | Successful delete of resource. Response has no body |
| 400 Bad Request          | Malformed request or invalid field values |
| 404 Not Found            | Requested resource is missing |
| 409 Conflict             | Conflict with current state of target resource. E.g. resource already exist while POST | 
| 422 Unprocessable Entity | Violates bussiness rules |

## Student

| Method    | URL                             |  Query Variables |    Body                                 | Returns          | HTTP Response |
| :-------: | ------------------------------- | ---------------  | --------------------------------------- | ---------------- | ------------- |
| GET       | /api/students                   | [first_name, last_name] | -                                | List: StudentResponse | 200 |  
| GET       | /api/students/{id}  |  -               | -                                       | StudentResponse          | 200, 404 |
| POST      | /api/students | - | first_name, last_name, email | StudentResponse  | 201, 400, 409 (email already used)|
| PATCH     | /api/students/{id}  | - | [first_name, last_name, email] | StudentResponse  | 200, 404, 400 (no field given), 409 (if set new different email and is already used) | 
| DELETE    | /api/students/{id}  | -                | -                                       | -        | 204, 404|

## Module

| Method    | URL                             |  Query Variables |    Body                                 | Returns          | HTTP Response |
| :-------: | ------------------------------- | ---------------  | --------------------------------------- | ---------------- | ------------- |
| GET       | /api/modules                    | [title, credits, semester, academic_year] | -  | List: ModuleResponse   | 200, 400 (wrong credits,semester,year) |
| GET       | /api/modules/{id}             | -                | -                                       | ModuleResponse           | 200, 404 |
| POST      | /api/modules                    | -                | code, title, credits, semester, academic_year | ModuleResponse        | 201, 400 (wrong code, credits,semester,year) |
| PATCH     | /api/modules/{id}             | -                | [title, credits, semester, academic_year] | ModuleResponse      | 200, 404, 400 (wrong credits,semester,year, no field given) |
| DELETE    | /api/modules/{id}             | -                | -                                        | -            | 204, 404 |


## Instructor
| Method    | URL                             |  Query Variables |    Body                                 | Returns          | HTTP Response |
| :-------: | ------------------------------- | ---------------  | --------------------------------------- | ---------------- | ------------- |
| GET       | /api/instructors                | [first_name, last_name] | -                            | List: InstructorResponse | 200        |
| GET       | /api/instructors/{id}           |                  |                                     | InstructorResponse  | 200, 404      |
| POST      | /api/instructors                | -                | first_name, last_name, email        | InstructorResponse     | 201, 400, 409 (email already used) |


## Enrollment
| Method    | URL                             |  Query Variables |    Body                                 | Returns          | HTTP Response |
| :-------: | ------------------------------- | ---------------  | --------------------------------------- | ---------------- | ------------- |
| GET*       | /api/students/{id}/enrollments | -     |  -                                      | List of Enrollments | 200, 404 |
| GET       | /api/enrollments/{id} | - | - | EnrollmentResponse | 200, 404 |
| POST      | /api/enrollments          | - | student_id, module_id, [status] | EnrollmentResponse   | 201, 404, 400, 422 (pair student, module already exists) |
| DELETE    | /api/enrollments/{id} | -            |                                        | -                | 204, 404, 422 (can only cancel ACTIVE enrollments) |

## Instuctor-Module Assignment
| Method    | URL                             |  Query Variables |    Body                                 | Returns          | HTTP Response |
| :-------: | ------------------------------- | ---------------  | --------------------------------------- | ---------------- | ------------- |
| GET       | /api/instructors/{id}/teaching-assignments |       |                   | InstructorAssignmentResponse | 200, 404  |
| POST      | /api/teaching-assignments | -            | moduleId, instructorId, role          | TeachingAssignmentResponse | 201, 404, 400 , 409 (pair already exists) |   

## Session
| Method    | URL                             |  Query Variables |    Body                                 | Returns          | HTTP Response |
| :-------: | ------------------------------- | ---------------  | --------------------------------------- | ---------------- | ------------- |
| GET       | /api/modules/{id}/sessions    | [from, to] | - | List: SessionResponse | 200, 404, 400, 422 (if from > to) |
| POST      | /api/modules/{id}/sessions    | - | session_date, start_time, end_time, session_type, [topic] | SessionResponse | 201, 404, 400, 422 (if start_time >= end_time) | 


## Absence
| Method    | URL                             |  Query Variables |    Body                                 | Returns          | HTTP Response |
| :-------: | ------------------------------- | ---------------  | --------------------------------------- | ---------------- | ------------- |
| GET       | /api/absences/{id} | - | - | AbsenceDetailsResponse | 200, 404 |
| GET       | /api/absences | student_id, module_id, session_id | - | List: AbsenceSummaryResponse | 200, 400 (no filter given), 404 |    
| POST      | /api/absences | - | enrollment_id, session_id, [status, justified, justification] | CreateAbsenseResponse | 201, 404, 400, 409 (session, enrollment already exists), 422 (enrollment & session must relate to the same module, enrollment must be ACTIVE, justified can be true if status=ABSENT, justification can be given if justified=true)|
| PATCH     | /api/absences/{absence_id}/justification | - | justify, [justification] | UpdateJustificationResponse | 200, 400, 404, 422 (if status is not ABSENT and justify=True, if justify=False but justification given) |


## Reporting

| Method    | URL                             |  Query Variables |    Body                                 | Returns          | HTTP Response |
| :-------: | ------------------------------- | ---------------  | --------------------------------------- | ---------------- | ------------- |
| GET       | /api/reports/students/{id}/modules/{id} | -  | - | StudentModuleSummaryResponse   | 200, 404 |
| GET       | /api/reports/modules/{id}/at-risk | threshold (default=0.33) | - | List of StudentModuleSummary | 200, 404, 400, 422 (threshold range) |
| GET       | /api/reports/modules/{id}/highest_absence_sessions | limit (default=5) | - | List of SessionAbsenceSummary | 200, 404, 400, 422 (limit range: [1, 20]) |
| GET       | /api/reports/modules/{id}/most_absent_students     | limit (default=5) | - | List of StudentAbsencesAtModule | 200, 404, 400, 422 (limit range: [1, 20]) |
