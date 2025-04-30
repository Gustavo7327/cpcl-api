# CPCL - Conectando Pequenos Comércios Locais
A API tem o objetivo de catalogar e exibir produtos e/ou ofertas dos comércios registrados, tornando mais fácil o acesso dessas informações ao consumidor

## Tecnologias utilizadas
- **Spring Web**: Utilizado para construir a API RESTful
- **Spring Data JPA**: Utilizado para persistência de dados SQL
- **H2 Database**: Banco de dados em memória
- **Spring Security**: Utilizado para autenticação e controle de acesso, protegendo rotas da aplicação contra acesso não autorizado
- **OAuth2 Resource Server**: Utilizado para autenticação e proteção da API

### Primeiro, certifique-se de que tem o maven instalado:
```bash
mvn -v
```
### Em seguida, baixe o repositório em sua máquina:
```bash
git clone https://github.com/Gustavo7327/cpcl-api.git
```
### Entre no diretório do projeto:
```bash
cd cpcl-api
```
### Instale as dependências:
```bash
mvn clean install
```
### Pronto, agora é so rodar o projeto com:
```bash
mvn spring-boot:run
```