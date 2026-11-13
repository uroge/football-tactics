# Tactical engine

- Reserve this component for operations justified by profiling. Capture a representative workload and a Python reference result before migrating an operation.
- Use Cargo workspace conventions, rustfmt and Clippy. Prefer vetted crates such as serde and nalgebra when they fit the job.
- Keep numeric operations deterministic and typed. Verify parity against the reference implementation and measure integration overhead before choosing PyO3/maturin or a process boundary.
