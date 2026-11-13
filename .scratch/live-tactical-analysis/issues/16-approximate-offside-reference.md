# 16 · Show an approximate offside reference line

**What to build**
Toggle a clearly approximate offside reference over reliably calibrated play.

**Blocked by**
[09 · Align tactical graphics with the visible pitch](09-pitch-calibration.md).

**Status**
Draft for owner review

**Modules and interfaces**
Tactical analysis and overlay.

**Requirements**
P03, G14, G15, O05. See the [coverage map](../coverage.md).

**Main risk**
Incomplete timing, ball or defender observations can make an offside reference misleading.

## Acceptance criteria

- [ ] Document required inputs for attacking direction, defenders, ball and relevant timing. Hide the line when required evidence is missing or uncertain. Label it as a reference and avoid adjudicating an offence.

## Verification

Use reviewed scenes for ball/defender position, direction flips, partial views and cut transitions. Verify label, suppression and alignment on TV.
