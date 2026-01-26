CREATE TABLE patients (
                          patient_id SERIAL PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          birth_date DATE NOT NULL,
                          phone VARCHAR(20) UNIQUE
);

CREATE TABLE doctors (
                         doctor_id SERIAL PRIMARY KEY,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         specialization VARCHAR(50) NOT NULL,
                         hire_date DATE NOT NULL
);

CREATE TABLE services (
                          service_id SERIAL PRIMARY KEY,
                          service_name VARCHAR(100) NOT NULL UNIQUE,
                          price NUMERIC(8,2) CHECK(price > 0)
);

CREATE TABLE appointments (
                              appointment_id SERIAL PRIMARY KEY,
                              patient_id INT NOT NULL REFERENCES patients(patient_id),
                              doctor_id INT NOT NULL REFERENCES doctors(doctor_id),
                              service_id INT NOT NULL REFERENCES services(service_id),
                              appointment_date TIMESTAMP NOT NULL
);

CREATE TABLE payments (
                          payment_id SERIAL PRIMARY KEY,
                          appointment_id INT NOT NULL REFERENCES appointments(appointment_id),
                          amount NUMERIC(8,2) NOT NULL CHECK(amount >= 0),
                          payment_date DATE NOT NULL
);


INSERT INTO patients (first_name, last_name, birth_date, phone) VALUES
                                                                    ('Абзал','Абдуманаб','2004-02-15','+77010012345'),
                                                                    ('Арман','Акмурат','2003-11-03','+77070098765'),
                                                                    ('Санжар','Анешбек','2005-01-09','+77470011223'),
                                                                    ('Санжар','Дуйсен','2004-08-12','+77010099887'),
                                                                    ('Бек','Койшыман','2003-12-01','+77010066778'),
                                                                    ('Абуxанифа','Муратов','2004-09-14','+77070022334'),
                                                                    ('Бекназар','Сарсенбек','2005-05-05','+77080044556'),
                                                                    ('Алихан','Хайруллаев','2004-10-10','+77010033221'),
                                                                    ('Даниал','Ыбырай','2005-02-02','+77070011998');


INSERT INTO doctors (first_name, last_name, specialization, hire_date) VALUES
                                                                           ('Ырысбек','Боранбек','Стоматолог','2023-09-01'),
                                                                           ('Асыл','Бактыгалиулы','Кардиолог','2022-05-15'),
                                                                           ('Толағай','Ускенулы','Терапевт','2021-03-10'),
                                                                           ('Алуа','Сагындык','Невролог','2024-01-20'),
                                                                           ('Нурасыл','Елубек','Офтальмолог','2022-11-11'),
                                                                           ('Дәулетияр','Жанабек','Педиатр','2023-02-28'),
                                                                           ('Мадияр','Толшибай','Дерматолог','2021-07-07'),
                                                                           ('Әуез','Раязов','Уролог','2024-04-04'),
                                                                           ('Арнур','Бәкір','Эндокринолог','2023-06-06');


INSERT INTO services (service_name, price) VALUES
                                               ('Чистка зубов',8000),
                                               ('Консультация кардиолога',12000),
                                               ('Проверка зрения',6000),
                                               ('Анализ крови общий',4000),
                                               ('Консультация невролога',11000),
                                               ('Осмотр дерматолога',7000);


INSERT INTO appointments (patient_id, doctor_id, service_id, appointment_date) VALUES
                                                                                   (1,1,1,'2025-01-05 10:00:00'),
                                                                                   (2,2,2,'2025-01-06 11:00:00'),
                                                                                   (3,5,3,'2025-01-07 09:30:00'),
                                                                                   (4,4,5,'2025-01-08 14:00:00'),
                                                                                   (5,7,6,'2025-01-09 15:00:00'),
                                                                                   (6,3,4,'2025-01-10 13:00:00');


INSERT INTO payments (appointment_id, amount, payment_date) VALUES
                                                                (1,8000,'2025-01-05'),
                                                                (2,12000,'2025-01-06'),
                                                                (3,6000,'2025-01-07'),
                                                                (4,11000,'2025-01-08'),
                                                                (5,7000,'2025-01-09'),
                                                                (6,4000,'2025-01-10');