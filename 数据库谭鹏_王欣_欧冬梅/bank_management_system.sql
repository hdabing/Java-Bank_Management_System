# SQL Manager 2005 Lite for MySQL 3.6.5.8
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : bank_management_system


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES gbk */;

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `bank_management_system`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_swedish_ci';

#
# Structure for the `userinfo` table : 
#

CREATE TABLE `userinfo` (
  `userAccount` varchar(20) NOT NULL,
  `userPassword` varchar(50) NOT NULL,
  `userTel` varchar(20) default NULL,
  `userID` varchar(20) NOT NULL,
  `userMail` varchar(30) default NULL,
  `userName` varchar(20) character set utf8 default NULL,
  `userSex` varchar(20) default NULL,
  `ifadmin` tinyint(4) default NULL,
  PRIMARY KEY  (`userAccount`),
  UNIQUE KEY `userID` (`userID`),
  UNIQUE KEY `userAccount` (`userAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `currentaccount` table : 
#

CREATE TABLE `currentaccount` (
  `userAccount` varchar(30) NOT NULL,
  `balance` float(9,3) default NULL,
  `interest` float(9,3) default NULL,
  `operateTime` datetime default NULL,
  PRIMARY KEY  (`userAccount`),
  CONSTRAINT `currentaccount_fk` FOREIGN KEY (`userAccount`) REFERENCES `userinfo` (`userAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `dep` table : 
#

CREATE TABLE `dep` (
  `depnum` int(11) NOT NULL,
  `depname` varchar(20) character set utf8 default NULL,
  `depmanager` varchar(20) character set utf8 default NULL,
  `deptel` varchar(20) default NULL,
  PRIMARY KEY  (`depnum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `desposittype` table : 
#

CREATE TABLE `desposittype` (
  `typename` varchar(20) character set utf8 default NULL,
  `typeid` tinyint(4) NOT NULL,
  PRIMARY KEY  (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `emp` table : 
#

CREATE TABLE `emp` (
  `empnum` int(11) NOT NULL auto_increment,
  `depnum` int(11) default NULL,
  `empborn` date default NULL,
  `hireday` date default NULL,
  `userAccount` varchar(20) NOT NULL,
  PRIMARY KEY  (`empnum`),
  UNIQUE KEY `userAccount` (`userAccount`),
  KEY `depnum` (`depnum`),
  CONSTRAINT `emp_fk` FOREIGN KEY (`depnum`) REFERENCES `dep` (`depnum`),
  CONSTRAINT `emp_fk1` FOREIGN KEY (`userAccount`) REFERENCES `userinfo` (`userAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `fixedaccount` table : 
#

CREATE TABLE `fixedaccount` (
  `userAccount` varchar(20) NOT NULL,
  `balance` float(9,3) default NULL,
  `interest` float(9,3) default NULL,
  `type` int(11) default NULL,
  `tip` varchar(20) default NULL,
  `time` date default NULL,
  PRIMARY KEY  (`userAccount`),
  UNIQUE KEY `userAccount` (`userAccount`),
  CONSTRAINT `fixedaccount_fk` FOREIGN KEY (`userAccount`) REFERENCES `userinfo` (`userAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `moneytypes` table : 
#

CREATE TABLE `moneytypes` (
  `userAccount` varchar(20) character set latin1 NOT NULL,
  `Amount` int(11) default NULL,
  `aAmount` int(11) default NULL,
  `moneyType` tinyint(4) default NULL,
  `typeid` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`typeid`),
  UNIQUE KEY `typeid` (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Structure for the `specialdraw` table : 
#

CREATE TABLE `specialdraw` (
  `sID` varchar(20) NOT NULL,
  `sAmount` int(11) default NULL,
  `sPassword` varchar(20) default NULL,
  PRIMARY KEY  (`sID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `userinfo` table  (LIMIT 0,500)
#

INSERT INTO `userinfo` (`userAccount`, `userPassword`, `userTel`, `userID`, `userMail`, `userName`, `userSex`, `ifadmin`) VALUES 
  ('5738157337742175739','50F8422F90FE100280DD4381397D5463','a','11111','a','a','1',NULL),
  ('6217002912938399929','7378EA43D685989626248E0329AB486D','12312','121112134',NULL,'啊','1',0),
  ('6217002940103996052','50F8422F90FE100280DD4381397D5463','213','2','22222@qq.com','啊','1',0),
  ('6217002940103996053','50F8422F90FE100280DD4381397D5463','13167422813','1','613258200@qq.com','谭鹏','1',1),
  ('6217002956233054890','50F8422F90FE100280DD4381397D5463','123','2312',NULL,'啊','1',0),
  ('8087333397591274629','194C264A7F2AFF8222B899353BAD7F10','112','11231','1123','欧冬梅','1',0);

COMMIT;

#
# Data for the `currentaccount` table  (LIMIT 0,500)
#

INSERT INTO `currentaccount` (`userAccount`, `balance`, `interest`, `operateTime`) VALUES 
  ('5738157337742175739',0,0,'2016-06-29'),
  ('6217002940103996052',392,2,'2015-06-06'),
  ('6217002940103996053',822.766,0.056,'2016-06-29'),
  ('8087333397591274629',0,0,'2016-06-28');

COMMIT;

#
# Data for the `dep` table  (LIMIT 0,500)
#

INSERT INTO `dep` (`depnum`, `depname`, `depmanager`, `deptel`) VALUES 
  (1,'组织部','王欣','13167422813'),
  (2,'策划部','欧冬梅','13111111110'),
  (3,'嘿嘿','2222','13146471241'),
  (4,'哈哈','2333','213123');

COMMIT;

#
# Data for the `desposittype` table  (LIMIT 0,500)
#

INSERT INTO `desposittype` (`typename`, `typeid`) VALUES 
  ('三个月定期',1),
  ('半年定期',2),
  ('一年定期',3),
  ('两年定期',4),
  ('三年定期',5);

COMMIT;

#
# Data for the `emp` table  (LIMIT 0,500)
#

INSERT INTO `emp` (`empnum`, `depnum`, `empborn`, `hireday`, `userAccount`) VALUES 
  (1,1,'2016-01-01','2016-06-30','6217002940103996052'),
  (2,1,'1996-05-28','1997-05-28','6217002912938399929');

COMMIT;

#
# Data for the `moneytypes` table  (LIMIT 0,500)
#

INSERT INTO `moneytypes` (`userAccount`, `Amount`, `aAmount`, `moneyType`, `typeid`) VALUES 
  ('6217002940103996052',1,1000,1,1),
  ('6217002940103996052',1000,2344,2,2),
  ('6217002940103996053',20000,2232,3,3),
  ('6217002940103996052',2000,45656,4,4);

COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;