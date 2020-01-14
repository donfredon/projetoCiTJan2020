# Teste Java - Desafio Upload File - Ci&T - Janeiro 2020

Desafio para processo seletivo Ci&T Jan/2020

# Overview do projeto

Deve ser criado uma aplicação que permita o upload de imagens e que seja possível listar todos os arquivos enviados. O site deve consumir APIs REST para realizar todo o trabalho.

# Observação

Devido ao pouco tempo para execução do teste, optei por fazer algo simples e que atenda as especificações o máximo possível.

Foi utilizado o H2 para persistir os dados.
Na API de Imagens, as imagens que vão ser enviadas devem estar obrigatoriamente associadas a um usuário por meio de seu id.
Foi criado outra API para CRUD de usuário.

Ao fazer upload de um ou mais imagens as mesmas só serão salvas no banco se todas atenderem os requisitos. 

# Executando a aplicação

Para executar:

```java
mvn spring-boot:run
```

Para executar os testes unitários:
```java
mvn test
```

# Endpoints de acesso

Swagger: http://localhost:8080/swagger-ui.html

Endpoint para CRUD Usuário

#### GET  - Usuario
URL: http://localhost:8080/usuario/{idUsuario}

Metodo: GET

#### GET ALL - Usuario
URL: http://localhost:8080/usuario/

Metodo: GET

#### Create - Usuario
URL: http://localhost:8080/usuario/create

Metodo: POST

Exemplo:
```java
{
  "email": "string",
  "name": "string",
  "publicKey": "string"
}

```

#### UPDATE  - Usuario
URL: http://localhost:8080/usuario/{usuarioId}/update

Metodo: PATCH

Parâmetro: idUsuario
```java
{
  "email": "string",
  "name": "string",
  "publicKey": "string"
}
```

#### DELETE - Usuario
URL: http://localhost:8080/usuario/{idUsuario}/delete

Metodo: DELETE

Endpoint Imagem:

#### GET ALL - Imagens
URL: http://localhost:8080/imagem

Metodo: GET

#### GET - Imagem por nome
URL: http://localhost:8080/imagem/{nomeImagem}

Metodo: GET

#### GET - Obter imagens por usuário ID
URL: http://localhost:8080/imagem/{usuarioId}/historico

Metodo: GET

#### DELETE - Imagem por Id
URL: http://localhost:8080/imagem/{imagemId}/delete

Metodo: DELETE

#### Upload - Fazer uplado de uma ou mais imagens associado obrigatoriamente a um usuario.
URL:http://localhost:8080/imagem/upload

Metodo: POST
Content-Type: multipart/form-data

```java
{
  "imagens": {[
    "name": "string",
    "name": "string"
  ]},
  "usuarioId": "string"
}
```

#### Histórico de imagens enviadas por por usuário
URL: http://localhost:8080/imagem/usuario/{usuarioId}/historico

Metodo: GET


# Referências:

- https://start.spring.io/
- https://medium.com/@MiltonFilho/testando-apis-com-postman-7ead94d38a0f
- https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
- https://pandao.github.io/editor.md/en.html
- https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/multipart/MultipartFile.html
