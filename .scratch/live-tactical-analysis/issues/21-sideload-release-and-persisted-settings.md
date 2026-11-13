# 21 · Install and configure a reproducible personal release

**What to build**
Install a documented personal-use APK and Mac environment, retain layer preferences, and recover cleanly from expected errors.

**Blocked by**
[08 · Show selectable track IDs and team colors on TV](08-track-id-and-team-layers.md), [20 · Run native checks and schema validation through Moon and CI](20-fast-ci-and-dev-workflow.md). [D07](../decisions.md#d07).

**Status**
Draft for owner review

**Modules and interfaces**
TV install, controls and Mac setup.

**Requirements**
P03, A01, A05, A10, N01, N07, O02, O03, O09, O11, O12, O13. See the [coverage map](../coverage.md).

**Main risk**
Dependency, asset or signing differences can prevent repeatable installs and upgrades.

## Acceptance criteria

- [ ] Persist preferences without persisting capture grants or raw media. Keep start/stop and settings usable from the companion via D-pad. Report unsupported content and connection issues with actionable status. Review later remote controls only if the owner expands scope.
- [ ] Pin APK and Mac setup prerequisites, dependencies and asset checksums. Document build, sideload, upgrade, rollback, Mac startup, LAN privacy, data handling and license obligations. Define the reproducibility test, including what signing or build metadata differences are allowed.

## Verification

Build from two clean environments under the documented definition. Install and upgrade on TV, check preference persistence, fresh consent and recovery, and inspect logs, files and outbound connections for unwanted data.
