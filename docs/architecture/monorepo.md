# Monorepo

| Moon project | Directory | Native toolchain |
| --- | --- | --- |
| `android-tv` | `apps/android-tv` | Gradle Kotlin DSL and Android Studio |
| `vision-server` | `apps/vision-server` | `uv`, pytest, Ruff and a type checker |
| `protocol` | `packages/protocol` | Schema validation and shared compatibility fixtures |
| `tactical-engine` | `crates/tactical-engine` | Cargo workspace when Rust is needed |

The workspace map follows the documented Moonrepo project-map syntax. Register tasks when their underlying commands exist. Introduce Android build and test tasks first, then Python checks, schema validation and Rust checks as each component becomes active. Keep GitHub Actions workflows tied to runnable tasks and reserve GPU and physical-TV checks for explicit runs.

The toolchain versions, wrappers, lockfiles and CI configuration will come from native tools when each component starts. [Moon workspace reference](https://moonrepo.dev/docs/config/workspace) · [Moon project and task reference](https://moonrepo.dev/docs/config/project)
