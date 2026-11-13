# Vision server

- Use Python 3.11 or newer with `uv`, typed interfaces, Ruff, pytest and one configured type checker. Use the `src/football_vision` package layout when code arrives.
- Use FastAPI and Pydantic for the network boundary. Keep detector, tracker and geometry independently callable from recorded video or fixtures.
- Evaluate pretrained PyTorch/YOLO, existing ByteTrack integration, OpenCV and NumPy before writing custom algorithms. Document model and asset provenance, hardware compatibility and evaluation results.
- Keep tracker state per session, reset it at camera cuts, and bound inference work. Follow the [vision design](../../docs/architecture/vision-pipeline.md) when adding a stage.
