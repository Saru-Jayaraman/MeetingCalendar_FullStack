## MEETING CALENDAR FULL STACK WEB APPLICATION Using REACT JS AND SPRING BOOT
Create a Full Stack Meeting Calendar Web Application with React in the Frontend and Java at the Backend. Make sure to include the following necessary functionalities in the application.
* Develop a backend with CRUD APIs for the Meeting Calendar app.
* Integrate the frontend Meeting Calendar app to the backend using Axios.
* Implement Router for efficient navigation in the Meeting Calendar app.

### Additional Notes:
* Adjust the tasks complexity based on your understanding.
* Add extra features or components to enhance the app.
* Create README files for both the frontend and backend.

By the end of the workshop, you should upload:
* A fully functional frontend application: Includes the history of development, showcasing the progress and changes made during the workshop.
* A fully functional backend API: Includes the history of development, detailing the updates and iterations throughout the workshop.
* README.md files for both frontend and backend: Provide a clear explanation of the app, its setup process, and its code structure and features.

## STEPS IMPLEMENTED:
Project is divided into 3 mini-projects/sections. Refer README.md files in each of those project for detailed explanation.
1. **Frontend:**
* Meeting Calendar React Component is a frontend react application more focusses on Schedule & Manage Meetings, Apply Routers for Component Switching and Axios for Connecting with Spring boot application.
* Implemented all the necessary **`CRUD APIs`** functionalities inside Schedule & Manage Meeting components.
* **`Form`** validation, error handling and managing user data also included.
* Switch across multiple components in Navigation bar and Dashboard using **`Routers`** efficiently.
* Connected with backend application using **`Axios`** for sending request to them and receiving response from them.
* Follow React best practices to ensure the solution is modular, reusable, scalable, and professional.

2. **`Backend:`**
* Meeting Calendar Java Application is a backend Spring Boot Application more focusses on CRUD functionalities like Create, Update and Delete Meetings.
* Java Spring boot Application is a middleware between Frontend application and Database.
* It connects with MySQL Database for doing CRUD operations with Meetings table.
* Frontend needs data like id, name, icon & navigate url for Dashboard and Navigation bar from backend. It provides the same data.

3. **`Email Microservice:`**
* Meeting Calendar Backend Application interacts with Email Microservice for sending notification Email to participants when meeting is created or updated.
* Sends an email from Gmail account using SMTP protocol.
* Mail is constructed as Mime message and sent using Java Mail Sender.
* Sent email is saved in the Email Database as well.