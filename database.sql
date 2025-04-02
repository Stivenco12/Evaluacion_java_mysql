CREATE DATABASE my_hospital;
use my_hospital;

CREATE Table paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    apellido VARCHAR(255) ,
    fecha_nacimiento VARCHAR(255),
    dirección VARCHAR(255) UNIQUE,
    teléfono VARCHAR(255) UNIQUE,
    email VARCHAR(255) UNIQUE
);
CREATE Table Especialidad(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255)
);
CREATE Table Médico(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    horario_inicio VARCHAR(255),
    horario_fin VARCHAR(255),
    Especialidad_id INT,
    CONSTRAINT FK_Medico_Paciente FOREIGN KEY (Especialidad_id) REFERENCES Especialidad(id)
);
CREATE Table Cita (
    Cita_id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    medico_id int,
    FOREIGN KEY (medico_id) REFERENCES Médico(id),
    fecha_hora VARCHAR(255),
    estado VARCHAR(255)
);
CREATE TABLE User (
    Id_user INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50),
    type VARCHAR(50)
);