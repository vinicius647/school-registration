### Ports:
* Server 8282
* Mysql 3306

### Swagger documentation
* [API DOC](http://localhost:8282/school-registration/v1/api-docs)
* http://localhost:8282/school-registration/v1/api-docs
* [SWAGGER UI](http://localhost:8282/swagger-ui.html)
* http://localhost:8282/swagger-ui.html

### Username and Password
All usernames and passwords are case sensitives and are in plaintext.

### Roles
* There are two roles, ROLE_SCHOOL and ROLE_STUDENT
* On Swagger documentation there is information about all services endpoints and its related roles. 

### Master User
* The master user is in hard code considering it is a test api, however in a production environment it would be in the database.
* Username is 'school', password is 'pass1234' and has ROLE_SCHOOL

### Students
* There are 3 students already created, STUDENT-1, STUDENT-2, STUDENT-3.
* The usernames are STUDENT1, STUDENT2, STUDENT3
* The passwords are 111, 222, 333
* All students have the ROLE_STUDENT

### Courses
There are 7 students already created, COURSE-1...COURSE-7

### How to run it
* Run the command: docker-compose up -d
* To stop: docker-compose down

### MAC WITH ARM ARCHITECTURE
Might be necessary to add new parameter in mysqlserver on docker-compose.yml
* platform: linux/x86_64