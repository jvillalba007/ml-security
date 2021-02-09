# ml-security
Challenge Seguridad Informática

| Task | URL | Method | Response code | Response |
|:----:|:---:|:------:|:-------------:|:--------:|
| Obtener Ips TOR de Fuentes Externas| /listadoips | GET | 200 | Listado en Json de las Ips Obtenidas|
| Agregar Ip a la Base de Datos | /agregarip | POST | 201 | Se agrega Ip a la Base de Datos y se retorna un 201 o en caso de error un 500 | 
| Obtener Ips TOR de Fuentes Externas y se filtra con las ip de la Base de Datos | /listadoips/filtrado | GET | 200 | Listado en Json de las Ips Filtradas |

**Resolución realizada en Java 11 con los frameworks Spring Boot, Sprint JPA / Hibernate y Scraping Jsoup.**

# Descripción de las Clases

**spring/controller/ControllerSpring.java**\
Esta Clase de Java se encarga de Gestionar todas las peticiones HTTP / API REST realizadas a los endpoints.

**spring/model/IpTor.java**\
Esta Clase de Java es utilizada principalmente por Spring JPA en los repositorios tiene los atributos necesarios para obtener la información o guardar entidades. Como extra tiene una método que dada un conjunto de ips y el repositorio filtra las ip de la base de datos.

**spring/repository/IpTorRepository.java**\
Repositorio de Spring JPA una interfaz que implementa las funcionalidades CRUD hacia la Base de Datos.

**spring/scraping/Scraping_all_IPSTor.java**\
Lógica a alto nivel que dada un array de Website, los recorre y les solicita que agregue su list de la ip disponibles en esa fuente. Cada Website implementa una interface agregarIPsFuenteExterna(ArrayList<String> listadoIps), ya que cada sitio implementara como hara el scraping particular y lo agregara al array que le dio Scraping_all_IPSTor.
  
**spring/MLWebApplication.java**\
Clase main que inicia el Spring Boot.

**ml-security/src/main/resources/application.properties**\
Datos de la Base de Datos hosteada en Cloud SQL en Google Cloud Platform utilizada por Spring JPA / Hibernate.

# Despliegue en Google Cloud
**Base de Datos** MySQL 5.7, desplegada en **Google Cloud SQL**\
Tipo de Instancia: db-f1-micro (1 vcpu, 614mb RAM, 10 GB HDD), Zona: us-east1-c

**Aplicación Desplegada** en **Google Cloud Run** conectada a este **Repositorio GitHub**\
[https://ml-security-hmp5yfvfla-ue.a.run.app](https://ml-security-hmp5yfvfla-ue.a.run.app)
