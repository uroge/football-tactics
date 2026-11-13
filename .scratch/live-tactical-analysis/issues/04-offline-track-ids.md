# 04 · Evaluate track continuity and camera-cut resets

**What to build**
Review recorded clips with stable player track IDs within a continuous shot and safe behavior through occlusions, motion and camera cuts.

**Blocked by**
[03 · Evaluate pretrained detection on licensed footage](03-licensed-player-detection.md).

**Status**
Draft for owner review

**Modules and interfaces**
Vision analysis and offline evaluation.

**Requirements**
P03, V03, V06, V07, O05, O06. See the [coverage map](../coverage.md).

**Main risk**
Occlusions and camera changes can fragment or misidentify tracks.

## Acceptance criteria

- [ ] Use ByteTrack initially and document track confidence, short occlusion handling and camera movement behavior. Keep tracker state per analysis session. Detect shot changes and replay transitions for the supported clips, reset tracker state at those events, and define when uncertain shots suppress identity. Verify the offline pipeline detects transitions from input frames, with explicit cut events also available as deterministic fixtures.
- [ ] Produce annotated output and a baseline for ID consistency, fragmentation or ID switches, cut stability and latency on annotated clips.

## Verification

Replay clips with occlusions, pans, cuts and replays. Run deterministic detection fixtures for track lifecycle and reset. Report metrics against the evaluation plan and demonstrate no identity promise across cuts.
