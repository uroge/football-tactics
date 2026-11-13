# 18 · Decide whether a measured bottleneck warrants Rust

**What to build**
Use the live profile and a repeatable Python workload to decide whether a candidate operation should move to Rust.

**Blocked by**
[17 · Measure and adapt live performance on the TV and Mac](17-profile-and-adapt-live-analysis.md). representative workload for the candidate.

**Status**
Draft for owner review

**Modules and interfaces**
Tactical analysis and benchmark.

**Requirements**
P02, P03, F04. See the [coverage map](../coverage.md).

**Main risk**
Microbenchmark savings may disappear when transfer and integration are included.

## Acceptance criteria

- [ ] Establish Python reference results and an end-to-end benefit threshold. Consider geometry, interpolation, spatial indexing, transformations and tactical metrics only where measured. Account for data copying, packaging and integration costs.
- [ ] Record a keep-Python or proceed decision. If proceeding, identify a candidate and compare PyO3/maturin with a process adapter using measured representative overhead. Keep the existing Rust reservation if the evidence favors Python.

## Verification

Review the decision against repeatable profiles, reference outputs and benchmark artifacts. A proceed decision identifies which workload and parity and end-to-end criteria ticket 19 must meet.
