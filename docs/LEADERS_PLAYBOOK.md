# Playbook para líderes revisores

## Líderes

- JOSHUA ABINADÍ CIRILO ALEGRÍA
- JOSUÉ MANUEL MARTÍNEZ PEDROZA

## Responsabilidades

- revisar PR
- aprobar o rechazar
- crear issues correctivos
- informar al alumno qué debe corregir
- proteger la rama principal

## Criterios de revisión mínimos

1. La rama sale de `main`
2. El PR corresponde a la tarea asignada
3. El código compila
4. No rompe los scripts base
5. La descripción del PR está completa

## Cuándo rechazar un PR

- no compila
- no cumple la tarea
- tiene conflictos sin resolver
- modifica áreas no autorizadas
- evidencia plagio o copia directa

## Issue correctivo sugerido

Título:

```text
Corrección requerida: [T-XX] nombre de la tarea
```

Cuerpo:

- Problema detectado
- Archivo afectado
- Acción esperada
- Fecha límite de corrección

## Regla operativa

No mergear por presión. Si no está bien, se rechaza y se documenta.

## Checklist tecnico rapido para aprobar PR

1. Ejecutar build y tests en limpio.
2. Confirmar que no se eliminaron scripts base en `scripts/`.
3. Verificar que `RunAllTests` termina sin AssertionError.
4. Confirmar que el PR no toca tareas ajenas sin justificacion.

Comando recomendado en Windows:

```bat
scripts\build.bat
scripts\test.bat
```
