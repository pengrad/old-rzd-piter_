-- MySQL dump 10.13  Distrib 5.1.50, for Win32 (ia32)
--
-- Host: localhost    Database: rzd
-- ------------------------------------------------------
-- Server version	5.1.50-community

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
-- Current Database: `rzd`
--

/*!40000 DROP DATABASE IF EXISTS `rzd`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `rzd` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rzd`;

--
-- Table structure for table `direction`
--

DROP TABLE IF EXISTS `direction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direction` (
  `dir_id` int(10) unsigned NOT NULL,
  `dir_name` varchar(100) NOT NULL,
  PRIMARY KEY (`dir_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `disc_id` int(10) unsigned NOT NULL,
  `disc_type_id` int(10) unsigned NOT NULL,
  `disc_type_name` varchar(50) NOT NULL,
  PRIMARY KEY (`disc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `discount_fednonsoc`
--

DROP TABLE IF EXISTS `discount_fednonsoc`;
/*!50001 DROP VIEW IF EXISTS `discount_fednonsoc`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `discount_fednonsoc` (
  `disc_id` int(10) unsigned
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `discount_fedsoc`
--

DROP TABLE IF EXISTS `discount_fedsoc`;
/*!50001 DROP VIEW IF EXISTS `discount_fedsoc`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `discount_fedsoc` (
  `disc_id` int(10) unsigned
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `discount_region`
--

DROP TABLE IF EXISTS `discount_region`;
/*!50001 DROP VIEW IF EXISTS `discount_region`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `discount_region` (
  `disc_id` int(10) unsigned
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `discount_rzdpersonal`
--

DROP TABLE IF EXISTS `discount_rzdpersonal`;
/*!50001 DROP VIEW IF EXISTS `discount_rzdpersonal`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `discount_rzdpersonal` (
  `disc_id` int(10) unsigned
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `discount_rzdservice`
--

DROP TABLE IF EXISTS `discount_rzdservice`;
/*!50001 DROP VIEW IF EXISTS `discount_rzdservice`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `discount_rzdservice` (
  `disc_id` int(10) unsigned
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `discount_rzdwork`
--

DROP TABLE IF EXISTS `discount_rzdwork`;
/*!50001 DROP VIEW IF EXISTS `discount_rzdwork`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `discount_rzdwork` (
  `disc_id` int(10) unsigned
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `discount_study`
--

DROP TABLE IF EXISTS `discount_study`;
/*!50001 DROP VIEW IF EXISTS `discount_study`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `discount_study` (
  `disc_id` int(10) unsigned
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `discount_war`
--

DROP TABLE IF EXISTS `discount_war`;
/*!50001 DROP VIEW IF EXISTS `discount_war`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `discount_war` (
  `disc_id` int(10) unsigned
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `FileId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FileName` varchar(100) DEFAULT NULL COMMENT 'имя XML документа',
  `NumTerm` int(11) DEFAULT NULL COMMENT 'номер терминала',
  `SmenaNum` int(11) DEFAULT NULL COMMENT 'номер смены',
  `PlaceTerm` int(11) NOT NULL COMMENT 'станция приписки',
  `Month` int(11) DEFAULT NULL COMMENT 'номер месяца',
  `TimeOpen` datetime DEFAULT NULL COMMENT 'дата и время открытия смены',
  `FirstTicket` int(11) DEFAULT NULL COMMENT 'порядковый номер пробного билета',
  `Sum` double DEFAULT NULL COMMENT 'сумма смены (денежная выручка за рабочую смену)',
  `TimeClose` datetime DEFAULT NULL COMMENT 'дата и время закрытия смены',
  `NumTickets` int(11) DEFAULT NULL COMMENT 'количество оформленных документов',
  `LenTape` float DEFAULT NULL COMMENT 'длина ленты(метраж)',
  `TypeTerm` varchar(10) NOT NULL COMMENT 'тип терминала',
  `SoftVersion` varchar(100) DEFAULT NULL COMMENT 'версия ПО',
  `INN` varchar(20) DEFAULT NULL COMMENT 'ИНН владельца терминала (организации при постановке на налоговый учет)',
  `FIO` varchar(100) DEFAULT NULL COMMENT 'ФИО кассира',
  `CardOut` int(11) DEFAULT NULL COMMENT 'возвращено карт (Сумма принятого залога)',
  `CardIn` int(11) DEFAULT NULL COMMENT 'выдано карт (Сумма возвращенного залога)',
  `Sup` double DEFAULT NULL COMMENT 'Сумма доплаты льготных билетов (выпадающий доход за рабочую смену)',
  `Cancel` double DEFAULT NULL COMMENT 'Сумма анулированных билетов ',
  `NumProc` int(11) DEFAULT NULL COMMENT 'количество билетов (для p97)',
  `SumProc` double DEFAULT NULL COMMENT 'выручка (сумма билетов для p97)',
  `SumEKLZ` double DEFAULT NULL COMMENT 'сумма в ЭКЛЗ',
  `SCol` double DEFAULT NULL COMMENT 'сумма сборов за оформление ППД',
  `STax` double DEFAULT NULL COMMENT 'НДС за оформление ППД',
  `Blank` int(11) DEFAULT NULL COMMENT 'расход бланков',
  `SumRet` double DEFAULT NULL COMMENT 'сумма возврата по аварийным квитанциям',
  `SumServ` double DEFAULT NULL COMMENT 'сумма услуги',
  `NDSServ` double DEFAULT NULL COMMENT 'НДС услуги',
  `TimeCreate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Дата записи файла',
  PRIMARY KEY (`FileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sector`
--

DROP TABLE IF EXISTS `sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sector` (
  `sect_id` int(10) unsigned NOT NULL,
  `sect_name` varchar(100) NOT NULL,
  `sect_dir_id` int(10) unsigned NOT NULL,
  `sect_parent_id` int(10) unsigned DEFAULT NULL COMMENT 'Ссылка на родительский участок, если участок вложенный',
  PRIMARY KEY (`sect_id`),
  KEY `fk__sector__sect_dir_id` (`sect_dir_id`),
  KEY `fk__sector__sect_parent_id` (`sect_parent_id`),
  CONSTRAINT `fk__sector__sect_dir_id` FOREIGN KEY (`sect_dir_id`) REFERENCES `direction` (`dir_id`),
  CONSTRAINT `fk__sector__sect_parent_id` FOREIGN KEY (`sect_parent_id`) REFERENCES `sector` (`sect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station` (
  `stat_id` int(10) unsigned NOT NULL,
  `stat_name` varchar(100) NOT NULL,
  PRIMARY KEY (`stat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `station_sector_cross`
--

DROP TABLE IF EXISTS `station_sector_cross`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station_sector_cross` (
  `stat_id` int(10) unsigned NOT NULL,
  `sect_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`stat_id`,`sect_id`),
  KEY `fk__station_sector_cross__sect_id` (`sect_id`),
  CONSTRAINT `fk__station_sector_cross__sect_id` FOREIGN KEY (`sect_id`) REFERENCES `sector` (`sect_id`),
  CONSTRAINT `fk__station_sector_cross__stat_id` FOREIGN KEY (`stat_id`) REFERENCES `station` (`stat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_ab`
--

DROP TABLE IF EXISTS `temp_ab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_ab` (
  `a_type` int(11) NOT NULL,
  `a_name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `TicketId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FileId` int(10) unsigned NOT NULL,
  `PerevozGkey` int(11) DEFAULT NULL COMMENT 'код перевозчика в АСОКУПЭ-Л',
  `TrainCat` varchar(5) DEFAULT NULL COMMENT 'категория поезда (скорый, пасс.)',
  `TPlanID` int(11) DEFAULT NULL COMMENT 'Тарифный план (ID в БД АСОКУПЭ-Л)',
  `FromStationE` int(11) NOT NULL COMMENT 'Станция отправления (Экспресс код)',
  `ToStationE` int(11) NOT NULL COMMENT 'Станция назначения (Экспресс код)',
  `TicketTypeID` int(11) NOT NULL COMMENT 'Тип билета (разовый, абонемент)',
  `TicketTypeL` int(11) DEFAULT NULL COMMENT 'Льготная категория',
  `N` int(11) DEFAULT NULL COMMENT 'Номер билета / квитанции /операции',
  `A` tinyint(4) DEFAULT NULL COMMENT 'Признак аннулирования =1',
  `T` datetime DEFAULT NULL COMMENT 'Время операции',
  `S` double DEFAULT NULL COMMENT 'сумма оплаченная пассажиром',
  `R` tinyint(4) DEFAULT NULL COMMENT 'Признак туда и обратно =1',
  `P` datetime DEFAULT NULL COMMENT 'Дата предварительного проезда',
  `U` double DEFAULT NULL COMMENT 'Сумма доплаты (сумма к выплате субъектом РФ, из федерального бюджета, из бюджета РЖД)',
  `V` int(11) DEFAULT NULL COMMENT 'Тип документа на льготу по КТО, ЭТТ, регионалы',
  `D` varchar(20) DEFAULT NULL COMMENT 'Номер льготного документа / ЭТТ с БСК',
  `B` varchar(10) DEFAULT NULL COMMENT 'Код билетного бюро',
  `O` varchar(20) DEFAULT NULL COMMENT 'Код организации',
  `H` varchar(2) DEFAULT NULL COMMENT 'Категория пассажира',
  `C` varchar(25) DEFAULT NULL COMMENT 'Номер ТК / СК / ЭТТ',
  `K` varchar(15) DEFAULT NULL COMMENT 'Кристалл ТК / СК / ЭТТ',
  `F` varchar(100) DEFAULT NULL COMMENT 'Фамилия льготника',
  `Z` varchar(25) DEFAULT NULL COMMENT 'Номер аннулированного документа',
  `E` varchar(10) DEFAULT NULL COMMENT 'Номер ошибки',
  `DK` varchar(25) DEFAULT NULL COMMENT 'Внутренний номер по ЭТТ с БСК',
  `Col` double DEFAULT NULL COMMENT 'Стоимость услуги оформления ППД на ПКТК',
  `Tax` double DEFAULT NULL COMMENT 'НДС услуги оформления ППД на ПКТК',
  `NDS` double DEFAULT NULL COMMENT 'НДС по ручной клади',
  `Bl` int(11) DEFAULT NULL COMMENT 'Номер бланка',
  `SN` varchar(15) DEFAULT NULL COMMENT 'СНИЛС',
  `TimeCalcReport` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Отчетный день, за который считать билет',
  PRIMARY KEY (`TicketId`),
  KEY `FK__ticket__FileId` (`FileId`),
  KEY `ix__ticket__TimeCalcReport` (`TimeCalcReport`) USING BTREE,
  CONSTRAINT `FK__ticket__FileId` FOREIGN KEY (`FileId`) REFERENCES `file` (`FileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'rzd'
--
/*!50003 DROP PROCEDURE IF EXISTS `test` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `test`(in s varchar(1000))
BEGIN

  declare i1,i2 int default 1;    
  
    create temporary table A(i int);
  
	select locate(',',s,1) into i2;
  WHILE i2 > 0 DO
 	 insert A(i) select substring(s,i1,i2-i1);
    set i1 = i2+1;
    select locate(',',s,i1) into i2;
  END WHILE;
  insert A(i) select substring(s,i1,length(s)-i1+1);  
  select * from a;
  
  drop table a;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Current Database: `rzd`
--

USE `rzd`;

--
-- Final view structure for view `discount_fednonsoc`
--

/*!50001 DROP TABLE IF EXISTS `discount_fednonsoc`*/;
/*!50001 DROP VIEW IF EXISTS `discount_fednonsoc`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `discount_fednonsoc` AS select `discount`.`disc_id` AS `disc_id` from `discount` where (`discount`.`disc_type_id` = 2) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `discount_fedsoc`
--

/*!50001 DROP TABLE IF EXISTS `discount_fedsoc`*/;
/*!50001 DROP VIEW IF EXISTS `discount_fedsoc`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `discount_fedsoc` AS select `discount`.`disc_id` AS `disc_id` from `discount` where (`discount`.`disc_type_id` = 1) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `discount_region`
--

/*!50001 DROP TABLE IF EXISTS `discount_region`*/;
/*!50001 DROP VIEW IF EXISTS `discount_region`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `discount_region` AS select `discount`.`disc_id` AS `disc_id` from `discount` where (`discount`.`disc_type_id` = 3) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `discount_rzdpersonal`
--

/*!50001 DROP TABLE IF EXISTS `discount_rzdpersonal`*/;
/*!50001 DROP VIEW IF EXISTS `discount_rzdpersonal`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `discount_rzdpersonal` AS select `discount`.`disc_id` AS `disc_id` from `discount` where (`discount`.`disc_type_id` = 6) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `discount_rzdservice`
--

/*!50001 DROP TABLE IF EXISTS `discount_rzdservice`*/;
/*!50001 DROP VIEW IF EXISTS `discount_rzdservice`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `discount_rzdservice` AS select `discount`.`disc_id` AS `disc_id` from `discount` where (`discount`.`disc_type_id` = 8) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `discount_rzdwork`
--

/*!50001 DROP TABLE IF EXISTS `discount_rzdwork`*/;
/*!50001 DROP VIEW IF EXISTS `discount_rzdwork`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `discount_rzdwork` AS select `discount`.`disc_id` AS `disc_id` from `discount` where (`discount`.`disc_type_id` = 7) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `discount_study`
--

/*!50001 DROP TABLE IF EXISTS `discount_study`*/;
/*!50001 DROP VIEW IF EXISTS `discount_study`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `discount_study` AS select `discount`.`disc_id` AS `disc_id` from `discount` where (`discount`.`disc_type_id` = 5) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `discount_war`
--

/*!50001 DROP TABLE IF EXISTS `discount_war`*/;
/*!50001 DROP VIEW IF EXISTS `discount_war`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `discount_war` AS select `discount`.`disc_id` AS `disc_id` from `discount` where (`discount`.`disc_type_id` = 4) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-02-20  2:10:44
