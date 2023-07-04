/*
 Navicat Premium Data Transfer

 Source Server         : review
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : red

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 04/07/2023 17:09:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for red_detail
-- ----------------------------
DROP TABLE IF EXISTS `red_detail`;
CREATE TABLE `red_detail`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `record_id` int(0) NOT NULL COMMENT '红包记录id',
  `amount` decimal(8, 2) NULL DEFAULT NULL COMMENT '金额（单位为分）',
  `is_active` tinyint(0) NULL DEFAULT 1,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 864 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '红包明细金额' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of red_detail
-- ----------------------------
INSERT INTO `red_detail` VALUES (864, 81, 608.00, 1, '2023-07-04 16:38:31');
INSERT INTO `red_detail` VALUES (865, 81, 1396.00, 1, '2023-07-04 16:38:31');
INSERT INTO `red_detail` VALUES (866, 81, 789.00, 1, '2023-07-04 16:38:31');
INSERT INTO `red_detail` VALUES (867, 81, 7066.00, 1, '2023-07-04 16:38:31');
INSERT INTO `red_detail` VALUES (868, 81, 5565.00, 1, '2023-07-04 16:38:31');
INSERT INTO `red_detail` VALUES (869, 81, 1100.00, 1, '2023-07-04 16:38:31');
INSERT INTO `red_detail` VALUES (870, 81, 5586.00, 1, '2023-07-04 16:38:31');
INSERT INTO `red_detail` VALUES (871, 81, 1921.00, 1, '2023-07-04 16:38:32');
INSERT INTO `red_detail` VALUES (872, 81, 1123.00, 1, '2023-07-04 16:38:32');
INSERT INTO `red_detail` VALUES (873, 81, 13845.00, 1, '2023-07-04 16:38:32');
INSERT INTO `red_detail` VALUES (874, 81, 1001.00, 1, '2023-07-04 16:38:32');
INSERT INTO `red_detail` VALUES (875, 82, 2103.00, 1, '2023-07-04 16:41:49');
INSERT INTO `red_detail` VALUES (876, 82, 4812.00, 1, '2023-07-04 16:41:49');
INSERT INTO `red_detail` VALUES (877, 82, 1793.00, 1, '2023-07-04 16:41:49');
INSERT INTO `red_detail` VALUES (878, 82, 1775.00, 1, '2023-07-04 16:41:49');
INSERT INTO `red_detail` VALUES (879, 82, 2900.00, 1, '2023-07-04 16:41:49');
INSERT INTO `red_detail` VALUES (880, 82, 8374.00, 1, '2023-07-04 16:41:49');
INSERT INTO `red_detail` VALUES (881, 82, 5182.00, 1, '2023-07-04 16:41:49');
INSERT INTO `red_detail` VALUES (882, 82, 3831.00, 1, '2023-07-04 16:41:49');
INSERT INTO `red_detail` VALUES (883, 82, 3728.00, 1, '2023-07-04 16:41:49');
INSERT INTO `red_detail` VALUES (884, 82, 1023.00, 1, '2023-07-04 16:41:49');
INSERT INTO `red_detail` VALUES (885, 82, 4479.00, 1, '2023-07-04 16:41:49');

-- ----------------------------
-- Table structure for red_record
-- ----------------------------
DROP TABLE IF EXISTS `red_record`;
CREATE TABLE `red_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `red_packet` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '红包全局唯一标识串',
  `total` int(0) NOT NULL COMMENT '人数',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额（单位为分）',
  `is_active` tinyint(0) NULL DEFAULT 1,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发红包记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of red_record
-- ----------------------------
INSERT INTO `red_record` VALUES (81, 200402, 'redis:red:packet:200402:1658350436554800', 11, 40000.00, 1, '2023-07-04 16:38:31');
INSERT INTO `red_record` VALUES (82, 200402, 'redis:red:packet:200402:1658547519256400', 11, 40000.00, 1, '2023-07-04 16:41:49');

-- ----------------------------
-- Table structure for red_rob_record
-- ----------------------------
DROP TABLE IF EXISTS `red_rob_record`;
CREATE TABLE `red_rob_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户账号',
  `red_packet` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '红包标识串',
  `amount` decimal(8, 2) NULL DEFAULT NULL COMMENT '红包金额（单位为分）',
  `rob_time` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `is_active` tinyint(0) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 346 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '抢红包记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of red_rob_record
-- ----------------------------
INSERT INTO `red_rob_record` VALUES (356, 10032, 'redis:red:packet:200402:1658350436554800', 1100.00, '2023-07-04 16:39:05', 1);
INSERT INTO `red_rob_record` VALUES (357, 10031, 'redis:red:packet:200402:1658350436554800', 7066.00, '2023-07-04 16:39:05', 1);
INSERT INTO `red_rob_record` VALUES (358, 10040, 'redis:red:packet:200402:1658350436554800', 1123.00, '2023-07-04 16:39:05', 1);
INSERT INTO `red_rob_record` VALUES (359, 10037, 'redis:red:packet:200402:1658350436554800', 1921.00, '2023-07-04 16:39:05', 1);
INSERT INTO `red_rob_record` VALUES (360, 10033, 'redis:red:packet:200402:1658350436554800', 5565.00, '2023-07-04 16:39:05', 1);
INSERT INTO `red_rob_record` VALUES (361, 10039, 'redis:red:packet:200402:1658350436554800', 1001.00, '2023-07-04 16:39:05', 1);
INSERT INTO `red_rob_record` VALUES (362, 10030, 'redis:red:packet:200402:1658350436554800', 608.00, '2023-07-04 16:39:05', 1);
INSERT INTO `red_rob_record` VALUES (363, 10036, 'redis:red:packet:200402:1658350436554800', 789.00, '2023-07-04 16:39:05', 1);
INSERT INTO `red_rob_record` VALUES (364, 10035, 'redis:red:packet:200402:1658350436554800', 1396.00, '2023-07-04 16:39:05', 1);
INSERT INTO `red_rob_record` VALUES (365, 10034, 'redis:red:packet:200402:1658350436554800', 5586.00, '2023-07-04 16:39:05', 1);
INSERT INTO `red_rob_record` VALUES (366, 10038, 'redis:red:packet:200402:1658350436554800', 13845.00, '2023-07-04 16:39:05', 1);
INSERT INTO `red_rob_record` VALUES (367, 10041, 'redis:red:packet:200402:1658547519256400', 3728.00, '2023-07-04 16:42:05', 1);
INSERT INTO `red_rob_record` VALUES (368, 10031, 'redis:red:packet:200402:1658547519256400', 5182.00, '2023-07-04 16:42:05', 1);
INSERT INTO `red_rob_record` VALUES (369, 10039, 'redis:red:packet:200402:1658547519256400', 4479.00, '2023-07-04 16:42:05', 1);
INSERT INTO `red_rob_record` VALUES (370, 10036, 'redis:red:packet:200402:1658547519256400', 2103.00, '2023-07-04 16:42:05', 1);
INSERT INTO `red_rob_record` VALUES (371, 10034, 'redis:red:packet:200402:1658547519256400', 8374.00, '2023-07-04 16:42:05', 1);
INSERT INTO `red_rob_record` VALUES (372, 10033, 'redis:red:packet:200402:1658547519256400', 1023.00, '2023-07-04 16:42:05', 1);
INSERT INTO `red_rob_record` VALUES (373, 10040, 'redis:red:packet:200402:1658547519256400', 3831.00, '2023-07-04 16:42:05', 1);
INSERT INTO `red_rob_record` VALUES (374, 10032, 'redis:red:packet:200402:1658547519256400', 2900.00, '2023-07-04 16:42:05', 1);
INSERT INTO `red_rob_record` VALUES (375, 10038, 'redis:red:packet:200402:1658547519256400', 1775.00, '2023-07-04 16:42:05', 1);
INSERT INTO `red_rob_record` VALUES (376, 10035, 'redis:red:packet:200402:1658547519256400', 1793.00, '2023-07-04 16:42:05', 1);
INSERT INTO `red_rob_record` VALUES (377, 10037, 'redis:red:packet:200402:1658547519256400', 4812.00, '2023-07-04 16:42:05', 1);

SET FOREIGN_KEY_CHECKS = 1;
