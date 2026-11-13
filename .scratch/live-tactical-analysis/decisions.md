# Decisions and verification evidence

The baseline is the Desktop checkout at `2c984db3c509d71922cc07bd1408cc66c01ff5d3`. The attached Documents checkout had an unborn branch and no project files. Existing verification below is recorded repository evidence. Hardware behavior, the live protocol, model quality and benchmark results await execution.

## Decisions for review

### D01

**Phase 1 approval and required playback app · Owner before gate sign-off.** Confirm approval to start the physical spike when implementation is requested. Identify the reference TV, firmware, available remote and authorized test clip. Name the required playback app, or confirm that a compatible separate playback app is sufficient. Record any missing prerequisites in 01. If a required app blocks capture or overlay, keep all functional descendants blocked pending a product-direction decision. The feasibility report must distinguish device results from controlled protected-content tests.

### D02

**Clean frame strategy · Evidence from 01, resolved in 02.** The experiment can record contamination and still establish simultaneous capture and overlay. Live inference requires a proven clean frame path and a defensible way to exclude unrelated screen content. Prefer the narrowest viable capture mode. If no supported option clears both issues, return to the owner before LAN or live integration. Capture success and overlay visibility are separate observations.

### D03

**Assets, Python compatibility and model scope · Resolve in 03 before asset use.** Check footage and annotation rights, framework and pretrained-weight terms separately, and record scope, provenance and hashes. The brief allows Python 3.11+, while setup pins Python 3.13 and verifies the empty package. Verify the selected runtime dependencies on M1 before assuming compatibility. Start with pretrained player/referee/ball detection and ByteTrack. Goalkeeper detection as a separate class is optional. Fine-tuning requires a measured gap, suitable licensed data and a follow-up scope decision.

### D04

**LAN transport contract · Resolve in 06 before sending frames.** Confirm pairing and credential issuance, storage, expiry and revocation, encryption/trust, listener scope, client limits, frame codec/envelope, size/rate limits, heartbeat and reconnect deadlines and stale age. The ADR selects binary frames and JSON controls/results. Preserve TV monotonic correlation and a new session after reconnect. The local WebSocket remains the transport until measurements justify a change. No broker or public listener enters this scope. The first real exchange stays in one chat.

### D05

**Evaluation and release thresholds · Resolve before the relevant test.** Set clip sets and splits, metrics and tolerances for vision/calibration, tactical confidence requirements, benchmark duration and reporting percentiles, memory and queue budgets, and the reliability soak duration and exit criteria. Decide how the under-500-ms ceiling is applied to the latency distribution and how unmeetable targets affect release. Keep the published numbers as engineering targets until measured. Use the first measured baseline to schedule any necessary follow-up.

### D06

**Rust activation · Adopt the repository's profiling decision.** The brief asks for a dedicated workspace and Rust checks and names Rust in the shared protocol. ADR 0005 and the monorepo design reserve the directory/project now and activate Cargo, Rust CI and schema use only when a measured candidate warrants it. Keep the Python baseline. Ticket 18 records the decision and ticket 19 is conditional. Review this interpretation if Cargo scaffolding or Rust schema parity is required before a migration.

### D07

**Personal distribution and remote controls · Adopt current scope, confirm release terms in 21.** The original brief asks for investigation of Google Play capture and overlay policies and later suggests overlay controls, shortcuts and a repository license. Current requirements target personal sideloading. Google Play and public/cloud distribution, focusable overlay controls, quick settings and global shortcuts are deferred until an owner scope change. Record a repository licensing decision before broader sharing. Per-layer controls and persisted preferences remain in 08 and 21.

### D08

**Protected playback · Adopted restriction.** Protected and restricted combinations are unsupported. Use documented Android APIs and authorized content only. Record a controlled test and observed failure in the matrix. Capture, visible overlay and playback control are independent checks. Public API behavior is documented, while device and playback app behavior awaits the physical run. [MediaProjection](https://developer.android.com/media/grow/media-projection), [foreground service types](https://developer.android.com/develop/background-work/services/fgs/service-types#media-projection), [Android 15 behavior changes](https://developer.android.com/about/versions/15/behavior-changes-15), [WindowManager layout parameters](https://developer.android.com/reference/android/view/WindowManager.LayoutParams), [secure activities](https://developer.android.com/security/fraud-prevention/activities).

### D09

**Stack, repository tree and escalation criteria · Adopt ADRs and repository guidance.** Use native Android, Canvas and supported libraries. MockK, generated protocol models, custom tooling and dedicated toolchain/task files depend on concrete need. OpenGL ES or Vulkan requires rendering evidence. The brief's tree and example commands are starting points. Verify Moon syntax against the version in use. [Moon workspace reference](https://moonrepo.dev/docs/config/workspace), [Moon project reference](https://moonrepo.dev/docs/config/project).

### D10

**Overlay configuration message · Confirm in 08.** The brief lists `overlay.configure` among minimum protocol messages. The proposed repo protocol sends it when a setting changes server behavior. Plan local rendering settings with a documented v1 disposition. If the server needs a setting, implement and verify the message. Confirm with the owner if unconditional wire support is required.

### D11

**Offline team classification and live sequencing · Planning interpretation.** Phase 2 explicitly includes kit classification and the repository roadmap puts the offline baseline before live integration. Ticket 06 therefore depends on 05 even though the first live display contains circles. IDs and colors are verified as selectable TV layers in 08. Tickets 10–16 are full-product scope after calibration and can advance independently where they leave the shared interface stable. Review the dependency if an earlier live tracer is preferred.

## Existing verification

### E01

**Android shell · Recorded Done.** Implementation `4cb4f79`, verification update `5c68868`, Android Gradle Plugin pin `ce85b3c`. A fresh checkout built and linted through Moonrepo. The TV emulator installed and launched the blank TV Compose activity. Back/relaunch and packaged permission checks passed. Android Studio recognized the app after the AGP 8.8.1 fix. This evidence covers the shell, IDE and toolchain. [Android setup ticket](/Users/urosmilosavljevic/Desktop/football-tactics/.scratch/android-tv-setup/issues/01-empty-android-tv-app.md).

### E02

**Python shell · Recorded Done.** Commit `2c984db`. Clean locked macOS setup on Python 3.13.7 and uv 0.13.8, isolated import, pytest smoke test, Ruff and mypy, both directly and through Moonrepo. The package has no runtime dependencies. [Python setup ticket](/Users/urosmilosavljevic/Desktop/football-tactics/.scratch/python-vision-setup/issues/01-empty-python-vision-project.md).

### E03

**Architecture and workspace · Inspected.** The repository contains the five architecture documents and five ADRs, requirements, roadmap, milestones, risk register, experiment plan, and four-project Moon map. The experiment says physical results are pending. Existing build/test task coverage is E01 and E02. [Architecture](/Users/urosmilosavljevic/Desktop/football-tactics/docs/architecture/system-overview.md), [milestones](/Users/urosmilosavljevic/Desktop/football-tactics/docs/roadmap/milestones.md), [experiment](/Users/urosmilosavljevic/Desktop/football-tactics/docs/experiments/android-capture-feasibility.md), [workspace](/Users/urosmilosavljevic/Desktop/football-tactics/.moon/workspace.yml).
