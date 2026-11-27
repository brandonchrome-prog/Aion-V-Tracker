# Aion V Planner

Open this directory in Android Studio, sync Gradle, and select Run on an Android phone or emulator. To share an installable build, use Android Studio’s Build APK action. The project uses JDK 17, Android SDK 35, and Android Gradle Plugin 8.7.3. The app stores logs on the device and has no Internet permission. Android Auto does not trigger logging. Google Maps Timeline imports use a file you select.

Open app/src/main/assets/index.html in a modern browser to preview the same screens. Browser and Android app logs are stored separately.

## GitHub build

Push to GitHub to run **Android APK**. The build checks Android lint, creates a debug APK, and verifies its signature. Download the **Aion-V-Planner** artifact from the completed run and extract the APK. This is a personal test build. GitHub-hosted build runners may generate a different debug signing key on subsequent runs.
