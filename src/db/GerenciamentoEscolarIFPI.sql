    -- Criação do Banco de Dados
    CREATE DATABASE GerenciamentoEscolarIFPI;
    USE GerenciamentoEscolarIFPI;

    -- Criação das Tabelas

    -- 1. Tabela Professores (não possui chaves estrangeiras, então deve ser criada primeiro)
    CREATE TABLE Professores (
        id_professor INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(100),
        data_contratacao DATE,
        departamento VARCHAR(100),
        telefone VARCHAR(15),
        email VARCHAR(100)
    );

    -- 2. Tabela Cursos (possui chave estrangeira para Professores)
    CREATE TABLE Cursos (
        id_curso INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(100),
        descricao TEXT,
        duracao_horas INT,
        id_professor INT,
        FOREIGN KEY (id_professor) REFERENCES Professores(id_professor)
    );

    -- 3. Tabela Alunos (não possui chaves estrangeiras, então pode ser criada agora)
    CREATE TABLE Alunos (
        id_aluno INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(100),
        data_nascimento DATE,
        endereco VARCHAR(200),
        telefone VARCHAR(15),
        email VARCHAR(100)
    );

    -- 4. Tabela Matriculas (possui chaves estrangeiras para Alunos e Cursos)
    CREATE TABLE Matriculas (
        id_matricula INT AUTO_INCREMENT PRIMARY KEY,
        id_aluno INT,
        id_curso INT,
        data_matricula DATE,
        FOREIGN KEY (id_aluno) REFERENCES Alunos(id_aluno),
        FOREIGN KEY (id_curso) REFERENCES Cursos(id_curso)
    );

    -- 5. Tabela Notas (possui chave estrangeira para Matriculas)
    CREATE TABLE Notas (
        id_nota INT AUTO_INCREMENT PRIMARY KEY,
        id_matricula INT,
        nota DECIMAL(4, 2),
        data_avaliacao DATE,
        FOREIGN KEY (id_matricula) REFERENCES Matriculas(id_matricula)
    );

    -- Inserção de Dados

    -- Inserção na Tabela Professores
    INSERT INTO Professores (nome, data_contratacao, departamento, telefone, email) VALUES
    ('Professor A', '2015-02-10', 'Matemática', '666666666', 'prof.a@example.com'),
    ('Professor B', '2016-07-12', 'História', '777777777', 'prof.b@example.com'),
    ('Professor C', '2017-09-01', 'Biologia', '888888888', 'prof.c@example.com'),
    ('Professor D', '2018-03-05', 'Física', '999999999', 'prof.d@example.com'),
    ('Professor E', '2019-05-23', 'Química', '101010101', 'prof.e@example.com');

    -- Inserção na Tabela Cursos
    INSERT INTO Cursos (nome, descricao, duracao_horas, id_professor) VALUES
    ('Matemática Básica', 'Curso introdutório de matemática.', 40, 1),
    ('História Geral', 'Curso abrangente sobre história mundial.', 60, 2),
    ('Biologia Avançada', 'Estudos aprofundados em biologia.', 80, 3),
    ('Física Teórica', 'Exploração de conceitos teóricos de física.', 50, 4),
    ('Química Orgânica', 'Curso focado em química orgânica.', 45, 5);

    -- Inserção na Tabela Alunos
    INSERT INTO Alunos (nome, data_nascimento, endereco, telefone, email) VALUES
    ('João Silva', '2005-05-15', 'Rua A, 123', '111111111', 'joao.silva@example.com'),
    ('Maria Oliveira', '2006-08-21', 'Rua B, 456', '222222222', 'maria.oliveira@example.com'),
    ('Carlos Souza', '2004-12-10', 'Rua C, 789', '333333333', 'carlos.souza@example.com'),
    ('Ana Pereira', '2005-03-30', 'Rua D, 101', '444444444', 'ana.pereira@example.com'),
    ('Lucas Fernandes', '2006-11-11', 'Rua E, 202', '555555555', 'lucas.fernandes@example.com');

    -- Inserção na Tabela Matriculas
    INSERT INTO Matriculas (id_aluno, id_curso, data_matricula) VALUES
    (1, 1, '2024-01-15'),
    (2, 2, '2024-02-20'),
    (3, 3, '2024-03-10'),
    (4, 4, '2024-04-05'),
    (5, 5, '2024-05-12');

    -- Inserção na Tabela Notas
    INSERT INTO Notas (id_matricula, nota, data_avaliacao) VALUES
    (1, 8.5, '2024-06-15'),
    (2, 9.0, '2024-07-18'),
    (3, 7.5, '2024-08-20'),
    (4, 6.0, '2024-09-10'),
    (5, 10.0, '2024-10-05');
