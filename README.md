# Wallet App

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/technologies/downloads/#java17)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.3-brightgreen)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-orange)](https://gradle.org/releases/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-darkblue)](https://www.postgresql.org/)
[![Swagger](https://img.shields.io/badge/Swagger-green)](https://your-swagger-url.com/)
[![Docker](https://img.shields.io/badge/Docker-blue)](https://www.docker.com/)

- REST API
    - [OpenAPI Spec](http://localhost:8080/swagger-ui/index.html)

## Сборка и запуск приложения

```shell
# склонируйте репозиторий
$ https://github.com/pacgds1man/wallet-app

# создайте исполняемый jar файл:
$ ./gradlew clean bootJar

# запустите приложение и базу данных с помощью docker-compose:
$ docker-compose -f docker-compose.yml up -d
```

Приложение работает на порту 8080.

## PostgreSQL

Postgres работает на порту 5432. БД заполняется данными с помощью 'liquibase'.
Для подключения к БД используйте следующие данные:

- База данных: postgres
- Логин: postgres
- Пароль: root

## Swagger UI

Для тестирования api приложения доступен swagger по адресу *http://localhost:8080/swagger-ui/index.html*