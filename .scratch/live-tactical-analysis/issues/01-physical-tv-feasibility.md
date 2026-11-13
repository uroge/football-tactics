# 01 · Prove capture and overlay on the physical TV

**What to build**
On the owner's TV, watch authorized, unprotected moving video in a separate playback app while Football Tactics captures usable frames, draws a static mark, and leaves playback controls working.

**Blocked by**
[Android setup evidence](../decisions.md#e01) and owner approval to start Phase 1 ([D01](../decisions.md#d01)) are satisfied. Physical verification requires ADB access to the reference ZTE MediaBox 12 and observation with its remote.

**Status**
Implementation and code review complete. Physical verification blocked on ADB connectivity to the reference box. The owner confirmed any compatible separate playback app is sufficient. See the [experiment and emulator evidence](../../../docs/experiments/android-capture-feasibility.md).

**Modules and interfaces**
TV session and platform adapters.

**Requirements**
P01, P02, P03, A01, A02, A03, A04, A05, A06, A07, A10, A12, O04, O08, O11. See the [coverage map](../coverage.md).

**Main risk**
Device or app blocks capture or overlay, and overlay pixels may be included in the capture.

## Acceptance criteria

- [ ] Record TV model, firmware, OS/API level, resolution, APK build/target SDK, playback app/version, clip permission, and which capture modes are offered. Identify any required playback app with the owner. Test overlay on the launcher and over playback, capture alone, then simultaneous capture and overlay. Repeat with display and app capture where offered. Check D-pad play/pause, seek where available, Back and app navigation.
- [ ] Use public overlay permission and fresh MediaProjection consent per session. Start the correctly typed foreground service from the visible app after consent, register projection stop handling before creating the virtual display, and acquire and close images through ImageReader. Show capture status and an accessible Stop control. Inspect movement and usable video pixels in memory.
- [ ] Deny each permission separately. Test Stop, system consent revocation, app switching, service termination and process death. Confirm the overlay clears, acquisition stops, display/reader/projection and window resources are released, and restart requires fresh consent. Test a controlled secure window and record the unavailable protected pixels. Record an unavailable check explicitly with its reason.
- [ ] Toggle the static mark while capturing in each viable mode. Record contamination separately from capture and overlay success. A result may pass the physical gate with contamination recorded, but live inference requires ticket 02 to prove clean inputs.
- [ ] Record the outcome in the existing experiment with per-case evidence and a go/no-go. A required playback app blocking either capability is a stop. If required-app status is undecided, record provisional evidence and obtain the owner's product direction before unlocking dependent work. A different working playback app cannot clear a required-app failure.

## Verification

Use ADB sideload and lifecycle diagnostics, compare in-memory frame observations, and observe playback, indicator and D-pad directly on the TV. Run session transition tests and available lifecycle instrumentation. Save device metadata and redacted evidence in the experiment. Record elapsed capture stop and window removal observations and evidence of teardown on each termination path. Emulator checks supplement the TV run.
