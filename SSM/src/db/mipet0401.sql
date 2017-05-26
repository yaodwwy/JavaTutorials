/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : mipet

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2016-04-01 16:38:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article_image
-- ----------------------------
DROP TABLE IF EXISTS `article_image`;
CREATE TABLE `article_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `image_url` varchar(512) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`),
  KEY `article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_image
-- ----------------------------

-- ----------------------------
-- Table structure for article_like
-- ----------------------------
DROP TABLE IF EXISTS `article_like`;
CREATE TABLE `article_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `article_id` int(11) DEFAULT NULL COMMENT '文章id',
  `deleted` int(1) DEFAULT '0' COMMENT '0：正常、1：删除',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_like
-- ----------------------------
INSERT INTO `article_like` VALUES ('3', '1', '1', '0', null);
INSERT INTO `article_like` VALUES ('4', '1', '2', '0', null);
INSERT INTO `article_like` VALUES ('59', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('60', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('61', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('62', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('63', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('64', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('65', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('66', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('67', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('68', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('69', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('70', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('71', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('72', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('73', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('74', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('75', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('76', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('77', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('78', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('79', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('80', '2', '1', '0', null);
INSERT INTO `article_like` VALUES ('81', '2', '1', '0', null);

-- ----------------------------
-- Table structure for foster_store
-- ----------------------------
DROP TABLE IF EXISTS `foster_store`;
CREATE TABLE `foster_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '店铺名称',
  `items` varchar(255) DEFAULT NULL COMMENT '服务项目：寄养,美容',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `city` varchar(32) DEFAULT NULL COMMENT '所在城市',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `opening` varchar(128) DEFAULT '0' COMMENT '营业时间',
  `service_info` varchar(2048) DEFAULT NULL COMMENT '服务介绍',
  `min_pic` varchar(512) DEFAULT NULL COMMENT '小图展示',
  `max_pic` varchar(512) DEFAULT NULL COMMENT '大图展示',
  `deleted` int(1) DEFAULT '0' COMMENT '0：正常、1：删除',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foster_store
-- ----------------------------
INSERT INTO `foster_store` VALUES ('2', '噜啦啦宠物店', '美容,寄养', '13852027261', '徐州市', '徐州市民主南路1号', '08:30-21:00', '提供宠物的所有服务', null, null, '0', null);
INSERT INTO `foster_store` VALUES ('3', '拉露露宠物店', '娱乐', '13852027262', '徐州市', '徐州市解放路1号', '08:30-22:00', '提供宠物的所有服务', null, null, '0', null);
INSERT INTO `foster_store` VALUES ('4', '哇挖挖宠物店', '美容,寄养,娱乐', '13852027263', '徐州市', '徐州市中山北路1号', '08:30-22:00', '提供宠物的所有服务', null, null, '0', null);
INSERT INTO `foster_store` VALUES ('5', '特种兵宠物店', '美容,寄养,娱乐', '13852027264', '徐州市', '徐州市中山南路1号', '08:30-22:00', '提供宠物的所有服务', null, null, '0', null);
INSERT INTO `foster_store` VALUES ('6', '啊露露宠物店', '美容,寄养,娱乐', '13852027265', '徐州市', '徐州市某路1号', '08:30-22:00', '提供宠物的所有服务', null, null, '0', null);
INSERT INTO `foster_store` VALUES ('7', '克鲁兹宠物店', '美容,寄养,娱乐', '13852027266', '徐州市', '徐州市某路1号', '08:30-22:00', '提供宠物的所有服务', null, null, '0', null);
INSERT INTO `foster_store` VALUES ('8', '金金宠物店', '美容,寄养,娱乐', '13852027267', '徐州市', '徐州市某路1号', '08:30-22:00', '提供宠物的所有服务', null, null, '1', null);
INSERT INTO `foster_store` VALUES ('9', '小鹏宠物店', '美容,寄养,娱乐', '13852027268', '徐州市', '徐州市某路1号', '08:30-22:00', '提供宠物的所有服务', null, null, '0', null);
INSERT INTO `foster_store` VALUES ('10', '1宠物店', '娱乐', '13852027268', '徐州市', '徐州市某路2号', '08:30-22:00', '提供宠物的所有服务', null, null, '0', null);
INSERT INTO `foster_store` VALUES ('11', '2宠物店', '娱乐', '13852027268', '徐州市', '徐州市某路3号', '08:30-22:00', '提供宠物的所有服务', null, null, '1', null);
INSERT INTO `foster_store` VALUES ('12', '3宠物店', '寄养,娱乐', '13852027268', '徐州市', '徐州市某路4号', '08:30-22:00', '提供宠物的所有服务', null, null, '0', null);

-- ----------------------------
-- Table structure for mipet_image
-- ----------------------------
DROP TABLE IF EXISTS `mipet_image`;
CREATE TABLE `mipet_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(512) DEFAULT NULL COMMENT '图片地址',
  `image_link` varchar(512) DEFAULT NULL COMMENT '图片链接',
  `status` int(1) DEFAULT NULL COMMENT '禁用启用状态0/1',
  `deleted` int(1) DEFAULT NULL COMMENT '是否删除0/1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mipet_image
-- ----------------------------
INSERT INTO `mipet_image` VALUES ('1', 'http://localhost/upload/mipetImages/headback2016-04-01-042707.png', 'articleDetail.html?id=1', '0', '0');
INSERT INTO `mipet_image` VALUES ('2', 'http://localhost/upload/mipetImages/show2016-04-01-042819.png', 'articleDetail.html?id=2', '0', '0');
INSERT INTO `mipet_image` VALUES ('3', 'http://localhost/upload/mipetImages/show2016-04-01-042833.png', 'articleDetail.html?id=3', '0', '0');
INSERT INTO `mipet_image` VALUES ('4', 'http://localhost/upload/mipetImages/shop2016-04-01-042842.png', 'articleDetail.html?id=4', '0', '0');

-- ----------------------------
-- Table structure for shops_goods
-- ----------------------------
DROP TABLE IF EXISTS `shops_goods`;
CREATE TABLE `shops_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `types` int(1) DEFAULT NULL COMMENT '商品所属类型',
  `post_price` int(11) DEFAULT NULL COMMENT '邮费',
  `goods_price` double(11,2) DEFAULT NULL COMMENT '商品价格',
  `position` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `main_image` varchar(512) DEFAULT NULL COMMENT '商品主图',
  `html_content` varchar(5000) DEFAULT NULL COMMENT '商品详情',
  `goods_link` varchar(512) DEFAULT NULL COMMENT '商品链接',
  `enabled` int(1) DEFAULT '0' COMMENT '0：启用、1：拉黑',
  `deleted` int(1) DEFAULT '0' COMMENT '0：正常、1：删除',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shops_goods
-- ----------------------------
INSERT INTO `shops_goods` VALUES ('1', '豆腐干', null, null, null, null, null, '额啊他额他而他认为人为认为', null, null, null, null);
INSERT INTO `shops_goods` VALUES ('2', '豆腐', '1', '99', '34.00', '北京', '../image/ex5.png', '<p>额啊他额他而他认为人为认为</p>\n', '23', '0', null, '2016-04-01 14:02:36');
INSERT INTO `shops_goods` VALUES ('3', '豆腐围绕', '1', '23', '345.00', '北京', '../image/ex5.png', '额啊他额他而他认为人为认为', '23', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('4', '老干妈', '1', '23', '345.00', '北京', '../image/ex5.png', '额啊他额他而他认为人为认为', '23', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('5', '老干爹', '1', '23', '345.00', '北京', '../image/ex5.png', '额啊他额他而他认为人为认为', '23', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('6', '老干爹', '1', '0', '345.00', '北京', '../image/ex5.png', '额啊他额他而他认为人为认为', '23', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('7', '老干爹', '1', '23', '345.00', '北京', 'http://localhost/upload/goodImages/ex62016-04-01-034429.png', '<p>额啊他额他而他认为人为认为</p>\n', 'http://www.baidu.com', '0', null, '2016-04-01 15:44:32');
INSERT INTO `shops_goods` VALUES ('8', '狗粮1', '0', '0', '345.00', '北京', '../image/ex5.png', '额啊他额他而他认为人为认为', '23', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('9', '狗粮2', '0', '23', '345.00', '北京', '../image/ex5.png', '额啊他额他而他认为人为认为', '23', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('10', '狗粮3', '0', '23', '345.00', '徐州', '../image/ex5.png', '额啊他额他而他认为人为认为', '23', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('11', '狗粮4', '0', '0', '345.00', '徐州', '../image/ex5.png', '额啊他额他而他认为人为认为', '23', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('12', '狗粮6', '0', '23', '345.00', '徐州', '../image/ex5.png', '额啊他额他而他认为人为认为', '23', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('13', '狗粮7', '0', '23', '345.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('14', '狗粮8', '0', '23', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('15', '狗粮9', '0', '23', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('16', '狗粮9', '1', '23', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('17', '狗粮10', '1', '0', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('18', '狗粮11', '1', '0', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('19', '狗粮11', '1', '0', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('20', '狗粮11', '1', '0', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('21', '狗粮11', '1', '0', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('22', '狗粮11', '1', '0', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('23', '狗粮11', '1', '0', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('24', '狗粮11', '1', '0', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('25', '狗粮11', '1', '0', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('26', '狗粮12', '0', '22', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('27', '狗粮13', '0', '22', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('28', '狗粮14', '0', '22', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('29', '狗粮15', '0', '22', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');
INSERT INTO `shops_goods` VALUES ('30', '狗粮16', '0', '22', '34.00', '南京', '../image/ex5.png', '狗食中的绝品', '43', '0', '0', '2016-03-23 10:25:19');

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '菜单显示名',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级菜单ID',
  `url` varchar(255) DEFAULT NULL COMMENT '后台URL',
  `web_url` varchar(255) DEFAULT NULL COMMENT 'web链接',
  `image_url` varchar(255) DEFAULT NULL COMMENT '图标地址',
  `sort` int(11) DEFAULT '1',
  `status` int(11) DEFAULT '0' COMMENT '显示状态 0 后台 1 全部 2 前台',
  `creator` varchar(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updator` varchar(11) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(11) DEFAULT NULL COMMENT '是否删除；0：删除；1：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', '首页', '0', '', '../home/home.jsp', null, '1', '2', '027681', '2016-01-14 17:02:25', 'admin', '2016-01-14 17:02:25', '1');
INSERT INTO `t_sys_menu` VALUES ('2', '接入API', '0', '#', '../document/list?id=5', '', '3', '1', '15484', '2015-06-21 12:22:50', 'admin', '2016-01-31 16:25:13', '1');
INSERT INTO `t_sys_menu` VALUES ('3', 'SDK说明', '0', '#', '../sdk/sdk_direction.jsp', null, '4', '2', '15484', null, 'admin', '2016-01-26 13:48:35', '1');
INSERT INTO `t_sys_menu` VALUES ('4', '常见问题', '0', '#', '../platformInfo/common_problems.jsp?type=3', null, '5', '1', '027681', '2016-01-14 17:07:07', 'admin', '2016-01-14 17:07:07', '1');
INSERT INTO `t_sys_menu` VALUES ('5', '订单新建', '2', 'document/menu?id=5', '../document/list?id=5', '', '1', '1', '027681', '2016-01-14 17:06:36', '027681', '2016-01-31 16:13:05', '0');
INSERT INTO `t_sys_menu` VALUES ('6', '订单撤销', '2', 'document/menu?id=6', '../document/list?id=6', '', '2', '1', '027681', '2016-01-14 17:06:23', '027681', '2016-01-31 16:13:10', '0');
INSERT INTO `t_sys_menu` VALUES ('7', '订单修改', '2', 'document/menu?id=7', '../document/list?id=7', '', '3', '1', '027681', '2016-01-14 17:07:07', '027681', '2016-01-31 16:13:14', '0');
INSERT INTO `t_sys_menu` VALUES ('8', '订单查询', '2', 'document/menu?id=8', '../document/list?id=8', '', '4', '1', '027380', '2015-12-16 15:07:14', '027681', '2016-01-31 16:13:21', '0');
INSERT INTO `t_sys_menu` VALUES ('9', '运单跟踪查询', '2', 'document/menu?id=9', '../document/list?id=9', '', '5', '1', '027681', '2016-01-14 17:04:41', '027681', '2016-01-31 16:13:18', '0');
INSERT INTO `t_sys_menu` VALUES ('10', '运单状态推送', '2', 'document/menu?id=10', '../document/list?id=10', '', '6', '1', '027681', '2016-01-14 17:05:51', '027681', '2016-01-31 16:13:25', '0');
INSERT INTO `t_sys_menu` VALUES ('11', 'SDK说明', '3', '', '../sdk/sdk_direction.jsp', '../upload/images/menu_dynamic2016-02-20-044121.png', '1', '2', null, null, '027681', '2016-02-20 16:41:23', '0');
INSERT INTO `t_sys_menu` VALUES ('12', 'SDK下载', '3', '', '../sdk/sdk_download.jsp', '../upload/images/menu_dynamic2016-02-20-044407.png', '2', '2', null, null, '027681', '2016-02-20 16:44:10', '0');
INSERT INTO `t_sys_menu` VALUES ('13', '接口相关问题', '4', 'menu/topage.do?url=sys/platform_info_manage&type=3', '../platformInfo/common_problems.jsp?type=3', null, '1', '1', '15484', null, '11385', '2016-01-25 13:52:20', '0');
INSERT INTO `t_sys_menu` VALUES ('14', '技术相关问题', '4', 'menu/topage.do?url=sys/platform_info_manage&type=4', '../platformInfo/common_problems.jsp?type=4', null, '2', '1', '015484', '2015-12-21 21:21:52', '11385', '2016-01-25 13:50:26', '0');
INSERT INTO `t_sys_menu` VALUES ('15', 'App管理', '0', 'menu/topage.do?url=bd/dict_manage', '', '', '2', '0', '15484', '2015-06-22 10:40:22', 'admin', '2016-03-22 16:03:56', '0');
INSERT INTO `t_sys_menu` VALUES ('16', '注册用户管理', '15', 'menu/topage.do?url=sys/mipetuser_manage', '', '../image/menu_dynamic.png', '1', '0', '027681', '2016-01-14 17:06:50', 'admin', '2016-03-22 16:03:52', '0');
INSERT INTO `t_sys_menu` VALUES ('17', '平台公告', '15', 'menu/topage.do?url=sys/platform_info_manage&type=2', '../platformInfo/platform_info.jsp?type=2', '../image/menu_notice.png', '2', '1', '027681', '2016-01-14 17:06:36', 'admin', '2016-01-20 12:22:22', '1');
INSERT INTO `t_sys_menu` VALUES ('102', '网站设置', '0', '#', null, null, '6', '0', '15484', '2015-06-21 12:22:50', 'admin', '2016-01-14 17:03:52', '1');
INSERT INTO `t_sys_menu` VALUES ('103', '系统管理', '0', '#', null, null, '7', '0', '15484', null, '15484', null, '0');
INSERT INTO `t_sys_menu` VALUES ('104', 'SDK发布管理', '102', 'menu/topage.do?url=sys/sdk_manage', '', null, '1', '0', '027681', '2016-01-14 17:06:23', '027681', '2016-01-14 17:06:23', '0');
INSERT INTO `t_sys_menu` VALUES ('105', '平台功能列表', '102', 'menu/topage.do?url=sys/homepage_manage_new&type=function', '', '', '2', '0', '027380', '2015-12-16 15:07:14', '11385', '2016-01-28 10:59:35', '0');
INSERT INTO `t_sys_menu` VALUES ('106', '合作公司列表', '102', 'menu/topage.do?url=sys/homepage_manage_new&type=partner', '', '', '3', '0', '027681', '2016-01-14 17:04:41', '11385', '2016-02-16 15:12:08', '0');
INSERT INTO `t_sys_menu` VALUES ('107', '首页广告设置', '102', 'menu/topage.do?url=sys/homepage_manage_new&type=ad', '', '', '4', '0', '027681', '2016-01-15 13:16:16', '11385', '2016-02-17 09:15:12', '0');
INSERT INTO `t_sys_menu` VALUES ('108', '用户管理', '103', 'menu/topage.do?url=sys/admin_manage', null, null, '2', '0', '15484', null, '15484', '2015-07-01 11:37:16', '0');
INSERT INTO `t_sys_menu` VALUES ('109', '权限管理', '103', 'menu/topage.do?url=sys/role_manage', null, null, '1', '0', '15484', null, '15484', '2015-07-01 14:17:21', '0');
INSERT INTO `t_sys_menu` VALUES ('110', '菜单管理', '103', 'menu/topage.do?url=sys/menu_manage', null, null, '3', '0', '15484', null, '15484', '2015-07-01 14:17:15', '0');
INSERT INTO `t_sys_menu` VALUES ('111', '接口管理', '103', 'menu/topage.do?url=sys/wb_manage', null, null, '4', '0', '015484', '2015-12-21 21:21:52', 'admin', '2015-12-21 21:21:52', '1');
INSERT INTO `t_sys_menu` VALUES ('112', '数据字典管理', '103', 'menu/topage.do?url=bd/dict_manage', null, null, '5', '0', '15484', '2015-06-22 10:40:22', 'admin', '2016-01-14 17:03:10', '1');
INSERT INTO `t_sys_menu` VALUES ('113', '系统任务管理', '103', 'menu/topage.do?url=sys/job_manage', null, null, '6', '0', '015484', '2015-12-21 08:58:48', 'admin', '2015-12-21 08:59:05', '1');
INSERT INTO `t_sys_menu` VALUES ('114', '接入客户管理', '103', 'menu/topage.do?url=sys/customer_manage', null, null, '7', '0', '027681', '2016-01-15 13:02:39', 'admin', '2016-01-16 19:21:37', '1');
INSERT INTO `t_sys_menu` VALUES ('186', '测试', '0', '#', '#', '', '7', '1', '027681', '2016-01-23 17:06:14', '027681', '2016-01-23 17:06:14', '1');
INSERT INTO `t_sys_menu` VALUES ('187', '快捷导航设置', '2', 'menu/topage.do?url=sys/apinavi_manage', '', '', '7', '0', '027681', '2016-01-23 17:33:55', '027681', '2016-01-23 17:36:35', '0');
INSERT INTO `t_sys_menu` VALUES ('188', '首页平台功能与合作伙伴', '102', 'menu/topage.do?url=sys/homepage_manage', '', '', '5', '0', '11385', '2016-01-28 10:56:16', '11385', '2016-01-28 10:56:50', '1');
INSERT INTO `t_sys_menu` VALUES ('189', '对接申请表管理', '102', 'menu/topage.do?url=sys/excel_manage', '', '', '6', '0', '027681', '2016-01-31 10:25:05', '027681', '2016-01-31 10:42:16', '0');
INSERT INTO `t_sys_menu` VALUES ('190', '文章管理', '15', 'menu/topage.do?url=sys/article_manage&type=2', '', '', '3', '0', 'admin', '2016-03-22 16:03:46', 'admin', '2016-03-22 16:04:04', '0');
INSERT INTO `t_sys_menu` VALUES ('191', '寄养管理', '15', 'menu/topage.do?url=sys/foster_manage', '', '', '4', '0', 'admin', '2016-03-28 09:44:36', 'admin', '2016-03-28 11:05:46', '0');
INSERT INTO `t_sys_menu` VALUES ('192', '首页管理', '15', 'menu/topage.do?url=sys/mipetimage_manage', '', '', '5', '0', 'admin', '2016-03-30 09:43:38', 'admin', '2016-03-30 09:43:55', '0');
INSERT INTO `t_sys_menu` VALUES ('193', '商品管理', '15', 'menu/topage.do?url=sys/goods_manage', '', '', '6', '0', 'admin', '2016-04-01 09:28:29', 'admin', '2016-04-01 09:29:01', '0');

-- ----------------------------
-- Table structure for t_sys_qctimertask
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_qctimertask`;
CREATE TABLE `t_sys_qctimertask` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `job_name` varchar(100) DEFAULT NULL COMMENT '任务名称',
  `job_group_name` varchar(100) DEFAULT NULL COMMENT '任务组名称',
  `job_status` int(11) DEFAULT NULL COMMENT '任务状态',
  `target_class` varchar(100) DEFAULT NULL COMMENT '任务class',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `trigger_group_name` varchar(100) DEFAULT NULL COMMENT '触发器组名',
  `creator` varchar(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updator` varchar(11) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(11) DEFAULT NULL COMMENT '是否删除；0：删除；1：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_qctimertask
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) DEFAULT NULL COMMENT '角色名',
  `desc` varchar(300) DEFAULT NULL COMMENT '角色描述',
  `creator` varchar(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updator` varchar(11) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除；0：删除；1：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', '系统开发', '开发人员使用权限', null, null, null, null, '0');

-- ----------------------------
-- Table structure for t_sys_rolemenu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_rolemenu`;
CREATE TABLE `t_sys_rolemenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '功能菜单ID',
  `creator` varchar(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updator` varchar(11) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(11) DEFAULT NULL COMMENT '是否删除；0：删除；1：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_rolemenu
-- ----------------------------
INSERT INTO `t_sys_rolemenu` VALUES ('66', '1', '11', '015484', '2015-12-21 09:18:29', '015484', '2015-12-21 09:18:29', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('67', '1', '12', '015484', '2015-12-21 21:22:01', '015484', '2015-12-21 21:22:01', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('88', '1', '1', '027681', '2016-01-18 17:38:45', '027681', '2016-01-18 17:38:45', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('89', '1', '3', '027681', '2016-01-18 17:38:48', '027681', '2016-01-18 17:38:48', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('90', '1', '101', '027681', '2016-01-18 17:38:51', '027681', '2016-01-18 17:38:51', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('103', '1', '103', '027681', '2016-01-18 17:40:19', '027681', '2016-01-18 17:40:19', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('105', '1', '108', '027681', '2016-01-18 17:40:19', '027681', '2016-01-18 17:40:19', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('108', '1', '110', '027681', '2016-01-18 17:40:19', '027681', '2016-01-18 17:40:19', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('110', '1', '109', '027681', '2016-01-18 17:40:19', '027681', '2016-01-18 17:40:19', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('125', '1', '15', '027681', '2016-01-18 17:40:25', '027681', '2016-01-18 17:40:25', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('126', '1', '16', '027681', '2016-01-18 17:40:25', '027681', '2016-01-18 17:40:25', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('127', '1', '17', '027681', '2016-01-18 17:40:25', '027681', '2016-01-18 17:40:25', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('128', '1', '190', 'admin', '2016-03-22 16:07:27', 'admin', '2016-03-22 16:07:27', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('129', '1', '191', 'admin', '2016-03-28 11:06:58', 'admin', '2016-03-28 11:06:58', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('130', '1', '192', 'admin', '2016-03-30 09:44:06', 'admin', '2016-03-30 09:44:06', '0');
INSERT INTO `t_sys_rolemenu` VALUES ('131', '1', '193', 'admin', '2016-04-01 09:29:22', 'admin', '2016-04-01 09:29:22', '0');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(10) DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(40) DEFAULT NULL COMMENT '密码',
  `status` int(11) DEFAULT NULL COMMENT '用户禁用启用状态；0：禁用；1：启用',
  `fullname` varchar(100) DEFAULT NULL COMMENT '姓名',
  `creator` varchar(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updator` varchar(11) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(11) DEFAULT NULL COMMENT '是否删除；0：删除；1：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', '超级管理员', 'admin', '2016-01-16 13:51:26', 'admin', '2016-01-20 14:11:05', '0');

-- ----------------------------
-- Table structure for t_sys_userrole
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_userrole`;
CREATE TABLE `t_sys_userrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `creator` varchar(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updator` varchar(11) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(11) DEFAULT NULL COMMENT '是否删除；0：删除；1：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_userrole
-- ----------------------------
INSERT INTO `t_sys_userrole` VALUES ('39', '1', 'admin', 'admin', '2015-12-12 09:48:42', 'admin', '2015-12-12 09:48:42', '0');

-- ----------------------------
-- Table structure for user_article
-- ----------------------------
DROP TABLE IF EXISTS `user_article`;
CREATE TABLE `user_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `portion` int(1) DEFAULT NULL COMMENT '版块：0：狗、1：猫、2：其它、3：SOS救助站',
  `user_id` int(11) DEFAULT NULL COMMENT '用户表ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `html_content` varchar(3000) DEFAULT NULL COMMENT '页面发布内容',
  `likes` int(11) DEFAULT '0' COMMENT '赞的人数',
  `comments` int(11) DEFAULT '0' COMMENT '评论数',
  `pub_time` datetime DEFAULT NULL COMMENT '发布时间',
  `status` int(1) DEFAULT '0' COMMENT '是否显示',
  `deleted` int(1) DEFAULT '0' COMMENT '0：正常、1：删除',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_article
-- ----------------------------
INSERT INTO `user_article` VALUES ('1', '1', '1', '我是一只Cat', '我是一只猫2', null, '2', null, '1', '0', '2016-04-01 16:15:39');
INSERT INTO `user_article` VALUES ('2', '2', '2', '我是一只猫', '我是一只猫。', null, null, null, '1', '0', '2016-03-23 11:16:50');
INSERT INTO `user_article` VALUES ('3', '1', '1', '俄方让他提供', '我要记住我生命的最后一个夕阳。', '1', '0', '2016-03-17 11:34:24', '1', '0', '2016-04-01 08:51:07');
INSERT INTO `user_article` VALUES ('4', '1', '1', '测试内容标题', '测试内容1', '0', '0', '2016-03-23 09:10:05', '1', '0', null);
INSERT INTO `user_article` VALUES ('5', '1', '1', '测试内容标题', '测试内容2', '0', '0', '2016-03-23 09:10:07', '0', '0', null);
INSERT INTO `user_article` VALUES ('6', '3', '2', '测试标题', '测试内容3', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('7', '0', '2', '测试标题', '测试内容121测试内容121测试内容121测试内容121测试内容121测试内容121测试内容121', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('8', '0', '2', '测试标题', '测试内容212测试内容212测试内容212测试内容212测试内容212测试内容212测试内容212', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('9', '1', '2', '测试标题', '测试内容2121', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('10', '2', '2', '测试标题', '测试内容', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('11', '3', '2', '测试标题', '测试内容', '0', '0', null, '1', '0', null);
INSERT INTO `user_article` VALUES ('12', '0', '2', '测试标题0', '测试内容0测试内容0测试内容0测试内容0测试内容0测试内容0测试内容0测试内容0测试内容0', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('13', '2', '2', '测试标题1', '测试内容1', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('14', '0', '2', '测试标题2', '测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('15', '1', '2', '测试标题3', '测试内容3', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('16', '1', '2', '测试标题4', '测试内容4', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('17', '0', '2', '测试标题5', '测试内容5', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('18', '1', '2', '测试标题6', '测试内容6', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('19', '0', '2', '测试标题7', '测试内容7', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('20', '0', '2', '测试标题8', '测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8测试内容8', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('21', '0', '2', '测试标题9', '测试内容9', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('22', '0', '2', '测试标题10', '测试内容10', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('23', '0', '2', '测试标题11', '测试内容11', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('24', '0', '2', '测试标题12', '测试内容12', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('25', '0', '2', '测试标题13', '测试内容13', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('26', '0', '2', '测试标题14', '测试内容14', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('27', '0', '2', '测试标题1511', '<p>测试内容1511111</p>\n', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('28', '0', '2', '测试标题16', '测试内容16', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('29', '0', '2', '测试标题17', '测试内容17', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('30', '0', '2', '测试标题18', '测试内容18', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('31', '1', '2', '测试标题19', '测试内容19', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('32', '0', '1', '测试标题20', '测试', '0', '0', null, '0', '0', null);
INSERT INTO `user_article` VALUES ('33', '2', '2', '经济管理里', 'HK哦咯LOL哦LOL鸡鸡鸡鸡', '0', '0', '2016-03-29 11:41:43', '0', '0', '2016-03-29 11:41:43');
INSERT INTO `user_article` VALUES ('34', '2', '2', '经济管理里', 'HK哦咯LOL哦LOL鸡鸡鸡鸡', '0', '0', '2016-03-29 11:41:51', '0', '0', '2016-03-29 11:41:51');
INSERT INTO `user_article` VALUES ('35', '2', '2', '经济管理里', 'HK哦咯LOL哦LOL鸡鸡鸡鸡', '0', '0', '2016-03-29 11:41:51', '0', '0', '2016-03-29 11:41:51');
INSERT INTO `user_article` VALUES ('36', '2', '2', '经济管理里', 'HK哦咯LOL哦LOL鸡鸡鸡鸡', '0', '0', '2016-03-29 11:41:51', '0', '0', '2016-03-29 11:41:51');
INSERT INTO `user_article` VALUES ('37', '2', '2', '经济管理里', 'HK哦咯LOL哦LOL鸡鸡鸡鸡', '0', '0', '2016-03-29 11:41:52', '0', '0', '2016-03-29 11:41:52');
INSERT INTO `user_article` VALUES ('38', '2', '2', '经济管理里', '<p>HK哦咯LOL哦LOL鸡鸡鸡鸡1111</p>\n', '0', '0', '2016-03-29 11:41:52', '0', '0', '2016-04-01 15:07:07');
INSERT INTO `user_article` VALUES ('39', '2', '2', '经济管理里', 'HK哦咯LOL哦LOL鸡鸡鸡鸡', '0', '0', '2016-03-29 11:41:52', '0', '0', '2016-03-29 11:41:52');
INSERT INTO `user_article` VALUES ('40', '2', '2', '经济管理里', 'HK哦咯LOL哦LOL鸡鸡鸡鸡', '0', '0', '2016-03-29 11:41:53', '0', '0', '2016-03-29 11:41:53');
INSERT INTO `user_article` VALUES ('41', '2', '2', '经济管理里', 'HK哦咯LOL哦LOL鸡鸡鸡鸡', '0', '0', '2016-03-29 11:41:53', '0', '0', '2016-03-29 11:41:53');
INSERT INTO `user_article` VALUES ('42', '2', '2', '经济管理里', 'HK哦咯LOL哦LOL鸡鸡鸡鸡', '0', '0', '2016-03-29 11:41:53', '0', '0', '2016-03-29 11:41:53');
INSERT INTO `user_article` VALUES ('43', '2', '2', '经济管理里', 'HK哦咯LOL哦LOL鸡鸡鸡鸡', '0', '0', '2016-03-29 11:41:53', '0', '0', '2016-03-29 11:41:53');
INSERT INTO `user_article` VALUES ('44', '2', '2', '季姬击鸡记', '老K克隆', '0', '0', '2016-03-29 11:43:07', '0', '0', '2016-03-29 11:43:07');
INSERT INTO `user_article` VALUES ('45', '2', '2', '累了就', '<p>考虑考虑太可怜了<img alt=\"\" src=\"http://localhost:80/upload/imagefile/c1cbfb85-e148-48bd-aa90-4a02c7bfde64.png\" style=\"height:72px; width:240px\" /></p>\n', '0', '0', '2016-03-29 11:43:35', '0', '0', '2016-03-29 11:43:35');
INSERT INTO `user_article` VALUES ('46', '0', '2', '测试标题 新--11', '<p>测试内容</p>\n', '0', '0', '2016-03-31 14:42:14', '0', '0', '2016-03-31 14:42:14');

-- ----------------------------
-- Table structure for user_comment
-- ----------------------------
DROP TABLE IF EXISTS `user_comment`;
CREATE TABLE `user_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` int(1) DEFAULT NULL COMMENT '文章ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户表ID',
  `content` varchar(512) DEFAULT NULL COMMENT '评论内容',
  `pub_time` datetime DEFAULT NULL COMMENT '评论时间',
  `deleted` int(1) DEFAULT '0' COMMENT '0：正常、1：删除',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_comment
-- ----------------------------
INSERT INTO `user_comment` VALUES ('2', '1', '1', 'SDG副董事长发噶地方', '2016-03-23 12:57:03', '0', '2016-03-23 12:57:10');
INSERT INTO `user_comment` VALUES ('3', '1', '2', '士大夫撒旦飞洒范德萨', '2016-03-23 12:57:03', '0', '2016-03-23 12:57:10');
INSERT INTO `user_comment` VALUES ('4', '1', '2', '虽然粉色软复位人物任务', '2016-03-23 12:57:03', '0', '2016-03-23 12:57:10');
INSERT INTO `user_comment` VALUES ('5', '1', '2', '豆腐DFvs在DF是概知道', '2016-03-23 12:57:03', '0', '2016-03-23 12:57:10');
INSERT INTO `user_comment` VALUES ('6', '1', '2', '测试内容', '2016-03-28 16:02:34', '0', '2016-03-28 16:02:34');
INSERT INTO `user_comment` VALUES ('8', '1', '1', '李老婆了', '2016-03-28 16:31:05', '0', '2016-03-28 16:31:05');
INSERT INTO `user_comment` VALUES ('9', '1', '1', '里脊肉诺记', '2016-03-28 16:35:38', '0', '2016-03-28 16:35:38');
INSERT INTO `user_comment` VALUES ('10', '1', '1', 'purl睡觉去了', '2016-03-28 16:37:01', '0', '2016-03-28 16:37:01');
INSERT INTO `user_comment` VALUES ('11', '1', '1', '爱了散了', '2016-03-28 16:38:35', '0', '2016-03-28 16:38:35');
INSERT INTO `user_comment` VALUES ('12', '1', '1', '李掠夺斤', '2016-03-28 16:39:04', '0', '2016-03-28 16:39:04');
INSERT INTO `user_comment` VALUES ('13', '1', '1', '好脸色PK', '2016-03-28 16:40:12', '0', '2016-03-28 16:40:12');
INSERT INTO `user_comment` VALUES ('14', '1', '2', '啊收费的热望亲热', '2016-04-01 16:10:43', '0', '2016-04-01 16:10:43');
INSERT INTO `user_comment` VALUES ('15', '1', '2', '啊收费的热望亲热', '2016-04-01 16:11:05', '0', '2016-04-01 16:11:05');
INSERT INTO `user_comment` VALUES ('16', '1', '2', '啊收费的热望亲热', '2016-04-01 16:13:41', '0', '2016-04-01 16:13:41');
INSERT INTO `user_comment` VALUES ('17', '1', '2', 'ABC', '2016-04-01 16:15:34', '0', '2016-04-01 16:15:34');

-- ----------------------------
-- Table structure for user_favs
-- ----------------------------
DROP TABLE IF EXISTS `user_favs`;
CREATE TABLE `user_favs` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户表ID',
  `collect_time` datetime DEFAULT NULL COMMENT '收藏时间',
  `deleted` int(1) DEFAULT NULL COMMENT '0：正常、1：删除',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_favs
-- ----------------------------
INSERT INTO `user_favs` VALUES ('1', '1', '1', null, null, null);
INSERT INTO `user_favs` VALUES ('3', '2', '1', null, null, null);
INSERT INTO `user_favs` VALUES ('4', '3', '2', null, null, null);
INSERT INTO `user_favs` VALUES ('5', '4', '2', null, null, null);
INSERT INTO `user_favs` VALUES ('6', '5', '1', null, null, null);
INSERT INTO `user_favs` VALUES ('7', '6', '1', null, null, null);
INSERT INTO `user_favs` VALUES ('8', '7', '2', null, null, null);
INSERT INTO `user_favs` VALUES ('9', '8', '1', null, null, null);
INSERT INTO `user_favs` VALUES ('10', '9', '1', null, null, null);
INSERT INTO `user_favs` VALUES ('51', '38', '2', null, null, null);
INSERT INTO `user_favs` VALUES ('52', '38', '2', null, null, null);
INSERT INTO `user_favs` VALUES ('53', '38', '2', null, null, null);
INSERT INTO `user_favs` VALUES ('54', '38', '2', null, null, null);
INSERT INTO `user_favs` VALUES ('55', '38', '2', null, null, null);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source` varchar(32) DEFAULT NULL COMMENT '用户来源',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '登录密码',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `gender` int(1) DEFAULT '0' COMMENT '性别 0表示女 1表示男',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `family` varchar(32) DEFAULT NULL COMMENT '家族',
  `head_pic` varchar(255) DEFAULT NULL COMMENT '头像图片',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `reg_time` datetime DEFAULT NULL COMMENT '注册时间',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(32) DEFAULT NULL COMMENT '登录IP',
  `login_times` int(11) DEFAULT NULL COMMENT '登录次数',
  `status` int(1) DEFAULT '0' COMMENT '0：未验证、1：正常',
  `enabled` int(1) DEFAULT '0' COMMENT '0：启用、1：拉黑',
  `deleted` int(1) DEFAULT '0' COMMENT '0：正常、1：删除',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', null, '管理员', 'e10adc3949ba59abbe56e057f20f883e', '大明', '1', '2016-03-09', '黑马', 'http://wx001/image/man.png', '13022162216', null, '2016-03-17 10:40:05', null, null, '0', '0', '0', '0', '2016-03-21 18:12:47');
INSERT INTO `user_info` VALUES ('2', null, null, 'e10adc3949ba59abbe56e057f20f883e', '小二', '1', '2016-03-01', '白兔', 'http://wx001/image/man.png', '13011115555', null, '2016-03-17 15:08:02', null, null, '0', '0', '0', '0', '2016-04-01 15:15:02');
INSERT INTO `user_info` VALUES ('10', null, null, 'e10adc3949ba59abbe56e057f20f883e', null, null, '2016-03-01', null, null, '13011115511', null, '2016-03-24 14:21:19', null, null, '0', '0', '0', '0', '2016-03-24 17:21:18');
INSERT INTO `user_info` VALUES ('11', null, null, 'e10adc3949ba59abbe56e057f20f883e', null, null, '2016-03-01', null, null, '13011111111', null, '2016-03-24 14:22:33', null, null, '0', '0', '0', '0', '2016-03-24 14:22:33');
INSERT INTO `user_info` VALUES ('12', null, null, 'e10adc3949ba59abbe56e057f20f883e', null, null, '2016-03-15', null, null, '13011111234', null, '2016-03-24 14:23:30', null, null, '0', '0', '0', '0', '2016-03-24 14:23:30');
INSERT INTO `user_info` VALUES ('13', null, null, 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, null, '13011112222', null, '2016-03-24 14:24:47', null, null, '0', '0', '0', '0', '2016-03-24 14:24:47');
INSERT INTO `user_info` VALUES ('14', null, null, 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, null, '13010111101', null, '2016-03-24 14:44:22', null, null, '0', '0', '0', '0', '2016-03-24 14:44:22');
INSERT INTO `user_info` VALUES ('15', null, null, 'e10adc3949ba59abbe56e057f20f883e', null, '1', null, null, null, '13011115519', null, '2016-03-25 08:48:18', '2016-03-25 08:48:18', '192.168.2.95', '0', '0', '0', '0', '2016-03-25 08:48:28');
