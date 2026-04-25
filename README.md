# Compilador SQL Java Base

Base oficial para la evaluación práctica de migración del proyecto `compiler-cpp` hacia Java.

> **Apoyo importante para estudiantes:** si necesitás más contexto sobre tu tarea, empezá por `docs/EXAM_BRIEF.md`, `docs/STUDENT_ASSIGNMENTS.md`, `docs/TASK_CONTEXT_GUIDE.md` y `docs/GIT_GUIDE.md`.

## Objetivo

Este repositorio sirve como punto de partida para que los estudiantes trabajen sobre una base Java **compilable, documentada y verificable**, manteniendo el enfoque didáctico del compilador SQL original en C++.

## Qué incluye la base

- Migración funcional del flujo principal del compilador:
  - lexer
  - parser
  - AST
  - tabla de símbolos
  - análisis semántico
- Interfaz simple en **Swing** para evitar dependencias externas como JavaFX.
- Scripts de compilación y pruebas sin Maven ni Gradle.
- Suite mínima de pruebas automáticas en Java puro.
- Documentación para estudiantes, líderes y configuración de IDEs.

## Decisión técnica principal

Se usa **Java puro + Swing + javac** para maximizar compatibilidad en laboratorios, laptops personales y múltiples IDEs.

- **Versión mínima soportada:** Java 8
- **Versión recomendada:** Java 17 LTS
- **Sin dependencias externas**

## Estructura

```text
compilador-sql-java-base/
├── src/main/java/org/umg/compilerjava/
│   ├── Main.java
│   ├── auth/
│   ├── compiler/
│   └── ui/
├── tests/
├── scripts/
├── docs/
└── .github/
```

## Compilar

### Linux / macOS

```bash
./scripts/build.sh
```

### Windows

```bat
scripts\build.bat
```

## Tabla de Símbolos (Symbol Table)

La implementación actual soporta:

- **Tablas**: Registro dinámico de tablas con `addTable()`, búsqueda con `findTable()` y `containsTable()`
- **Columnas**: Agregado con `addColumn()`, búsqueda con `findColumn()` y detección de duplicados
- **Validación semántica**: El `SemanticAnalyzer` verifica existencia de tablas y columnas en consultas SELECT

### API principal

```java
SymbolTable symbolTable = new SymbolTable();
symbolTable.addTable("usuarios");           // Agregar tabla
Table t = symbolTable.findTable("usuarios"); // Buscar tabla
t.addColumn("edad", DataType.INT);     // Agregar columna

// En el flujo del compilador
CompilerFacade facade = new CompilerFacade();
facade.compile("CREATE TABLE users (id INT, name VARCHAR);");
```

### Integración con el flujo

1. **Lexer** tokeniza `CREATE TABLE`, nombres de tablas, columnas y tipos (INT, FLOAT, VARCHAR)
2. **Parser** crea `CreateTableNode` con las definiciones de columnas
3. **CompilerFacade** registra la tabla y columnas en la SymbolTable
4. **SemanticAnalyzer** valida consultas contra las tablas registradas

## Ejecutar pruebas

### Linux / macOS

```bash
./scripts/test.sh
```

### Windows

```bat
scripts\test.bat
```

## Ejecutar la aplicación

### Modo GUI

```bash
java -cp build/classes org.umg.compilerjava.Main
```

### Modo CLI

```bash
java -cp build/classes org.umg.compilerjava.Main examples/query1.sql
```

## Credenciales demo

La base incluye dos usuarios de demostración para orientar el flujo:

- `admin@umg.local` / `admin123`
- `student@umg.local` / `student123`

## Archivos clave para la evaluación

- `docs/README.md` → índice general de documentación del examen
- `docs/EXAM_BRIEF.md` → enunciado oficial
- `docs/STUDENT_ASSIGNMENTS.md` → asignación de tareas
- `docs/TASK_CONTEXT_GUIDE.md` → explicación detallada de cada tarea
- `docs/GIT_GUIDE.md` → guía Git/GitHub y apoyo para líderes con `git worktree`
- `docs/STUDENT_SETUP.md` → guía de instalación y uso para estudiantes
- `docs/LEADERS_PLAYBOOK.md` → guía operativa para líderes revisores
- `.github/pull_request_template.md` → plantilla obligatoria de PR

## Nota docente

Esta base NO reemplaza la evaluación. La idea es que los estudiantes trabajen sobre una estructura coherente y enfocada en la migración a Java, no en pelear con el entorno.
