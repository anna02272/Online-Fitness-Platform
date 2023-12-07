# Online Fitness Platform

This project focuses on developing a web-based MVC (Model-View-Controller) application for scheduling online fitness training sessions. 
The primary emphasis during the implementation was on creating essential UML (Unified Modeling Language) diagrams, including Class Diagrams, Activity Diagrams, Sequence Diagrams, and Use Case Diagrams.

## Technologies
- Java
- MVC
- MySQL
- Thymeleaf

## Roles
1. Client
2. Trainer
3. Admin
4. Platform Owner
5. Sports Hour (Nutritionist)

## Functionality
**Registration and Profiles:**
- Clients and trainers register independently.
- Registration data includes: name, surname, email, contact phone, address, credit card number, primary language, additional languages.
- Clients provide additional details: height, weight, health status, goals, and a list of home workout equipment.
- Trainers input qualifications, certificates, and titles. The account becomes active upon admin approval.

**Schedule and Booking:**
- Trainers maintain a list of available slots for the next month.
- Clients can book slots with any trainer.
- The schedule considers time zones of both clients and trainers.
- Cancellation is allowed up to two hours before the session, after which it incurs a charge.

**Finances:**
- A percentage of each training fee goes to the platform.
- Clients can work with different trainers for their sessions.

**Conducting Training:**
- Trainers tailor workouts to clients' equipment and goals.
- During sessions, trainers monitor clients via camera and data from sports devices.
- Clients can input data regarding weight changes and other parameters.

**Ratings and Progress Tracking:**
- After each session, both the trainer and client provide ratings (stars and comments).
- Trainers can track the progress of clients they've worked with.

**Reports for the Owner:**
- The platform generates reports for the owner, including earnings for a specific interval, daily, weekly, and monthly earnings.
- Provides a list of top-rated trainers and those with the highest earnings.

This platform facilitates effective tracking, training management, and financial performance tracking, offering users and the owner comprehensive insights into its operations.

## UML Diagrams

### Class Diagram
![Dijagram klasa](https://github.com/anna02272/Specifikacije2022-projekat/assets/96575598/be02d14c-1925-447a-9233-fcdda05dd939)

### Use Case Diagram
![Dijagram slucajeva koriscenja](https://github.com/anna02272/Specifikacije2022-projekat/assets/96575598/3fd182ce-092d-428e-b486-0527b3ddb622)

### Activity Diagram
![Registracija_DijagramAktivnosti](https://github.com/anna02272/Specifikacije2022-projekat/assets/96575598/84dbe5dd-0574-486c-8ca5-39a1773d1a8b)
![Kreiranje izvestaja](https://github.com/anna02272/Specifikacije2022-projekat/assets/96575598/ead85934-5dc8-4c62-9699-f6a216aa85eb)

### Sequence Diagram
![Sekvenca](https://github.com/anna02272/Specifikacije2022-projekat/assets/96575598/e8acaeac-4688-4a01-b276-87a902d20ab0)
![Registracija_DijagramSekvence](https://github.com/anna02272/Specifikacije2022-projekat/assets/96575598/aa341dbe-9ed2-4222-8d64-ba1ed8301add)
![Model](https://github.com/anna02272/Specifikacije2022-projekat/assets/96575598/4bcb0dde-ede1-46b5-a84a-ee32c14cda6a)
![Dijagram sekvence kreiranje izvestaja](https://github.com/anna02272/Specifikacije2022-projekat/assets/96575598/64a42bb4-6632-4728-a6f9-6262373dfbc8)

