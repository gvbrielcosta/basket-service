# Basket Service

Este é um microserviço de carrinho de compras para uma aplicação de e-commerce.  
Ele permite criar, atualizar e remover carrinhos, adicionar produtos e simular o fechamento da compra.  
Também se integra a serviços externos para buscar informações de produtos e processar pagamentos.  

## Tecnologias usadas
- Java 17
- Spring Boot
- MongoDB
- Redis
- OpenFeign
- Lombok
- Docker / Docker Compose

## Como rodar
Pré-requisitos: Java 17, Maven, Docker e Docker Compose.

1. Clone o repositório:
   ```bash
   git clone https://github.com/gvbrielcosta/basket-service.git
   cd basket-service
   ```

2. Suba o MongoDB e Redis com Docker Compose:
   ```bash
   docker-compose up -d
   ```

3. Rode a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

A aplicação ficará disponível em `http://localhost:8080`.

## Endpoints principais
- `GET /basket/{id}` → retorna um carrinho pelo ID
- `POST /basket` → cria um novo carrinho
- `PUT /basket/{id}` → atualiza um carrinho
- `DELETE /basket/{id}` → remove um carrinho
- `POST /basket/{id}/payment` → processa o pagamento

### API de produtos (via OpenFeign)
- `GET /products` → lista todos os produtos
- `GET /products/{id}` → retorna detalhes de um produto

## Conceitos aplicados
- Integração com APIs externas usando OpenFeign
- Cache com Redis para reduzir latência
- Tratamento global de exceções com `@ControllerAdvice`
- Uso de DTOs, separação de camadas e princípios SOLID

## Melhorias futuras
- Implementar autenticação/autorização
- Adicionar testes (unitários e de integração)
- Monitoramento e métricas
