/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : bd_store

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2018-03-30 21:09:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `itemname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(8000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pirce` float NOT NULL,
  `count` int(11) NOT NULL DEFAULT '0',
  `remain` int(11) NOT NULL DEFAULT '100000',
  `datatime` datetime NOT NULL,
  `status` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_item
-- ----------------------------
INSERT INTO `tb_item` VALUES ('1', 'java虚拟机1', '标题', '描述', 'http://f10.topitme.com/l/201004/18/12715343252460.jpg', '34', '11', '294', '2018-03-19 21:02:14', '1');
INSERT INTO `tb_item` VALUES ('2', 'java虚拟机2', '标题', '描述', 'https://images-cn.ssl-images-amazon.com/images/I/51ewBewBwIL._AC_SS150_.jpg', '34', '42', '91', '2018-03-19 21:02:14', '1');
INSERT INTO `tb_item` VALUES ('4', 'java虚拟机4', '标题', '描述', 'https://images-cn.ssl-images-amazon.com/images/I/51ewBewBwIL._AC_SS150_.jpg', '34', '96', '92', '2018-03-19 21:02:14', '1');
INSERT INTO `tb_item` VALUES ('6', '标题1', '摘要2', '正文3', '1522415245764.jpg', '43', '0', '100000', '2018-03-30 21:07:25', '0');
INSERT INTO `tb_item` VALUES ('24', '2', '3', '4', '1522414446316.jpg', '5', '2', '99998', '2018-03-30 20:54:06', '0');
INSERT INTO `tb_item` VALUES ('25', '2', '3', '4', 'http://nec.netease.com/img/l/1.jpg', '6', '0', '100000', '2018-03-30 19:43:31', '1');
INSERT INTO `tb_item` VALUES ('28', '5', '6', '5', '1522414475147.jpg', '8', '1', '99999', '2018-03-30 20:54:35', '0');
INSERT INTO `tb_item` VALUES ('29', '哈哈', '大纲', '的噶', '1522415304572.jpg', '80', '0', '100000', '2018-03-30 21:08:24', '0');

-- ----------------------------
-- Table structure for tb_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_record`;
CREATE TABLE `tb_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `price` float NOT NULL,
  `count` int(11) NOT NULL,
  `datatime` datetime NOT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_record
-- ----------------------------
INSERT INTO `tb_record` VALUES ('23', '1', '1', '34', '2', '2018-03-30 00:13:01', '1');
INSERT INTO `tb_record` VALUES ('24', '1', '4', '34', '2', '2018-03-30 00:13:01', '1');
INSERT INTO `tb_record` VALUES ('25', '3', '28', '8', '1', '2018-03-30 21:02:45', '1');
INSERT INTO `tb_record` VALUES ('26', '3', '2', '34', '1', '2018-03-30 21:05:48', '1');
INSERT INTO `tb_record` VALUES ('27', '3', '24', '5', '2', '2018-03-30 21:05:48', '1');
INSERT INTO `tb_record` VALUES ('28', '3', '1', '34', '1', '2018-03-30 21:06:46', '1');

-- ----------------------------
-- Table structure for tb_shopcar
-- ----------------------------
DROP TABLE IF EXISTS `tb_shopcar`;
CREATE TABLE `tb_shopcar` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `datatime` datetime NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_shopcar
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role` int(1) NOT NULL,
  `status` int(1) NOT NULL,
  `datatime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '10001', '81dc9bdb52d04dc20036dbd8313ed055', '1', '1', '2018-03-19 20:50:50');
INSERT INTO `tb_user` VALUES ('2', 'seller', '981c57a5cfb0f868e064904b8745766f', '2', '1', '2018-03-19 20:51:11');
INSERT INTO `tb_user` VALUES ('3', 'buyer', '37254660e226ea65ce6f1efd54233424', '1', '1', '2018-03-30 21:02:00');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of test
-- ----------------------------
