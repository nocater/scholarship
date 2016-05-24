/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : scholarship

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2016-04-14 15:06:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `role_id` int(15) DEFAULT NULL COMMENT '角色ID',
  `college_id` int(15) DEFAULT NULL,
  `grade_id` int(15) DEFAULT NULL,
  `accno` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `account_ibfk_3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=374 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '1', null, null, 'cs', '陈帅', '95cc64dd2825f9df13ec4ad683ecf339', '男', '123456', '13124345677', '456', null, '2016-04-11 12:42:39');

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `account_id` int(15) DEFAULT NULL,
  `status` int(15) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `approve_account_id` int(15) DEFAULT NULL COMMENT '审批人',
  `scholarship_id` int(15) DEFAULT NULL COMMENT '助学金',
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply
-- ----------------------------


-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '院系名称',
  `memo` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------

-- ----------------------------
-- Table structure for datas
-- ----------------------------
DROP TABLE IF EXISTS `datas`;
CREATE TABLE `datas` (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account_id` int(15) DEFAULT NULL COMMENT '学生',
  `college` varchar(255) DEFAULT NULL COMMENT '学院',
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `grade` varchar(255) DEFAULT NULL COMMENT '班级',
  `year` varchar(15) DEFAULT NULL COMMENT '申请年份',
  `type` int(15) DEFAULT NULL COMMENT '信息状态0未修改1修改后信息',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `idnumber` varchar(255) DEFAULT NULL COMMENT '身份证ID',
  `bankcard` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `area` varchar(255) DEFAULT NULL COMMENT '东中西部',
  `address` varchar(500) DEFAULT NULL COMMENT '家庭地址',
  `distance` varchar(255) DEFAULT NULL COMMENT '离县城的距离',
  `expenses` varchar(255) DEFAULT NULL COMMENT '月生活费',
  `isLoan` varchar(255) DEFAULT NULL COMMENT '生源地贷款',
  `name_grandfather` varchar(255) DEFAULT NULL,
  `name_grandmother` varchar(255) DEFAULT NULL,
  `in_grandfather` varchar(255) DEFAULT NULL COMMENT '爷爷收入(退休金)',
  `in_grandmother` varchar(255) DEFAULT NULL COMMENT '奶奶收入',
  `health_grandfather` varchar(500) DEFAULT NULL COMMENT '爷爷身体状况',
  `health_grandmother` varchar(500) DEFAULT NULL COMMENT '奶奶身体状况',
  `name_father` varchar(255) DEFAULT NULL,
  `name_mother` varchar(255) DEFAULT NULL,
  `in_father` varchar(255) DEFAULT NULL COMMENT '父亲收入',
  `in_mother` varchar(255) DEFAULT NULL COMMENT '母亲收入',
  `career_father` varchar(255) DEFAULT NULL COMMENT '父亲职业',
  `career_mother` varchar(255) DEFAULT NULL COMMENT '母亲职业',
  `health_father` varchar(500) DEFAULT NULL COMMENT '父亲身体状况',
  `health_mother` varchar(500) DEFAULT NULL COMMENT '母亲身体状况',
  `others` varchar(500) DEFAULT NULL COMMENT '兄弟姐妹信息',
  `in_family` varchar(255) DEFAULT NULL COMMENT '家庭年收入',
  `out_main` varchar(500) DEFAULT NULL COMMENT '主要支出项',
  `balance` varchar(255) DEFAULT NULL COMMENT '盈亏',
  `descripe` varchar(500) DEFAULT NULL COMMENT '主要困难原因描述',
  `file` varchar(255) DEFAULT NULL COMMENT '附件',
  `accident` varchar(500) DEFAULT NULL COMMENT '变故',
  `score_place` varchar(255) DEFAULT NULL COMMENT '成绩排名',
  `quality_place` varchar(255) DEFAULT NULL COMMENT '素质排名',
  `memo` varchar(500) DEFAULT NULL COMMENT '备注',
  `ex1` varchar(255) DEFAULT NULL,
  `ex2` varchar(255) DEFAULT NULL,
  `ex3` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `acc_id` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of datas
-- ----------------------------

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `college_id` int(15) DEFAULT NULL COMMENT '学院',
  `status` int(11) DEFAULT NULL COMMENT '班级状态标识 1激活-1锁定',
  `major` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '班名',
  `edubg` varchar(255) DEFAULT NULL COMMENT '学历(本科/专科)',
  `grade` varchar(255) DEFAULT NULL COMMENT '入学年级',
  `stay` int(11) DEFAULT NULL COMMENT '学年',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `college_id` (`college_id`),
  CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `role_id` int(15) DEFAULT NULL,
  `college_id` int(15) DEFAULT NULL,
  `grade_id` int(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `college_id` (`college_id`),
  KEY `grade_id` (`grade_id`),
  CONSTRAINT `relation_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `relation_ibfk_2` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`),
  CONSTRAINT `relation_ibfk_3` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=259 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of relation
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名字',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '', '2016-04-14 15:05:20', '2016-04-14 15:05:20');
INSERT INTO `role` VALUES ('2', '学生', null, '2016-04-14 15:05:20', '2016-04-14 15:05:20');

-- ----------------------------
-- Table structure for scholarship
-- ----------------------------
DROP TABLE IF EXISTS `scholarship`;
CREATE TABLE `scholarship` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL COMMENT '奖/助学金名字',
  `money` int(15) DEFAULT NULL COMMENT '金额',
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `id_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scholarship
-- ----------------------------
INSERT INTO `scholarship` VALUES ('1', '国家奖学金', '', '5000', null, null);
INSERT INTO `scholarship` VALUES ('2', '国家助学金', '一等', '4000', null, null);
INSERT INTO `scholarship` VALUES ('3', '国家助学金', '二等', '2000', null, null);
INSERT INTO `scholarship` VALUES ('4', '国家助学金', '三等', '1000', null, null);
INSERT INTO `scholarship` VALUES ('5', '国家励志奖学金', '', '8000', null, null);
INSERT INTO `scholarship` VALUES ('6', '明珠学子助学金', '一等', '2000', null, null);
INSERT INTO `scholarship` VALUES ('7', '明珠学子助学金', '二等', '1000', null, null);
INSERT INTO `scholarship` VALUES ('8', '明珠学子助学金', '三等', '800', null, null);
INSERT INTO `scholarship` VALUES ('9', '明珠学子励志奖学金', '一等', '4000', null, null);
INSERT INTO `scholarship` VALUES ('10', '明珠学子励志奖学金', '二等', '2000', null, null);
INSERT INTO `scholarship` VALUES ('11', '明珠学子励志奖学金', '三等', '1000', null, null);
INSERT INTO `scholarship` VALUES ('12', '预留', '', '0', null, null);



/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : scholarship_setting

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2016-05-24 18:46:58
*/

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `scholarship_setting` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `scholarship_setting`;

-- ----------------------------
-- Table structure for audit
-- ----------------------------
DROP TABLE IF EXISTS `audit`;
CREATE TABLE `audit` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `account_id` int(15) DEFAULT NULL,
  `operation_time` datetime DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `source_ip` varchar(255) DEFAULT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4065 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of audit
-- ----------------------------

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `setkey` varchar(50) DEFAULT NULL COMMENT 'key',
  `setvalue` varchar(255) DEFAULT NULL COMMENT 'value',
  `settype` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES ('1', 'applyswitch', '1', null);
INSERT INTO `setting` VALUES ('2', 'codesswitch', '0', null);
INSERT INTO `setting` VALUES ('3', 'alertinfo', '请确保信息填写真实完整，一经核实失败，后果自负！', null);
