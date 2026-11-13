# Project requirements

## Agreed scope

- Build a personal, sideloaded Android TV companion. Use the owner's existing physical TV and a MacBook Air M1 on the same local network. Record the TV model, Android version and playback app during the spike. A required streaming service has yet to be named.
- The viewing experience uses an existing playback app. First prove simultaneous capture of authorized unprotected playback, visible overlay and functional playback remote controls. A required app blocking either capability triggers a stop and an owner decision on the product direction.
- The initial spike uses local frame inspection, visible capture status, an easy stop control, consent revocation and teardown checks, and an overlay contamination check.
- Later provide per-layer controls, start and stop controls, local-network analysis, and player circles, track IDs and team highlighting. Add pitch geometry and tactical metrics after the initial overlay is reliable.

## Functional requirements

- Request capture consent per session and overlay permission through supported Android APIs. Honor denied or revoked permissions, device and app restrictions, DRM, HDCP and secure windows. Use public APIs and authorized content only.
- End capture and clear the overlay on stop, revocation or terminal failure. Surface unsupported capture and connection problems. Preserve normal D-pad playback controls.
- Keep capture and analysis session state explicit. Correlate every result to its frame and session. Suppress stale or spatially unreliable graphics, including after camera cuts, resize and reconnection.
- Authenticate TV-to-Mac sessions, restrict the listening interface and clients, and bound frame sizes, rates and queues. Keep raw video transient by default and exclude video contents and credentials from logs.
- Evaluate licensed footage with deterministic replay and record device/app compatibility separately from vision accuracy.

## Initial engineering targets

| Metric | Target |
| --- | --- |
| Capture sampling | 5–10 FPS |
| Inference input width | 640–960 px |
| Overlay rendering when active | 30–60 FPS |
| Capture-to-overlay latency | Under 300 ms target and under 500 ms initial ceiling |
| Resource usage | Measured memory, bounded queues, stale frames dropped |

Measure capture, encoding, transport, inference, tactical processing and rendering on the reference setup. Choose reporting percentiles and evaluation clips before benchmarking. These targets await measurement.
