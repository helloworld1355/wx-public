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

 Date: 22/03/2024 21:03:07
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
  `firm_establish_date` date NULL DEFAULT NULL COMMENT '@desc 公司成立日期\r\n@sample  \"2021-01-01 00:00:00\"',
  `firm_business_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司营业范围\r\n@sample  科技、餐饮',
  `firm_tax_belong` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司归属税局\r\n@sample  廊坊市税务局',
  `firm_contacts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司联系人姓名\r\n@sample  张三',
  `firm_contacts_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  公司联系人电话\r\n@sample  12345678910',
  `firm_price_transfer` float NULL DEFAULT NULL COMMENT '@desc  公司转让价格(单位：元）\r\n@sample  2000',
  `firm_regist_capital` float NULL DEFAULT NULL COMMENT '@desc  公司注册资本\r\n@sample  300.02',
  `firm_status_transfer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '@desc  暂不使用；公司转让状态',
  `create_time` datetime NULL DEFAULT NULL COMMENT '@desc  暂不使用；创建字段时间',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '@desc  暂不使用；最后修改字段时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of firm_info
-- ----------------------------
INSERT INTO `firm_info` VALUES (0000000006, '廊坊科技有限公司', '河北省-廊坊市-广阳区', '爱民东道133号北华航天工业学院', '科技', '廊坊市税务局', '2005-05-12', '教育、餐饮', '廊坊市税务局', '管理员', '18114426636', 4800, 5000, NULL, '2024-03-19 11:16:52', NULL);
INSERT INTO `firm_info` VALUES (0000000007, 'test', '北京市-市辖区-东城区', 'test', '科技', '性质1', '2000-01-01', 'se', '123', '123', '123', 123, 123, NULL, '2024-03-22 09:26:54', NULL);

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
INSERT INTO `init_config` VALUES (1, '科技,法律,美容,', '一般纳税人,', '../../static/image/4.png,,http://bilibili.com;;../../static/image/4.png,,http://bilibili.com;;../../static/image/4.png,,http://bilibili.com;;', '1年,2年,3年及之后,');

SET FOREIGN_KEY_CHECKS = 1;
