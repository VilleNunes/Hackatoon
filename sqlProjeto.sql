CREATE TABLE
  `curso` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nome` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
 ) ENGINE = InnoDB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci

 CREATE TABLE
  `evento` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `titulo` varchar(100) NOT NULL,
    `descricao` text NOT NULL,
    `data_inicio` datetime NOT NULL,
    `data_fim` datetime NOT NULL,
    `id_palestrante` int(11) NOT NULL,
    `id_curso` int(11) NOT NULL,
    `localizacao` varchar(100) NOT NULL,
    `imagem` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `id_palestrante` (`id_palestrante`),
    KEY `id_curso` (`id_curso`),
    CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`id_palestrante`) REFERENCES `palestrante` (`id`),
    CONSTRAINT `evento_ibfk_2` FOREIGN KEY (`id_curso`) REFERENCES `curso` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 166 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci

CREATE TABLE
  `palestrante` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nome` varchar(100) NOT NULL,
    `minicurriculo` text NOT NULL,
    `foto` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 12 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci