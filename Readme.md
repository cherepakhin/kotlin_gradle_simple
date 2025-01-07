### Заготовка для проекта на Kotlin со Spring-boot (REST+JPA)

## Подготовка проекта:

````shell
$ echo $JAVA_HOME
/usr/lib/jvm/java-17-openjdk-amd64
$ git init
$ git branch main
$ git branch
* main
  master
  
# добавление удалённого репозитория к локальному в Git
# git remote add <shortname> <url>  
$ git remote add origin https://github.com/cherepakhin/kotlin_gradle_simple.git
$ git push -u origin main
````

## Проведение unit-тестов:

````shell
./gradlew test
````

## Запуск:

````shell
./gradlew bootRun
````

Тест REST echo:

````shell
$ http :8780/api/echo/aaa

HTTP/1.1 200 
Connection: keep-alive
Content-Length: 3
Content-Type: text/plain;charset=UTF-8

aaa

````

Другие тесты REST:

````shell
$ http :8780/api/product/count_names
HTTP/1.1 200
Connection: keep-alive
Content-Type: application/json
Keep-Alive: timeout=60
Transfer-Encoding: chunked

9

````

````shell
$ http :8780/api/group_product/
HTTP/1.1 200 
Connection: keep-alive
Content-Type: application/json

[
    {
        "haveChilds": true,
        "n": 1,
        "name": "IT products",
        "parentN": -1
    },
    {
        "haveChilds": false,
        "n": 3,
        "name": "Desktop Computers",
        "parentN": 2
    },
    {
        "haveChilds": false,
        "n": 4,
        "name": "Notebooks",
        "parentN": 2
    },
    {
        "haveChilds": false,
        "n": 5,
        "name": "Monitors",
        "parentN": 1
    },
    {
        "haveChilds": false,
        "n": 6,
        "name": "Hard drives",
        "parentN": 1
    }
]
````

````shell
http :8780/api/group_product/6

{
  "haveChilds": false,
  "n": 6,
  "name": "Hard drives",
  "parentN": 1
}
````

## Сборка jar

````shell
./gradlew build

$ ls build/libs/
kotlin_gradle_simple-0.0.1-SNAPSHOT.jar  kotlin_gradle_simple-0.0.1-SNAPSHOT-plain.jar
````

Запуск jar

````shell
$ /usr/lib/jvm/java-1.17.0-openjdk-amd64/bin/java -jar build/libs/kotlin_gradle_simple-0.0.1-SNAPSHOT.jar
````

...0.0.1-SNAPSHOT... - берется из version в build.gradle.kts

````shell
....
version = "0.0.1-SNAPSHOT"
....
````

## Publish to Nexus

Настройка сделана в build.gradle.kts.
Имя и пароль для deploy в Nexus берутся из переменных окружения __NEXUS_CRED_USR__, __NEXUS_CRED_PSW__. 
Для задания переменных выполнить:

````shell
$ export NEXUS_CRED_USR=admin 
$ export NEXUS_CRED_PSW=pass 
````

Для deploy выполнить:

````shell
$ ./gradlew publish
````
