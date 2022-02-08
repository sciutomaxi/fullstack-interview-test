### Requerimientos para el Build

* Java 1.8 o superior
* Docker
* node.js

1-Levantar la base de datos mysql con docker-compose

```sh
$ cd docker/mysql
$ docker-compose up
```

2-Levantar la api resful backend (spring boot + mysql + swagger)

```sh
$ cd backend/
$ mvn spring-boot:run 
```
La applicacion corre en [http://localhost:8080/](http://localhost:8080/) . En la carpeta backend/postman se encuentra una suite con endpoint para probar.

Agregamos swagger para poder ver la documentacion de la api [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

3-Se pueden correr algunos test
```sh
$ mvn clean test
```

4- Para levantar la aplicacion de front (ReactJS + PrimeReact + node v16.13.2 + npm v8.1.2)
```sh
cd ../front-react/
$ npm install
$ npm start
```

La aplicacion corre en  [http://localhost:3000](http://localhost:3000).

