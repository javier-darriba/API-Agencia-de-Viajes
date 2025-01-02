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
- Representa una reserva con las propiedades identificador, nombre, dni e identificadores del hotel y vuelo.

### 2. **Hotel**
- Representa un hotel contiene las propiedades identificador, nombre, categoría, precio y disponibilidad.

### 3. **Vuelo**
- Representa un vuelo con las propiedades identificador, compañía, fecha, precio y plazas disponilbes.

## Servicios

### **Servicio de Hoteles**

Este microservicio proporciona funcionalidades para gestionar los hoteles.

#### Funcionalidades
1. **Listar hoteles disponibles**
2. **Buscar un hotel por nombre**
3. **Obtener identificador del hotel por nombre**
4. **Comprobar si un hotel existe por su identificador**
5. **Comprobar si un hotel existe por su nombre**

#### Endpoints
**Puerto 8080**
1. **Listar hoteles disponibles**
   - **URL**: `/api/hoteles/disponibles`
   - **Método**: `GET`

2. **Buscar hotel por nombre**
   - **URL**: `/api/hoteles/{nombre}`
   - **Método**: `GET`

3. **Obtener identificador del hotel por nombre**
   - **URL**: `/api/hoteles/identificadores/{nombre}`
   - **Método**: `GET`

4. **Comprobar existencia de hotel por identificador**
   - **URL**: `/api/hoteles/existe/{idHotel}`
   - **Método**: `GET`

5. **Comprobar si un hotel existe por su nombre**
   - **URL**: `/api/hoteles/existe/nombre/{nombreHotel}`
   - **Método**: `GET`

### **Servicio de Vuelos**

Este microservicio proporciona funcionalidades para gestionar los vuelos.

#### Funcionalidades
1. **Listar vuelos con suficientes plazas disponibles**
2. **Actualizar las plazas disponibles de un vuelo**
3. **Comprobar si un vuelo existe por su identificador**

#### Endpoints
**Puerto 9090**
1. **Listar vuelos con suficientes plazas disponibles*
   - **URL**: `/api/vuelos/disponibles/{plazas}`
   - **Método**: `GET`
  
2. **Actualizar las plazas disponibles de un vuelo**
   - **URL**: `/api/vuelos/reservar/{idVuelo}/{plazasReservadas}`
   - **Método**: `PUT`

3. **Comprobar existencia de vuelo por identificador**
   - **URL**: `/api/vuelos/existe/{idVuelo}`
   - **Método**: `GET`

### **Servicio de Reservas**

Este microservicio gestiona las reservas de vuelos y hoteles. Interactúa con los microservicios de **hoteles** y **vuelos** para comprobar que se puedan realizar dichas operaciones y actualizar la información de esas entidades tras la reserva.

#### Funcionalidades
1. **Crear una nueva reserva**:
2. **Obtener reservas por nombre de hotel**:

#### Endpoints
**Puerto 9000**
1. **Crear nueva reserva**
   - **URL**: `/api/reservas`
   - **Método**: `POST`

2. **Obtener reservas por nombre de hotel**
   - **URL**: `/api/reservas/hoteles/{nombreHotel}`
   - **Método**: `GET`

## Estado del Proyecto
- **Borrador(02/01/2025)**: Pendiente completar tests unitarios.

## LICENCIA

Este proyecto está bajo la GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007 - ver el archivo LICENSE para más detalles.
