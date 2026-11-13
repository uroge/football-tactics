# System architecture

```text
Playback app → permitted capture → Android TV → local WebSocket → Mac vision service
                    Android overlay ← timestamped annotations ← vision pipeline
```

- [Android TV](android-tv.md) owns consent, frame capture, session state, D-pad controls and screen-space rendering.
- [Vision service](vision-pipeline.md) owns detection, tracking, team classification, calibration and tactical calculations.
- [Shared protocol](realtime-protocol.md) owns the wire contract and fixtures. Both apps depend on the contract and can run independently for local tests.
- [Monorepo](monorepo.md) coordinates the native toolchains. Rust has a reserved boundary for measured bottlenecks.

Physical compatibility is the first unknown. The [TV experiment](../experiments/android-capture-feasibility.md) tests capture and overlays independently and then together. [Requirements](../requirements.md) own product behavior and performance targets. [Decisions](../decisions/) record the trade-offs.
