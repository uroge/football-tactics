# 03 · Evaluate pretrained detection on licensed footage

**What to build**
Run licensed football footage through the vision pipeline and review frame-correlated detections and an accuracy baseline.

**Blocked by**
[01 · Prove capture and overlay on the physical TV](01-physical-tv-feasibility.md). [Python setup evidence](../decisions.md#e02). [D03](../decisions.md#d03).

**Status**
Draft for owner review

**Modules and interfaces**
Vision frame input and offline evaluation.

**Requirements**
P03, V01, V02, V06, O03, O05, O06. See the [coverage map](../coverage.md).

**Main risk**
Asset terms, M1 runtime support and small-object detection may limit the baseline.

## Acceptance criteria

- [ ] Record source, version/checksum, footage and annotation permissions, framework and weight terms, and intended use before selecting assets. Document reproducible acquisition and keep media and weights out of Git. Use pretrained PyTorch/YOLO and assess football-specific candidates on the M1.
- [ ] Decode, validate dimensions and encoded input, resize, normalize and preserve source metadata. Reject malformed input. Produce typed normalized player, referee and ball detections with confidence. Evaluate goalkeeper support separately if data allows.
- [ ] Run the same callable analysis from a recorded clip with annotated output. Measure per-class precision and recall and inference latency with declared test set, input width and Mac runtime.

## Verification

Use pytest for malformed inputs, normalization and metadata retention. Run the licensed annotated clips on M1 and save pinned configuration, metrics and reproducible artifact references. Review dependency support on the pinned Python version.
