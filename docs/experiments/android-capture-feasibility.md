# Android TV capture and overlay experiment

**Status** Emulator checks recorded. Physical gate pending access to the reference Android TV box.

## Setup

Use the owner's physical TV, its normal remote, a separate playback app and an authorized unprotected local clip with visible movement. Record model, OS/API level, firmware, playback app and version, app target SDK, capture mode, screen resolution and consent behavior. Use USB for the sideload if the box permits it. Use ADB for diagnostic logs when available. Record only test metadata by default.

## Run

| Check | Expected observation |
| --- | --- |
| Overlay only | A static visible mark appears over playback. D-pad playback controls and app navigation still work. |
| Capture only | In-memory inspection confirms changing video content from the playback app. |
| Combined | Moving playback remains capturable while the mark is visible and playback controls respond. |
| Contamination | Toggle the mark and observe whether it appears in frames. Repeat per available capture mode and record whether a clean-frame path works. |
| Permission denial | Deny capture and overlay permissions separately. Report the failure with no active capture or leftover window. |
| Stop and revoke | Use the app stop control and available system revocation control in separate runs. Capture ends, the window disappears, and projection resources are released. |
| Restart and app switch | Restart with fresh consent, switch apps, and verify cleanup on termination. Record the scope of frames in each capture mode. |
| Protected content | Use a controlled secure-window test if available. Record that protected pixels are unavailable. |

## Decision

Pass when the reference setup demonstrates simultaneous capture, overlay and working D-pad controls, with usable stop and revocation paths and a recorded contamination outcome. Any unresolved contamination must be addressed before live inference. Record each row with its observed result, status and evidence. If a required playback app blocks either capture or overlay, stop and bring the finding to the owner for a direction decision.


## Evidence from 2026-11-13

### Reference hardware

The owner reported a Samsung display driven by a Yettel ZTE MediaBox 12 running Android 12. The owner confirmed that any compatible separate playback app is sufficient. Wireless ADB did not discover the box and connecting to its reported address on port 5555 returned `No route to host`. Sideload, box firmware, API level, display resolution, installed playback app/version, and capture modes remain unverified on the hardware. Every physical run case above remains open for this reason.

### Emulator

Android TV emulator `sdk_google_atv64_arm64`, API 34, 1920×1080. The consent dialog offered display capture. The build was debug APK 0.1.0 (1), target SDK 35, from `a6b6fc0`, SHA-256 `ebc909e7758dfcccdf88294d2b9dc2d512b7a3bdc236a6d560aa938b4034c92d`. Gradle assemble, lint and all three JVM session tests passed.

A temporary separate playback app (`com.footballtactics.fixture`, version code unset) used Android `VideoView` to play an FFmpeg-generated 640×360 grayscale `testsrc2` clip, with no third-party footage. Its secure variant added `FLAG_SECURE`. Diagnostics used ADB input, `dumpsys window windows`, `dumpsys media_projection`, `dumpsys activity services`, `dumpsys media.player` and `logcat -s FootballTactics`.

| Case | Emulator observation |
| --- | --- |
| Overlay alone | The foreground service reported its overlay-only type. The 192×56 application-overlay window was present while playback kept D-pad input. Media player diagnostics showed pause and resume across D-pad Center and Right inputs. Home and Back were exercised. Seek position was not measured. |
| Capture alone | Playback samples at 960×540 showed 6–8% changed luma samples and `markCandidate=false` on grayscale footage. |
| Combined and contamination | Playback with the overlay had 7–15% changed luma samples and `markCandidate=true`. Window diagnostics showed the mark registered over playback and after returning Home. Colored synthetic footage sometimes matched the signature with the mark off, so the comparison uses grayscale footage with a mark-off baseline. |
| Denials and restart | Overlay permission was set to denied on the emulator. The app opened Android overlay settings and left no active service. Back on the capture dialog returned the app to idle with a consent-declined message. Restart opened a fresh consent dialog. Android notification permission was requested through its system dialog. |
| App Stop and overlay revocation | With the mark attached, Stop reported `captureReleased=true overlayRemoved=true cleanupMs=36`. Overlay permission revocation during capture reported the same release flags in 65 ms. Both left no active projection. These times measure in-process cleanup. |
| App switch and force-stop | Display capture continued across app switches. ADB force-stop during combined playback left no projection, overlay window or service in the immediate follow-up diagnostics. A force-stopped process cannot report its own cleanup timing. Overlay-only force-stop also removed the window and relaunch showed idle. |
| Secure window | The same clip in a controlled secure activity produced sustained 0% lit and 0% motion in sampled video pixels. The independent overlay signature was still detectable. |
| Unavailable or pending | The emulator consent UI offered no app-only mode. A shell service-stop attempt could not stop the non-exported service. The emulator run did not exercise system projection revocation or an independent process-death path. Physical notification visibility and remote usability still need direct observation. |

### Gate decision

**Pending.** The emulator demonstrated simultaneous display capture and a registered overlay and found display-capture contamination. The reference hardware run is required before tickets 02 and 03 can advance. The box prompts for a password on the Developer page. The owner will try installing the debug APK from USB through Android’s package installer. If installation succeeds, use the physical remote and an authorized, unprotected clip to begin the matrix above. Record any checks that remain unavailable without ADB. Record what the box actually offers before deciding which modes to repeat.
