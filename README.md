# PSL Prueba técnica 
**Aspirante al cargo** : César Augusto Banguero Arévalo
**correo electrónico** : cbanguero.a@gmail.com

## display-lcd-technical-test
A continuación sé realizar una descripción del proceso de instalación de la aplicación y la ejecución de pruebas.

  - Instalación y ejecución
  - Detalle de la implementación

### Instalación y ejecución 

**Prerequisitos**
-  Java 1.8
-  Apache Maven 3.3.9
 
**Instalación**
Ubicándonos a nivel del pom.xml del proyecto en línea de comandos
```sh
$ cd com.psl.technicaltest.displaylcd
$ mvn clean install 
```
**Ejecución de prueba**
Se ha configurado el proyecto para permitir la ejecución de la aplicación mediante comandos maven(Con o sin parametros).

-  Ejecución con parametros: 
   inputPath = Ruta en la que se alojara el fichero a leer
   outputPath = Ruta en la que se alojarael fichero de salida
```sh
$ mvn exec:java -Dexec.mainClass="com.psl.logic.print.NumberPrinter" -Dexec.args=["inputPath outputPath"]
```
 Ejemplo:
 ```sh
$ mvn exec:java -Dexec.mainClass="com.psl.logic.print.NumberPrinter" -Dexec.args="C:/devel/PSL/in/ C:/devel/PSL/out/"
```

-  Ejecución sin parametros: 
   En este caso se toman las rutas establecidas por defecto
    inputPath = "src/main/resources/files/input/"
   outputPath = "src/main/resources/files/output/"
```sh
$ mvn exec:java -Dexec.mainClass="com.psl.logic.print.NumberPrinter
```
-  Ejecución mediante JUnit: 
  También es posible ejecutar la aplicación haciendo de uso de pruebas automaticas con JUnit. Solo es necesario invocar el metodo printNumbersInLCDDisplay() de la clase NumberPrinter especificando los path de entrada y salida. 

Se ha definido la clase de pruebas DisplayLCDUnitTest.java en la que se definieneron las pruebas que se consideran mas relevantes para validar las posibles entredas de la aplicacion. Por otra parte, se ha dedicado esfuerzo la ejecucion de pruebas funcionales con el fin de garantizar la estabilidad de la aplicacion.

### Detalle de la implementación
Puntos destacables:
-  La aplicación se implementado como un proyecto Maven.
-  Se implementaron pruebas unitarias con JUnit utilizando reflexión para acceder a los metodos y atributos privados de la aplicación. 
-  Se Agrego el uso de Log4J para poseer trazabilidad en los posibles errores a generar.
-  Se implemento un control para las execiones lanzadas durante las ejecucion imprimiento el detalle del problema en Logs.
-  El codigo fuente ha sido debidamente documentado meditan JavaDoc y se agregaron comentarios descriptivos en el flujo de la aplicación.

**Gracias por la oportunidad!**