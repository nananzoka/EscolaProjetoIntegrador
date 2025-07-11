## 0. Estrutura Técnica Comum para Todos os Projetos:
- **Backend**: Spring Boot 3.x, criar usando site springinitializer.io
- **Banco de Dados**: PostgreSQL com servidor online NEON.TECH e base de dados de nome = biblioteca
- **ORM**: JPA/Hibernate
- **Segurança**: Spring Security + JWT - Não é obrigatorio...
- **Documentação**: Swagger/OpenAPI - Exemplo no projeto MAGALU
- **Testes**: JUnit 5 + Mockito
- **Build**: Maven
- **Versionamento**: Git

## 1. Sistema de Gestão de Escola
- **Funcionalidades**: Alunos, professores, turmas, notas
- **Tecnologias**: Spring Boot, JPA, PostgreSQL
- **APIs**: CRUD de alunos, professores, turmas, notas
- **Diferencial**: Portal para pais acompanharem notas e frequência


### Funcionalidades Detalhadas do Sistema de Gestão de Escola:

#### 2. Cadastro de Alunos -ok 
- **ID do Aluno**: Código único do aluno
- **Nome Completo**: Nome e sobrenome

- **Cidade**: Nome da cidade
- **Estado**: UF

- **Telefone**: Número de contato
- **Email**: Endereço de email

- **Turma Atual**: Referência à turma atual
- **Status**: Matriculado, Transferido, Concluído, Cancelado

#### 3. Cadastro de Professores - ok
- **ID do Professor**: Código único do professor
- **Nome Completo**: Nome e sobrenome

- **Cidade**: Nome da cidade
- **Estado**: UF

- **Telefone**: Número de contato
- **Email**: Endereço de email

- **Data de Contratação**: Data em que foi contratado

- **Área de Atuação**: Disciplinas que leciona
- **Status**: Ativo, Inativo, Aposentado


#### 4. Cadastro de Turmas
- **ID da Turma**: Código único da turma
- **Nome da Turma**: Identificação da turma (ex: 1º Ano A)

- **Série/Ano**: Ano escolar (1º ano, 2º ano, etc.)
- **Turno**: Matutino, Vespertino, Noturno

- **Ano Letivo**: Ano de referência

- **Sala**: Número ou identificação da sala

- **Status**: Ativa, Inativa, Concluída
- **Horário de Aula**: Horários das aulas
- **Disciplinas**: Lista de disciplinas da turma


#### 5. Cadastro de Disciplinas
- **ID da Disciplina**: Código único da disciplina
- **Nome**: Nome da disciplina (Matemática, Português, etc.)

- **Série**: Para qual série é destinada

- **Professor**: Referência ao professor responsável - Professores que lecionam a disciplina[array com id]

- **Status**: Ativa, Inativa

#### 6. Cadastro de Notas
- **ID da Nota**: Código único da nota
- **Aluno**: Referência ao aluno
- **Disciplina**: Referência à disciplina
- **Turma**: Referência à turma

- **Tipo de Avaliação**: Prova, Trabalho, Atividade, Recuperação
- **Nota**: Valor da nota (0 a 10)
- **Data da Avaliação**: Data em que foi aplicada

- **Professor**: Referência ao professor que aplicou


## Banco de Dados
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    endereco TEXT,
    data_nascimento DATE,
    tipo_usuario VARCHAR(50) NOT NULL,
    matricula VARCHAR(50),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Ativo',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuario(id),
    nome_completo VARCHAR(255) NOT NULL,
    matricula VARCHAR(50) UNIQUE NOT NULL,
    data_nascimento DATE NOT NULL,
    nome_pai VARCHAR(255),
    nome_mae VARCHAR(255),
    responsavel_id INTEGER REFERENCES usuario(id),
    endereco TEXT,
    telefone VARCHAR(20),
    email VARCHAR(255),
    serie_atual VARCHAR(50),
    turma_atual VARCHAR(50),
    data_matricula DATE DEFAULT CURRENT_DATE,
    status VARCHAR(20) DEFAULT 'Ativo',
    observacoes TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE professor (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuario(id),
    nome_completo VARCHAR(255) NOT NULL,
    matricula VARCHAR(50) UNIQUE NOT NULL,
    formacao VARCHAR(255),
    especialidade VARCHAR(255),
    data_contratacao DATE,
    telefone VARCHAR(20),
    email VARCHAR(255),
    endereco TEXT,
    status VARCHAR(20) DEFAULT 'Ativo',
    observacoes TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE turma (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    serie VARCHAR(50) NOT NULL,
    ano_letivo INTEGER NOT NULL,
    periodo VARCHAR(20) NOT NULL,
    sala VARCHAR(50),
    capacidade_maxima INTEGER,
    professor_responsavel INTEGER REFERENCES professor(id),
    status VARCHAR(20) DEFAULT 'Ativa',
    observacoes TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE disciplina (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    carga_horaria INTEGER NOT NULL,
    serie VARCHAR(50) NOT NULL,
    professor_id INTEGER REFERENCES professor(id),
    descricao TEXT,
    status VARCHAR(20) DEFAULT 'Ativa',
    observacoes TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE nota (
    id SERIAL PRIMARY KEY,
    aluno_id INTEGER NOT NULL REFERENCES aluno(id),
    disciplina_id INTEGER NOT NULL REFERENCES disciplina(id),
    turma_id INTEGER NOT NULL REFERENCES turma(id),
    bimestre INTEGER NOT NULL,
    tipo_avaliacao VARCHAR(50) NOT NULL,
    nota DECIMAL(4,2) NOT NULL,
    peso DECIMAL(3,2) DEFAULT 1.00,
    data_avaliacao DATE NOT NULL,
    professor_id INTEGER NOT NULL REFERENCES professor(id),
    observacoes TEXT,
    data_lancamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE frequencia (
    id SERIAL PRIMARY KEY,
    aluno_id INTEGER NOT NULL REFERENCES aluno(id),
    disciplina_id INTEGER NOT NULL REFERENCES disciplina(id),
    turma_id INTEGER NOT NULL REFERENCES turma(id),
    data_aula DATE NOT NULL,
    presente BOOLEAN DEFAULT true,
    justificativa TEXT,
    professor_id INTEGER NOT NULL REFERENCES professor(id),
    observacoes TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE gestor (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuario(id),
    nome_completo VARCHAR(255) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    data_nomeacao DATE NOT NULL,
    area_responsabilidade VARCHAR(255),
    permissoes TEXT,
    status VARCHAR(20) DEFAULT 'Ativo',
    observacoes TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
