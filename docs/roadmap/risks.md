# Risks

| Risk | Validation or response |
| --- | --- |
| TV or playback app blocks capture or overlays | Test each capability independently and together on the physical reference setup. Apply the agreed stop decision for a required app. |
| Captured frames include the overlay or unrelated screen content | Compare available capture modes. Prefer the narrowest mode that works and require usable frames before live analysis. |
| Projection teardown fails on revocation or process exit | Run repeated start/stop and revocation scenarios. Verify resource and overlay state. |
| LAN traffic exposes screen contents | Decide authenticated transport and encryption before integration. Bound clients, frame sizes and rates. |
| Inference lags on the M1 or Wi-Fi | Measure end-to-end frame age, drop stale frames, and tune input width and sampling. |
| Camera cuts or calibration cause misplaced graphics | Reset tracking, invalidate geometry and suppress unreliable annotations. |
| Weights and footage carry incompatible terms | Verify licenses and provenance before acquiring or using assets. |

Open inputs for the spike are the TV model and software version, the chosen test playback app, and whether a specific streaming app is required.
