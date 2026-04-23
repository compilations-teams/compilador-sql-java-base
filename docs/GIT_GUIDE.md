# Guía Git/GitHub para estudiantes y líderes

## Parte A - Guía simple para estudiantes

## Objetivo

Trabajar sin romper `main`, entregar evidencia de trabajo real y abrir un Pull Request correcto.

## Flujo recomendado

### 1. Clonar el repositorio

```bash
git clone https://github.com/compilations-teams/compilador-sql-java-base.git
cd compilador-sql-java-base
```

### 2. Actualizar `main`

```bash
git checkout main
git pull origin main
```

### 3. Crear tu rama

```bash
git checkout -b feature/nombre-apellido-tarea
```

Ejemplo:

```bash
git checkout -b feature/mercedes-lopez-login-ui
```

### 4. Trabajar tu tarea

Hacé cambios SOLO en lo que corresponde a tu tarea.

### 5. Probar antes del commit

#### Linux/macOS

```bash
./scripts/build.sh
./scripts/test.sh
```

#### Windows

```bat
scripts\build.bat
scripts\test.bat
```

### 6. Hacer commit

```bash
git add .
git commit -m "feat: implementa login ui en java"
```

### 7. Hacer push

```bash
git push -u origin feature/nombre-apellido-tarea
```

### 8. Abrir Pull Request

Desde GitHub, crear PR hacia `main`.

---

## Errores comunes de estudiantes

### Trabajar directo en `main`

No lo hagás.

### Mezclar varias tareas en un mismo PR

No lo hagás. Un PR debe reflejar tu tarea.

### No probar antes del push

Si no probás, el líder te lo puede rechazar.

### Hacer cambios en archivos ajenos sin necesidad

Eso aumenta conflictos y daña la revisión.

---

## Si Git dice que tenés conflictos

No entres en pánico.

1. leé qué archivo está en conflicto
2. compará tu cambio con el de `main`
3. corregí manualmente
4. volvé a probar
5. hacé commit de resolución

Si no entendés el conflicto, pedile apoyo al líder.

---

## Parte B - Guía para líderes

## Rol del líder

Los líderes no hacen tarea técnica individual. Administran la integración.

### Deben verificar

- rama correcta
- PR bien descrito
- tarea correspondiente
- build y tests funcionales
- ausencia de cambios extraños

### Si un PR no está bien

- rechazarlo
- dejar comentario claro
- abrir issue correctivo si hace falta

---

## Parte C - `git worktree` para líderes/docente

## Qué es

`git worktree` permite tener varias carpetas del mismo repositorio, cada una en una rama diferente.

## Cuándo conviene usarlo

- revisar varios PR al mismo tiempo
- comparar ramas sin cambiar constantemente de branch
- mantener una carpeta limpia para `main`

## Cuándo NO conviene obligar a usarlo

No conviene exigirlo a estudiantes novatos. Les suma complejidad operativa sin resolver por sí sola los conflictos.

## Ejemplo práctico para líderes

Teniendo el repo ya clonado:

```bash
git fetch origin
git worktree add ../review-mercedes origin/feature/mercedes-lopez-login-ui
git worktree add ../review-esau origin/feature/esau-cruz-login-validation
```

Así podés tener:

- carpeta principal → `main`
- carpeta `review-mercedes` → PR de Mercedes
- carpeta `review-esau` → PR de Esaú

## Ver worktrees activos

```bash
git worktree list
```

## Eliminar un worktree cuando ya no lo necesitás

```bash
git worktree remove ../review-mercedes
```

## Recomendación operativa

### Para estudiantes
- usar branches normales

### Para líderes y docente
- usar `git worktree` si revisan varios PR en paralelo

Ese balance reduce complejidad para el alumno y mejora control para revisión.
