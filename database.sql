-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinecourseweb
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `ACCOUNTID` int NOT NULL AUTO_INCREMENT,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ACCOUNTID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'123456',1),(2,'123456',1),(3,'123456',1),(4,'123456',1),(5,'ZAP',1),(6,'',1),(7,'123456',1),(8,'123456',1),(9,'123456',1),(10,'123456',1),(11,'123456',1),(12,'123456',1),(13,'',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chap`
--

DROP TABLE IF EXISTS `chap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chap` (
  `CHAPID` int NOT NULL,
  `CHAPORDER` int DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `COURSE_COURSEID` int DEFAULT NULL,
  PRIMARY KEY (`CHAPID`),
  KEY `FK_CHAP_COURSE_COURSEID` (`COURSE_COURSEID`),
  CONSTRAINT `FK_CHAP_COURSE_COURSEID` FOREIGN KEY (`COURSE_COURSEID`) REFERENCES `course` (`COURSEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chap`
--

LOCK TABLES `chap` WRITE;
/*!40000 ALTER TABLE `chap` DISABLE KEYS */;
INSERT INTO `chap` VALUES (1,1,'Chương 1: Giới thiệu                                                       ',1),(2,2,'Chương 2: Làm quen với cấu trúc cơ bản của ứng dụng web',1),(51,1,'Foundametion',2),(52,2,'ANN',2);
/*!40000 ALTER TABLE `chap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `COURSEID` int NOT NULL,
  `APPROVED` tinyint(1) DEFAULT '0',
  `MODIFIEDDATE` datetime DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `OBJECTIVE` varchar(255) DEFAULT NULL,
  `DOCUMENT` varchar(255) DEFAULT NULL,
  `USER_USERID` int DEFAULT NULL,
  PRIMARY KEY (`COURSEID`),
  KEY `FK_COURSE_USER_USERID` (`USER_USERID`),
  CONSTRAINT `FK_COURSE_USER_USERID` FOREIGN KEY (`USER_USERID`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,0,'2021-06-21 09:14:09','Lập trình web với java','Hoan thanh \r\n','',2),(2,0,'2021-06-14 15:06:09','Basic DL','hoàn thành kiến thức Deep Learning','',4);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `excercise`
--

DROP TABLE IF EXISTS `excercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `excercise` (
  `EXCERCISEID` int NOT NULL,
  `ANSWERA` varchar(255) DEFAULT NULL,
  `ANSWERB` varchar(255) DEFAULT NULL,
  `ANSWERC` varchar(255) DEFAULT NULL,
  `ANSWERD` varchar(255) DEFAULT NULL,
  `CORRECTANSWER` varchar(255) DEFAULT NULL,
  `EXCERCISEORDER` int DEFAULT NULL,
  `EXPLAINATION` varchar(255) DEFAULT NULL,
  `QUESTION` varchar(255) DEFAULT NULL,
  `CHAP_CHAPID` int DEFAULT NULL,
  `COURSE_COURSEID` int DEFAULT NULL,
  `PART_PARTID` int DEFAULT NULL,
  PRIMARY KEY (`EXCERCISEID`),
  KEY `FK_EXCERCISE_COURSE_COURSEID` (`COURSE_COURSEID`),
  KEY `FK_EXCERCISE_CHAP_CHAPID` (`CHAP_CHAPID`),
  KEY `FK_EXCERCISE_PART_PARTID` (`PART_PARTID`),
  CONSTRAINT `FK_EXCERCISE_CHAP_CHAPID` FOREIGN KEY (`CHAP_CHAPID`) REFERENCES `chap` (`CHAPID`),
  CONSTRAINT `FK_EXCERCISE_COURSE_COURSEID` FOREIGN KEY (`COURSE_COURSEID`) REFERENCES `course` (`COURSEID`),
  CONSTRAINT `FK_EXCERCISE_PART_PARTID` FOREIGN KEY (`PART_PARTID`) REFERENCES `part` (`PARTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `excercise`
--

LOCK TABLES `excercise` WRITE;
/*!40000 ALTER TABLE `excercise` DISABLE KEYS */;
INSERT INTO `excercise` VALUES (11,'A','B','C','D','A',1,'GT1','Câu 1',1,1,3);
/*!40000 ALTER TABLE `excercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq` (
  `FAQID` int NOT NULL,
  `ANSWER` varchar(255) DEFAULT NULL,
  `FAQORDER` int DEFAULT NULL,
  `QUESTION` varchar(255) DEFAULT NULL,
  `COURSE_COURSEID` int DEFAULT NULL,
  PRIMARY KEY (`FAQID`),
  KEY `FK_FAQ_COURSE_COURSEID` (`COURSE_COURSEID`),
  CONSTRAINT `FK_FAQ_COURSE_COURSEID` FOREIGN KEY (`COURSE_COURSEID`) REFERENCES `course` (`COURSEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (7,'Trả lời 1',1,'Q1',1),(8,'Trả lời 2',2,'Q2',1),(58,'A1',1,'Q1',2);
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructor` (
  `ID` int NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PATHOFIMAGE` varchar(255) DEFAULT NULL,
  `POSITION` varchar(255) DEFAULT NULL,
  `COURSE_COURSEID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_INSTRUCTOR_COURSE_COURSEID` (`COURSE_COURSEID`),
  CONSTRAINT `FK_INSTRUCTOR_COURSE_COURSEID` FOREIGN KEY (`COURSE_COURSEID`) REFERENCES `course` (`COURSEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES (9,'GV1','files/person1_2_21062021_091409.jpg','HCMUTE',1),(59,'GV2','files/hinh.png','GVC',2),(151,'GV2','files/person2_2_21062021_091409.jpg','HCMUTE',1),(152,'GV3','files/person3_2_21062021_091409.png','HCMUTE',1);
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part`
--

DROP TABLE IF EXISTS `part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part` (
  `PARTID` int NOT NULL,
  `DOCUMENT` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PARTORDER` int DEFAULT NULL,
  `VIDEO` varchar(255) DEFAULT NULL,
  `CHAP_CHAPID` int DEFAULT NULL,
  `COURSE_COURSEID` int DEFAULT NULL,
  PRIMARY KEY (`PARTID`),
  KEY `FK_PART_CHAP_CHAPID` (`CHAP_CHAPID`),
  KEY `FK_PART_COURSE_COURSEID` (`COURSE_COURSEID`),
  CONSTRAINT `FK_PART_CHAP_CHAPID` FOREIGN KEY (`CHAP_CHAPID`) REFERENCES `chap` (`CHAPID`),
  CONSTRAINT `FK_PART_COURSE_COURSEID` FOREIGN KEY (`COURSE_COURSEID`) REFERENCES `course` (`COURSEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part`
--

LOCK TABLES `part` WRITE;
/*!40000 ALTER TABLE `part` DISABLE KEYS */;
INSERT INTO `part` VALUES (3,'','Phần 1: Vai trò của Web',1,'BÃ¡o cÃ¡o cuá»‘i kÃ¬ - Tham Khao.docx',1,1),(4,'','Phần 2: Cài đặt để lập trình web',2,'',1,1),(5,'','Phần 1: Cấu trúc project',1,'',2,1),(6,'','Phần 2: Thiết kế giao diện cơ bản với html, css',2,'',2,1),(53,'','Introduction of DL',1,'',51,2),(54,'','Prepare Env',2,'',51,2),(55,'','Linear Algebra',3,'',51,2),(56,'','Basic ANN',1,'',52,2),(57,'','ANN operation',2,'',52,2);
/*!40000 ALTER TABLE `part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partfiles`
--

DROP TABLE IF EXISTS `partfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partfiles` (
  `ID` int NOT NULL,
  `FILEDOCUMENT` varchar(255) DEFAULT NULL,
  `PART_PARTID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PARTFILES_PART_PARTID` (`PART_PARTID`),
  CONSTRAINT `FK_PARTFILES_PART_PARTID` FOREIGN KEY (`PART_PARTID`) REFERENCES `part` (`PARTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partfiles`
--

LOCK TABLES `partfiles` WRITE;
/*!40000 ALTER TABLE `partfiles` DISABLE KEYS */;
INSERT INTO `partfiles` VALUES (60,'20210614_093959.mp4',3);
/*!40000 ALTER TABLE `partfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `REGISTRATIONID` int NOT NULL,
  `COURSE_COURSEID` int DEFAULT NULL,
  `USER_USERID` int DEFAULT NULL,
  PRIMARY KEY (`REGISTRATIONID`),
  KEY `FK_REGISTRATION_USER_USERID` (`USER_USERID`),
  KEY `FK_REGISTRATION_COURSE_COURSEID` (`COURSE_COURSEID`),
  CONSTRAINT `FK_REGISTRATION_COURSE_COURSEID` FOREIGN KEY (`COURSE_COURSEID`) REFERENCES `course` (`COURSEID`),
  CONSTRAINT `FK_REGISTRATION_USER_USERID` FOREIGN KEY (`USER_USERID`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (12,1,3);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `ROLEID` int NOT NULL AUTO_INCREMENT,
  `ROLENAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLEID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'teacher'),(3,'student');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',250);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentexcercise`
--

DROP TABLE IF EXISTS `studentexcercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentexcercise` (
  `ID` int NOT NULL,
  `CORRECT` tinyint(1) DEFAULT '0',
  `SELECTEDANSWER` varchar(255) DEFAULT NULL,
  `TIME` int DEFAULT NULL,
  `CHAP_CHAPID` int DEFAULT NULL,
  `COURSE_COURSEID` int DEFAULT NULL,
  `EXCERCISE_EXCERCISEID` int DEFAULT NULL,
  `PART_PARTID` int DEFAULT NULL,
  `USER_USERID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_STUDENTEXCERCISE_USER_USERID` (`USER_USERID`),
  KEY `FK_STUDENTEXCERCISE_EXCERCISE_EXCERCISEID` (`EXCERCISE_EXCERCISEID`),
  KEY `FK_STUDENTEXCERCISE_COURSE_COURSEID` (`COURSE_COURSEID`),
  KEY `FK_STUDENTEXCERCISE_CHAP_CHAPID` (`CHAP_CHAPID`),
  KEY `FK_STUDENTEXCERCISE_PART_PARTID` (`PART_PARTID`),
  CONSTRAINT `FK_STUDENTEXCERCISE_CHAP_CHAPID` FOREIGN KEY (`CHAP_CHAPID`) REFERENCES `chap` (`CHAPID`),
  CONSTRAINT `FK_STUDENTEXCERCISE_COURSE_COURSEID` FOREIGN KEY (`COURSE_COURSEID`) REFERENCES `course` (`COURSEID`),
  CONSTRAINT `FK_STUDENTEXCERCISE_EXCERCISE_EXCERCISEID` FOREIGN KEY (`EXCERCISE_EXCERCISEID`) REFERENCES `excercise` (`EXCERCISEID`),
  CONSTRAINT `FK_STUDENTEXCERCISE_PART_PARTID` FOREIGN KEY (`PART_PARTID`) REFERENCES `part` (`PARTID`),
  CONSTRAINT `FK_STUDENTEXCERCISE_USER_USERID` FOREIGN KEY (`USER_USERID`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentexcercise`
--

LOCK TABLES `studentexcercise` WRITE;
/*!40000 ALTER TABLE `studentexcercise` DISABLE KEYS */;
INSERT INTO `studentexcercise` VALUES (13,1,'A',1,1,1,11,3,3),(14,0,'D',2,1,1,11,3,3),(201,0,'B',3,1,1,11,3,3);
/*!40000 ALTER TABLE `studentexcercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `USERID` int NOT NULL AUTO_INCREMENT,
  `CREATEDDATE` datetime DEFAULT NULL,
  `DATEOFBIRTH` date DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `GENDER` tinyint(1) DEFAULT '0',
  `NAME` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `RoleId` int DEFAULT NULL,
  `AccountId` int DEFAULT NULL,
  PRIMARY KEY (`USERID`),
  KEY `FK_USER_AccountId` (`AccountId`),
  KEY `FK_USER_RoleId` (`RoleId`),
  CONSTRAINT `FK_USER_AccountId` FOREIGN KEY (`AccountId`) REFERENCES `account` (`ACCOUNTID`),
  CONSTRAINT `FK_USER_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `role` (`ROLEID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'1998-01-01','nguyenquoc18320@gmail.com',0,'Admin2','045123',1,1),(2,NULL,NULL,'teacher1@gmail.com',0,'Teacher',NULL,2,2),(3,NULL,NULL,'student1@gmail.com',0,'Student',NULL,3,3),(4,'2021-06-14 15:03:22','1996-01-01','teacher2@gmail.com',1,'GV2','0123456789',2,4);
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

-- Dump completed on 2021-06-21 10:27:20
