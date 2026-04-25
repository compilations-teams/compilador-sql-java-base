@echo off
setlocal

for %%I in ("%~dp0..") do set "ROOT_DIR=%%~fI"
set "BUILD_DIR=%ROOT_DIR%\build"
set "MAIN_CLASSES=%BUILD_DIR%\classes"
set "MAIN_SOURCES=%BUILD_DIR%\main-sources.txt"

where javac >nul 2>&1
if errorlevel 1 (
    echo Error: javac no esta disponible en PATH.
    exit /b 1
)

if exist "%BUILD_DIR%" rmdir /s /q "%BUILD_DIR%"
mkdir "%MAIN_CLASSES%" || exit /b 1

dir /s /b "%ROOT_DIR%\src\main\java\*.java" > "%MAIN_SOURCES%"
for %%A in ("%MAIN_SOURCES%") do if %%~zA==0 (
    echo Error: no se encontraron archivos Java en src\main\java.
    exit /b 1
)

javac -encoding UTF-8 -d "%MAIN_CLASSES%" @"%MAIN_SOURCES%"
if errorlevel 1 exit /b 1

echo Build completado en %MAIN_CLASSES%
