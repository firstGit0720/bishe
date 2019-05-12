/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : localhost:3309
 Source Schema         : bishe

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 12/05/2019 22:35:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for indent_message
-- ----------------------------
DROP TABLE IF EXISTS `indent_message`;
CREATE TABLE `indent_message`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `indent_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建的时间',
  `indent_from` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发位置',
  `indent_arrive` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达位置',
  `seat_type` int(10) NULL DEFAULT NULL COMMENT '座位等级',
  `is_payment` smallint(5) NULL DEFAULT NULL COMMENT '是否付款：0未付款，1付款，2退票已退款',
  `is_status` smallint(5) NULL DEFAULT NULL COMMENT '信息标记：0未出票，1已出票，2已改签，3已退票',
  `is_success` smallint(5) NULL DEFAULT NULL COMMENT '是否完成行程：0未完成，1完成',
  `train_start_time` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '火车处罚的时间',
  `user_id` bigint(10) NULL DEFAULT NULL COMMENT '用户id',
  `train_id` bigint(20) NULL DEFAULT NULL COMMENT '火车id',
  `seat_message` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座位信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of indent_message
-- ----------------------------
INSERT INTO `indent_message` VALUES (1, '2019-05-10 15:54:53', '北京', '廊坊北', 8, 1, 2, 0, '2019-05-10 7:15', 2, 2, '1号车厢001号座位');
INSERT INTO `indent_message` VALUES (2, '2019-05-10 17:42:52', '北京', '天津', 8, 1, 0, 0, '2019-05-11 7:15', 2, 2, '1号车厢001号座位');
INSERT INTO `indent_message` VALUES (4, '2019-05-12 20:13:56', '北京', '廊坊北', 8, 1, 0, 0, '2019-05-12 7:15', 1, 2, '1号车厢001号座位');

-- ----------------------------
-- Table structure for train
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `train_card` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车次编号',
  `train_from` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出发地点',
  `train_from_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出发时间',
  `train_arrive` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目的地',
  `train_arrive_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '到达时间',
  `train_after` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '历时多长',
  `train_seat` bigint(20) NOT NULL COMMENT '座位总数',
  `train_status` smallint(6) NULL DEFAULT 0 COMMENT '运行状态，0正在运行，1停运',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train
-- ----------------------------
INSERT INTO `train` VALUES (1, 'k7729', '北京', '09:13', '德州', '15:28', '6小时15分钟', 1800, 1);
INSERT INTO `train` VALUES (2, 'k1330', '北京', '7:15', '天津', '9:30', '2小时15分钟', 2500, 0);

-- ----------------------------
-- Table structure for train_arrive
-- ----------------------------
DROP TABLE IF EXISTS `train_arrive`;
CREATE TABLE `train_arrive`  (
  `id` bigint(60) NOT NULL AUTO_INCREMENT,
  `train_id` bigint(60) NOT NULL COMMENT '火车id',
  `train_arrive` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中间的站点',
  `train_arrive_wait` int(11) NULL DEFAULT NULL COMMENT '该站点停留的时间',
  `train_arrive_time` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达的时间',
  `train_after` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达该站点的时间',
  `othrt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  `train_arrive_grade` smallint(11) NULL DEFAULT NULL COMMENT '站点的等级',
  `train_arrive_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '到达该站点的额、票价',
  `status` smallint(11) NULL DEFAULT 0 COMMENT '状态0正常运行，1停运',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train_arrive
-- ----------------------------
INSERT INTO `train_arrive` VALUES (1, 1, '廊坊北', 4, '10:10', '57分钟', NULL, 1, NULL, 0);
INSERT INTO `train_arrive` VALUES (2, 1, '天津西', 3, '11:14', '2小时1分钟', NULL, 2, NULL, 0);
INSERT INTO `train_arrive` VALUES (3, 1, '杨柳青', 3, '11:32', '2小时19分钟', NULL, 3, NULL, 0);
INSERT INTO `train_arrive` VALUES (4, 1, '静海', 3, '11:54', '2小时41分钟', NULL, 4, NULL, 0);
INSERT INTO `train_arrive` VALUES (5, 1, '青县', 5, '12:23', '3小时10分钟', NULL, 5, NULL, 0);
INSERT INTO `train_arrive` VALUES (6, 1, '泊头', 3, '13:17', '4小时4分钟', NULL, 6, NULL, 0);
INSERT INTO `train_arrive` VALUES (7, 2, '廊坊北', 5, '8:10', '55分钟', NULL, 1, NULL, 0);
INSERT INTO `train_arrive` VALUES (8, 2, '燕郊', 3, '8:45', '1小时30分钟', NULL, 2, NULL, 0);

-- ----------------------------
-- Table structure for train_seat
-- ----------------------------
DROP TABLE IF EXISTS `train_seat`;
CREATE TABLE `train_seat`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `train_id` bigint(20) NOT NULL COMMENT '火车id',
  `seat_best_enery` bigint(20) NULL DEFAULT NULL COMMENT '每节车厢的座位数',
  `seat_best_carriage` bigint(20) NULL DEFAULT NULL COMMENT '特等座多少节车厢',
  `seat_best_num` bigint(20) NULL DEFAULT NULL COMMENT '特等座的总数量',
  `seat_best_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '特等座价格',
  `seat_first_enery` bigint(20) NULL DEFAULT NULL COMMENT '一等做每节车厢的数量',
  `seat_first_num` bigint(20) NULL DEFAULT NULL COMMENT '一等座的总数量',
  `seat_first_carriage` bigint(20) NULL DEFAULT NULL COMMENT '一等座缩少节车厢',
  `seat_first_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '一等座价格',
  `seat_second_enery` bigint(20) NULL DEFAULT NULL COMMENT '二等座每节车厢的数量',
  `seat_second_carriage` bigint(20) NULL DEFAULT NULL COMMENT '二等座多少节车箱',
  `seat_second_num` bigint(20) NULL DEFAULT NULL COMMENT '二等座的数量',
  `seat_second_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '二等座价格',
  `sleeper_first_soft_carriage` bigint(20) NULL DEFAULT NULL COMMENT '软卧一等卧多少节车箱',
  `sleeper_first_soft_enery` bigint(20) NULL DEFAULT NULL COMMENT '软卧一等卧每节车厢的数量',
  `sleeper_first_soft_num` bigint(20) NULL DEFAULT NULL COMMENT '软卧一等卧的数量',
  `sleeper_first_soft_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '软卧一等卧的价格',
  `sleeper_best_soft_enery` bigint(20) NULL DEFAULT NULL COMMENT '高级软卧的每节车厢的数量',
  `sleeper_best_soft_carriage` bigint(20) NULL DEFAULT NULL COMMENT '高级软卧多少节车箱',
  `sleeper_best_soft_num` bigint(20) NULL DEFAULT NULL COMMENT '高级软卧的数量',
  `sleeper_best_soft_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '高级软卧的价格',
  `sleeper_sport_enery` bigint(20) NULL DEFAULT NULL COMMENT '动卧的每节车厢的数量',
  `sleeper_sport_carriage` bigint(20) NULL DEFAULT NULL COMMENT '动卧有多少节车厢',
  `sleeper_sport_num` bigint(20) NULL DEFAULT NULL COMMENT '动卧的数量',
  `sleeper_sport_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '动卧的价格',
  `sleeper_stiff_enery` bigint(20) NULL DEFAULT NULL COMMENT '硬卧的每节车厢的数量',
  `sleeper_stiff_carriage` bigint(20) NULL DEFAULT NULL COMMENT '硬卧有多少节车厢',
  `sleeper_stiff_num` bigint(20) NULL DEFAULT NULL COMMENT '硬卧的数量',
  `sleeper_stiff_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '硬卧的价格',
  `seat_soft_enery` bigint(20) NULL DEFAULT NULL COMMENT '软座的每节车厢的数量',
  `seat_soft_carriage` bigint(20) NULL DEFAULT NULL COMMENT '软座的车厢数',
  `seat_soft_num` bigint(20) NULL DEFAULT NULL COMMENT '软座的数量',
  `seat_soft_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '软座的价格',
  `seat_stiff_enery` bigint(20) NULL DEFAULT NULL COMMENT '硬座的每节车厢的数量',
  `seat_stiff_carriage` bigint(20) NULL DEFAULT NULL COMMENT '硬座的车厢数',
  `seat_stiff_num` bigint(20) NULL DEFAULT NULL COMMENT '硬座的数量',
  `seat_stiff_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '硬座的价格',
  `seat_no_enery` bigint(20) NULL DEFAULT NULL COMMENT '无座每节车厢的数量',
  `seat_no_carriage` bigint(20) NULL DEFAULT NULL COMMENT '无座的车厢数',
  `seat_no_num` bigint(20) NULL DEFAULT NULL COMMENT '无座的数量',
  `seat_no_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '无座的价格',
  `seat_other_enery` bigint(20) NULL DEFAULT 0 COMMENT '其他座位的每节车厢的数量',
  `seat_other_num` bigint(20) NULL DEFAULT NULL COMMENT '其他',
  `seat_other_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '其他的价格',
  `seat_other_carriage` bigint(20) NULL DEFAULT NULL COMMENT '其他的车厢数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train_seat
-- ----------------------------
INSERT INTO `train_seat` VALUES (2, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 5, 500, 22.30, 100, 10, 1000, 12.50, 100, 10, 1000, 12.50, 0, NULL, NULL, NULL);
INSERT INTO `train_seat` VALUES (6, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, 3, 300, 35.50, NULL, NULL, NULL, NULL, 150, 10, 1500, 12.50, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for train_seat_message
-- ----------------------------
DROP TABLE IF EXISTS `train_seat_message`;
CREATE TABLE `train_seat_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `train_id` bigint(20) NOT NULL COMMENT '火车id',
  `back_change_message` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '退票或改签的信息',
  `train_from` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出发地',
  `train_arrive` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目的地',
  `train_time` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出发时间',
  `seat_type` int(11) NOT NULL COMMENT '座位等级',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '是否已卖出0：未卖出，1：已卖出',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train_seat_message
-- ----------------------------
INSERT INTO `train_seat_message` VALUES (1, 2, '1号车厢001号座位', '北京', '廊坊北', '2019-05-10 7:15', 8, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户注册名',
  `user_pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_sex` smallint(6) NOT NULL COMMENT '性别：0男，1：女',
  `user_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `user_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `user_birthday` date NOT NULL COMMENT '出身日期',
  `user_phone` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户手机号',
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `user_age` int(11) NOT NULL COMMENT '用户年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '李刚', 0, '北京', '', '1996-03-01', '15028409088', '1041189691@qq.com', 23);
INSERT INTO `user` VALUES (2, 'test1', '测试1号', 0, '廊坊', '', '1997-05-06', '15833234589', '', 22);

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES (1, 1, 'ISMvKXpXpadDiUoOSoAfww==');
INSERT INTO `user_password` VALUES (2, 2, '4QrcOUm6Wau+VuBX8g+IPg==');

-- ----------------------------
-- Table structure for user_ticket_message
-- ----------------------------
DROP TABLE IF EXISTS `user_ticket_message`;
CREATE TABLE `user_ticket_message`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(11) NOT NULL COMMENT '用户id',
  `train_id` bigint(11) NOT NULL COMMENT '火车id',
  `seat_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '座位信息',
  `is_payment` smallint(6) NOT NULL DEFAULT 0 COMMENT '是否付款，0未支付，1支付，默认为0',
  `is_use_ticket` smallint(6) NOT NULL DEFAULT 0 COMMENT '是否出票，0未出票，1出票，默认为0',
  `is_change_ticket` smallint(6) NOT NULL DEFAULT 0 COMMENT '是否改签，0未改签，1改签，默认为0',
  `is_back_ticket` smallint(6) NOT NULL DEFAULT 0 COMMENT '是否退票，0未退票，1退票，默认为0',
  `train_date` datetime(0) NOT NULL COMMENT '出行时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_type
-- ----------------------------
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增长',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_type` smallint(6) NOT NULL COMMENT '用户权限，0：管理员，1，票务人员，2：普通用户',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '用户创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '权限修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_type
-- ----------------------------
INSERT INTO `user_type` VALUES (1, 1, 0, '2019-05-10 00:30:30', '2019-05-10 00:29:58');
INSERT INTO `user_type` VALUES (2, 2, 1, '2019-05-10 10:24:11', '2019-05-10 09:55:29');

SET FOREIGN_KEY_CHECKS = 1;
