# ml-security
Challenge Seguridad Informática

| Task | URL | Method | Response code | Response |
|:----:|:---:|:------:|:-------------:|:--------:|
| Obtener Ips TOR de Fuentes Externas| /listadoips | GET | 200 | Listado en Json de las Ips Obtenidas|
| Agregaer Ip a la Base de Datos | /agregarip | POST | 201 | Se agrega Ip a la Base de Datos y se retorna un 201 o en caso de error un 500 | 
| Obtener Ips TOR de Fuentes Externas y se filtra con las ip de la Base de Datos | /listadoips/filtrado | GET | 200 | Listado en Json de las Ips Filtradas |

Desafio resuelto realizado en Java 11 con los frameworks Spring Boot, Sprint JPA / Hibernate y Scraping Jsoup.

Decisiones / Estrategias tomadas
