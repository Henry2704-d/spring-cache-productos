# Sistema de Comparación de Rendimiento con Caché

Aplicación desarrollada utilizando **Java, Spring Boot y SQL Server**, cuyo objetivo es comparar los tiempos de acceso a una base de datos mediante consultas tradicionales y consultas optimizadas con memoria caché.

Este proyecto fue realizado como actividad práctica para reforzar los conocimientos sobre Java y Spring Framework, así como comprender la importancia de la caché en el rendimiento de las aplicaciones.

## 1. Tecnologías utilizadas

- **Java 21:** lenguaje de programación utilizado para el backend.
- **Spring Boot 4.0.8:** framework para desarrollar la aplicación.
- **Spring Data JPA / Hibernate:** acceso a los registros de SQL Server.
- **SQL Server:** sistema de gestión de base de datos.
- **Spring Cache:** integración del mecanismo de caché.
- **Caffeine:** almacenamiento temporal de los resultados en memoria.
- **HTML, CSS y JavaScript:** desarrollo del frontend.
- **IntelliJ IDEA:** entorno de desarrollo.
- **Maven:** gestión de dependencias.

## 2. Desarrollo del proyecto

### Paso 1. Creación de la base de datos

Se creó una base de datos llamada `SistemaProductosCache` en Microsoft SQL Server.

Dentro se implementó una tabla denominada `Producto`, con los siguientes campos:

| Campo | Descripción |
|---|---|
| id_producto | Identificador único del producto |
| nombre | Nombre del producto |
| categoria | Categoría a la que pertenece |
| precio | Precio del producto |
| stock | Cantidad disponible |

Se generaron automáticamente **10.000 registros** mediante instrucciones SQL, con el propósito de disponer de una cantidad considerable de información para realizar las pruebas de rendimiento.

### Paso 2. Desarrollo del backend

Se creó un proyecto con Spring Boot 4.0.8 y Java 21 utilizando Spring Initializr.

Se configuró la conexión con SQL Server mediante el controlador JDBC y el archivo `application.properties`.

Posteriormente se desarrollaron los siguientes componentes:

- `Producto.java`: representa la tabla de productos mediante una entidad JPA.
- `ProductoRepository.java`: permite acceder a los registros de la base de datos.
- `ProductoService.java`: contiene los métodos de consulta con caché y sin caché.
- `ProductoController.java`: expone los endpoints REST de la aplicación.

Se comprobó que el backend podía acceder correctamente a los 10.000 registros.

### Paso 3. Implementación de caché

Se implementó un sistema de almacenamiento temporal utilizando Spring Cache y Caffeine.

Se desarrollaron dos métodos de consulta:

**Consulta sin caché:** utiliza `findAll()` para recuperar los productos directamente desde SQL Server en cada solicitud.

**Consulta con caché:** utiliza la anotación `@Cacheable("productos")` para almacenar el resultado de la primera consulta y reutilizarlo posteriormente.

Para medir el tiempo de ejecución se utilizó `System.nanoTime()`, convirtiendo los resultados a milisegundos.

De esta manera, la aplicación permite observar la diferencia entre consultar directamente la base de datos y recuperar información previamente almacenada en memoria.

### Paso 4. Desarrollo del frontend

Una vez desarrollado el backend, se utilizó inteligencia artificial como apoyo para generar una interfaz web utilizando HTML, CSS y JavaScript.

La interfaz incluye:

- Visualización de la cantidad de productos registrados.
- Botón para ejecutar consultas sin caché.
- Botón para ejecutar consultas con caché.
- Comparación gráfica de los tiempos.
- Tabla con información de los primeros 20 productos.

El frontend consume los endpoints REST mediante JavaScript y presenta los resultados obtenidos.

### Paso 5. Configuración de CORS

Durante el desarrollo, el backend se ejecuta en el puerto `8080` y el frontend en el puerto `5500`.

Debido a que utilizan diferentes orígenes, se configuró CORS mediante la anotación `@CrossOrigin` en el controlador de Spring Boot.

Esto permite la comunicación entre ambas partes de la aplicación durante las pruebas locales.

## 3. Endpoints disponibles

| Endpoint | Función |
|---|---|
| `/api/productos/cantidad` | Obtiene la cantidad total de productos |
| `/api/productos/todos` | Recupera todos los productos |
| `/api/productos/sin-cache` | Ejecuta y mide una consulta sin caché |
| `/api/productos/con-cache` | Ejecuta y mide una consulta con caché |

## 4. Ejecución del proyecto

1. Tener instalado Java 21, SQL Server y Maven.
2. Crear la base de datos `SistemaProductosCache` y cargar los 10.000 registros.
3. Configurar la conexión JDBC en `application.properties`, ajustando el servidor y puerto según el entorno.
4. Establecer las variables de entorno `DB_USERNAME` y `DB_PASSWORD` con las credenciales locales de SQL Server.
5. Ejecutar `ProductosCacheApplication.java` desde IntelliJ IDEA.
6. Abrir una terminal dentro de la carpeta `frontend`.
7. Ejecutar el siguiente comando, teniendo Python instalado:

   `py -m http.server 5500 --bind 127.0.0.1`

8. Abrir en el navegador:

   `http://127.0.0.1:5500/index.html`

## 5. Comparación de rendimiento

La aplicación permite ejecutar las consultas repetidamente y observar sus tiempos de respuesta.

La primera consulta con caché necesita obtener los registros de SQL Server y almacenarlos en memoria. Las consultas posteriores pueden reutilizar esos datos, reduciendo el tiempo necesario para recuperarlos.

Para realizar una comparación más representativa, se recomienda ejecutar varias consultas, descartar la primera carga de caché y calcular los tiempos promedio.

Los resultados pueden variar según el equipo, la configuración de SQL Server y la carga de trabajo.

## 6. Resultados y conclusiones

Durante el desarrollo se consiguió:

- Establecer una conexión funcional entre Spring Boot y SQL Server.
- Consultar correctamente los 10.000 registros de productos.
- Implementar consultas con y sin caché mediante Caffeine.
- Medir los tiempos de acceso a los datos.
- Desarrollar un frontend para visualizar los resultados.
- Resolver la comunicación entre frontend y backend mediante CORS.

**Conclusión:** la implementación de caché permite reducir el trabajo de consulta repetitiva a la base de datos al reutilizar resultados almacenados en memoria. Esto puede mejorar el rendimiento de las aplicaciones, especialmente cuando se consultan frecuentemente datos que no cambian constantemente.

## 7. Lecciones aprendidas

1. Se aprendió a conectar una aplicación Java desarrollada con Spring Boot a una base de datos SQL Server mediante JDBC y Spring Data JPA.

2. Se comprendió el funcionamiento de la caché y su utilidad para evitar consultas repetitivas a la base de datos, utilizando Spring Cache y Caffeine.

3. Se reforzaron los conocimientos sobre la comunicación entre frontend y backend mediante APIs REST, incluyendo la configuración de CORS para permitir solicitudes entre diferentes orígenes.

## 8. Uso de inteligencia artificial

Se utilizó inteligencia artificial como herramienta de apoyo para generar el frontend, integrar las peticiones a los endpoints y orientar la configuración de CORS.

La aplicación fue comprobada mediante su ejecución local y la consulta de los registros almacenados en SQL Server.