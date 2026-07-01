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


### 28-29/6
1. **Work done**
    - Configure pom.xml: Integrate Spring parent, Web, JPA, validation, SpringDoc, PostgreSQL dependencies.
    - Create the first version of README.md: Title, Description, Overview/Features, Tech-Stack and Prerequisites.
2. **Challenges**
    - Had to check dependency compatibility between Spring & SpringDoc.
    - PostgreSQL's version is configured at spring-boot-starter-parent.
    - Had to understand the "scope" element used by POM.xml.
3. **Knowledge Gained**
    - I only need to define the Spring-Boot version once at spring-boot-starter-parent and this applied to most spring dependencies.
    - Gained better understanding of scopes. 
    - Git History Management: Used git stash & git rebase -i, to rewrite local commit message.

### 1/7
1. **Work done**
    - Added db folder, for database initialization.
    - Initialized docker-compose.yml, by adding the first service which configures, initialize and starts the database.
    - Created .env file and added the database credentials, which are used by db service in docker-compose.yml.
2. **Challenges**
    - I was having problems to connect my machine to the container where the database is running.
    - I also wasn't sure how the initialization of the database works while using docker.
    - I wasn't sure about where the database credentials should be stored.
3. **Knowledge Gained**
    - I learnt that to run a database with docker doesn't require a Dockerfile.
    - I understood that sensitive information should be stored in .env files and not be uploaded to github.
