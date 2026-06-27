### 23/6
1. **Work done** 
    - Read the project description and requirements to understand the problem.
    - Extracted potential Entities and Relations from the functional requirements and initiated a Database Schema diagram.
2. **Challenges**
    - I didn't know where to start. I knew that the first step must be to understand the problem but all these pages made me nervous.
3. **Knowledge gained**
    - I can start simple, doing small steps every day. Focus strictly on Domain Understanding before writing any implementation.

### 24/6
1. **Work done**
    - Finished the construction of the ER Diagram, gaining a deeper understanding of the Database structure and architecture.
2. **Challenges**
    - I was a little confused of the relationship for the absences of a student at session. After some thought I understood that the relationship is many-to-many and thus a new table "Absences" is necessary to establish the relationship. Another thing that was the multi-relationship built such as student -> enrollment -> module -> session -> absences -> enrollment. These circular relations is a bit challenging to understand.
3. **Knowledge gained**
    - Distinguishing an abstract "Subject" (e.g., Java) from its concrete temporal instance (the Module entity, e.g., Java Fall 2025). A single student can have multiple distinct enrollments for the same subject over time.


### 26-27/6
1. **Work done**
    - Extracted project dependencies
    - Created git repository
    - Initialize Spring-boot project.
2. **Challenges**
    - It was sometimes difficult to find the exact dependency needed for a requirement.
    - Most dependencies could be extracted from "Non-functional requirements", but to find the other, I should carefully read other sections of the document.
    - I wasn't familiar with the purpose of maven wrappers and .gitattributes.
3. **Knowledge gained**
    - I learnt to read carefully the specification document in order to find the project dependencies. Most dependencies can be extracted from the Non-functional Requirements sections, but other can be anywhere.
    - I learnt that maven wrappers are important and they should be uploaded to github in order for other users to run the application without the need of having maven installed in their machine.
    - .gitattributes can handle how the end of line is translated in different text documents.