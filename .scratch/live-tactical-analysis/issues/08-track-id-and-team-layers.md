# 08 · Show selectable track IDs and team colors on TV

**What to build**
Use the D-pad in Football Tactics to toggle circles, track IDs and team colors independently while playback keeps its own controls.

**Blocked by**
[06 · Show the first authenticated live player circles](06-first-live-player-circles.md).

**Status**
Draft for owner review

**Modules and interfaces**
TV controls, protocol and vision annotations.

**Requirements**
P03, A10, N04, V03, V04, V07, G02, G03, O04. See the [coverage map](../coverage.md).

**Main risk**
Labels can outlive their shot or make independent controls difficult to verify.

## Acceptance criteria

- [ ] Render shot-local IDs and team highlighting from typed frame-correlated annotations, with a neutral fallback for unknown teams. Clear IDs and stale annotations across cuts and replays. Preserve overlay focus behavior and a global off switch.
- [ ] Validate overlay configuration and layer independence. Implement and verify overlay.configure only where a setting changes server behavior, with supported values and defined invalid or unsupported responses. Record its disposition in the protocol.

## Verification

Use shared fixtures for layer combinations, unknown team, cuts and unsupported configuration. Demonstrate the toggles and playback focus on the TV and verify the configuration exchange when server behavior changes.
