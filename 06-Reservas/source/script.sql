USE viajes;

DROP TABLE IF EXISTS `viajes`.`reservas`;

DROP TABLE IF EXISTS `viajes`.`hoteles`;

DROP TABLE IF EXISTS `viajes`.`vuelos`;

CREATE TABLE `viajes`.`hoteles` (
`id_hotel` INT NOT NULL AUTO_INCREMENT,
`nombre` VARCHAR(45) NOT NULL,
`categoria` VARCHAR(45) NOT NULL,
`precio` DECIMAL(10,2) NOT NULL,
`disponible` TINYINT NOT NULL,
PRIMARY KEY (`id_hotel`));

CREATE TABLE `viajes`.`vuelos` (
`id_vuelo` INT NOT NULL AUTO_INCREMENT,
`compania` VARCHAR(45) NOT NULL,
`fecha_vuelo` DATETIME NOT NULL,
`precio` DECIMAL(10,2) NOT NULL,
`plazas_disponibles` INT NOT NULL,
PRIMARY KEY (`id_vuelo`));

CREATE TABLE `viajes`.`reservas` (
`id_reserva` INT NOT NULL AUTO_INCREMENT,
`nombre_cliente` VARCHAR(45) NOT NULL,
`dni` VARCHAR(45) NOT NULL,
`id_hotel` INT NOT NULL,
`id_vuelo` INT NOT NULL,
  PRIMARY KEY (`id_reserva`));

INSERT INTO hoteles (id_hotel, nombre, categoria, precio, disponible) VALUES 
(1, 'Hotel Paraíso', 'CINCO_ESTRELLAS', 250.50, true),
(2, 'Hotel Luna', 'CUATRO_ESTRELLAS', 150.75, true),
(3, 'Hotel Sol', 'TRES_ESTRELLAS', 100.00, false),
(4, 'Hotel Estrella', 'DOS_ESTRELLAS', 75.25, true),
(5, 'Hotel Cielo', 'UNA_ESTRELLA', 50.00, false);

INSERT INTO vuelos (id_vuelo, compania, fecha_vuelo, precio, plazas_disponibles) VALUES 
(1, 'VUELING', '2024-01-15', 120.50, 150),
(2, 'IBERIA', '2024-01-20', 200.75, 100),
(3, 'RYANAIR', '2024-02-10', 90.00, 50),
(4, 'VUELING', '2024-02-25', 110.30, 75),
(5, 'EASYJET', '2024-03-05', 300.00, 30);
  
INSERT INTO reservas (id_reserva, nombre_cliente, dni, id_hotel, id_vuelo) VALUES 
(1, 'Juan Pérez', '12345678A', 1, 2),
(2, 'María López', '87654321B', 3, 1),
(3, 'Carlos Sánchez', '11223344C', 2, 4),
(4, 'Ana García', '44332211D', 4, 3),
(5, 'Lucía Fernández', '55667788E', 1, 5);