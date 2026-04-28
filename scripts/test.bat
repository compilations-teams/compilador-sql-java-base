@echo off
setlocal

for %%I in ("%~dp0..") do set "ROOT_DIR=%%~fI"
set "BUILD_DIR=%ROOT_DIR%\build"
set "ALL_CLASSES=%BUILD_DIR%\all-classes"
set "ALL_SOURCES=%BUILD_DIR%\all-sources.txt"

where javac >nul 2>&1
if errorlevel 1 (
    echo Error: javac no esta disponible en PATH.
    exit /b 1
)

where java >nul 2>&1
if errorlevel 1 (
    echo Error: java no esta disponible en PATH.
    exit /b 1
)

if not exist "%BUILD_DIR%" mkdir "%BUILD_DIR%" || exit /b 1
if exist "%ALL_CLASSES%" rmdir /s /q "%ALL_CLASSES%"
mkdir "%ALL_CLASSES%" || exit /b 1

(
    dir /s /b "%ROOT_DIR%\src\main\java\*.java"
    dir /s /b "%ROOT_DIR%\tests\*.java"
) > "%ALL_SOURCES%"

for %%A in ("%ALL_SOURCES%") do if %%~zA==0 (
    echo Error: no se encontraron archivos Java en src\main\java o tests.
    exit /b 1
)

javac -encoding UTF-8 -d "%ALL_CLASSES%" @"%ALL_SOURCES%"
if errorlevel 1 exit /b 1

java -cp "%ALL_CLASSES%" org.umg.compilerjava.tests.RunAllTests
if errorlevel 1 exit /b 1
