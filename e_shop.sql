/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : e_shop

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 04/03/2023 23:12:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `userId` varchar(42) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户Id',
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `money` decimal(22, 2) NULL DEFAULT NULL COMMENT '用户余额',
  `payPwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付密码',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('100001', '张三', '4343434', 9000.00, '834835');

-- ----------------------------
-- Table structure for wallet_details
-- ----------------------------
DROP TABLE IF EXISTS `wallet_details`;
CREATE TABLE `wallet_details`  (
  `wdId` varchar(42) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '钱包余额明细Id',
  `userId` varchar(42) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户Id',
  `money` decimal(22, 2) NULL DEFAULT NULL COMMENT '余额',
  `change` decimal(22, 2) NULL DEFAULT NULL COMMENT '变动金额',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源和流向',
  `changeTime` datetime NULL DEFAULT NULL COMMENT '变动时间',
  PRIMARY KEY (`wdId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wallet_details
-- ----------------------------
INSERT INTO `wallet_details` VALUES ('2959eb70-1eb9-405e-b39f-070372da196d', '100001', 2200.00, -6800.00, '购买华为手机', '2023-03-04 23:04:07');
INSERT INTO `wallet_details` VALUES ('2b9ee4b4-e523-4466-8a73-8ad732a9829c', '100001', 9000.00, 6800.00, '购买的华为手机退款', '2023-03-04 23:07:58');
INSERT INTO `wallet_details` VALUES ('ef1332ba-30e7-4280-b11f-d435f1394060', '100001', 10000.00, -1000.00, '买家具消费1000', '2023-02-02 15:38:14');
INSERT INTO `wallet_details` VALUES ('ef1332ba-30e7-4280-b11f-d435f1394066', '100001', 9000.00, -1000.00, '消费1000', '2023-03-04 15:36:46');

SET FOREIGN_KEY_CHECKS = 1;
