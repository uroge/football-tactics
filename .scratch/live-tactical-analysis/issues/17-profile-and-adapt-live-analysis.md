# 17 · Measure and adapt live performance on the TV and Mac

**What to build**
Run a representative live workload and see how adaptive sampling protects freshness under load.

**Blocked by**
[07 · Replay and disrupt the verified live session deterministically](07-replay-and-fault-regressions.md), [08 · Show selectable track IDs and team colors on TV](08-track-id-and-team-layers.md), [09 · Align tactical graphics with the visible pitch](09-pitch-calibration.md). [D05](../decisions.md#d05).

**Status**
Draft for owner review

**Modules and interfaces**
Live workload, benchmark and sampling.

**Requirements**
P03, A08, N06, F01, F02, F03, O06, O11. See the [coverage map](../coverage.md).

**Main risk**
Cross-device timing and changing workloads can make measurements incomparable.

## Acceptance criteria

- [ ] Agree clips, duration, reported percentiles, memory budgets and latency interpretation before measurement. Record device/build/runtime, network and model/config. Measure capture, encode, outbound transport, decode/inference, tactics, return transport, render and TV-clock capture-to-overlay. Use a documented method or bounds for per-direction network time across clocks.
- [ ] Report capture 5–10 FPS, input width 640–960 px, active Canvas 30–60 FPS, target under 300 ms and initial ceiling under 500 ms as measured goals. Record memory on both devices, queue high-water marks, frame age, drops and sustained overload. Measure CPU and viable M1 acceleration.
- [ ] Add and compare adaptive sampling and any measured encoding, serialization, LAN or rendering improvements. Under load, bound work and drop obsolete frames. Report failures against targets and give the owner evidence for trade-offs.

## Verification

Run repeatable baseline and adapted workloads on the reference TV and M1, including available Ethernet/Wi-Fi and induced slowdown. Compare the agreed percentiles and memory/queue curves and save reproducible benchmark commands and traces.
