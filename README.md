# Fetch Android App

This Android project fetches a list of items from a remote API and displays them in a list. The app
handles loading, success, and error states using Jetpack Compose. It also provides a clean UI that
gracefully handles screen rotation, data fetching, and network error handling.

## Features

- Fetches data from a remote API.
- Displays loading indicators while fetching data.
- Displays items in a sorted and grouped manner.
- Handles network errors and displays error messages.
- Gracefully handles screen rotations without repeating network calls.
- Intercept back press to show an exit dialog.
- Custom logging utility to track network requests.

## Technical Choices

1. Architecture
   The app follows MVVM (Model-View-ViewModel) architecture for better separation of concerns and
   testability. The UI is decoupled from the business logic, with the ViewModel handling data
   fetching and processing.

- ViewModel: Manages UI-related data and interacts with the repository layer for API calls
- State Management: Uses Result (Success, Loading, and Error states) to represent different UI
  states and provide better error handling and loading control.
- Jetpack Compose: Adopted for UI development due to its declarative nature, modern features, and
  better integration with Kotlin
- LaunchedEffect: To trigger data fetching or side effects when the composable is first created,
  without blocking the UI thread.
- BackHandler: Used for intercepting back presses and showing an exit confirmation dialog

1. Libraries & Tools

- Retrofit: Handles the network communication with the backend API. Retrofit is used to define and
  execute HTTP requests
- OkHttp Logging Interceptor: For logging network requests and responses, which is useful during
  debugging to track API calls.
- Jetpack Compose Navigation: For managing screen transitions between different composables,
  ensuring type-safety and simplified navigation.
- Kotlin Coroutines: Employed for managing background tasks such as data fetching in a non-blocking
  manner.
- Custom Logging Utility: A utility for logging network responses and requests to monitor and debug
  API interactions.
- Retry Mechanism: A retry mechanism is implemented in the data fetching logic to handle transient
  network errors and improve user experience by automatically retrying the request.

3. Why These Choices?

- Jetpack Compose: Provides a modern, concise, and reactive way to build UIs, significantly reducing
  boilerplate code and integrating seamlessly with Kotlin and Android’s modern features.
- Retrofit & OkHttp: Retrofit is highly customizable, supports Kotlin coroutines, and integrates
  well with OkHttp for making HTTP requests. OkHttp’s Logging Interceptor helps track network
  traffic, which is essential for debugging.
- Jetpack Compose Navigation: Using Jetpack Compose’s Navigation API ensures that the navigation
  between different screens is type-safe and manageable.
- Custom Logging Utility: Custom logging allows for detailed visibility into network traffic and is
  helpful for debugging and monitoring network performance
- Retry Mechanism: Automatically retrying network requests provides a smoother experience,
  especially when dealing with unstable networks or intermittent connectivity issues.

4.Trade-offs

- Jetpack Compose vs XML Layouts: While Jetpack Compose offers a modern and reactive UI development
  experience, it is relatively new and some developers may find its ecosystem still maturing.
  However, its flexibility and the ability to integrate seamlessly with Kotlin outweigh these
  concerns.
- Dependency Injection (DI): DI libraries like Dagger or Hilt were not included in this project.
  Given the relatively small scale of the app, the complexity added by incorporating a DI framework
  was deemed unnecessary. Instead, a simpler and more direct approach was used for managing
  dependencies, which works well for a small project like this.
- Room Database: The project does not include Room for local data storage. Since the app is
  primarily focused on fetching and displaying remote data, adding Room would be over-engineering
  for this use case. Should the app grow in complexity and require offline capabilities, Room could
  be integrated later.
- This app currently consists of a single screen, so Jetpack Compose Navigation is not used. Since
  there is no need for multiple screens or navigation flow, there is no NavHost or NavController
  implemented. If the app were to be extended in the future with additional screens, Jetpack Compose
  Navigation would be a recommended approach for managing navigation.

## Prerequisites

Make sure you have the following installed:

- Android Studio (latest stable version)
- JDK 11 or higher
- Kotlin 1.6 or higher

## Run the App

- Connect your Android device or start an emulator.
- Press the Run button in Android Studio to launch the app.

## App Usage

- Item List Screen: Displays a list of items fetched from the remote API. While fetching, a loading
  indicator is displayed. Each item is presented with its name and additional details, such as
  descriptions.
- Exit Confirmation Dialog: The user is shown a confirmation dialog when they attempt to exit the
  screen (e.g., pressing the back button), confirming if they want to leave the app or stay.
- Retry Mechanism: In case of network failure, a retry option is presented to the user. The app will
  attempt to fetch the data again for a predefined number of retries.
- Network Logs: All network requests and responses are logged using OkHttp's Logging Interceptor to
  aid in debugging and provide insight into network operations.

## Unit Tests
This project includes unit tests to ensure that the core logic is functioning correctly. Unit tests are written using JUnit and MockK, focusing on testing the ViewModel and repository layers. The tests cover the following scenarios:

- Grouping and sorting items based on their listId and name.
- Filtering out items with blank or null names.
- Handling edge cases such as empty lists or lists with invalid data.

To run the unit tests, you can use the following command in Android Studio:
- Open the Run menu.
- Select Run 'tests' (or select a specific test from the Test tab).