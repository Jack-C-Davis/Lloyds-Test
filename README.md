# EarthQuake List Android Application

This repository contains a detailed sample application that uses MVVM as its presentation layer pattern.

Essential dependencies are Dagger2 with Dagger-android, RxJava2 with RxAndroid, Room, Retrofit and Espresso. Other noteworthy dependencies would be Mockito, Chrome CustomTabs and Guava.
## App Demo
This app displays latest earthquakes from all around the world. A number of quakes is fetched realtime upon request. If offline, the app displays the most recent loaded quakes.

## Presentation Layer
MVVM pattern is integrated to facilitate testing and to allow separating the user interface logic from business logic.

As Views were passive in MVP, here the View layer is much more flexibile as an indefinite number of Views can bind to a ViewModel. Also, MVVM enforces a clear separation between Views and their master - ViewModel, as the latter holds no reference to Views. The model layer is completely isolated and centralized through the repository pattern.

## Model Layer
The model layer is structured on repository pattern so that the ViewModel has no clue on the origins of the data. 

The repository handles data interactions and transactions from two main data sources - local and remote:
- `QuakesRemoteDataSource` defined by a REST API consumed with [Retrofit](http://square.github.io/retrofit)
- `QuakesLocalDataSource` defined by a SQL database consumed with [Room](https://developer.android.com/topic/libraries/architecture/room)

There are two main use-cases, online and offline. In both use cases, `QuakesLocalDataSource` has priority. In the online use-case if the local data is stale, new data is fetched from the `NewsRemoteDataSource` and the repository data is refreshed. In case of no internet connection,  `QuakesLocalDataSource` is always queried.

Decoupling is also inforced within the Model layer (entirely consisted by `QuakesRepository`). Therefore, lower level components (which are the data sources: `QuakesRemoteDataSource` and `QuakesLocalDatasource`) are decoupled through `QuakesDataSource` interface. Also, through their dependence on the same interface, these data sources are interchangeable.

In this manner, the project respects the DIP (Dependency Inversion Principle) as both low and high level modules depend on abstractions.
### Reactive approach
It is extremely important to note that this project is not essentially a reactive app as it is not capitalizing the enormous potential of a fully reactive approach.
Nevertheless, the app was intended to have a flexible and efficient testing capability, rather than a fully reactive build.

Even in this case, we are able to notice RxJava's benefits when data is being retrieved from the repository through different sources and then is channeled through the ViewModel and finally consumed in Views.
- Data Flow is centralized.
- Threading is much easier, with no need for the dreaded `AsyncTasks`.
- Error handling is straightforward and comfortable.
## Dependency Injection
Dagger2 is used to externalize the creation of dependencies from the classes that use them. Android specific helpers are provided by `Dagger-Android` and the most significant advantage is that they generate a subcomponent for each `Activity` through a new code generator.
Such subcomponent is:
```kotlin
@ActivityScoped
@ContributesAndroidInjector(modules = [QuakesModule::class])
abstract fun quakesActivity(): QuakesActivity?
```
## Testing
The apps' components are extremely easy to test due to DI achieved through Dagger and the project's structure, but as well for the reason that the data flow is centralized with RxJava which results in highly testable pieces of code. 

Unit tests are conducted with the help of Mockito and Instrumentation tests with the help of Espresso. 
## Strong points
- Decoupling level is high.
- Data Flow is centralized through RxJava.
- Possess high flexibility to create variants for automated and manual testing.
- Possess lightweight structure due to MVVM presentation pattern.
- Is scalable and easy to expand.

## Weak points
- Possess high code base - simpler approaches will certainly lower code size
- Possess medium complexity - other approaches might lower complexity and increase efficiency.

