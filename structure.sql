/*
SQLyog Community
MySQL - 8.0.17 : Database - mailapi2021
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `mailapi2021`;

/*Table structure for table `messages` */

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `from_username` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmskamep6up21d7no97oo0321v` (`from_username`),
  CONSTRAINT `FKmskamep6up21d7no97oo0321v` FOREIGN KEY (`from_username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `recipients` */

DROP TABLE IF EXISTS `recipients`;

CREATE TABLE `recipients` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message_status` varchar(255) DEFAULT NULL,
  `recipient_type` varchar(255) DEFAULT NULL,
  `message` bigint(20) NOT NULL,
  `user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKanva8lpaajf333xoly0w2r917` (`message`),
  KEY `FKd2pmnrju3f9jge9r4tprn8gu` (`user`),
  CONSTRAINT `FKanva8lpaajf333xoly0w2r917` FOREIGN KEY (`message`) REFERENCES `messages` (`id`),
  CONSTRAINT `FKd2pmnrju3f9jge9r4tprn8gu` FOREIGN KEY (`user`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Procedure structure for procedure `pAddUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `pAddUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `pAddUser`(pName VARCHAR(50), pLastName varchar(50), pUsername varchar(50), pPassword VARCHAR(50))
BEGIN
	INSERT INTO users(`name`, last_name, username, `password`) values (pName, pLastName, pUsername, pPassword);
END */$$
DELIMITER ;

/* Procedure structure for procedure `pDeleteUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `pDeleteUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `pDeleteUser`(pUsername varchar(50), OUT pRowCount  INT)
BEGIN
	DELETE FROM users where username = pUsername;
	select ROW_COUNT() INTO pRowCount;
END */$$
DELIMITER ;

/* Procedure structure for procedure `pGetUsers` */

/*!50003 DROP PROCEDURE IF EXISTS  `pGetUsers` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `pGetUsers`()
begin
	select * from users;
end */$$
DELIMITER ;

/* Procedure structure for procedure `pGetUsersBeginWith` */

/*!50003 DROP PROCEDURE IF EXISTS  `pGetUsersBeginWith` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `pGetUsersBeginWith`(pName varchar(50))
begin
	SELECT * FROM users where `name` like CONCAT(pName,'%');
end */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
