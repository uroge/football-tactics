# 02 · Keep analysis frames clean and session-scoped

**What to build**
Produce a bounded stream of analysis-ready frames from the verified capture path while the overlay remains visible.

**Blocked by**
[01 · Prove capture and overlay on the physical TV](01-physical-tv-feasibility.md). [D02](../decisions.md#d02) if contamination or unrelated content remains unresolved.

**Status**
Draft for owner review

**Modules and interfaces**
TV session and frame acquisition.

**Requirements**
P03, A02, A03, A06, A07, A08, A12, F03, O04, O11. See the [coverage map](../coverage.md).

**Main risk**
Unrelated screens or overlay marks may enter frames, and teardown races can leave the capture active.

## Acceptance criteria

- [ ] Prove the chosen mode or mitigation prevents the app's annotations and unrelated screen content from reaching live analysis. Document what the selected mode captures, including app switches, dialogs and resize. Suspend frame submission where the permitted content bounds cannot be established.
- [ ] Expose explicit idle, permission, connecting, capture, analysis, stopping, error and unsupported transitions as they apply. Prevent double start, clear on permission loss, and make stop and failure cleanup idempotent.
- [ ] Resize and compress frames with source dimensions, crop/scale metadata, sequence and monotonic capture time. Cap capture and encode work, close every image, keep raw video transient, and omit video contents and secrets from logs.

## Verification

Run physical mark-on/off and unrelated-screen tests, resize and app-switch tests, plus unit and instrumentation checks for duplicate start, cancellation, failure and repeated teardown. Inspect heap and queue counters and verify the frame source stops emitting after cleanup.
