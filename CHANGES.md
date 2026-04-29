# Cambios realizados - T-18 SymbolTable Column/DataType
## Autor: José Pablo Carías Flores

## Archivos modificados

### DataType.java
- Se agregaron los tipos de datos BOOLEAN y DATE al enum
- El enum ahora contiene: INT, FLOAT, VARCHAR, BOOLEAN, DATE

### Column.java
- Se agregó el campo nullable (boolean)
- Se agregó constructor overload Column(String, DataType) con nullable = true por defecto
- Se agregó constructor completo Column(String, DataType, boolean)
- Se agregó método isNullable()
- Se agregó método toString()

## Evidencia
- Build: exitoso
- Tests: 5 suites ejecutadas, todas pasaron
