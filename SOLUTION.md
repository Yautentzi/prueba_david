# Solución — Examen Práctico

# BACKEND 

## Qué se implementó
- Documentación de los endpoints en el controlador Client: GET all y POST save
- Nuevo endpoint para eliminar Clientes: DELETE /api/clients/{id}
- Documentación del endpoint DELETE delele

## Cómo se implementó
- Implementación de la documentación siguiendo las anotaciones swagger.
- Integración del nuevo endpoint con el método DELETE. Implementación del Service y Repository siguiendo la lógica de negocio del proyecto

## Cambios en base de datos / configuración
- Dependencia eliminada para sqlserver
- Dependencia agregada para h2database
- Definición de las propiedades para la Base de Datos y conexión con H2
- Integración de la lista de columnas en el INSERT del Repositorio
- Creación de los archivos schema.sql y data.sql para la definición de la tabla Clients y datos de prueba (INSERT)

## Dificultades encontradas
- Error en el pom.xml: la dependencia de lombok estaba mal escrita

## Mejoras que podrían aplicarse
- Eliminar el Warning que aparece relacionado al Starter Validation
- Intregación con una Base de Datos con presistencia: MySQL o PostgreSQL
- Integración de la librería JPA para el manejo de las transacciones (dependencia incuida)
- Java 17: Integración de las clases de tipo record para los DTO's
- Java 17: Uso de stream para el manejo de las colecciones


# FRONTEND 

## Qué se implementó
- Al intentar guardar un Cliente, mostrar mensaje de error indicando que faltan campos obligatorios
- Al intentar guardar un Cliente, marcar en rojo los campos marcados como requeridos
- Al guardar un Cliente, limpiar el formlulario de forma automática
- Nuevo campo Email en el formulario
- Validacion en el campo Email para que se cumpla el formato: usuario@dominio.com
- Validaicon en el campo Phone para que se cumpla el formato: sólo dígitos, sin letras ni caracteres especiales
- Implementación de la acción DELETE, acción en el botón y llamada al endpoint DELETE del API

## Cómo se implementó
- Integración de un form: clientForm
- Cambio en el tipo de boton Save a submit
- Integración de los mensajes mat-error
- Validación del formulario en el método onSave
- Nueva función llamada clearForm para hacer el resetForm de los campos y se limpien, también cambia el estado del formulario
- Nueva función onDelete para hacer la llamada al endpoint correspondiente

## Cambios en base de datos / configuración

## Dificultades encontradas

## Mejoras que podrían aplicarse
