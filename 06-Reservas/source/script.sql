USE viajes;

DROP TABLE IF EXISTS `viajes`.`reservas;

CREATE TABLE `viajes`.`reservas` (
`id_reseva` INT NOT NULL AUTO_INCREMENT,
`nombre_cliente` VARCHAR(45) NOT NULL,
`dni` VARCHAR(45) NOT NULL,
`id_hotel` INT NOT NULL,
`id_vuelo` INT NOT NULL,
  PRIMARY KEY (`id_reseva`));
  
INSERT INTO reservas (id_reseva, nombre_cliente, dni, id_hotel, id_vuelo) VALUES 
(1, 'Juan Pérez', '12345678A', 1, 2),
(2, 'María López', '87654321B', 3, 1),
(3, 'Carlos Sánchez', '11223344C', 2, 4),
(4, 'Ana García', '44332211D', 4, 3),
(5, 'Lucía Fernández', '55667788E', 1, 5);