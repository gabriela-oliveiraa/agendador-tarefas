# 📅 Agendador de Tarefas — Microsserviço de Gerenciamento de Tarefas

Microsserviço responsável pela criação, listagem e gerenciamento de tarefas dos usuários da plataforma. Desenvolvido com **Spring Boot 4**, **Java 21**, **MongoDB** como banco de dados NoSQL, autenticação via **JWT** e mapeamento de objetos com **MapStruct**.

---

## 🚀 Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.3 |
| Spring Security | — |
| Spring Data MongoDB | — |
| JWT (jjwt) | 0.13.0 |
| Spring Cloud OpenFeign | 2025.1.0 |
| MapStruct | 1.5.5.Final |
| Lombok | — |
| Spring Boot DevTools | — |
| Docker | — |
| Gradle | — |

---

## 📁 Estrutura do Projeto

```
agendador-tarefas/
├── .github/workflows/        # Pipelines CI/CD (GitHub Actions)
├── gradle/wrapper/           # Wrapper do Gradle
├── src/main/                 # Código-fonte principal
│   ├── java/                 # Controllers, Services, Repositories, DTOs, Mappers
│   └── resources/            # Configurações da aplicação
├── Dockerfile                # Imagem Docker da aplicação
├── build.gradle              # Dependências e configurações de build
└── settings.gradle           # Configurações do projeto Gradle
```

---

## ⚙️ Configuração e Execução

### Pré-requisitos

- Java 21+
- Docker
- MongoDB (local ou via Docker)
- Gradle (ou use o wrapper `./gradlew`)

### Executando localmente

```bash
# Clone o repositório
git clone https://github.com/gabriela-oliveiraa/agendador-tarefas.git
cd agendador-tarefas

# Build do projeto
./gradlew build

# Execução
./gradlew bootRun
```

### Executando com Docker

```bash
# Build da imagem
docker build -t agendador-tarefas .

# Execute garantindo que o MongoDB esteja acessível
docker run -p 8080:8080 agendador-tarefas
```

> 💡 Para facilitar a execução com MongoDB, recomenda-se usar um `docker-compose.yml` orquestrando os serviços.

---

## 🗄️ Banco de Dados

Este serviço utiliza **MongoDB** como banco de dados NoSQL, ideal para o armazenamento flexível de tarefas com diferentes atributos e estruturas.

Configure a conexão no `application.properties` ou via variável de ambiente:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/db_tarefas
```

---

## 🔒 Segurança

A autenticação é baseada em **JWT (JSON Web Token)**. O token gerado pelo microsserviço `usuario` deve ser enviado no header de cada requisição:

```
Authorization: Bearer <token>
```

---

## 🔗 Integração com outros serviços

Utiliza **Spring Cloud OpenFeign** para comunicação com o microsserviço de **usuários**, validando tokens e buscando dados do usuário autenticado.

Faz parte da arquitetura de microsserviços da plataforma:

```
bff-agendador-tarefas  →  agendador-tarefas
                       →  usuario
                       →  notificacao
```

---

## 🗂️ Mapeamento de Objetos

O projeto usa **MapStruct** para conversão automática entre entidades e DTOs, mantendo o código limpo e desacoplado:

```java
@Mapper
public interface TarefaMapper {
    TarefaDTO toDTO(Tarefa tarefa);
    Tarefa toEntity(TarefaDTO dto);
}
```

---

## 🧪 Testes

```bash
./gradlew test
```

---

## 📄 Licença

Este projeto foi desenvolvido para fins de estudo e prática com microsserviços em Java/Spring Boot.
