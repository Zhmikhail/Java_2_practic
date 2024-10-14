На данный момент реализовано

3 модуля: diploma-validator, ministry-of-education, university

Строка, компилирующая diploma-validator

    javac --release 8 -d out\diploma-validator (Get-ChildItem -Recurse diploma-validator\src\*.java).FullName
Строка, компилирующая university

    javac --release 8 -d out\university -cp "lib\jackson-core-2.12.3.jar;lib\jackson-databind-2.12.3.jar;lib\common.jar;lib\jackson-annotations-2.12.3.jar" (Get-ChildItem -Recurse university\src\*.java).FullName
Строка, компилирующая ministry-of-education

    javac --release 8 -d out\ministry-of-education -cp "lib\jackson-core-2.12.3.jar;lib\jackson-databind-2.12.3.jar;lib\common.jar;lib\jackson-annotations-2.12.3.jar" (Get-ChildItem -Recurse ministry-of-education\src\*.java).FullName
Далее запускаем в двух терминалах сервисы проверки

university

    java -cp "out\university;lib\common.jar;lib\jackson-core-2.12.3.jar;lib\jackson-databind-2.12.3.jar;lib\jackson-annotations-2.12.3.jar" main.University
ministry-of-education

    java -cp "out\ministry-of-education;lib\common.jar;lib\jackson-core-2.12.3.jar;lib\jackson-databind-2.12.3.jar;lib\jackson-annotations-2.12.3.jar" MinistryOfEducation
И в основном терминале, в котором будет работа, запускаем diploma-validator

    java -cp "out\diploma-validator" diploma_validator.DiplomaValidatorApp 

