# Android TV development

Use JDK 17, Android SDK Platform 35, and Android SDK Build-Tools 35.0.0. Install the SDK packages and Android Emulator through Android Studio's SDK Manager. Use a TV system image for your host architecture and set `ANDROID_HOME` to the SDK directory for command-line builds. Set `JAVA_HOME` to JDK 17 and use that JDK for Gradle in Android Studio.

Open `apps/android-tv` as the Android Studio project. From the repository root, Moonrepo delegates to the checked-in Gradle wrapper:

```sh
moon run android-tv:build android-tv:lint
```

The equivalent native commands run from `apps/android-tv`:

```sh
./gradlew :app:assembleDebug :app:lintDebug
```

The debug APK is at `app/build/outputs/apk/debug/app-debug.apk` relative to the Android project. Use `apkanalyzer manifest permissions` on the APK to check that it requests no capture, overlay, or network permissions. AndroidX contributes an app-scoped signature permission for its receiver support.

## TV launcher smoke check

1. Boot an Android TV AVD (API 26 or newer) in Android Studio Device Manager. Install the debug APK with `adb install -r app/build/outputs/apk/debug/app-debug.apk`.
2. Open the TV home screen and select **Football Tactics** from the apps list. The app should show a blank, dark Compose surface.
3. Press Back to return to the TV launcher. Launch Football Tactics again and verify that it opens without crashing.

For an ADB check of the same launcher intent, use `adb shell am start -W -a android.intent.action.MAIN -c android.intent.category.LEANBACK_LAUNCHER -n com.footballtactics.tv/.ui.MainActivity`. The launch should report `Status: ok`.
