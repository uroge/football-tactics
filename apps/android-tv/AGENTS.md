# Android TV

- Use Kotlin, the Gradle wrapper and Kotlin DSL. Generate the app scaffold with Android Studio or supported Gradle tooling when the spike starts. Use TV Compose for D-pad controls and Android View/Canvas for drawing.
- Keep UI, session coordination and platform adapters separate within one app module initially. Expose immutable UI state through `StateFlow`, use structured coroutines and confine window mutations to the main thread.
- Give the projection service ownership of projection, display and reader resources. Close acquired images promptly and make teardown idempotent.
- Use official permission flows and non-focusable overlay windows. Exercise remote navigation on hardware. Keep the [Android design](../../docs/architecture/android-tv.md) and [experiment](../../docs/experiments/android-capture-feasibility.md) current.
- Use AndroidX Lifecycle, JUnit and Android instrumentation tooling. Add MockK only where a small fake or direct unit test does not fit. Use OkHttp and Kotlin serialization or generated protocol models when transport enters scope.
