# 06 · Show the first authenticated live player circles

**What to build**
Pair the TV to the Mac, watch permitted footage, and see fresh player circles over playback. Keep schema, client, server and rendering work in one implementation chat.

**Blocked by**
[02 · Keep analysis frames clean and session-scoped](02-safe-capture-for-analysis.md), [05 · Evaluate team colors on recorded footage](05-offline-kit-classification.md). [D04](../decisions.md#d04).

**Status**
Draft for owner review

**Modules and interfaces**
TV, shared protocol and Mac analysis.

**Requirements**
P02, P03, A01, A04, A09, A10, A12, N01, N02, N03, N05, N06, N07, V01, V06, V07, G01, F02, F03, O02, O04, O05, O09, O11. See the [coverage map](../coverage.md).

**Main risk**
Frame ages, reconnect generations and display transforms can diverge across the two apps.

## Acceptance criteria

- [ ] Establish a persistent authenticated local WebSocket using OkHttp and FastAPI/Pydantic, narrow bind and restricted clients. Confirm pairing, encryption/trust, credential lifetime, timeouts, rate/resource and payload limits before sending footage. Reject unauthorized sessions and malformed or oversized inputs before expensive work.
- [ ] Verify v1 session.start, session.stop, session.status, frame.submit, analysis.result, analysis.error and heartbeat across TV and Mac. Use binary compressed images with documented envelope and JSON control/results, explicit version, session ID, increasing frame sequence, and echoed TV session-relative monotonic nanoseconds. Mac processing durations are Mac-local. Maintain shared schemas, examples and compatibility fixtures.
- [ ] TV correlates results to the active session and submitted frame, rejects out-of-order, expired, prior-session and invalidated geometry results. Document the configurable maximum result age. Clear the last visible result when it ages out even if no new results arrive. Clear all annotations on disconnect. Bound TV send, Mac pending/in-flight and TV result work. Start with one latest pending Mac frame per session. Time out a silent connection and reconnect into a fresh session without reusing invalid projection consent.
- [ ] Draw player circles via the non-focusable Canvas overlay. Define image origin, resize/crop metadata and video-to-overlay transform. Align on the reference clip with normal, letterboxed and pillarboxed playback. Invalidate on resize or uncertain bounds. Provide a D-pad-accessible global overlay toggle and session status.

## Verification

Exercise the actual exchange on TV/M1 and save one correlated trace with frame age and control/error results. Run Kotlin/Python shared-fixture tests and end-to-end checks for invalid credentials, version, size, ordering, slow consumer, heartbeat timeout, reconnect and overlay placement.
