#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
BUILD_DIR="$ROOT_DIR/build"
MAIN_CLASSES="$BUILD_DIR/classes"
MAIN_SOURCES="$BUILD_DIR/main-sources.txt"

if ! command -v javac >/dev/null 2>&1; then
  echo "Error: javac no esta disponible en PATH."
  exit 1
fi

rm -rf "$BUILD_DIR"
mkdir -p "$MAIN_CLASSES"

find "$ROOT_DIR/src/main/java" -type f -name "*.java" | sort > "$MAIN_SOURCES"
if [[ ! -s "$MAIN_SOURCES" ]]; then
  echo "Error: no se encontraron archivos Java en src/main/java."
  exit 1
fi

javac -encoding UTF-8 -d "$MAIN_CLASSES" @"$MAIN_SOURCES"

printf "Build completado en %s\n" "$MAIN_CLASSES"
