/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.5.20 : Database - store
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`store` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `store`;

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `name` varchar(255) DEFAULT NULL,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `login` */

insert  into `login`(`name`,`account`,`password`) values 
('黄奕然','hyr','123'),
('孙佳冬','sjd','123');

/*Table structure for table `shoopingcar` */

DROP TABLE IF EXISTS `shoopingcar`;

CREATE TABLE `shoopingcar` (
  `account` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `shoopingcar` */

/*Table structure for table `tradeinformation` */

DROP TABLE IF EXISTS `tradeinformation`;

CREATE TABLE `tradeinformation` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tradeinformation` */

insert  into `tradeinformation`(`id`,`name`,`area`,`introduce`,`price`) values 
(1,'倍思蓝牙耳机','广州','待电强，音质高','168'),
(2,'小米pro','深圳','1200前置，5000MA超大容量','4999'),
(3,'13pro','美国','A15处理器，3065MA,超长待机','9799');

/*Table structure for table `tradeshow` */

DROP TABLE IF EXISTS `tradeshow`;

CREATE TABLE `tradeshow` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `productPrice` varchar(255) DEFAULT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tradeshow` */

insert  into `tradeshow`(`id`,`name`,`productPrice`,`imgUrl`) values 
(1,'净爽肤护','499','https://img2.baidu.com/it/u=2451135064,3805245217&fm=253&fmt=auto&app=138&f=JPEG?w=658&h=465');

/*Table structure for table `userbuy` */

DROP TABLE IF EXISTS `userbuy`;

CREATE TABLE `userbuy` (
  `acount` varchar(255) NOT NULL,
  `tradeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`acount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userbuy` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
