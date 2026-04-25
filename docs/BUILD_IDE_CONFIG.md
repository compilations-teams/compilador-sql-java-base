# Configuración de Build e IDE — Windows 11

**Tarea T-31 | Autor: Maryori Rachael Fajardo Paredes**

## Requisitos previos

- Windows 11
- Java JDK 8 o 17 (compatible con este proyecto)
- Git instalado
- IDE: VS Code o IntelliJ IDEA Community

## Verificar instalación de Java

Abre una terminal (cmd o Git Bash) y escribe:

    java -version
    javac -version

Si aparece error, descarga el JDK desde: https://adoptium.net/

## Configurar JAVA_HOME en Windows 11

1. Busca "Variables de entorno" en el menú inicio
2. Click en "Editar las variables de entorno del sistema"
3. Click en "Variables de entorno..."
4. En "Variables del sistema" click en "Nueva":
   - Nombre: JAVA_HOME
   - Valor: C:\Program Files\Eclipse Adoptium\jdk-17
5. Busca la variable "Path", click en "Editar"
6. Click en "Nuevo" y escribe: %JAVA_HOME%\bin
7. Aceptar todo y reiniciar la terminal

## Compilar el proyecto en Windows 11

Desde la raíz del proyecto en Git Bash o cmd:

    scripts\build.bat

Esto compilará todos los archivos .java y los dejará en build\classes\

## Ejecutar pruebas en Windows 11

    scripts\test.bat

## Ejecutar la aplicación manualmente

    java -cp build\classes org.umg.compilerjava.Main examples\query1.sql

## Configuración en VS Code

1. Descargar VS Code desde https://code.visualstudio.com/
2. Instalar extensiones:
   - Extension Pack for Java
   - Language Support for Java by Red Hat
   - Debugger for Java
3. Abrir carpeta del proyecto: File > Open Folder
4. Para compilar: Ctrl+Shift+P > "Java: Build Project"
5. Para ejecutar: click derecho en Main.java > "Run Java"

## Configuración en IntelliJ IDEA Community

1. Descargar desde https://www.jetbrains.com/idea/download/
2. Seleccionar "Open" y elegir la carpeta del proyecto
3. File > Project Structure > Project > SDK: seleccionar JDK 8 o 17
4. Click derecho en src/main/java > Mark Directory as > Sources Root
5. Click derecho en Main.java > Run Main

## Errores comunes en Windows 11

### javac no se reconoce como comando
- JAVA_HOME no está configurado correctamente
- Solución: seguir los pasos de "Configurar JAVA_HOME"

### Error de codificación
- El script build.bat ya incluye el flag -encoding UTF-8
- Verifica que tus archivos .java estén guardados en UTF-8

## Referencias

- STUDENT_SETUP.md: guía general de configuración
- GIT_GUIDE.md: flujo de trabajo con Git
- README.md: descripción general del proyecto
