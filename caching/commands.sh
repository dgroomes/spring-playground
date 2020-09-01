#!/usr/bin/env bash

export CACHING_ROOT_DIR=$(pwd)

# Build (without the tests)
build() {
  "$CACHING_ROOT_DIR"/scripts/build.sh
}

# Run the app
run() {
  "$CACHING_ROOT_DIR"/scripts/run.sh
}