# Analysis Algorithm

## Overview
This project uses local deterministic heuristics to inspect chart screenshots and give educational observations.

## Pipeline
1. Image fetch from gallery or local folder.
2. Resize and grayscale conversion.
3. Detect visual density and possible chart area.
4. Estimate candle-like vertical groups by scanning image columns.
5. Compute relative brightness and contrast around detected bodies.
6. Count rising/falling/sideways patterns within the visible chart.
7. Produce educational summary with confidence scoring.

## Important rules
- No price values, timestamps, or exact candle data are invented.
- If the image is unclear, return an "Insufficient information" result.
- All technical observations are framed as educational observations only, not financial advice.

## Limitations
- The algorithm is heuristic-based and dependent on image clarity.
- Complex chart styles or partial screenshots may reduce confidence.
- Support/resistance labels are conceptual only when visible evidence is present.
