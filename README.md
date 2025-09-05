# Naturassp

<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" width="40" /> 
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" width="40" /> 
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original.svg" width="40" /> 
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/maven/maven-original.svg" width="40" /> 
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/hibernate/hibernate-original.svg" width="40" /> 

**Naturassp** é um mini e-commerce para gestão de produtos, clientes e pedidos, desenvolvido com **Java** e **Spring Boot**.

> ⚠️ Projeto em desenvolvimento

---

## Funcionalidades

- Cadastro e gerenciamento de **clientes**, incluindo endereço completo.
- Cadastro e gerenciamento de **categorias** de produtos.
- Cadastro e gerenciamento de **produtos** com detalhes, preço, categoria e disponibilidade.
- Criação e gerenciamento de **pedidos**, incluindo itens e cálculo de valor total.
- Atualização de dados de clientes, produtos e categorias.

---

## Tecnologias Utilizadas

- <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" width="20" /> **Java 21**  
- <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" width="20" /> **Spring Boot 3.5.5**  
- <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original.svg" width="20" /> **MySQL**  
- <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/maven/maven-original.svg" width="20" /> **Maven**  
- <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/hibernate/hibernate-original.svg" width="20" /> **Hibernate / JPA**  
- <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" width="20" /> **Bean Validation (javax.validation)**  

---

## Estrutura do Projeto

O projeto segue a arquitetura padrão de **Spring Boot** com camadas:

- **model** — Classes de entidade (`Cliente`, `Categoria`, `Produto`, `Pedido`, `ItemPedido`, `Endereco`).
- **dto** — Objetos de transferência de dados (`RequestDTO`, `UpdateDTO`).
- **repository** — Interfaces JPA para acesso ao banco.
- **controller** — Endpoints REST para cada entidade.
- **service** — Lógica de negócio e operações.

---

## Modelos de Dados

### Cliente
- `id` (Long)
- `nome`, `email`, `telefone`, `cpf`
- `endereco` (Embedded com CEP, logradouro, número, complemento, bairro, cidade e estado)

### Categoria
- `id` (Long)
- `nome` (String) — único e obrigatório

### Produto
- `id`, `nome`, `detalhe`, `linkFoto`, `preco`
- `categoria` (Categoria)
- `disponivel` (Boolean)

### Pedido
- `id`, `dataPedido`, `valorTotal`, `observacoes`, `status`
- `cliente` (Cliente)
- `itensPedidos` (List<ItemPedido>)

### ItemPedido
- `numSeq`, `qtdItem`, `precoUnitario`, `precoTotal`
- `pedido` (Pedido)
- `produto` (Produto)

---

## Como Executar

1. Clone o repositório:
```
git clone https://github.com/seu-usuario/naturassp.git
cd naturassp
```
2. Configure o banco de dados no application.properties:
```

spring.datasource.url=jdbc:mysql://localhost:3306/naturassp
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```


