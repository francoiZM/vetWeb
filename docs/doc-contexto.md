# Portada
- Título: VetWeb — Informe del Proyecto
- Asignatura: Desarrollo Web 2
- Docente: [Nombre del docente]
- Estudiante: [Tu nombre]

# Introducción
Contexto: Aplicación web para uso veterinario enfocada en la gestión de pacientes, citas, historial clínico y administración básica de la clínica.  
Herramientas utilizadas: Spring Boot, Thymeleaf, Java, Maven/Gradle, HTML/CSS, (opcional) H2/MySQL para la base de datos, Git para control de versiones.

# Desarrollo (Capturas de Pantalla)
A continuación se listan las vistas principales y dónde colocar las capturas. Guarde las imágenes en: src/main/resources/static/images/ y use la ruta relativa.

1. Listado de pacientes
   - Archivo de imagen sugerido: src/main/resources/static/images/listado_pacientes.png
   - Descripción: vista con la lista de pacientes, botones para ver/editar/eliminar.

   ![Listado de pacientes](src/main/resources/static/images/listado_pacientes.png)

2. Detalle de paciente
   - Archivo de imagen sugerido: src/main/resources/static/images/detalle_paciente.png
   - Descripción: página de detalle que muestra información del paciente (nombre, especie, raza, fecha de nacimiento, últimas citas).

   ![Detalle de paciente](src/main/resources/static/images/detalle_paciente.png)

3. Formulario de edición/creación de paciente
   - Archivo de imagen sugerido: src/main/resources/static/images/form_paciente.png
   - Descripción: formulario con campos para nombre, especie, raza, fecha de nacimiento, peso, etc.

   ![Formulario paciente](src/main/resources/static/images/form_paciente.png)

4. Gestión de citas
   - Archivo de imagen sugerido: src/main/resources/static/images/listado_citas.png
   - Descripción: vista para crear y listar citas, seleccionar paciente, fecha y motivo.

   ![Listado citas](src/main/resources/static/images/listado_citas.png)

Notas sobre capturas:
- Tome las capturas desde el navegador con la aplicación en ejecución.
- Asegúrese de que las rutas de las imágenes en este documento coincidan con las ubicaciones reales dentro del proyecto.

# Conclusión
Reflexión breve: Durante la configuración del proyecto los retos principales fueron alinear dependencias de Spring Boot y configurar Thymeleaf correctamente (rutas de templates y recursos estáticos). En la creación de vistas se presentaron desafíos en diseño responsivo y en el enlace entre formularios y controladores (binding de datos y validación). Superar estos desafíos requirió revisar la documentación oficial, ajustar configuraciones de recursos estáticos y probar iterativamente las plantillas Thymeleaf.

