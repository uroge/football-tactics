# Python vision development

Use Python 3.13 and uv. The `.python-version` file selects the Python minor version, and `uv.lock` pins the development dependencies. The setup was verified on Apple Silicon with Python 3.13.7 and uv 0.13.8.

From `apps/vision-server`:

```sh
uv sync --locked
uv run --locked pytest
uv run --locked ruff check .
uv run --locked mypy
```

From the repository root, Moonrepo runs the same checks:

```sh
moon run vision-server:test vision-server:lint vision-server:typecheck
```

The installed package is `football_vision`, under the standard `src` layout. Add the future FastAPI adapter under `football_vision.api` and keep frame analysis in modules under `football_vision` that recorded-video tools can call directly. The FastAPI adapter will call those modules and own connection handling. See the [vision pipeline design](../architecture/vision-pipeline.md).
