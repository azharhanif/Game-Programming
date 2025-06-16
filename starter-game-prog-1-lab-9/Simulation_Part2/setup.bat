@echo off

REM Script to setup github username and email

set /p username=Enter your github username: 
set /p email=Enter your email address: 


git config --global user.name "%username%"
git config --global user.email "%email%"
