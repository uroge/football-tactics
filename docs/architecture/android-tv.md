# Android TV architecture

## Module layout

The Gradle project has one Android application module. TV Compose screens belong in the UI package. As analysis is added, a session package will own session state and coordination, and platform packages will own Android capture and window resources. Keep those resources behind small interfaces when session logic needs them. See the [Android TV development guide](../development/android-tv.md) for build and launcher verification.

## Documented platform behavior

- MediaProjection can project permitted display or selected app content to a virtual display. Apps targeting Android 14 or newer request consent per session, use a token once for one virtual display, declare the appropriate foreground service and register the stop callback before creating the display. [Media projection](https://developer.android.com/media/grow/media-projection) · [Foreground service types](https://developer.android.com/develop/background-work/services/fgs/service-types#media-projection)
- Android 15 narrows the overlay-permission exemption for background foreground-service starts. Start the spike from its visible activity after consent. [Android 15 behavior changes](https://developer.android.com/about/versions/15/behavior-changes-15)
- Application overlays require special permission and other apps can hide them. Secure windows can be excluded from capture. [Window parameters](https://developer.android.com/reference/android/view/WindowManager.LayoutParams) · [Secure activities](https://developer.android.com/security/fraud-prevention/activities)

## Proposed design

A TV Compose activity owns controls and permission requests. A session coordinator exposes idle, requesting consent, running, stopping and terminal error or unsupported states. A foreground service owns the projection, virtual display and `ImageReader`. An overlay adapter owns a non-focusable Canvas view. The experiment starts with a static graphic and an in-memory frame preview or checksum.

For live rendering, map normalized frame coordinates into the captured content bounds and then into overlay coordinates. Invalidate previous geometry after a size change and test letterboxing and pillarboxing. Clear the overlay on stop, stale results, or loss of alignment.

## Physical verification pending

Permission UI and capture mode availability, D-pad focus, combined operation, overlay contamination and capture revocation are device checks. Test single-app and display capture where offered. Start with sideloading. Review [Google Play policies](https://play.google.com/about/developer-content-policy/) if distribution enters scope.
