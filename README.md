# API REST Agencia de Viajes

Este repositorio contiene tres microservicios desarrollados en **Spring Boot** para gestionar reservas de vuelos, hoteles y la interacción entre ambos. La aplicación utiliza una base de datos MySQL. La funcionalidad de los microservicios se expone a través de una API REST que se puede consumir con herramientas como Swagger o Postman.

## Características Principales
- **Arquitectura basada en microservicios**.
- **Interacción entre los microservicios de hoteles, vuelos y reservas**.
- **Base de datos MySQL**.
- **Exposición de endpoints para interactuar con las entidades de vuelos, hoteles y reservas**.

## Tecnologías Utilizadas
- **Spring Boot**: Infraestructura para microservicios eficiente.
- **MySQL**: Base de datos relacional.
- **Swagger**: Documentación y visualización de la API RESTful.
- **JUnit**: Marco para pruebas unitarias en Java.
- **RestTemplate**: Para la comunicación entre microservicios.
  
## Entidades
### 1. **Reserva**
- Representa una reserva con las propiedades **idReserva**, **nombreCliente**, **dni**, **idHotel** e **idVuelo**.

### 2. **Hotel**
- Representa un hotel con las propiedades **idHotel**, **nombre**, **disponible** (booleano), etc.

### 3. **Vuelo**
- Representa un vuelo con las propiedades **idVuelo**, **origen**, **destino**, **fecha**, **plazasDisponibles**, etc.

## Servicios

### **Servicio de Hoteles**

Este microservicio proporciona funcionalidades para gestionar los hoteles.

#### Funcionalidades
1. **Listar hoteles disponibles**
2. **Buscar un hotel por nombre**
3. **Comprobar si un hotel existe por su identificador o nombre**

#### Endpoints
**Puerto 8080**
1. **Listar hoteles disponibles**
   - **URL**: `/hoteles`
   - **Método**: `GET`

2. **Buscar hotel por nombre**
   - **URL**: `/hoteles/nombre/{nombre}`
   - **Método**: `GET`

3. **Comprobar existencia de hotel por identificador**
   - **URL**: `/hoteles/{idHotel}/existe`
   - **Método**: `GET`

### **Servicio de Vuelos**

Este microservicio proporciona funcionalidades para gestionar los vuelos.

#### Funcionalidades
1. **Listar vuelos disponibles**
2. **Comprobar si un vuelo existe por su identificador**

#### Endpoints
**Puerto 9090**
1. **Listar vuelos disponibles**
   - **URL**: `/vuelos`
   - **Método**: `GET`

2. **Comprobar existencia de vuelo por identificador**
   - **URL**: `/vuelos/{idVuelo}/existe`
   - **Método**: `GET`

### **Servicio de Reservas**

Este microservicio gestiona las reservas de vuelos y hoteles. Interactúa con los microservicios de **hoteles** y **vuelos** para comprobar la existencia de los mismos antes de proceder con la reserva.

#### Funcionalidades
1. **Crear una nueva reserva**: Verifica que el vuelo y el hotel existen antes de realizar la reserva.
2. **Obtener reservas por nombre de hotel**: Devuelve todas las reservas asociadas a un hotel específico.

#### Endpoints
**Puerto 8081**
1. **Crear nueva reserva**
   - **URL**: `/reservas`
   - **Método**: `POST`

2. **Obtener reservas por nombre de hotel**
   - **URL**: `/reservas/hotel/{nombreHotel}`
   - **Método**: `GET`

## Estado del Proyecto
- **Borrador**: Funcionalidades básicas implementadas.
- **Implementado**:
  - Alta de reserva.
  - Listado de hoteles y vuelos.
  - Validación de existencia de vuelos y hoteles.

## Cómo Ejecutar

### 1. Clonar el repositorio.
### 2. Acceder al directorio del proyecto.
### 3. Crear la base de datos en MySQL.
  Para crear las tablas e introducirle datos puedes utilizar los script.sql que hay en el directorio /source de cada microservicio.
### 4. Ejecutar los microservicios.
  Puedes ejecutar los microservicios de manera independiente desde la terminal con el siguiente comando para cada uno:
  ./mvnw spring-boot:run
### 5. Acceder a la API a través de sus url.

## Cómo Ejecutar

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

### 1. Haz un fork del repositorio.
### 2. Crea una nueva rama: git checkout -b feature/nueva-funcionalidad.
### 3. Realiza tus cambios y haz un commit.
### 4. Haz un push a tu rama: git push origin feature/nueva-funcionalidad.
### 5. Abre un Pull Request.

## Cómo Ejecutar

Este proyecto está bajo la GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007 - ver el archivo LICENSE para más detalles.
