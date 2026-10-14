# Limitations

## Android compatibility
- Android 7.0+ is the minimum target for testing; actual compatibility depends on device hardware and Android version.
- Different OEM camera/gallery implementations may behave differently.

## Image recognition accuracy
- Heuristic chart detection may fail on low-resolution, rotated, blurred, or cropped images.
- Partial screenshots may not contain enough structure for a confident result.

## Offline processing
- This is a local processing prototype; it does not perform cloud inference.
- It is not a trade recommendation system and not a feasible replacement for professional chart analysis.

## Data handling
- Local data is stored only on device in Room DB or local browser storage.
- No external server or analytics service is used by design.
