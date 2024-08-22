#!/bin/bash

# Компиляция всех Java-файлов и сохранение скомпилированных классов в директорию out
javac -d out -sourcepath src src/main/Main.java src/controller/InternalDataController.java src/service/InternalDataService.java src/repository/impl/UniversityRepositoryImpl.java

# Запуск скомпилированного Main-класса
java -cp out src.Main
