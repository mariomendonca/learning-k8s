# Exemplos de curl para testar a API Todo

## 1. LISTAR TODOS

### Listar todos os todos
curl -X GET http://localhost:8080/api/todos \
  -H "Content-Type: application/json"

### Listar todos completados
curl -X GET "http://localhost:8080/api/todos?completed=true" \
  -H "Content-Type: application/json"

### Listar todos não completados
curl -X GET "http://localhost:8080/api/todos?completed=false" \
  -H "Content-Type: application/json"

### Buscar todos por palavra-chave
curl -X GET "http://localhost:8080/api/todos?search=estudar" \
  -H "Content-Type: application/json"

### Buscar todo por ID
curl -X GET http://localhost:8080/api/todos/1 \
  -H "Content-Type: application/json"

## 2. CRIAR TODO

### Criar todo simples
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Estudar Kubernetes",
    "description": "Aprender conceitos básicos de K8s"
  }'

### Criar todo apenas com título
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Fazer exercícios"
  }'

### Criar todo para estudos
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Implementar CRUD",
    "description": "Criar Controller, Service e Repository com Spring Boot"
  }'

## 3. OUTROS ENDPOINTS ÚTEIS

### Atualizar todo
curl -X PUT http://localhost:8080/api/todos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Estudar Kubernetes - ATUALIZADO",
    "description": "Aprender conceitos avançados de K8s",
    "completed": true
  }'

### Alternar status do todo (completado/não completado)
curl -X PATCH http://localhost:8080/api/todos/1/toggle \
  -H "Content-Type: application/json"

### Deletar todo
curl -X DELETE http://localhost:8080/api/todos/1 \
  -H "Content-Type: application/json"

### Health check
curl -X GET http://localhost:8080/api/todos/health

### Health check do Actuator
curl -X GET http://localhost:8080/actuator/health

## EXEMPLOS DE RESPOSTA

### Resposta ao listar todos:
# [
#   {
#     "id": 1,
#     "title": "Estudar Kubernetes",
#     "description": "Aprender conceitos básicos de K8s",
#     "completed": false,
#     "createdAt": "2025-07-23T10:30:00",
#     "updatedAt": "2025-07-23T10:30:00"
#   }
# ]

### Resposta ao criar todo:
# {
#   "id": 1,
#   "title": "Estudar Kubernetes",
#   "description": "Aprender conceitos básicos de K8s",
#   "completed": false,
#   "createdAt": "2025-07-23T10:30:00",
#   "updatedAt": "2025-07-23T10:30:00"
# }
