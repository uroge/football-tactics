# 01 · Set up the empty Python vision project

**What to build**  
A minimal Python vision package that a developer can install from a fresh checkout and verify on macOS using uv. Future vision code can be imported and tested independently of its network entry point.

**Blocked by**  
None (can start immediately).

**Status**  
ready-for-agent

## Acceptance criteria

- [ ] A fresh checkout syncs from a checked-in uv lockfile using a documented, supported Python version. The `football_vision` package imports from the installed environment using the standard `src` layout.
- [ ] The native verification commands pass an import smoke test, Ruff checks, and a configured type checker. Moonrepo can invoke those checks.
- [ ] The project docs show the setup and check commands and describe where the future FastAPI adapter and offline vision code will live. Keep the initial package small and add subpackages when code needs them.
- [ ] Importing the package starts no process, opens no listener, and reads no models or media. The setup includes no routes, protocol implementation, or inference dependencies.
