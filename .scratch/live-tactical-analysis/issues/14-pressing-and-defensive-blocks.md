# 14 · Show pressing structure and defensive blocks

**What to build**
Toggle pressing and defensive block estimates independently during supported play.

**Blocked by**
[09 · Align tactical graphics with the visible pitch](09-pitch-calibration.md).

**Status**
Draft for owner review

**Modules and interfaces**
Tactical analysis and overlay.

**Requirements**
P03, G10, G11, G15, O05. See the [coverage map](../coverage.md).

**Main risk**
Uncertain possession or player roles can distort pressing and block estimates.

## Acceptance criteria

- [ ] Document the possession, opponent, movement and team-shape evidence used by each estimate, and hide each layer when its required inputs are uncertain.

## Verification

Run reviewed examples of pressing, a settled block and ambiguous possession. Verify independent suppression, alignment and toggles on TV.
