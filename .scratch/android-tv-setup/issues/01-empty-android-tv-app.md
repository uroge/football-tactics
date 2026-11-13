# 01 · Set up the empty Android TV app

**What to build**  
A minimal Kotlin Android TV project that a developer can build from a fresh checkout and launch to a blank TV Compose activity. Android-specific code will have a clear home as the app grows.

**Blocked by**  
None (can start immediately).

**Status**  
Done

## Acceptance criteria

- [x] A fresh checkout builds a debug APK with the checked-in Gradle wrapper, Gradle Kotlin DSL, pinned plugin and dependency versions, and documented JDK and SDK prerequisites.
- [x] Android Studio recognizes the app module.
- [x] The debug APK installs on an Android TV emulator and opens from the TV launcher to a blank TV Compose activity.
- [x] Moonrepo can invoke the native build and lint checks. The project docs include the equivalent Gradle commands.
- [x] The setup keeps one app module with a minimal application entry point. The architecture notes describe where TV UI, session coordination, and Android platform adapters will live when needed. Add code for those responsibilities only when a feature needs it.
- [x] The APK requests no permissions for capture, overlays, or networking. The setup includes only the launcher, build configuration, and setup verification.

## Verification

Implementation commit `4cb4f79`. A fresh checkout passed the Moonrepo build and lint tasks. On the Android TV emulator, the launcher tile opened the blank Compose screen, Back returned to the launcher, and the tile reopened the app. The packaged APK was checked for capture, overlay, and network permissions.

Android Studio Ladybug Feature Drop completed Gradle sync and showed the Android project view and the `app` run configuration after the project was pinned to Android Gradle Plugin 8.8.1. The debug APK build and lint also passed with that version.
