# ponto-api

[![Build Status](https://travis-ci.org/thealessandro/ponto-api.svg?branch=master)](https://travis-ci.org/thealessandro/ponto-api)

API RESTful para o controle de horas trabalhadas de usuários desenvolvido em Spring Boot.

## Tecnologias utilizadas
* Spring Boot
* Spring Data
* Spring Security
* Spring Cache
* Retorno assíncronos com Callable
* JWT para autenticação
* PostgreSQL
* BCrypt para o hash das senhas
* CQRS (Command Query Responsibility Segregation)
* Swagger
* JUnit
* Gradle
* [Travis-CI](https://travis-ci.org/thealessandro/ponto-api)
* [Heroku](https://api-ponto.herokuapp.com/api/swagger-ui.html)

## Documentação da API gerada pelo Swagger
```
https://api-ponto.herokuapp.com/api/swagger-ui.html
http://localhost:8080/api/swagger-ui.html
```

## Base URL
```
https://api-ponto.herokuapp.com/api/v1
http://localhost:8080/api/v1
```

## Alguns testes com o Postman
Aquivo na raiz do projeto: `Ponto-API.postman_collection.json` 


## Configuração dos profiles
Os profiles são divididos em 3:
* Produção - `application-prod.properties`
* Desenvolvimento - `application-dev.properties`
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

## Popular o banco 

### Usuário ADMIN
```
username: admin 
password: 123456

INSERT INTO usuario (id, nome, username, password, permissao) VALUES (1, 'admin', 'admin', '$2a$12$zufrXLwx61IJVLGEk13oI.DAiXPQmoinT5kmEZVDvnEAZGSyYm59W', 'ADMIN');
```
