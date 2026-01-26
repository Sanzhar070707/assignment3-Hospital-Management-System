# Assignment 3 – Hospital Management System

## A. Project Overview
**Purpose of API:**  
This project implements a Hospital Management System API in Java with PostgreSQL. It provides CRUD operations for patients, doctors, services, appointments, and payments. The API ensures data validation, error handling, and multi-layer architecture (Controller → Service → Repository → Database).

**Summary of entities and their relationships:**
- **Patient**: has personal data and phone (unique).
- **Doctor**: has specialization and hire date.
- **Service**: medical service with description and price.
- **Appointment**: links Patient, Doctor, and Service with a scheduled date.
- **Payment**: linked to Appointment, stores amount and status.

Relationships:
- One Patient → many Appointments
- One Doctor → many Appointments
- One Service → many Appointments
- One Appointment → one Payment

**OOP design overview:**
- Abstract class `BaseEntity` defines common fields and abstract methods.
- Subclasses (`Patient`, `Doctor`, `Appointment`, `Service`, `Payment`) extend `BaseEntity`.
- Interfaces `Validatable` and `Payable` enforce validation and payment logic.
- Composition: `Appointment` contains `Patient`, `Doctor`, `Service`; `Payment` contains `Appointment`.
- Polymorphism: `printInfo()` overridden in all entities.

---

## B. OOP Design Documentation
**Abstract class and subclasses:**
- `BaseEntity` (id, name, timestamps, abstract methods `printInfo()`, `validate()`)
- Subclasses: `Patient`, `Doctor`, `Appointment`, `Service`, `Payment`

**Interfaces and implemented methods:**
- `Validatable`: `isValid()` implemented in all entities
- `Payable`: `pay(double amount)` implemented in `Payment`

**Composition/Aggregation:**
- `Appointment` aggregates `Patient`, `Doctor`, `Service`
- `Payment` aggregates `Appointment`

**Polymorphism examples:**
- `printInfo()` overridden in each entity to display specific details.
- Example: `Doctor.printInfo()` vs `Patient.printInfo()` produce different outputs.

**UML diagram (simplified):**
![img_7.png](img_7.png)

---

## C. Database Description
**Schema:**
- `patients(patient_id PK, first_name, last_name, birth_date, phone UNIQUE)`
- `doctors(doctor_id PK, first_name, last_name, specialization, hire_date)`
- `services(service_id PK, service_name UNIQUE, price CHECK > 0)`
- `appointments(appointment_id PK, patient_id FK, doctor_id FK, service_id FK, appointment_date)`
- `payments(payment_id PK, appointment_id FK, amount CHECK >= 0, payment_date)`

**Constraints & Foreign Keys:**
- FK links between appointments → patients, doctors, services
- FK link between payments → appointments
- Unique constraint on patient phone
- Check constraints on service price and payment amount

**Sample SQL inserts:**  
See [`schema.sql`](./schema.sql) for full inserts (9 patients, 9 doctors, 6 services, 6 appointments, 6 payments).

---

## D. Controller
**CRUD operations summary (examples):**
- **Create Patient:**  
  `addPatient(new Patient(...))` → inserts into DB
- **Read All Doctors:**  
  `getAllDoctors()` → returns list of doctors
- **Read by ID:**  
  `getPatientById(3)` → returns patient or throws `ResourceNotFoundException`
- **Update:**  
  `updateDoctor(2, doctor)` → updates doctor info
- **Delete:**  
  `deletePatient(5)` → removes patient by ID

Responses:
- Success → confirmation message
- Error → custom exception (`InvalidInputException`, `ResourceNotFoundException`, `DatabaseOperationException`)

---

## E. Instructions to Compile and Run
**Compile:**
```bash
javac -d out $(find . -name "*.java")
```
## F. Screenshots

![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)
![img_3.png](img_3.png)
![img_4.png](img_4.png)
![img_5.png](img_5.png)
![img_6.png](img_6.png)
---

## G. Reflection Section
**What I learned:**  
- How to design a system using OOP principles (inheritance, interfaces, polymorphism, composition).  
- How to connect a Java application to PostgreSQL using JDBC and implement CRUD operations.  
- How to structure a multi-layer architecture (Controller → Service → Repository → Database) for clear separation of concerns.  

**Challenges faced:**  
- Debugging SQL schema and foreign key relationships.  
- Handling exceptions consistently across different layers.  
- Configuring JDBC connection and working with `PreparedStatement`.  