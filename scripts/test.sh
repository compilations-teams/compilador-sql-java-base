#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
BUILD_DIR="$ROOT_DIR/build"
ALL_CLASSES="$BUILD_DIR/all-classes"
ALL_SOURCES="$BUILD_DIR/all-sources.txt"

if ! command -v javac >/dev/null 2>&1; then
  echo "Error: javac no esta disponible en PATH."
  exit 1
fi

if ! command -v java >/dev/null 2>&1; then
  echo "Error: java no esta disponible en PATH."
  exit 1
fi

mkdir -p "$BUILD_DIR"
rm -rf "$ALL_CLASSES"
mkdir -p "$ALL_CLASSES"

{
  find "$ROOT_DIR/src/main/java" -type f -name "*.java"
  find "$ROOT_DIR/tests" -type f -name "*.java"
} | sort > "$ALL_SOURCES"

if [[ ! -s "$ALL_SOURCES" ]]; then
  echo "Error: no se encontraron archivos Java en src/main/java o tests."
  exit 1
fi

javac -encoding UTF-8 -d "$ALL_CLASSES" @"$ALL_SOURCES"

java -cp "$ALL_CLASSES" org.umg.compilerjava.tests.RunAllTests
