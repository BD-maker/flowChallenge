# Flow Challenge

## Extracto
El ejercicio consisite en visualizar una lista de personajes de la serie Rick And Morty con el patrón Master-Detail.

Para este proyecto se utilizó:
- Clean Arquitecture
- Navigation Component
- ViewBinding
- UseCases
- Corutinas
- Arquitectura MVVM
- Retrofit
- Picasso
- Inyección de dependencias
- Motion Layout
- Generics

## Instrucciones

- Clona el repositorio `https://github.com/BD-maker/flowChallenge.git`
- Sincroniza Gradle
- Y por último compila el proyecto


##  Arquitectura

En el proyecto se implementó Clean Arquitecture, esto permite desacoplar diferentes unidades del código para que resulte más fácil de modificar, expandir y testear.

![onion](https://miro.medium.com/max/1400/1*jH0iI7-MSQYgLUrqTUm6mg.png)

La idea es definir el proyecto en varias capas y que las exteriores sepan lo que hay en las interiores, pero en en las capas interiores no sepan lo que hay en las exteriores. Por este motivo las dependencias se inyectan de afuera hacia adentro.

Para aplicar esta arquitectura se necesitaba inyectar las dependencias a cada capa lo cual se realizó de forma manual a través del patrón Factory.


## Capa UI / Data Source

Se implementó un Navigation Component para manejar los 2 Fragments en una única Activity y cada Fragment es una pantalla de la aplicación: 

- Pantalla de lista de personajes
- Pantalla de visualización de personaje

Navigation Component esta formado por:

- NavGraph: Colección de destinos con sus conexiones
- NavHost: Contenedor de los fragmentos (ubicado en el MainActivity)
- NavController: Clase que maneja la navegación del NavHost interactuando con el NavGraph

Para pasar la información a cada Fragment se utilizó el componente de Gradle "Safe Args", que nos provee seguridad de tipo, a diferencia del objeto Bundle.

Para la Data Source, en este caso se utiliza una llamada a la API de Rick And Morty pero también podría ser una base de datos room.


## Capa Presenter/Repository

Se utilizó patrón MVVM, creando un ViewModel para el Fragment de lista de personajes y otro para el detalle de c/u. Se podría haber usado el mismo ViewModel perfectamente, pero si tuviésemos expectativas de ampliar las funcionalidades de cada pantalla sería mejor dejar los ViewModels seperados.

ViewModel utiliza LiveData, actualizando la UI automáticamente ante cambios en las variables previamente marcadas como observables.

Se crearon 2 repositorios, uno para ir a buscar la lista de personajes y otro para ir a buscar el personaje en particular.


## Capa Use Cases

Para abstraer un grado más el ViewModel del repositorio de datos y evitar los “God ViewModels” se utilizan los casos de uso, donde se implementa la lógica del negocio. Esta capa puede ser considerada como la Capa de Servicio en una arquitectura de capas.

![layers](https://miro.medium.com/max/1064/1*CAdK7Eqcbaof4p-N_HHv8Q.png)

 El objetivo primario es separar las responsabilidades, en esta aplicación se definieron 2 casos de usos:
 
-	GetCharactersUseCase: Solicita una lista de personajes
-	GetCharacterDetailUseCase: Solicita los datos del personaje seleccionado.


## Capa Entities

Se describen los objetos del negocio de nuestro sistema, en este caso se declararon las estructuras de datos que corresponden a los retornos de las llamadas al API de Rick And Morty.


## Testing

Se realizó tests unitarios en los ViewModels, casos de uso y respositorios.


Ing. Bruno Diaz
2022

