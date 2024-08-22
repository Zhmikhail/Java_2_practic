@echo off

REM Создание директории out для всех скомпилированных файлов
mkdir out

@REM REM Компиляция файлов DataReceiver
@REM javac -d out -sourcepath DataReceiver\src DataReceiver\src\exception\*.java DataReceiver\src\console\*.java DataReceiver\src\service\*.java DataReceiver\src\repository\*.java DataReceiver\src\transport\*.java

REM Компиляция файлов InternalDataProcessor
echo Компиляция InternalDataProcessor...
javac -d out -sourcepath InternalDataProcessor\src -cp "lib\jackson-core-2.12.3.jar;lib\jackson-databind-2.12.3.jar;lib\jackson-annotations-2.12.3.jar" InternalDataProcessor\src\exception\*.java InternalDataProcessor\src\controller\*.java InternalDataProcessor\src\service\*.java InternalDataProcessor\src\mapper\*.java InternalDataProcessor\src\repository\*.java InternalDataProcessor\src\transport\client\*.java InternalDataProcessor\src\transport\dto\response\*.java InternalDataProcessor\src\transport\dto\request\*.java InternalDataProcessor\src\transport\client\impl\*.java InternalDataProcessor\src\repository\entity\*.java InternalDataProcessor\src\repository\impl\*.java InternalDataProcessor\src\utils\*.java
@REM REM Компиляция файлов ExternalDataProcessor
@REM javac -d out -sourcepath ExternalDataProcessor\src ExternalDataProcessor\src\main\*.java ExternalDataProcessor\src\controller\*.java ExternalDataProcessor\src\service\*.java ExternalDataProcessor\src\repository\*.java ExternalDataProcessor\src\transport\*.java
@REM
REM Запуск InternalDataProcessor сервиса
start java -cp out main.InternalDataProcessorMain
@REM
@REM REM Запуск ExternalDataProcessor сервиса
@REM start java -cp out main.ExternalDataProcessorMain
