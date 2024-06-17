# Stocks Multiplatform Mobile App

## How to Run the Project

To get started with the Stocks project, you'll need Xcode 15 and Android Studio or JetBrains Fleet configured for Kotlin Multiplatform development. Follow the comprehensive setup instructions provided by JetBrains:
[Setting up Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-setup.html).

Clone the repository:
```bash
git clone https://github.com/jassycliq/Stocks.git
```
After cloning, open the project in your preferred IDE and allow it to sync. There are two default run configurations provided—one for iOS and one for Android—that will build, install, and launch the app on the respective platforms.

## Focus Areas and Trade-offs

### Focus:
The architecture of the app was a major focus, balancing necessary abstractions for scalability with simplicity, as the app's functionality remains basic. This approach was chosen to ease future enhancements.

### Trade-offs:
The UI design is relatively basic due to limited data available from the endpoints. The use of Circuit for UI management simplified screen and presenter setups but posed challenges in accurately modeling various UI states. Ideally, an MVI pattern would be utilized to distinctly represent Loading, Error, and Success states; however, the complexity introduced by Circuit limited this implementation.

## Dependencies and Third-Party Libraries

The project's codebase is largely original, inspired by Chris Bane's Tivi app, particularly in terms of module setup, Gradle convention plugins, and iOS-specific Kotlin compiler flags.

## Project Duration

The project took about 5 hours focused on UI development. As this was an initial foray into Kotlin Multiplatform projects, additional time was spent on setup and learning, providing a robust learning experience and excitement for future developments with this technology.
