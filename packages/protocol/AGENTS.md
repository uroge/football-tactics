# Shared protocol

- Own versioned schemas, example messages and compatibility fixtures here. Follow the [protocol design](../../docs/architecture/realtime-protocol.md) when changing a message.
- Keep one schema source of truth. Use established schema validators and generation tools where they preserve semantics. Validate examples and verify client decoding against the same fixtures.
- Specify units, clock origin, coordinate space, bounds and compatibility for each field. Keep breaking changes versioned and reject unsupported versions explicitly.
