# GitHub Copilot Instructions for WiFi Assistant

## Project Overview
WiFi Assistant (wifissistor2j) is an Android application that helps users analyze and optimize their WiFi network performance. The app provides real-time WiFi speed testing, network scanning, signal strength monitoring, and mapping capabilities.

## Technology Stack
- **Language**: Java
- **Platform**: Android (minSdk: 24, targetSdk: 36, compileSdk: 36)
- **Build System**: Gradle with Kotlin DSL (.kts files)
- **Java Version**: Java 11
- **UI Framework**: Material Design Components, AndroidX libraries
- **Key Libraries**:
  - Speed Test Library (com.github.bertrandmartel:speed-test-lib:1.32.1) from JitPack
  - AndroidX Core SplashScreen
  - AndroidX AppCompat
  - Google Material Components

## Project Structure
```
app/src/main/java/com/example/wifissistor2j/
├── HomeActivity.java          - Main activity with WiFi scanning and speed testing
├── ToolsActivity.java         - Additional WiFi tools and utilities
├── MapActivity.java           - Network mapping features
├── SettingsActivity.java      - App configuration
├── SpeedTestManager.java      - Singleton manager for speed tests
├── BmartelSpeedTester.java    - Speed test implementation
├── SpeedTester.java           - Speed test interface
├── SignalStrengthProvider.java - WiFi signal strength utilities
├── SignalStrengthMapper.java  - Signal strength mapping logic
├── WifiNetwork.java           - WiFi network data model
└── MyApplication.java         - Application class
```

## Code Conventions

### Java Code Style
1. **Naming Conventions**:
   - Activities: Suffix with `Activity` (e.g., `HomeActivity`, `SettingsActivity`)
   - Use camelCase for variables and methods
   - Use UPPER_SNAKE_CASE for constants
   - Class-level TAG constant for logging: `private static final String TAG = "ClassName";`

2. **Layout & Resources**:
   - Layout files use snake_case: `activity_home.xml`, `activity_settings.xml`
   - View IDs use snake_case with prefixes: `btn_analyze`, `network_status_text`
   - String resources defined in `res/values/strings.xml`

3. **Permissions**:
   - App requires WiFi and location permissions
   - Request permissions at runtime following Android best practices
   - Permission request codes use descriptive constant names

4. **Activity Lifecycle**:
   - Use SplashScreen API for startup experience
   - All activities use `singleTop` launch mode
   - Bottom navigation for main navigation flow

### Architecture Patterns
1. **Singleton Pattern**: `SpeedTestManager` uses singleton pattern with thread-safe getInstance()
2. **Listener Pattern**: Speed test callbacks use `SpeedTester.SpeedTestListener` interface
3. **Handler Pattern**: UI updates posted to main thread using `Handler(Looper.getMainLooper())`

### Speed Testing
- Speed tests run asynchronously with progress callbacks
- Download and upload speeds reported in Mbps and MB/s
- Speed test can be started, stopped, or cancelled
- Results interpreted and categorized by quality level
- Manager pattern ensures single test instance across app lifecycle

### Android Components
- **Broadcast Receivers**: Used for WiFi scan results
- **System Services**: 
  - `WifiManager` for WiFi operations
  - `ConnectivityManager` for network state
- **SharedPreferences**: For persistent settings storage

## Build & Test

### Building
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Clean build
./gradlew clean build
```

### Testing
```bash
# Run unit tests
./gradlew test

# Run instrumented tests (requires emulator/device)
./gradlew connectedAndroidTest
```

### Linting
```bash
# Run Android lint checks
./gradlew lint
```

## Important Notes
1. **Thread Safety**: UI updates must be posted to main thread via Handler
2. **Null Safety**: Always check for null listeners before invoking callbacks
3. **Resource Management**: Properly unregister broadcast receivers and remove listeners in onDestroy()
4. **Permissions**: Handle permission denial gracefully with user feedback
5. **Network Operations**: All speed tests and network operations run on background threads
6. **Package Name**: The package namespace is `com.example.wifissistor2j`

## Common Development Tasks
- Adding new activities: Update `AndroidManifest.xml` with activity declaration
- Adding dependencies: Update `gradle/libs.versions.toml` and `app/build.gradle.kts`
- UI changes: Material Design components preferred, follow existing theming
- New permissions: Add to `AndroidManifest.xml` and implement runtime request flow

## Testing Infrastructure
- Unit tests: `app/src/test/java/`
- Instrumented tests: `app/src/androidTest/java/`
- Test runner: `androidx.test.runner.AndroidJUnitRunner`
