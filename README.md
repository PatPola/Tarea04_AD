# Tarea04_AD
# Aplicación Java con Hibernate-JPA - Gestión de Inventario -Tarea 04 de Acceso a Datos de DAM

## Descripción  
Esta aplicación de consola en Java, desarrollada con Eclipse y Hibernate-JPA, gestiona un inventario de equipos informáticos en un centro educativo. La base de datos **MySQL** se generará automáticamente a partir de las entidades de la aplicación.

## Estructura de la Base de Datos  

### Entidades  

#### Aula  
- **id** (Long) - Identificador único del aula  
- **nombre** (String) - Nombre del aula  
- **equipos** (Lista<Equipo>) - Relación uno a muchos con la entidad Equipo  

#### Equipo  
- **id** (Long) - Identificador único del equipo  
- **numSerie** (String) - Número de serie del equipo  
- **fechaAlta** (Date) - Fecha de alta en el inventario  
- **características** (String) - Descripción de las características del equipo  
- **aula** (Aula) - Relación muchos a uno con la entidad Aula  

## Relación entre Entidades  
- Un **Aula** puede tener múltiples **Equipos** (uno a muchos).  
- Un **Equipo** pertenece a un único **Aula** (muchos a uno).  

## Funcionalidades  

1. **Listado de aulas y sus equipos**  
   - Muestra todas las aulas y, dentro de cada una, los equipos asociados.  

2. **Insertar un aula**  
   - Permite agregar un aula nueva.  

3. **Borrar un aula**  
   - Solicita el ID del aula y la elimina si existe. Si no, vuelve a pedir el ID.  

4. **Modificar un aula**  
   - Permite cambiar el nombre de un aula existente tras solicitar su ID.  

5. **Insertar un equipo y añadirlo a un aula**  
   - Solicita los datos del equipo y el ID del aula a la que se añadirá.  
   - Muestra todas las aulas disponibles antes de seleccionar una.  

6. **Buscar un equipo por su número de serie**  
   - Permite buscar un equipo por su número de serie y muestra sus detalles junto con el nombre del aula a la que pertenece.  

7. **Salir**  
   - Termina la ejecución de la aplicación.  

## Tecnologías Utilizadas  
- **Java 11+**  
- **Hibernate-JPA**  
- **MySQL**  
- **Eclipse IDE**  

## Instalación y Uso  
1. Clonar este repositorio o descargar el código fuente.  
2. Configurar una base de datos MySQL llamada `Inventario`.  
3. Ajustar el archivo de configuración de Hibernate (`hibernate.cfg.xml`) con las credenciales de la base de datos.  
4. Ejecutar la aplicación desde la consola utilizando Eclipse.  

---



