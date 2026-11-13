# 05 · Evaluate team colors on recorded footage

**What to build**
Review recorded tracks with kit-based team labels and visible uncertainty.

**Blocked by**
[04 · Evaluate track continuity and camera-cut resets](04-offline-track-ids.md).

**Status**
Draft for owner review

**Modules and interfaces**
Vision analysis and offline evaluation.

**Requirements**
P03, V04, V06, O05, O06. See the [coverage map](../coverage.md).

**Main risk**
Similar kits and lighting can produce unreliable team labels.

## Acceptance criteria

- [ ] Evaluate kit appearance and clustering with lighting variation, broadcast grading and similar colors. Handle referee and goalkeeper kits and allow unknown team when evidence is ambiguous.
- [ ] Publish annotated examples and per-team/unknown quality and latency with documented thresholds. Keep the baseline callable without FastAPI.

## Verification

Run labeled clip and deterministic crop/track fixtures. Review hard cases and compare expected team or unknown labels and colors with the report.
