#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
SQL_FILE="${SQL_FILE:-examples/query1.sql}"
WATCH_INTERVAL="${WATCH_INTERVAL:-1}"
RUN_TESTS="${RUN_TESTS:-false}"

cd "$ROOT_DIR"

snapshot() {
    find src tests examples scripts \
        -type f \
        \( -name "*.java" -o -name "*.sql" -o -name "*.sh" \) \
        -print0 2>/dev/null \
        | sort -z \
        | xargs -0 sha256sum 2>/dev/null || true
}

run_once() {
    if [ "$RUN_TESTS" = "true" ]; then
        ./scripts/test.sh
        return
    fi

    ./scripts/build.sh
    java -cp build/classes org.umg.compilerjava.Main "$SQL_FILE"
}

printf "Hot reload activo. RUN_TESTS=%s SQL_FILE=%s\n" "$RUN_TESTS" "$SQL_FILE"
printf "Editá archivos en src/, tests/ o examples/ para recompilar.\n\n"

last_snapshot=""

while true; do
    current_snapshot="$(snapshot)"

    if [ "$current_snapshot" != "$last_snapshot" ]; then
        last_snapshot="$current_snapshot"
        printf "\n[%s] Cambio detectado, compilando...\n" "$(date '+%H:%M:%S')"

        if run_once; then
            printf "[%s] Ejecución finalizada correctamente.\n" "$(date '+%H:%M:%S')"
        else
            printf "[%s] Falló la compilación o ejecución. Esperando cambios...\n" "$(date '+%H:%M:%S')"
        fi
    fi

    sleep "$WATCH_INTERVAL"
done
