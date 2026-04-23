@echo off
setlocal enabledelayedexpansion

set ROOT_DIR=%~dp0..
set BUILD_DIR=%ROOT_DIR%\build
set MAIN_CLASSES=%BUILD_DIR%\classes

if exist "%BUILD_DIR%" rmdir /s /q "%BUILD_DIR%"
mkdir "%MAIN_CLASSES%"

dir /s /b "%ROOT_DIR%\src\main\java\*.java" > "%BUILD_DIR%\main-sources.txt"

javac -encoding UTF-8 -d "%MAIN_CLASSES%" @"%BUILD_DIR%\main-sources.txt"

echo Build completado en %MAIN_CLASSES%
