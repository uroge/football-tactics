# 15 · Show movement trails and space occupation heatmaps

**What to build**
Toggle recent player movement trails and space occupation heatmaps with bounded history.

**Blocked by**
[09 · Align tactical graphics with the visible pitch](09-pitch-calibration.md).

**Status**
Draft for owner review

**Modules and interfaces**
Tactical analysis and overlay.

**Requirements**
P03, G12, G13, G15, O05. See the [coverage map](../coverage.md).

**Main risk**
Unbounded histories or geometry changes can distort trails and density.

## Acceptance criteria

- [ ] Define sampling, accumulation interval, visible coverage and resets across cuts, sessions and calibration changes. Use trusted pitch positions and keep history transient.

## Verification

Run known paths and heatmap cell counts in replay. Measure memory with long input, verify reset and display both layers independently on TV.
