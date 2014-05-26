-- --------------------------------------------------------
-- Host:                         5.45.110.9
-- Server Version:               5.5.37-0ubuntu0.12.04.1 - (Ubuntu)
-- Server Betriebssystem:        debian-linux-gnu
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Exportiere Struktur von Tabelle pmforum.Category
CREATE TABLE IF NOT EXISTS `Category` (
  `Category_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` text NOT NULL,
  `Subtitle` text NOT NULL,
  PRIMARY KEY (`Category_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table for Category';

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle pmforum.Posts
CREATE TABLE IF NOT EXISTS `Posts` (
  `Post_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Content` text,
  `Poster_ID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`Post_ID`),
  UNIQUE KEY `Post_ID` (`Post_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table for Posts';

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle pmforum.ThreadPostRelationTable
CREATE TABLE IF NOT EXISTS `ThreadPostRelationTable` (
  `ThreadPost_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Thread_ID` int(10) DEFAULT NULL,
  `Post_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ThreadPost_ID`),
  KEY `FK_ThreadPostRelationTable_Threads` (`Thread_ID`),
  KEY `FK_ThreadPostRelationTable_Posts` (`Post_ID`),
  CONSTRAINT `FK_ThreadPostRelationTable_Posts` FOREIGN KEY (`Post_ID`) REFERENCES `Posts` (`Post_ID`),
  CONSTRAINT `FK_ThreadPostRelationTable_Threads` FOREIGN KEY (`Thread_ID`) REFERENCES `Threads` (`Thread_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Relational Table';

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle pmforum.Threads
CREATE TABLE IF NOT EXISTS `Threads` (
  `Thread_ID` int(10) NOT NULL AUTO_INCREMENT,
  `Name` text,
  `Content` text,
  `Thread Starter` int(10) unsigned DEFAULT NULL,
  `Creation Date` date DEFAULT NULL,
  `ReadOnly` tinyint(3) unsigned DEFAULT NULL,
  `Category_ID` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Thread_ID`),
  UNIQUE KEY `Thread_ID` (`Thread_ID`),
  KEY `FK_Threads_Category` (`Category_ID`),
  CONSTRAINT `FK_Threads_Category` FOREIGN KEY (`Category_ID`) REFERENCES `Category` (`Category_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table for Threads';

-- Daten Export vom Benutzer nicht ausgewählt
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
