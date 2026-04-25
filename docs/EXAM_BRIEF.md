# Examen práctico - Migración de compiler-cpp a Java

> **Antes de empezar:** apoyate en `STUDENT_ASSIGNMENTS.md` para encontrar tu tarea, en `TASK_CONTEXT_GUIDE.md` para entender qué debés hacer y en `GIT_GUIDE.md` para el flujo correcto de Git/GitHub.

## Datos generales

- **Curso:** Compiladores
- **Sección:** 035 A
- **Duración:** 100 minutos
- **Valor total:** 10 puntos
- **Modalidad:** trabajo individual con integración por Pull Request

## Objetivo

Cada estudiante debe contribuir a una migración parcial del compilador SQL desde C++ hacia Java, trabajando sobre esta base oficial.

La evaluación busca comprobar:

- comprensión del proyecto original en C++
- capacidad de migración a Java
- manejo correcto de Git y Pull Requests
- entrega verificable y defendible

## Líderes del proceso

Los siguientes estudiantes NO reciben tarea técnica individual. Su rol es operativo:

- **JOSHUA ABINADÍ CIRILO ALEGRÍA**
- **JOSUÉ MANUEL MARTÍNEZ PEDROZA**

### Responsabilidades de los líderes

- revisar PR
- aprobar o rechazar PR
- crear issues correctivos si aplica
- informar al alumno qué debe corregir
- mergear a `main` únicamente cuando el PR esté correcto

## Flujo obligatorio

1. actualizar `main`
2. crear rama personal desde `main`
3. implementar la tarea asignada
4. hacer commit claro
5. hacer push
6. abrir Pull Request contra `main`

## Formato de rama

```text
feature/nombre-apellido-tarea
```

## Formato de commit sugerido

```text
feat: implementa login ui en java
```

o

```text
refactor: migra token cpp a token java
```

## Reglas

- no trabajar directo en `main`
- no abrir PR vacío
- no cambiar tareas de otros compañeros
- no eliminar scripts base del proyecto
- no romper compilación general de la base

## Criterio de calificación

### Nota máxima: 10 puntos

- **10 puntos**: cumplimiento completo y correcto
- **Punteo parcial**: según avance verificable

### Referencia sugerida

- **3 puntos**: flujo Git correcto
- **4 puntos**: cumplimiento técnico de la tarea
- **2 puntos**: calidad mínima y coherencia
- **1 punto**: capacidad de explicar lo realizado

## Validación mínima antes de PR

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

## Nota tecnica de pruebas

Esta base usa pruebas en Java puro (sin JUnit ni Maven/Gradle).

Comandos oficiales:

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

Runner de pruebas:
- Clase principal: `org.umg.compilerjava.tests.RunAllTests`
- Ubicacion: `tests/org/umg/compilerjava/tests/RunAllTests.java`
