/*
 Navicat Premium Data Transfer

 Source Server         : zz阿里云北京24.8
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 60.205.0.18:3310
 Source Schema         : EPAMS

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 14/06/2024 13:17:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `admin_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '系统管理员编号',
  `admin_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '系统管理员登陆编号',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '系统管理员登陆密码',
  `remarks` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES (1, '111', '111', 'test');
INSERT INTO `admins` VALUES (2, 'admin', 'admin', NULL);
INSERT INTO `admins` VALUES (3, '222', '222', NULL);
INSERT INTO `admins` VALUES (4, '333', '333', NULL);
INSERT INTO `admins` VALUES (5, '444', '444', NULL);
INSERT INTO `admins` VALUES (6, '555', '555', NULL);
INSERT INTO `admins` VALUES (7, '666', '666', NULL);
INSERT INTO `admins` VALUES (8, '777', '777', 'string');
INSERT INTO `admins` VALUES (9, 'string', 'string', 'string');
INSERT INTO `admins` VALUES (10, 'zz', 'zz', '周');
INSERT INTO `admins` VALUES (11, 'xj', 'xj', '辛');
INSERT INTO `admins` VALUES (12, 'xx', 'xx', '谢');

-- ----------------------------
-- Table structure for confirmation
-- ----------------------------
DROP TABLE IF EXISTS `confirmation`;
CREATE TABLE `confirmation`  (
  `conf_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'AQI确认id',
  `information_id` int(0) NOT NULL COMMENT 'AQI反馈id',
  `inspector_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格员姓名',
  `supervisor_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '监督员姓名',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `community` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `pollution_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '污染等级（1~6数字）',
  `SO2` double NULL DEFAULT NULL COMMENT '二氧化硫浓度',
  `CO` double NULL DEFAULT NULL COMMENT '一氧化碳浓度',
  `PM25` double NULL DEFAULT NULL COMMENT 'PM2.5浓度',
  `date` date NULL DEFAULT NULL COMMENT '确认日期',
  `time` time(0) NULL DEFAULT NULL COMMENT '确认时间',
  PRIMARY KEY (`conf_id`) USING BTREE,
  INDEX `info_id`(`information_id`) USING BTREE,
  INDEX `sup_name`(`supervisor_name`) USING BTREE,
  INDEX `insp_name`(`inspector_name`) USING BTREE,
  CONSTRAINT `info_id` FOREIGN KEY (`information_id`) REFERENCES `information` (`information_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `insp_name` FOREIGN KEY (`inspector_name`) REFERENCES `inspector` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `sup_name` FOREIGN KEY (`supervisor_name`) REFERENCES `supervisor` (`real_name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of confirmation
-- ----------------------------
INSERT INTO `confirmation` VALUES (1, 3, 'zz', 'xx', '北京市', '北京市', '海淀区', '1', 55.5, 4.8, 120, '2024-03-05', '07:57:14');
INSERT INTO `confirmation` VALUES (2, 4, 'zz', 'xx', '辽宁省', '沈阳市', '浑南区', '2', 100, 4.9, 35, '2024-03-05', '07:57:14');
INSERT INTO `confirmation` VALUES (3, 5, 'zz', 'xx', '辽宁省', '沈阳市', '浑南区', '3', 35.1, 8, 36, '2024-04-05', '07:57:14');
INSERT INTO `confirmation` VALUES (8, 11, 'zz', 'xx', '海南省', '三亚市', '海棠区', '4', 120.5, 3.5, 338, '2024-04-06', '03:07:27');
INSERT INTO `confirmation` VALUES (17, 12, 'zz', 'xx', '海南省', '三亚市', '海棠区', '3', 20.5, 9.6, 42.3, '2024-05-07', '11:42:40');
INSERT INTO `confirmation` VALUES (18, 13, 'zz', 'xx', '海南省', '三亚市', '海棠区', '6', 68.6, 13, 55.5, '2024-06-07', '11:44:52');
INSERT INTO `confirmation` VALUES (19, 14, 'xx', 'xx', 'string', '武汉市', 'string', '2', 0, 0, 0, '2024-06-13', '12:55:09');

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information`  (
  `information_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'AQI信息id',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `community` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `pollution_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '预估污染水平（1~6数字）',
  `date` date NULL DEFAULT NULL COMMENT '日期',
  `time` time(0) NULL DEFAULT NULL COMMENT '时间',
  `state` int(0) NULL DEFAULT 0 COMMENT '反馈表状态（0未委派 1已委派 2已完成）',
  `supervisor_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '监督员id',
  `inspector_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格员id',
  `feedback` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈信息',
  `deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除（0未删除 1已删除）',
  PRIMARY KEY (`information_id`) USING BTREE,
  INDEX `inspectorId`(`inspector_id`) USING BTREE,
  INDEX `supervisor_id`(`supervisor_id`) USING BTREE,
  CONSTRAINT `information_ibfk_1` FOREIGN KEY (`supervisor_id`) REFERENCES `supervisor` (`tel_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `inspectorId` FOREIGN KEY (`inspector_id`) REFERENCES `inspector` (`tel_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of information
-- ----------------------------
INSERT INTO `information` VALUES (3, '海南省', '海口市', '秀英区', '2', '2024-03-13', '07:57:11', 1, 'xj', 'zz', '无', 0);
INSERT INTO `information` VALUES (4, '山西省', '太原市', '小店区', '3', '2024-03-27', '07:57:11', 1, 'xj', 'zz', '无', 0);
INSERT INTO `information` VALUES (5, '湖北省', '武汉市', '洪山区', '4', '2024-04-17', '07:57:11', 2, 'xj', 'zz', '无', 0);
INSERT INTO `information` VALUES (10, 'string', 'string', 'string', '5', '2024-04-18', '11:39:46', 1, 'xj', 'zz', 'string', 0);
INSERT INTO `information` VALUES (11, 'string', 'string', 'string', '4', '2024-05-24', '11:39:46', 2, 'xj', 'zz', 'string', 0);
INSERT INTO `information` VALUES (12, '辽宁省', '沈阳市', 'xx', '1', '2024-05-15', '10:14:36', 0, 'xx', 'xx', 'xx', 0);
INSERT INTO `information` VALUES (13, '海南省', '海口市', 'aa', '6', '2024-05-26', '10:14:42', 0, 'xx', 'xx', 'ss', 0);
INSERT INTO `information` VALUES (14, 'string', 'string', 'string', '5', '2024-05-30', '12:54:39', 0, 'xx', 'xx', 'xx', 0);
INSERT INTO `information` VALUES (15, '辽宁省', '沈阳市', '11', '1', '2024-06-13', '10:14:44', 0, 'xx', 'xx', 'aa', 0);
INSERT INTO `information` VALUES (16, '辽宁省', '沈阳市', '11', '1', '2024-06-13', '10:14:45', 0, 'xx', 'xx', '11', 0);
INSERT INTO `information` VALUES (17, '辽宁省', '沈阳市', '11', '1', '2024-06-13', '10:14:46', 0, 'xx', 'xx', '11', 0);
INSERT INTO `information` VALUES (18, '辽宁省', '沈阳市', 'aa', '2', '2024-06-13', '10:14:47', 0, 'xx', 'xx', 'ss', 0);
INSERT INTO `information` VALUES (19, '辽宁省', '沈阳市', 'aa', '1', '2024-06-13', '14:59:57', 0, 'xx', 'xx', '11', 0);
INSERT INTO `information` VALUES (20, '海南省', '海口市', '11', '6', '2024-06-13', '15:03:07', 0, 'xx', 'xx', '11', 0);
INSERT INTO `information` VALUES (21, '辽宁省', '大连市', 'aa', '4', '2024-06-13', '15:38:12', 0, 'xx', 'xx', 'aa', 0);
INSERT INTO `information` VALUES (22, '河北省', '石家庄市', 'qq', '6', '2024-06-14', '09:36:54', 0, 'xx', 'xx', 'qq', 0);

-- ----------------------------
-- Table structure for inspector
-- ----------------------------
DROP TABLE IF EXISTS `inspector`;
CREATE TABLE `inspector`  (
  `tel_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网格员电话号码（作为主键,作为登录账号）',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格员密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格员姓名',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格员所属省',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格员所属市区',
  PRIMARY KEY (`tel_id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inspector
-- ----------------------------
INSERT INTO `inspector` VALUES ('123456', '123456', '张三', '海南省', '三亚市');
INSERT INTO `inspector` VALUES ('222222', '123456', '李四', '湖北省', '武汉市');
INSERT INTO `inspector` VALUES ('333333', '123456', '王五', '北京市', '北京市');
INSERT INTO `inspector` VALUES ('string', 'string', 'string', '辽宁省', '沈阳市');
INSERT INTO `inspector` VALUES ('xj', 'xj', 'xj', '辽宁省', '沈阳市');
INSERT INTO `inspector` VALUES ('xx', 'xx', 'xx', '辽宁省', '沈阳市');
INSERT INTO `inspector` VALUES ('zz', 'zz', 'zz', '辽宁省', '沈阳市');

-- ----------------------------
-- Table structure for provincialCapital
-- ----------------------------
DROP TABLE IF EXISTS `provincialCapital`;
CREATE TABLE `provincialCapital`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of provincialCapital
-- ----------------------------
INSERT INTO `provincialCapital` VALUES (1, '北京市');
INSERT INTO `provincialCapital` VALUES (2, '天津市');
INSERT INTO `provincialCapital` VALUES (3, '上海市');
INSERT INTO `provincialCapital` VALUES (4, '重庆市');
INSERT INTO `provincialCapital` VALUES (5, '石家庄市');
INSERT INTO `provincialCapital` VALUES (6, '太原市');
INSERT INTO `provincialCapital` VALUES (7, '西安市');
INSERT INTO `provincialCapital` VALUES (8, '济南市');
INSERT INTO `provincialCapital` VALUES (9, '郑州市');
INSERT INTO `provincialCapital` VALUES (10, '沈阳市');
INSERT INTO `provincialCapital` VALUES (11, '长春市');
INSERT INTO `provincialCapital` VALUES (12, '哈尔滨市');
INSERT INTO `provincialCapital` VALUES (13, '南京市');
INSERT INTO `provincialCapital` VALUES (14, '杭州市');
INSERT INTO `provincialCapital` VALUES (15, '合肥市');
INSERT INTO `provincialCapital` VALUES (16, '南昌市');
INSERT INTO `provincialCapital` VALUES (17, '福州市');
INSERT INTO `provincialCapital` VALUES (18, '武汉市');
INSERT INTO `provincialCapital` VALUES (19, '长沙市');
INSERT INTO `provincialCapital` VALUES (20, '成都市');
INSERT INTO `provincialCapital` VALUES (21, '贵阳市');
INSERT INTO `provincialCapital` VALUES (22, '昆明市');
INSERT INTO `provincialCapital` VALUES (23, '广州市');
INSERT INTO `provincialCapital` VALUES (24, '海口市');
INSERT INTO `provincialCapital` VALUES (25, '兰州市');
INSERT INTO `provincialCapital` VALUES (26, '西宁市');
INSERT INTO `provincialCapital` VALUES (27, '台北市');
INSERT INTO `provincialCapital` VALUES (28, '呼和浩特市');
INSERT INTO `provincialCapital` VALUES (29, '乌鲁木齐市');
INSERT INTO `provincialCapital` VALUES (30, '拉萨市');
INSERT INTO `provincialCapital` VALUES (31, '南宁市');
INSERT INTO `provincialCapital` VALUES (32, '银川市');
INSERT INTO `provincialCapital` VALUES (33, '香港市');
INSERT INTO `provincialCapital` VALUES (34, '澳门市');

-- ----------------------------
-- Table structure for supervisor
-- ----------------------------
DROP TABLE IF EXISTS `supervisor`;
CREATE TABLE `supervisor`  (
  `tel_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公众监督员编号(即手机号码)',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公众监督员登录密码',
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公众监督员真实姓名',
  `birthday` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公众监督员出生日期',
  `sex` int(0) NOT NULL DEFAULT 1 COMMENT '公众监督员性别(1:男;0:女)',
  `remarks` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`tel_id`) USING BTREE,
  INDEX `real_name`(`real_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supervisor
-- ----------------------------
INSERT INTO `supervisor` VALUES ('123445', 'string', '123456', 'string', 0, 'string');
INSERT INTO `supervisor` VALUES ('18976414179', '123', '小龙虾', '2022', 1, 'string');
INSERT INTO `supervisor` VALUES ('23', '12', 'xxxx', '2021', 1, '');
INSERT INTO `supervisor` VALUES ('345345', '345345', '345345', '345345', 1, '34534');
INSERT INTO `supervisor` VALUES ('aa', 'aa', 'aa', '2023', 1, '');
INSERT INTO `supervisor` VALUES ('string', 'string', 'string', 'string', 0, 'string');
INSERT INTO `supervisor` VALUES ('string111', 'string', 'string', 'string', 0, 'string');
INSERT INTO `supervisor` VALUES ('string33', 'string', 'string', 'string', 0, 'string');
INSERT INTO `supervisor` VALUES ('xj', 'xj', 'xj', 'xj', 1, 'xj');
INSERT INTO `supervisor` VALUES ('xx', 'xx', 'xx', 'xx', 1, 'xx');
INSERT INTO `supervisor` VALUES ('zz', 'zz', 'zz', 'zz', 1, 'zz');

SET FOREIGN_KEY_CHECKS = 1;
