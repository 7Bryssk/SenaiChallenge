-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 05-Jun-2018 às 23:20
-- Versão do servidor: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projeto`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `frases`
--

CREATE TABLE `frases` (
  `idfrase` int(10) UNSIGNED NOT NULL,
  `nfrase` int(10) DEFAULT NULL,
  `frase` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `inimigos`
--

CREATE TABLE `inimigos` (
  `idInimigo` int(10) UNSIGNED NOT NULL,
  `Nome` varchar(45) DEFAULT NULL,
  `Velocidade` int(10) DEFAULT NULL,
  `Dano` int(10) DEFAULT NULL,
  `foto` varchar(100) DEFAULT NULL,
  `altura` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `inimigos`
--

INSERT INTO `inimigos` (`idInimigo`, `Nome`, `Velocidade`, `Dano`, `foto`, `altura`) VALUES
(1, 'Chuveiro', 1, 50, '/Arquivos/chuveiro.gif', 100),
(2, 'Cafeteira', 2, 35, '/Arquivos/cafeteira.gif', 69),
(3, 'Computador', 1, 25, '/Arquivos/computador.gif', 77),
(4, 'Ferro', 8, 15, '/Arquivos/Ferro.gif', 44),
(5, 'Ventilador', 1, 37, '/Arquivos/Ventilador.gif', 96);

-- --------------------------------------------------------

--
-- Estrutura da tabela `personagens`
--

CREATE TABLE `personagens` (
  `idPersonagem` int(10) UNSIGNED NOT NULL,
  `Nome` varchar(45) DEFAULT NULL,
  `Velocidade` int(10) DEFAULT NULL,
  `Vida` int(10) DEFAULT NULL,
  `Altura` int(10) DEFAULT NULL,
  `Preco` int(10) DEFAULT NULL,
  `gif` varchar(100) DEFAULT NULL,
  `pulo` varchar(100) DEFAULT NULL,
  `foto` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `personagens`
--

INSERT INTO `personagens` (`idPersonagem`, `Nome`, `Velocidade`, `Vida`, `Altura`, `Preco`, `gif`, `pulo`, `foto`) VALUES
(1, 'Jack', 15, 1500, -13, 0, '/Arquivos/jackg.gif', '/Arquivos/Jackp.png', NULL),
(2, 'Jack Zombie', 100, 150, -11, 1500, '/Arquivos/JackZg.gif', '/Arquivos/JackZp.png', NULL),
(9, 'JackNinjag', 10, 500, -17, 1, '/Arquivos/JackNinjag.gif', '/Arquivos/JackNinjap.png', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `personagenspessoa`
--

CREATE TABLE `personagenspessoa` (
  `idPersonagensPessoa` int(10) UNSIGNED NOT NULL,
  `IdPessoa` int(10) UNSIGNED NOT NULL,
  `IdPersonagem` int(10) UNSIGNED NOT NULL,
  `Uso` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `personagenspessoa`
--

INSERT INTO `personagenspessoa` (`idPersonagensPessoa`, `IdPessoa`, `IdPersonagem`, `Uso`) VALUES
(1, 1, 1, 0),
(11, 1, 2, 0),
(12, 1, 9, 1),
(14, 2, 1, 0),
(15, 2, 9, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoas`
--

CREATE TABLE `pessoas` (
  `idPessoa` int(10) UNSIGNED NOT NULL,
  `Nome` varchar(45) DEFAULT NULL,
  `Login` varchar(45) DEFAULT NULL,
  `Senha` varchar(16) DEFAULT NULL,
  `Moedas` int(10) DEFAULT NULL,
  `Pontuacao` int(10) DEFAULT NULL,
  `ADM` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `pessoas`
--

INSERT INTO `pessoas` (`idPessoa`, `Nome`, `Login`, `Senha`, `Moedas`, `Pontuacao`, `ADM`) VALUES
(1, 'Bruno', 'bonaky', '1010', 3794, 12122, 1),
(2, 'bonaky', '7Bryssk', '1010', 156, 556, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `frases`
--
ALTER TABLE `frases`
  ADD PRIMARY KEY (`idfrase`);

--
-- Indexes for table `inimigos`
--
ALTER TABLE `inimigos`
  ADD PRIMARY KEY (`idInimigo`);

--
-- Indexes for table `personagens`
--
ALTER TABLE `personagens`
  ADD PRIMARY KEY (`idPersonagem`);

--
-- Indexes for table `personagenspessoa`
--
ALTER TABLE `personagenspessoa`
  ADD PRIMARY KEY (`idPersonagensPessoa`),
  ADD KEY `fk_PersonagensPessoa_Pessoas1_idx` (`IdPessoa`),
  ADD KEY `fk_PersonagensPessoa_Personagens1_idx` (`IdPersonagem`);

--
-- Indexes for table `pessoas`
--
ALTER TABLE `pessoas`
  ADD PRIMARY KEY (`idPessoa`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `frases`
--
ALTER TABLE `frases`
  MODIFY `idfrase` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `inimigos`
--
ALTER TABLE `inimigos`
  MODIFY `idInimigo` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `personagens`
--
ALTER TABLE `personagens`
  MODIFY `idPersonagem` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `personagenspessoa`
--
ALTER TABLE `personagenspessoa`
  MODIFY `idPersonagensPessoa` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `pessoas`
--
ALTER TABLE `pessoas`
  MODIFY `idPessoa` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `personagenspessoa`
--
ALTER TABLE `personagenspessoa`
  ADD CONSTRAINT `fk_PersonagensPessoa_Personagens1` FOREIGN KEY (`IdPersonagem`) REFERENCES `personagens` (`idPersonagem`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_PersonagensPessoa_Pessoas1` FOREIGN KEY (`IdPessoa`) REFERENCES `pessoas` (`idPessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
