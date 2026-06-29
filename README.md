#  Sistema de Gestão de Pedidos - API REST


## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 4** (Web, Data JPA, Validation, Lombock)
* **PostgreSQL** (Banco de Dados Relacional)
* **Flyway** (Versionamento e Migrações de Banco de Dados)
* **Maven** (Gerenciamento de Dependências)
* **Railway** (Hospedagem em Nuvem)

## 🌐 Links do Projeto
* **Repositório do Front-end:** `https://github.com/amandaconstante/sistema-pedidos-frontend`
* **Aplicação Web (Front-end ao vivo):** `https://sistema-pedidos-frontend-production.up.railway.app/`




## 🗄️ Banco de Dados e Migrations

A estrutura do banco de dados é gerenciada automaticamente pelo **Flyway**.
Os scripts SQL de criação e alteração de tabelas podem ser encontrados no diretório padrão da aplicação:
📁 [`src/main/resources/db/migration`](src/main/resources/db/migration)


## 📡 Endpoints

### Clientes
* `POST /clientes` - Cadastra um novo cliente (valida CPF único, e-mail e endereço).
* `GET /clientes` - Retorna a lista de clientes paginada.
* `GET /clientes/todos` - Retorna lista de todos os clientes sem paginação. 

### Produtos
* `GET /produtos` - Retorna a lista de produtos disponíveis.

### Pedidos
* `POST /pedidos` - Cria um novo pedido, vinculando cliente e itens (produtos).
* `GET /pedidos` - Lista todos os pedidos de forma paginada (aceita filtros como `nomeCliente`, `dataInicio`, `dataFim`).
* `GET /pedidos/{id}` - Retorna os detalhes completos de um pedido específico, incluindo a lista de itens comprados e o valor total.

----

## 🏗️ Decisões Arquiteturais
* **Validação de Dados:** Uso de Bean Validation (`@Valid`, `@NotBlank`, etc.) para garantir a integridade dos dados nas requisições.
* **Tratamento Global de Erros:** Implementação de `@RestControllerAdvice` para capturar exceções (como `MethodArgumentNotValidException` e `DataIntegrityViolationException`) e retornar respostas JSON limpas e amigáveis para o Front-end.
* **Paginação:** Uso do `Pageable` do Spring para otimizar buscas de listas grandes.
* **Profiles de Configuração:** Configuração de `application-local.properties` e `application-dev.properties` para isolar os ambientes de desenvolvimento e produção, injetando credenciais de banco dinamicamente via variáveis de ambiente no Railway.


> **Nota sobre o Front-end:** A interface gráfica foi construída em React para demonstrar a integração ponta a ponta (Full Stack).
> A estilização (CSS) da interface gráfica foram aceleradas com o auxílio de IA.
> 
> Repositório front-end: https://github.com/amandaconstante/sistema-pedidos-frontend.

