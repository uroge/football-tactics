# 01 · Set up the empty Android TV app

**What to build**  
A minimal Kotlin Android TV project that a developer can build from a fresh checkout and launch to a blank TV Compose activity. Android-specific code will have a clear home as the app grows.

**Blocked by**  
None (can start immediately).

**Status**  
ready-for-agent

## Acceptance criteria

- [ ] A fresh checkout builds a debug APK with the checked-in Gradle wrapper, Gradle Kotlin DSL, pinned plugin and dependency versions, and documented JDK and SDK prerequisites.
- [ ] Android Studio recognizes the app module. The debug APK installs on an Android TV emulator and opens from the TV launcher to a blank TV Compose activity.
- [ ] Moonrepo can invoke the native build and lint checks. The project docs include the equivalent Gradle commands.
- [ ] The setup keeps one app module with a minimal application entry point. The architecture notes describe where TV UI, session coordination, and Android platform adapters will live when needed. Add code for those responsibilities only when a feature needs it.
- [ ] The APK requests no permissions for capture, overlays, or networking. The setup includes only the launcher, build configuration, and setup verification.
