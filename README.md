# Sistema de Monitoramento de Clima

## 📋 Descrição do Projeto
Sistema completo desenvolvido com Spring Boot para consulta e monitoramento de dados climáticos de diferentes cidades. A aplicação utiliza a API pública Open-Meteo para obter dados climáticos em tempo real, armazenando-os em banco de dados para consultas históricas.

## 🚀 Funcionalidades

### 1. Tela de Cadastro de Cidades (Admin)
- Acesso exclusivo para usuários com perfil **Admin**
- Formulário para cadastro de cidades com:
  - Nome da cidade
  - Latitude
  - Longitude
- Persistência dos dados no banco de dados H2 via JPA

### 2. Tela de Consulta de Clima (Admin/User)
- Acesso para usuários com perfil **User** e **Admin**
- Lista das cidades cadastradas
- Exibição dos dados climáticos de cada cidade:
  - Temperatura
  - Precipitação
  - Velocidade do vento
  - Condições climáticas
- Persistência automática dos dados no banco de dados

### 3. Tela Inicial (Login)
- Autenticação via Spring Security
- Suporte a diferentes perfis de usuário (**Admin** e **User**)
- Redirecionamento baseado no perfil do usuário

## 🛠️ Tecnologias Utilizadas

- **Spring Boot 3.2.3**: Framework base para o desenvolvimento da aplicação
- **Spring Security**: Controle de acesso e autenticação
- **Spring Data JPA**: Persistência de dados e mapeamento objeto-relacional
- **Thymeleaf**: Template engine para as páginas HTML
- **RestClient**: Cliente REST nativo do Spring para integração com a API Open-Meteo
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes
- **Internacionalização (i18n)**: Suporte para múltiplos idiomas (português e inglês)
- **Lombok**: Redução de código boilerplate
- **Gradle**: Gerenciamento de dependências e build

## 🗄️ Modelo de Dados

### Cidade
- id: Long (chave primária)
- nome: String
- latitude: Double
- longitude: Double
- climas: One-to-Many relacionamento com Clima

### Clima
- id: Long (chave primária)
- cidade: Many-to-One relacionamento com Cidade
- temperatura: Double
- velocidadeVento: Double
- condicaoClimatica: String
- precipitacao: Double
- dataConsulta: LocalDateTime

## 🔒 Segurança e Perfis

- **Admin**: Acesso completo ao sistema, incluindo cadastro e consulta
- **User**: Acesso restrito apenas à consulta dos dados climáticos

## 🌐 Endpoints

### Cidade
- GET /admin/cidades – Lista cidades cadastradas (Admin)
- GET /admin/cidades/novo – Formulário para adicionar nova cidade (Admin)
- POST /admin/cidades – Adiciona nova cidade (Admin)
- GET /admin/cidades/{id}/editar – Edita cidade existente (Admin)
- GET /admin/cidades/{id}/excluir – Remove cidade (Admin)

### Clima
- GET /clima/consulta – Exibe formulário para consulta do clima (Admin/User)
- POST /clima/consultar – Consulta e persiste os dados climáticos (Admin/User)
- GET /clima/historico/{cidadeId} – Exibe histórico de consultas para uma cidade (Admin/User)

## 🌎 Internacionalização

- Suporte para dois idiomas: Português (Brasil) e Inglês
- Arquivos de mensagens: messages_pt_BR.properties e messages_en.properties
- Seleção de idioma via parâmetro "lang" na URL

## 🚀 Como Executar o Projeto

### Pré-requisitos
- JDK 17 ou superior
- Gradle

### Passos para execução

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/clima-app.git
cd clima-app
```

2. Compile o projeto com Gradle:
```bash
./gradlew build
```

3. Execute a aplicação:
```bash
./gradlew bootRun
```

4. Acesse a aplicação:
```
http://localhost:8080
```

5. Credenciais de acesso:
   - Admin: usuário `admin`, senha `admin`
   - User: usuário `user`, senha `user`

6. Para acessar o console do H2:
```
http://localhost:8080/h2-console
```
- JDBC URL: jdbc:h2:mem:climadb
- Usuário: sa
- Senha: (deixe em branco)

## 📋 Estrutura do Projeto

```
clima-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── clima/
│   │   │               ├── ClimaApplication.java
│   │   │               ├── client/
│   │   │               │   └── OpenMeteoClient.java
│   │   │               ├── config/
│   │   │               │   ├── I18nConfig.java
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller/
│   │   │               │   ├── CidadeController.java
│   │   │               │   ├── ClimaController.java
│   │   │               │   └── LoginController.java
│   │   │               ├── dto/
│   │   │               │   └── OpenMeteoResponse.java
│   │   │               ├── model/
│   │   │               │   ├── Cidade.java
│   │   │               │   └── Clima.java
│   │   │               ├── repository/
│   │   │               │   ├── CidadeRepository.java
│   │   │               │   └── ClimaRepository.java
│   │   │               └── service/
│   │   │                   ├── CidadeService.java
│   │   │                   └── ClimaService.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── messages_en.properties
│   │   │   ├── messages_pt_BR.properties
│   │   │   ├── static/
│   │   │   └── templates/
│   │   │       ├── cidade/
│   │   │       │   ├── form.html
│   │   │       │   └── lista.html
│   │   │       ├── clima/
│   │   │       │   ├── consulta.html
│   │   │       │   └── historico.html
│   │   │       ├── error/
│   │   │       │   ├── 403.html
│   │   │       │   └── 404.html
│   │   │       ├── fragments/
│   │   │       │   └── layout.html
│   │   │       ├── home.html
│   │   │       └── login.html
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── clima/
│                       └── ClimaApplicationTests.java
├── build.gradle
├── README.md
└── .gitignore
```

## 📝 Considerações Adicionais

- A aplicação usa um banco de dados em memória (H2) para facilitar o desenvolvimento e testes. Em um ambiente de produção, seria recomendado substituir por um banco de dados persistente.
- O sistema de autenticação utiliza usuários em memória para simplicidade. Em um ambiente de produção, considere implementar autenticação baseada em banco de dados.
- A API Open-Meteo tem limites de uso, verifique a documentação oficial para mais informações.
