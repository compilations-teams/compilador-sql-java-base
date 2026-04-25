@echo off
setlocal enabledelayedexpansion

set ROOT_DIR=%~dp0..
set BUILD_DIR=%ROOT_DIR%\build
set MAIN_CLASSES=%BUILD_DIR%\classes
set TEST_CLASSES=%BUILD_DIR%\test-classes
set JAVA_EXE=C:\Program Files\Java\jdk-17\bin\java.exe
set JAVAC_EXE=C:\Program Files\Java\jdk-17\bin\javac.exe

call "%ROOT_DIR%\scripts\build.bat"
if errorlevel 1 exit /b 1

mkdir "%TEST_CLASSES%"
dir /s /b "%ROOT_DIR%\tests\*.java" > "%BUILD_DIR%\test-sources.txt"

"%JAVAC_EXE%" -encoding UTF-8 -source 8 -target 8 -cp "%MAIN_CLASSES%" -d "%TEST_CLASSES%" @"%BUILD_DIR%\test-sources.txt"
if errorlevel 1 exit /b 1

"%JAVA_EXE%" -cp "%MAIN_CLASSES%;%TEST_CLASSES%" org.umg.compilerjava.tests.RunAllTests
if errorlevel 1 exit /b 1
