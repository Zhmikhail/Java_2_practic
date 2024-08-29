На данный момент реализовано

3 модуля: DataReceiver, InternalDataProcessor, ExternalDataProcessor

Строка, компилирующая DataReceiver

    javac --release 8 -d out\DataReceiver (Get-ChildItem -Recurse DataReceiver\src\*.java).FullName
Строка, компилирующая ExternalDataProcessor

    javac --release 8 -d out\ExternalDataProcessor -cp "lib\jackson-core-2.12.3.jar;lib\jackson-databind-2.12.3.jar;lib\jackson-annotations-2.12.3.jar" (Get-ChildItem -Recurse ExternalDataProcessor\src\*.java).FullName
Строка, компилирующая InternalDataProcessor

    javac --release 8 -d out\InternalDataProcessor -cp "lib\jackson-core-2.12.3.jar;lib\jackson-databind-2.12.3.jar;lib\jackson-annotations-2.12.3.jar" (Get-ChildItem -Recurse InternalDataProcessor\src\*.java).FullName
Далее запускаем в двух терминалах сервисы проверки

External

    java -cp "out\ExternalDataProcessor;lib\jackson-core-2.12.3.jar;lib\jackson-databind-2.12.3.jar;lib\jackson-annotations-2.12.3.jar" Main
Internal

    java -cp "out\InternalDataProcessor;lib\jackson-core-2.12.3.jar;lib\jackson-databind-2.12.3.jar;lib\jackson-annotations-2.12.3.jar" Main 

И в основном терминале, в котором будет работа, запускаем DataReceiver

    java -cp "out\DataReceiver" Main 

