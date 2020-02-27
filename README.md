# Taller de modularización con virtualización e Introducción a AWS #
El objetivo del taller es manejar los conceptos de modularización con virtualización por medio de AWS **(Amazon Web Services)**, concurrencia en Java **(Threads)** y Virtualización en la nube.
También se busca que un cliente haga múltiples solicitudes al servidor tanto en paralelo como dinámicamente y que el servidor sea capaz de responder todas las solicitudes.

### **Link del despliegue de la aplicación: https://dry-ravine-53382.herokuapp.com/** ###

## Prerrequisitos ##

* Se debe tener conocimientos claros de la programación Orientada a objetos.
* Se debe tener conocimientos sobre manejo de Manejo de Máquinas Virtuales.
* Se debe tener conocimientos sobre cómo funciona la concurrencia en java.

## Construido con ##

[Maven](https://maven.apache.org/): Para la gestión de dependencias.

[Java](https://www.java.com/es/): Como lenguaje de programación.

[Heroku](https://www.heroku.com/): Para realizar el despliegue de la aplicación.

[AWS](https://aws.amazon.com/es/): Para realizar la máquina virtual para subir el servidor.

## Descripción en Latex ##
En el siguiente vinculo encontrara el respectivo PDF de la descripción del diseño desarrollado en LaTeX.
[Click](LATEX)

## Documentación Javadoc ##
Para generar la documentación javadoc, fue necesario ejecutar los siguientes comandos:

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
La ruta donde encontrara la documentación es la siguiente: **\target\apidocs\edu\escuelaing\arep\...**.

## Ejecución ##
Para empaquetar el proyecto se ejecuta el comando:

```mvn package```

Para ejecutar las pruebas se ejecuta el comando:

```mvn test```

## Pruebas del Servidor recibiendo múltiples solicitudes ##

### Menú de la aplicación ###

![](https://github.com/JonatanGonzalez09/tallerAWS/blob/master/Imagenes/menu.jpeg)

### Recursos Estáticos ###

_Se puede observar el menú principal de la aplicación donde se mostrara tanto la parte de  **recursos estáticos _(Cuando llamamos una imagen por URL)_**:

**https://dry-ravine-53382.herokuapp.com/cat.png**

![](https://github.com/JonatanGonzalez09/tallerAWS/blob/master/Imagenes/gato.jpeg)

**https://dry-ravine-53382.herokuapp.com/ironMan.png**

![](https://github.com/JonatanGonzalez09/tallerAWS/blob/master/Imagenes/ironMan.jpeg)


### Recursos Dinámicos ###

  _Se puede observar la parte de **recursos dinámicos (Cuando utilizamos la multiplicación de dos números)**_
  
  **https://dry-ravine-53382.herokuapp.com/multiplicar.html**
 
 ![](https://github.com/JonatanGonzalez09/tallerAWS/blob/master/Imagenes/multiplicacion.jpeg)
 
 _Da como resultado_
 
 ![](https://github.com/JonatanGonzalez09/tallerAWS/blob/master/Imagenes/resultado.jpeg)
 

## Licencia ##
Este proyecto cuenta con la licencia [**GNU General Public License v3.0**](https://github.com/JonatanGonzalez09/tallerAWS/blob/master/LICENSE)

## Autor ##
Jonatan Esteban Gonzalez Rodriguez 
