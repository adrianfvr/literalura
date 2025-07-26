# 📚 Literalura

**Literalura** es una aplicación de consola desarrollada como desafío de práctica. Permite consultar información de libros usando la API de [Gutendex](https://gutendex.com/), almacenar resultados en una base de datos PostgreSQL y explorarlos mediante un menú interactivo.

---

## 🚀 Funcionalidades

A través de un menú en consola, puedes:

1. 🔍 **Buscar un libro y guardarlo**  
   Ingresa el nombre de un libro, la aplicación consulta la API de Gutendex y guarda la información seleccionada en la base de datos.

2. 📚 **Listar libros guardados**  
   Muestra todos los libros previamente almacenados.

3. ✍️ **Listar autores guardados**  
   Extrae y muestra todos los autores registrados a partir de los libros guardados.

4. 📅 **Buscar autores vivos en una fecha**  
   Ingresa una fecha y obtiene los autores que estaban vivos en esa fecha (según sus fechas de nacimiento y fallecimiento).

5. 🌐 **Buscar libros por idioma**  
   Filtra los libros guardados mostrando solo aquellos que están escritos en un idioma específico.

---

## ⚙️ Tecnologías usadas

- **Java** 17
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**

---

## 📦 Instalación y ejecución

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/adrianfvr/literalura.git
   cd literalura
   ```

2. **Configurar la base de datos**

   - Asegúrate de tener PostgreSQL corriendo.
   - Crea una base de datos llamada `literalura` o edita `application.properties` con tus credenciales:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5050/literalura
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     ```

3. **Compilar y correr**

   ```bash
   ./mvnw spring-boot:run
   ```

   o

   ```bash
   mvn spring-boot:run
   ```

---

## 🗂️ Estructura del proyecto

- `src/main/java` — Código fuente principal (controladores, servicios, modelos, repositorios)
- `src/main/resources` — Archivos de configuración (`application.properties`)

---

## ✅ Estado del proyecto

✔️ Desafío completado como parte de un ejercicio práctico.  
💡 Puedes usarlo como base para ampliar funcionalidades como autenticación, front-end, u otros filtros de búsqueda.

---

## ✒️ Autor

**Adrian Felipe Vargas Rivera**  
[Repositorio](https://github.com/adrianfvr)
