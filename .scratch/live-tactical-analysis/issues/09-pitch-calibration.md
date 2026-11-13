# 09 · Align tactical graphics with the visible pitch

**What to build**
Show a calibration-dependent pitch reference that remains aligned during camera movement and disappears when geometry is unreliable.

**Blocked by**
[07 · Replay and disrupt the verified live session deterministically](07-replay-and-fault-regressions.md), [08 · Show selectable track IDs and team colors on TV](08-track-id-and-team-layers.md). [D05](../decisions.md#d05).

**Status**
Draft for owner review

**Modules and interfaces**
Vision calibration, protocol and overlay.

**Requirements**
P03, A09, N04, N05, V03, V05, V07, G15, O04, O05, O06. See the [coverage map](../coverage.md).

**Main risk**
Partial landmarks can yield a plausible-looking but unreliable projection.

## Acceptance criteria

- [ ] Use visible pitch landmarks and OpenCV homography. Document the standard pitch coordinate origin, axes and units, confidence and limitations for partial pitch, pan, zoom and broadcast perspective.
- [ ] Verify calibration.update and result semantics with frame and geometry generation correlation. Invalidate after resize, cut, replay, missing landmarks or unreliable calibration. Reject delayed updates from previous geometry.
- [ ] Measure landmark/reprojection and pitch-coordinate errors on annotated footage. Publish thresholds, coverage and failure cases against the approved evaluation plan.

## Verification

Run deterministic projection/inverse fixtures and annotated camera movement and cut clips. Demo aligned calibration-dependent graphics and suppression on the TV.
