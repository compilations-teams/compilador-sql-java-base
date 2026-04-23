# Exam Base Java Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Crear una base Java compilable, documentada y verificable para la evaluación práctica de migración desde C++.

**Architecture:** La base usa Java puro con Swing para GUI y `javac` para build, evitando dependencias externas. El núcleo implementa un compilador SQL simplificado basado en el proyecto C++, con lexer, parser, AST y análisis semántico. Las pruebas usan un runner liviano sin JUnit para maximizar compatibilidad.

**Tech Stack:** Java 8+, Swing, javac, scripts shell/batch.

---

### Task 1: Crear estructura del proyecto

**Files:**
- Create: `README.md`
- Create: `.gitignore`
- Create: `scripts/build.sh`
- Create: `scripts/test.sh`
- Create: `scripts/build.bat`
- Create: `scripts/test.bat`

**Step 1: Definir estructura base y documentación inicial**

**Step 2: Crear scripts de compilación**

**Step 3: Crear scripts de pruebas**

**Step 4: Verificar rutas y layout del repo**

### Task 2: Implementar dominio del compilador

**Files:**
- Create: `src/main/java/org/umg/compilerjava/compiler/*.java`

**Step 1: Implementar tipos léxicos y AST**

**Step 2: Implementar tabla de símbolos**

**Step 3: Implementar lexer**

**Step 4: Implementar parser**

**Step 5: Implementar análisis semántico**

### Task 3: Implementar autenticación y GUI base

**Files:**
- Create: `src/main/java/org/umg/compilerjava/auth/*.java`
- Create: `src/main/java/org/umg/compilerjava/ui/*.java`
- Create: `src/main/java/org/umg/compilerjava/Main.java`

**Step 1: Implementar servicio de autenticación demo**

**Step 2: Implementar login en Swing**

**Step 3: Implementar vista de resultados**

**Step 4: Implementar modo CLI y GUI desde `Main`**

### Task 4: Escribir pruebas mínimas

**Files:**
- Create: `tests/org/umg/compilerjava/tests/*.java`

**Step 1: Probar lexer**

**Step 2: Probar parser**

**Step 3: Probar semantic analyzer**

**Step 4: Probar autenticación**

**Step 5: Probar fachada de compilación**

### Task 5: Documentar operación docente y estudiantil

**Files:**
- Create: `docs/STUDENT_SETUP.md`
- Create: `docs/LEADERS_PLAYBOOK.md`

**Step 1: Documentar requisitos de Java e IDEs**

**Step 2: Documentar flujo de líderes**

**Step 3: Confirmar comandos de build y test**
