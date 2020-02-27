# Taller de de modularización con virtualización e Introducción a AWS #
El objetivo del taller es manejar los conceptos de modularización con virtualización por medio de AWS **(Amazon Web Services)**, concurrencia en Java **(Threads)** y Virtualizacion en la nube.
Tambien se busca que un cliente haga multiples solicitudes al servidor tanto en paralelo como dinamicamente y que el servidor sea capaz de responder todas las solicitudes.

### **Link del despliegue de la aplicación: https://dry-ravine-53382.herokuapp.com/** ###

## Prerrequisitos ##
* Se debe tener conocimientos claros de la programación Orientada a objetos.
* Se debe tener conocimientos sobre manejo de Manejo de Maquinas Virtuales.
* Se debe tener conocimientos sobre como funciona la concurrencia en java.

## Construido con ##
[Maven](https://maven.apache.org/): Para la gestión de dependencias.

[Java](https://www.java.com/es/): Como lenguaje de programacion.

[Heroku](https://www.heroku.com/): Para realizar el despliegue de la aplicación.

[AWS](https://aws.amazon.com/es/): Para realizar la maquina virtual para subir el servidor.

## Descripción en Latex ##
En el siguiente vinculo encontrara el respectivo PDF de la descripción del diseño desarrollado en LaTeX.
[Click](LATEX)

## Documentación Javadoc ##
Para generar la documentacion javadoc, fue necesario ejecutar los siguientes comandos:

```
- mvn javadoc:javadoc
- mvn javadoc:jar
- mvn javadoc:aggregate
- mvn javadoc:aggregate-jar
- mvn javadoc:test-javadoc
- mvn javadoc:test-jar
- mvn javadoc:test-aggregate
- mvn javadoc:test-aggregate-jar
```
La ruta donde encontrara la documentación es la siguiente: **\target\apidocs\edu\escuelaing\arep\______**.

## Ejecución ##
Para empaquetar el proyecto se ejecuta el comando:

```mvn package```

Para ejecutar las pruebas se ejecuta el comando:

```mvn test```

## Pruebas del Servidor recibiendo multiples solicitudes ##

_Se puede observar el menu principal de la aplicacion donde se mostrara tanto la parte de  **recursos estaticos _(Cuando llamamos una imagen por url)_**:

![](IMAGEN)

 _Se puede observar la parte de **recursos dinamicos (Cuando utilizamos la multiplicacion de dos numeros)**_
 
 ![](IMAGEN)

## Licencia ##
Este proyecto cuenta con la licencia [**GNU General Public License v3.0**](https://github.com/JonatanGonzalez09/tallerAWS/blob/master/LICENSE)

## Autor ##
Jonatan Esteban Gonzalez Rodriguez 
