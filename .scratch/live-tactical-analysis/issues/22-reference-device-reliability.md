# 22 · Verify compatibility and long sessions on the reference setup

**What to build**
Run the agreed personal-use release through a long viewing session on the reference TV and Mac and publish a compatibility matrix.

**Blocked by**
[10 · Show estimated defensive lines](10-defensive-lines.md), [11 · Show team compactness and player spacing](11-compactness-and-spacing.md), [12 · Show estimated passing lanes and open space](12-passing-lanes-and-open-space.md), [13 · Show formation estimates](13-formations.md), [14 · Show pressing structure and defensive blocks](14-pressing-and-defensive-blocks.md), [15 · Show movement trails and space occupation heatmaps](15-movement-trails-and-heatmaps.md), [16 · Show an approximate offside reference line](16-approximate-offside-reference.md), [17 · Measure and adapt live performance on the TV and Mac](17-profile-and-adapt-live-analysis.md), [18 · Decide whether a measured bottleneck warrants Rust](18-rust-decision.md), [21 · Install and configure a reproducible personal release](21-sideload-release-and-persisted-settings.md). [19 · Migrate one proven bottleneck to Rust](19-conditional-rust-migration.md) if activated. [D05](../decisions.md#d05).

**Status**
Draft for owner review

**Modules and interfaces**
Release verification.

**Requirements**
P03, A01, A02, A04, A05, A07, N07, V07, F01, F03, O06, O07, O08, O11, O12. See the [coverage map](../coverage.md).

**Main risk**
Firmware and network behavior over a long session can differ from the short gate run.

## Acceptance criteria

- [ ] Record model, OS/firmware, app/version, content type, capture mode, network and individual capture, overlay and playback input outcomes. Check launcher, unprotected playback, controlled protected playback, permission denial/revocation, app switching, network interruption, service/process termination and repeated sessions.
- [ ] Run the agreed soak with all delivered layers, resource/age thresholds and recovery criteria. Verify bounds, cleanup, alignment and layer behavior under cuts, resize and outages. Record evidence and owner disposition for failures, unsupported combinations or unmet targets.

## Verification

Run the signed-off physical matrix and soak profile and save redacted traces, memory and latency data and release build IDs. Review deterministic replay, licensed vision evaluation, reproducibility and CI evidence alongside physical compatibility.
