-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 14, 2014 at 09:45 AM
-- Server version: 5.5.38-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `CLOUDLYRA`
--

-- --------------------------------------------------------

--
-- Table structure for table `AnalyticEngine`
--

CREATE TABLE IF NOT EXISTS `AnalyticEngine` (
  `analyticEngineID` varchar(30) DEFAULT NULL,
  `analyticEngineName` varchar(30) DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `port` varchar(30) DEFAULT NULL,
  `api` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `AnalyticEngine`
--

INSERT INTO `AnalyticEngine` (`analyticEngineID`, `analyticEngineName`, `ip`, `port`, `api`) VALUES
('es', 'esper', 'localhost', '8080', '/ESPERStreamProcessing/rest/esper'),
('jbpm', 'jbpm', 'localhost', '8080', '/JBPMEngine/rest/jbpm');

-- --------------------------------------------------------

--
-- Table structure for table `Daf`
--

CREATE TABLE IF NOT EXISTS `Daf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `file` mediumblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

-- --------------------------------------------------------

--
-- Table structure for table `Event`
--

CREATE TABLE IF NOT EXISTS `Event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `daf` varchar(30) DEFAULT NULL,
  `detected_time` varchar(50) DEFAULT NULL,
  `event_values` varchar(255) DEFAULT NULL,
  `severity` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1797 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
