# Real-time protocol

Proposed v1 for the integration phase. Schemas and wire fixtures arrive with that phase.

| Message | Direction | Purpose |
| --- | --- | --- |
| `session.start`, `session.stop` | TV → Mac | Start and stop authenticated analysis |
| `session.status`, `analysis.error` | Mac → TV | Report lifecycle and failures |
| `frame.submit` | TV → Mac | Submit a binary message with frame metadata and encoded image |
| `analysis.result`, `calibration.update` | Mac → TV | Return frame-correlated annotations and calibration |
| `overlay.configure` | TV → Mac | Request analysis options when server behavior is affected |
| `heartbeat` | Both | Check session liveness |

Use JSON for control and result messages. Include a protocol version and session ID. Every submitted frame has a strictly increasing sequence within the session and a TV session-relative monotonic capture timestamp in nanoseconds. Echo the correlation fields in results. Compute capture-to-render age on the TV. Report Mac processing duration separately.

Use normalized coordinates relative to the submitted image with the origin at top left. Carry dimensions and source bounds when a frame is cropped or resized. Pitch coordinates, when present, use metres with a documented origin and axis orientation. Define the binary envelope, codec and limits before implementing transport.

Use one bounded pending-frame slot per session initially and replace pending frames with newer ones. Reject invalid payloads before decoding. Reconnect with a new session and clear pending annotations. Choose and document pairing, encryption, timeouts, and size limits before sending frames over the LAN.
