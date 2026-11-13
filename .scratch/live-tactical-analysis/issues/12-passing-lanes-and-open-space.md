# 12 · Show estimated passing lanes and open space

**What to build**
Toggle passing lane estimates and available-space graphics over calibrated play.

**Blocked by**
[09 · Align tactical graphics with the visible pitch](09-pitch-calibration.md).

**Status**
Draft for owner review

**Modules and interfaces**
Tactical analysis and overlay.

**Requirements**
P03, G07, G08, G15, O05. See the [coverage map](../coverage.md).

**Main risk**
Unobserved players or the ball can make an apparently clear lane unreliable.

## Acceptance criteria

- [ ] Define inputs, visibility limits and confidence for each layer, including when ball position or team/direction matters. Limit estimates to reliably observed areas and suppress when evidence is insufficient.

## Verification

Replay annotated blocked/open lanes and partial views. Check projected graphics and separate toggles on TV and review estimates on licensed footage.
