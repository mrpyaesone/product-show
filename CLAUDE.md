# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

General repository guidelines (structure, naming, testing conventions, commit prefixes) live in AGENTS.md and apply here too:

@AGENTS.md

## Commands

```bash
./gradlew :app:assembleDebug                 # build debug APK (applicationId gets a .debug suffix)
./gradlew :app:testDebugUnitTest             # all JVM unit tests
./gradlew :app:testDebugUnitTest --tests "com.example.product_show.ui.productlist.ProductListViewModelTest"
./gradlew :app:testDebugUnitTest --tests "*ProductShowRepoImplTest.*empty*"   # single test by pattern
./gradlew :app:lintDebug
./gradlew :app:connectedDebugAndroidTest     # needs a connected device/emulator
```

Toolchain: AGP 9.x with compileSdk/targetSdk 37, minSdk 24, Java 11 bytecode. README notes JVM 25 is required to run AGP 9.6.0.

## Architecture

Single `:app` module, Clean Architecture + MVVM, backed by the public DummyJSON API (`BuildConfig.BASE_URL`, defined per build type in `app/build.gradle.kts`). Two screens: a searchable, paginated product grid and a product detail screen.

### Request flow and error handling

`ProductShowApi` (Retrofit) → `ProductShowNetworkDataSourceImpl` → `ProductShowRepoImpl` → use case → ViewModel.

- The network data source wraps every call in try/catch and rethrows via `NetworkErrorHandler`, which parses DummyJSON's error body into `domain.exception.NetworkException` (an `IOException`), or a plain `Exception` otherwise. Errors therefore surface as exceptions, not result types, until the use case layer.
- `ProductShowRepoImpl` maps DTOs to domain models (`data/mapper`) and chooses between `/products` and `/products/search` based on whether the search query is empty. A null detail response becomes `Product.empty`.
- Use cases switch to `DispatcherProvider.io` (injected, so tests can substitute a test dispatcher). They differ in error style: `GetProductDetailUseCase` returns `Result<Product>`, while `GetProductListUseCase` lets exceptions propagate so Paging can turn them into `LoadResult.Error`.

### Pagination (the subtle part)

Pagination is offset-based (`skip`/`limit`), not page-number based. `Page<T>.nextPage` holds the next `skip` value.

- `ProductListViewModel.products` = `searchText.debounce(300ms).distinctUntilChanged().flatMapLatest { Pager(...) }.cachedIn(viewModelScope)`; each query change creates a fresh `ProductPagingSource`.
- `ProductPagingSource` uses keys that are skip offsets: `prevKey = page - PAGE_SIZE`, `nextKey = null` when fewer than `PAGE_SIZE` items come back (this avoids an extra trailing API call), and `getRefreshKey` derives the key from the closest page's `prevKey + PAGE_SIZE` / `nextKey - PAGE_SIZE`.
- `PAGE_SIZE` (20) in the paging source must stay in sync with `initialLoadSize`/`pageSize` in the ViewModel's `PagingConfig`. If they diverge, the prev/refresh key arithmetic breaks. Recent commits have specifically fixed extra or duplicate API calls here, so re-check this logic when changing paging behaviour.

### UI and navigation

- Navigation uses type-safe `@Serializable` routes. Each feature package owns its route plus `NavGraphBuilder.xxxDestination(...)` and `NavHostController.navToXxx(...)` extensions (`ui/*/…Navigation.kt`), and `navigation/NavGraph.kt` just wires them together. Add new screens the same way.
- ViewModels are `@HiltViewModel`. The detail screen exposes a sealed `ProductDetailUiState` (Loading/Success/Error) via `StateFlow`, and the screen triggers `getProductDetail(productId)`. The list screen collects `PagingData` and drives `searchProduct(input)`.
- Shared Loading/Error/Empty views and the top bar live in `ui/components`.

### DI

Hilt modules: `data/di/RepoModule` (repo binding), `data/network/di/NetworkModule` (OkHttp with body logging in debug only, Retrofit, Gson, API), and `domain/di/DispatcherModule`. LeakCanary is included in debug builds.

## Testing notes

JVM tests mock collaborators with MockK, set `Dispatchers.setMain(StandardTestDispatcher())` in `@Before` and reset it in `@After`, and use Turbine for `StateFlow` assertions. Use case tests mock `DispatcherProvider` so that `io` returns `Dispatchers.Unconfined`.
