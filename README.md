# 🛒 Basket Service

Este projeto é um microserviço de gerenciamento de carrinho de compras para uma aplicação de e-commerce.  
Ele permite adicionar, remover, listar e finalizar compras de produtos, integrando-se com serviços externos para obter informações dos produtos e processar pagamentos.

## 📋 Índice

- [🚀 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [📦 Estrutura do Projeto](#-estrutura-do-projeto)
- [🔧 Configuração e Execução](#-configuração-e-execução)
- [📌 Endpoints Disponíveis](#-endpoints-disponíveis)
- [🧠 Conceitos Aplicados](#-conceitos-aplicados)
- [🛠️ Melhorias Futuras](#-melhorias-futuras)
- [👨‍💻 Autor](#-autor)

## 🚀 Tecnologias Utilizadas

- **Java 17**: Linguagem principal do projeto.
- **Spring Boot**: Framework para facilitar a criação de aplicações Java.
- **MongoDB**: Banco de dados NoSQL para armazenar os dados dos carrinhos.
- **Redis**: Utilizado como cache para otimizar o acesso a dados de produtos.
- **OpenFeign**: Cliente HTTP declarativo para comunicação com serviços externos.
- **Lombok**: Biblioteca para reduzir a verbosidade do código Java.
- **Docker**: Containerização dos serviços para facilitar o ambiente de desenvolvimento.
- **Docker Compose**: Orquestração dos containers Docker.

## 🔧 Configuração e Execução

### Pré-requisitos

- Java 17
- Maven
- Docker e Docker Compose

### Passos para execução

1. Clone o repositório:

   ```bash
   git clone https://github.com/gvbrielcosta/basket-service.git
   cd basket-service

2. Inicie os serviços do MongoDB e Redis:
   
   ```bash
   docker-compose up -d


3. Execute a aplicação

   ```bash
   ./mvnw spring-boot:run

 A aplicação estará disponível em http://localhost:8080.

 ## 📌 Endpoints Disponíveis

- **GET /basket/{id}**: Recupera um carrinho pelo ID.
- **POST /basket**: Cria um novo carrinho.
- **PUT /basket/{id}**: Atualiza um carrinho existente.
- **DELETE /basket/{id}**: Remove um carrinho.
- **POST /basket/{id}/payment**: Processa o pagamento do carrinho e altera o status para "SOLD".

### 🧩 API Externa (Produto)

Esses endpoints são consumidos via OpenFeign:

- **GET /products**: Lista todos os produtos disponíveis.
- **GET /products/{id}**: Retorna os detalhes de um produto específico.

## 🧠 Conceitos Aplicados

- **Integração com APIs externas**: Utilização do OpenFeign para comunicação com o serviço de produtos.
- **Cache com Redis**: Implementação de cache para reduzir a latência nas requisições de produtos.
- **Tratamento de exceções**: Criação de exceções personalizadas e uso de `@ControllerAdvice` para tratamento global.
- **Boas práticas de desenvolvimento**: Uso de DTOs, separação de camadas, e princípios SOLID.



