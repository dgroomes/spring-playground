#!/usr/bin/env bash
# Run the app

START_SCRIPT="$CACHING_ROOT_DIR/build/install/caching/bin/caching"

if [[ ! -f "$START_SCRIPT" ]]; then
  echo "The start script does not exist. Exiting. (hint: build the project with the 'build' command)" >&2
  exit 1;
fi

bash "$START_SCRIPT"
