# Laboratorio 7 - APIs con Spring Boot

El proyecto contiene tres ejercicios independientes dentro de la misma aplicación:

- Administración de profesores.
- Administración de proveedores.
- Administración de empleados.

Los datos se almacenan en listas en memoria, por lo que se reinician al detener la aplicación.

## Ejecutar el proyecto

Requisitos:

- Java 25.
- Maven 3.9 o superior.

Desde la raíz del proyecto:

```powershell
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`.

## API Key

Todos los endpoints bajo `/api/` requieren el encabezado:

```http
Authorization: Bearer laboratorio7-key
```

La clave puede reemplazarse mediante la variable de entorno `APP_SECURITY_API_KEY`:

```powershell
$env:APP_SECURITY_API_KEY = "otra-clave"
mvn spring-boot:run
```

## Endpoints

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/profesores` | Crear profesor |
| GET | `/api/profesores` | Consultar profesores |
| GET | `/api/profesores?especialidad=Matemática` | Filtrar profesores |
| GET | `/api/profesores/{id}` | Consultar profesor por ID |
| POST | `/api/proveedores` | Crear proveedor |
| GET | `/api/proveedores` | Consultar proveedores |
| GET | `/api/proveedores?categoria=Tecnología` | Filtrar proveedores |
| GET | `/api/proveedores/{id}` | Consultar proveedor por ID |
| POST | `/api/empleados` | Crear empleado |
| GET | `/api/empleados` | Consultar empleados |
| GET | `/api/empleados?especialidad=Desarrollo Backend` | Filtrar empleados |
| GET | `/api/empleados/{id}` | Consultar empleado por ID |

Las creaciones exitosas devuelven `201 Created`. Las consultas devuelven `200 OK`, los datos inválidos `400 Bad Request`, una API Key incorrecta `401 Unauthorized` y un recurso inexistente `404 Not Found`.

## Postman

Importe los siguientes archivos y seleccione el entorno **Laboratorio 7 - Local**:

- `postman/Laboratorio7-APIs.postman_collection.json`
- `postman/Laboratorio7-Local.postman_environment.json`

Ejecute primero la petición **Crear** de cada carpeta. La colección guardará automáticamente el ID recibido para las consultas por ID.

## Pruebas

```powershell
mvn test
```

Cada ejercicio contiene pruebas unitarias para crear, obtener un registro existente y consultar uno inexistente.
