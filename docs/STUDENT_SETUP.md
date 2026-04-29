# Guía de instalación para estudiantes

## Objetivo

Evitar que el examen se convierta en un problema de entorno. Esta base fue diseñada para funcionar con **javac** sin Maven ni Gradle.

## Requisitos mínimos

- **Java mínimo:** 8
- **Java recomendado:** 17 LTS
- `javac` disponible en terminal

## Verificación rápida

```bash
java -version
javac -version
```

## IDEs recomendados

### Opción 1: VS Code

Instalar extensiones:

- Extension Pack for Java
- Language Support for Java by Red Hat
- Debugger for Java

### Opción 2: IntelliJ IDEA Community

- Abrir carpeta del proyecto
- Configurar JDK 8 o 17
- Ejecutar `Main.java`

### Opción 3: Eclipse

- Importar como proyecto Java existente
- Configurar JRE/JDK del workspace

## Compilar desde terminal

### Linux/macOS

```bash
./scripts/build.sh
./scripts/test.sh
```

### Windows

```bat
scripts\build.bat
scripts\test.bat
```

## Problemas comunes

### `javac: command not found`

No tenés el JDK configurado en `PATH`.

### La GUI no abre

Probablemente estás en entorno headless o remoto. Usá modo CLI:

```bash
java -cp build/classes org.umg.compilerjava.Main examples/query1.sql
```

### Mi IDE usa una versión distinta de Java

Usá Java 17 si lo tenés. Si no, esta base fue escrita para mantener compatibilidad con Java 8.

## Ejecutar pruebas en IDE (sin JUnit)

Esta base no usa JUnit. Si el IDE marca error al correr cada clase de test por separado, ejecutar:

- Clase principal: `org.umg.compilerjava.tests.RunAllTests`
- Archivo: `tests/org/umg/compilerjava/tests/RunAllTests.java`

Alternativa segura: correr siempre `scripts/test.bat` o `./scripts/test.sh` desde terminal.
