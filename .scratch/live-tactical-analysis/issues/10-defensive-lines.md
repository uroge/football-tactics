# 10 · Show estimated defensive lines

**What to build**
Toggle a team defensive line that follows usable defender positions on the pitch.

**Blocked by**
[09 · Align tactical graphics with the visible pitch](09-pitch-calibration.md).

**Status**
Draft for owner review

**Modules and interfaces**
Tactical analysis and overlay.

**Requirements**
P03, G04, G15, O05. See the [coverage map](../coverage.md).

**Main risk**
Too few visible defenders can make the estimated line misleading.

## Acceptance criteria

- [ ] Define team/direction and confidence inputs. Suppress on insufficient or ambiguous tracks, unreliable calibration and scene resets. Expose the layer through TV settings and typed annotations.

## Verification

Verify synthetic line geometry and suppression in replay, then check TV alignment and toggling on a reviewed clip.
