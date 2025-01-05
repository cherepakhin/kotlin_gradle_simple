Заготовка для проекта на Kotlin со Spring-boot (REST+JPA)

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