#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
BUILD_DIR="$ROOT_DIR/build"
MAIN_CLASSES="$BUILD_DIR/classes"

rm -rf "$BUILD_DIR"
mkdir -p "$MAIN_CLASSES"

find "$ROOT_DIR/src/main/java" -name "*.java" | sort > "$BUILD_DIR/main-sources.txt"

javac -encoding UTF-8 -d "$MAIN_CLASSES" @"$BUILD_DIR/main-sources.txt"

printf "Build completado en %s\n" "$MAIN_CLASSES"
