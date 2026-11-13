# 20 · Run native checks and schema validation through Moon and CI

**What to build**
Build and verify the live companion from a fresh checkout with documented native and Moon commands and GitHub Actions.

**Blocked by**
[07 · Replay and disrupt the verified live session deterministically](07-replay-and-fault-regressions.md). [19 · Migrate one proven bottleneck to Rust](19-conditional-rust-migration.md) only for adding Rust CI if activated.

**Status**
Draft for owner review

**Modules and interfaces**
Developer and CI workflow.

**Requirements**
P03, P04, N02, F05, O01, O02, O04, O05, O07, O09, O10, O12. See the [coverage map](../coverage.md).

**Main risk**
Incorrect cache inputs or toolchain drift can hide failing builds.

## Acceptance criteria

- [ ] Run Python lint, types and unit tests, Android unit tests and debug APK build, and shared schema/example checks. Add Rust fmt, Clippy, tests and benchmark command when Rust is active. Document the Mac development workflow with Android Studio, VS Code or Cursor, ADB, TV emulator and physical device, native equivalents, and Moon commands including Mac server dev and protocol validation.
- [ ] Pin toolchains/dependencies and check Moon syntax against docs for the installed version. Use cache where safe and verify task dependencies. Keep expensive vision, GPU and hardware suites separately invoked. Document whether a combined dev command is practical. Keep orchestration free of unnecessary Node setup.

## Verification

Run on a clean checkout and inspect a GitHub Actions run. Prove a deliberately invalid schema/fixture or failing check is detected. Verify native equivalence and record cache and tool versions.
