# ponto-api

[![Build Status](https://travis-ci.org/thealessandro/ponto-api.svg?branch=master)](https://travis-ci.org/thealessandro/ponto-api)

API RESTful para o controle de horas trabalhadas de usuários desenvolvido em Spring Boot.

## Tecnologias utilizadas
* Spring Boot
* Spring Data
* Spring Security
* Spring Cache
* JWT para autenticação
* PostgreSQL
* CQRS (Command Query Responsibility Segregation)
* Swagger
* JUnit
* Gradle
* [Travis-CI](https://travis-ci.org/thealessandro/ponto-api)
* [Heroku](https://www.heroku.com/)

## Documentação da API gerada pelo Swagger
`http://localhost:8080/api/swagger-ui.html`

## Base URL
`http://localhost:8080/api/v1`


## Configuração dos profiles
Os profiles são divididos em 3:
* Produção - `application-prod.properties`
* Desenvolimento - `application-dev.properties`
* Testes - `application-test.properties`


## Criando os bancos no PostgreSQL

`CREATE DATABASE <db-name> OWNER <nome-usuario-db>;`

```
$ sudo -i -u postgres
$ psql
psql (9.6.5, server 9.4.12)
Type "help" for help.

postgres=# CREATE DATABASE ponto_prod OWNER postgres;
postgres=# CREATE DATABASE ponto_dev OWNER postgres;
postgres=# CREATE DATABASE ponto_test OWNER postgres;
```

