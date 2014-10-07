CREATE DATABASE  IF NOT EXISTS `project_management` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `project_management`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: project_management
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employeeID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(17) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `position` varchar(220) COLLATE utf8_unicode_ci NOT NULL,
  `dateOfEmployment` date NOT NULL,
  `phoneNumber` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `salary` double NOT NULL,
  `postal_code` int(5) NOT NULL,
  `birthDate` date NOT NULL,
  `streetName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `streetNum` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `works` tinyint(1) NOT NULL,
  PRIMARY KEY (`employeeID`),
  KEY `postal_code` (`postal_code`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`postal_code`) REFERENCES `town` (`postal_code`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Neko','Nasod','Female','asd','2014-08-17','111234',0,11000,'2014-08-17','ads','12',0),(2,'Milan','Deretić','Male','Menadžer I&R','2009-05-20','65483197',50000,34000,'1980-03-27','Banatska','11',0),(3,'Neda','Karanović','Female','Menadžer ljudskih resursa','2012-05-22','611234567',50000,11000,'1985-06-08','Pregrevica','50',0),(4,'Petar','Petrović','Male','Community menadžer','2008-02-02','11123456',30000,11000,'1990-01-29','Moše Pijade','42',0),(5,'Pera','Petrusić','Male','Menadžer kvaliteta','2000-05-05','113214567',44000,11000,'1970-11-10','Džona Kenedija','4d',0),(6,'Damir','Dodović','Male','Menadzer logistike','2009-05-05','18111111',50000,21000,'1980-11-10','Neka','55',1),(7,'Miodrag','Milutinović','Male','Programer','2012-07-20','113130288',40000,11080,'1987-05-17','Goce Delčeva','71',0),(8,'Mara','Marjanovic','Male','New position','2009-05-04','+381+38111123456',50000,11070,'1980-08-31','Bulevar Zorana Djindjica','28',1),(9,'Zikica','Zikic','Male','Some position','2014-11-11','+381638509202',50000,11080,'1990-11-08','Prvomajska','23',1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meeting`
--

DROP TABLE IF EXISTS `meeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meeting` (
  `meetingID` int(11) NOT NULL AUTO_INCREMENT,
  `meetingDate` date DEFAULT NULL,
  `meetingTitle` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `report` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `meetingPlace` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `projectID` int(11) NOT NULL,
  PRIMARY KEY (`meetingID`),
  KEY `projectID` (`projectID`),
  CONSTRAINT `meeting_ibfk_1` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meeting`
--

LOCK TABLES `meeting` WRITE;
/*!40000 ALTER TABLE `meeting` DISABLE KEYS */;
INSERT INTO `meeting` VALUES (1,'2014-08-20','ads','asd','das',2),(2,'2014-01-01','Clojure sastanak','Izvestaj o clojure sastanku mozes ovde videti','Clojure sala',3);
/*!40000 ALTER TABLE `meeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participant` (
  `participantID` int(5) NOT NULL AUTO_INCREMENT,
  `employeeID` int(11) NOT NULL,
  `projectID` int(11) NOT NULL,
  `duty` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`participantID`),
  KEY `employeeID` (`employeeID`,`projectID`),
  KEY `projectID` (`projectID`),
  CONSTRAINT `participant_ibfk_6` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`),
  CONSTRAINT `participant_ibfk_7` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant`
--

LOCK TABLES `participant` WRITE;
/*!40000 ALTER TABLE `participant` DISABLE KEYS */;
INSERT INTO `participant` VALUES (1,0,7,'Dodajem ti zaduzenje Maro'),(2,1,7,'Dodajem ti zaduzenje Jovicu'),(3,1,8,'Strategise'),(4,2,8,'Istrazuje i razvija'),(5,5,8,'Nosi kafu'),(6,6,8,'Transport robe'),(7,0,9,'Odnos sa firmama.'),(8,1,9,'Plan strat. realizacije projekta.'),(9,2,9,'Pronalaženje potencijalnih mušterija.'),(10,5,9,'Upravljanje i kontrola svim procesima.'),(11,7,9,'Kreiranje novog sajta.');
/*!40000 ALTER TABLE `participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `projectID` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `projectDesc` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `budget` decimal(19,4) NOT NULL,
  `userID` int(11) NOT NULL,
  PRIMARY KEY (`projectID`),
  KEY `userID` (`userID`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (2,'Projekat2','Opis2','2013-07-28','2013-09-13',0,0.0000,2),(3,'Projekat3','Ovo je neki kratak opis projekta.','2013-08-07','2013-10-07',1,0.0000,2),(4,'Projekat(promenjen)','Opis projekta tralalala','2013-05-05','2013-06-06',0,0.0000,2),(5,'adsasd','asdsdaasddsa','2013-01-01','2013-01-02',0,0.0000,2),(6,'Projekat52','Opis projekta','2013-05-05','2013-10-05',1,0.0000,2),(7,'Projekat Od Damjana','Damjan pravi projekat','2013-05-05','2013-12-05',1,0.0000,1),(8,'Projekat1','Opis projekta','2013-11-11','2013-11-12',0,0.0000,2),(9,'Uvoz i promocija proizvoda \"Electrolux\"','Na tržištu se pojavio novi frižider marke “Electrolux”, model “ZX 750” sa najnovijom tehnologijom “UPS” (Unlimited Power Supply), tehnologija koja omogu?ava da frižider neometano radi i nakon nestanka struje u trajanju od 24h.\nZa ovim frižiderom vlada veliko interesovanje, pa prodavnica “Centar bele tehnike” želi da ponudi potroša?ima za sebe najbolju cenu, kako bi pove?ala svoj udeo na tržištu bele tehnike.\nGlavni konkurent “Centra bele tehnike” je lanac prodavnica “Tehnomanija”, te je cilj “Centra bele tehnike” da postavi takvu cenu da ve?ina potroša?a kupi baš od njih pomenuti frižider, što bi dosta uticalo na tržišni udeo. Trenutno, “Centar bele tehnike” zauzima 40% tržišnog udela u prodaji frižidera, naspram 60% koliko drži “Tehnomanija”. S obzirom da je “Centar bele tehnike” mlada prodavnica, tj. da “Tehnomanija” uživa poverenje ve?eg broja potroša?a, treba imati u vidu da ljudi imaju ve?e poverenje u lanac prodavnica “Tehnomanija”, te ?eš?e kupuju u istom ukoliko su cena i uslovi prodaje sli?ni.\nKompanija “Electrolux” je prodava?ima je za model “ZX 750” navela cenu od 50.000rsd kao preporu?enu cenu.\nBudžet za realizaciju projekta iznosi 20.000e\n','2013-07-20','2013-08-29',1,0.0000,2),(10,'Nekakav projekat','Neko i nesto','2013-11-12','2013-11-18',1,0.0000,2),(11,'New project wow','Project description for something\r\nBla','2014-10-04','2014-10-31',1,50000.0000,2),(12,'Poslednji projekat','Opis poslednjeg projekta','2014-10-08','2014-10-31',1,100000.0000,2);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `town`
--

DROP TABLE IF EXISTS `town`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `town` (
  `postal_code` int(5) NOT NULL,
  `town_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`postal_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `town`
--

LOCK TABLES `town` WRITE;
/*!40000 ALTER TABLE `town` DISABLE KEYS */;
INSERT INTO `town` VALUES (11000,'Beograd'),(11070,'Novi Beograd'),(11080,'Zemun'),(18000,'Niš'),(21000,'Novi Sad'),(32000,'Čačak'),(34000,'Kragujevac');
/*!40000 ALTER TABLE `town` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `username` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(17) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(17) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,'Username','username','ime','ime','ime@ime.com'),(1,'damjan','damjan12','Damjan','Zamaklar','zdamjan90@gmail.com'),(2,'pera','pera12','Pera','Peric','pera.peric@perice.com'),(3,'noviKorisnik12','noviKorisnik12','Novi','korisnik12','asd@dsa.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-10-07 22:08:00
