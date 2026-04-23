#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
BUILD_DIR="$ROOT_DIR/build"
MAIN_CLASSES="$BUILD_DIR/classes"
TEST_CLASSES="$BUILD_DIR/test-classes"

"$ROOT_DIR/scripts/build.sh"

mkdir -p "$TEST_CLASSES"
find "$ROOT_DIR/tests" -name "*.java" | sort > "$BUILD_DIR/test-sources.txt"

javac -encoding UTF-8 -cp "$MAIN_CLASSES" -d "$TEST_CLASSES" @"$BUILD_DIR/test-sources.txt"

java -cp "$MAIN_CLASSES:$TEST_CLASSES" org.umg.compilerjava.tests.RunAllTests
