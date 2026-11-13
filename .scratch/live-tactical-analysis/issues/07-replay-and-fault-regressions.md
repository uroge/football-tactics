# 07 · Replay and disrupt the verified live session deterministically

**What to build**
Reproduce live-session behavior using recorded or fixture frames and controlled failures.

**Blocked by**
[06 · Show the first authenticated live player circles](06-first-live-player-circles.md).

**Status**
Draft for owner review

**Modules and interfaces**
Frame input, session exchange and overlay.

**Requirements**
P03, A07, A09, A12, N02, N03, N05, N06, N07, V06, V07, F03, O04, O07, O08. See the [coverage map](../coverage.md).

**Main risk**
Nondeterministic inference or timing can hide ordering and cleanup regressions.

## Acceptance criteria

- [ ] Feed recorded frames through the same analysis, wire and overlay interfaces. Freeze inputs and timing or use deterministic inference fixtures for protocol assertions. Record tolerances for native model evaluation where applicable.
- [ ] Exercise duplicate, late and mismatched results, delayed decode/inference, backlog saturation, peer/server restart, heartbeat loss and reconnect with cleanup. Verify previous generations and stale graphics cannot reappear after reset, resize or terminal error.
- [ ] Verify coordinate mapping with golden landmarks under scaling, density, letterbox, pillarbox, cutout/overscan and resolution/aspect changes. Clear uncertain geometry and surface errors in TV UI.

## Verification

Run repeatable integration replay twice and compare normalized outputs and recorded timing decisions. Run Android unit and instrumentation checks and one physical link-loss/app-switch test, with an evidence trace and bounded-resource observations.
