-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mag 30, 2016 alle 17:41
-- Versione del server: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_database_scolastico`
--
CREATE DATABASE IF NOT EXISTS `db_database_scolastico` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_database_scolastico`;

-- --------------------------------------------------------

--
-- Struttura della tabella `amministratore`
--

CREATE TABLE IF NOT EXISTS `amministratore` (
`id` int(12) NOT NULL,
  `tipo_utente` enum('-1','0','1','2','3') DEFAULT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(64) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `cf` char(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `file`
--

CREATE TABLE IF NOT EXISTS `file` (
  `id_utente` int(12) NOT NULL,
  `path_file` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `file`
--

INSERT INTO `file` (`id_utente`, `path_file`) VALUES
(1, 'C:\\Users\\Proprietario\\AppData\\Roaming\\NetBeans\\8.1\\config\\GF_4.1.1\\domain1\\config\\files\\1\\example.txt'),
(1, 'files/1/gigi.txt');

-- --------------------------------------------------------

--
-- Struttura della tabella `log_line`
--

CREATE TABLE IF NOT EXISTS `log_line` (
`id` int(15) NOT NULL,
  `utente` varchar(40) NOT NULL,
  `azione_eseguita` varchar(300) NOT NULL,
  `datetime_esecuzione` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE IF NOT EXISTS `utente` (
`id` int(12) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `cf` char(16) NOT NULL,
  `tipo_utente` enum('personale_ata','professore','studente_biennio','studente_triennio') NOT NULL,
  `data_nascita` date NOT NULL,
  `luogo_nascita` varchar(60) NOT NULL,
  `provenienza` varchar(60) DEFAULT NULL,
  `istituto` enum('ITT','IPSIA','ITE') DEFAULT NULL,
  `residenza` varchar(60) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`id`, `nome`, `cognome`, `cf`, `tipo_utente`, `data_nascita`, `luogo_nascita`, `provenienza`, `istituto`, `residenza`) VALUES
(1, 'uyut', 'ttt', 'HSD45616EASD5', 'professore', '1985-10-06', 'Vittorio Veneto', 'Italia', 'ITT', 'Vittorio Veneto');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `amministratore`
--
ALTER TABLE `amministratore`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `file`
--
ALTER TABLE `file`
 ADD PRIMARY KEY (`path_file`), ADD KEY `id_utente` (`id_utente`);

--
-- Indexes for table `log_line`
--
ALTER TABLE `log_line`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utente`
--
ALTER TABLE `utente`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `amministratore`
--
ALTER TABLE `amministratore`
MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `log_line`
--
ALTER TABLE `log_line`
MODIFY `id` int(15) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `utente`
--
ALTER TABLE `utente`
MODIFY `id` int(12) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `file`
--
ALTER TABLE `file`
ADD CONSTRAINT `file_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
