# UniAlfa Eventos

## 🧠 Descrição

Imagine um ambiente onde poderemos concentrar todos os eventos da Faculdade UniALFA: jornadas e semanas acadêmicas, seminários, palestras, cursos etc. O objetivo é que a proposta seja algo **simples**, sem complicações, sem formulários dispersos, no qual o **atrativo principal**, que são os eventos, sejam visíveis de forma clara e objetiva.

Este projeto é o resultado de um **hackathon institucional**, que teve como desafio criar uma plataforma interativa para **organizar e promover os eventos acadêmicos da UniALFA**.

## 🎯 Objetivos da Plataforma

- Permitir que **coordenadores de curso** cadastrem eventos com facilidade.
- Possibilitar que **alunos visualizem a programação, realizem inscrições e gerem seus certificados**.
- Apresentar dados dos **palestrantes**, como fotos, minicurrículo e temas abordados.
- Evitar **conflitos de horários** entre os eventos.
- Permitir **filtros por curso** e manter todas as informações organizadas.
- Ter foco em **usabilidade**, **experiência do usuário** e **escalabilidade**.

## 🚀 Tecnologias Utilizadas

- **Backend:**
  - Node.js
  - Express
  - Knex.js
  - TypeScript
  - Java (com Maven)
  - PHP

- **Frontend:**
  - Tailwind CSS

## 📦 Instalação e Execução

### Pré-requisitos

- Node.js e npm
- MySQL
- Java (JDK) para módulos em Java
- Servidor local para PHP

### Passos para rodar o projeto



```bash
# Clone o repositório
git clone https://github.com/VilleNunes/Hackatoon.git

- Crie um banco chamado unialfa


- Execute o códico SQL
```sql
CREATE TABLE eventos (
  id INT PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(255),
  descricao TEXT,
  data_inicio DATE,
  data_fim DATE,
  id_palestrante INT,
  id_curso INT,
  localizacao VARCHAR(255),
  imagem TEXT
);

CREATE TABLE palestrantes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255),
  minicurriculo TEXT,
  foto TEXT
);

CREATE TABLE cursos (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255)
);

# Acesse a pasta do projeto
cd Node

# Instale as dependências do Node
npm install

# Rode as migrações
npx knex migrate:latest

# Inicie o servidor
npm run dev

# Entere na Pasta do Php
cd Php

# Rode o Servidor
php -S localhost:8080
```

## 👥 Autores

- **Bruno Cernach Madeira**
- **Gabriel de Abreu da Silva**
- **Heitor dos Santos Corrêa**
- **Ville Nunes Figueiredo da Silva**
