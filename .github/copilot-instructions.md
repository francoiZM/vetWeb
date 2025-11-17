# Copilot Instructions for vetweb-gestor

## Project Overview

This is a Spring Boot web application for veterinary management. It uses Java 17, Maven, Spring Data JPA, Thymeleaf, and MySQL. The main package is `com.vetweb.gestor`.

## Architecture & Key Components

- **Controllers**: Handle HTTP requests. See `src/main/java/com/vetweb/gestor/controller/` for examples (`MascotaController.java`, `UsuarioController.java`).
- **Entities**: JPA entities for domain models. See `src/main/java/com/vetweb/gestor/entity/` (`Mascota.java`, `Usuario.java`).
- **Templates**: Thymeleaf HTML templates in `src/main/resources/templates/`.
- **Static Assets**: CSS and JS in `src/main/resources/static/`.
- **Application Entry**: Main class is `VetwebGestorApplication.java`.
- **Configuration**: Properties in `src/main/resources/application.properties`.

## Developer Workflows

- **Build**: Use Maven wrapper: `./mvnw clean package` (Linux/macOS) or `mvnw.cmd clean package` (Windows).
- **Run**: Start app with `./mvnw spring-boot:run` or `mvnw.cmd spring-boot:run`.
- **Test**: Run tests with `./mvnw test` or `mvnw.cmd test`. Main test class: `VetwebGestorApplicationTests.java`.
- **Database**: Uses MySQL. Connection details in `application.properties`.

## Project-Specific Patterns

- **Form Handling**: Follows Spring MVC and Thymeleaf conventions. See `form-crear.html`, `form-editar.html` for examples.
- **REST Endpoints**: Controllers use `@RestController` or `@Controller` and map routes for CRUD operations.
- **Entity Naming**: Use singular nouns for entities (e.g., `Mascota`, `Usuario`).
- **Service Layer**: SERVICES, DAO
- **Maven Parent Overrides**: The POM disables inheritance of `<license>` and `<developers>` from parent.

## Integration Points

- **Spring Data JPA**: For ORM and database access.
- **Thymeleaf**: For server-side rendering of HTML.
- **MySQL**: As the persistent data store.

## Examples

- To add a new entity, create a Java class in `entity/`, update the corresponding controller, and add/edit templates as needed.
- To add a new page, create a Thymeleaf template and link it in a controller.

## References

- [Spring Boot Docs](https://docs.spring.io/spring-boot/3.5.7/reference/htmlsingle/)
- [Thymeleaf Docs](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
- [Spring Data JPA Docs](https://spring.io/projects/spring-data-jpa)

---

**Update this file if project structure or conventions change.**
