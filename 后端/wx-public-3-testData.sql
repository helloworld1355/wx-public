/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50743 (5.7.43-log)
 Source Host           : localhost:3306
 Source Schema         : wx-public

 Target Server Type    : MySQL
 Target Server Version : 50743 (5.7.43-log)
 File Encoding         : 65001

 Date: 27/03/2024 13:02:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for firm_info
-- ----------------------------
DROP TABLE IF EXISTS `firm_info`;
CREATE TABLE `firm_info`  (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '@desc 主键id',
  `firm_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司名称\r\n@sample  xxx科技公司',
  `firm_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司位置\r\n@sample 河北省-廊坊市-广阳区 ',
  `firm_location_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司位置详细地址\r\n@sample  爱民东道133号北华航天工业学院',
  `firm_sector_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司行业性质\r\n@sample  科技',
  `firm_taxable_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司纳税性质\r\n@sample  一般纳税',
  `firm_establish_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc 公司成立日期\r\n@sample  1年',
  `firm_business_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司营业范围\r\n@sample  科技、餐饮',
  `firm_tax_belong` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司归属税局\r\n@sample  廊坊市税务局',
  `firm_contacts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司联系人姓名\r\n@sample  张三',
  `firm_contacts_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司联系人电话\r\n@sample  12345678910',
  `firm_price_transfer` float NULL DEFAULT NULL COMMENT '@desc  公司转让价格(单位：元）\r\n@sample  2000',
  `firm_regist_capital` float NULL DEFAULT NULL COMMENT '@desc  公司注册资本\r\n@sample  300.02',
  `firm_status_transfer` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '@desc  公司转让状态；1为已转让，0为未转让\r\n@sample 1',
  `create_time` datetime NULL DEFAULT NULL COMMENT '@desc  创建字段时间\r\n@sample  1年',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '@desc  暂不使用；最后修改字段时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of firm_info
-- ----------------------------
INSERT INTO `firm_info` VALUES (0000000006, '廊坊科技有限公司', '河北省-廊坊市-广阳区', '爱民东道133号北华航天工业学院', '科技', '廊坊市税务局', '1年', '教育、餐饮', '廊坊市税务局', '管理员', '18114426636', 4800, 5000, 0, '2024-03-19 11:16:52', NULL);
INSERT INTO `firm_info` VALUES (0000000007, 'test', '北京市-市辖区-东城区', 'test', '科技', '性质1', '2年', 'se', '123', '123', '123', 123, 123, 0, '2024-03-22 09:26:54', NULL);
INSERT INTO `firm_info` VALUES (0000000008, '123', '北京市-市辖区-东城区', '', '', '', '3年', '', '', '', '', NULL, NULL, 0, '2024-03-23 04:01:47', NULL);
INSERT INTO `firm_info` VALUES (0000000013, '科技公司', '北京市-市辖区-东城区', '111', '法律', '', '2年', '333', '444', '555', '666', 777, 222, 0, '2024-03-23 05:37:20', NULL);
INSERT INTO `firm_info` VALUES (0000000014, '科技公司', '北京市-市辖区-东城区', '111', '美容', '一般纳税人', '2年', '333', '444', '555', '666', 777, 222, 0, '2024-03-23 05:40:06', NULL);
INSERT INTO `firm_info` VALUES (0000000015, '123', '北京市-市辖区-东城区', '123', '科技', '一般纳税人', '3年及之后', '23', '', '12312', '312', 3, 1231, 0, '2024-03-23 05:49:41', NULL);
INSERT INTO `firm_info` VALUES (0000000016, '222', '北京市-市辖区-东城区', '222', '科技', '一般纳税人', '1年', '2222', '2222', '22', '22', 222, 22, 0, '2024-03-24 01:58:12', NULL);
INSERT INTO `firm_info` VALUES (0000000018, '123', '北京市-市辖区-东城区', '1232', '科技', '一般纳税人', '1年', '3', '123', '123', '12312', 3, 1312, 0, '2024-03-26 09:24:15', NULL);
INSERT INTO `firm_info` VALUES (0000000019, '', '北京市-市辖区-东城区', '', '科技', '一般纳税人', '1年', '', '', '', '12345667778', NULL, NULL, 0, '2024-03-26 10:36:40', NULL);
INSERT INTO `firm_info` VALUES (0000000020, '测试科技公司', '北京市-市辖区-东城区', '无', '美容', '一般纳税人', '1年', '科技、教育', '无', '张先生', '13559366077', NULL, 200, 0, '2024-03-26 12:42:57', NULL);
INSERT INTO `firm_info` VALUES (0000000021, '测试科技公司', '北京市-市辖区-东城区', '无', '美容', '一般纳税人', '1年', '科技、教育', '无', '张先生', '13559366077', NULL, 200, 0, '2024-03-26 12:52:24', NULL);
INSERT INTO `firm_info` VALUES (0000000022, '123', '北京市-市辖区-东城区', '', '科技', '一般纳税人', '3年及之后', '', '', '111', '12312312312', NULL, 111, 0, '2024-03-26 12:54:01', NULL);
INSERT INTO `firm_info` VALUES (0000000023, '123', '北京市-市辖区-东城区', '', '科技', '一般纳税人', '3年及之后', '', '', '111', '12312312312', NULL, 111, 0, '2024-03-26 12:54:32', NULL);
INSERT INTO `firm_info` VALUES (0000000024, '123', '河北省-石家庄市-长安区', '', '科技', '一般纳税人', '1年', '', '', '123', '12312312312', NULL, 11, 0, '2024-03-26 12:55:41', NULL);
INSERT INTO `firm_info` VALUES (0000000025, '123', '河北省-石家庄市-长安区', '', '科技', '一般纳税人', '1年', '', '', '123', '12312312312', NULL, 11, 0, '2024-03-26 12:56:13', NULL);

-- ----------------------------
-- Table structure for firm_purchase
-- ----------------------------
DROP TABLE IF EXISTS `firm_purchase`;
CREATE TABLE `firm_purchase`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `firm_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司位置\r\n@sample 河北省-廊坊市-广阳区 ',
  `firm_sector_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司行业性质\r\n@sample  科技',
  `firm_taxable_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司纳税性质\r\n@sample  一般纳税',
  `firm_establish_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc 公司成立日期\r\n@sample  1年',
  `firm_contacts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司联系人姓名\r\n@sample  张三',
  `firm_contacts_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司联系人电话\r\n@sample  12345678910',
  `firm_price_transfer` double NULL DEFAULT NULL COMMENT '@desc  公司转让价格(单位：元）\r\n@sample  2000',
  `firm_status_transfer` int(11) NULL DEFAULT NULL COMMENT '@desc  公司转让状态；1为已转让，0为未转让\r\n@sample 1',
  `create_time` datetime NULL DEFAULT NULL COMMENT '@desc  创建字段时间\r\n@sample  1年',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '@desc  暂不使用；最后修改字段时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of firm_purchase
-- ----------------------------
INSERT INTO `firm_purchase` VALUES (00000000009, '北京市-市辖区-东城区', '科技', '一般纳税人', '1年', '123', '12312', 3, 0, '2024-03-26 09:30:20', NULL);
INSERT INTO `firm_purchase` VALUES (00000000010, '全部', '全部', '全部', '全部', 'ttt', 'tt', 12333, 0, '2024-03-26 10:26:41', NULL);

-- ----------------------------
-- Table structure for firm_show
-- ----------------------------
DROP TABLE IF EXISTS `firm_show`;
CREATE TABLE `firm_show`  (
  `id` int(11) NOT NULL COMMENT '@desc  公司id，与公司信息id相同',
  `firm_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司名称\r\n@sample  xxx科技公司',
  `firm_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司位置\r\n@sample 河北省-廊坊市-广阳区 ',
  `create_time` datetime NULL DEFAULT NULL COMMENT '@desc  创建字段时间\r\n@sample  1年',
  `firm_sector_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司行业性质\r\n@sample  科技',
  `firm_taxable_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司纳税性质\r\n@sample  一般纳税',
  `firm_establish_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc 公司成立日期\r\n@sample  1年',
  `firm_status_transfer` int(11) NULL DEFAULT NULL COMMENT '@desc  公司转让状态，0表示未转让，1表示已转让',
  `firm_price_transfer` float NULL DEFAULT NULL COMMENT '@desc  公司转让价格(单位：元）\r\n@sample  2000',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of firm_show
-- ----------------------------
INSERT INTO `firm_show` VALUES (6, '廊坊科技有限公司', '河北省-廊坊市-广阳区', '2024-03-19 11:16:52', '科技', '廊坊市税务局', '3年及以上', 0, 2000);
INSERT INTO `firm_show` VALUES (7, 'test', '北京市-市辖区-东城区', '2024-03-19 11:16:52', '科技', '性质1', '1年', 0, 3000);
INSERT INTO `firm_show` VALUES (8, '123', '北京市-市辖区-东城区', '2024-03-23 04:01:47', '美容', '性质2', '2年', 1, 1900);
INSERT INTO `firm_show` VALUES (13, '科技公司', '北京市-市辖区-东城区', '2024-03-23 05:37:20', '法律', '', '2年', 0, 777);
INSERT INTO `firm_show` VALUES (14, '科技公司', '北京市-市辖区-东城区', '2024-03-23 05:40:06', '美容', '一般纳税人', '2年', 0, 777);
INSERT INTO `firm_show` VALUES (15, '123', '北京市-市辖区-东城区', '2024-03-23 05:49:41', '科技', '一般纳税人', '3年及之后', 0, 3);
INSERT INTO `firm_show` VALUES (16, '222', '北京市-市辖区-东城区', '2024-03-24 01:58:12', '科技', '一般纳税人', '1年', 0, 222);
INSERT INTO `firm_show` VALUES (18, '123', '北京市-市辖区-东城区', '2024-03-26 09:24:15', '科技', '一般纳税人', '1年', 0, 3);
INSERT INTO `firm_show` VALUES (19, '', '北京市-市辖区-东城区', '2024-03-26 10:36:40', '科技', '一般纳税人', '1年', 0, NULL);
INSERT INTO `firm_show` VALUES (20, '测试科技公司', '北京市-市辖区-东城区', '2024-03-26 12:42:57', '美容', '一般纳税人', '1年', 0, NULL);
INSERT INTO `firm_show` VALUES (21, '测试科技公司', '北京市-市辖区-东城区', '2024-03-26 12:52:24', '美容', '一般纳税人', '1年', 0, NULL);
INSERT INTO `firm_show` VALUES (22, '123', '北京市-市辖区-东城区', '2024-03-26 12:54:01', '科技', '一般纳税人', '3年及之后', 0, NULL);
INSERT INTO `firm_show` VALUES (23, '123', '北京市-市辖区-东城区', '2024-03-26 12:54:32', '科技', '一般纳税人', '3年及之后', 0, NULL);
INSERT INTO `firm_show` VALUES (24, '123', '河北省-石家庄市-长安区', '2024-03-26 12:55:41', '科技', '一般纳税人', '1年', 0, NULL);
INSERT INTO `firm_show` VALUES (25, '123', '河北省-石家庄市-长安区', '2024-03-26 12:56:13', '科技', '一般纳税人', '1年', 0, NULL);

-- ----------------------------
-- Table structure for firm_show_purchase
-- ----------------------------
DROP TABLE IF EXISTS `firm_show_purchase`;
CREATE TABLE `firm_show_purchase`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `firm_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司位置\r\n@sample 河北省-廊坊市-广阳区 ',
  `firm_sector_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司行业性质\r\n@sample  科技',
  `firm_taxable_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司纳税性质\r\n@sample  一般纳税',
  `firm_establish_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc 公司成立日期\r\n@sample  1年',
  `firm_status_transfer` int(11) NULL DEFAULT NULL COMMENT '@desc  公司转让状态；1为已转让，0为未转让\r\n@sample 1',
  `firm_price_transfer` float NULL DEFAULT NULL COMMENT '@desc  公司转让价格(单位：元）\r\n@sample  2000',
  `create_time` datetime NULL DEFAULT NULL COMMENT '@desc  创建字段时间\r\n@sample  1年',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of firm_show_purchase
-- ----------------------------
INSERT INTO `firm_show_purchase` VALUES (00000000009, '北京市-市辖区-东城区', '科技', '一般纳税人', '1年', 0, 3, '2024-03-26 09:30:20');
INSERT INTO `firm_show_purchase` VALUES (00000000010, '全部', '全部', '全部', '全部', 0, 12333, '2024-03-26 10:26:41');

-- ----------------------------
-- Table structure for init_config
-- ----------------------------
DROP TABLE IF EXISTS `init_config`;
CREATE TABLE `init_config`  (
  `id` int(11) NOT NULL,
  `sectors` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  行业类型数组',
  `taxables` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  纳税性质数组',
  `img_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  图片路径以及对应网站url数组',
  `years` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  年份选择器\r\n@sample  1年；2年；3年及之后',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of init_config
-- ----------------------------
INSERT INTO `init_config` VALUES (1, '科技,法律,美容,', '一般纳税人,小规模纳税人,', '../../static/image/4.png,,http://bilibili.com;;../../static/image/4.png,,http://bilibili.com;;../../static/image/4.png,,http://bilibili.com;;', '1年,2年,3年及之后,');

SET FOREIGN_KEY_CHECKS = 1;
