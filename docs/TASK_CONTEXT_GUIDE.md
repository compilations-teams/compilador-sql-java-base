# Guía de contexto de tareas - Examen práctico Java

## Cómo usar esta guía

Si no tenés experiencia fuerte en desarrollo de software, NO arranques escribiendo código a ciegas.

Primero hacé esto:

1. leé tu tarea asignada en `STUDENT_ASSIGNMENTS.md`
2. buscá aquí el código de tu tarea (`T-01`, `T-02`, etc.)
3. entendé **qué hace**, **para qué sirve**, **qué archivo tocar** y **cómo probarlo**
4. recién después creá tu rama y empezá a trabajar

## Regla general

Cada tarea tiene 5 partes:

- **Objetivo** → qué debés construir o mejorar
- **Contexto** → por qué existe esa pieza en el sistema
- **Archivos sugeridos** → dónde deberías trabajar
- **Mínimo aceptable** → qué se espera como base
- **Cómo probarlo** → cómo demostrar que funciona

---

## T-01 LoginView - UI

**Objetivo:** construir o mejorar la pantalla de login.

**Contexto:** el compilador original en C++ no tenía una interfaz moderna de acceso. En Java se agregó una vista inicial para orientar la experiencia de usuario.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/ui/LoginFrame.java`

**Mínimo aceptable:**
- campo de correo
- campo de contraseña
- botón de ingreso

**Cómo probarlo:** ejecutar `Main` sin argumentos y verificar que la ventana abra.

---

## T-02 LoginView - Validación

**Objetivo:** validar datos antes de autenticar.

**Contexto:** una interfaz sin validación permite errores básicos como email vacío o contraseña vacía.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/ui/LoginFrame.java`

**Mínimo aceptable:**
- impedir login si email está vacío
- impedir login si password está vacío
- mostrar mensaje claro al usuario

**Cómo probarlo:** intentar iniciar sesión con campos vacíos.

---

## T-03 UserService - Auth

**Objetivo:** mantener o mejorar el servicio de autenticación.

**Contexto:** el login necesita una capa que valide credenciales sin mezclar la lógica con la interfaz.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/auth/AuthService.java`
- `src/main/java/org/umg/compilerjava/auth/InMemoryAuthService.java`
- `src/main/java/org/umg/compilerjava/auth/AuthResult.java`

**Mínimo aceptable:**
- validar usuario conocido
- rechazar contraseña incorrecta
- devolver mensaje comprensible

**Cómo probarlo:** usar credenciales demo y ejecutar tests.

---

## T-04 SessionManager

**Objetivo:** guardar quién está autenticado y con qué rol.

**Contexto:** cuando un usuario entra al sistema, conviene centralizar el estado de sesión.

**Archivos sugeridos:**
- crear `src/main/java/org/umg/compilerjava/auth/SessionManager.java`
- tocar `LoginFrame.java` o `Main.java` si hace falta integrarlo

**Mínimo aceptable:**
- guardar email actual
- guardar rol actual
- permitir limpiar sesión

**Cómo probarlo:** hacer login y verificar que la app conozca el rol activo.

---

## T-05 Token.java

**Objetivo:** trabajar el objeto token del lexer.

**Contexto:** el lexer divide el SQL en piezas pequeñas llamadas tokens. Cada token tiene tipo, valor, línea y columna.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/Token.java`

**Mínimo aceptable:**
- getters correctos
- `toString()` útil para diagnóstico

**Cómo probarlo:** ejecutar tests de lexer o crear tokens manualmente.

---

## T-06 TokenType enum

**Objetivo:** trabajar el enum con tipos de token.

**Contexto:** sin tipos claros no hay forma ordenada de saber si algo es `SELECT`, `FROM`, `IDENTIFIER`, etc.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/TokenType.java`

**Mínimo aceptable:**
- mantener consistencia con los tipos usados por `Lexer` y `Parser`

**Cómo probarlo:** compilar y ejecutar pruebas del lexer/parser.

---

## T-07 Lexer parte 1

**Objetivo:** reconocer identificadores, palabras clave y números.

**Contexto:** esta es la base del análisis léxico. Si esto falla, el parser recibe basura.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/Lexer.java`

**Mínimo aceptable:**
- reconocer `SELECT`, `FROM`, `WHERE`
- reconocer nombres como `usuarios`, `nombre`, `edad`
- reconocer números enteros

**Cómo probarlo:** `./scripts/test.sh`

---

## T-08 Lexer parte 2

**Objetivo:** reconocer strings y operadores.

**Contexto:** el SQL usa comparaciones y literales de texto, por ejemplo `'Roma'`, `>=`, `!=`.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/Lexer.java`

**Mínimo aceptable:**
- reconocer strings entre comillas simples
- reconocer `=`, `>`, `<`, `>=`, `<=`, `!=`

**Cómo probarlo:** usar consultas con WHERE de texto y de número.

---

## T-09 Lexer whitespace/comentarios

**Objetivo:** ignorar espacios y comentarios SQL.

**Contexto:** el compilador no debe tratar comentarios o espacios como si fueran tokens reales.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/Lexer.java`

**Mínimo aceptable:**
- ignorar saltos de línea
- ignorar espacios/tabulaciones
- ignorar comentarios con `--`

**Cómo probarlo:** usar SQL con comentarios antes o entre líneas.

---

## T-10 Lexer tokenize()

**Objetivo:** construir la lista completa de tokens.

**Contexto:** este método recorre toda la entrada y llama internamente a `getNextToken()` hasta llegar al final.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/Lexer.java`

**Mínimo aceptable:**
- devolver todos los tokens
- incluir `END_OF_FILE`

**Cómo probarlo:** imprimir o revisar la lista generada por una consulta válida.

---

## T-11 AST SelectNode

**Objetivo:** representar una consulta SELECT en el árbol sintáctico.

**Contexto:** el AST guarda la estructura lógica de la consulta después del parser.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/SelectNode.java`

**Mínimo aceptable:**
- tabla
- lista de columnas
- flag `selectAll`
- condición where opcional

**Cómo probarlo:** parsear una consulta y revisar los datos del nodo.

---

## T-12 AST ExpressionNode

**Objetivo:** representar una expresión simple.

**Contexto:** las expresiones aparecen en condiciones, por ejemplo `edad`, `18`, `'Roma'`.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/ExpressionNode.java`

**Mínimo aceptable:**
- tipo de expresión
- valor de expresión

**Cómo probarlo:** revisar el resultado del parser sobre un WHERE.

---

## T-13 AST ConditionNode

**Objetivo:** representar una condición WHERE.

**Contexto:** la condición tiene parte izquierda, operador y parte derecha.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/ConditionNode.java`
- `src/main/java/org/umg/compilerjava/compiler/CompOperator.java`

**Mínimo aceptable:**
- left
- operator
- right

**Cómo probarlo:** parsear un WHERE como `edad >= 18`.

---

## T-14 Parser parseExpression()

**Objetivo:** leer una expresión válida.

**Contexto:** el parser necesita saber cuándo una cosa es identificador, número o string.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/Parser.java`

**Mínimo aceptable:**
- aceptar identificador
- aceptar número
- aceptar string
- lanzar error si no es válido

**Cómo probarlo:** usar tests de parser y casos de error.

---

## T-15 Parser parseCondition()

**Objetivo:** armar una condición completa.

**Contexto:** una condición correcta es algo como `edad > 18` o `categoria = 'libros'`.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/Parser.java`

**Mínimo aceptable:**
- expresión izquierda
- operador válido
- expresión derecha

**Cómo probarlo:** parsear consultas con WHERE.

---

## T-16 Parser parseColumns()

**Objetivo:** leer columnas de SELECT.

**Contexto:** puede venir `*` o una lista separada por comas.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/Parser.java`

**Mínimo aceptable:**
- soportar `*`
- soportar `nombre, edad`

**Cómo probarlo:** consultas SELECT simples y múltiples columnas.

---

## T-17 Parser parseQuery()

**Objetivo:** orquestar el parse completo de la consulta.

**Contexto:** este método es el corazón del parser para la gramática base.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/Parser.java`

**Mínimo aceptable:**
- leer SELECT
- leer columnas
- leer FROM
- leer tabla
- leer WHERE opcional

**Cómo probarlo:** parsear `SELECT nombre FROM usuarios WHERE edad > 18;`

---

## T-18 SymbolTable Column/DataType

**Objetivo:** representar tipos y columnas del schema.

**Contexto:** el análisis semántico valida nombres y tipos usando esta información.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/DataType.java`
- `src/main/java/org/umg/compilerjava/compiler/Column.java`

**Mínimo aceptable:**
- tipos consistentes: INT, FLOAT, VARCHAR
- columna con nombre y tipo

**Cómo probarlo:** revisar integración con `Table` y `SemanticAnalyzer`.

---

## T-19 SymbolTable Table

**Objetivo:** representar una tabla del schema.

**Contexto:** una tabla contiene varias columnas y se busca por nombre.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/Table.java`

**Mínimo aceptable:**
- nombre de tabla
- lista de columnas
- búsqueda de columna por nombre

**Cómo probarlo:** probar `findColumn()`.

---

## T-20 SymbolTable Schema

**Objetivo:** mantener el schema fijo del proyecto.

**Contexto:** acá viven tablas como `usuarios` y `productos`.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/SymbolTable.java`

**Mínimo aceptable:**
- incluir `usuarios`
- incluir `productos`
- búsqueda case-insensitive de tabla

**Cómo probarlo:** análisis semántico de consultas válidas e inválidas.

---

## T-21 SemanticAnalyzer tipos

**Objetivo:** validar compatibilidad de tipos.

**Contexto:** no todo se puede comparar con todo. Por ejemplo, texto con número suele ser inválido.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/SemanticAnalyzer.java`

**Mínimo aceptable:**
- permitir INT con INT
- permitir FLOAT con INT
- rechazar VARCHAR con INT si no corresponde

**Cómo probarlo:** consultas con WHERE válidas e inválidas.

---

## T-22 SemanticAnalyzer validateTable/Columns

**Objetivo:** validar que tabla y columnas existan.

**Contexto:** si el usuario consulta una tabla o columna inexistente, el compilador debe informarlo.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/SemanticAnalyzer.java`

**Mínimo aceptable:**
- detectar tabla inexistente
- detectar columna inexistente

**Cómo probarlo:** `SELECT salario FROM usuarios;`

---

## T-23 SemanticAnalyzer analyze()

**Objetivo:** coordinar el análisis semántico completo.

**Contexto:** este método une las validaciones de tabla, columnas y condición.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/SemanticAnalyzer.java`

**Mínimo aceptable:**
- devolver true/false correctamente
- llenar lista de errores cuando corresponda

**Cómo probarlo:** ejecutar tests y revisar diagnósticos.

---

## T-24 CompilerException / ErrorHandler

**Objetivo:** mejorar el manejo de errores del compilador.

**Contexto:** un error útil dice qué pasó y dónde pasó.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/CompilerException.java`

**Mínimo aceptable:**
- mensaje claro
- línea
- columna

**Cómo probarlo:** forzar errores de sintaxis.

---

## T-25 LexerTests

**Objetivo:** fortalecer pruebas del lexer.

**Contexto:** si el lexer falla, todo lo demás falla después.

**Archivos sugeridos:**
- `tests/org/umg/compilerjava/tests/LexerTests.java`

**Mínimo aceptable:**
- al menos un caso nuevo útil
- cubrir un comportamiento real

**Cómo probarlo:** `./scripts/test.sh`

---

## T-26 ParserTests

**Objetivo:** fortalecer pruebas del parser.

**Contexto:** el parser transforma tokens en estructura lógica.

**Archivos sugeridos:**
- `tests/org/umg/compilerjava/tests/ParserTests.java`

**Mínimo aceptable:**
- caso válido nuevo o mejora de cobertura

**Cómo probarlo:** `./scripts/test.sh`

---

## T-27 SemanticAnalyzerTests

**Objetivo:** fortalecer pruebas semánticas.

**Contexto:** hay que probar casos buenos y malos.

**Archivos sugeridos:**
- `tests/org/umg/compilerjava/tests/SemanticAnalyzerTests.java`

**Mínimo aceptable:**
- un caso nuevo útil de compatibilidad o error

**Cómo probarlo:** `./scripts/test.sh`

---

## T-28 MainController / flujo principal

**Objetivo:** mejorar la orquestación principal de la aplicación.

**Contexto:** `Main` o un controlador central coordina login, compilación y vistas.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/Main.java`
- crear controlador si hace falta

**Mínimo aceptable:**
- separar responsabilidades mejor que antes

**Cómo probarlo:** modo GUI o CLI.

---

## T-29 ResultView

**Objetivo:** mejorar la vista donde se muestran resultados.

**Contexto:** ahí el usuario ve tokens, errores o el estado de la compilación.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/ui/ResultFrame.java`

**Mínimo aceptable:**
- área clara de entrada
- área clara de salida
- botón de ejecución funcional

**Cómo probarlo:** abrir GUI y ejecutar una consulta.

---

## T-30 App Entry / mejoras Main.java

**Objetivo:** mejorar el punto de entrada de la app.

**Contexto:** `Main` define cómo arranca el sistema en CLI o GUI.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/Main.java`

**Mínimo aceptable:**
- flujo claro
- mensajes útiles
- sin romper CLI ni GUI

**Cómo probarlo:** probar ambos modos.

---

## T-31 Configuración build/IDE docs

**Objetivo:** mejorar documentación de entorno.

**Contexto:** varios alumnos dependen más de la guía de setup que del código mismo.

**Archivos sugeridos:**
- `README.md`
- `docs/STUDENT_SETUP.md`

**Mínimo aceptable:**
- instrucciones más claras para Java/IDE

**Cómo probarlo:** otra persona debería poder seguir la guía sin preguntarte todo.

---

## T-32 README migración

**Objetivo:** documentar mejor la migración de C++ a Java.

**Contexto:** no basta con codificar; hay que explicar qué se migró y qué cambió.

**Archivos sugeridos:**
- `README.md`
- o crear documento adicional en `docs/`

**Mínimo aceptable:**
- explicar propósito
- explicar decisiones principales

**Cómo probarlo:** leer el README y verificar que dé contexto suficiente.

---

## T-33 Login estilos / UX Swing

**Objetivo:** mejorar la experiencia visual del login.

**Contexto:** aunque Swing es simple, se puede mejorar espaciado, colores, alineación y claridad visual.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/ui/LoginFrame.java`

**Mínimo aceptable:**
- mejor orden visual
- texto claro
- mejor experiencia que la base inicial

**Cómo probarlo:** abrir la GUI y comparar visualmente.

---

## T-34 Lexer números flotantes

**Objetivo:** soportar decimales.

**Contexto:** consultas sobre `precio` pueden requerir números como `19.99`.

**Archivos sugeridos:**
- `src/main/java/org/umg/compilerjava/compiler/Lexer.java`
- tal vez `SemanticAnalyzer.java` si toca tipo inferido

**Mínimo aceptable:**
- reconocer números con punto decimal
- no romper números enteros

**Cómo probarlo:** usar una consulta como `SELECT * FROM productos WHERE precio > 19.99;`

---

## Consejo final para todos

Si no sabés por dónde empezar:

1. corré `./scripts/test.sh`
2. leé el archivo que te toca
3. hacé un cambio pequeño y entendible
4. probá de nuevo
5. hacé commit con mensaje claro

Lo importante no es “parecer experto”. Lo importante es que se note que **entendiste, tocaste y defendés tu parte**.

## Atajo de prueba general

Para casi todas las tareas tecnicas, el cierre minimo recomendado es:

### Linux/macOS

```bash
./scripts/test.sh
```

### Windows

```bat
scripts\test.bat
```

La suite se ejecuta desde `RunAllTests` y no requiere JUnit.
