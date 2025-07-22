-- Step 0: Create database if it does not exist
CREATE DATABASE IF NOT EXISTS employee_directory;
USE employee_directory;

-- Step 1: Create tables in DB
CREATE TABLE `employee` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `first_name` varchar(45) DEFAULT NULL,
                            `last_name` varchar(45) DEFAULT NULL,
                            `email` varchar(45) DEFAULT NULL,
                            `employee_code` varchar(20) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
                         `email` varchar(50) NOT NULL,
                         `password` varchar(68) NOT NULL,
                         `fullname` varchar(50) NOT NULL,
                         `enabled` tinyint NOT NULL,
                         PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `roles` (
                         `email` varchar(50) NOT NULL,
                         `role` varchar(50) NOT NULL,
                         UNIQUE KEY `authorities5_idx_1` (`email`,`role`),
                         CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`email`) REFERENCES `users` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Step 2: Insert data into tables
INSERT INTO `employee` (`first_name`, `last_name`, `email`, `employee_code`)
VALUES
    ('Leslie','Andrews','leslie@test.com','EMP-0001'),
    ('Luis','Delzo','ldelzo@test.com','EMP-0002'),
    ('Lionel','Messi','leo_messi@test.com','EMP-0003');


INSERT INTO `users`
VALUES
    ('ldelzo.e@gmail.com','$2a$12$Mtj59vtNkn528mK/x1YyoOIg1fwtHUUuVbWyEY2ed0mLM55m1li5O','Luis Delzo',1),
    ('pepe_v@gmail.com','$2a$12$Mtj59vtNkn528mK/x1YyoOIg1fwtHUUuVbWyEY2ed0mLM55cm1li5O','Pepe Vazques',1);

INSERT INTO `roles`
VALUES
    ('ldelzo.e@gmail.com','EMPLOYEE'),
    ('ldelzo.e@gmail.com','ADMIN'),
    ('pepe_v@gmail.com','MANAGER'),
    ('pepe_v@gmail.com','EMPLOYEE');