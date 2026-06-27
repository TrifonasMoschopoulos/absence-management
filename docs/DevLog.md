### 23/6
1. **Work done** 
    Read the project description and requirements to understand the problem.
    Extracted potential Entities and Relations from the functional requirements and initiated a Database Schema diagram.
2. **Challenges**
    I didn't know where to start. I knew that the first step must be to understand the problem but all these pages made me nervous.
3. **Knowledge gained**
    I can start simple, doing small steps every day. Focus strictly on Domain Understanding before writing any implementation.

### 24/6
1. **Work done**
    Finished the construction of the DB Diagram, gaining a deeper understanding of the Database structure and architecture.
2. **Challenges**
    I was a little confused of the relationship for the absences of a student at session. After some thought I understood that the relationship is many-to-many and thus a new table "Absences" is necessary to establish the relationship. Another thing that was the multi-relationship built such as student -> enrollment -> module -> session -> absences -> enrollment. These circular relations is a bit challenging to understand.
3. **Knowledge gained**
    Distinguishing an abstract "Subject" (e.g., Java) from its concrete temporal instance (the Module entity, e.g., Java Fall 2025). A single student can have multiple distinct enrollments for the same subject over time.
