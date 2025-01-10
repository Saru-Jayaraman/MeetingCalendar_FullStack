## MEETING CALENDAR BACKEND APPLICATION Using SPRING BOOT
### Objective:
* Meeting Calendar Java Application is a backend Spring Boot Application more focuses on CRUD functionalities like Create, Update and Delete Meetings.
* Java Spring boot Application is a middleware between Frontend application and Database.
* It connects with MySQL Database for doing CRUD operations with Meetings table.
* Frontend needs data like id, name, icon & navigate url for Dashboard and Navigation bar from backend. It provides the same data.
* This application connects with Email Microservice for generating Email to the participants.

* It has the following Controllers for accessing those as Webservice:
### Dashboard Controller:
* Populates the Dashboard table with ID, Name, Icon and Navigate url when the application starts.
* Whenever Dashboard API is called, then all these data are returned as array of DTO and rendered on the Meeting Calendar UI.
### Meeting Controller:
5 APIs are created.
**`GetAllMeetings`**
- Returns all available meetings data present inside Meetings table as an array of MeetingDTO.
**`GetMeetingById`**
- Return meeting by their ID as a MeetingDTO.
**`CreateMeeting`**
- Meeting body is received and saved inside Meetings table. Email is generated to respective participants after create.
**`UpdateMeeting`**
- Meeting body is received and updated inside Meetings table. Email is generated to respective participants after update.
**`DeleteMeeting`**
- Meeting is deleted inside Meetings table by their ID.
### Navbar Controller:
Two APIs are created.
**`Navbar`**
- Populates the Navbar table with ID, Name and Url when the application starts.
-Whenever Navbar API is called, then all these data are returned as array of DTO and rendered on the Meeting Calendar UI.
**`NavbarDropdown`**
- Populates the NavbarDropdown table with ID, Name, Icon and Href when the application starts.
-Whenever NavbarDropdown API is called, then all these data are returned as array of DTO and rendered on the Meeting Calendar UI.

### Technical Requirements:
#### Backend:
* Java 17
* Spring Boot REST
* Spring Data JPA
* Validation
* Maven
#### Database:
* MySQL