# Alkemy-Challenge-Disney
Api Rest Spring Boot

# Creada con
* Java 8
* Spring Boot 2
* Maven
* JWT

# Como utilizar la aplicación:

Usuario no registrado: solo puede registrarse e iniciar secion

Usuario Registrado y Logueado : puede ver todas las peliculas / series y personajes cargados en base de datos, la app le proporciona un JWT. Este debe ir en los headers de Postman para acceder a los distintos End-Points. Si no coloca dicho JWT no tendrá acceso. El mismo dura 10 horas.

Primero se debe postear un genero, luego se puede cargar la película asociada a dicho genero y por ultimo el personaje.

# End-Points:

* POST localhost:8080/auth/register : persiste un usuario en la base de datos y envía un correo de bienvenida a Alkemy. En el body de la peticion debemos enviar: username y password.

* POST localhost:8080/auth/login : genera un JWT que da acceso a las distintos end-Points de la aplicación. En el body de la peticion debemos enviar: username y password.

* GET http://localhost:8080/characters : Devuelve las imagenes y nombre de todos los personajes cargados en la base de datos. En el body de la peticion No enviamos nada.

* GET http://localhost:8080/characters/{id} : Devuelve todos los datos de un personaje. Incluido los datos de la pelicula asociada y genero al que pertence.
En el path enviamos el id del personaje que deseo buscar.

* GET http://localhost:8080/characters/filter : En el path - params se puede o no filtrar por: Nombre, Edad, peliculaId. En el body no se envía información.

* POST http://localhost:8080/characters : Para crear un personaje, en el body de la peticion se deben enviar: imagen, nombre, edad, peso, historia, peliculaId.

* PUT http://localhost:8080/characters/{id} : Para modificar un personaje, en el body de la peticion se deben enviar: id, imagen, nombre, edad, peso, historia, peliculaId. 
Ademas en el path debe enviarse el id.

* DELETE http://localhost:8080/characters/{id} : Para eliminar un personaje en el path se debe enviar el id del mismo.

* POST http://localhost:8080/movies : Para crear una pelicula, en el body de la peticion se deben enviar: imagen, titulo, fechaCreacion, calificacion, generoId.

* PUT http://localhost:8080/movies/{id} :  Para modificar una pelicula, en el body de la peticion se deben enviar: id, imagen, titulo, fechaCreacion, calificacion, generoId
Ademas en el path debe enviarse el id.

* DELETE http://localhost:8080/movies/{id} : Para eliminar una pelicula en el path se debe enviar el id de la misma.

* GET http://localhost:8080/movies : Devuelve las imagenes, titulo y fecha de creacion de todas las peliculas cargadas en la base de datos. En el body de la peticion No enviamos nada.

* GET http://localhost:8080/movies/{id} :  Devuelve todos los datos de una pelicula. Incluido los datos del genero al que pertence.
En el path enviamos el id de la pelicula que deseo buscar.

* GET http://localhost:8080/movies/filter : En el path - params se puede o no filtrar por: Titulo, generoId y ordenar por fecha. En el body no se envía información.

* POST http://localhost:8080/genero : Para crear un genero, en el body de la peticion se deben enviar nombre e imagen.
