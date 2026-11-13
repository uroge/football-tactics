# 13 · Show formation estimates

**What to build**
Toggle a formation estimate when enough reliable team observations are available.

**Blocked by**
[09 · Align tactical graphics with the visible pitch](09-pitch-calibration.md).

**Status**
Draft for owner review

**Modules and interfaces**
Tactical analysis and overlay.

**Requirements**
P03, G09, G15, O05. See the [coverage map](../coverage.md).

**Main risk**
A partial view can make a formation estimate ambiguous.

## Acceptance criteria

- [ ] Define observation window, team roles and minimum confidence. Report uncertainty or suppress when partial visibility, motion, or cuts invalidate the estimate.

## Verification

Use known formation fixtures and clips with partial coverage and shape changes. Check reset, labeling and layer toggle on TV.
