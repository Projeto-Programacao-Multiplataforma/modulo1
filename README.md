
# Módulo 1 – API de Pessoas (Spring Boot)

Este projeto implementa uma API REST simples para gerenciamento de pessoas.
A aplicação foi desenvolvida em **Spring Boot 3**, utilizando **JPA/Hibernate**, **PostgreSQL** e integração de logs com **Graylog**.
O objetivo do módulo é praticar a criação de um CRUD básico, paginação e boas práticas de estruturação de projeto.

---

## Funcionalidades

A API permite:

* Criar pessoas
* Atualizar pessoas
* Listar pessoas com paginação
* Listar apenas pessoas ativas
* Buscar por ID
* Deletar pessoas

A entidade principal possui os campos:

* id
* nome
* dtNascimento
* ativo 

---

## Estrutura do Projeto

O projeto foi dividido em dois módulos para organizar melhor as responsabilidades e facilitar a manutenção do código.

### **Módulo `domain`**

Reúne apenas os elementos que representam o modelo de dados da aplicação.
Neste módulo está a entidade `Pessoa`, que define os atributos e a estrutura básica utilizada pelo restante do sistema.
Ele não depende de frameworks ou recursos externos, permitindo que a modelagem permaneça simples e isolada.

### **Módulo `springframework`**

Contém toda a parte responsável pela execução da API e pela comunicação com serviços externos.

Esse módulo inclui:

* **Controllers**: definem as rotas e recebem as requisições HTTP.
* **Services**: concentram a lógica utilizada pelos controllers antes de acessar o banco.
* **Repositórios JPA**: responsáveis pela persistência dos dados no PostgreSQL.
* **Configurações da aplicação**: parâmetros de inicialização, integração com Graylog e demais ajustes necessários para execução.

---

## Lógica de Negócio

### Paginação

Os endpoints que retornam listas utilizam `PageRequest` com index base zero.

### Pessoas Ativas

O projeto implementa um endpoint dedicado para retornar **somente pessoas com `ativo = true`**, seguindo o requisito.

```java
Page<Pessoa> findByAtivoTrue(Pageable pageable);
```

---

## Endpoints

### Criar Pessoa

**POST /pessoas**

```json
{
  "nome": "Maria Silva",
  "dtNascimento": "1990-05-20",
  "ativo": true
}
```

---

### Atualizar Pessoa

**PUT /pessoas/{id}**

```json
{
  "nome": "Maria Silva Souza",
  "dtNascimento": "1990-05-20",
  "ativo": true
}
```

---

### Listar com paginação

**GET /pessoas?pagina=0**

Retorna um `Page<Pessoa>` com 10 itens por página.

---

### Listar apenas ativos

**GET /pessoas/ativos?pagina=0&tamanho=10**

---

### Buscar por ID

**GET /pessoas/{id}**

---

### Remover pessoa

**DELETE /pessoas/{id}**

---

## Docker

O projeto inclui:

* **Aplicação Spring Boot**
* **PostgreSQL**
* **Graylog** para captura e visualização dos logs

A execução conjunta dos serviços é definida no arquivo `docker-compose.yml`.
