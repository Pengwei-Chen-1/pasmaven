-- MySQL dump 10.13  Distrib 5.5.35, for Win64 (x86)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.5.35

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
-- Table structure for table `pas_account`
--

DROP TABLE IF EXISTS `pas_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_account` (
  `ACCOUNT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `ACCOUNT_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_ID`),
  KEY `FK_ACCOUNT_USER_idx` (`USER_ID`),
  CONSTRAINT `FK_ACCOUNT_USER` FOREIGN KEY (`USER_ID`) REFERENCES `pas_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_account`
--

LOCK TABLES `pas_account` WRITE;
/*!40000 ALTER TABLE `pas_account` DISABLE KEYS */;
INSERT INTO `pas_account` VALUES (1,0,'现金账户'),(2,0,'银行卡'),(3,0,'支付宝'),(4,0,'信用卡'),(9,0,'其他'),(10,15,'现金账户'),(11,15,'银行卡'),(12,15,'支付宝'),(13,15,'信用卡'),(14,15,'其他'),(15,16,'现金账户'),(16,16,'银行卡'),(17,16,'支付宝'),(18,16,'信用卡'),(19,16,'其他');
/*!40000 ALTER TABLE `pas_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pas_business`
--

DROP TABLE IF EXISTS `pas_business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_business` (
  `BUSINESS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `BUSINESS_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`BUSINESS_ID`),
  KEY `FK_BUSINESS_USER_idx` (`USER_ID`),
  CONSTRAINT `FK_BUSINESS_USER` FOREIGN KEY (`USER_ID`) REFERENCES `pas_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_business`
--

LOCK TABLES `pas_business` WRITE;
/*!40000 ALTER TABLE `pas_business` DISABLE KEYS */;
INSERT INTO `pas_business` VALUES (1,0,'餐厅'),(2,0,'超市'),(3,0,'银行'),(4,0,'其他'),(6,15,'餐厅'),(7,15,'超市'),(8,15,'银行'),(9,15,'其他'),(10,16,'餐厅'),(11,16,'超市'),(12,16,'银行'),(13,16,'其他');
/*!40000 ALTER TABLE `pas_business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pas_family`
--

DROP TABLE IF EXISTS `pas_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_family` (
  `MEMBER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `MEMBER_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MEMBER_ID`),
  KEY `FK_FAMILY_USER_idx` (`USER_ID`),
  CONSTRAINT `FK_FAMILY_USER` FOREIGN KEY (`USER_ID`) REFERENCES `pas_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_family`
--

LOCK TABLES `pas_family` WRITE;
/*!40000 ALTER TABLE `pas_family` DISABLE KEYS */;
INSERT INTO `pas_family` VALUES (1,0,'本人'),(2,0,'配偶'),(3,0,'子女'),(4,0,'父母'),(6,0,'家庭公用'),(7,15,'本人'),(8,15,'配偶'),(9,15,'子女'),(10,15,'父母'),(11,15,'家庭公用'),(12,16,'本人'),(13,16,'配偶'),(14,16,'子女'),(15,16,'父母'),(16,16,'家庭公用');
/*!40000 ALTER TABLE `pas_family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pas_firstlevel`
--

DROP TABLE IF EXISTS `pas_firstlevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_firstlevel` (
  `FIRSTLEVEL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `FIRSTLEVEL_NAME` varchar(20) DEFAULT NULL,
  `IMAGE` varchar(50) DEFAULT NULL,
  `IOTYPE` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`FIRSTLEVEL_ID`),
  KEY `FK_FIRSTLEVEL_USER_idx` (`USER_ID`),
  CONSTRAINT `FK_FIRSTLEVEL_USER` FOREIGN KEY (`USER_ID`) REFERENCES `pas_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_firstlevel`
--

LOCK TABLES `pas_firstlevel` WRITE;
/*!40000 ALTER TABLE `pas_firstlevel` DISABLE KEYS */;
INSERT INTO `pas_firstlevel` VALUES (1,0,'食品酒水','spjs.png','O'),(2,0,'职业收入','zysr.png','I'),(4,0,'衣服饰品','','O'),(5,0,'行车交通','','O'),(6,0,'居家物业','','O'),(7,0,'休闲娱乐','','O'),(8,0,'其他杂项','','O'),(9,0,'其他收入','','I'),(11,14,'食品酒水','','O'),(12,15,'食品酒水','','O'),(13,15,'衣服饰品','','O'),(14,15,'行车交通','','O'),(15,15,'居家物业','','O'),(16,15,'其他杂项','','O'),(17,15,'职业收入','','I'),(18,15,'其他收入','','I'),(19,16,'食品酒水','','O'),(20,16,'衣服饰品','','O'),(21,16,'行车交通','','O'),(22,16,'居家物业','','O'),(23,16,'其他杂项','','O'),(24,16,'职业收入','','I'),(25,16,'其他收入','','I');
/*!40000 ALTER TABLE `pas_firstlevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pas_income`
--

DROP TABLE IF EXISTS `pas_income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_income` (
  `INCOME_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `IMAGE` varchar(50) DEFAULT NULL,
  `FIRSTLEVEL_ID` int(11) DEFAULT NULL,
  `SECONDLEVEL_ID` int(11) DEFAULT NULL,
  `ACCOUNT_ID` int(11) DEFAULT NULL,
  `MONEY` decimal(10,2) DEFAULT NULL,
  `PROJECT_ID` int(11) DEFAULT NULL,
  `MEMBER_ID` int(11) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `REMARK` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`INCOME_ID`),
  KEY `FK_INCOME_USER_idx` (`USER_ID`),
  KEY `FK_INCOME_FIRSTLEVEL_idx` (`FIRSTLEVEL_ID`),
  KEY `FK_INCOME_SECONDLEVEL_idx` (`SECONDLEVEL_ID`),
  KEY `FK_INCOME_ACCOUNT_idx` (`ACCOUNT_ID`),
  KEY `FK_INCOME_PROJECT_idx` (`PROJECT_ID`),
  KEY `FK_INCOME_FAMILY_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_INCOME_ACCOUNT` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `pas_account` (`ACCOUNT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_INCOME_FAMILY` FOREIGN KEY (`MEMBER_ID`) REFERENCES `pas_family` (`MEMBER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_INCOME_FIRSTLEVEL` FOREIGN KEY (`FIRSTLEVEL_ID`) REFERENCES `pas_firstlevel` (`FIRSTLEVEL_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_INCOME_PROJECT` FOREIGN KEY (`PROJECT_ID`) REFERENCES `pas_project` (`PROJECT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_INCOME_SECONDLEVEL` FOREIGN KEY (`SECONDLEVEL_ID`) REFERENCES `pas_secondlevel` (`SECONDLEVEL_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_INCOME_USER` FOREIGN KEY (`USER_ID`) REFERENCES `pas_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_income`
--

LOCK TABLES `pas_income` WRITE;
/*!40000 ALTER TABLE `pas_income` DISABLE KEYS */;
INSERT INTO `pas_income` VALUES (1,0,'',2,2,2,1000.00,5,1,'2014-02-16 07:59:59','工资收入'),(2,0,'',2,23,2,300.00,5,2,'2014-04-14 23:04:00','餐补哦！！！！'),(3,0,'',9,29,1,1000.00,4,3,'2014-04-08 23:10:00','红包'),(4,0,'',9,31,1,20.00,1,1,'2014-04-22 16:48:00','捡了20元，呵呵'),(5,15,'',17,76,10,2000.00,7,7,'2014-04-09 12:21:00','工资'),(6,0,'',2,23,1,300.00,5,1,'2014-05-03 16:19:00','餐补'),(7,0,'',4,6,3,200.00,1,2,'2014-05-03 16:19:00','衣服');
/*!40000 ALTER TABLE `pas_income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pas_log`
--

DROP TABLE IF EXISTS `pas_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_log` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `OPERATE_TYPE` varchar(5) DEFAULT NULL,
  `OPERATE_DES` varchar(100) DEFAULT NULL,
  `OPERATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`),
  KEY `FK_USER_LOG_idx` (`USER_ID`),
  CONSTRAINT `FK_USER_LOG` FOREIGN KEY (`USER_ID`) REFERENCES `pas_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_log`
--

LOCK TABLES `pas_log` WRITE;
/*!40000 ALTER TABLE `pas_log` DISABLE KEYS */;
INSERT INTO `pas_log` VALUES (1,0,'O','测试日志','2014-04-22 15:23:09'),(3,0,'A','添加支出信息','2014-04-22 16:48:50'),(4,0,'A','添加收入信息','2014-04-22 16:49:49'),(5,0,'D','删除二级分类','2014-04-22 16:52:12'),(6,0,'A','添加[沃尔玛]商家信息','2014-04-22 16:52:37'),(7,0,'D','删除[沃尔玛]商家','2014-04-22 16:52:44'),(8,0,'A','添加[五一出游]项目分类','2014-04-22 16:53:13'),(9,0,'D','删除[五一出游]项目','2014-04-22 16:53:17'),(10,0,'U','修改用户信息','2014-04-22 16:53:56'),(11,0,'U','修改用户信息','2014-04-22 16:54:06'),(12,0,'I','登录系统','2014-04-22 17:29:26'),(13,0,'I','登录系统','2014-04-22 17:59:14'),(14,0,'I','登录系统','2014-04-22 18:20:14'),(15,0,'I','登录系统','2014-04-22 18:46:16'),(16,0,'I','登录系统','2014-04-22 19:27:27'),(17,0,'I','登录系统','2014-04-22 19:31:43'),(18,0,'I','登录系统','2014-04-22 20:19:47'),(19,0,'U','修改用户信息','2014-04-22 20:20:21'),(20,0,'I','登录系统','2014-04-22 22:11:49'),(21,0,'U','修改用户信息','2014-04-22 22:14:44'),(22,0,'U','修改用户信息','2014-04-22 22:16:51'),(23,0,'I','登录系统','2014-04-22 23:16:09'),(24,0,'I','登录系统','2014-04-22 23:19:28'),(25,0,'I','登录系统','2014-04-22 23:22:08'),(26,0,'U','修改用户密码','2014-04-22 23:22:47'),(27,0,'I','登录系统','2014-04-23 00:03:05'),(28,0,'I','登录系统','2014-04-23 08:08:02'),(29,0,'I','登录系统','2014-04-23 08:27:21'),(30,0,'O','退出系统','2014-04-23 08:27:45'),(31,0,'I','登录系统','2014-04-23 08:28:29'),(32,0,'I','登录系统','2014-04-23 08:29:44'),(33,0,'U','修改用户信息','2014-04-23 08:34:09'),(34,0,'I','登录系统','2014-04-23 08:48:56'),(35,0,'I','登录系统','2014-04-23 09:29:04'),(36,0,'I','登录系统','2014-04-23 09:32:52'),(37,0,'I','登录系统','2014-04-23 09:37:40'),(38,0,'I','登录系统','2014-04-23 09:41:28'),(39,0,'U','修改用户信息','2014-04-23 09:43:28'),(40,0,'U','修改用户信息','2014-04-23 09:43:34'),(41,0,'I','登录系统','2014-04-23 09:49:21'),(42,0,'U','修改用户信息','2014-04-23 09:49:29'),(43,0,'I','登录系统','2014-04-23 09:51:20'),(44,0,'U','修改用户信息','2014-04-23 09:51:26'),(45,0,'U','修改用户信息','2014-04-23 09:51:36'),(46,0,'O','退出系统','2014-04-23 09:52:58'),(47,0,'I','登录系统','2014-04-23 10:51:25'),(48,12,'I','登录系统','2014-04-23 11:08:21'),(49,12,'U','修改用户信息','2014-04-23 11:09:26'),(50,12,'O','退出系统','2014-04-23 11:10:39'),(51,0,'A','添加[食品酒水]一级分类','2014-04-23 11:13:56'),(52,0,'A','添加二级分类','2014-04-23 11:13:56'),(53,0,'A','添加二级分类','2014-04-23 11:13:56'),(54,0,'A','添加二级分类','2014-04-23 11:13:56'),(55,0,'A','添加二级分类','2014-04-23 11:13:56'),(56,0,'A','添加二级分类','2014-04-23 11:13:56'),(57,0,'A','添加二级分类','2014-04-23 11:13:56'),(58,0,'A','添加二级分类','2014-04-23 11:13:56'),(59,0,'A','添加二级分类','2014-04-23 11:13:56'),(60,0,'A','添加二级分类','2014-04-23 11:13:56'),(61,0,'A','添加二级分类','2014-04-23 11:13:56'),(62,0,'A','添加二级分类','2014-04-23 11:13:57'),(63,0,'A','添加二级分类','2014-04-23 11:13:57'),(64,0,'A','添加二级分类','2014-04-23 11:13:57'),(65,0,'A','添加二级分类','2014-04-23 11:13:57'),(66,0,'A','添加二级分类','2014-04-23 11:13:57'),(67,0,'A','添加二级分类','2014-04-23 11:13:57'),(68,0,'A','添加二级分类','2014-04-23 11:13:57'),(69,0,'A','添加二级分类','2014-04-23 11:13:57'),(70,0,'A','添加二级分类','2014-04-23 11:13:57'),(71,0,'A','添加二级分类','2014-04-23 11:13:57'),(72,0,'A','添加二级分类','2014-04-23 11:13:57'),(73,0,'A','添加二级分类','2014-04-23 11:13:57'),(74,0,'A','添加二级分类','2014-04-23 11:13:57'),(75,0,'A','添加二级分类','2014-04-23 11:13:57'),(76,0,'A','添加二级分类','2014-04-23 11:13:57'),(77,0,'I','登录系统','2014-04-23 11:19:56'),(78,0,'I','登录系统','2014-04-23 11:21:13'),(79,0,'I','登录系统','2014-04-23 11:21:47'),(80,0,'O','退出系统','2014-04-23 11:22:13'),(81,14,'A','添加[食品酒水]一级分类','2014-04-23 11:23:15'),(82,14,'A','添加二级分类','2014-04-23 11:23:35'),(83,14,'A','添加二级分类','2014-04-23 11:23:36'),(84,14,'A','添加二级分类','2014-04-23 11:23:39'),(85,14,'I','登录系统','2014-04-23 11:23:53'),(86,14,'I','登录系统','2014-04-23 12:05:02'),(87,0,'I','登录系统','2014-04-23 12:13:24'),(88,0,'O','退出系统','2014-04-23 12:14:06'),(89,15,'A','添加[现金账户]账户信息','2014-04-23 12:15:30'),(90,15,'A','添加[银行卡]账户信息','2014-04-23 12:15:30'),(91,15,'A','添加[支付宝]账户信息','2014-04-23 12:15:30'),(92,15,'A','添加[信用卡]账户信息','2014-04-23 12:15:30'),(93,15,'A','添加[其他]账户信息','2014-04-23 12:15:30'),(94,15,'A','添加[餐厅]商家信息','2014-04-23 12:15:30'),(95,15,'A','添加[超市]商家信息','2014-04-23 12:15:30'),(96,15,'A','添加[银行]商家信息','2014-04-23 12:15:30'),(97,15,'A','添加[其他]商家信息','2014-04-23 12:15:30'),(98,15,'A','添加[本人]成员信息','2014-04-23 12:15:30'),(99,15,'A','添加[配偶]成员信息','2014-04-23 12:15:30'),(100,15,'A','添加[子女]成员信息','2014-04-23 12:15:30'),(101,15,'A','添加[父母]成员信息','2014-04-23 12:15:30'),(102,15,'A','添加[家庭公用]成员信息','2014-04-23 12:15:31'),(103,15,'A','添加[日常花销]项目分类','2014-04-23 12:15:31'),(104,15,'A','添加[出差]项目分类','2014-04-23 12:15:31'),(105,15,'A','添加[旅游]项目分类','2014-04-23 12:15:31'),(106,15,'A','添加[过年]项目分类','2014-04-23 12:15:31'),(107,15,'A','添加[其他]项目分类','2014-04-23 12:15:31'),(108,15,'A','添加[食品酒水]一级分类','2014-04-23 12:15:31'),(109,15,'A','添加[衣服饰品]一级分类','2014-04-23 12:15:31'),(110,15,'A','添加[行车交通]一级分类','2014-04-23 12:15:31'),(111,15,'A','添加[居家物业]一级分类','2014-04-23 12:15:31'),(112,15,'A','添加[其他杂项]一级分类','2014-04-23 12:15:31'),(113,15,'A','添加[职业收入]一级分类','2014-04-23 12:15:31'),(114,15,'A','添加[其他收入]一级分类','2014-04-23 12:15:31'),(115,15,'A','添加二级分类','2014-04-23 12:15:31'),(116,15,'A','添加二级分类','2014-04-23 12:15:31'),(117,15,'A','添加二级分类','2014-04-23 12:15:31'),(118,15,'A','添加二级分类','2014-04-23 12:15:31'),(119,15,'A','添加二级分类','2014-04-23 12:15:31'),(120,15,'A','添加二级分类','2014-04-23 12:15:31'),(121,15,'A','添加二级分类','2014-04-23 12:15:31'),(122,15,'A','添加二级分类','2014-04-23 12:15:31'),(123,15,'A','添加二级分类','2014-04-23 12:15:32'),(124,15,'A','添加二级分类','2014-04-23 12:15:32'),(125,15,'A','添加二级分类','2014-04-23 12:15:32'),(126,15,'A','添加二级分类','2014-04-23 12:15:32'),(127,15,'A','添加二级分类','2014-04-23 12:15:32'),(128,15,'A','添加二级分类','2014-04-23 12:15:32'),(129,15,'A','添加二级分类','2014-04-23 12:15:32'),(130,15,'A','添加二级分类','2014-04-23 12:15:32'),(131,15,'A','添加二级分类','2014-04-23 12:15:32'),(132,15,'A','添加二级分类','2014-04-23 12:15:32'),(133,15,'A','添加二级分类','2014-04-23 12:15:32'),(134,15,'A','添加二级分类','2014-04-23 12:15:32'),(135,15,'A','添加二级分类','2014-04-23 12:15:32'),(136,15,'A','添加二级分类','2014-04-23 12:15:32'),(137,15,'I','登录系统','2014-04-23 12:15:43'),(138,15,'A','添加[午饭模板]模板','2014-04-23 12:20:25'),(139,15,'A','添加支出信息','2014-04-23 12:20:40'),(140,15,'A','添加[工资]模板','2014-04-23 12:21:11'),(141,15,'A','添加收入信息','2014-04-23 12:21:25'),(142,15,'U','修改收入信息','2014-04-23 12:21:37'),(143,15,'U','修改用户信息','2014-04-23 12:22:43'),(144,15,'I','登录系统','2014-04-23 12:26:10'),(145,0,'I','登录系统','2014-04-24 17:16:24'),(146,0,'I','登录系统','2014-04-24 17:28:50'),(147,0,'O','退出系统','2014-04-24 17:29:11'),(148,0,'I','登录系统','2014-04-25 16:03:00'),(149,0,'O','退出系统','2014-04-25 16:04:06'),(150,0,'I','登录系统','2014-04-25 17:19:47'),(151,0,'I','登录系统','2014-04-25 19:59:58'),(152,0,'U','修改收入信息','2014-04-25 20:00:21'),(153,0,'I','登录系统','2014-04-25 20:07:00'),(154,0,'I','登录系统','2014-04-25 20:08:38'),(155,0,'I','登录系统','2014-04-25 21:17:45'),(156,0,'I','登录系统','2014-04-25 22:19:49'),(157,0,'I','登录系统','2014-04-25 22:51:15'),(158,0,'I','登录系统','2014-04-27 09:49:02'),(159,0,'O','退出系统','2014-04-27 09:50:36'),(160,0,'I','登录系统','2014-04-27 14:21:47'),(161,0,'A','添加支出信息','2014-04-27 14:26:44'),(162,0,'I','登录系统','2014-04-29 16:00:42'),(163,0,'I','登录系统','2014-05-03 16:18:00'),(164,0,'A','添加支出信息','2014-05-03 16:18:57'),(165,0,'A','添加支出信息','2014-05-03 16:19:09'),(166,0,'A','添加支出信息','2014-05-03 16:19:26'),(167,0,'A','添加收入信息','2014-05-03 16:19:55'),(168,0,'A','添加收入信息','2014-05-03 16:20:53'),(169,16,'A','添加[现金账户]账户信息','2014-05-03 16:26:21'),(170,16,'A','添加[银行卡]账户信息','2014-05-03 16:26:21'),(171,16,'A','添加[支付宝]账户信息','2014-05-03 16:26:21'),(172,16,'A','添加[信用卡]账户信息','2014-05-03 16:26:21'),(173,16,'A','添加[其他]账户信息','2014-05-03 16:26:21'),(174,16,'A','添加[餐厅]商家信息','2014-05-03 16:26:22'),(175,16,'A','添加[超市]商家信息','2014-05-03 16:26:22'),(176,16,'A','添加[银行]商家信息','2014-05-03 16:26:22'),(177,16,'A','添加[其他]商家信息','2014-05-03 16:26:22'),(178,16,'A','添加[本人]成员信息','2014-05-03 16:26:22'),(179,16,'A','添加[配偶]成员信息','2014-05-03 16:26:22'),(180,16,'A','添加[子女]成员信息','2014-05-03 16:26:22'),(181,16,'A','添加[父母]成员信息','2014-05-03 16:26:22'),(182,16,'A','添加[家庭公用]成员信息','2014-05-03 16:26:22'),(183,16,'A','添加[日常花销]项目分类','2014-05-03 16:26:23'),(184,16,'A','添加[出差]项目分类','2014-05-03 16:26:23'),(185,16,'A','添加[旅游]项目分类','2014-05-03 16:26:23'),(186,16,'A','添加[过年]项目分类','2014-05-03 16:26:23'),(187,16,'A','添加[其他]项目分类','2014-05-03 16:26:23'),(188,16,'A','添加[食品酒水]一级分类','2014-05-03 16:26:23'),(189,16,'A','添加[衣服饰品]一级分类','2014-05-03 16:26:23'),(190,16,'A','添加[行车交通]一级分类','2014-05-03 16:26:24'),(191,16,'A','添加[居家物业]一级分类','2014-05-03 16:26:24'),(192,16,'A','添加[其他杂项]一级分类','2014-05-03 16:26:24'),(193,16,'A','添加[职业收入]一级分类','2014-05-03 16:26:24'),(194,16,'A','添加[其他收入]一级分类','2014-05-03 16:26:24'),(195,16,'A','添加二级分类','2014-05-03 16:26:24'),(196,16,'A','添加二级分类','2014-05-03 16:26:24'),(197,16,'A','添加二级分类','2014-05-03 16:26:24'),(198,16,'A','添加二级分类','2014-05-03 16:26:25'),(199,16,'A','添加二级分类','2014-05-03 16:26:25'),(200,16,'A','添加二级分类','2014-05-03 16:26:25'),(201,16,'A','添加二级分类','2014-05-03 16:26:25'),(202,16,'A','添加二级分类','2014-05-03 16:26:25'),(203,16,'A','添加二级分类','2014-05-03 16:26:25'),(204,16,'A','添加二级分类','2014-05-03 16:26:25'),(205,16,'A','添加二级分类','2014-05-03 16:26:25'),(206,16,'A','添加二级分类','2014-05-03 16:26:26'),(207,16,'A','添加二级分类','2014-05-03 16:26:26'),(208,16,'A','添加二级分类','2014-05-03 16:26:26'),(209,16,'A','添加二级分类','2014-05-03 16:26:26'),(210,16,'A','添加二级分类','2014-05-03 16:26:26'),(211,16,'A','添加二级分类','2014-05-03 16:26:26'),(212,16,'A','添加二级分类','2014-05-03 16:26:26'),(213,16,'A','添加二级分类','2014-05-03 16:26:27'),(214,16,'A','添加二级分类','2014-05-03 16:26:27'),(215,16,'A','添加二级分类','2014-05-03 16:26:27'),(216,16,'A','添加二级分类','2014-05-03 16:26:27'),(217,16,'I','登录系统','2014-05-03 16:26:41'),(218,16,'A','添加支出信息','2014-05-03 16:28:14'),(219,16,'U','修改支出信息','2014-05-03 16:28:37'),(220,16,'A','添加支出信息','2014-05-03 16:28:56'),(221,16,'A','添加支出信息','2014-05-03 16:29:02'),(222,16,'A','添加支出信息','2014-05-03 16:29:10'),(223,16,'A','添加支出信息','2014-05-03 16:29:21'),(224,16,'A','添加支出信息','2014-05-03 16:29:25'),(225,0,'I','登录系统','2014-05-03 16:30:35'),(226,0,'I','登录系统','2014-05-03 17:58:03'),(227,0,'I','登录系统','2014-05-03 18:19:10'),(228,0,'U','修改[话费模板]模板','2014-05-03 18:20:02'),(229,0,'O','退出系统','2014-05-03 18:20:32');
/*!40000 ALTER TABLE `pas_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pas_project`
--

DROP TABLE IF EXISTS `pas_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_project` (
  `PROJECT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `PROJECT_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`PROJECT_ID`),
  KEY `FK_PROJECT_USER_idx` (`USER_ID`),
  CONSTRAINT `FK_PROJECT_USER` FOREIGN KEY (`USER_ID`) REFERENCES `pas_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_project`
--

LOCK TABLES `pas_project` WRITE;
/*!40000 ALTER TABLE `pas_project` DISABLE KEYS */;
INSERT INTO `pas_project` VALUES (1,0,'日常花销'),(2,0,'出差'),(3,0,'旅游'),(4,0,'过年'),(5,0,'其他'),(7,15,'日常花销'),(8,15,'出差'),(9,15,'旅游'),(10,15,'过年'),(11,15,'其他'),(12,16,'日常花销'),(13,16,'出差'),(14,16,'旅游'),(15,16,'过年'),(16,16,'其他');
/*!40000 ALTER TABLE `pas_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pas_secondlevel`
--

DROP TABLE IF EXISTS `pas_secondlevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_secondlevel` (
  `SECONDLEVEL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `SECONDLEVEL_NAME` varchar(20) DEFAULT NULL,
  `IMAGE` varchar(50) DEFAULT NULL,
  `IOTYPE` varchar(5) DEFAULT NULL,
  `FIRSTLEVEL_ID` int(11) NOT NULL,
  PRIMARY KEY (`SECONDLEVEL_ID`),
  KEY `FK_SECONDLEVEL_USER_idx` (`USER_ID`),
  KEY `FK_SECONDLEVEL_FIRSTLEVEL_idx` (`FIRSTLEVEL_ID`),
  CONSTRAINT `FK_SECONDLEVEL_FIRSTLEVEL` FOREIGN KEY (`FIRSTLEVEL_ID`) REFERENCES `pas_firstlevel` (`FIRSTLEVEL_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SECONDLEVEL_USER` FOREIGN KEY (`USER_ID`) REFERENCES `pas_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_secondlevel`
--

LOCK TABLES `pas_secondlevel` WRITE;
/*!40000 ALTER TABLE `pas_secondlevel` DISABLE KEYS */;
INSERT INTO `pas_secondlevel` VALUES (1,0,'早午晚餐','breakfast.png','O',1),(2,0,'工资收入','gzsr.png','I',2),(3,0,'水果零食','','O',1),(4,0,'烟酒茶','','O',1),(5,0,'衣服裤子','','O',4),(6,0,'鞋帽包包','','O',4),(7,0,'化妆饰品','','O',4),(8,0,'公共交通','','O',5),(9,0,'打车租车','','O',5),(10,0,'私家车费用','','O',5),(11,0,'日常用品','','O',6),(12,0,'水电煤气','','O',6),(13,0,'物业管理','','O',6),(14,0,'维修保养','','O',6),(15,0,'运动健身','','O',7),(16,0,'腐败聚会','','O',7),(17,0,'休闲玩乐','','O',7),(18,0,'宠物宝贝','','O',7),(19,0,'旅游度假','','O',7),(20,0,'其他支出','','O',8),(21,0,'意外丢失','','O',8),(22,0,'烂账损失','','O',8),(23,0,'补助','','I',2),(24,0,'利息收入','','I',2),(25,0,'加班收入','','I',2),(26,0,'奖金收入','','I',2),(28,0,'兼职收入','','I',2),(29,0,'礼金收入','','I',9),(30,0,'中奖收入','','I',9),(31,0,'意外来钱','','I',9),(32,0,'经营所得','','I',9),(58,14,'早午晚餐','','O',11),(59,14,'水果零食','','O',11),(60,14,'烟酒茶','','O',11),(61,15,'早午晚餐','','O',12),(62,15,'水果零食','','O',12),(63,15,'烟酒茶','','O',12),(64,15,'衣服裤子','','O',13),(65,15,'鞋帽包包','','O',13),(66,15,'化妆饰品','','O',13),(67,15,'公共交通','','O',14),(68,15,'打车租车','','O',14),(69,15,'私家车费用','','O',14),(70,15,'日常用品','','O',15),(71,15,'水电煤气','','O',15),(72,15,'物业管理','','O',15),(73,15,'其他支出','','O',16),(74,15,'意外丢失','','O',16),(75,15,'烂账损失','','O',16),(76,15,'工资收入','','I',17),(77,15,'补助','','I',17),(78,15,'加班收入','','I',17),(79,15,'兼职收入','','I',17),(80,15,'礼金收入','','I',18),(81,15,'中奖收入','','I',18),(82,15,'意外来钱','','I',18),(83,16,'早午晚餐','','O',19),(84,16,'水果零食','','O',19),(85,16,'烟酒茶','','O',19),(86,16,'衣服裤子','','O',20),(87,16,'鞋帽包包','','O',20),(88,16,'化妆饰品','','O',20),(89,16,'公共交通','','O',21),(90,16,'打车租车','','O',21),(91,16,'私家车费用','','O',21),(92,16,'日常用品','','O',22),(93,16,'水电煤气','','O',22),(94,16,'物业管理','','O',22),(95,16,'其他支出','','O',23),(96,16,'意外丢失','','O',23),(97,16,'烂账损失','','O',23),(98,16,'工资收入','','I',24),(99,16,'补助','','I',24),(100,16,'加班收入','','I',24),(101,16,'兼职收入','','I',24),(102,16,'礼金收入','','I',25),(103,16,'中奖收入','','I',25),(104,16,'意外来钱','','I',25);
/*!40000 ALTER TABLE `pas_secondlevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pas_spending`
--

DROP TABLE IF EXISTS `pas_spending`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_spending` (
  `SPENDING_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `IMAGE` varchar(50) DEFAULT NULL,
  `FIRSTLEVEL_ID` int(11) DEFAULT NULL,
  `SECONDLEVEL_ID` int(11) DEFAULT NULL,
  `ACCOUNT_ID` int(11) DEFAULT NULL,
  `MONEY` decimal(10,2) DEFAULT NULL,
  `PROJECT_ID` int(11) DEFAULT NULL,
  `MEMBER_ID` int(11) DEFAULT NULL,
  `BUSINESS_ID` int(11) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `REMARK` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SPENDING_ID`),
  KEY `FK_SPENDING_USER_idx` (`USER_ID`),
  KEY `FK_SPENDING_FIRSTLEVEL_idx` (`FIRSTLEVEL_ID`),
  KEY `FK_SPENDING_SECONDLEVEL_idx` (`SECONDLEVEL_ID`),
  KEY `FK_SPENDING_ACCOUNT_idx` (`ACCOUNT_ID`),
  KEY `FK_SPENDING_PROJECT_idx` (`PROJECT_ID`),
  KEY `FK_SPENDING_FAMILY_idx` (`MEMBER_ID`),
  KEY `FK_SPENDING_BUSINESS_idx` (`BUSINESS_ID`),
  CONSTRAINT `FK_SPENDING_ACCOUNT` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `pas_account` (`ACCOUNT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SPENDING_BUSINESS` FOREIGN KEY (`BUSINESS_ID`) REFERENCES `pas_business` (`BUSINESS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SPENDING_FAMILY` FOREIGN KEY (`MEMBER_ID`) REFERENCES `pas_family` (`MEMBER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SPENDING_FIRSTLEVEL` FOREIGN KEY (`FIRSTLEVEL_ID`) REFERENCES `pas_firstlevel` (`FIRSTLEVEL_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SPENDING_PROJECT` FOREIGN KEY (`PROJECT_ID`) REFERENCES `pas_project` (`PROJECT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SPENDING_SECONDLEVEL` FOREIGN KEY (`SECONDLEVEL_ID`) REFERENCES `pas_secondlevel` (`SECONDLEVEL_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SPENDING_USER` FOREIGN KEY (`USER_ID`) REFERENCES `pas_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_spending`
--

LOCK TABLES `pas_spending` WRITE;
/*!40000 ALTER TABLE `pas_spending` DISABLE KEYS */;
INSERT INTO `pas_spending` VALUES (1,0,'',1,1,1,3.00,1,2,1,'2013-12-16 07:59:59','早饭'),(2,0,'',1,3,1,11.00,1,2,2,'2014-04-06 16:47:00','啤酒炸鸡'),(3,0,'',1,3,1,20.00,1,1,2,'2014-04-12 14:38:00','水果'),(4,0,'',4,5,1,200.00,1,3,2,'2014-04-01 14:38:00','阿迪达斯上衣'),(5,0,'',5,8,2,100.00,1,1,4,'2014-03-18 14:38:00','公交卡充值'),(6,0,'',6,12,2,500.00,1,1,2,'2014-03-18 14:38:00','房租'),(7,0,'',7,16,2,360.00,1,1,1,'2014-03-18 14:38:00','同学聚会'),(8,0,'',8,21,1,100.00,5,6,4,'2014-02-06 14:38:00','丢了100'),(9,0,'',1,3,1,50.00,1,1,2,'2014-04-01 13:09:00',''),(10,0,'',6,11,1,50.00,1,1,2,'2014-04-22 16:47:00','被褥'),(11,15,'',12,61,10,10.00,7,7,6,'2014-04-23 12:20:00','午饭'),(12,0,'',7,19,1,300.00,3,6,4,'2014-04-27 14:24:00','五一旅游'),(13,0,'',1,1,1,3.00,1,1,1,'2014-05-01 16:18:00','早饭'),(14,0,'',1,1,1,3.00,1,1,1,'2014-05-02 16:18:00','早饭'),(15,0,'',6,11,3,50.00,1,1,4,'2014-05-03 16:19:00','话费模板'),(16,16,'',23,96,17,123445.00,12,14,11,'2014-05-03 16:27:00','填写备注'),(17,16,'',19,83,15,123.00,12,12,10,'2014-05-03 16:28:00','填写备注'),(18,16,'',19,83,15,0.00,12,12,10,'2014-05-03 16:28:00','填写备注'),(19,16,'',19,83,15,0.00,12,12,10,'2014-05-03 16:29:00','填写备注'),(20,16,'',19,83,15,0.00,12,12,10,'2014-05-03 16:29:00','填写备注'),(21,16,'',19,83,15,111.00,12,12,10,'2014-05-03 16:29:00','填写备注');
/*!40000 ALTER TABLE `pas_spending` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pas_template`
--

DROP TABLE IF EXISTS `pas_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_template` (
  `TEMPLATE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `TEMPLATE_NAME` varchar(20) DEFAULT NULL,
  `IMAGE` varchar(50) DEFAULT NULL,
  `IOTYPE` varchar(5) DEFAULT NULL,
  `FIRSTLEVEL_ID` int(11) DEFAULT NULL,
  `SECONDLEVEL_ID` int(11) DEFAULT NULL,
  `ACCOUNT_ID` int(11) DEFAULT NULL,
  `MONEY` decimal(10,2) DEFAULT NULL,
  `PROJECT_ID` int(11) DEFAULT NULL,
  `BUSINESS_ID` int(11) DEFAULT NULL,
  `MEMBER_ID` int(11) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `REMARK` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`TEMPLATE_ID`),
  KEY `FK_TEMPLATE_USER_idx` (`USER_ID`),
  KEY `FK_TEMPLATE_FIRSTLEVEL_idx` (`FIRSTLEVEL_ID`),
  KEY `FK_TEMPLATE_SECONDLEVEL_idx` (`SECONDLEVEL_ID`),
  KEY `FK_TEMPLATE_ACCOUNT_idx` (`ACCOUNT_ID`),
  KEY `FK_TEMPLATE_PROJECT_idx` (`PROJECT_ID`),
  KEY `FK_TEMPLATE_BUSINESS_idx` (`BUSINESS_ID`),
  KEY `FK_TEMPLATE_FAMILY_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_TEMPLATE_ACCOUNT` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `pas_account` (`ACCOUNT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TEMPLATE_BUSINESS` FOREIGN KEY (`BUSINESS_ID`) REFERENCES `pas_business` (`BUSINESS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TEMPLATE_FAMILY` FOREIGN KEY (`MEMBER_ID`) REFERENCES `pas_family` (`MEMBER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TEMPLATE_FIRSTLEVEL` FOREIGN KEY (`FIRSTLEVEL_ID`) REFERENCES `pas_firstlevel` (`FIRSTLEVEL_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TEMPLATE_PROJECT` FOREIGN KEY (`PROJECT_ID`) REFERENCES `pas_project` (`PROJECT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TEMPLATE_SECONDLEVEL` FOREIGN KEY (`SECONDLEVEL_ID`) REFERENCES `pas_secondlevel` (`SECONDLEVEL_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TEMPLATE_USER` FOREIGN KEY (`USER_ID`) REFERENCES `pas_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_template`
--

LOCK TABLES `pas_template` WRITE;
/*!40000 ALTER TABLE `pas_template` DISABLE KEYS */;
INSERT INTO `pas_template` VALUES (1,0,'早餐模板','breakfast.png','O',1,1,1,3.00,1,1,1,'2013-12-16 23:59:59','早饭'),(2,0,'工资模板',NULL,'I',2,2,2,3000.00,5,1,1,'2014-04-15 21:27:29','工资'),(3,0,'话费模板',NULL,'O',6,11,3,50.00,1,4,1,'2014-05-03 18:20:02','话费模板'),(4,0,'餐补模板',NULL,'I',2,23,1,300.00,5,1,1,'2014-04-15 21:54:47','餐补'),(5,15,'午饭模板',NULL,'O',12,61,10,10.00,7,6,7,'2014-04-23 12:20:25','午饭'),(6,15,'工资',NULL,'I',17,76,11,2000.00,7,6,7,'2014-04-23 12:21:11','工资');
/*!40000 ALTER TABLE `pas_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pas_user`
--

DROP TABLE IF EXISTS `pas_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `NICKNAME` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `HEADIAMGE` varchar(50) DEFAULT NULL,
  `CREATETIME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_NAME_UNIQUE` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_user`
--

LOCK TABLES `pas_user` WRITE;
/*!40000 ALTER TABLE `pas_user` DISABLE KEYS */;
INSERT INTO `pas_user` VALUES (0,'admin','管理员','769835590@qq.com','admin.png','1991-12-16 23:59:59'),(11,'test01','test','110@qq.com','','2014-03-20 13:29:00'),(12,'111@qq.com','','','','2014-04-23 11:07:55'),(13,'11test@qq.com','11test@qq.com','11test@qq.com','','2014-04-23 11:13:29'),(14,'test@qq.com','test@qq.com','test@qq.com','','2014-04-23 11:23:02'),(15,'guduchina@qq.com','guduchina@qq.com','guduchina@qq.com','','2014-04-23 12:15:29'),(16,'shenzixiang','shenzixiang','shenzixiang','','2014-05-03 16:26:21');
/*!40000 ALTER TABLE `pas_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pas_userlogin`
--

DROP TABLE IF EXISTS `pas_userlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pas_userlogin` (
  `USER_NAME` varchar(20) NOT NULL,
  `USER_PASS` varchar(50) NOT NULL,
  PRIMARY KEY (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pas_userlogin`
--

LOCK TABLES `pas_userlogin` WRITE;
/*!40000 ALTER TABLE `pas_userlogin` DISABLE KEYS */;
INSERT INTO `pas_userlogin` VALUES ('111@qq.com','E63E97B8DBBB8722A25F88CD3C27A018'),('11test@qq.com','4061863CAF7F28C0B0346719E764D561'),('admin','FF9830C42660C1DD1942844F8069B74A'),('guduchina@qq.com','5AD334BF2DDBEF59697665FBAB024D71'),('shenzixiang','E13F3643CC57E9C43577229842080912'),('test@qq.com','D30B6A4D9E94CBB9DB971C6283CFCD9E');
/*!40000 ALTER TABLE `pas_userlogin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-03 21:57:51
