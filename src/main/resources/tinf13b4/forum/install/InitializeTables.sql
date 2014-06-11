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

-- Exportiere Struktur von Tabelle pmforum.Categories
CREATE TABLE IF NOT EXISTS `Categories` (
  `Category_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Title` text NOT NULL,
  `Subtitle` text NOT NULL,
  PRIMARY KEY (`Category_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table for Category';

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle pmforum.Posts
CREATE TABLE IF NOT EXISTS `Posts` (
  `Post_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Content` text NOT NULL,
  `Date` datetime NOT NULL,
  `Thread_ID` int(10) unsigned NOT NULL,
  `User_ID` int(11) unsigned NOT NULL,
  PRIMARY KEY (`Post_ID`),
  UNIQUE KEY `Post_ID` (`Post_ID`),
  KEY `FK_Posts_Users` (`User_ID`),
  KEY `FK_Posts_Threads` (`Thread_ID`),
  CONSTRAINT `FK_Posts_Threads` FOREIGN KEY (`Thread_ID`) REFERENCES `Threads` (`Thread_ID`),
  CONSTRAINT `FK_Posts_Users` FOREIGN KEY (`User_ID`) REFERENCES `Users` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table for Posts';

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle pmforum.Settings
CREATE TABLE IF NOT EXISTS `Settings` (
  `Settings_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Pagedescription` text NOT NULL,
  `Imprint` text NOT NULL,
  `TermsOfUse` text NOT NULL,
  `Notification` text NOT NULL,
  `ServiceMode` tinyint(1) NOT NULL DEFAULT '0',
  `ServiceReason` text NOT NULL,
  PRIMARY KEY (`Settings_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table for Settings for Adminsettings';

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle pmforum.Threads
CREATE TABLE IF NOT EXISTS `Threads` (
  `Thread_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Title` text NOT NULL,
  `Content` text NOT NULL,
  `Date` datetime NOT NULL,
  `ReadOnly` tinyint(3) unsigned NOT NULL,
  `User_ID` int(10) unsigned NOT NULL,
  `Category_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Thread_ID`),
  UNIQUE KEY `Thread_ID` (`Thread_ID`),
  KEY `FK_Threads_Category` (`Category_ID`),
  KEY `User_ID` (`User_ID`),
  CONSTRAINT `Category_ID` FOREIGN KEY (`Category_ID`) REFERENCES `Categories` (`Category_ID`),
  CONSTRAINT `User_ID` FOREIGN KEY (`User_ID`) REFERENCES `Users` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table for Threads';

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle pmforum.Users
CREATE TABLE IF NOT EXISTS `Users` (
  `User_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(64) NOT NULL DEFAULT '0',
  `Picture` text,
  `Email` varchar(254) NOT NULL DEFAULT '0',
  `Password` varchar(61) NOT NULL DEFAULT '0',
  `JoinedOn` date NOT NULL,
  `Confirmed` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `Confirmation_Key` varchar(36) NOT NULL DEFAULT '0',
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table for Users';

-- Daten Export vom Benutzer nicht ausgewählt
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
