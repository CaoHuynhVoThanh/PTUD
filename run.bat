@echo off
cd /d %~dp0
setlocal EnableDelayedExpansion

:: Thêm jar chính
set CP=ckjava2.jar

:: Thêm tất cả thư viện trong thư mục libs/
for %%i in ("libs\*.jar") do (
    set CP=!CP!;%%i
)

:: Chạy chương trình
java -cp "!CP!" gui.Application

endlocal
pause
