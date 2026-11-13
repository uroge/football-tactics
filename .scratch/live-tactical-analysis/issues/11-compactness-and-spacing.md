# 11 · Show team compactness and player spacing

**What to build**
View team compactness and inter-player distance as independent layers.

**Blocked by**
[09 · Align tactical graphics with the visible pitch](09-pitch-calibration.md).

**Status**
Draft for owner review

**Modules and interfaces**
Tactical analysis and overlay.

**Requirements**
P03, G05, G06, G15, O05. See the [coverage map](../coverage.md).

**Main risk**
Partial team visibility can skew spacing or compactness.

## Acceptance criteria

- [ ] Define compactness and distance units, player inclusion and minimum usable observations. Render each estimate only with trusted geometry and suitable tracks.

## Verification

Check known formations and metric distances, excluded outliers and missing players in deterministic replay. Verify labels, alignment and independent toggles on TV.
