# Android TV capture and overlay experiment

**Status** Planned. Physical-device results pending.

## Setup

Use the owner's physical TV, its normal remote, a separate playback app and an authorized unprotected local clip with visible movement. Record model, OS/API level, firmware, playback app and version, app target SDK, capture mode, screen resolution and consent behavior. Use ADB for the sideload and diagnostic logs. Record only test metadata by default.

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
