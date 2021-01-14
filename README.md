## Overview
Simple console implementation of _Conway's Game of Life_.

## Build/Compile
Execute command `gradle build` 
from project root.

## Running
Execute script `./start.sh` from project root. This will run the application with a default configuration:
- 20 rows
- 20 columns
- A probability that a cell is alive at seeding of 0.3
- 20 iterations (generations)

It is possible to override the configuration with by providing optional arguments:
 - ``-r`` (rows, `Integer > 0`)
 - ``-c`` (columns, `Integer > 0`)
 - ``-p`` (probability, `0 <= Double <= 1`)
 - ``-i`` (iterations, `Integer > 0`)

Example: `./start.sh -r 100 -c 100 -p 0.33 -i 100`