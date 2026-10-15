# Repository Guidelines

## Project Structure & Module Organization

This is a single-module Android application using Kotlin, Jetpack Compose, and Clean Architecture with MVVM. Source code lives in `app/src/main/java/com/example/product_show/`:

- `data/`: repository implementations, API responses, networking, mappers, and Hilt modules.
- `domain/`: models, repository contracts, use cases, and dispatcher abstractions.
- `ui/`: product list/detail screens, ViewModels, UI state, shared components, and themes.
- `navigation/`: navigation graph and host.

Resources, including fonts and launcher images, live in `app/src/main/res/`. JVM tests are under `app/src/test/`; device tests are under `app/src/androidTest/`. Dependency versions and aliases are centralized in `gradle/libs.versions.toml`.

## Build, Test, and Development Commands

Run commands from the repository root using the checked-in Gradle wrapper:

- `./gradlew :app:assembleDebug`: build the debug APK.
- `./gradlew :app:installDebug`: install on a connected emulator or device; launch the app from its launcher icon.
- `./gradlew :app:testDebugUnitTest`: run debug JVM unit tests.
- `./gradlew :app:connectedDebugAndroidTest`: run instrumentation tests on a connected device.
- `./gradlew :app:lintDebug`: run Android Lint checks.

For interactive development, open the project in Android Studio, sync Gradle, and run the `app` configuration. Configure the Android SDK locally in `local.properties`.

## Coding Style & Naming Conventions

Follow the configured official Kotlin style with four-space indentation and Android Studio formatting. Use PascalCase for types and composables, camelCase for functions and properties, and lowercase underscore-separated Android resource names. Match existing suffixes such as `ViewModel`, `UiState`, `UseCase`, and `RepoImpl`. Keep networking in `data/` and expose domain models to the UI through repository contracts and use cases. No standalone formatter is configured.

## Testing Guidelines

Use JUnit 4, MockK, coroutine test utilities, and Turbine for JVM tests. Mirror source packages and name files `<ClassName>Test.kt`. Cover success, failure, pagination/search behavior, and observable state transitions when relevant. Instrumentation dependencies include AndroidX JUnit, Espresso, and Compose UI testing. No numeric coverage threshold is configured.

## Commit & Pull Request Guidelines

History commonly uses `fix:`, `chore:`, `add:`, and `enhance:` prefixes. Keep commit subjects concise and describe the concrete change. Pull requests should explain behavior changes, link relevant issues, report validation commands and results, and include screenshots for UI changes.
