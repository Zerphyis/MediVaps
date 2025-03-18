# MediVaps

`MediVaps` é um projeto de demonstração para um sistema de gerenciamento de médicos e pacientes, utilizando o framework Spring Boot. O projeto envolve autenticação de usuários, gerenciamento de médicos, pacientes e outras funcionalidades de um sistema médico básico.

## Tecnologias Utilizadas

- **Spring Boot**: Framework principal para desenvolvimento da aplicação.
- **Spring Data JPA**: Para manipulação de dados em banco de dados relacional.
- **Spring Security**: Para segurança e autenticação de usuários.
- **MySQL**: Banco de dados utilizado para persistência de dados.
-  **JWT** (Autenticação baseada em token)
-  **Spring Mail Sender** Para mandar email com a aplicação
- **OpenCSV**: Para leitura e escrita de arquivos CSV.
- **Spring Validation**: Para validação de dados de entrada.
- **Spring Boot Test**: Para realizar testes na aplicação.

## Como Rodar a Aplicação

### Requisitos

- **Java 17 ou superior**
- **Maven** (para gerenciamento de dependências)
- **MySQL** (ou outro banco de dados configurado)

### Passos

1. **Clone o repositório:**

   Clone este repositório para a sua máquina local:
   
   ````´bash
   git clone https://github.com/seu-usuario/MediVaps.git
````

spring.datasource.url=jdbc:mysql://localhost:3306/seu banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=seu_email_para_envio
spring.mail.password=senha_gerada_para_envio
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
api.security.token.secret=${JWT_SECRET:my-secret-key}


mvn spring-boot:run
O servidor estará rodando na URL http://localhost:8080.
````

## Endpoints da API
#### Autenticação
### **POST /login/register**
<br>

![Image](https://github.com/user-attachments/assets/a14af7e6-4dbd-4ace-ac83-e6a4c2da3aa2)
<br>

### **POST /login/auth**
<br>

![Image](https://github.com/user-attachments/assets/1d1a6d69-fe52-4db6-a2ff-595304ef676f)
<br>
#### Médicos
### **GET /medicos**
Retorna todos os médicos cadastrados.
<br>

![Image](https://github.com/user-attachments/assets/c3db9fce-4629-427e-b7bd-7738f60635f8)
<br>
### **GET /medicos/{id}**
Retorna um médico específico com base no ID.
<br>

![Image](https://github.com/user-attachments/assets/3eb50019-904e-4590-b09b-7f5da2dfeb9c)
<br>
### **POST /medicos**
Cria um novo médico. Exemplo de corpo da requisição:
<br>

![Image](https://github.com/user-attachments/assets/5e94416d-feea-44b1-864c-c2086bb36f88)
<br>
### **PUT /medicos/{id}**
Atualiza os dados de um médico com base no ID.
<br>

![Image](https://github.com/user-attachments/assets/db00d46e-0b00-4101-9840-f998f11cb2bd)
<br>
### **DELETE /medicos/{id}**
Exclui um médico com base no ID.
<br>

![Image](https://github.com/user-attachments/assets/58764506-45ca-4027-be4c-5a8a27d79471)
<br>
#### Pacientes
### **GET /pacientes**
Retorna todos os pacientes cadastrados.
<br>

![Image](https://github.com/user-attachments/assets/ba6de951-2cf3-4834-b5c5-1bef418a32ce)
<br>
### **GET /pacientes/{id}**
Retorna um paciente específico com base no ID.
<br>

![Image](https://github.com/user-attachments/assets/e881f02b-756e-4136-b158-7284f5f7b998)
<br>

### **POST /pacientes**
Cria um novo paciente. Exemplo de corpo da requisição:
<br>

![Image](https://github.com/user-attachments/assets/19943566-e876-4231-a4b3-401876a25e20)
<br>

###  **PUT /pacientes/{id}**
Atualiza os dados de um paciente com base no ID.
<br>

![Image](https://github.com/user-attachments/assets/3a3356c2-d494-4cfb-b09f-f79febcb5ed5)
<br>

### **DELETE /pacientes/{id}**
Exclui um paciente com base no ID.

<br>

![Image](https://github.com/user-attachments/assets/7ce6bc12-d61f-4844-acdf-17c216901066)
<br>

#### Consultas
### **GET /consultas**
Retorna todas as consultas agendadas.
<br>

![Image](https://github.com/user-attachments/assets/c423c513-6380-473c-b843-d981841f911e)
<br>
### **GET /consultas/{id}**
Retorna uma consulta específica com base no ID.
<br>

![Image](https://github.com/user-attachments/assets/a65c771e-9697-4ae0-af2b-7ab274026e8d)
<br>
### **GET /export**
Exporta os agendamentos de consultas para um arquivo CSV com base no ano e mês fornecidos. Exemplo de corpo da requisição:
<br>
GET /export?year=2025&month=03
Esse endpoint exporta todos os agendamentos para um arquivo CSV.
<br>

![Image](https://github.com/user-attachments/assets/df78a587-3f65-49d0-879f-8b31c8b60cec)
<br>
### **POST /consultas**
Cria uma nova consulta. Exemplo de corpo da requisição:
<br>

![Image](https://github.com/user-attachments/assets/58393269-7911-4c75-9373-4e90cd9f2c04)
<br>
### **PUT /consultas/{id}**
Atualiza os dados de uma consulta com base no ID.
<br>

![Image](https://github.com/user-attachments/assets/c2951c09-c1b4-424d-a721-b75e6cb4c03f)
<br>
### **DELETE /consultas/{id}**
Exclui uma consulta com base no ID.
<br>

![Image](https://github.com/user-attachments/assets/edc37210-0223-45d0-8d60-3fb688a96aad)
<br>

### Estrutura do Projeto
#### Controllers
<br>
AutheticationController: Gerencia login e registro de usuários.
<br>
DoctorsController: Gerencia operações de médicos.
<br>
PatientController: Gerencia operações de pacientes.
<br>
AppointmentController: Gerencia operações de consultas.
<br>

#### Entities
<br>
Login: Representa a entidade de login do usuário.
<br>
Doctors: Representa a entidade de médico.
<br>
Patient: Representa a entidade de paciente.
<br>
Appointment: Representa a entidade de consulta.
<br>

#### Service
<br>
LoginService: Gerencia a criação de usuários e autenticação.
<br>
DoctorsService: Gerencia operações relacionadas aos médicos.
<br>
PatientService: Gerencia operações relacionadas aos pacientes.
<br>
AppointmentService: Gerencia operações relacionadas às consultas.



