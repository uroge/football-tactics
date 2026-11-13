# 19 · Migrate one proven bottleneck to Rust

**What to build**
Run the same user-visible annotation through a measured Rust implementation when it meets the agreed benefit threshold.

**Blocked by**
[18 · Decide whether a measured bottleneck warrants Rust](18-rust-decision.md) with proceed decision. [D06](../decisions.md#d06).

**Status**
Draft for owner review

**Modules and interfaces**
Tactical computation seam.

**Requirements**
P03, N02, F04, F05, O02. See the [coverage map](../coverage.md).

**Main risk**
Numeric drift and packaging costs can erase the benefit of a Rust implementation.

## Acceptance criteria

- [ ] Use a Cargo workspace and pinned vetted dependencies as needed, with deterministic typed outputs, benchmarks, rustfmt, Clippy, tests and Moon commands. Preserve the shared schema/semantics if Rust consumes the wire model.
- [ ] Verify parity against Python and ship the migration only if representative end-to-end benefit clears ticket 18's threshold with integration and packaging costs included.

## Verification

Run shared fixtures, benchmarks, native Rust checks and a live before/after TV/M1 run. Record evidence and decision. If ticket 18 keeps Python, close this conditional draft as out of scope with the ADR evidence.
