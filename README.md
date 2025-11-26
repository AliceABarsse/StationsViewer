# Consigne initiale
```
Le projet est à réaliser sur la plateforme Android (API minimum 24)
En utilisant l’Open API de Radio France, réaliser une interface avec deux écrans.
Le premier écran devra simplement aﬃcher une liste des stations, scrollable
verticalement. Le tap sur une station devra aﬃcher un second écran aﬃchant la liste des
émissions pour la station en question.
```

# Application : StationsViewer

**NB: `API_KEY=12345` doit être renseigné dans un fichier local.properties**

## Démos

https://github.com/user-attachments/assets/7cbad6c7-eaa9-41bf-a962-12df514fb94d

### Écran des stations et Écran des émissions d'une station donnée

|Stations|Émissions|
|-|-|
|<img width="300" alt="Screenshot 2025-11-26 at 14 34 11" src="https://github.com/user-attachments/assets/c05069d7-4bf9-4a15-b68b-bd242a1c59fa" />|<img width="300" alt="Screenshot 2025-11-26 at 14 27 17" src="https://github.com/user-attachments/assets/d10041bf-3943-4f20-b47c-9a5611f8a700" />|

## Fonctionnalités
- Listes scrollables avec du lazy-loading
- app icon + personalized Theme
- Compose tests
- "Back” action

## Outillage
- Couverture de code JaCoCo + *github action* pour visualiser la couverture de code (mais la config ne semble pas tout à fait au point...)
- Autonomouse *dependency analysis plugin* (`./gradlew projectHealth`)
- Random / test values dans des `testFixtures` srcSets

## Architecture
- Mono-activité
- MVI (UI states immuables et actions utilisateurs envoyées aux ViewModels)
- Single responsability Principle : modules Gradles clairement identifiés et surfaces d'interface petites
- Open (for extension) / Closed (for modification) Principle : scope limité des types internes, interfaces définies au niveau visible pour tous
- (Liskov Principle: pas de point particulier)
- Interface segregation Principle: petites interfaces ciblées (par exemple GetStationUseCase)
- Dependency inversion Principle: `:core:network`, `:core:data`, `:features` ne dépendent que de `:core:domain` (interfaces) et de `:code:model` (objets métier)

## Stack tech
- API Android minimum 24
- build : *Gradle avec DSL Kotlin*, catalogue de dépendances *toml*
- *Modularisation Gradle*: `:app`, `:features`, `:core` (sous-projets `:core:model`, `:core:data`, `:core:domain`, `:core:network`)
- Single Activity
- *Compose* UI, Material3 components
- Injection de dépendances avec *Koin*
- *Tests unitaires* JUnit4, tests Compose, Robolectric, Mockk
- *Retrofit* + OkHttpClient avec définition d'un interceptor pour le token d'autorisation, et *kotlinx serialization*
- *Coroutines et Flows* pour les données
- Strings extraites dans une `resource`, *I18n-ready*

## TODO : Pas eu le temps 
- Test instrumentés avec Espresso
- Room database pour un cache offline
- Immutable to ensure data is stable (list)
- Mieux voir la couverture de code
- Gérer un Top AppBar
- Afficher les programmes dans un BottomSheet
  
