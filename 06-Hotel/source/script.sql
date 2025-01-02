USE viajes;

DROP TABLE IF EXISTS `viajes`.`hoteles`;

CREATE TABLE `viajes`.`hoteles` (
`id_hotel` INT NOT NULL AUTO_INCREMENT,
`nombre` VARCHAR(45) NOT NULL,
`categoria` VARCHAR(45) NOT NULL,
`precio` DECIMAL(10,2) NOT NULL,
`disponible` TINYINT NOT NULL,
PRIMARY KEY (`id_hotel`));

INSERT INTO hoteles (id_hotel, nombre, categoria, precio, disponible) VALUES 
(1, 'Hotel_Paraíso', 'CINCO_ESTRELLAS', 250.50, true),
(2, 'Hotel Luna', 'CUATRO_ESTRELLAS', 150.75, true),
(3, 'Hotel Sol', 'TRES_ESTRELLAS', 100.00, false),
(4, 'Hotel Estrella', 'DOS_ESTRELLAS', 75.25, true),
(5, 'Hotel Cielo', 'UNA_ESTRELLA', 50.00, false);