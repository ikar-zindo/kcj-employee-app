-- liquibase formatted sql
-- changeset employee:v1.1.0-data-employee
-- comment adding employee data

--
-- Dumping data for tables
--

--
-- Dumping data for table employees
--

INSERT INTO `employees` (`employee_id`, `last_name`, `first_name`,  `email`, `username`, `password`, `phone_number`, `restaurant_id`, `role`) VALUES
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c1'), 'Davolio', 'Nancy', 'davolio@mail.com', 'nancy', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 1, 'ROLE_USER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c2'), 'Fuller', 'Ewa', 'fuller@mail.com', 'ewa', '$2a$10$rmQi5Z5O1HzHsV.6lM8.D..6gAf5vWQPtYwnlNzwWbfq4Ww8ZMDV.', '+49 123 456 789', 1, 'ROLE_USER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c3'), 'Leverling', 'Janet', 'leverling@mail.com', 'janet', '$2a$10$T4Ti17zkvQMube2OlAcuNepOKNp6QE8vDFk18p51ZwbHNk1jbyOoy', '+49 123 456 789', 1, 'ROLE_USER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c4'), 'Peacock', 'Margaret', 'peacock@mail.com', 'margaret', '$2a$10$T4Ti17zkvQMube2OlAcuNepOKNp6QE8vDFk18p51ZwbHNk1jbyOoy', '+49 123 456 789', 1, 'ROLE_USER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c5'), 'Buchanan', 'Steven', 'buchanan@mail.com', 'steven', '$2a$10$T4Ti17zkvQMube2OlAcuNepOKNp6QE8vDFk18p51ZwbHNk1jbyOoy', '+49 123 456 789', 1, 'ROLE_USER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c6'), 'Suyama', 'Michael', 'suyama@mail.com', 'manager', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 1, 'ROLE_MANAGER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c7'), 'King', 'Robert', 'king@mail.com', 'robert', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 1, 'ROLE_MANAGER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c8'), 'Callahan', 'Laura', 'callahan@mail.com', 'admin', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 1, 'ROLE_ADMIN'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c9'), 'Dodsworth', 'Anne', 'dodsworth@mail.com', 'anne', '$2a$10$rmQi5Z5O1HzHsV.6lM8.D..6gAf5vWQPtYwnlNzwWbfq4Ww8ZMDV.', '+49 123 456 789', 1, 'ROLE_ADMIN'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c0'), 'West', 'Adam', 'west@mail.com', 'adam', '$2a$10$rmQi5Z5O1HzHsV.6lM8.D..6gAf5vWQPtYwnlNzwWbfq4Ww8ZMDV.', '+49 123 456 789', 1, 'ROLE_ADMIN');

COMMIT;