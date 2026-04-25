# Asignación de tareas - Examen práctico Java

## Líderes sin tarea técnica individual

| No. | Estudiante | Rol |
|---|---|---|
| 4 | JOSHUA ABINADÍ CIRILO ALEGRÍA | Líder |
| — | JOSUÉ MANUEL MARTÍNEZ PEDROZA | Líder |

## Asignaciones

| No. | Estudiante | Tarea | Rama sugerida |
|---|---|---|---|
| 3 | MERCEDES AZUCENA LÓPEZ PÉREZ | T-01 LoginView - UI | `feature/mercedes-lopez-login-ui` |
| 5 | ESAÚ ABIMAEL DE LA CRUZ | T-02 LoginView - Validación | `feature/esau-cruz-login-validation` |
| 6 | JOSÉ JULIÁN AYALA FLORES | T-03 UserService - Auth | `feature/julian-ayala-user-service-auth` |
| 7 | BORIS ALEXANDER QUIROA ORELLANA | T-04 SessionManager | `feature/boris-quiroa-session-manager` |
| 8 | JOSHUA EDUARDO GARCÍA REYES | T-05 Token.java | `feature/joshua-garcia-token-model` |
| 9 | BRYNER ANDRÉ PAIZ LUNA | T-06 TokenType enum | `feature/bryner-paiz-token-type-enum` |
| 10 | DANIEL ADEMAR RIVERA HERNANDEZ | T-07 Lexer parte 1 | `feature/daniel-rivera-lexer-identifiers` |
| 11 | MARJORIE SAMANTHA GIRÓN MORALES | T-08 Lexer parte 2 | `feature/marjorie-giron-lexer-strings-ops` |
| 12 | JAVIER ALEXANDER FAJARDO LÓPEZ | T-09 Lexer whitespace/comentarios | `feature/javier-fajardo-lexer-whitespace` |
| 13 | JOSUÉ FERNANDO HICHO GARCÍA | T-10 Lexer tokenize() | `feature/josue-hicho-lexer-tokenize` |
| 14 | AXEL ELIÚ HERRERA SÁNCHEZ | T-11 AST SelectNode | `feature/axel-herrera-ast-select-node` |
| 15 | GLENDI PATRICIA CAMPOS ORELLANA | T-12 AST ExpressionNode | `feature/glendi-campos-ast-expression-node` |
| 16 | KEILY FABIOLA ORELLANA MARROQUÍN | T-13 AST ConditionNode | `feature/keily-orellana-ast-condition-node` |
| 17 | OSCAR LEONEL CRUZ PAREDES | T-14 Parser parseExpression() | `feature/oscar-cruz-parser-expression` |
| 18 | GERSON GIOVANNI ORELLANA VÉLIZ | T-15 Parser parseCondition() | `feature/gerson-orellana-parser-condition` |
| 19 | FELIX DANIEL FLORES ESTRADA | T-16 Parser parseColumns() | `feature/felix-flores-parser-columns` |
| 20 | LUIS DAVID AROCHE CONTRERAS | T-17 Parser parseQuery() | `feature/luis-aroche-parser-query` |
| 21 | JOSÉ PABLO CARÍAS FLORES | T-18 SymbolTable Column/DataType | `feature/pablo-carias-symbol-table-column` |
| 22 | DIDHYER ALEXANDER ORTÍZ GUEVARA | T-19 SymbolTable Table | `feature/didhyer-ortiz-symbol-table-table` |
| 23 | JOSUÉ DAVID MORALES RAMÍREZ | T-20 SymbolTable Schema | `feature/josue-morales-symbol-table-schema` |
| 24 | CINDY MAYTTÉ RUANO CALDERÓN | T-21 SemanticAnalyzer tipos | `feature/cindy-ruano-semantic-types` |
| 25 | MARÍA DE LOS ANGELES LÓPEZ FAJARDO | T-22 SemanticAnalyzer validateTable/Columns | `feature/maria-lopez-semantic-validate` |
| 26 | DULCE MARÍA PRADO VÁSQUEZ | T-23 SemanticAnalyzer analyze() | `feature/dulce-prado-semantic-analyze` |
| 27 | ALBINO SEBASTIAN ROSALES RUANO | T-24 CompilerException / ErrorHandler | `feature/albino-rosales-error-handler` |
| 28 | MARIA YAMILET LINDO PABLO | T-25 LexerTests | `feature/yamilet-lindo-test-lexer` |
| 29 | JOSÉ ALEXÁNDER DIONICIO CÓRDOVA | T-26 ParserTests | `feature/jose-dionicio-test-parser` |
| 30 | ERICK ROLANDO RAMAZZINI MURALLES | T-27 SemanticAnalyzerTests | `feature/erick-ramazzini-test-semantic` |
| 31 | ARMANDO CECILIO MORALES SAGASTUME | T-28 MainController / flujo principal | `feature/armando-morales-main-controller` |
| 32 | MADELIN JAZMÍN CERÓN MOLINA | T-29 ResultView | `feature/madelin-ceron-result-view` |
| 33 | EDDY ADOLFO CASTRO VÉLIZ | T-30 App Entry / mejoras Main.java | `feature/eddy-castro-app-entry` |
| 34 | MARYORI RACHAEL FAJARDO PAREDES | T-31 Configuración build/IDE docs | `feature/maryori-fajardo-build-docs` |
| 35 | BRANDON JOSÉ AQUINO ARRIVILLAGA | T-32 README migración | `feature/brandon-aquino-readme-migration` |
| 36 | HUGO DAVID MOSCOSO CASTRO | T-33 Login estilos / UX Swing | `feature/hugo-moscoso-login-styles` |
| 37 | ANTHONY DAVID MARTÍNEZ LEÓN | T-34 Lexer números flotantes | `feature/anthony-martinez-lexer-float` |

## Nota

La distribución puede ajustarse manualmente si un estudiante no participa, se retira o necesita reasignación.

## Recordatorio operativo

Antes de abrir PR, cada estudiante debe validar su rama con:

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

Si el IDE muestra tests en rojo pero la terminal pasa, ejecutar el runner `RunAllTests` como aplicacion Java y no como suite JUnit.
