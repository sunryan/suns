/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : suns

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-03-27 16:23:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for suns_userconnection
-- ----------------------------
DROP TABLE IF EXISTS `suns_userconnection`;
CREATE TABLE `suns_userconnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL DEFAULT '',
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(512) NOT NULL,
  `secret` varchar(512) DEFAULT NULL,
  `refreshToken` varchar(512) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='三方用户与本地用户关联表';

-- ----------------------------
-- Records of suns_userconnection
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menuId` int(11) NOT NULL COMMENT '菜单ID',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `permission` varchar(32) DEFAULT NULL COMMENT '菜单权限标识',
  `path` varchar(128) DEFAULT NULL COMMENT '前端URL',
  `url` varchar(128) DEFAULT NULL COMMENT '请求链接',
  `method` varchar(32) DEFAULT NULL COMMENT '请求方法',
  `parentId` int(11) NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `component` varchar(64) DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) DEFAULT NULL COMMENT '排序值',
  `type` char(1) DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `delFlag` tinyint(1) DEFAULT '0' COMMENT '0--正常 1--删除',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', null, '/admin', null, null, '-1', null, 'Layout', null, '0', '2017-11-07 20:56:00', '2017-11-14 14:26:03', '0');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', null, 'admin', null, null, '1', null, '_import(\'admin/admin\')', null, '0', '2017-11-02 22:24:37', '2017-11-14 15:22:40', '0');
INSERT INTO `sys_menu` VALUES ('3', '菜单管理', null, 'menu', null, null, '1', null, '_import(\'admin/menu\')', null, '0', '2017-11-08 09:57:27', '2017-11-14 15:22:45', '0');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', null, 'role', null, null, '1', null, '_import(\'admin/role\')', null, '0', '2017-11-08 10:13:37', '2017-11-14 15:22:51', '0');
INSERT INTO `sys_menu` VALUES ('5', '日志管理', null, 'log', null, null, '1', null, '_import(\'admin/log\')', null, '0', '2017-11-20 14:06:22', '2017-11-20 14:14:11', '0');
INSERT INTO `sys_menu` VALUES ('6', '字典管理', null, 'dict', null, null, '1', null, '_import(\'admin/dict\')', null, '0', '2017-11-29 11:30:52', '2017-11-29 11:31:15', '0');
INSERT INTO `sys_menu` VALUES ('7', '部门管理', null, null, null, null, '1', null, '_import(\'admin/dept\')', null, '0', '2018-01-20 13:17:19', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '系统监控', null, null, null, null, '-1', null, '_import(\'admin/monitor\')', null, '1', '2018-01-22 12:30:41', '2018-01-23 10:51:46', '0');
INSERT INTO `sys_menu` VALUES ('9', '服务监控', null, null, null, null, '8', null, '_import(\'admin/monitor/service\')', null, null, '2018-01-23 10:53:33', null, '0');
INSERT INTO `sys_menu` VALUES ('10', 'zipkin监控', null, null, null, null, '8', null, '_import(\'admin/monitor/zipkin\')', null, '0', '2018-01-23 10:55:18', '2018-01-25 11:10:16', '0');
INSERT INTO `sys_menu` VALUES ('11', 'pinpoint监控', null, null, null, null, '8', null, '_import(\'admin/monitor/pinpoint\')', null, '0', '2018-01-25 11:08:52', '2018-01-25 11:11:01', '0');
INSERT INTO `sys_menu` VALUES ('12', '缓存状态', null, null, null, null, '8', null, '_import(\'admin/monitor/cache\')', null, '0', '2018-01-23 10:56:11', null, '0');
INSERT INTO `sys_menu` VALUES ('13', 'ELK状态', null, null, null, null, '8', null, '_import(\'admin/monitor/elk\')', null, '0', '2018-01-23 10:55:47', '2018-01-25 11:11:40', '0');
INSERT INTO `sys_menu` VALUES ('14', '接口文档', null, null, null, null, '8', null, '_import(\'admin/monitor/swagger\')', null, '0', '2018-01-23 10:56:43', '2018-01-25 11:11:45', '0');
INSERT INTO `sys_menu` VALUES ('21', '用户查看', '', null, '/admin/user/**', 'GET', '2', null, null, null, '1', '2017-11-07 20:58:05', '2018-02-04 14:28:49', '0');
INSERT INTO `sys_menu` VALUES ('22', '用户新增', 'sys_user_add', null, '/admin/user/*', 'POST', '2', null, null, null, '1', '2017-11-08 09:52:09', '2017-12-04 16:31:10', '0');
INSERT INTO `sys_menu` VALUES ('23', '用户修改', 'sys_user_upd', null, '/admin/user/**', 'PUT', '2', null, null, null, '1', '2017-11-08 09:52:48', '2018-01-17 17:40:01', '0');
INSERT INTO `sys_menu` VALUES ('24', '用户删除', 'sys_user_del', null, '/admin/user/*', 'DELETE', '2', null, null, null, '1', '2017-11-08 09:54:01', '2017-12-04 16:31:18', '0');
INSERT INTO `sys_menu` VALUES ('31', '菜单查看', null, null, '/admin/menu/**', 'GET', '3', null, null, null, '1', '2017-11-08 09:57:56', '2017-11-14 17:29:17', '0');
INSERT INTO `sys_menu` VALUES ('32', '菜单新增', 'sys_menu_add', null, '/admin/menu/*', 'POST', '3', null, null, null, '1', '2017-11-08 10:15:53', '2018-01-20 14:37:50', '0');
INSERT INTO `sys_menu` VALUES ('33', '菜单修改', 'sys_menu_edit', null, '/admin/menu/*', 'PUT', '3', null, null, null, '1', '2017-11-08 10:16:23', '2018-01-20 14:37:56', '0');
INSERT INTO `sys_menu` VALUES ('34', '菜单删除', 'sys_menu_del', null, '/admin/menu/*', 'DELETE', '3', null, null, null, '1', '2017-11-08 10:16:43', '2018-01-20 14:38:03', '0');
INSERT INTO `sys_menu` VALUES ('41', '角色查看', null, null, '/admin/role/**', 'GET', '4', null, null, null, '1', '2017-11-08 10:14:01', '2018-02-04 13:55:06', '0');
INSERT INTO `sys_menu` VALUES ('42', '角色新增', null, null, '/admin/role/*', 'POST', '4', null, null, null, '1', '2017-11-08 10:14:18', '2017-11-14 14:10:03', '0');
INSERT INTO `sys_menu` VALUES ('43', '角色修改', null, null, '/admin/role/*', 'PUT', '4', null, null, null, '1', '2017-11-08 10:14:41', '2017-11-08 20:35:33', '0');
INSERT INTO `sys_menu` VALUES ('44', '角色删除', null, null, '/admin/role/*', 'DELETE', '4', null, null, null, '1', '2017-11-08 10:14:59', '2017-11-08 20:35:35', '0');
INSERT INTO `sys_menu` VALUES ('51', '日志查看', null, null, '/admin/log/**', 'GET', '5', null, null, null, '1', '2017-11-20 14:07:25', '2018-02-04 14:28:53', '0');
INSERT INTO `sys_menu` VALUES ('52', '日志删除', 'sys_log_del', null, '/admin/log/*', 'DELETE', '5', null, null, null, '1', '2017-11-20 20:37:37', '2017-11-28 17:36:52', '0');
INSERT INTO `sys_menu` VALUES ('61', '字典查看', null, null, '/admin/dict/**', 'GET', '6', null, null, null, '1', '2017-11-19 22:04:24', '2017-11-29 11:31:24', '0');
INSERT INTO `sys_menu` VALUES ('62', '字典删除', 'sys_dict_del', null, '/admin/dict/**', 'DELETE', '6', null, null, null, '1', '2017-11-29 11:30:11', '2018-01-07 15:40:51', '0');
INSERT INTO `sys_menu` VALUES ('71', '部门查看', '', null, '/admin/dept/**', 'GET', '7', null, '', null, '1', '2018-01-20 13:17:19', '2018-01-20 14:55:24', '0');
INSERT INTO `sys_menu` VALUES ('72', '部门新增', 'sys_dept_add', null, '/admin/dept/**', 'POST', '7', null, null, null, '1', '2018-01-20 14:56:16', '2018-01-20 21:17:57', '0');
INSERT INTO `sys_menu` VALUES ('73', '部门修改', 'sys_dept_edit', null, '/admin/dept/**', 'PUT', '7', null, null, null, '1', '2018-01-20 14:56:59', '2018-01-20 21:17:59', '0');
INSERT INTO `sys_menu` VALUES ('74', '部门删除', 'sys_dept_del', null, '/admin/dept/**', 'DELETE', '7', null, null, null, '1', '2018-01-20 14:57:28', '2018-01-20 21:18:05', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `roleName` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '角色名称',
  `roleCode` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '角色编码(eg: ROLE_USER）',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT NULL,
  `delFlag` tinyint(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `role_idx_role_code` (`roleCode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'ROLE_ADMIN', '2018-03-27 15:35:15', '2018-01-28 12:46:43', '0');
INSERT INTO `sys_role` VALUES ('2', '普通用户', 'ROLE_USER', '2018-03-27 15:35:10', '2018-01-28 12:46:43', '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `menuId` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_role_menu` (`roleId`,`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('13', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('14', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('15', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('16', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('17', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('18', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('19', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('20', '1', '32');
INSERT INTO `sys_role_menu` VALUES ('21', '1', '33');
INSERT INTO `sys_role_menu` VALUES ('22', '1', '34');
INSERT INTO `sys_role_menu` VALUES ('23', '1', '41');
INSERT INTO `sys_role_menu` VALUES ('24', '1', '42');
INSERT INTO `sys_role_menu` VALUES ('25', '1', '51');
INSERT INTO `sys_role_menu` VALUES ('26', '1', '52');
INSERT INTO `sys_role_menu` VALUES ('27', '1', '61');
INSERT INTO `sys_role_menu` VALUES ('28', '1', '62');
INSERT INTO `sys_role_menu` VALUES ('29', '1', '71');
INSERT INTO `sys_role_menu` VALUES ('30', '1', '72');
INSERT INTO `sys_role_menu` VALUES ('31', '1', '73');
INSERT INTO `sys_role_menu` VALUES ('32', '1', '74');
INSERT INTO `sys_role_menu` VALUES ('33', '2', '21');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '随机盐',
  `mobile` varchar(13) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '手机号',
  `introduction` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户简介',
  `avatar` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户头像',
  `deptId` int(11) DEFAULT NULL COMMENT '部门ID',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(1) DEFAULT '0' COMMENT '0-正常，1-删除',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `user_idx_username` (`username`) USING BTREE,
  UNIQUE KEY `user_idx_mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$gBejTEife2WvJzysrRDXL.MVNCcBsso3adMZWQDsVewCDUaIsIvSK', null, '17326092280', '17034642119', 'http://p0hpm86wj.bkt.clouddn.com/10bd0c7c-03c7-4ad1-8366-b24b4201b969.png', '3', '2017-10-29 15:45:13', '2018-02-28 17:44:56', '0');
INSERT INTO `sys_user` VALUES ('2', 'ryan', '$2a$10$gBejTEife2WvJzysrRDXL.MVNCcBsso3adMZWQDsVewCDUaIsIvSK', null, '17326092281', '17034642119', null, '2', null, null, '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_role` (`userId`,`roleId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1', '2');
INSERT INTO `sys_user_role` VALUES ('3', '2', '2');
