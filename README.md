# Consigne initiale
```
Le projet est à réaliser sur la plateforme Android (API minimum 24)
En utilisant l’Open API de Radio France, réaliser une interface avec deux écrans.
Le premier écran devra simplement aﬃcher une liste des stations, scrollable
verticalement. Le tap sur une station devra aﬃcher un second écran aﬃchant la liste des
émissions pour la station en question.
```

# Application : StationsViewer

## Démos

### Écran des stations

### Écran des émissions d'une station donnée

## Fonctionnalités
- Liste scrollable avec du lazy-loading

TODO
- Loading screen
- Empty / error screen
- 

## Architecture
- Mono-activité
- MVI

## Stack tech
- API Android minimum 24
- build : Gradle avec DSL Kotlin, catalogue de dépendances toml
- Modularisation Gradle: app, features, core (sous-projets model, data, domain, etc.)
- Single Activity
- Compose UI, Material3 components
- Injection de dépendances avec Koin
- Tests unitaires JUnit4

TODO
- JUnit4 tests, Compose / Espresso / Robolectric / mockK
- Strings extracted to a strings resource, I18n-ready
- Room database
- Retrofit for remote API call, and kotlinx serialization for Json
- Flow + coroutines to handle exchange of data between layers. Flow was not really needed to do a one-time data retrieval, though.
- Immutable to ensure data is stable (list). No direct handling of stable / unstable data for Compose, in this example it is not really needed.
- Dependency analysis gradle plugin to detect unused dependencies easily


