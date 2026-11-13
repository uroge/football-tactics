# Vision pipeline

| Stage | Proposed approach | Evaluate |
| --- | --- | --- |
| Decode and validate | OpenCV and NumPy | Limits, resize metadata, invalid input |
| Detect | Pretrained YOLO on PyTorch | Player, referee and ball accuracy and latency |
| Track | Existing ByteTrack implementation | ID continuity, occlusion and camera-cut resets |
| Classify teams | Kit appearance with established clustering | Similar colors, referee and goalkeeper handling |
| Calibrate pitch | OpenCV homography with pitch landmarks | Reprojection error, partial views and camera motion |
| Produce annotations | Typed player circles first, then IDs and team color | Frame alignment, confidence and latency |

Run the same stages offline and from the live server. Keep session state explicit. Benchmark on the M1 using authorized footage and record the selected model, runtime, input sizes and licenses. Evaluate CPU and available acceleration with measurements. Keep FastAPI responsible for connections, session lifecycle and input limits.
