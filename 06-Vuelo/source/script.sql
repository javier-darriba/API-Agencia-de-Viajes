USE viajes;

DROP TABLE IF EXISTS `viajes`.`vuelos`;

CREATE TABLE `viajes`.`vuelos` (
`id_vuelo` INT NOT NULL AUTO_INCREMENT,
`compania` VARCHAR(45) NOT NULL,
`fecha_vuelo` DATETIME NOT NULL,
`precio` DECIMAL(10,2) NOT NULL,
`plazas_disponibles` INT NOT NULL,
PRIMARY KEY (`id_vuelo`));
  
INSERT INTO vuelos (id_vuelo, compania, fecha_vuelo, precio, plazas_disponibles) VALUES 
(1, 'VUELING', '2024-01-15', 120.50, 150),
(2, 'IBERIA', '2024-01-20', 200.75, 100),
(3, 'RYANAIR', '2024-02-10', 90.00, 50),
(4, 'VUELING', '2024-02-25', 110.30, 75),
(5, 'EASYJET', '2024-03-05', 300.00, 30);
