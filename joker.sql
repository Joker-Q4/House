/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : joker

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-01-18 20:18:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_number` int(11) NOT NULL,
  `house_number` int(11) NOT NULL,
  `house_address` varchar(255) NOT NULL,
  `owner_name` varchar(255) NOT NULL,
  `owner_pnumber` int(11) NOT NULL,
  `owner_idnumber` varchar(255) NOT NULL,
  `renter_name` varchar(255) NOT NULL,
  `renter_number` int(11) NOT NULL,
  `renter_idnumber` varchar(255) NOT NULL,
  `rent` int(11) NOT NULL,
  `lease_term` int(11) NOT NULL,
  `agent` varchar(255) NOT NULL,
  `agency_fee` int(11) NOT NULL,
  `contract_date` date NOT NULL,
  `contract_photo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES ('1', '10000', '1', '航空航天大学', '丁小平', '89723110', '9', '张庆民', '1111111', '1', '2300', '2', '霍超然', '200', '2019-01-15', '/files/images/1/1/P0MSSF8FT7311MDEL9ZV0KFR390L4K2I.jpeg');
INSERT INTO `contract` VALUES ('2', '10001', '15', '37号航空航天大学', '张庆民', '123456788', '12133464564664', '何聪', '98723100', '54646161', '8000', '2', '霍超然', '2000', '2019-12-20', '/files/images/14/5/N9OHS5U4O3HIFDAHCWVT4SX1J025FU0W.jpeg');
INSERT INTO `contract` VALUES ('3', '12', '135', '皇姑区怒江区4栋2-1', '吴彦祖', '189723110', '34899161977946624', '程稳重', '186359646', '34987619976644634', '2500', '2', '何聪', '250', '2019-01-18', '/files/images/6/15/NSPHYA32K0VP1MCX07UW4UWYVUINQ53K.jpeg');

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `phone` int(15) NOT NULL,
  `rent` double(10,2) NOT NULL,
  `apartment` varchar(255) NOT NULL,
  `area` double(10,2) NOT NULL,
  `flr` int(5) NOT NULL,
  `orientation` varchar(255) NOT NULL,
  `condi` varchar(255) NOT NULL,
  `residential_quarters` varchar(255) NOT NULL,
  `respective_area` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `property_right` int(3) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image1` varchar(255) NOT NULL,
  `image2` varchar(255) NOT NULL,
  `image3` varchar(255) NOT NULL,
  `stater` varchar(255) NOT NULL,
  `id_owner` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('9', '丁小平', '98723110', '80000.00', '一室一厅', '800000.00', '11', '南北', '精装', '沈北小区', '沈北新区', '航空航天大学', '10', '母校', '/files/images/13/4/E1KFAIYQADGQZ5MWZFIWIQEHACH6C4MW.jpeg', '/files/images/2/8/ALZOON97TGDDA3L360O7AOJA51Y5ZNSK.jpeg', '/files/images/1/1/P0MSSF8FT7311MDEL9ZV0KFR390L4K2I.jpeg', 'false', '10');
INSERT INTO `house` VALUES ('10', '周大海', '98723111', '8000.00', '一室两厅', '80000.00', '11', '南北', '粗装修', '沈北小区', '沈北新区', '38号', '10', '母校', '/files/images/13/4/E1KFAIYQADGQZ5MWZFIWIQEHACH6C4MW.jpeg', '/files/images/2/8/ALZOON97TGDDA3L360O7AOJA51Y5ZNSK.jpeg', '/files/images/1/1/P0MSSF8FT7311MDEL9ZV0KFR390L4K2I.jpeg', 'false', '11');
INSERT INTO `house` VALUES ('11', '阮磊', '98723112', '8000.00', '三室两厅', '80000.00', '5', '东西', '粗装修', '沈北小区', '沈北新区', '39号', '10', '母校', '/files/images/13/4/E1KFAIYQADGQZ5MWZFIWIQEHACH6C4MW.jpeg', '/files/images/2/8/ALZOON97TGDDA3L360O7AOJA51Y5ZNSK.jpeg', '/files/images/1/1/P0MSSF8FT7311MDEL9ZV0KFR390L4K2I.jpeg', 'false', '12');
INSERT INTO `house` VALUES ('12', '王奕', '98723113', '8000.00', '四室一厅', '80000.00', '5', '东西', '精装修', '沈北小区', '苏家屯区', '39号', '10', '母校', '/files/images/13/4/E1KFAIYQADGQZ5MWZFIWIQEHACH6C4MW.jpeg', '/files/images/2/8/ALZOON97TGDDA3L360O7AOJA51Y5ZNSK.jpeg', '/files/images/1/1/P0MSSF8FT7311MDEL9ZV0KFR390L4K2I.jpeg', 'false', '13');
INSERT INTO `house` VALUES ('13', '王大明', '98723114', '8000.00', '四室一厅', '80000.00', '5', '东西', '精装修', '沈北小区', '铁西区', '40号', '10', '母校', '/files/images/13/4/E1KFAIYQADGQZ5MWZFIWIQEHACH6C4MW.jpeg', '/files/images/2/8/ALZOON97TGDDA3L360O7AOJA51Y5ZNSK.jpeg', '/files/images/1/1/P0MSSF8FT7311MDEL9ZV0KFR390L4K2I.jpeg', 'false', '14');
INSERT INTO `house` VALUES ('14', '王小红', '98723115', '8000.00', '四室一厅', '80000.00', '5', '东西', '精装修', '沈北小区', '铁西区', '40号', '10', '母校', '/files/images/13/4/E1KFAIYQADGQZ5MWZFIWIQEHACH6C4MW.jpeg', '/files/images/2/8/ALZOON97TGDDA3L360O7AOJA51Y5ZNSK.jpeg', '/files/images/1/1/P0MSSF8FT7311MDEL9ZV0KFR390L4K2I.jpeg', 'false', '15');
INSERT INTO `house` VALUES ('15', '张庆民', '123456788', '5000.00', '四室两厅', '100.00', '5', '东西', '精装', '小区', '沈北新区', '37号航空航天大学', '50', '很好', '/files/images/7/15/3IYGU2LKDXR7Q88UW8HJRNKFE1DHFTF0.jpeg', '/files/images/2/0/P0BRV31FYRHO8C0W9ZKP6TGJISFXX1BF.jpeg', '/files/images/11/14/5BS9TD42YUDY4BO8HZ5HYRIOQX9EL40S.jpeg', 'false', '19');

-- ----------------------------
-- Table structure for houseowner
-- ----------------------------
DROP TABLE IF EXISTS `houseowner`;
CREATE TABLE `houseowner` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `age` int(30) NOT NULL,
  `p_number` int(30) NOT NULL,
  `id_number` varchar(30) NOT NULL,
  `address` varchar(255) NOT NULL,
  `id_house` int(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of houseowner
-- ----------------------------
INSERT INTO `houseowner` VALUES ('10', '丁小平', '', '0', '98723110', '0', '', '9');
INSERT INTO `houseowner` VALUES ('11', '周大海', '', '0', '98723111', '0', '', '10');
INSERT INTO `houseowner` VALUES ('12', '阮磊', '', '0', '98723112', '0', '', '11');
INSERT INTO `houseowner` VALUES ('13', '王奕', '', '0', '98723113', '0', '', '12');
INSERT INTO `houseowner` VALUES ('14', '王大明', '', '0', '98723114', '0', '', '13');
INSERT INTO `houseowner` VALUES ('15', '王小红', '', '0', '98723115', '0', '', '14');
INSERT INTO `houseowner` VALUES ('16', '何聪', '男', '12', '136999654', '211384446', '好多', '0');
INSERT INTO `houseowner` VALUES ('17', '霍超然', '男', '12', '1234567890', '21138444645667', '好多好多话', '0');
INSERT INTO `houseowner` VALUES ('19', '张庆民', '', '0', '123456788', '0', '', '15');
INSERT INTO `houseowner` VALUES ('20', '吴彦祖', '', '0', '189723110', '0', '', '16');

-- ----------------------------
-- Table structure for tenant
-- ----------------------------
DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `id_number` varchar(255) NOT NULL,
  `min_rent` double(11,0) NOT NULL,
  `max_rent` double(11,0) NOT NULL,
  `apart` varchar(255) NOT NULL,
  `min_area` double(11,0) NOT NULL,
  `max_area` double(11,0) NOT NULL,
  `residential_quarters` varchar(255) NOT NULL,
  `respective_area` varchar(255) NOT NULL,
  `p_number` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tenant
-- ----------------------------
INSERT INTO `tenant` VALUES ('1', '张庆民', '男', '20', '110228199605121112', '2000', '3000', '一室一厅两厅', '80', '100', '沈北小区', '沈北新区', '110', '南4');
INSERT INTO `tenant` VALUES ('2', '王小明', '男', '22', '34897668185646764', '0', '3000', '一室一厅', '100', '150', '清华小区', '皇姑区', '123654789', '沈阳北站南四小区');

-- ----------------------------
-- Table structure for worker
-- ----------------------------
DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `workername` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of worker
-- ----------------------------
INSERT INTO `worker` VALUES ('1', '张庆民', '123456');
INSERT INTO `worker` VALUES ('2', '何聪', '123456');
INSERT INTO `worker` VALUES ('3', '霍超然', '123456');
