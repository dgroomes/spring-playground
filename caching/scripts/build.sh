#!/usr/bin/env bash
# Build and 'install' the project. Installing the project means that a start script will be generated.
# The tests are skipped.

"$CACHING_ROOT_DIR"/../gradlew -p "$CACHING_ROOT_DIR" installDist -x test
