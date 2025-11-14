CREATE DATABASE biblioteca_db;
USE biblioteca_db;

-- Tabela de usuários (quem faz empréstimos)
CREATE TABLE usuarios (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
email VARCHAR(255)
);
-- Tabela de livros
CREATE TABLE livros (
id INT AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(255) NOT NULL,
autor VARCHAR(255) NOT NULL,
ano INT NOT NULL,
disponivel BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabela de empréstimos
CREATE TABLE emprestimos (
id INT AUTO_INCREMENT PRIMARY KEY,
livro_id INT NOT NULL,
usuario_id INT NOT NULL,
data_emprestimo DATE NOT NULL,
data_devolucao DATE,
FOREIGN KEY (livro_id) REFERENCES livros(id),
FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);




