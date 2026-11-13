# Implementation plan

## Phase 0 · Planning

**Objective and prerequisites.** Capture the agreed personal-use scope and identify the physical-device unknowns from the product brief.

**Files and tasks.** Establish project boundaries in `.moon/workspace.yml`. Record requirements, architecture, decisions, experiment and risks under `docs/`. Establish repository and component guidance in `AGENTS.md` files.

**Verification and exit.** Check project paths and links. Review the documented Android permission sequence and Moon project map against official documentation. Finish with a reviewable scaffold and the owner's approval before Phase 1. Main risk is assuming reference device behavior from generic Android documentation.

## Phase 1 · Physical TV feasibility

**Objective and prerequisites.** Prove the agreed gate using the owner's TV, ADB, a supported Android build toolchain, a separate playback app and an authorized test clip.

**Files and tasks.** In `apps/android-tv`, generate the Gradle wrapper, app module and manifest with Android tooling. Add a D-pad-friendly activity, the foreground capture service and the static overlay view under `app/src/main`. Use public permission flows, capture to `ImageReader`, inspect frames locally, and add deterministic cleanup. Register available build and test commands in `apps/android-tv/moon.yml`.

**Testing and exit.** Exercise permission denial, normal and revoked stop, restart, capture modes, D-pad behavior, app switching and secure content using the [experiment](../experiments/android-capture-feasibility.md). Use unit tests for session transitions and instrumented tests where hardware-independent. Record the physical-device outcome and go/no-go in the experiment. Main risks are unavailable permission UI, vendor differences and overlay contamination.

## Later phases

| Phase | Prerequisite and scope | Work, tests and definition of done | Main risk |
| --- | --- | --- | --- |
| 2 | Gate passes. `apps/vision-server/src/football_vision`, `tests` | Run pretrained detection and tracking on licensed clips. Add basic kit clustering and deterministic evaluation. Review annotated footage and record baseline accuracy and latency. | Broadcast cuts and small objects |
| 3 | Offline baseline. Both apps and `packages/protocol` | Validate schemas and implement authenticated LAN WebSockets, backpressure, stale rejection and aligned player circles. Replay and interrupt sessions. Verify the live result on the TV. | Frame age and coordinate mismatch |
| 4 | Stable live overlay. Vision pipeline and overlay renderer | Add pitch landmarks, homography and tactical metrics. Evaluate reprojection and shot cuts. Verify that low-confidence graphics disappear. | Partial pitch visibility |
| 5 | Measured live workload. Both apps, `tools/benchmarks` and Rust if justified | Profile each stage, bound memory and compare changes against the latency targets. Keep any migration only if parity and end-to-end benefit are measured. | Optimization shifting cost across the boundary |
| 6 | Repeatable performance. Both apps, `docs/development` and applicable CI workflows | Add configuration persistence, recovery and installation instructions. Verify long sessions, supported device combinations and reproducible APK builds. | Firmware and lifecycle variability |
