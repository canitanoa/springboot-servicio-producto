# springboot-servicio-producto
## Servicio REST creado con SpringBoot poblado con bbdd H2 y documentado con Swagger

Este proyecto es un ejemplo de construcción de una API Rest creada con Springboot 
La información expuesta por la API es generada en memoria por el gestor H2
La API se Auto-documenta con las dependencias de Swagger

> Para implementar Swagger en la API:
  - Insertar las dependencias "springfox-swagger2" y "springfox-swagger-ui" en el POM.xml
  - Crear la clase SwaggerConfig.java

> Configuraciones:
- Impl: Importar como proyecto Maven
- URL: http://localhost:8001/swagger-ui.html
- Postman: /springboot-servicio-producto/src/main/resources/productos.postman_collection.json


> Subir Proyecto a GitHub:
- git init
- git add .
- git commit -m "first commit"
- git remote add origin https://github.com/canitanoa/springboot-servicio-producto.git
- git push -u origin master