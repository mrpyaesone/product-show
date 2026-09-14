# Product Show App

A modern Android application showcasing a list of products with search and detailed views, built using Jetpack Compose and following Clean Architecture principles + MVVM.

## 🚀 Tech Stack

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) for a declarative UI.
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for robust DI.
- **Networking**: [Retrofit](https://github.com/lysine-dev/retrofit) & [OkHttp](https://github.com/lysine-dev/okhttp) for API calls.
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/) for asynchronous image loading.
- **Pagination**: [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data) for efficient list loading.
- **Asynchronous Flow**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html).
- **Testing**: JUnit 4, MockK, and Turbine.

## 🏗 Architecture & Project Structure

The project follows **Clean Architecture** principles + MVVM, organized as follows:

```text
app/src/main/java/com/example/product_show/
|_ data/
|  |_ di/                 # Hilt modules for data layer
|  |_ mapper/             # DTO to Domain model mappers
|  |_ network/            # Networking layer
|  |  |_ api/             # Retrofit API definitions
|  |  |_ di/              # Network-specific Hilt modules
|  |  |_ repo/            # Network data source interfaces & implementations
|  |  |_ response/        # API response DTOs
|  |_ ProductShowRepoImpl # Repository implementation
|_ domain/
|  |_ di/                 # Hilt modules for domain layer
|  |_ dispatcher/         # Coroutine dispatcher abstractions
|  |_ model/              # Pure domain entities
|  |_ repo/               # Repository interfaces
|  |_ usecase/            # Business logic (UseCases)
|_ ui/
|  |_ components/         # Reusable UI components
|  |_ productdetail/      # Product Detail screen & ViewModel
|  |_ productlist/        # Product List screen & ViewModel
|  |_ theme/              # Material 3 theme & colors
|  |_ utils/              # UI-specific helpers
|_ navigation/            # App navigation configuration
|_ MainActivity.kt        # Main Entry Activity
|_ ProductShowApp.kt      # Application class
```

## 🛠 Getting Started

### Prerequisites
- Android Studio Quail 4 or newer.
- JDK 11 or higher.

### Build and Run
1. Clone the repository.
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the `:app` module on an emulator or physical device.

## 🧪 Testing

The project includes unit tests for the Repository, UseCases, and ViewModels.

To run the unit tests, use the following command:
```bash
./gradlew test
```

Tests are located in: `app/src/test/java/com/example/product_show/`

## 🤖 AI Usage

This project utilized AI assistance (Gemini in Android Studio) for the following:
- **Unit Testing**: Generating comprehensive unit tests for the Repository, UseCases, and ViewModels using MockK and Turbine.
- **Documentation**: Structuring and writing this `README.md` file, including the project architecture visualization.
