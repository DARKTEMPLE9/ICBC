/*
 Navicat MySQL Data Transfer

 Source Server         : ytxmg
 Source Server Type    : MySQL
 Source Server Version : 100136
 Source Host           : 172.19.187.147:3306
 Source Schema         : cms

 Target Server Type    : MySQL
 Target Server Version : 100136
 File Encoding         : 65001

 Date: 02/09/2019 09:36:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_bill
-- ----------------------------
DROP TABLE IF EXISTS `cms_bill`;
CREATE TABLE `cms_bill` (
  `ID` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `BILL_NAME` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `BILL_CODE` varchar(64) NOT NULL COMMENT '类型编码',
  `PARENT_ID` bigint(20) DEFAULT '0' COMMENT '父节点',
  `BILL_ORDER` int(4) DEFAULT NULL COMMENT '类型顺序',
  `LEAF` char(1) DEFAULT NULL COMMENT '是否为叶子节点(0-子节点，1-非子节点)',
  `ALL_PATH` varchar(255) DEFAULT NULL COMMENT '祖级列表',
  `STATUS` char(1) DEFAULT NULL COMMENT '分类状态（0-启用，1-停用）',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQ_1IM_BILL` (`ID`) USING BTREE,
  KEY `IDX_1IM_BILL` (`PARENT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
-- Records of cms_bill
-- ----------------------------
BEGIN;
INSERT INTO `cms_bill` VALUES (5, '测试节点1', 'trg4', 0, 1, '1', '0', '0', '1', '2019-08-27 16:47:10', '001', '0000-00-00 00:00:00');
INSERT INTO `cms_bill` VALUES (6, '测试节点2', 'test1', 0, 1, '1', '0', '0', '1', '2019-08-27 16:48:00', '001', '0000-00-00 00:00:00');
INSERT INTO `cms_bill` VALUES (7, '测试节点3', 'trg2', 0, 1, '1', '0', '0', '1', '2019-08-27 17:18:36', '001', '0000-00-00 00:00:00');
INSERT INTO `cms_bill` VALUES (8, '测试节点三', 'trg3', 0, 1, '1', '0', '0', '1', '2019-08-27 17:26:47', '001', '0000-00-00 00:00:00');
INSERT INTO `cms_bill` VALUES (9, '测试节点5', 'trg5', 6, 2, '0', ',6', '0', '1', '2019-08-27 17:29:39', '001', '0000-00-00 00:00:00');
INSERT INTO `cms_bill` VALUES (10, '测试节点6', 'trg6', 5, 1, '0', ',5', '0', '1', '2019-08-27 17:38:49', '001', '0000-00-00 00:00:00');
INSERT INTO `cms_bill` VALUES (11, '测试节点11', '3', 10, 3, '0', ',10', '0', '1', '2019-08-27 17:59:53', '001', '0000-00-00 00:00:00');
INSERT INTO `cms_bill` VALUES (12, '测试节点22', 'trg22', 7, 1, '0', ',7', '0', '1', '2019-08-28 09:19:20', '001', '0000-00-00 00:00:00');
INSERT INTO `cms_bill` VALUES (13, '测试节点12', '123', 10, 2, '0', ',10', '0', '1', '2019-08-28 10:08:05', '', '0000-00-00 00:00:00');
INSERT INTO `cms_bill` VALUES (14, '测试节点7', 'trg11', 5, 1, '1', '1', '0', '1', '2019-08-28 17:37:32', NULL, NULL);
INSERT INTO `cms_bill` VALUES (15, '测试节点7.1', 'trg71', 14, 1, '0', '1', '0', '1', '2019-08-28 17:45:47', NULL, NULL);
INSERT INTO `cms_bill` VALUES (16, '科技创新部', 'kjcxb', 0, 1, '1', '0', '0', '1', '2019-08-28 19:42:29', NULL, NULL);
INSERT INTO `cms_bill` VALUES (17, '项目维度', 'yxpt', 16, 1, '0', ',16', '0', '1', '2019-08-28 19:44:02', NULL, NULL);
INSERT INTO `cms_bill` VALUES (18, '内容管理平台', 'cms', 16, 1, '0', ',16', '0', '1', '2019-08-28 19:44:16', NULL, NULL);
INSERT INTO `cms_bill` VALUES (19, '立项', 'lx', 17, NULL, '0', ',17', '0', '1', '2019-08-28 19:44:40', NULL, NULL);
INSERT INTO `cms_bill` VALUES (20, '合同', 'ht', 17, 2, '0', ',17', '0', '1', '2019-08-28 19:45:03', NULL, NULL);
INSERT INTO `cms_bill` VALUES (21, '全行', 'qh', 0, 1, '1', '0', '0', '1', '2019-08-28 19:56:36', NULL, NULL);
INSERT INTO `cms_bill` VALUES (22, '合规部', 'hgb', 0, 1, '1', '0', '0', '1', '2019-08-28 19:57:04', NULL, NULL);
INSERT INTO `cms_bill` VALUES (23, '全行', 'qh', 22, 1, '0', ',22', '0', '1', '2019-08-28 19:57:44', NULL, NULL);
INSERT INTO `cms_bill` VALUES (24, '部门', 'bm', 22, 1, '0', ',22', '0', '1', '2019-08-28 19:57:59', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for cms_file
-- ----------------------------
DROP TABLE IF EXISTS `cms_file`;
CREATE TABLE `cms_file` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `FILE_ID` bigint(20) unsigned NOT NULL COMMENT '逻辑主键',
  `FILE_NAME` varchar(200) DEFAULT NULL COMMENT '文件名称',
  `STATUS` int(1) DEFAULT NULL COMMENT '文件状态（0-正常；1-已废弃）',
  `BATCH_ID` bigint(32) DEFAULT NULL COMMENT '文件批次',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '上传时间',
  `ORDER_NUM` bigint(12) DEFAULT NULL COMMENT '文件顺序',
  `FILE_PATH` varchar(255) DEFAULT NULL COMMENT '文件存放路径',
  `FILE_SIZE` decimal(8,0) DEFAULT '0' COMMENT '文件大小',
  `FILE_TYPE` varchar(8) DEFAULT NULL COMMENT '文件类型',
  `BILL_ID` bigint(20) DEFAULT NULL COMMENT '所属分类',
  `REMARK` text COMMENT '备注',
  `CREATE_USER` bigint(20) DEFAULT NULL COMMENT '上传人编号',
  `FILE_SOURCE` varchar(255) DEFAULT NULL COMMENT '文件来源',
  `VERSION` varchar(10) DEFAULT NULL COMMENT '版本',
  `METADATA` text COMMENT '元数据',
  `SYS_ID` bigint(20) DEFAULT NULL COMMENT '所属系统',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '所属部门',
  `MODEL_ID` int(8) DEFAULT NULL COMMENT '模型ID',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQ_1IM_FILE` (`ID`) USING BTREE,
  KEY `IDX_1IM_FILE` (`BATCH_ID`) USING BTREE,
  KEY `IDX_2IM_FILE` (`CREATE_TIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件信息表';

-- ----------------------------
-- Table structure for cms_image
-- ----------------------------
DROP TABLE IF EXISTS `cms_image`;
CREATE TABLE `cms_image` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `IMAGE_ID` bigint(20) unsigned NOT NULL COMMENT '逻辑主键',
  `IMAGE_NAME` varchar(200) DEFAULT NULL COMMENT '影像名称',
  `STATUS` int(1) DEFAULT NULL COMMENT '影像状态（0-正常；1-已废弃）',
  `BATCH_ID` bigint(32) DEFAULT NULL COMMENT '批次ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '影像上传时间',
  `ORDER_NUM` bigint(12) DEFAULT NULL COMMENT '影像顺序',
  `WIDTH` varchar(100) DEFAULT NULL COMMENT '影像宽度',
  `HEIGHT` varchar(100) DEFAULT NULL COMMENT '影像高度',
  `BILL_ID` varchar(32) DEFAULT NULL COMMENT '所属分类',
  `TEMPLATE_ID` varchar(32) DEFAULT NULL COMMENT '影像模版ID',
  `IMAGE_SIZE` decimal(8,0) DEFAULT '0' COMMENT '影像大小',
  `IMAGE_PATH` varchar(255) DEFAULT NULL COMMENT '影像存放路径',
  `OCR_RESULT` longtext COMMENT '识别结果',
  `OCR_STATUS` varchar(10) DEFAULT NULL COMMENT '识别状态(0-待识别;1-已识别;)',
  `REMARK` text COMMENT '备注',
  `CREATE_USER` bigint(20) DEFAULT NULL COMMENT '上传人编号',
  `DELETE_TIME` datetime DEFAULT NULL COMMENT '删除时间',
  `DELETE_USER` bigint(20) DEFAULT NULL COMMENT '删除人编号',
  `PZ_NUM` int(38) DEFAULT '0' COMMENT '批注数量',
  `IMAGE_SOURCE` varchar(255) DEFAULT NULL COMMENT '影像来源',
  `OCR_TYPE` varchar(64) DEFAULT NULL COMMENT '识别类型',
  `VERSION` varchar(10) DEFAULT NULL COMMENT '版本',
  `METADATA` text COMMENT '元数据',
  `SYS_ID` bigint(20) DEFAULT NULL COMMENT '所属系统',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '所属部门',
  `MODEL_ID` int(8) DEFAULT NULL COMMENT '模型ID',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQ_1IM_IMAGE` (`ID`) USING BTREE,
  KEY `IDX_1IM_IMAGE` (`BATCH_ID`) USING BTREE,
  KEY `IDX_2IM_IMAGE` (`CREATE_TIME`,`IMAGE_SIZE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='影像信息表';

-- ----------------------------
-- Table structure for cms_keyword
-- ----------------------------
DROP TABLE IF EXISTS `cms_keyword`;
CREATE TABLE `cms_keyword` (
  `ID` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(30) DEFAULT NULL COMMENT '标签名称',
  `SYS_ID` varchar(32) DEFAULT NULL COMMENT '所属系统',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '所属部门',
  `NUMBER` int(10) DEFAULT NULL COMMENT '使用次数',
  `KEYWORD` text COMMENT '关键字',
  `TRG_CODE` varchar(8) DEFAULT NULL COMMENT '标签编号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Table structure for cms_model
-- ----------------------------
DROP TABLE IF EXISTS `cms_model`;
CREATE TABLE `cms_model` (
  `ID` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MODEL_NAME` varchar(64) DEFAULT NULL COMMENT '模型名称',
  `MODEL_CODE` varchar(32) DEFAULT NULL COMMENT '模型编码',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '所属部门',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建者',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '更新者',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `REMARK` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQ_CMS_MODEL` (`ID`) USING BTREE,
  KEY `IDX_CMS_MODEL` (`DEPT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='模型表';

-- ----------------------------
-- Records of cms_model
-- ----------------------------
BEGIN;
INSERT INTO `cms_model` VALUES (1, '测试模型一', 'model5', 1001, '1', '2019-08-27 13:18:56', NULL, NULL, 'test5');
INSERT INTO `cms_model` VALUES (8, '测试模型二', 'model1', 2000, '1', '2019-08-27 14:54:59', NULL, NULL, 'test1');
INSERT INTO `cms_model` VALUES (15, '模型四', '1111', NULL, '1', '2019-08-28 19:16:03', NULL, NULL, '11');
INSERT INTO `cms_model` VALUES (16, '模型三', 'model3', 2000, '1', '2019-08-28 19:20:42', NULL, NULL, '11');
INSERT INTO `cms_model` VALUES (18, '科技创新部-项目维度', NULL, NULL, '1', '2019-08-28 19:43:38', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for cms_model_bill
-- ----------------------------
DROP TABLE IF EXISTS `cms_model_bill`;
CREATE TABLE `cms_model_bill` (
  `MODEL_ID` int(8) NOT NULL COMMENT '模型ID',
  `BILL_ID` int(8) NOT NULL COMMENT '分类ID',
  `FILE_NUM` int(4) DEFAULT NULL COMMENT '文件数量',
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='模型与分类关联表';

-- ----------------------------
-- Records of cms_model_bill
-- ----------------------------
BEGIN;
INSERT INTO `cms_model_bill` VALUES (1, 5, 8, 1);
INSERT INTO `cms_model_bill` VALUES (1, 6, 6, 2);
INSERT INTO `cms_model_bill` VALUES (1, 7, 6, 3);
INSERT INTO `cms_model_bill` VALUES (1, 8, 12, 4);
INSERT INTO `cms_model_bill` VALUES (1, 9, 3, 5);
INSERT INTO `cms_model_bill` VALUES (1, 10, 1, 6);
INSERT INTO `cms_model_bill` VALUES (1, 11, 9, 7);
INSERT INTO `cms_model_bill` VALUES (1, 12, 10, 8);
INSERT INTO `cms_model_bill` VALUES (1, 13, 20, 9);
INSERT INTO `cms_model_bill` VALUES (1, 14, 53, 10);
INSERT INTO `cms_model_bill` VALUES (1, 15, 5, 11);
INSERT INTO `cms_model_bill` VALUES (15, 5, NULL, 12);
INSERT INTO `cms_model_bill` VALUES (15, 10, NULL, 13);
INSERT INTO `cms_model_bill` VALUES (15, 11, NULL, 14);
INSERT INTO `cms_model_bill` VALUES (15, 13, NULL, 15);
INSERT INTO `cms_model_bill` VALUES (15, 14, NULL, 16);
INSERT INTO `cms_model_bill` VALUES (15, 15, NULL, 17);
INSERT INTO `cms_model_bill` VALUES (16, 6, NULL, 18);
INSERT INTO `cms_model_bill` VALUES (16, 9, NULL, 19);
INSERT INTO `cms_model_bill` VALUES (17, 5, NULL, 20);
INSERT INTO `cms_model_bill` VALUES (17, 10, NULL, 21);
INSERT INTO `cms_model_bill` VALUES (17, 11, NULL, 22);
INSERT INTO `cms_model_bill` VALUES (17, 13, NULL, 23);
INSERT INTO `cms_model_bill` VALUES (17, 14, NULL, 24);
INSERT INTO `cms_model_bill` VALUES (17, 15, NULL, 25);
INSERT INTO `cms_model_bill` VALUES (18, 16, NULL, 26);
COMMIT;

-- ----------------------------
-- Table structure for cms_role
-- ----------------------------
DROP TABLE IF EXISTS `cms_role`;
CREATE TABLE `cms_role` (
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='分类角色信息表';

-- ----------------------------
-- Records of cms_role
-- ----------------------------
BEGIN;
INSERT INTO `cms_role` VALUES ('111', '1', 11, '1', '0', '0', 'admin', '2019-08-28 17:14:59', '', NULL, '11', 100);
INSERT INTO `cms_role` VALUES ('3123', '11', 11, '1', '0', '0', 'admin', '2019-08-28 17:17:32', '', NULL, '21321', 101);
INSERT INTO `cms_role` VALUES ('keji', '1111', 11, '1', '0', '0', 'admin', '2019-08-28 19:31:37', '', NULL, '111', 102);
INSERT INTO `cms_role` VALUES ('科技创新部管理员', '22323', 1, '1', '0', '0', 'admin', '2019-08-28 19:45:48', '', NULL, NULL, 103);
COMMIT;

-- ----------------------------
-- Table structure for cms_role_bill
-- ----------------------------
DROP TABLE IF EXISTS `cms_role_bill`;
CREATE TABLE `cms_role_bill` (
  `ROLE_ID` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `BILL_ID` int(8) unsigned NOT NULL COMMENT '分类ID',
  `AUTH_TYPE` varchar(10) DEFAULT NULL COMMENT '权限类型 R读 W写',
  `ID` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='角色与分类关联表';

-- ----------------------------
-- Records of cms_role_bill
-- ----------------------------
BEGIN;
INSERT INTO `cms_role_bill` VALUES (101, 6, NULL, 1);
INSERT INTO `cms_role_bill` VALUES (101, 9, NULL, 2);
INSERT INTO `cms_role_bill` VALUES (102, 5, NULL, 3);
INSERT INTO `cms_role_bill` VALUES (102, 10, NULL, 4);
INSERT INTO `cms_role_bill` VALUES (102, 11, NULL, 5);
INSERT INTO `cms_role_bill` VALUES (102, 13, NULL, 6);
INSERT INTO `cms_role_bill` VALUES (102, 14, NULL, 7);
INSERT INTO `cms_role_bill` VALUES (102, 15, NULL, 8);
INSERT INTO `cms_role_bill` VALUES (103, 16, NULL, 9);
INSERT INTO `cms_role_bill` VALUES (103, 17, NULL, 10);
INSERT INTO `cms_role_bill` VALUES (103, 19, NULL, 11);
INSERT INTO `cms_role_bill` VALUES (103, 20, NULL, 12);
INSERT INTO `cms_role_bill` VALUES (103, 18, NULL, 13);
COMMIT;

-- ----------------------------
-- Table structure for cms_system
-- ----------------------------
DROP TABLE IF EXISTS `cms_system`;
CREATE TABLE `cms_system` (
  `ID` int(4) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SYS_CODE` varchar(32) NOT NULL COMMENT '系统编码',
  `SYS_NAME` varchar(255) DEFAULT NULL COMMENT '系统名称',
  `AUTHENT_INFO` varchar(255) DEFAULT NULL COMMENT '身份验证信息',
  `REMARK` varchar(500) DEFAULT NULL COMMENT '备注',
  `SYS_IP` varchar(255) DEFAULT NULL COMMENT '服务器地址',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建者',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改者',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `STATUS` char(1) DEFAULT NULL COMMENT '系统状态（0-启用，1-停用）',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQ_1IM_ACCESS_SYSTEM` (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接入系统表';

-- ----------------------------
-- Table structure for cms_user_bill_tmp
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_bill_tmp`;
CREATE TABLE `cms_user_bill_tmp` (
  `USER_ID` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `BILL_ID` int(8) unsigned NOT NULL COMMENT '分类ID',
  `AUTH_TYPE` varchar(10) NOT NULL COMMENT '权限类型 R读 W写',
  `AUTH_START` datetime DEFAULT NULL COMMENT '权限起始',
  `AUTH_END` datetime DEFAULT NULL COMMENT '权限终止',
  `ID` int(8) NOT NULL COMMENT '主键',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='临时分类权限表';

-- ----------------------------
-- Table structure for cms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_role`;
CREATE TABLE `cms_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `id` bigint(20) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Table structure for pms_batch
-- ----------------------------
DROP TABLE IF EXISTS `pms_batch`;
CREATE TABLE `pms_batch` (
  `ID` bigint(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `REGBILLGLIDENO` varchar(64) DEFAULT NULL COMMENT '登记流水号',
  `OPERATIONCODE` varchar(64) NOT NULL DEFAULT '' COMMENT '批次编号',
  `CREATE_BY` bigint(20) DEFAULT NULL COMMENT '创建人',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '部门编号',
  `ORDER_NUM` bigint(12) DEFAULT NULL COMMENT '顺序码',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '批次创建时间',
  `BATCH_ID` bigint(20) unsigned NOT NULL COMMENT '逻辑主键',
  `SYS_ID` bigint(20) DEFAULT NULL COMMENT '所属系统',
  `MODEL_ID` bigint(20) DEFAULT NULL COMMENT '使用模型',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATE_BY` bigint(20) DEFAULT NULL COMMENT '修改人',
  `METADATA` text COMMENT '元数据',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `UNIQ_1IM_BATCH` (`ID`) USING BTREE,
  KEY `IDX_3IM_BATCH` (`CREATE_TIME`) USING BTREE,
  KEY `IDX_1IM_BATCH` (`REGBILLGLIDENO`) USING BTREE,
  KEY `IDX_2IM_BATCH` (`CREATE_TIME`) USING BTREE,
  KEY `IDX_4IM_BATCH` (`OPERATIONCODE`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='影像批次表';

-- ----------------------------
-- Records of pms_batch
-- ----------------------------
BEGIN;
INSERT INTO `pms_batch` VALUES (1, '201900000001', '201900000001', 1, 1005, 1, '2019-08-14 14:40:55', 0, 1, 1, '2019-08-30 14:41:03', 2, NULL);
INSERT INTO `pms_batch` VALUES (2, '201900000002', '201900000002', 1, 1005, 1, '2019-08-06 16:03:24', 0, 1, 1, '2019-08-27 16:03:44', 2, NULL);
INSERT INTO `pms_batch` VALUES (3, '201900000003', '201900000003', 1, 1005, 1, '2019-08-09 16:04:08', 0, 1, 1, '2019-08-16 16:04:26', 2, NULL);
COMMIT;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(200) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_cron_triggers` VALUES ('wxyScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('wxyScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/15 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('wxyScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');
COMMIT;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_job_details` VALUES ('wxyScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 'net.northking.iacmp.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F50455254494553737200286E65742E6E6F7274686B696E672E6961636D702E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E0009787200316E65742E6E6F7274686B696E672E6961636D702E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3130202A202A202A202A203F74001172795461736B2E72794E6F506172616D7374000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('wxyScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 'net.northking.iacmp.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F50455254494553737200286E65742E6E6F7274686B696E672E6961636D702E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E0009787200316E65742E6E6F7274686B696E672E6961636D702E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3135202A202A202A202A203F74001572795461736B2E7279506172616D7328277279272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000002740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('wxyScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 'net.northking.iacmp.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F50455254494553737200286E65742E6E6F7274686B696E672E6961636D702E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E0009787200316E65742E6E6F7274686B696E672E6961636D702E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3230202A202A202A202A203F74003872795461736B2E72794D756C7469706C65506172616D7328277279272C20747275652C20323030304C2C203331362E3530442C203130302974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000003740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E5A49AE58F82EFBC8974000133740001317800);
COMMIT;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_locks` VALUES ('wxyScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('wxyScheduler', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_scheduler_state` VALUES ('wxyScheduler', 'localhost1567387931756', 1567388211427, 15000);
COMMIT;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_triggers` VALUES ('wxyScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 1567387940000, -1, 5, 'PAUSED', 'CRON', 1567387932000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('wxyScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 1567387935000, -1, 5, 'PAUSED', 'CRON', 1567387933000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('wxyScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 1567387940000, -1, 5, 'PAUSED', 'CRON', 1567387933000, 0, NULL, 2, '');
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '深色主题theme-dark，浅色主题theme-light');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2006 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1000, 0, '0', '消费金融事业部', 0, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1001, 0, '0', '财务管理部', 1, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1002, 0, '0', '支付创新发展部', 2, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1003, 0, '0', '财富管理事业部', 3, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1004, 0, '0', '战略发展部', 4, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1005, 0, '0', '大数据部', 5, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1006, 0, '0', '信息技术部', 6, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1007, 0, '0', '风险管理部', 7, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1008, 0, '0', '品牌中心', 8, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1009, 0, '0', '平台金融事业部', 9, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1010, 0, '0', '人力资源部', 10, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1011, 0, '0', '营运支持部', 11, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1012, 0, '0', '发展协作中心', 12, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1013, 0, '0', '行政部', 13, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1014, 0, '0', '行领导', 14, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1015, 0, '0', '法律合规部', 15, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1016, 0, '0', '综合管理部', 16, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1017, 0, '0', '内审部', 17, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1018, 0, '0', '董监事会办公室', 18, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1019, 0, '0', '实习生', 19, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1020, 0, '0', '党工办', 20, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1021, 0, '0', '韩旭测试0906', 21, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (1022, 0, '0', 'AD测试部门修改', 22, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (2000, 1006, '0,1006', '运维', 1, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (2001, 1006, '0,1006', '研发', 2, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (2002, 1006, '0,1006', '规划', 3, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (2003, 1016, '0,1016', '品牌', 1, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (2004, 1009, '0,1009', '金融市场中心', 1, NULL, NULL, NULL, '0', '0', 'admin', '2019-08-19 14:11:37', '', NULL);
INSERT INTO `sys_dept` VALUES (2005, 2000, '0,1006,2000', '11', 11, '1', '13698899987', 'baixin@163.com', '0', '2', 'admin', '2019-08-26 17:28:23', '', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');
COMMIT;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- ----------------------------
-- Table structure for sys_login_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_info`;
CREATE TABLE `sys_login_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `info_id` bigint(20) unsigned NOT NULL COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ip_addr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=327 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
BEGIN;
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 14:10:58');
INSERT INTO `sys_logininfor` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 14:56:55');
INSERT INTO `sys_logininfor` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 15:40:26');
INSERT INTO `sys_logininfor` VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 16:43:15');
INSERT INTO `sys_logininfor` VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 17:26:27');
INSERT INTO `sys_logininfor` VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-26 17:48:53');
INSERT INTO `sys_logininfor` VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-26 17:50:50');
INSERT INTO `sys_logininfor` VALUES (107, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 18:18:16');
INSERT INTO `sys_logininfor` VALUES (108, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 18:20:15');
INSERT INTO `sys_logininfor` VALUES (109, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-26 18:26:50');
INSERT INTO `sys_logininfor` VALUES (110, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 18:58:25');
INSERT INTO `sys_logininfor` VALUES (111, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-26 19:08:18');
INSERT INTO `sys_logininfor` VALUES (112, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-26 19:12:44');
INSERT INTO `sys_logininfor` VALUES (113, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 19:17:16');
INSERT INTO `sys_logininfor` VALUES (114, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 19:38:25');
INSERT INTO `sys_logininfor` VALUES (115, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-26 19:43:23');
INSERT INTO `sys_logininfor` VALUES (116, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-26 19:50:57');
INSERT INTO `sys_logininfor` VALUES (117, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-08-26 19:51:07');
INSERT INTO `sys_logininfor` VALUES (118, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-26 19:54:03');
INSERT INTO `sys_logininfor` VALUES (119, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 19:59:30');
INSERT INTO `sys_logininfor` VALUES (120, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 20:01:53');
INSERT INTO `sys_logininfor` VALUES (121, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 20:05:09');
INSERT INTO `sys_logininfor` VALUES (122, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-26 20:12:51');
INSERT INTO `sys_logininfor` VALUES (123, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-26 20:17:41');
INSERT INTO `sys_logininfor` VALUES (124, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-26 20:24:46');
INSERT INTO `sys_logininfor` VALUES (125, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 20:34:49');
INSERT INTO `sys_logininfor` VALUES (126, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-26 20:36:17');
INSERT INTO `sys_logininfor` VALUES (127, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-26 20:44:05');
INSERT INTO `sys_logininfor` VALUES (128, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-26 20:45:14');
INSERT INTO `sys_logininfor` VALUES (129, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-26 20:46:37');
INSERT INTO `sys_logininfor` VALUES (130, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-26 20:49:29');
INSERT INTO `sys_logininfor` VALUES (131, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 20:52:41');
INSERT INTO `sys_logininfor` VALUES (132, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 20:55:35');
INSERT INTO `sys_logininfor` VALUES (133, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 21:02:01');
INSERT INTO `sys_logininfor` VALUES (134, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-26 21:02:11');
INSERT INTO `sys_logininfor` VALUES (135, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 21:04:25');
INSERT INTO `sys_logininfor` VALUES (136, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-26 21:04:47');
INSERT INTO `sys_logininfor` VALUES (137, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 21:05:38');
INSERT INTO `sys_logininfor` VALUES (138, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 21:08:28');
INSERT INTO `sys_logininfor` VALUES (139, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 21:09:54');
INSERT INTO `sys_logininfor` VALUES (140, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-26 21:17:22');
INSERT INTO `sys_logininfor` VALUES (141, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 21:20:20');
INSERT INTO `sys_logininfor` VALUES (142, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-26 21:22:54');
INSERT INTO `sys_logininfor` VALUES (143, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 09:16:32');
INSERT INTO `sys_logininfor` VALUES (144, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 09:18:01');
INSERT INTO `sys_logininfor` VALUES (145, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 09:19:41');
INSERT INTO `sys_logininfor` VALUES (146, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 09:21:06');
INSERT INTO `sys_logininfor` VALUES (147, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 09:40:08');
INSERT INTO `sys_logininfor` VALUES (148, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-27 10:12:10');
INSERT INTO `sys_logininfor` VALUES (149, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 10:15:35');
INSERT INTO `sys_logininfor` VALUES (150, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 10:50:21');
INSERT INTO `sys_logininfor` VALUES (151, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 11:02:08');
INSERT INTO `sys_logininfor` VALUES (152, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 11:03:52');
INSERT INTO `sys_logininfor` VALUES (153, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 11:04:09');
INSERT INTO `sys_logininfor` VALUES (154, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 11:22:56');
INSERT INTO `sys_logininfor` VALUES (155, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 11:24:26');
INSERT INTO `sys_logininfor` VALUES (156, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 11:29:12');
INSERT INTO `sys_logininfor` VALUES (157, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 11:32:42');
INSERT INTO `sys_logininfor` VALUES (158, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 11:36:52');
INSERT INTO `sys_logininfor` VALUES (159, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 11:37:28');
INSERT INTO `sys_logininfor` VALUES (160, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 11:42:30');
INSERT INTO `sys_logininfor` VALUES (161, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 11:50:40');
INSERT INTO `sys_logininfor` VALUES (162, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 11:52:27');
INSERT INTO `sys_logininfor` VALUES (163, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 12:38:57');
INSERT INTO `sys_logininfor` VALUES (164, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 12:46:57');
INSERT INTO `sys_logininfor` VALUES (165, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 12:48:08');
INSERT INTO `sys_logininfor` VALUES (166, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 12:49:11');
INSERT INTO `sys_logininfor` VALUES (167, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 12:50:56');
INSERT INTO `sys_logininfor` VALUES (168, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 13:15:05');
INSERT INTO `sys_logininfor` VALUES (169, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 13:26:57');
INSERT INTO `sys_logininfor` VALUES (170, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 13:46:58');
INSERT INTO `sys_logininfor` VALUES (171, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-27 13:51:05');
INSERT INTO `sys_logininfor` VALUES (172, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 14:05:00');
INSERT INTO `sys_logininfor` VALUES (173, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 14:07:25');
INSERT INTO `sys_logininfor` VALUES (174, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 14:09:56');
INSERT INTO `sys_logininfor` VALUES (175, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 14:13:55');
INSERT INTO `sys_logininfor` VALUES (176, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 14:17:44');
INSERT INTO `sys_logininfor` VALUES (177, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 14:17:53');
INSERT INTO `sys_logininfor` VALUES (178, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 14:22:36');
INSERT INTO `sys_logininfor` VALUES (179, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 14:26:00');
INSERT INTO `sys_logininfor` VALUES (180, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 14:34:18');
INSERT INTO `sys_logininfor` VALUES (181, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 14:42:58');
INSERT INTO `sys_logininfor` VALUES (182, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 14:47:48');
INSERT INTO `sys_logininfor` VALUES (183, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 14:52:42');
INSERT INTO `sys_logininfor` VALUES (184, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 14:53:06');
INSERT INTO `sys_logininfor` VALUES (185, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 15:06:10');
INSERT INTO `sys_logininfor` VALUES (186, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 15:11:42');
INSERT INTO `sys_logininfor` VALUES (187, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 15:14:24');
INSERT INTO `sys_logininfor` VALUES (188, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 15:19:41');
INSERT INTO `sys_logininfor` VALUES (189, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 15:25:28');
INSERT INTO `sys_logininfor` VALUES (190, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 15:27:27');
INSERT INTO `sys_logininfor` VALUES (191, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 15:32:34');
INSERT INTO `sys_logininfor` VALUES (192, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 15:35:21');
INSERT INTO `sys_logininfor` VALUES (193, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 15:35:31');
INSERT INTO `sys_logininfor` VALUES (194, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 15:45:02');
INSERT INTO `sys_logininfor` VALUES (195, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 15:49:08');
INSERT INTO `sys_logininfor` VALUES (196, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 15:51:46');
INSERT INTO `sys_logininfor` VALUES (197, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 15:55:26');
INSERT INTO `sys_logininfor` VALUES (198, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 16:01:50');
INSERT INTO `sys_logininfor` VALUES (199, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-27 16:13:38');
INSERT INTO `sys_logininfor` VALUES (200, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 16:21:11');
INSERT INTO `sys_logininfor` VALUES (201, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 16:23:32');
INSERT INTO `sys_logininfor` VALUES (202, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 16:23:39');
INSERT INTO `sys_logininfor` VALUES (203, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 16:25:25');
INSERT INTO `sys_logininfor` VALUES (204, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 16:46:01');
INSERT INTO `sys_logininfor` VALUES (205, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 16:55:56');
INSERT INTO `sys_logininfor` VALUES (206, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 17:02:38');
INSERT INTO `sys_logininfor` VALUES (207, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 17:04:18');
INSERT INTO `sys_logininfor` VALUES (208, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 17:18:09');
INSERT INTO `sys_logininfor` VALUES (209, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-27 17:22:53');
INSERT INTO `sys_logininfor` VALUES (210, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 17:37:49');
INSERT INTO `sys_logininfor` VALUES (211, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 17:57:43');
INSERT INTO `sys_logininfor` VALUES (212, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 17:57:57');
INSERT INTO `sys_logininfor` VALUES (213, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 18:44:49');
INSERT INTO `sys_logininfor` VALUES (214, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 18:51:16');
INSERT INTO `sys_logininfor` VALUES (215, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 19:01:45');
INSERT INTO `sys_logininfor` VALUES (216, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 19:37:22');
INSERT INTO `sys_logininfor` VALUES (217, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-27 19:39:23');
INSERT INTO `sys_logininfor` VALUES (218, 'admin', '172.19.187.147', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-27 19:54:56');
INSERT INTO `sys_logininfor` VALUES (219, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 09:14:30');
INSERT INTO `sys_logininfor` VALUES (220, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 09:17:23');
INSERT INTO `sys_logininfor` VALUES (221, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 09:27:45');
INSERT INTO `sys_logininfor` VALUES (222, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 09:48:25');
INSERT INTO `sys_logininfor` VALUES (223, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 10:00:58');
INSERT INTO `sys_logininfor` VALUES (224, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 10:14:05');
INSERT INTO `sys_logininfor` VALUES (225, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 10:38:10');
INSERT INTO `sys_logininfor` VALUES (226, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 11:05:25');
INSERT INTO `sys_logininfor` VALUES (227, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 11:26:24');
INSERT INTO `sys_logininfor` VALUES (228, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 11:27:28');
INSERT INTO `sys_logininfor` VALUES (229, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 11:28:48');
INSERT INTO `sys_logininfor` VALUES (230, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 11:29:22');
INSERT INTO `sys_logininfor` VALUES (231, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 13:48:03');
INSERT INTO `sys_logininfor` VALUES (232, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 13:51:18');
INSERT INTO `sys_logininfor` VALUES (233, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 13:54:34');
INSERT INTO `sys_logininfor` VALUES (234, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '退出成功', '2019-08-28 13:56:26');
INSERT INTO `sys_logininfor` VALUES (235, 'gaobo', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 13:56:37');
INSERT INTO `sys_logininfor` VALUES (236, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 14:53:41');
INSERT INTO `sys_logininfor` VALUES (237, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 15:04:08');
INSERT INTO `sys_logininfor` VALUES (238, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 15:06:03');
INSERT INTO `sys_logininfor` VALUES (239, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 15:21:45');
INSERT INTO `sys_logininfor` VALUES (240, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 15:22:11');
INSERT INTO `sys_logininfor` VALUES (241, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 15:27:16');
INSERT INTO `sys_logininfor` VALUES (242, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 15:34:20');
INSERT INTO `sys_logininfor` VALUES (243, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 15:42:34');
INSERT INTO `sys_logininfor` VALUES (244, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 15:44:21');
INSERT INTO `sys_logininfor` VALUES (245, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 15:51:33');
INSERT INTO `sys_logininfor` VALUES (246, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 16:11:35');
INSERT INTO `sys_logininfor` VALUES (247, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 16:16:39');
INSERT INTO `sys_logininfor` VALUES (248, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 16:18:43');
INSERT INTO `sys_logininfor` VALUES (249, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 16:21:01');
INSERT INTO `sys_logininfor` VALUES (250, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 16:26:44');
INSERT INTO `sys_logininfor` VALUES (251, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 16:28:34');
INSERT INTO `sys_logininfor` VALUES (252, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 16:30:22');
INSERT INTO `sys_logininfor` VALUES (253, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 16:36:20');
INSERT INTO `sys_logininfor` VALUES (254, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 16:38:54');
INSERT INTO `sys_logininfor` VALUES (255, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 16:45:25');
INSERT INTO `sys_logininfor` VALUES (256, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 16:49:56');
INSERT INTO `sys_logininfor` VALUES (257, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 16:50:12');
INSERT INTO `sys_logininfor` VALUES (258, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 16:56:45');
INSERT INTO `sys_logininfor` VALUES (259, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 16:57:27');
INSERT INTO `sys_logininfor` VALUES (260, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 16:57:28');
INSERT INTO `sys_logininfor` VALUES (261, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 17:00:41');
INSERT INTO `sys_logininfor` VALUES (262, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 17:03:59');
INSERT INTO `sys_logininfor` VALUES (263, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 17:08:27');
INSERT INTO `sys_logininfor` VALUES (264, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 17:13:37');
INSERT INTO `sys_logininfor` VALUES (265, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 17:17:13');
INSERT INTO `sys_logininfor` VALUES (266, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '退出成功', '2019-08-28 17:22:57');
INSERT INTO `sys_logininfor` VALUES (267, 'gaobo', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 17:23:03');
INSERT INTO `sys_logininfor` VALUES (268, 'gaobo', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '退出成功', '2019-08-28 17:23:36');
INSERT INTO `sys_logininfor` VALUES (269, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 17:23:38');
INSERT INTO `sys_logininfor` VALUES (270, 'admin', '172.19.187.147', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 17:25:47');
INSERT INTO `sys_logininfor` VALUES (271, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 17:29:47');
INSERT INTO `sys_logininfor` VALUES (272, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 17:32:15');
INSERT INTO `sys_logininfor` VALUES (273, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 17:56:23');
INSERT INTO `sys_logininfor` VALUES (274, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 18:29:27');
INSERT INTO `sys_logininfor` VALUES (275, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 18:59:21');
INSERT INTO `sys_logininfor` VALUES (276, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 19:05:55');
INSERT INTO `sys_logininfor` VALUES (277, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 19:10:28');
INSERT INTO `sys_logininfor` VALUES (278, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 19:15:47');
INSERT INTO `sys_logininfor` VALUES (279, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 19:17:26');
INSERT INTO `sys_logininfor` VALUES (280, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 19:18:37');
INSERT INTO `sys_logininfor` VALUES (281, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 19:22:13');
INSERT INTO `sys_logininfor` VALUES (282, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 19:23:29');
INSERT INTO `sys_logininfor` VALUES (283, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 19:24:57');
INSERT INTO `sys_logininfor` VALUES (284, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '退出成功', '2019-08-28 19:37:38');
INSERT INTO `sys_logininfor` VALUES (285, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 19:37:41');
INSERT INTO `sys_logininfor` VALUES (286, 'admin', '172.19.187.147', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 19:40:59');
INSERT INTO `sys_logininfor` VALUES (287, 'admin', '172.19.187.147', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 19:43:19');
INSERT INTO `sys_logininfor` VALUES (288, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 19:44:45');
INSERT INTO `sys_logininfor` VALUES (289, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 19:56:51');
INSERT INTO `sys_logininfor` VALUES (290, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 20:00:25');
INSERT INTO `sys_logininfor` VALUES (291, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 20:16:34');
INSERT INTO `sys_logininfor` VALUES (292, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-28 20:28:19');
INSERT INTO `sys_logininfor` VALUES (293, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '退出成功', '2019-08-28 20:28:32');
INSERT INTO `sys_logininfor` VALUES (294, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-28 20:28:34');
INSERT INTO `sys_logininfor` VALUES (295, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 20:28:39');
INSERT INTO `sys_logininfor` VALUES (296, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '退出成功', '2019-08-28 20:28:58');
INSERT INTO `sys_logininfor` VALUES (297, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-28 20:29:00');
INSERT INTO `sys_logininfor` VALUES (298, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 20:31:12');
INSERT INTO `sys_logininfor` VALUES (299, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 20:38:08');
INSERT INTO `sys_logininfor` VALUES (300, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-28 20:45:03');
INSERT INTO `sys_logininfor` VALUES (301, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-28 20:46:30');
INSERT INTO `sys_logininfor` VALUES (302, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 09:34:50');
INSERT INTO `sys_logininfor` VALUES (303, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-29 09:41:03');
INSERT INTO `sys_logininfor` VALUES (304, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 09:50:02');
INSERT INTO `sys_logininfor` VALUES (305, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 09:57:21');
INSERT INTO `sys_logininfor` VALUES (306, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 09:58:32');
INSERT INTO `sys_logininfor` VALUES (307, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-29 10:00:16');
INSERT INTO `sys_logininfor` VALUES (308, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 10:02:11');
INSERT INTO `sys_logininfor` VALUES (309, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-29 10:10:11');
INSERT INTO `sys_logininfor` VALUES (310, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-29 10:15:13');
INSERT INTO `sys_logininfor` VALUES (311, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 10:23:30');
INSERT INTO `sys_logininfor` VALUES (312, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 10:24:45');
INSERT INTO `sys_logininfor` VALUES (313, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 10:27:16');
INSERT INTO `sys_logininfor` VALUES (314, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 10:30:01');
INSERT INTO `sys_logininfor` VALUES (315, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-29 10:36:31');
INSERT INTO `sys_logininfor` VALUES (316, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 10:46:14');
INSERT INTO `sys_logininfor` VALUES (317, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 10:58:18');
INSERT INTO `sys_logininfor` VALUES (318, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 11:03:26');
INSERT INTO `sys_logininfor` VALUES (319, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 11:11:36');
INSERT INTO `sys_logininfor` VALUES (320, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-08-29 11:15:40');
INSERT INTO `sys_logininfor` VALUES (321, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 11:22:02');
INSERT INTO `sys_logininfor` VALUES (322, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 11:23:11');
INSERT INTO `sys_logininfor` VALUES (323, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-29 11:25:38');
INSERT INTO `sys_logininfor` VALUES (324, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 7', '0', '登录成功', '2019-08-29 11:30:34');
INSERT INTO `sys_logininfor` VALUES (325, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-08-29 11:32:30');
INSERT INTO `sys_logininfor` VALUES (326, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', '0', '登录成功', '2019-09-02 09:33:22');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2009 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, '#', '', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, '#', '', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, '#', '', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, '/system/user', '', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/system/role', '', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', '', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, '/system/dept', '', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, '/system/post', '', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', '', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', '', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, '/system/notice', '', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, '#', '', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, '/monitor/online', '', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, '/monitor/job', '', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, '/monitor/data', '', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 3, '/monitor/server', '', 'C', '0', 'monitor:server:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '表单构建', 3, 1, '/tool/build', '', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES (114, '代码生成', 3, 2, '/tool/gen', '', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES (115, '系统接口', 3, 3, '/tool/swagger', '', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, '/monitor/operlog', '', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, '/monitor/logininfor', '', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', '', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', '', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', '', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', '', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', '', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '#', '', 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '#', '', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '#', '', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '#', '', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '#', '', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '#', '', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '#', '', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '#', '', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '#', '', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '#', '', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '#', '', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '#', '', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '#', '', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '#', '', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '#', '', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '#', '', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '#', '', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '#', '', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '#', '', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '#', '', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1041, '详细信息', 500, 3, '#', '', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1054, '任务详细', 110, 6, '#', '', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1055, '任务导出', 110, 7, '#', '', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1056, '生成查询', 114, 1, '#', '', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1057, '生成修改', 114, 2, '#', '', 'F', '0', 'tool:gen:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1058, '生成删除', 114, 3, '#', '', 'F', '0', 'tool:gen:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 114, 4, '#', '', 'F', '0', 'tool:gen:preview', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 114, 5, '#', '', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (2000, '接入系统管理', 1, 10, '/cms/cmsSystem', 'menuItem', 'C', '0', 'cms:cmsSystem:view', 'fa fa-adjust', 'admin', '2019-08-26 20:51:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2001, '分类模型', 0, 4, '#', 'menuItem', 'M', '0', '', 'fa fa-ban', 'admin', '2019-08-26 21:10:55', 'admin', '2019-08-28 15:22:18', '');
INSERT INTO `sys_menu` VALUES (2002, '模型管理', 2001, 2, '/cms/cmsModel', 'menuItem', 'C', '0', 'cms:cmsModel:view', 'fa fa-address-card-o', 'admin', '2019-08-26 21:11:20', 'admin', '2019-08-28 15:23:15', '');
INSERT INTO `sys_menu` VALUES (2004, '项目管理', 0, 5, '#', 'menuItem', 'M', '0', NULL, 'fa fa-bank', 'admin', '2019-08-27 14:08:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2005, '项目展示', 2004, 1, '/pms/pmsBatch', 'menuItem', 'C', '0', '', 'fa fa-asterisk', 'admin', '2019-08-27 14:08:31', 'admin', '2019-08-27 14:18:13', '');
INSERT INTO `sys_menu` VALUES (2006, '完成度统计', 2004, 2, '#', 'menuItem', 'C', '0', NULL, 'fa fa-bar-chart', 'admin', '2019-08-27 14:08:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2007, '分类管理', 2001, 3, '/cms/cmsBill', 'menuItem', 'C', '0', 'cms:cmsBill:view', 'fa fa-at', 'admin', '2019-08-27 19:55:39', 'admin', '2019-08-28 15:23:30', '');
INSERT INTO `sys_menu` VALUES (2008, '分类角色管理', 2001, 1, '/cms/cmsRole', 'menuItem', 'C', '0', 'cms:cmsRole:view', 'fa fa-address-book-o', 'admin', '2019-08-28 15:23:02', '', NULL, '');
COMMIT;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) DEFAULT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
BEGIN;
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
COMMIT;

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_oper_log` VALUES (100, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.genCode()', 1, 'admin', NULL, '/tool/gen/genCode/cms_system', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-08-26 14:57:20');
INSERT INTO `sys_oper_log` VALUES (101, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\n  \"tables\" : [ \"cms_keyword,cms_system,cms_model,cms_bill,cms_image,cms_file,cms_role_bill,cms_user_bill_tmp,cms_model_bill,cms_post_bill\" ]\n}', 0, NULL, '2019-08-26 16:43:54');
INSERT INTO `sys_oper_log` VALUES (102, '部门管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysDeptController.addSave()', 1, 'admin', NULL, '/system/dept/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"2000\" ],\n  \"deptName\" : [ \"11\" ],\n  \"orderNum\" : [ \"11\" ],\n  \"leader\" : [ \"1\" ],\n  \"phone\" : [ \"13698899987\" ],\n  \"email\" : [ \"baixin@163.com\" ],\n  \"status\" : [ \"0\" ]\n}', 0, NULL, '2019-08-26 17:28:23');
INSERT INTO `sys_oper_log` VALUES (103, '部门管理', 3, 'net.northking.iacmp.cms.web.controller.system.SysDeptController.remove()', 1, 'admin', NULL, '/system/dept/remove/2005', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-08-26 17:28:40');
INSERT INTO `sys_oper_log` VALUES (104, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.genCode()', 1, 'admin', NULL, '/tool/gen/genCode/cms_keyword', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-08-26 18:24:17');
INSERT INTO `sys_oper_log` VALUES (105, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\r\n  \"tables\" : [ \"cms_keyword,pms_batch,cms_file,cms_image,cms_system,cms_model,cms_bill,cms_role_bill\" ]\r\n}', 0, NULL, '2019-08-26 18:27:40');
INSERT INTO `sys_oper_log` VALUES (106, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\r\n  \"tables\" : [ \"cms_user_bill_tmp,cms_model_bill\" ]\r\n}', 0, NULL, '2019-08-26 18:27:57');
INSERT INTO `sys_oper_log` VALUES (107, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\n  \"tables\" : [ \"cms_keyword,pms_batch,cms_file,cms_image,cms_system,cms_model,cms_bill,cms_role_bill,cms_user_bill_tmp,cms_model_bill\" ]\n}', 0, NULL, '2019-08-26 18:59:05');
INSERT INTO `sys_oper_log` VALUES (108, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\n  \"tables\" : [ \"cms_keyword,pms_batch,cms_file,cms_image,cms_system,cms_model,cms_bill,cms_role_bill,cms_user_bill_tmp,cms_model_bill\" ]\n}', 0, NULL, '2019-08-26 19:17:55');
INSERT INTO `sys_oper_log` VALUES (109, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\n  \"tables\" : [ \"cms_image,cms_file,pms_batch,cms_keyword,cms_system,cms_model,cms_bill,cms_role_bill,cms_user_bill_tmp,cms_model_bill\" ]\n}', 0, NULL, '2019-08-26 19:34:11');
INSERT INTO `sys_oper_log` VALUES (110, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\n  \"tables\" : [ \"cms_image,cms_file,pms_batch,cms_keyword,cms_system,cms_model,cms_bill,cms_role_bill,cms_user_bill_tmp,cms_model_bill\" ]\n}', 0, NULL, '2019-08-26 19:38:44');
INSERT INTO `sys_oper_log` VALUES (111, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\n  \"tables\" : [ \"pms_batch,cms_file\" ]\n}', 0, NULL, '2019-08-26 19:54:13');
INSERT INTO `sys_oper_log` VALUES (112, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\n  \"tables\" : [ \"pms_batch,cms_file\" ]\n}', 0, NULL, '2019-08-26 19:59:41');
INSERT INTO `sys_oper_log` VALUES (113, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\n  \"tables\" : [ \"pms_batch,cms_file\" ]\n}', 0, NULL, '2019-08-26 20:02:00');
INSERT INTO `sys_oper_log` VALUES (114, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\n  \"tables\" : [ \"pms_batch,cms_file\" ]\n}', 0, NULL, '2019-08-26 20:05:16');
INSERT INTO `sys_oper_log` VALUES (115, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.genCode()', 1, 'admin', NULL, '/tool/gen/genCode/pms_batch', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-08-26 20:46:45');
INSERT INTO `sys_oper_log` VALUES (116, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.genCode()', 1, 'admin', NULL, '/tool/gen/genCode/pms_batch', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-08-26 20:51:00');
INSERT INTO `sys_oper_log` VALUES (117, '菜单管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"1\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"接入系统管理\" ],\n  \"url\" : [ \"/cms/cmsSystem\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"cms:cmsSystem:view\" ],\n  \"orderNum\" : [ \"10\" ],\n  \"icon\" : [ \"fa fa-adjust\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-26 20:51:38');
INSERT INTO `sys_oper_log` VALUES (118, '菜单管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"menuType\" : [ \"M\" ],\n  \"menuName\" : [ \"模型设计\" ],\n  \"url\" : [ \"\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"\" ],\n  \"orderNum\" : [ \"4\" ],\n  \"icon\" : [ \"fa fa-ban\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-26 21:10:55');
INSERT INTO `sys_oper_log` VALUES (119, '菜单管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"2001\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"模型管理\" ],\n  \"url\" : [ \"\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"\" ],\n  \"orderNum\" : [ \"1\" ],\n  \"icon\" : [ \"fa fa-address-card-o\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-26 21:11:20');
INSERT INTO `sys_oper_log` VALUES (120, '菜单管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"2001\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"模型分类管理\" ],\n  \"url\" : [ \"\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"\" ],\n  \"orderNum\" : [ \"2\" ],\n  \"icon\" : [ \"fa fa-adjust\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-26 21:12:06');
INSERT INTO `sys_oper_log` VALUES (121, '菜单管理', 2, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.editSave()', 1, 'admin', NULL, '/system/menu/edit', '127.0.0.1', '内网IP', '{\n  \"menuId\" : [ \"2002\" ],\n  \"parentId\" : [ \"2001\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"模型管理\" ],\n  \"url\" : [ \"/cms/cmsModel\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"cms:cmsModel:view\" ],\n  \"orderNum\" : [ \"1\" ],\n  \"icon\" : [ \"fa fa-address-card-o\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-26 21:18:14');
INSERT INTO `sys_oper_log` VALUES (122, '菜单管理', 2, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.editSave()', 1, 'admin', NULL, '/system/menu/edit', '127.0.0.1', '内网IP', '{\n  \"menuId\" : [ \"2003\" ],\n  \"parentId\" : [ \"2001\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"模型分类管理\" ],\n  \"url\" : [ \"/cms/cmsBill\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"cms:cmsBill:view\" ],\n  \"orderNum\" : [ \"2\" ],\n  \"icon\" : [ \"fa fa-adjust\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-26 21:22:19');
INSERT INTO `sys_oper_log` VALUES (123, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"billId\" : [ \"\" ],\n  \"billName\" : [ \"\" ],\n  \"billCode\" : [ \"\" ],\n  \"parentId\" : [ \"\" ],\n  \"billOrder\" : [ \"\" ],\n  \"lEAF\" : [ \"\" ],\n  \"allPath\" : [ \"\" ],\n  \"sTATUS\" : [ \"\" ],\n  \"createBy\" : [ \"\" ],\n  \"createTime\" : [ \"\" ],\n  \"updateBy\" : [ \"\" ],\n  \"updateTime\" : [ \"\" ]\n}', 1, '\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsBillMapper.insertCmsBill-Inline\n### The error occurred while setting parameters\n### SQL: insert into cms_bill\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1', '2019-08-27 09:24:28');
INSERT INTO `sys_oper_log` VALUES (124, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\r\n  \"modelId\" : [ \"\" ],\r\n  \"modelName\" : [ \"\" ],\r\n  \"modelCode\" : [ \"\" ],\r\n  \"deptId\" : [ \"\" ],\r\n  \"createBy\" : [ \"\" ],\r\n  \"createTime\" : [ \"\" ],\r\n  \"updateBy\" : [ \"\" ],\r\n  \"updateTime\" : [ \"\" ],\r\n  \"rEMARK\" : [ \"\" ]\r\n}', 1, '\r\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\r\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsModelMapper.insertCmsModel-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cms_model\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1', '2019-08-27 09:26:36');
INSERT INTO `sys_oper_log` VALUES (125, '接入系统', 1, 'net.northking.iacmp.cms.web.sysController.CmsSystemController.addSave()', 1, 'admin', NULL, '/cms/cmsSystem/add', '127.0.0.1', '内网IP', '{\n  \"sysCode\" : [ \"pms\" ],\n  \"sysName\" : [ \"项目管理系统\" ],\n  \"authentInfo\" : [ \"baixinpms\" ],\n  \"remark\" : [ \"项目管理系统\" ],\n  \"sysIp\" : [ \"127.0.0.1\" ]\n}', 1, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'SYS_ID\' doesn\'t have a default value\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsSystemMapper.insertCmsSystem-Inline\n### The error occurred while setting parameters\n### SQL: insert into cms_system          ( SYS_CODE,             SYS_NAME,             AUTHENT_INFO,             REMARK,             SYS_IP )           values ( ?,             ?,             ?,             ?,             ? )\n### Cause: java.sql.SQLException: Field \'SYS_ID\' doesn\'t have a default value\n; Field \'SYS_ID\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'SYS_ID\' doesn\'t have a default value', '2019-08-27 09:39:32');
INSERT INTO `sys_oper_log` VALUES (126, '接入系统', 1, 'net.northking.iacmp.cms.web.sysController.CmsSystemController.addSave()', 1, 'admin', NULL, '/cms/cmsSystem/add', '127.0.0.1', '内网IP', '{\n  \"sysCode\" : [ \"pms\" ],\n  \"sysName\" : [ \"项目管理系统\" ],\n  \"authentInfo\" : [ \"baixinpms\" ],\n  \"remark\" : [ \"项目管理系统\" ],\n  \"sysIp\" : [ \"127.0.0.1\" ]\n}', 1, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'SYS_ID\' doesn\'t have a default value\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsSystemMapper.insertCmsSystem-Inline\n### The error occurred while setting parameters\n### SQL: insert into cms_system          ( SYS_CODE,             SYS_NAME,             AUTHENT_INFO,             REMARK,             SYS_IP )           values ( ?,             ?,             ?,             ?,             ? )\n### Cause: java.sql.SQLException: Field \'SYS_ID\' doesn\'t have a default value\n; Field \'SYS_ID\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'SYS_ID\' doesn\'t have a default value', '2019-08-27 09:45:19');
INSERT INTO `sys_oper_log` VALUES (127, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\r\n  \"modelId\" : [ \"\" ],\r\n  \"modelName\" : [ \"\" ],\r\n  \"deptId\" : [ \"\" ],\r\n  \"rEMARK\" : [ \"\" ]\r\n}', 1, '\r\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\r\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsModelMapper.insertCmsModel-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cms_model\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1', '2019-08-27 10:26:35');
INSERT INTO `sys_oper_log` VALUES (128, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\r\n  \"modelId\" : [ \"\" ],\r\n  \"modelName\" : [ \"\" ],\r\n  \"deptId\" : [ \"\" ],\r\n  \"rEMARK\" : [ \"\" ]\r\n}', 1, '\r\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\r\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsModelMapper.insertCmsModel-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cms_model\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1', '2019-08-27 10:28:01');
INSERT INTO `sys_oper_log` VALUES (129, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\r\n  \"billId\" : [ \"\" ],\r\n  \"billName\" : [ \"\" ],\r\n  \"billCode\" : [ \"\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"billOrder\" : [ \"\" ],\r\n  \"lEAF\" : [ \"\" ],\r\n  \"allPath\" : [ \"\" ],\r\n  \"sTATUS\" : [ \"\" ],\r\n  \"createBy\" : [ \"\" ],\r\n  \"createTime\" : [ \"\" ],\r\n  \"updateBy\" : [ \"\" ],\r\n  \"updateTime\" : [ \"\" ]\r\n}', 1, '\r\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\r\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsBillMapper.insertCmsBill-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cms_bill\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1', '2019-08-27 10:28:43');
INSERT INTO `sys_oper_log` VALUES (130, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\n  \"tables\" : [ \"cms_role_bill,cms_model,cms_keyword,cms_user_bill_tmp,cms_system,cms_bill,cms_model_bill\" ]\n}', 0, NULL, '2019-08-27 10:51:05');
INSERT INTO `sys_oper_log` VALUES (131, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\r\n  \"modelId\" : [ \"\" ],\r\n  \"modelName\" : [ \"\" ],\r\n  \"deptId\" : [ \"\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 1, '\r\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\r\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsModelMapper.insertCmsModel-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cms_model\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1', '2019-08-27 11:02:17');
INSERT INTO `sys_oper_log` VALUES (132, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型1\" ],\n  \"modelCode\" : [ \"model1\" ],\n  \"deptId\" : [ \"\", \"消费金融事业部\" ],\n  \"remark\" : [ \"测试模型\" ]\n}', 0, NULL, '2019-08-27 11:29:41');
INSERT INTO `sys_oper_log` VALUES (133, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"billName\" : [ \"\" ],\r\n  \"billCode\" : [ \"\" ],\r\n  \"fileName\" : [ \"\" ]\r\n}', 1, '\r\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\r\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsBillMapper.insertCmsBill-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cms_bill\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\' at line 1', '2019-08-27 11:33:47');
INSERT INTO `sys_oper_log` VALUES (134, '模型', 3, 'net.northking.iacmp.cms.web.modelController.CmsModelController.remove()', 1, 'admin', NULL, '/cms/cmsModel/remove', '127.0.0.1', '内网IP', '{\n  \"ids\" : [ \"1\" ]\n}', 0, NULL, '2019-08-27 11:36:06');
INSERT INTO `sys_oper_log` VALUES (135, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型1\" ],\n  \"modelCode\" : [ \"model1\" ],\n  \"deptId\" : [ \"\", \"消费金融事业部\" ],\n  \"remark\" : [ \"test测试\" ]\n}', 0, NULL, '2019-08-27 11:37:14');
INSERT INTO `sys_oper_log` VALUES (136, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型2\" ],\n  \"modelCode\" : [ \"model2\" ],\n  \"deptId\" : [ \"\", \"运维\" ],\n  \"remark\" : [ \"test2\" ]\n}', 0, NULL, '2019-08-27 13:07:49');
INSERT INTO `sys_oper_log` VALUES (137, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型三\" ],\n  \"modelCode\" : [ \"model3\" ],\n  \"deptId\" : [ \"\" ],\n  \"remark\" : [ \"test3\" ]\n}', 0, NULL, '2019-08-27 13:09:06');
INSERT INTO `sys_oper_log` VALUES (138, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型四\" ],\n  \"modelCode\" : [ \"model4\" ],\n  \"deptId\" : [ \"\" ],\n  \"remark\" : [ \"test4\" ]\n}', 0, NULL, '2019-08-27 13:17:14');
INSERT INTO `sys_oper_log` VALUES (139, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型5\" ],\n  \"modelCode\" : [ \"model5\" ],\n  \"deptId\" : [ \"1001\" ],\n  \"remark\" : [ \"test5\" ]\n}', 0, NULL, '2019-08-27 13:18:56');
INSERT INTO `sys_oper_log` VALUES (140, '模型', 3, 'net.northking.iacmp.cms.web.modelController.CmsModelController.remove()', 1, 'admin', NULL, '/cms/cmsModel/remove', '127.0.0.1', '内网IP', '{\n  \"ids\" : [ \"2,3,4,5\" ]\n}', 0, NULL, '2019-08-27 13:19:25');
INSERT INTO `sys_oper_log` VALUES (141, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"\" ],\n  \"modelCode\" : [ \"\" ],\n  \"deptId\" : [ \"\" ],\n  \"remark\" : [ \"\" ]\n}', 0, NULL, '2019-08-27 13:36:24');
INSERT INTO `sys_oper_log` VALUES (142, '模型', 3, 'net.northking.iacmp.cms.web.modelController.CmsModelController.remove()', 1, 'admin', NULL, '/cms/cmsModel/remove', '127.0.0.1', '内网IP', '{\n  \"ids\" : [ \"7\" ]\n}', 0, NULL, '2019-08-27 13:36:38');
INSERT INTO `sys_oper_log` VALUES (143, '菜单管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"menuType\" : [ \"M\" ],\n  \"menuName\" : [ \"项目管理\" ],\n  \"url\" : [ \"\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"\" ],\n  \"orderNum\" : [ \"5\" ],\n  \"icon\" : [ \"fa fa-bank\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-27 14:08:02');
INSERT INTO `sys_oper_log` VALUES (144, '菜单管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"2004\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"项目展示\" ],\n  \"url\" : [ \"\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"\" ],\n  \"orderNum\" : [ \"1\" ],\n  \"icon\" : [ \"fa fa-asterisk\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-27 14:08:31');
INSERT INTO `sys_oper_log` VALUES (145, '菜单管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"2004\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"完成度统计\" ],\n  \"url\" : [ \"\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"\" ],\n  \"orderNum\" : [ \"2\" ],\n  \"icon\" : [ \"fa fa-bar-chart\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-27 14:08:57');
INSERT INTO `sys_oper_log` VALUES (146, '菜单管理', 2, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.editSave()', 1, 'admin', NULL, '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2005\" ],\r\n  \"parentId\" : [ \"2004\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"项目展示\" ],\r\n  \"url\" : [ \"/proManage/pmsBatch\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-asterisk\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-08-27 14:15:16');
INSERT INTO `sys_oper_log` VALUES (147, '菜单管理', 2, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.editSave()', 1, 'admin', NULL, '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2005\" ],\r\n  \"parentId\" : [ \"2004\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"项目展示\" ],\r\n  \"url\" : [ \"/pms/pmsBatch\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-asterisk\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-08-27 14:18:13');
INSERT INTO `sys_oper_log` VALUES (148, '影像批次', 3, 'net.northking.iacmp.pms.controller.PmsBatchController.remove()', 1, 'admin', NULL, '/pms/pmsBatch/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"undefined\" ]\r\n}', 0, NULL, '2019-08-27 14:50:23');
INSERT INTO `sys_oper_log` VALUES (149, '模型', 1, 'net.northking.iacmp.cms.web.modelController.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型一\" ],\n  \"modelCode\" : [ \"model1\" ],\n  \"deptId\" : [ \"2000\" ],\n  \"remark\" : [ \"test1\" ]\n}', 0, NULL, '2019-08-27 14:54:59');
INSERT INTO `sys_oper_log` VALUES (150, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"billName\" : [ \"类型1\" ],\n  \"billCode\" : [ \"bill1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-27 15:06:48');
INSERT INTO `sys_oper_log` VALUES (151, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"billName\" : [ \"节点1\" ],\n  \"billCode\" : [ \"trg1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-27 15:35:46');
INSERT INTO `sys_oper_log` VALUES (152, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"billName\" : [ \"节点1\" ],\n  \"billCode\" : [ \"trg1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-27 16:44:32');
INSERT INTO `sys_oper_log` VALUES (153, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"billName\" : [ \"节点2\" ],\n  \"billCode\" : [ \"trg2\" ],\n  \"orderNum\" : [ \"2\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-27 16:46:24');
INSERT INTO `sys_oper_log` VALUES (154, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"billName\" : [ \"节点4\" ],\n  \"billCode\" : [ \"trg4\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-27 16:47:10');
INSERT INTO `sys_oper_log` VALUES (155, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"billName\" : [ \"节点1\" ],\n  \"billCode\" : [ \"test1\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-27 16:48:00');
INSERT INTO `sys_oper_log` VALUES (156, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"billName\" : [ \"节点2\" ],\n  \"billCode\" : [ \"trg2\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-27 17:18:36');
INSERT INTO `sys_oper_log` VALUES (157, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"billName\" : [ \"节点三\" ],\n  \"billCode\" : [ \"trg3\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-27 17:26:47');
INSERT INTO `sys_oper_log` VALUES (158, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"6\" ],\n  \"billName\" : [ \"节点5\" ],\n  \"billCode\" : [ \"trg5\" ],\n  \"billOrder\" : [ \"2\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-27 17:29:39');
INSERT INTO `sys_oper_log` VALUES (159, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"5\" ],\n  \"billName\" : [ \"节点6\" ],\n  \"billCode\" : [ \"trg6\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-27 17:38:48');
INSERT INTO `sys_oper_log` VALUES (160, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"10\" ],\r\n  \"billName\" : [ \"321\" ],\r\n  \"billCode\" : [ \"3\" ],\r\n  \"billOrder\" : [ \"3\" ],\r\n  \"fileName\" : [ \"\" ]\r\n}', 0, NULL, '2019-08-27 17:59:53');
INSERT INTO `sys_oper_log` VALUES (161, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.genCode()', 1, 'admin', NULL, '/tool/gen/genCode/cms_bill', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-08-27 19:08:28');
INSERT INTO `sys_oper_log` VALUES (162, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"cmsModel.id\" : [ \"8\" ],\n  \"parentId\" : [ \"\" ],\n  \"billName\" : [ \"节点11\" ],\n  \"billCode\" : [ \"11\" ],\n  \"billOrder\" : [ \"11\" ],\n  \"fileName\" : [ \"\" ]\n}', 1, '', '2019-08-27 19:40:13');
INSERT INTO `sys_oper_log` VALUES (163, '菜单管理', 3, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.remove()', 1, 'admin', NULL, '/system/menu/remove/2003', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-08-27 19:47:44');
INSERT INTO `sys_oper_log` VALUES (164, '菜单管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"2001\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"模型分类管理\" ],\n  \"url\" : [ \"\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"\" ],\n  \"orderNum\" : [ \"2\" ],\n  \"icon\" : [ \"fa fa-at\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-27 19:55:39');
INSERT INTO `sys_oper_log` VALUES (165, '菜单管理', 2, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.editSave()', 1, 'admin', NULL, '/system/menu/edit', '127.0.0.1', '内网IP', '{\n  \"menuId\" : [ \"2007\" ],\n  \"parentId\" : [ \"2001\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"模型分类管理\" ],\n  \"url\" : [ \"/cms/cmsBill\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"cms:cmsBill:view\" ],\n  \"orderNum\" : [ \"2\" ],\n  \"icon\" : [ \"fa fa-at\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-28 09:15:35');
INSERT INTO `sys_oper_log` VALUES (166, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"7\" ],\n  \"billName\" : [ \"节点22\" ],\n  \"billCode\" : [ \"trg22\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 09:19:25');
INSERT INTO `sys_oper_log` VALUES (167, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"\" ],\n  \"billName\" : [ \"节点7\" ],\n  \"billCode\" : [ \"trg7\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 1, '', '2019-08-28 09:19:43');
INSERT INTO `sys_oper_log` VALUES (168, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"\" ],\n  \"billName\" : [ \"节点7\" ],\n  \"billCode\" : [ \"trg7\" ],\n  \"billOrder\" : [ \"11\" ],\n  \"fileName\" : [ \"\" ]\n}', 1, '', '2019-08-28 09:19:51');
INSERT INTO `sys_oper_log` VALUES (169, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"\" ],\n  \"billName\" : [ \"节点6\" ],\n  \"billCode\" : [ \"trg6\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 1, '', '2019-08-28 09:28:09');
INSERT INTO `sys_oper_log` VALUES (170, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"\" ],\n  \"billName\" : [ \"节点6\" ],\n  \"billCode\" : [ \"trg7\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 1, '', '2019-08-28 09:28:38');
INSERT INTO `sys_oper_log` VALUES (171, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"\" ],\n  \"billName\" : [ \"节点6\" ],\n  \"billCode\" : [ \"trg7\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 1, '', '2019-08-28 09:29:15');
INSERT INTO `sys_oper_log` VALUES (172, '分类', 1, 'net.northking.iacmp.cms.web.modelController.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"10\" ],\n  \"billName\" : [ \"123\" ],\n  \"billCode\" : [ \"123\" ],\n  \"billOrder\" : [ \"2\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 10:08:05');
INSERT INTO `sys_oper_log` VALUES (173, '分类', 2, 'net.northking.iacmp.cms.web.modelController.CmsBillController.editSave()', 1, 'admin', NULL, '/cms/cmsBill/edit', '127.0.0.1', '内网IP', '{\n  \"id\" : [ \"5\" ],\n  \"billName\" : [ \"节点3\" ],\n  \"billCode\" : [ \"trg4\" ],\n  \"parentId\" : [ \"0\" ],\n  \"billOrder\" : [ \"1\" ]\n}', 0, NULL, '2019-08-28 10:15:24');
INSERT INTO `sys_oper_log` VALUES (174, '角色管理', 2, 'net.northking.iacmp.cms.web.controller.system.SysRoleController.editSave()', 1, 'admin', NULL, '/system/role/edit', '127.0.0.1', '内网IP', '{\n  \"roleId\" : [ \"1\" ],\n  \"roleName\" : [ \"管理员\" ],\n  \"roleKey\" : [ \"admin\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"管理员\" ],\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,2000,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,3,113,114,1056,1057,1058,1059,1060,115,2001,2002,2007,2004,2005,2006\" ]\n}', 0, NULL, '2019-08-28 13:48:58');
INSERT INTO `sys_oper_log` VALUES (175, '角色管理', 2, 'net.northking.iacmp.cms.web.controller.system.SysRoleController.authDataScopeSave()', 1, 'admin', NULL, '/system/role/authDataScope', '127.0.0.1', '内网IP', '{\n  \"roleId\" : [ \"1\" ],\n  \"roleName\" : [ \"管理员\" ],\n  \"roleKey\" : [ \"admin\" ],\n  \"dataScope\" : [ \"2\" ],\n  \"deptIds\" : [ \"1000,1001,1002\" ]\n}', 0, NULL, '2019-08-28 13:49:09');
INSERT INTO `sys_oper_log` VALUES (176, '代码生成', 8, 'net.northking.iacmp.generator.controller.GenController.batchGenCode()', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\n  \"tables\" : [ \"cms_user_role,cms_role,cms_role_bill\" ]\n}', 0, NULL, '2019-08-28 15:03:55');
INSERT INTO `sys_oper_log` VALUES (177, '菜单管理', 2, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.editSave()', 1, 'admin', NULL, '/system/menu/edit', '127.0.0.1', '内网IP', '{\n  \"menuId\" : [ \"2001\" ],\n  \"parentId\" : [ \"0\" ],\n  \"menuType\" : [ \"M\" ],\n  \"menuName\" : [ \"分类模型\" ],\n  \"url\" : [ \"#\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"\" ],\n  \"orderNum\" : [ \"4\" ],\n  \"icon\" : [ \"fa fa-ban\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-28 15:22:18');
INSERT INTO `sys_oper_log` VALUES (178, '菜单管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\n  \"parentId\" : [ \"2001\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"分类角色管理\" ],\n  \"url\" : [ \"/cms/cmsRole\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"cms:cmsRole:view\" ],\n  \"orderNum\" : [ \"1\" ],\n  \"icon\" : [ \"fa fa-address-book-o\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-28 15:23:02');
INSERT INTO `sys_oper_log` VALUES (179, '菜单管理', 2, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.editSave()', 1, 'admin', NULL, '/system/menu/edit', '127.0.0.1', '内网IP', '{\n  \"menuId\" : [ \"2002\" ],\n  \"parentId\" : [ \"2001\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"模型管理\" ],\n  \"url\" : [ \"/cms/cmsModel\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"cms:cmsModel:view\" ],\n  \"orderNum\" : [ \"2\" ],\n  \"icon\" : [ \"fa fa-address-card-o\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-28 15:23:15');
INSERT INTO `sys_oper_log` VALUES (180, '菜单管理', 2, 'net.northking.iacmp.cms.web.controller.system.SysMenuController.editSave()', 1, 'admin', NULL, '/system/menu/edit', '127.0.0.1', '内网IP', '{\n  \"menuId\" : [ \"2007\" ],\n  \"parentId\" : [ \"2001\" ],\n  \"menuType\" : [ \"C\" ],\n  \"menuName\" : [ \"分类管理\" ],\n  \"url\" : [ \"/cms/cmsBill\" ],\n  \"target\" : [ \"menuItem\" ],\n  \"perms\" : [ \"cms:cmsBill:view\" ],\n  \"orderNum\" : [ \"3\" ],\n  \"icon\" : [ \"fa fa-at\" ],\n  \"visible\" : [ \"0\" ]\n}', 0, NULL, '2019-08-28 15:23:30');
INSERT INTO `sys_oper_log` VALUES (181, '角色管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysRoleController.addSave()', 1, 'admin', NULL, '/system/role/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"测试\" ],\n  \"roleKey\" : [ \"111\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"11\" ],\n  \"menuIds\" : [ \"5,10,11,13\" ]\n}', 0, NULL, '2019-08-28 16:21:30');
INSERT INTO `sys_oper_log` VALUES (182, '角色管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysRoleController.addSave()', 1, 'admin', NULL, '/system/role/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"ceshi\" ],\n  \"roleKey\" : [ \"11111\" ],\n  \"roleSort\" : [ \"111\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"fds\" ],\n  \"menuIds\" : [ \"5,10,11,13\" ]\n}', 0, NULL, '2019-08-28 16:25:34');
INSERT INTO `sys_oper_log` VALUES (183, '角色管理', 3, 'net.northking.iacmp.cms.web.controller.system.SysRoleController.remove()', 1, 'admin', NULL, '/system/role/remove', '127.0.0.1', '内网IP', '{\n  \"ids\" : [ \"100,101\" ]\n}', 0, NULL, '2019-08-28 16:26:30');
INSERT INTO `sys_oper_log` VALUES (184, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"ceshi11\" ],\n  \"roleKey\" : [ \"1\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"menuIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'\n### The error may exist in file [/Users/amor/Documents/project/work/iacmp-parent/iacmp-biz/iacmp-biz-cms-server/target/classes/mapper/cms/CmsRoleMapper.xml]\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.checkRoleNameUnique-Inline\n### The error occurred while setting parameters\n### SQL: select role_id, role_name, role_key, role_sort, data_scope, status, del_flag, create_by, create_time, update_by, update_time, remark, id from cms_role               where r.role_name=?\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'', '2019-08-28 16:31:21');
INSERT INTO `sys_oper_log` VALUES (185, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"ceshi1111\" ],\n  \"roleKey\" : [ \"1\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'\n### The error may exist in file [/Users/amor/Documents/project/work/iacmp-parent/iacmp-biz/iacmp-biz-cms-server/target/classes/mapper/cms/CmsRoleMapper.xml]\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.checkRoleNameUnique-Inline\n### The error occurred while setting parameters\n### SQL: select role_id, role_name, role_key, role_sort, data_scope, status, del_flag, create_by, create_time, update_by, update_time, remark, id from cms_role               where r.role_name=?\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'', '2019-08-28 16:34:20');
INSERT INTO `sys_oper_log` VALUES (186, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"ceshi1111\" ],\n  \"roleKey\" : [ \"1\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'\n### The error may exist in file [/Users/amor/Documents/project/work/iacmp-parent/iacmp-biz/iacmp-biz-cms-server/target/classes/mapper/cms/CmsRoleMapper.xml]\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.checkRoleNameUnique-Inline\n### The error occurred while setting parameters\n### SQL: select role_id, role_name, role_key, role_sort, data_scope, status, del_flag, create_by, create_time, update_by, update_time, remark, id from cms_role               where r.role_name=?\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'', '2019-08-28 16:37:10');
INSERT INTO `sys_oper_log` VALUES (187, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"ceshi1111\" ],\n  \"roleKey\" : [ \"1\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'\n### The error may exist in file [/Users/amor/Documents/project/work/iacmp-parent/iacmp-biz/iacmp-biz-cms-server/target/classes/mapper/cms/CmsRoleMapper.xml]\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.checkRoleNameUnique-Inline\n### The error occurred while setting parameters\n### SQL: select role_id, role_name, role_key, role_sort, data_scope, status, del_flag, create_by, create_time, update_by, update_time, remark, id from cms_role               where r.role_name=?\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'', '2019-08-28 16:37:10');
INSERT INTO `sys_oper_log` VALUES (188, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"ceshi1111\" ],\n  \"roleKey\" : [ \"1\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'\n### The error may exist in file [/Users/amor/Documents/project/work/iacmp-parent/iacmp-biz/iacmp-biz-cms-server/target/classes/mapper/cms/CmsRoleMapper.xml]\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.checkRoleNameUnique-Inline\n### The error occurred while setting parameters\n### SQL: select role_id, role_name, role_key, role_sort, data_scope, status, del_flag, create_by, create_time, update_by, update_time, remark, id from cms_role               where r.role_name=?\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'r.role_name\' in \'where clause\'', '2019-08-28 16:37:10');
INSERT INTO `sys_oper_log` VALUES (189, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"ceshi111\" ],\n  \"roleKey\" : [ \"111\" ],\n  \"roleSort\" : [ \"111\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.insertCmsRole-Inline\n### The error occurred while setting parameters\n### SQL: insert into cms_role          ( role_name,             role_key,             role_sort,                          status,                          create_by,                                                    remark )           values ( ?,             ?,             ?,                          ?,                          ?,                                                    ? )\n### Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n; Field \'role_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'role_id\' doesn\'t have a default value', '2019-08-28 16:40:48');
INSERT INTO `sys_oper_log` VALUES (190, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"ceshi\" ],\n  \"roleKey\" : [ \"1\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.insertCmsRole-Inline\n### The error occurred while setting parameters\n### SQL: insert into cms_role          ( role_name,             role_key,             role_sort,                          status,                          create_by,                                                    remark )           values ( ?,             ?,             ?,                          ?,                          ?,                                                    ? )\n### Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n; Field \'role_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'role_id\' doesn\'t have a default value', '2019-08-28 16:45:43');
INSERT INTO `sys_oper_log` VALUES (191, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"ceshi\" ],\n  \"roleKey\" : [ \"1\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.insertCmsRole-Inline\n### The error occurred while setting parameters\n### SQL: insert into cms_role          ( role_name,             role_key,             role_sort,                          status,                          create_by,                                                    remark )           values ( ?,             ?,             ?,                          ?,                          ?,                                                    ? )\n### Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n; Field \'role_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'role_id\' doesn\'t have a default value', '2019-08-28 16:47:18');
INSERT INTO `sys_oper_log` VALUES (192, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"ceshi1\" ],\n  \"roleKey\" : [ \"1\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.insertCmsRole-Inline\n### The error occurred while setting parameters\n### SQL: insert into cms_role          ( role_name,             role_key,             role_sort,                          status,                          create_by,                                                    remark )           values ( ?,             ?,             ?,                          ?,                          ?,                                                    ? )\n### Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n; Field \'role_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'role_id\' doesn\'t have a default value', '2019-08-28 16:50:11');
INSERT INTO `sys_oper_log` VALUES (193, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"ceshi1\" ],\n  \"roleKey\" : [ \"1\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.insertCmsRole-Inline\n### The error occurred while setting parameters\n### SQL: insert into cms_role          ( role_name,             role_key,             role_sort,                          status,                          create_by,                                                    remark )           values ( ?,             ?,             ?,                          ?,                          ?,                                                    ? )\n### Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n; Field \'role_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'role_id\' doesn\'t have a default value', '2019-08-28 16:54:33');
INSERT INTO `sys_oper_log` VALUES (194, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"11\" ],\n  \"roleKey\" : [ \"11\" ],\n  \"roleSort\" : [ \"11\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"11\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.insertCmsRole-Inline\n### The error occurred while setting parameters\n### SQL: insert into cms_role          ( role_name,             role_key,             role_sort,                          status,                          create_by,                                                    remark )           values ( ?,             ?,             ?,                          ?,                          ?,                                                    ? )\n### Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n; Field \'role_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'role_id\' doesn\'t have a default value', '2019-08-28 16:57:50');
INSERT INTO `sys_oper_log` VALUES (195, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"11\" ],\n  \"roleKey\" : [ \"11\" ],\n  \"roleSort\" : [ \"11\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"11\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.insertCmsRole-Inline\n### The error occurred while setting parameters\n### SQL: insert into cms_role(                    role_name,           role_key,           role_sort,                     status,           remark,           create_by,          create_time         )values(                    ?,           ?,           ?,                     ?,           ?,           ?,          sysdate()         )\n### Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n; Field \'role_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'role_id\' doesn\'t have a default value', '2019-08-28 17:01:02');
INSERT INTO `sys_oper_log` VALUES (196, '角色管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysRoleController.addSave()', 1, 'admin', NULL, '/system/role/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"11\" ],\n  \"roleKey\" : [ \"11\" ],\n  \"roleSort\" : [ \"11\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,2000\" ]\n}', 0, NULL, '2019-08-28 17:03:08');
INSERT INTO `sys_oper_log` VALUES (197, '角色管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysRoleController.addSave()', 1, 'admin', NULL, '/system/role/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"12321\" ],\n  \"roleKey\" : [ \"3\" ],\n  \"roleSort\" : [ \"32\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"1\" ],\n  \"menuIds\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 17:04:10');
INSERT INTO `sys_oper_log` VALUES (198, '角色管理', 1, 'net.northking.iacmp.cms.web.controller.system.SysRoleController.addSave()', 1, 'admin', NULL, '/system/role/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"sdfsdafsad\" ],\n  \"roleKey\" : [ \"111111\" ],\n  \"roleSort\" : [ \"111\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"11\" ],\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,2000\" ]\n}', 0, NULL, '2019-08-28 17:09:12');
INSERT INTO `sys_oper_log` VALUES (199, '角色管理', 3, 'net.northking.iacmp.cms.web.controller.system.SysRoleController.remove()', 1, 'admin', NULL, '/system/role/remove', '127.0.0.1', '内网IP', '{\n  \"ids\" : [ \"102,103,104\" ]\n}', 0, NULL, '2019-08-28 17:09:20');
INSERT INTO `sys_oper_log` VALUES (200, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"111\" ],\n  \"roleKey\" : [ \"1\" ],\n  \"roleSort\" : [ \"11\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"11\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n### The error may involve iacmp.biz.common.dao.mapper.cms.CmsRoleMapper.insertCmsRole-Inline\n### The error occurred while setting parameters\n### SQL: insert into cms_role(                    role_name,           role_key,           role_sort,                     status,           remark,           create_by,          create_time         )values(                    ?,           ?,           ?,                     ?,           ?,           ?,          sysdate()         )\n### Cause: java.sql.SQLException: Field \'role_id\' doesn\'t have a default value\n; Field \'role_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'role_id\' doesn\'t have a default value', '2019-08-28 17:14:11');
INSERT INTO `sys_oper_log` VALUES (201, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"111\" ],\n  \"roleKey\" : [ \"1\" ],\n  \"roleSort\" : [ \"11\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"11\" ],\n  \"billIds\" : [ \"5,10,11,13\" ]\n}', 1, '\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'cms.sys_role_bill\' doesn\'t exist\n### The error may involve defaultParameterMap\n### The error occurred while setting parameters\n### SQL: insert into sys_role_bill(role_id, bill_id) values                        (?,?)          ,              (?,?)          ,              (?,?)          ,              (?,?)\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'cms.sys_role_bill\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'cms.sys_role_bill\' doesn\'t exist', '2019-08-28 17:15:06');
INSERT INTO `sys_oper_log` VALUES (202, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"3123\" ],\n  \"roleKey\" : [ \"11\" ],\n  \"roleSort\" : [ \"11\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"21321\" ],\n  \"billIds\" : [ \"6,9\" ]\n}', 0, NULL, '2019-08-28 17:17:50');
INSERT INTO `sys_oper_log` VALUES (203, '角色管理', 2, 'net.northking.iacmp.cms.web.controller.system.SysRoleController.authDataScopeSave()', 1, 'admin', NULL, '/system/role/authDataScope', '127.0.0.1', '内网IP', '{\n  \"roleId\" : [ \"2\" ],\n  \"roleName\" : [ \"普通角色\" ],\n  \"roleKey\" : [ \"common\" ],\n  \"dataScope\" : [ \"2\" ],\n  \"deptIds\" : [ \"1000\" ]\n}', 0, NULL, '2019-08-28 17:22:54');
INSERT INTO `sys_oper_log` VALUES (204, '模型', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型11\" ],\n  \"modelCode\" : [ \"1\" ],\n  \"deptId\" : [ \"1003\" ],\n  \"remark\" : [ \"111\" ]\n}', 1, '', '2019-08-28 18:59:47');
INSERT INTO `sys_oper_log` VALUES (205, '模型', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型11\" ],\n  \"modelCode\" : [ \"1\" ],\n  \"deptId\" : [ \"1003\" ],\n  \"remark\" : [ \"111\" ]\n}', 1, '', '2019-08-28 19:00:28');
INSERT INTO `sys_oper_log` VALUES (206, '模型', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型11\" ],\n  \"modelCode\" : [ \"1\" ],\n  \"deptId\" : [ \"1003\" ],\n  \"remark\" : [ \"111\" ]\n}', 1, '', '2019-08-28 19:00:53');
INSERT INTO `sys_oper_log` VALUES (207, '模型', 3, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.remove()', 1, 'admin', NULL, '/cms/cmsModel/remove', '127.0.0.1', '内网IP', '{\n  \"ids\" : [ \"9,10,11\" ]\n}', 0, NULL, '2019-08-28 19:01:27');
INSERT INTO `sys_oper_log` VALUES (208, '模型', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型四\" ],\n  \"modelCode\" : [ \"11\" ],\n  \"deptId\" : [ \"1002\" ],\n  \"remark\" : [ \"11\" ]\n}', 1, '', '2019-08-28 19:01:57');
INSERT INTO `sys_oper_log` VALUES (209, '模型', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型四\" ],\n  \"modelCode\" : [ \"11\" ],\n  \"remark\" : [ \"111\" ],\n  \"bills\" : [ \"5,10,11,13,14,15\" ]\n}', 1, '\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'13,5)\n         , \n            (13,10)\n         , \n            (13,11)\n         ,\' at line 3\n### The error may involve defaultParameterMap\n### The error occurred while setting parameters\n### SQL: insert into cms_model_bill(model_id,bill_id)                        (?,?)          ,              (?,?)          ,              (?,?)          ,              (?,?)          ,              (?,?)          ,              (?,?)\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'13,5)\n         , \n            (13,10)\n         , \n            (13,11)\n         ,\' at line 3\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'13,5)\n         , \n            (13,10)\n         , \n            (13,11)\n         ,\' at line 3', '2019-08-28 19:04:33');
INSERT INTO `sys_oper_log` VALUES (210, '模型', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型四\" ],\n  \"modelCode\" : [ \"11\" ],\n  \"remark\" : [ \"11\" ],\n  \"bills\" : [ \"5,10,11,13,14,15,6,9\" ]\n}', 1, '\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'14,5)\n         , \n            (14,10)\n         , \n            (14,11)\n         ,\' at line 3\n### The error may involve defaultParameterMap\n### The error occurred while setting parameters\n### SQL: insert into cms_model_bill(model_id,bill_id)                        (?,?)          ,              (?,?)          ,              (?,?)          ,              (?,?)          ,              (?,?)          ,              (?,?)          ,              (?,?)          ,              (?,?)\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'14,5)\n         , \n            (14,10)\n         , \n            (14,11)\n         ,\' at line 3\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'14,5)\n         , \n            (14,10)\n         , \n            (14,11)\n         ,\' at line 3', '2019-08-28 19:13:26');
INSERT INTO `sys_oper_log` VALUES (211, '模型', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型四\" ],\n  \"modelCode\" : [ \"1111\" ],\n  \"remark\" : [ \"11\" ],\n  \"bills\" : [ \"5,10,11,13,14,15\" ]\n}', 0, NULL, '2019-08-28 19:16:09');
INSERT INTO `sys_oper_log` VALUES (212, '模型', 3, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.remove()', 1, 'admin', NULL, '/cms/cmsModel/remove', '127.0.0.1', '内网IP', '{\n  \"ids\" : [ \"12,13,14\" ]\n}', 0, NULL, '2019-08-28 19:16:29');
INSERT INTO `sys_oper_log` VALUES (213, '模型', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"模型三\" ],\n  \"modelCode\" : [ \"model3\" ],\n  \"deptId\" : [ \"2000\" ],\n  \"remark\" : [ \"11\" ],\n  \"bills\" : [ \"6,9\" ]\n}', 0, NULL, '2019-08-28 19:20:42');
INSERT INTO `sys_oper_log` VALUES (214, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"keji\" ],\n  \"roleKey\" : [ \"1111\" ],\n  \"roleSort\" : [ \"11\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"111\" ],\n  \"billIds\" : [ \"5,10,11,13,14,15\" ]\n}', 0, NULL, '2019-08-28 19:31:37');
INSERT INTO `sys_oper_log` VALUES (215, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"keji\" ],\n  \"roleKey\" : [ \"1111\" ],\n  \"roleSort\" : [ \"11\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"111\" ],\n  \"billIds\" : [ \"5,10,11,13,14,15\" ]\n}', 0, NULL, '2019-08-28 19:31:37');
INSERT INTO `sys_oper_log` VALUES (216, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '127.0.0.1', '内网IP', '{\n  \"roleName\" : [ \"keji\" ],\n  \"roleKey\" : [ \"1111\" ],\n  \"roleSort\" : [ \"11\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"111\" ],\n  \"billIds\" : [ \"5,10,11,13,14,15\" ]\n}', 0, NULL, '2019-08-28 19:31:37');
INSERT INTO `sys_oper_log` VALUES (217, '模型', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '127.0.0.1', '内网IP', '{\n  \"modelName\" : [ \"\" ],\n  \"modelCode\" : [ \"\" ],\n  \"deptId\" : [ \"\" ],\n  \"remark\" : [ \"\" ],\n  \"bills\" : [ \"5,10,11,13,14,15\" ]\n}', 0, NULL, '2019-08-28 19:35:31');
INSERT INTO `sys_oper_log` VALUES (218, '模型', 3, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.remove()', 1, 'admin', NULL, '/cms/cmsModel/remove', '127.0.0.1', '内网IP', '{\n  \"ids\" : [ \"17\" ]\n}', 0, NULL, '2019-08-28 19:41:45');
INSERT INTO `sys_oper_log` VALUES (219, '分类', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '172.19.187.147', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"billName\" : [ \"科技创新部\" ],\n  \"billCode\" : [ \"kjcxb\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 19:42:29');
INSERT INTO `sys_oper_log` VALUES (220, '模型', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '172.19.187.147', '内网IP', '{\n  \"modelName\" : [ \"科技创新部-项目维度\" ],\n  \"modelCode\" : [ \"\" ],\n  \"deptId\" : [ \"\" ],\n  \"remark\" : [ \"\" ],\n  \"bills\" : [ \"16\" ]\n}', 0, NULL, '2019-08-28 19:43:39');
INSERT INTO `sys_oper_log` VALUES (221, '分类', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '172.19.187.147', '内网IP', '{\n  \"parentId\" : [ \"16\" ],\n  \"billName\" : [ \"影像平台项目\" ],\n  \"billCode\" : [ \"yxpt\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 19:44:01');
INSERT INTO `sys_oper_log` VALUES (222, '分类', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '172.19.187.147', '内网IP', '{\n  \"parentId\" : [ \"16\" ],\n  \"billName\" : [ \"内容管理平台\" ],\n  \"billCode\" : [ \"cms\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 19:44:16');
INSERT INTO `sys_oper_log` VALUES (223, '分类', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '172.19.187.147', '内网IP', '{\n  \"parentId\" : [ \"17\" ],\n  \"billName\" : [ \"立项\" ],\n  \"billCode\" : [ \"lx\" ],\n  \"billOrder\" : [ \"\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 19:44:39');
INSERT INTO `sys_oper_log` VALUES (224, '分类', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '172.19.187.147', '内网IP', '{\n  \"parentId\" : [ \"17\" ],\n  \"billName\" : [ \"合同\" ],\n  \"billCode\" : [ \"ht\" ],\n  \"billOrder\" : [ \"2\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 19:45:03');
INSERT INTO `sys_oper_log` VALUES (225, '模型', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.addSave()', 1, 'admin', NULL, '/cms/cmsModel/add', '172.19.187.147', '内网IP', '{\n  \"modelName\" : [ \"\" ],\n  \"modelCode\" : [ \"\" ],\n  \"deptId\" : [ \"\" ],\n  \"remark\" : [ \"\" ],\n  \"bills\" : [ \"16,17,19,20\" ]\n}', 0, NULL, '2019-08-28 19:45:14');
INSERT INTO `sys_oper_log` VALUES (226, '分类角色', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsRoleController.addSave()', 1, 'admin', NULL, '/cms/cmsRole/add', '172.19.187.147', '内网IP', '{\n  \"roleName\" : [ \"科技创新部管理员\" ],\n  \"roleKey\" : [ \"22323\" ],\n  \"roleSort\" : [ \"1\" ],\n  \"status\" : [ \"0\" ],\n  \"remark\" : [ \"\" ],\n  \"billIds\" : [ \"16,17,19,20,18\" ]\n}', 0, NULL, '2019-08-28 19:45:48');
INSERT INTO `sys_oper_log` VALUES (227, '分类', 2, 'net.northking.iacmp.cms.web.controller.bill.CmsBillController.editSave()', 1, 'admin', NULL, '/cms/cmsBill/edit', '172.19.187.147', '内网IP', '{\n  \"id\" : [ \"17\" ],\n  \"billName\" : [ \"项目维度\" ],\n  \"billCode\" : [ \"yxpt\" ],\n  \"parentId\" : [ \"16\" ],\n  \"billOrder\" : [ \"1\" ]\n}', 0, NULL, '2019-08-28 19:49:09');
INSERT INTO `sys_oper_log` VALUES (228, '分类', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '172.19.187.147', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"billName\" : [ \"全行\" ],\n  \"billCode\" : [ \"qh\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 19:56:36');
INSERT INTO `sys_oper_log` VALUES (229, '分类', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '172.19.187.147', '内网IP', '{\n  \"parentId\" : [ \"0\" ],\n  \"billName\" : [ \"合规部\" ],\n  \"billCode\" : [ \"hgb\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 19:57:03');
INSERT INTO `sys_oper_log` VALUES (230, '分类', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '172.19.187.147', '内网IP', '{\n  \"parentId\" : [ \"22\" ],\n  \"billName\" : [ \"全行\" ],\n  \"billCode\" : [ \"qh\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 19:57:43');
INSERT INTO `sys_oper_log` VALUES (231, '分类', 1, 'net.northking.iacmp.cms.web.controller.bill.CmsBillController.addSave()', 1, 'admin', NULL, '/cms/cmsBill/add', '172.19.187.147', '内网IP', '{\n  \"parentId\" : [ \"22\" ],\n  \"billName\" : [ \"部门\" ],\n  \"billCode\" : [ \"bm\" ],\n  \"billOrder\" : [ \"1\" ],\n  \"fileName\" : [ \"\" ]\n}', 0, NULL, '2019-08-28 19:57:59');
INSERT INTO `sys_oper_log` VALUES (232, '模型', 3, 'net.northking.iacmp.cms.web.controller.bill.CmsModelController.remove()', 1, 'admin', NULL, '/cms/cmsModel/remove', '127.0.0.1', '内网IP', '{\n  \"ids\" : [ \"19\" ]\n}', 0, NULL, '2019-09-02 09:34:37');
COMMIT;

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 1, '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-08-28 13:49:09', '管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-08-28 17:22:54', '普通角色');
INSERT INTO `sys_role` VALUES (100, '测试', '111', 1, '1', '0', '2', 'admin', '2019-08-28 16:21:30', '', NULL, '11');
INSERT INTO `sys_role` VALUES (101, 'ceshi', '11111', 111, '1', '0', '2', 'admin', '2019-08-28 16:25:34', '', NULL, 'fds');
INSERT INTO `sys_role` VALUES (102, '11', '11', 11, '1', '0', '2', 'admin', '2019-08-28 17:03:08', '', NULL, '1');
INSERT INTO `sys_role` VALUES (103, '12321', '3', 32, '1', '0', '2', 'admin', '2019-08-28 17:04:10', '', NULL, '1');
INSERT INTO `sys_role` VALUES (104, 'sdfsdafsad', '111111', 111, '1', '0', '2', 'admin', '2019-08-28 17:09:12', '', NULL, '11');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_dept` VALUES (1, 1000);
INSERT INTO `sys_role_dept` VALUES (1, 1001);
INSERT INTO `sys_role_dept` VALUES (1, 1002);
INSERT INTO `sys_role_dept` VALUES (2, 1000);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 100);
INSERT INTO `sys_role_menu` VALUES (1, 101);
INSERT INTO `sys_role_menu` VALUES (1, 102);
INSERT INTO `sys_role_menu` VALUES (1, 103);
INSERT INTO `sys_role_menu` VALUES (1, 104);
INSERT INTO `sys_role_menu` VALUES (1, 105);
INSERT INTO `sys_role_menu` VALUES (1, 106);
INSERT INTO `sys_role_menu` VALUES (1, 107);
INSERT INTO `sys_role_menu` VALUES (1, 108);
INSERT INTO `sys_role_menu` VALUES (1, 109);
INSERT INTO `sys_role_menu` VALUES (1, 110);
INSERT INTO `sys_role_menu` VALUES (1, 111);
INSERT INTO `sys_role_menu` VALUES (1, 112);
INSERT INTO `sys_role_menu` VALUES (1, 113);
INSERT INTO `sys_role_menu` VALUES (1, 114);
INSERT INTO `sys_role_menu` VALUES (1, 115);
INSERT INTO `sys_role_menu` VALUES (1, 500);
INSERT INTO `sys_role_menu` VALUES (1, 501);
INSERT INTO `sys_role_menu` VALUES (1, 1000);
INSERT INTO `sys_role_menu` VALUES (1, 1001);
INSERT INTO `sys_role_menu` VALUES (1, 1002);
INSERT INTO `sys_role_menu` VALUES (1, 1003);
INSERT INTO `sys_role_menu` VALUES (1, 1004);
INSERT INTO `sys_role_menu` VALUES (1, 1005);
INSERT INTO `sys_role_menu` VALUES (1, 1006);
INSERT INTO `sys_role_menu` VALUES (1, 1007);
INSERT INTO `sys_role_menu` VALUES (1, 1008);
INSERT INTO `sys_role_menu` VALUES (1, 1009);
INSERT INTO `sys_role_menu` VALUES (1, 1010);
INSERT INTO `sys_role_menu` VALUES (1, 1011);
INSERT INTO `sys_role_menu` VALUES (1, 1012);
INSERT INTO `sys_role_menu` VALUES (1, 1013);
INSERT INTO `sys_role_menu` VALUES (1, 1014);
INSERT INTO `sys_role_menu` VALUES (1, 1015);
INSERT INTO `sys_role_menu` VALUES (1, 1016);
INSERT INTO `sys_role_menu` VALUES (1, 1017);
INSERT INTO `sys_role_menu` VALUES (1, 1018);
INSERT INTO `sys_role_menu` VALUES (1, 1019);
INSERT INTO `sys_role_menu` VALUES (1, 1020);
INSERT INTO `sys_role_menu` VALUES (1, 1021);
INSERT INTO `sys_role_menu` VALUES (1, 1022);
INSERT INTO `sys_role_menu` VALUES (1, 1023);
INSERT INTO `sys_role_menu` VALUES (1, 1024);
INSERT INTO `sys_role_menu` VALUES (1, 1025);
INSERT INTO `sys_role_menu` VALUES (1, 1026);
INSERT INTO `sys_role_menu` VALUES (1, 1027);
INSERT INTO `sys_role_menu` VALUES (1, 1028);
INSERT INTO `sys_role_menu` VALUES (1, 1029);
INSERT INTO `sys_role_menu` VALUES (1, 1030);
INSERT INTO `sys_role_menu` VALUES (1, 1031);
INSERT INTO `sys_role_menu` VALUES (1, 1032);
INSERT INTO `sys_role_menu` VALUES (1, 1033);
INSERT INTO `sys_role_menu` VALUES (1, 1034);
INSERT INTO `sys_role_menu` VALUES (1, 1035);
INSERT INTO `sys_role_menu` VALUES (1, 1036);
INSERT INTO `sys_role_menu` VALUES (1, 1037);
INSERT INTO `sys_role_menu` VALUES (1, 1038);
INSERT INTO `sys_role_menu` VALUES (1, 1039);
INSERT INTO `sys_role_menu` VALUES (1, 1040);
INSERT INTO `sys_role_menu` VALUES (1, 1041);
INSERT INTO `sys_role_menu` VALUES (1, 1042);
INSERT INTO `sys_role_menu` VALUES (1, 1043);
INSERT INTO `sys_role_menu` VALUES (1, 1044);
INSERT INTO `sys_role_menu` VALUES (1, 1045);
INSERT INTO `sys_role_menu` VALUES (1, 1046);
INSERT INTO `sys_role_menu` VALUES (1, 1047);
INSERT INTO `sys_role_menu` VALUES (1, 1048);
INSERT INTO `sys_role_menu` VALUES (1, 1049);
INSERT INTO `sys_role_menu` VALUES (1, 1050);
INSERT INTO `sys_role_menu` VALUES (1, 1051);
INSERT INTO `sys_role_menu` VALUES (1, 1052);
INSERT INTO `sys_role_menu` VALUES (1, 1053);
INSERT INTO `sys_role_menu` VALUES (1, 1054);
INSERT INTO `sys_role_menu` VALUES (1, 1055);
INSERT INTO `sys_role_menu` VALUES (1, 1056);
INSERT INTO `sys_role_menu` VALUES (1, 1057);
INSERT INTO `sys_role_menu` VALUES (1, 1058);
INSERT INTO `sys_role_menu` VALUES (1, 1059);
INSERT INTO `sys_role_menu` VALUES (1, 1060);
INSERT INTO `sys_role_menu` VALUES (1, 2000);
INSERT INTO `sys_role_menu` VALUES (1, 2001);
INSERT INTO `sys_role_menu` VALUES (1, 2002);
INSERT INTO `sys_role_menu` VALUES (1, 2004);
INSERT INTO `sys_role_menu` VALUES (1, 2005);
INSERT INTO `sys_role_menu` VALUES (1, 2006);
INSERT INTO `sys_role_menu` VALUES (1, 2007);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);
INSERT INTO `sys_role_menu` VALUES (100, 5);
INSERT INTO `sys_role_menu` VALUES (100, 10);
INSERT INTO `sys_role_menu` VALUES (100, 11);
INSERT INTO `sys_role_menu` VALUES (100, 13);
INSERT INTO `sys_role_menu` VALUES (101, 5);
INSERT INTO `sys_role_menu` VALUES (101, 10);
INSERT INTO `sys_role_menu` VALUES (101, 11);
INSERT INTO `sys_role_menu` VALUES (101, 13);
INSERT INTO `sys_role_menu` VALUES (102, 1);
INSERT INTO `sys_role_menu` VALUES (102, 100);
INSERT INTO `sys_role_menu` VALUES (102, 101);
INSERT INTO `sys_role_menu` VALUES (102, 102);
INSERT INTO `sys_role_menu` VALUES (102, 103);
INSERT INTO `sys_role_menu` VALUES (102, 104);
INSERT INTO `sys_role_menu` VALUES (102, 105);
INSERT INTO `sys_role_menu` VALUES (102, 106);
INSERT INTO `sys_role_menu` VALUES (102, 107);
INSERT INTO `sys_role_menu` VALUES (102, 108);
INSERT INTO `sys_role_menu` VALUES (102, 500);
INSERT INTO `sys_role_menu` VALUES (102, 501);
INSERT INTO `sys_role_menu` VALUES (102, 1000);
INSERT INTO `sys_role_menu` VALUES (102, 1001);
INSERT INTO `sys_role_menu` VALUES (102, 1002);
INSERT INTO `sys_role_menu` VALUES (102, 1003);
INSERT INTO `sys_role_menu` VALUES (102, 1004);
INSERT INTO `sys_role_menu` VALUES (102, 1005);
INSERT INTO `sys_role_menu` VALUES (102, 1006);
INSERT INTO `sys_role_menu` VALUES (102, 1007);
INSERT INTO `sys_role_menu` VALUES (102, 1008);
INSERT INTO `sys_role_menu` VALUES (102, 1009);
INSERT INTO `sys_role_menu` VALUES (102, 1010);
INSERT INTO `sys_role_menu` VALUES (102, 1011);
INSERT INTO `sys_role_menu` VALUES (102, 1012);
INSERT INTO `sys_role_menu` VALUES (102, 1013);
INSERT INTO `sys_role_menu` VALUES (102, 1014);
INSERT INTO `sys_role_menu` VALUES (102, 1015);
INSERT INTO `sys_role_menu` VALUES (102, 1016);
INSERT INTO `sys_role_menu` VALUES (102, 1017);
INSERT INTO `sys_role_menu` VALUES (102, 1018);
INSERT INTO `sys_role_menu` VALUES (102, 1019);
INSERT INTO `sys_role_menu` VALUES (102, 1020);
INSERT INTO `sys_role_menu` VALUES (102, 1021);
INSERT INTO `sys_role_menu` VALUES (102, 1022);
INSERT INTO `sys_role_menu` VALUES (102, 1023);
INSERT INTO `sys_role_menu` VALUES (102, 1024);
INSERT INTO `sys_role_menu` VALUES (102, 1025);
INSERT INTO `sys_role_menu` VALUES (102, 1026);
INSERT INTO `sys_role_menu` VALUES (102, 1027);
INSERT INTO `sys_role_menu` VALUES (102, 1028);
INSERT INTO `sys_role_menu` VALUES (102, 1029);
INSERT INTO `sys_role_menu` VALUES (102, 1030);
INSERT INTO `sys_role_menu` VALUES (102, 1031);
INSERT INTO `sys_role_menu` VALUES (102, 1032);
INSERT INTO `sys_role_menu` VALUES (102, 1033);
INSERT INTO `sys_role_menu` VALUES (102, 1034);
INSERT INTO `sys_role_menu` VALUES (102, 1035);
INSERT INTO `sys_role_menu` VALUES (102, 1036);
INSERT INTO `sys_role_menu` VALUES (102, 1037);
INSERT INTO `sys_role_menu` VALUES (102, 1038);
INSERT INTO `sys_role_menu` VALUES (102, 1039);
INSERT INTO `sys_role_menu` VALUES (102, 1040);
INSERT INTO `sys_role_menu` VALUES (102, 1041);
INSERT INTO `sys_role_menu` VALUES (102, 1042);
INSERT INTO `sys_role_menu` VALUES (102, 1043);
INSERT INTO `sys_role_menu` VALUES (102, 1044);
INSERT INTO `sys_role_menu` VALUES (102, 1045);
INSERT INTO `sys_role_menu` VALUES (102, 2000);
INSERT INTO `sys_role_menu` VALUES (104, 1);
INSERT INTO `sys_role_menu` VALUES (104, 100);
INSERT INTO `sys_role_menu` VALUES (104, 101);
INSERT INTO `sys_role_menu` VALUES (104, 102);
INSERT INTO `sys_role_menu` VALUES (104, 103);
INSERT INTO `sys_role_menu` VALUES (104, 104);
INSERT INTO `sys_role_menu` VALUES (104, 105);
INSERT INTO `sys_role_menu` VALUES (104, 106);
INSERT INTO `sys_role_menu` VALUES (104, 107);
INSERT INTO `sys_role_menu` VALUES (104, 108);
INSERT INTO `sys_role_menu` VALUES (104, 500);
INSERT INTO `sys_role_menu` VALUES (104, 501);
INSERT INTO `sys_role_menu` VALUES (104, 1000);
INSERT INTO `sys_role_menu` VALUES (104, 1001);
INSERT INTO `sys_role_menu` VALUES (104, 1002);
INSERT INTO `sys_role_menu` VALUES (104, 1003);
INSERT INTO `sys_role_menu` VALUES (104, 1004);
INSERT INTO `sys_role_menu` VALUES (104, 1005);
INSERT INTO `sys_role_menu` VALUES (104, 1006);
INSERT INTO `sys_role_menu` VALUES (104, 1007);
INSERT INTO `sys_role_menu` VALUES (104, 1008);
INSERT INTO `sys_role_menu` VALUES (104, 1009);
INSERT INTO `sys_role_menu` VALUES (104, 1010);
INSERT INTO `sys_role_menu` VALUES (104, 1011);
INSERT INTO `sys_role_menu` VALUES (104, 1012);
INSERT INTO `sys_role_menu` VALUES (104, 1013);
INSERT INTO `sys_role_menu` VALUES (104, 1014);
INSERT INTO `sys_role_menu` VALUES (104, 1015);
INSERT INTO `sys_role_menu` VALUES (104, 1016);
INSERT INTO `sys_role_menu` VALUES (104, 1017);
INSERT INTO `sys_role_menu` VALUES (104, 1018);
INSERT INTO `sys_role_menu` VALUES (104, 1019);
INSERT INTO `sys_role_menu` VALUES (104, 1020);
INSERT INTO `sys_role_menu` VALUES (104, 1021);
INSERT INTO `sys_role_menu` VALUES (104, 1022);
INSERT INTO `sys_role_menu` VALUES (104, 1023);
INSERT INTO `sys_role_menu` VALUES (104, 1024);
INSERT INTO `sys_role_menu` VALUES (104, 1025);
INSERT INTO `sys_role_menu` VALUES (104, 1026);
INSERT INTO `sys_role_menu` VALUES (104, 1027);
INSERT INTO `sys_role_menu` VALUES (104, 1028);
INSERT INTO `sys_role_menu` VALUES (104, 1029);
INSERT INTO `sys_role_menu` VALUES (104, 1030);
INSERT INTO `sys_role_menu` VALUES (104, 1031);
INSERT INTO `sys_role_menu` VALUES (104, 1032);
INSERT INTO `sys_role_menu` VALUES (104, 1033);
INSERT INTO `sys_role_menu` VALUES (104, 1034);
INSERT INTO `sys_role_menu` VALUES (104, 1035);
INSERT INTO `sys_role_menu` VALUES (104, 1036);
INSERT INTO `sys_role_menu` VALUES (104, 1037);
INSERT INTO `sys_role_menu` VALUES (104, 1038);
INSERT INTO `sys_role_menu` VALUES (104, 1039);
INSERT INTO `sys_role_menu` VALUES (104, 1040);
INSERT INTO `sys_role_menu` VALUES (104, 1041);
INSERT INTO `sys_role_menu` VALUES (104, 1042);
INSERT INTO `sys_role_menu` VALUES (104, 1043);
INSERT INTO `sys_role_menu` VALUES (104, 1044);
INSERT INTO `sys_role_menu` VALUES (104, 1045);
INSERT INTO `sys_role_menu` VALUES (104, 2000);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101013 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 103, 'admin', ' 百信', '00', 'ry@163.com', '15888888888', '1', '', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', '2019-09-02 09:33:24', 'admin', '2018-03-16 11:33:00', 'ry', '2019-09-02 09:33:22', '管理员');
INSERT INTO `sys_user` VALUES (100000, 1000, 'baoshengfei', '鲍胜飞', '00', 'baoshengfei@qa.aibank.com', '18611408362', '2', '', '22391bcd6b0103a2e76de26ac1eea114', '88eb34', '0', '0', '172.19.188.147', '2019-08-19 15:36:43', 'admin', '2019-08-19 14:11:37', '', '2019-08-19 15:36:43', NULL);
INSERT INTO `sys_user` VALUES (100001, 1001, 'chenchao', '陈超', '00', 'chenchao@qa.aibank.com', '18622909765', '2', '', '5afc28c75bd0cf4a294e627df39fa5a5', 'ab2bda', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:37', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100002, 1002, 'chengdeming', '程德明', '00', 'chengdeming@qa.aibank.com', '15210832922', '2', '', 'a0acbf9890c39a08eabee310eed184bf', '0994dd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100003, 1003, 'chenglei', '程磊', '00', 'chenglei@qa.aibank.com', '13581561593', '2', '', 'f8af5f235b3d00af37feb376984bf16b', '503cc2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100004, 1001, 'chenjingping', '陈晶平', '00', 'chenjingping@qa.aibank.com', '13810157602', '2', '', 'b3935f6b23a5039b07302aed41dd3793', '4a49ab', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100005, 1004, 'chenlongqiang', '陈龙强', '00', 'chenlongqiang@qa.aibank.com', '13911066427', '2', '', '2755771c1bb4dcf9d77ec53199e7f73e', '4b1851', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100006, 1000, 'chenyue', '陈月', '00', 'chenyue@qa.aibank.com', '18600532648', '2', '', '11ded0fb5a3181e16b1f8841871162f2', '74d321', '0', '0', '172.19.188.147', '2019-08-19 20:37:07', 'admin', '2019-08-19 14:11:38', '', '2019-08-19 20:37:07', NULL);
INSERT INTO `sys_user` VALUES (100007, 1000, 'chenyuxia', '陈玉霞', '00', 'chenyuxia@qa.aibank.com', '13923441423', '2', '', '98ac712f9b523e1600c3612be8dc4584', 'b23eda', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100008, 1000, 'cuiying', '崔莹', '00', 'cuiying@qa.aibank.com', '18630996920', '2', '', '11d3f7b9a740983fb4030dc965babb42', '6cefb0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100009, 1002, 'dengjianli', '邓建利', '00', 'dengjianli@qa.aibank.com', '13401052764', '2', '', 'd78630464c283bea8cb32ea3df69c4e7', '4c10d0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100010, 1000, 'dingboxun', '丁博寻', '00', 'dingboxun@qa.aibank.com', '18645173566', '2', '', '51c8558e700f40388658138baa442cad', '4105fd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100011, 1002, 'dinghao', '丁豪', '00', 'dinghao@qa.aibank.com', '13810658674', '2', '', '9a705022a98cdfc53e5b9c34cb7db11c', 'c7f6bf', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100012, 1001, 'dingjianing', '丁加宁', '00', 'dingjianing@qa.aibank.com', '13401113862', '2', '', '52b019f63b443fb28a07c9077ee2418f', '604b81', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100013, 1005, 'dingnan', '丁楠', '00', 'dingnan@qa.aibank.com', '15814631465', '2', '', '497cb09ee5cb0bea23c1eaa51d650abd', '57833e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100014, 1003, 'dingpeng', '丁芃', '00', 'dingpeng@qa.aibank.com', '18516957561', '2', '', 'a527c4439e8f0aae62830b9362970aa4', '00ab06', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100015, 1002, 'donghongpan', '董红盼', '00', 'donghongpan@qa.aibank.com', '18538758586', '2', '', 'def658c7559df1925610d8133760f40f', 'a0485d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100016, 1002, 'dongjingzhi', '董井智', '00', 'dongjingzhi@qa.aibank.com', '18410005955', '2', '', 'fc9fdb20b1f004748db99346d6e90104', 'cfbc56', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100017, 1003, 'dongzhe', '董喆', '00', 'dongzhe@qa.aibank.com', '18500187500', '2', '', 'e22d50ef4e460c5794619649309680d3', 'b12a49', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100018, 2000, 'dufanghua', '杜芳华', '00', 'dufanghua@qa.aibank.com', '18516993959', '2', '', '36348ca32b0190eb5f98903245035e4f', '7d0a41', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100019, 1002, 'dujiajie', '杜家杰', '00', 'dujiajie@qa.aibank.com', '18610934099', '2', '', '5f149df2ea4e67c9e81cafa8eddbb03d', 'f1f617', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100020, 1002, 'fanchenfei', '范晨霏', '00', 'fanchenfei@qa.aibank.com', '13911132783', '2', '', 'f71c9b88bd34ac083bbb8744a372f638', '96187a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100021, 1007, 'gaosong', '高嵩', '00', 'gaosong@qa.aibank.com', '18501274063', '2', '', '3684d2a28c30dcb35d436ab8d4260c11', '0693ea', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100022, 1000, 'gaotao', '高涛', '00', 'gaotao@qa.aibank.com', '15210099169', '2', '', 'c0796f08860f201cd4e43ccb4a385f42', 'aef2e5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100023, 1003, 'gaozhi', '高智', '00', 'gaozhi@qa.aibank.com', '18601045059', '2', '', 'ba0b488247edbdd2b00b5ddb7117e437', 'a9839f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100024, 1000, 'genghaixu', '耿海旭', '00', 'genghaixu@qa.aibank.com', '15011552927', '2', '', 'e6809fbc7cea4560babb5709d2e8e928', 'e090d4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100025, 1000, 'guanchunli', '管春丽', '00', 'guanchunli@qa.aibank.com', '13718278699', '2', '', '9cac0430ddb696c4bc2e226c7e2731b8', '7e7fc8', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100026, 1000, 'guanxuan', '管璇', '00', 'guanxuan@qa.aibank.com', '15120046260', '2', '', '8c846763a8672f9cb90d31c00760f762', 'd20fe6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100027, 1004, 'guanzhenggang', '管正刚', '00', 'guanzhenggang@qa.aibank.com', '18600260137', '2', '', '4467192f31a2b9b20f4fc809d8791ae0', 'e14b83', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100028, 1008, 'guomaolong', '郭茂龙', '00', 'guomaolong@qa.aibank.com', '13811861990', '2', '', '0ee9c4e18ec69135806f4686bc24af21', '01af74', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100029, 1009, 'guqingsong', '古青松', '00', 'guqingsong@qa.aibank.com', '15801625834', '2', '', '586d9d5f45d6d1bdfa34b49f6482806d', 'c008e7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100030, 1002, 'herui', '何蕊', '00', 'herui@qa.aibank.com', '18614066985', '2', '', '157604ed3ab8ccffb023a81d0c72995f', '658ca4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:38', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100031, 1010, 'hexiaotong', '何笑彤', '00', 'hexiaotong@qa.aibank.com', '13901284086', '2', '', 'e17be27484de0951a9374d529c3d7894', 'b53c5b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100032, 2001, 'heyuren', '何予人', '00', 'heyuren@qa.aibank.com', '18611210182', '2', '', '91ad86dd89bbdd98801372333544b7e5', '0a850e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100033, 1002, 'houruoyang', '侯若阳', '00', 'houruoyang@qa.aibank.com', '13621199074', '2', '', '2cc0a3c78360d46ef559756f50042bc2', 'da522c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100034, 1011, 'huajing', '华敬', '00', 'huajing@qa.aibank.com', '13811045663', '2', '', 'c03508ef9229220e2d94f7354bf88fc3', '6647ef', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100035, 1005, 'huchengjun', '胡成君', '00', 'huchengjun@qa.aibank.com', '13910037017', '2', '', 'b7d9bf84c16e1a74e14767078a7ca2a9', '750cfa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100036, 1012, 'huowei', '霍炜', '00', 'huowei@qa.aibank.com', '13811866040', '2', '', '90fb55180f474a1b231e30c13159afe0', '983973', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100037, 1000, 'jialaibin', '贾来宾', '00', 'jialaibin@qa.aibank.com', '13691121317', '2', '', '4149902ccfd31c892bb5a9723ee33efa', 'cfd009', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100038, 1002, 'jiangnan', '江楠', '00', 'jiangnan@qa.aibank.com', '18513417285', '2', '', 'd512d606929018abacbce00098abdb7d', '9df1b8', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100039, 2000, 'jiangwei', '姜巍', '00', 'jiangwei@qa.aibank.com', '13581999603', '2', '', '4adcd5897fa61bafa05c29d3ca069e59', '4bf5b5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100040, 1011, 'jiaopeng', '焦鹏', '00', 'jiaopeng@qa.aibank.com', '13811898323', '2', '', '332284a19373b5fe2e48413c1767e3a6', '439efa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100041, 1003, 'jiayunfei', '贾云飞', '00', 'jiayunfei@qa.aibank.com', '18511878576', '2', '', 'b50648a372782ac3b24a97f02043e2cd', '349208', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100042, 1013, 'jinfulin', '靳富林', '00', 'jinfulin@qa.aibank.com', '17610181326', '2', '', '1dd1085c3be0eabc20234d3175d367be', '33d58b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100043, 2002, 'jingxinzhong', '荆新忠', '00', 'jingxinzhong@qa.aibank.com', '15001383225', '2', '', 'f4a08cd1fbb97aebb486f65ee5b85c21', '416070', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100044, 2000, 'jizenghui', '及增惠', '00', 'jizenghui@qa.aibank.com', '18031116161', '2', '', 'a6ff96780ae27ea0b20dabf7715e6d74', '0ce8ca', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100045, 1007, 'kangxi', '康希', '00', 'kangxi@qa.aibank.com', '13581518074', '2', '', '7b6b813cf731246cdf39c4a23f448461', 'f728c5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100046, 1003, 'kangyawei', '康亚玮', '00', 'kangyawei@qa.aibank.com', '18611184583', '2', '', '3f4bd87e018cc08f748661df34af285c', '316435', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100047, 1014, 'kouguan', '寇冠', '00', 'kouguan@qa.aibank.com', '18611317333', '2', '', '5c55c1f79c4544b3d5d8d94825b2a9dc', '840e3e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100048, 1002, 'laimingwang', '赖名望', '00', 'laimingwang@qa.aibank.com', '17710126063', '2', '', '16d6cb222eedbc427a4d7e816a1933c4', '5c6ea3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100049, 1010, 'lansheng', '兰升', '00', 'lansheng@qa.aibank.com', '15110234430', '2', '', '833364aca3f7fe7e91537c0cb3e9ac35', 'a3cab7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100050, 1002, 'leiyulin', '雷雨霖', '00', 'leiyulin@qa.aibank.com', '18810496811', '2', '', 'c97905b62fc85721dfff23b637db1a22', 'a8fef6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100051, 2001, 'liangjunfeng', '梁俊锋', '00', 'liangjunfeng@qa.aibank.com', '18666212217', '2', '', '21ce8ccf8657d92e7b3c953971dd2e0b', '070667', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100052, 2000, 'liangrui', '梁睿', '00', 'liangrui@qa.aibank.com', '18618308691', '2', '', 'e4630376e48bb27af65d799063a5cc2a', '9f882b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100053, 2001, 'lichao', '李超', '00', 'lichao@qa.aibank.com', '18811218626', '2', '', 'e54f30d11cc3ae0b7a8469aff66d254b', '40b19e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100054, 1007, 'lichen', '李忱', '00', 'lichen@qa.aibank.com', '13811198965', '2', '', 'e9416eca396b5915fe2d31bbeb8db943', 'ed4066', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100055, 1000, 'lichen1', '李晨1', '00', 'lichen1@qa.aibank.com', '18145170861', '2', '', 'e1c3a34c4326fbef212beadb0bd3b2ca', '4938d9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100056, 2000, 'lidong', '李冬', '00', 'lidong@qa.aibank.com', '13651103223', '2', '', 'ff7352f602e55371e32318a8833c45e8', '822d0e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100057, 1000, 'lihongzhao', '李红朝', '00', 'lihongzhao@qa.aibank.com', '13901338197', '2', '', 'a4666135b39ca1b0ce1f5df4d790c978', 'ca48b4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100058, 2002, 'lijia', '李佳', '00', 'lijia@qa.aibank.com', '13683638686', '2', '', 'c3b1123e99ba76d6ddbaf8109bed83e0', 'babd32', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100059, 1001, 'lijuan', '李娟', '00', 'lijuan@qa.aibank.com', '18910963885', '2', '', '90dbaeab4212465955bf54286a46435f', '27cde0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100060, 1002, 'likunna', '李坤娜', '00', 'likunna@qa.aibank.com', '18600088238', '2', '', '8f3f732a08e7537ed0228a3559ee0743', '63061c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:39', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100061, 2001, 'lilin', '李琳', '00', 'lilin@qa.aibank.com', '18601180521', '2', '', 'f38ae198bce9039ff8261f2111ed11d2', 'd63b45', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100062, 1009, 'limeixia', '李美霞', '00', 'limeixia@qa.aibank.com', '15110410295', '2', '', 'd83483c9f977f4dfaf460b1d0bacfe59', 'd31568', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100063, 1009, 'linhuayong', '林华勇', '00', 'linhuayong@qa.aibank.com', '13581782005', '2', '', 'd6ff69779c7d2be166067d8f46a4b291', '17f103', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100064, 2000, 'linlu', '林路', '00', 'linlu@qa.aibank.com', '18611896915', '2', '', '7fe1744de4591228271397b10446a6d8', '8f119f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100065, 1002, 'lipeng', '李鹏', '00', 'lipeng@qa.aibank.com', '13683361293', '2', '', 'feddadbf931d6130bbc259eb75f42134', 'daf6fc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100066, 1015, 'liqiang', '李嫱', '00', 'liqiang@qa.aibank.com', '15120049662', '2', '', '39985dae23edc77445c4d08450ad2cba', '9dd9a7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100067, 1014, 'lirudong', '李如东', '00', 'lirudong@qa.aibank.com', '18601163936', '2', '', '00aed84c14a3c06d90527d2842e372d5', 'b334e5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100068, 1001, 'lishouquan', '李守泉', '00', 'lishouquan@qa.aibank.com', '18510512996', '2', '', '2f63a8ab2285866dffd6335c61ee373b', '9ff807', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100069, 2000, 'liuboyang', '刘博洋', '00', 'liuboyang@qa.aibank.com', '18600805178', '2', '', '00fbe65872a6b0b34d20979a6505de40', '15691e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100070, 1000, 'liudandan', '刘丹丹', '00', 'liudandan@qa.aibank.com', '13681271254', '2', '', 'b924fc49834ea6a4744077934157cb82', 'd4a1e5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100071, 1002, 'liugang', '刘岗', '00', 'liugang@qa.aibank.com', '18311001670', '2', '', '902b6dbc0cc6128e6a9bf018c168beb9', 'd2ce44', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100072, 1001, 'liugeyu', '李葛禹', '00', 'liugeyu@qa.aibank.com', '18811436784', '2', '', '8b716c1ef6e24c158fc9d4ae2ac1d1b9', '058078', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100073, 2001, 'liujindong', '刘晋东', '00', 'liujindong@qa.aibank.com', '13840369483', '2', '', 'b573c992dd6be40ed90769b1fec2d56d', '7205ab', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100074, 1000, 'liujun', '刘俊', '00', 'liujun@qa.aibank.com', '13488717029', '2', '', 'cbec8913fbe3261691e59cf4f0f9023c', '2b3c78', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100075, 1003, 'liuke', '刘柯', '00', 'liuke@qa.aibank.com', '13560256367', '2', '', '1ea1b886043543b965cdef30f15dbeb1', '441cd5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100076, 1000, 'liuliang', '刘亮', '00', 'liuliang@qa.aibank.com', '13811122917', '2', '', 'e3ba030979d335964af886552394f96d', 'd82324', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100077, 1000, 'liurui', '刘蕊', '00', 'liurui@qa.aibank.com', '18701456701', '2', '', '14daeaf0bee9ec2a77ac7fb2e08d5762', '2a0b20', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100078, 1001, 'liutingting', '刘婷婷', '00', 'liutingting@qa.aibank.com', '15901083720', '2', '', 'd7218a05434e384cda11d8c2b7c1b0c8', '4ec6f0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100079, 2001, 'liuweiwei', '刘巍巍', '00', 'liuweiwei@qa.aibank.com', '13811679787', '2', '', '0318b95f9bd83ad2a2653a9ddd2b4686', '33e2f4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100080, 1000, 'liuyang', '刘洋', '00', 'liuyang@qa.aibank.com', '18610103521', '2', '', 'd14e32392a7087fd1e4e9f2e61122aab', 'ee2338', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100081, 1001, 'liuyawei', '刘亚威', '00', 'liuyawei@qa.aibank.com', '18610277152', '2', '', 'db82fea31b36534df1db244661239778', 'db1e0e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100082, 2002, 'liuying', '刘颖', '00', 'liuying@qa.aibank.com', '13811388679', '2', '', '4eb57af90394a85846a0feeec1d6eada', '116a35', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100083, 1009, 'liuzhiguo', '刘志国', '00', 'liuzhiguo@qa.aibank.com', '13811201756', '2', '', '950f9074cd640b37875c256d11e60dbc', '6341d4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100084, 1003, 'liwenhui', '李文惠', '00', 'liwenhui@qa.aibank.com', '13811648716', '2', '', '602c9828422c7bebb45ad9f1a07f1b9c', 'c94d59', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100085, 1010, 'lixiaolei', '李晓磊', '00', 'lixiaolei@qa.aibank.com', '18601276624', '2', '', '0de869dae20ba0a05158452ab074ffdc', '837536', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100086, 1002, 'lixiaomin', '李晓敏', '00', 'lixiaomin@qa.aibank.com', '15116990635', '2', '', '699d7159ce6a102e42efd3b7eb1bb92c', '022182', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100087, 1003, 'lixuezhi', '李学志', '00', 'lixuezhi@qa.aibank.com', '13810096396', '2', '', 'a07b0c87e1b4724fab537b7f2cdf9522', 'fa39e8', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100088, 1002, 'liyongyu', '李永煜', '00', 'liyongyu@qa.aibank.com', '18666025401', '2', '', 'e8194d462a6d44f7699f3b26f36f5775', '57bd37', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100089, 2000, 'liyugong', '李雨恭', '00', 'liyugong@qa.aibank.com', '13810286446', '2', '', '7982e25a5ee7239c316a1d92ffcf5412', 'ef6b3d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100090, 1014, 'luogang', '罗刚', '00', 'luogang@qa.aibank.com', '15110066372', '2', '', 'e6eeae1b1979435e3fcebe1b3e13eb8d', 'c7ce4e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100091, 1014, 'mahaiqing', '马海清', '00', 'mahaiqing@qa.aibank.com', '13911318756', '2', '', '8bcaf76c992256a3ec2ed351a3f65c7c', 'fa7e15', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100092, 1013, 'majun', '马骏', '00', 'majun@qa.aibank.com', '18904000858', '2', '', '10a6a0b2339e7d3a14f202b1f90d9222', '2ae68f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:40', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100093, 2000, 'malin', '马琳', '00', 'malin@qa.aibank.com', '13701238299', '2', '', 'ff4e9c1bd0693ede1b2f42b9b39baaaa', 'f956f4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100094, 2000, 'manan', '马楠', '00', 'manan@qa.aibank.com', '13801197280', '2', '', '35be098067e072116cb590f28831eabc', '9295a3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100095, 2001, 'maoxiong', '毛雄', '00', 'maoxiong@qa.aibank.com', '18911992601', '2', '', '35a100ca3db32599ecdfdecdce0c7ba7', 'e3ad2a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100096, 2000, 'mayiteng', '马一腾', '00', 'mayiteng@qa.aibank.com', '15210837302', '2', '', 'c46977a38faca0a5dbb98750ef8a2809', '819448', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100097, 1005, 'mengdejun', '孟德君', '00', 'mengdejun@qa.aibank.com', '13910513533', '2', '', '17384e757de4584d55c664af43295d65', 'e14254', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100098, 2000, 'mengfanlei', '孟凡磊', '00', 'mengfanlei@qa.aibank.com', '18911315196', '2', '', 'b15a9152c784f6d6b55850d05c5ea79e', 'b7f71b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100099, 1005, 'menglingwei', '孟令伟', '00', 'menglingwei@qa.aibank.com', '18236585536', '2', '', '0d8eed346be75e38369399f06805382d', 'ea20d5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100100, 1003, 'mengqingkui', '孟庆魁', '00', 'mengqingkui@qa.aibank.com', '13810706400', '2', '', '9e5aabe810770553ebe41ddb16e46822', '8e992b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100101, 2000, 'mengzhaojun', '孟兆君', '00', 'mengzhaojun@qa.aibank.com', '13401103382', '2', '', '2bf9d86489da93910456daa805a23cdf', 'a9341f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100102, 2000, 'mujianmin', '沐建民', '00', 'mujianmin@qa.aibank.com', '13716993699', '2', '', 'd789fedcc1c28ea036141f8a49763899', 'aa2a56', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100103, 1009, 'nihao', '倪皓', '00', 'nihao@qa.aibank.com', '13811164037', '2', '', 'b600a61de4e675cadc7da42ec5e2a82c', 'cea29e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100104, 2001, 'panhongshuang', '潘红双', '00', 'panhongshuang@qa.aibank.com', '13718223633', '2', '', 'ad3e76c8cc1fb03e2b6acbcecd9b1486', '953f77', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100105, 1003, 'qianjing', '钱晶', '00', 'qianjing@qa.aibank.com', '15901356473', '2', '', 'c9aa5adc54d4630c2174a83da2a220c9', 'f83bd3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100106, 1010, 'qianxiaoyan', '钱晓燕', '00', 'qianxiaoyan@qa.aibank.com', '13220150810', '2', '', 'e747bbcf1430137991bb8f796d90e31a', '6cf997', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100107, 1003, 'qinting', '秦霆', '00', 'qinting@qa.aibank.com', '18910829541', '2', '', '62f0a95e6ee7da82d683b3548b2bed37', 'f8ef8e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100108, 1002, 'quzijie', '瞿子杰', '00', 'quzijie@qa.aibank.com', '13552830936', '2', '', 'd618c69ca1c1a54b47bae715dafc6a94', '18a576', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100109, 1011, 'sangmingyuan', '桑明媛', '00', 'sangmingyuan@qa.aibank.com', '18612419823', '2', '', 'b93828a216c0130ea2af8fff0002b20b', 'ff33ec', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100110, 2002, 'shangxinquan', '尚新全', '00', 'shangxinquan@qa.aibank.com', '13910000202', '2', '', 'a637b2e57f6f14ff01b726b89b9f1799', 'cfe12b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100111, 1003, 'shaojunying', '邵军营', '00', 'shaojunying@qa.aibank.com', '18618148168', '2', '', '721b47c09aa6fe021d6def7505582dc7', '3abc9b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100112, 1002, 'shenjiahao', '沈嘉皓', '00', 'shenjiahao@qa.aibank.com', '18612485616', '2', '', 'e4f7c6d9862e43884774cb6a6f10e2a8', 'a9077e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100113, 2002, 'shidan', '石丹', '00', 'shidan@qa.aibank.com', '18811041904', '2', '', 'a3ebdc89eed9c7e3abddfcd332d2c1be', '68aa8e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100114, 1008, 'shiyuanshao', '史原韶', '00', 'shiyuanshao@qa.aibank.com', '15942323806', '2', '', '1c06ff6160fbafdd672cf647d5bffa3c', '262941', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100115, 2001, 'songyupeng', '宋宇鹏', '00', 'songyupeng@qa.aibank.com', '18310027719', '2', '', 'a4b7a17fef2cef6c01a922ce2fabaa96', '522d42', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100116, 1009, 'sunjiexiong', '孙杰雄', '00', 'sunjiexiong@qa.aibank.com', '18600805178', '2', '', '76264dc326f2e90b15f287e988c19c8e', '360571', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100117, 1000, 'suxiang', '苏翔', '00', 'suxiang@qa.aibank.com', '18612563831', '2', '', '90d7120d0ae77340f3e364034eb7cea1', '9895be', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100118, 1002, 'tangbangfu', '唐邦富', '00', 'tangbangfu@qa.aibank.com', '18601295396', '2', '', '9070c0da3f2f8be08abaa3b31217cfef', '8ee075', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100119, 1007, 'tangshunyao', '汤顺尧', '00', 'tangshunyao@qa.aibank.com', '18301198399', '2', '', '52d76793f5ea3fc523eed2ead9d15763', '45c298', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100120, 1000, 'tangxiaonan', '唐晓楠', '00', 'tangxiaonan@qa.aibank.com', '18612967966', '2', '', '6d485009684e947940ba89eece46139b', '5d89de', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100121, 1000, 'taoxiaoping', '陶小平', '00', 'taoxiaoping@qa.aibank.com', '18098941217', '2', '', '1250e2ab9133bbf2e7d5108b47844f29', 'a0fbb3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100122, 1002, 'tianlin', '田琳', '00', 'tianlin@qa.aibank.com', '18219201816', '2', '', '43d4fb62ec17c32659ddfeffc5da8015', '7e4742', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100123, 1002, 'wangbin', '王斌', '00', 'wangbin@qa.aibank.com', '18910229378', '2', '', '5da8dc68dcdedd5a7ae547da6ab3a107', '02d410', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:41', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100124, 1011, 'wangdapeng', '王大鹏', '00', 'wangdapeng@qa.aibank.com', '13522014706', '2', '', '003c818bd44e7381e6fdf4feac45c1d7', '297b7b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100125, 1011, 'wangdengli', '王邓立', '00', 'wangdengli@qa.aibank.com', '17310298771', '2', '', '78631f022ac0f1f0a145ed5a225fbf9b', 'fba985', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100126, 2002, 'wangfei', '王飞', '00', 'wangfei@qa.aibank.com', '15910618313', '2', '', '030570214b96aed469ea3b5f96c4d1bb', '3b80a9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100127, 1000, 'wangfeng', '王锋', '00', 'wangfeng@qa.aibank.com', '18612672712', '2', '', 'd699293b2ed12e01a474b64eec050891', '2c7ed5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100128, 1003, 'wanghongchao', '王宏超', '00', 'wanghongchao@qa.aibank.com', '18911907071', '2', '', 'e88dd2dfe9be3994ab168ffad93e1fa8', '991b98', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100129, 1012, 'wanghu', '王虎', '00', 'wanghu@qa.aibank.com', '18601295396', '2', '', '75f9382beca303b6361ea023f6cfd912', 'd603cd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100130, 1003, 'wangjialei', '王甲雷', '00', 'wangjialei@qa.aibank.com', '13693031223', '2', '', '1d35dac88d8121940577404864b95b01', '43cb70', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100131, 1002, 'wangjie', '王杰', '00', 'wangjie@qa.aibank.com', '18810572634', '2', '', '4992be436968ea3b0d43d69e49f4c18b', '624813', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100132, 1014, 'wangjunhui', '王军晖', '00', 'wangjunhui@qa.aibank.com', '18511344833', '2', '', 'ace5ee2a57b34a61110090252499a329', '95385b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100133, 1003, 'wangkailun', '王凯伦', '00', 'wangkailun@qa.aibank.com', '13911234838', '2', '', 'de130cc7dacd53194287fb75302cd412', '4b8a93', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100134, 1002, 'wanglei', '王磊', '00', 'wanglei@qa.aibank.com', '15011314467', '2', '', '5e72b8fac6257ff75634c73bbd8fd021', '138726', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100135, 1005, 'wangmingshuang', '王明爽', '00', 'wangmingshuang@qa.aibank.com', '13681540614', '2', '', 'eb674c4ec73bd43d3af4efa48cceeb6c', 'd38408', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100136, 2000, 'wangpengcheng', '王鹏程', '00', 'wangpengcheng@qa.aibank.com', '13810352532', '2', '', '35cada28caaa3d1105a8ac4e0c86ce02', '9de1e6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100137, 1000, 'wangshuo', '王硕', '00', 'wangshuo@qa.aibank.com', '18600576557', '2', '', 'd826efeeee157d486a620d95ac76eace', '1b0e32', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100138, 1010, 'wangwen', '王文', '00', 'wangwen@qa.aibank.com', '18612978880', '2', '', '4774b30d22cc40fb0762b804b576d43d', '11f11d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100139, 1003, 'wangxiao', '王霄', '00', 'wangxiao@qa.aibank.com', '18141925267', '2', '', '113eac54fc6bb01e8168568192f20061', '1e5102', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100140, 1014, 'wangxiaowei', '王晓炜', '00', 'wangxiaowei@qa.aibank.com', '13501180275', '2', '', '1b5297801746dde84bf08f4b969b3ee0', '39d9c4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100141, 1009, 'wangxudong', '王旭东', '00', 'wangxudong@qa.aibank.com', '18510341507', '2', '', '6c23ab6d07a9843308e695ac29f0d472', 'ff09dc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100142, 1008, 'wangyan', '王妍', '00', 'wangyan@qa.aibank.com', '18810918436', '2', '', 'aacfd94cebe1190270e00338541fe0ce', '575dfe', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100143, 2000, 'wangyang', '汪洋', '00', 'wangyang@qa.aibank.com', '18610838751', '2', '', 'eb472cccf412daf76a0de154a5bc6ccf', 'ff11da', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100144, 1001, 'wangyetao', '王叶涛', '00', 'wangyetao@qa.aibank.com', '18662586880', '2', '', '180061bfa82e4d9c7fdf9ae24446c941', 'af220e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100145, 1011, 'wangying', '王颖', '00', 'wangying@qa.aibank.com', '13581920747', '2', '', '30d2f5614213c0f972b3dc29331a6953', '93ba11', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100146, 2001, 'wangyongyuan', '王永远', '00', 'wangyongyuan@qa.aibank.com', '18201328539', '2', '', '4ad42d811cc862f2790f48ebe07d8400', '123862', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100147, 1007, 'wangzhen', '王震', '00', 'wangzhen@qa.aibank.com', '13261622093', '2', '', '6c2cc6f69874362b4fce8b2c79c8b9c8', 'dae4e1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100148, 2001, 'wangzhengang', '王振刚', '00', 'wangzhengang@qa.aibank.com', '18810818968', '2', '', '4444942e211e5c879f8af8ecd6f6f718', '1fee9e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100149, 1002, 'wangzhenyan', '王振燕', '00', 'wangzhenyan@qa.aibank.com', '18311058055', '2', '', '904fb790ffe4c042b9ada67c63c3ac55', '59e24b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100150, 1002, 'wangzhirui', '王知睿', '00', 'wangzhirui@qa.aibank.com', '13260153531', '2', '', 'bdf1ee3970a0754059538df9a4cee025', '94c4bc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100151, 1011, 'wangzhiyu', '王致宇', '00', 'wangzhiyu@qa.aibank.com', '13581845300', '2', '', '7ba2f556b10ab8a148ab0bbfc364c4ed', 'bdfdd3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100152, 1000, 'wanyi', '万亿', '00', 'wanyi@qa.aibank.com', '13683208272', '2', '', 'abec8985b588f328f6ba98d1abdc0bf4', 'a318c4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100153, 1013, 'weiwenbo', '魏文博', '00', 'weiwenbo@qa.aibank.com', '13671074255', '2', '', '408e2846dd88a9a866d82f5568083d29', 'f53274', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:42', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100154, 2000, 'weizhaoxue', '魏朝雪', '00', 'weizhaoxue@qa.aibank.com', '13811275278', '2', '', '76aab19345243014432b7615682db026', '9977dd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100155, 2001, 'wuboxuan', '武博轩', '00', 'wuboxuan@qa.aibank.com', '18518401991', '2', '', 'd2008442935149b7184dbf00c30afd7f', 'd16dae', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100156, 1002, 'wushuang', '吴爽', '00', 'wushuang@qa.aibank.com', '13810027483', '2', '', '28c637ca38ba46efe2fc00df1530c0d4', 'fd0087', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100157, 1011, 'wutong', '吴桐', '00', 'wutong@qa.aibank.com', '13901237078', '2', '', '7758b2aeb6f5611189696d7e3015c1af', '1b04dd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100158, 1009, 'wuxiaojun', '吴晓军', '00', 'wuxiaojun@qa.aibank.com', '15618334961', '2', '', '6ced120667a142d25e6bc676f1f15e06', 'ba12c0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100159, 1005, 'wuyin', '吴垠', '00', 'wuyin@qa.aibank.com', '13163190960', '2', '', '93e10799e2cc34d16aebee5aa29f5168', 'f8ac05', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100160, 1003, 'wuyuchen', '吴禹辰', '00', 'wuyuchen@qa.aibank.com', '18710080377', '2', '', 'f293451d7cc8c99442f36684d431991e', 'dfaa72', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100161, 1000, 'wuyuhao', '吴玉昊', '00', 'wuyuhao@qa.aibank.com', '18611128197', '2', '', 'c04c3b4c86ef43c15f19a4062ba41e18', '6945bd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100162, 1002, 'xiaowei', '肖威', '00', 'xiaowei@qa.aibank.com', '13816971928', '2', '', 'c5445386e259f92f1c60c9326dd19d21', '22e8dc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100163, 1003, 'xiehouyi', '谢厚溢', '00', 'xiehouyi@qa.aibank.com', '18612559182', '2', '', '58867de0df0ae538d5b6234165ed3b4f', '2e6701', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100164, 1000, 'xingrongxuan', '邢荣轩', '00', 'xingrongxuan@qa.aibank.com', '18201631025', '2', '', '3fb53ae3977089686e9aa41424fc9841', '1e60c1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100165, 2001, 'xinziying', '辛子英', '00', 'xinziying@qa.aibank.com', '13661299129', '2', '', '435a7c391d7bf887a51622ba72ddfd81', '692599', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100166, 1000, 'xiongweina', '熊维娜', '00', 'xiongweina@qa.aibank.com', '18201008394', '2', '', '2ecb97f3dd1e2d0a4ab4a488430adac8', '0c0616', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100167, 2001, 'xiongzhenyun', '熊振云', '00', 'xiongzhenyun@qa.aibank.com', '18910059459', '2', '', 'c430a4560de579f8e4a228e7e928eb8e', 'f40442', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100168, 1000, 'xuhao', '徐豪', '00', 'xuhao@qa.aibank.com', '13370154678', '2', '', '30fe14f176ed35bc5ce0a0b215e350ef', 'b85ea6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100169, 1002, 'xulei', '徐磊', '00', 'xulei@qa.aibank.com', '13603033895', '2', '', '194e7e951aa51e0a73dd48678ee85462', '56003f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100170, 1011, 'xushuisheng', '徐水生', '00', 'xushuisheng@qa.aibank.com', '13621101968', '2', '', '500561cae30bea3a18a0ee32b39aa6b8', '5ac8d7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100171, 1000, 'xuxiangguang', '徐祥广', '00', 'xuxiangguang@qa.aibank.com', '18147122981', '2', '', '84c119f965e3169927be578ef46fa65e', 'dfe484', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100172, 1008, 'xuxiaoqing', '许小青', '00', 'xuxiaoqing@qa.aibank.com', '18611938618', '2', '', 'c5248b5a8c5e1bf4437585b3592572f6', 'c5b979', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100173, 1005, 'yanbo', '闫博', '00', 'yanbo@qa.aibank.com', '13810756659', '2', '', '17f1f8a6dfb8140ee47d33b4da21bd48', 'e80baf', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100174, 1003, 'yanchangjun', '闫长君', '00', 'yanchangjun@qa.aibank.com', '18612081609', '2', '', 'e911e4c2dde456577d3bda750dd8445c', 'f2dd2e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100175, 2001, 'yanchen', '闫晨', '00', 'yanchen@qa.aibank.com', '15201329426', '2', '', '99b0f5af3e08b79cc6cff606f95627d8', '5db027', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100176, 2001, 'yangjinbin', '杨金彬', '00', 'yangjinbin@qa.aibank.com', '13261701832', '2', '', '55b48632a2cd7123b49df9ba013003ed', 'af9b41', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100177, 1002, 'yangsiqi', '杨思奇', '00', 'yangsiqi@qa.aibank.com', '18801470056', '2', '', '58b229e846d3fe6bde72146963d321de', 'b4cb46', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100178, 2003, 'yangweidong', '杨卫东', '00', 'yangweidong@qa.aibank.com', '13381024729', '2', '', '523d679f821d17dfbcfb342fef693e02', 'c95b94', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100179, 1013, 'yangxiaofeng', '杨晓风', '00', 'yangxiaofeng@qa.aibank.com', '13552192877', '2', '', 'f977fa2bbc9200807d82f5ffa2ced6fc', 'ad4a60', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100180, 1013, 'yangxiaoxing', '杨晓星', '00', 'yangxiaoxing@qa.aibank.com', '18500992933', '2', '', '8cbd6c2a69b428025a02815691354c90', '1e0d77', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100181, 2001, 'yangyi', '杨弋', '00', 'yangyi@qa.aibank.com', '13811616548', '2', '', '6c324eaf2a8ff763be4812a4336649c8', '958bdc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100182, 1000, 'yangzhiwei', '杨治伟', '00', 'yangzhiwei@qa.aibank.com', '15810252078', '2', '', '710e959a2a740532d464bb8bd5aafc74', '082844', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100183, 2000, 'yangzhizhen', '杨志桢', '00', 'yangzhizhen@qa.aibank.com', '18610649071', '2', '', '6ceb9a4345ca1372e66a0fc616cf0d5b', 'd341d0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:43', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100184, 1000, 'yanran', '闫冉', '00', 'yanran@qa.aibank.com', '13581723720', '2', '', '489bfde3afb667ec55fd9220fa2d3d43', '6e45ef', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100185, 1000, 'yanxuxin', '燕旭鑫', '00', 'yanxuxin@qa.aibank.com', '17704027334', '2', '', '5ded6f54fe42cd5de93ba724224d754b', '66ebce', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100186, 1003, 'yejunjiao', '叶君骄', '00', 'yejunjiao@qa.aibank.com', '18611371282', '2', '', 'a3a596a3895c7fe066951fbf7dcc5e7e', '113527', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100187, 1003, 'yize', '伊泽', '00', 'yize@qa.aibank.com', '13683568650', '2', '', 'f567232ca4126ccd12794124a44f02b4', '707608', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100188, 1002, 'yuanqihua', '原奇华', '00', 'yuanqihua@qa.aibank.com', '18519738176', '2', '', 'af3b92f148c0908b764014dcc19a1bd9', '5b7be1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100189, 1000, 'yuanweiwu', '袁威武', '00', 'yuanweiwu@qa.aibank.com', '18910252075', '2', '', '694622f499c31f8563dd074773698047', 'a0528e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100190, 1002, 'yuanxiaodong', '袁晓冬', '00', 'yuanxiaodong@qa.aibank.com', '13693031223', '2', '', 'fa4870249c6ea3c384cc821da7345333', '5814c6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100191, 2002, 'yuhaohan', '于浩瀚', '00', 'yuhaohan@qa.aibank.com', '13822158157', '2', '', 'ed5c05a3f22fe8819bc47cd474d4b131', 'b98f38', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100192, 2002, 'yuning', '于宁', '00', 'yuning@qa.aibank.com', '13810113732', '2', '', '9982c22974aa3ada828d2bafc4b9ca63', '3cf204', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100193, 1003, 'yuxinming', '于新铭', '00', 'yuxinming@qa.aibank.com', '13262278008', '2', '', 'ab99b766e5072c90c20072eaf4e4a4e0', '428aff', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100194, 1017, 'yuzheng', '于正', '00', 'yuzheng@qa.aibank.com', '15810313948', '2', '', 'b9eecc3ff1aac4af904c7d54e672ca06', 'a69ab7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100195, 1005, 'zengwenzhong', '曾文忠', '00', 'zengwenzhong@qa.aibank.com', '13751740105', '2', '', '5b0157da9e35911bdc8793a28ce33280', '975e37', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100196, 1002, 'zhangbo', '张博', '00', 'zhangbo@qa.aibank.com', '13910416224', '2', '', 'dc91cbd766b93a8bc43103f27a7a05ec', '4ef81b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100197, 1007, 'zhangcongwei', '张丛薇', '00', 'zhangcongwei@qa.aibank.com', '15810229625', '2', '', 'fabc4f5c6aa21faab3c261bc9b2537c8', '692f49', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100198, 1013, 'zhangfan', '张帆', '00', 'zhangfan@qa.aibank.com', '18910037971', '2', '', 'ae4f39c03a826c7b1e6e69b989a0974a', '2691be', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100199, 2004, 'zhanghexin', '张贺信', '00', 'zhanghexin@qa.aibank.com', '13241806701', '2', '', 'b52143d144f935be68c02b809f66107f', '2f86d2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100200, 1005, 'zhanghui', '张会', '00', 'zhanghui@qa.aibank.com', '13811129479', '2', '', 'd6d3100b94671bf9a12b398d8db50434', 'bc4ceb', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100201, 1009, 'zhanghui1', '张辉', '00', 'zhanghui1@qa.aibank.com', '15811041018', '2', '', 'c9427a67bda1d20e39978645c44582f8', '64df0c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100202, 1007, 'zhangjiuqiang', '张久强', '00', 'zhangjiuqiang@qa.aibank.com', '13662025506', '2', '', '471f88a700d7bfd17522bc4c8c50bd2e', '32d246', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100203, 1003, 'zhangkexuan', '张珂瑄', '00', 'zhangkexuan@qa.aibank.com', '18600769504', '2', '', 'dc109d699164bd22e3a4fc3fdceaae25', 'd80bee', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100204, 1009, 'zhanglili', '张丽丽', '00', 'zhanglili@qa.aibank.com', '13691527437', '2', '', 'af288fc786451006c5c02d081fd02be9', 'fdf917', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100205, 1000, 'zhangmeiling', '张美玲', '00', 'zhangmeiling@qa.aibank.com', '18514594953', '2', '', '6b51c227846982d92b3c5bacb13401e0', 'e32a5b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100206, 1018, 'zhangran', '张冉', '00', 'zhangran@qa.aibank.com', '13811600196', '2', '', '2ab664140ff642980a4eb8af90234a32', '1d92aa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100207, 1000, 'zhangtianfu', '张天夫', '00', 'zhangtianfu@qa.aibank.com', '15811065605', '2', '', '6f4049028e24db2928a75abda992d699', '8ba8ef', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100208, 1001, 'zhangtingting', '张婷婷', '00', 'zhangtingting@qa.aibank.com', '13910973362', '2', '', 'a75289e2a579e8d254b9d89d7514ff2d', '9c4e4e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100209, 2001, 'zhangwei', '张伟', '00', 'zhangwei@qa.aibank.com', '18653881741', '2', '', 'a08520f65b00f5fc2a8a017503eb7c06', '18528b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:44', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100210, 2004, 'zhangxuxin', '张旭鑫', '00', 'zhangxuxin@qa.aibank.com', '18511691778', '2', '', '63db2645e1fe114523d42f235620ed44', '56fb48', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100211, 1002, 'zhangyunzhao', '张云昭', '00', 'zhangyunzhao@qa.aibank.com', '13811065877', '2', '', '3b66223186712345706870d8b547c5a9', '25ce42', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100212, 1003, 'zhangzhenyu', '张振雨', '00', 'zhangzhenyu@qa.aibank.com', '18601276624', '2', '', '4e6fbd783de573240d0575cd2a9c4621', 'f90a16', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100213, 1010, 'zhaobin', '赵彬', '00', 'zhaobin@qa.aibank.com', '13269462810', '2', '', '6c469dbd059fe3293c7bfdd1ec784de5', '5dd3de', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100214, 1002, 'zhaoqing', '赵卿', '00', 'zhaoqing@qa.aibank.com', '15201305947', '2', '', '5d6ee71c9b02e4e8155b5f56974b3aea', '62743e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100215, 1011, 'zhaoxingchen', '赵星辰', '00', 'zhaoxingchen@qa.aibank.com', '15210371066', '2', '', '1be1f33e7921876738c22fa10f1bf80b', '6b3cec', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100216, 1003, 'zhaozhenya', '赵振亚', '00', 'zhaozhenya@qa.aibank.com', '15210744742', '2', '', 'f6ef5f49443a18c62f7019d8578e557a', '8d083f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100217, 1000, 'zhongfaming', '钟发明', '00', 'zhongfaming@qa.aibank.com', '15814423860', '2', '', '1b5044b8dd6dce10348e06f3746bdfc0', '983aed', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100218, 2001, 'zhoubeichun', '周北春', '00', 'zhoubeichun@qa.aibank.com', '13810549928', '2', '', '94bb688cfb1664bb01332a71364c349b', '961a05', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100219, 1002, 'zhouhao', '周昊', '00', 'zhouhao@qa.aibank.com', '18500178044', '2', '', '24c0db270fb979d069973c575304db5c', '023423', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100220, 2000, 'zhoujie', '周杰', '00', 'zhoujie@qa.aibank.com', '18500948348', '2', '', 'a5a74da774442e3a5a755675e9e32093', '93bd89', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100221, 2001, 'zhoujuntao', '周竣涛', '00', 'zhoujuntao@qa.aibank.com', '13718741522', '2', '', '0a6e452082c162fb2f2555ab730501c0', 'f97394', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100222, 1003, 'zhoumingming', '周明明', '00', 'zhoumingming@qa.aibank.com', '18617375369', '2', '', '2777bc5ce55af2c2c5cd0b598d0d9092', '2fa7c7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100223, 2000, 'zhouqichao', '周启超', '00', 'zhouqichao@qa.aibank.com', '18611393384', '2', '', '3ec6d35a9990a68e9752bc09202b691b', '5bfd9c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100224, 2001, 'zhouyanming', '周延明', '00', 'zhouyanming@qa.aibank.com', '15901208706', '2', '', 'e57cd5da561167148c23a1663a2917cb', 'd80549', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100225, 1003, 'zhouyixuan', '周怡轩', '00', 'zhouyixuan@qa.aibank.com', '13488761789', '2', '', 'cc74628db8b7b33bf1cc99d1be644686', 'e586fa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100226, 1003, 'zhuhongliang', '朱宏亮', '00', 'zhuhongliang@qa.aibank.com', '13466318594', '2', '', '4d1417ddc430eb4413921df70f8ef9aa', '700526', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100227, 2000, 'zhukai', '朱凯', '00', 'zhukai@qa.aibank.com', '18601392181', '2', '', '343315a13ff8ec5cb17fbac9a9c3968f', '6a34e5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100228, 1000, 'zhuqingyi', '朱清沂', '00', 'zhuqingyi@qa.aibank.com', '13810320410', '2', '', 'a09eaf022a89fe99e5184a7992f8cde7', '7e2f0a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100229, 1003, 'zhuyanan', '朱亚男', '00', 'zhuyanan@qa.aibank.com', '18612216348', '2', '', 'e5739ccc159c2ea08fe0b32d43bf5d3c', '3b9a6b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100230, 1002, 'zhuyimin', '朱祎敏', '00', 'zhuyimin@qa.aibank.com', '18611330704', '2', '', '3b28789f260190eca4015ede1162f5c9', 'f2f139', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100231, 1000, 'zhuzhenying', '祝振莹', '00', 'zhuzhenying@qa.aibank.com', '13581683282', '2', '', '15d7e702ed62cbb8292f1f6816793abb', '2a0bdf', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100232, 1000, 'changzhijun', '常志军', '00', 'changzhijun@qa.aibank.com', '18682009254', '2', '', '3cc92f5abda950b031952655fa1ddc20', '4c9ba5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100233, 2003, 'liangwenchao', '梁文超', '00', 'liangwenchao@qa.aibank.com', '13810628980', '2', '', 'b24599294498e76ff80f43322e585775', 'b32730', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100234, 2002, 'wuyufei', '吴雨霏', '00', 'wuyufei@qa.aibank.com', '18611754140', '2', '', '4725e3ae6a55360709691cd0af5436d2', 'e7daef', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100235, 1000, 'yangbowen', '杨博文', '00', 'yangbowen@qa.aibank.com', '18911035202', '2', '', '0065cff3e794607a8cf76408e3fded2e', '9e06b9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100236, 1005, 'huruijuan', '胡瑞娟', '00', 'huruijuan@qa.aibank.com', '', '2', '', 'cb2ae7cd8025767dc7a81061481ddbd9', '8b6b9b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100237, 2002, 'ningyaozong', '宁耀宗', '00', 'ningyaozong@qa.aibank.com', '', '2', '', 'cc3fbb4129ddb3579b76056694f35ed4', 'b1c8e1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100238, 1009, 'panzhitian', '潘志田', '00', 'panzhitian@qa.aibank.com', '', '2', '', 'dcd1018c944def845cffb48ffb86b27d', 'f054b8', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100239, 2000, 'zhongwumi', '钟武汨', '00', 'zhongwumi@qa.aibank.com', '', '2', '', 'e0aa01933f99bfda0badc857053238e3', 'b0552b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100240, 1015, 'luoqiang', '洛强', '00', 'luoqiang@qa.aibank.com', '', '2', '', '21d8e8a6b4febf92289fd5c7de4f3225', '30613d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100241, 2000, 'test16', 'test16', '00', 'test16@qa.aibank.com', '', '2', '', 'dae0b346f907677d7b089ef66f699d1a', '34c685', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100242, 2000, 'test17', 'test17', '00', 'test17@qa.aibank.com', '', '2', '', 'e1b2fcddc9e2e97bfe8e0dfeacf64f36', '6eed44', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100243, 1009, 'zhongwenyue', '衷文跃', '00', 'zhongwenyue@qa.aibank.com', '18601202425', '2', '', '7ae8707f817051f526bd058a1a2f57d4', '7a7d90', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100244, 1003, 'mapengfei', '马鹏飞', '00', 'mapengfei@qa.aibank.com', '13952084890', '2', '', '8433390f291c8b2336239645185cb090', '63516c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:45', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100245, 1000, 'zhanzhenguo', '詹振国', '00', 'zhanzhenguo@qa.aibank.com', '18610034395', '2', '', '5899d5c3b9c6107a9e65612a5a635165', '8cd154', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100246, 1011, 'jiangxiufei', '姜秀飞', '00', 'jiangxiufei@qa.aibank.com', '', '2', '', '147a42fa6c8b7f2b8f954194fc8747f7', '663a43', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100247, 1000, 'panlinjie', '潘琳杰', '00', '', '18611987187', '2', '', '45b92592d31d0be7b60de03ef5560dba', '1d76c9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100248, 1000, 'zenghao', '曾皓', '00', '', '13911722462', '2', '', '8dabe1b9a7be513783c36cff473ebc63', '400629', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100249, 1000, 'zhangtingming', '张庭鸣', '00', '', '13552342107', '2', '', '34eb3dd0d1e9e0aea8ef0dc81dfde879', '4a9fe7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100250, 1002, 'jiajian', '贾健', '00', '', '13699113630', '2', '', '22db392cffa6c1ec6bf53e2cf414cb08', '6509ab', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100251, 1002, 'lixiaohang', '李晓航', '00', '', '15901121586', '2', '', '317bed46bd00dc747573ab175721c249', '8de378', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100252, 1003, 'maopenghao', '毛彭浩', '00', '', '', '2', '', 'c0afe62211e13d8e1c64e142f04eec74', 'cb9a89', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100253, 1017, 'zhangpuhua', '张普华', '00', '', '', '2', '', '883dfb5f0f02fc8e7ecbfe1893bc32d9', '007433', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100254, 2001, 'dupeifeng', '杜培锋', '00', '', '18654504959', '2', '', 'c9363d40093d7531ac0ccae2f5098dce', 'e8b05c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100255, 1003, 'zhanghaige', '张海格', '00', '', '13401043721', '2', '', 'ca2178415338944294facd7492a92cb3', '9a36b6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100256, 1000, 'wangyoupeng', '王友鹏', '00', '', '', '2', '', 'd6e2dfa6da9665c8bc52d87ba3b9eeff', '0df0dc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100258, 1019, 'lihanpeng_intern', '李汉鹏', '00', '', '18811389450', '2', '', '2b58c77eb095a1dc9e68db28c66f6286', '5c5908', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100259, 1019, 'zhoumoqin_intern', '周墨钦', '00', '', '18610821805', '2', '', '32e7d79c411ad3d223293c949ff74e9a', '99a510', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100260, 2001, 'xubowen', '徐博文', '00', '', '18514661116', '2', '', '4875db317434b20897a3184f939690ec', '91c9b2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100261, 1002, 'chenchao1', '陈超', '00', '', '13651139005', '2', '', '701cebf9b16867309b83902ac00a2b28', '789c5b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100262, 1019, 'liangshuang_intern', '梁爽', '00', '', '13051576386', '2', '', 'ad626f08372ba78e48265e3f930bed74', '75b907', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100263, 1003, 'dingyang', '丁阳', '00', '', '18510084014', '2', '', 'ef518fc410f7798e93dcab7478ab0a5c', '2445e1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100264, 1007, 'songxinyu', '宋欣宇', '00', '', '18519659255', '2', '', '810c35b7a152369804004293145b44e3', 'da8ad5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100265, 1007, 'caohan', '曹晗', '00', '', '18701182877', '2', '', 'a213dd0b64c7e0ac85a86826132d3301', 'f0171a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100266, 1017, 'qiuximing', '邱希明', '00', '', '', '2', '', 'a228d0a36c8fb6fb8e64d9c44940b257', '3dbc69', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100267, 1020, 'lihuaicheng', '李怀城', '00', '', '', '2', '', '151e8dbff170d5e29681ddb0cdd48073', '77f641', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100268, 1010, 'jiaxibei', '贾西贝', '00', '', '', '2', '', '0b4a7fc26507e306f3f401ed821446d0', 'bac45b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100269, 1000, 'zhangxiaowei', '张小伟', '00', '', '13811625284', '2', '', '2cae4cd56842a3abfc63ba2ec725ed0c', '5c40c5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100270, 1019, 'yinye_intern', '尹晔', '00', '', '18810278561', '2', '', 'a0375c4fa96f3a65c56955b171d20a8a', 'fe6f8c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:46', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100271, 1019, 'jiangjincheng_intren', '蒋金成', '00', '', '18011602261', '2', '', '09056aa80409f9070b57beee083d8000', '31b496', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100272, 1019, 'huangziyin_intern', '黄子崟', '00', '', '13051108906', '2', '', '91921e10c8749be3c9dbbfaa78ac3105', '2018c3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100273, 1000, 'fandongping', '范东平', '00', '', '18502344522', '2', '', '32f1e06440f32022d86c571fdc3518b3', '29a87c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100274, 1019, 'guoxialin_intern', '郭夏霖', '00', '', '18566266997', '2', '', '1c66f3fcff59f88d49a75a28250cc9c7', '86b141', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100275, 1000, 'lvfugang', '吕福刚', '00', '', '18611177675', '2', '', '71d135432cbcc8733bc4283f9c7ed896', '2b56d0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100276, 1005, 'zhangyuanlong', '张元龙', '00', '', '18790260038', '2', '', 'de74f413f150f5d1964ca93d1b32c0b4', 'd98777', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100277, 1000, 'hanyanping', '韩艳萍', '00', '', '13466533471', '2', '', 'b2efdc8b2f6b2f188d73db61687d9153', 'ab0080', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100278, 1003, 'xulu', '徐璐', '00', '', '18600240626', '2', '', 'a93b84f3a0abf3c2c84e24d76361eeca', 'ecc6ec', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100279, 1002, 'xuhualiang', '许华亮', '00', '', '13439785182', '2', '', '91493dbf9f9dfdafe460d024945f19ba', 'ffe4bf', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100280, 1000, 'sunchuan', '孙川', '00', '', '15210839417', '2', '', 'e5e0988e433c2984beeb14ff5195af18', '1a9a14', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100282, 1002, 'yuanxiaodong_mac', '袁晓东,支付创新发展部MAC', '00', '', '', '2', '', '4693ce4adf024914bdea7910017dde24', '35f8a1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100283, 1019, 'douyinan_intern', '窦祎楠', '00', '', '', '2', '', 'ff8a9ee20d7ed3ccaa7bfd25bba4d803', '638500', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100284, 1002, 'wangshujuan', '王淑娟', '00', '', '15652593731', '2', '', 'f48d9de55be88092ba84070042cbb1ae', '65d22e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100285, 2001, 'liqiang1', '李强', '00', '', '15901093597', '2', '', '77fc571da31f14de1388957b9c03b2a5', 'fae99f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100286, 1002, 'hanxu', '韩旭', '00', '', '18612509659', '2', '', 'a3f6096d61b5b51245e9e5663b033c95', '439aa9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100287, 1000, 'liyang', '李洋', '00', '', '18911606886', '2', '', 'fd097444dff59ece74fa52709e5058ea', '24208f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100288, 1000, 'shaohefeng', '邵河峰', '00', '', '18511599030', '2', '', '5e5baed3e9f3054ceaa39c55173dc34f', '464fac', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100289, 1000, 'shimingzhi', '史明志', '00', '', '13466601766', '2', '', 'a72c63adabe7eef70fa06a5f02f646e3', '08db8d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100290, 2000, 'fanxueru', '范学儒', '00', '', '18511406535', '2', '', '2e91233cfb34b61acc3b9aa16fd59632', 'b5030d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100291, 1002, 'zhuyueguang', '朱跃光', '00', '', '13522640718', '2', '', '705024ff3297e50815d015bcfbaf4a48', '8e0c5b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100292, 1005, 'zhuosonglei', '卓松磊', '00', '', '13811364058', '2', '', '259457385fe5586a9555f8674dad7bb4', '33eb64', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100293, 1000, 'liuqing', '刘庆', '00', '', '18321183913', '2', '', '803a29b3b5c5652930fb1b4aff1090d9', 'cdda35', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100294, 1005, 'songchao', '宋超', '00', '', '15810829159', '2', '', '911c4d5d9f0e8cf56e5dc8426f73abf0', '1fc109', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100295, 1000, 'tanggengyang', '唐庚阳', '00', '', '18210720280', '2', '', '29d83441ecef89956cd1550448f9a294', '7fd64e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100296, 1002, 'wangjianhui', '王建辉', '00', '', '13621130586', '2', '', '5e0a272a514a5c7d8747068f09d8245c', '92fbcd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:47', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100297, 1002, 'yangming', '杨明', '00', '', '15056600565', '2', '', '701659545d752d79cebc9004bca665e9', '2f6097', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100298, 1000, 'zhanhoulong', '詹厚龙', '00', '', '18813066773', '2', '', '7a5d320352001571696eef284bc11334', 'bed67c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100299, 2000, 'wangchao', '王超', '00', '', '13126778620', '2', '', 'abbb3c9aad01c3a7c801df567a135e8e', '873f23', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100300, 1000, 'liweimin', '李伟民', '00', '', '18631353700', '2', '', '598b2c47fa1b1c2b056f481c87c49b86', '5a7415', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100301, 1000, 'yanghao', '杨昊', '00', '', '15330271266', '2', '', '4f7e71476c1b989c9d18c6e410ded52b', '30ce55', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100302, 2001, 'liufengyi', '刘峰伊', '00', '', '18011429250', '2', '', '8e97ecf981a999231b2131879c2eea14', 'fc4ab9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100303, 1004, 'wangzhichao', '王智超', '00', '', '13312000360', '2', '', 'c79e55108bb00eb543421fc8496d4024', '7e6670', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100304, 1010, 'qiyane', '齐妍娥', '00', '', '13911823847', '2', '', 'e42e12983b787b82ec4325d619c7a5d4', '181434', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100305, 1005, 'lusonglei', '卢松磊', '00', '', '13811364058', '2', '', '46a0e0ddb9288802262bcf9ea174b55f', 'adb4aa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100306, 1000, 'lipingkuan', '李平宽', '00', '', '15011258339', '2', '', '99f1add70a301446a13376da28000c61', 'c8567b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100307, 1011, 'yangsu', '杨溯', '00', '', '18611807789', '2', '', '37dc296ce72b948bc33e2e2d5555914b', '47aaf7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100309, 1000, 'dingjuwei', '丁巨伟', '00', '', '15318766836', '2', '', '233c0d7fc7249154ca0003b7d2e108ed', 'a3daf3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100310, 1019, 'yuwenjie_intern', '余文杰', '00', '', '', '2', '', 'ecbfd9c8e042bad0cfcbdd9864e8ecc2', '047ea8', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100311, 1000, 'guoqi', '郭琦', '00', '', '18701186061', '2', '', 'bce967849c77c424dbcdab68dfb82a69', '79f6f2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100312, 1009, 'wuhaitao', '吴海涛', '00', '', '13401147596', '2', '', 'a0649b26ac99b8b5d385afd821999ac1', '6b9fee', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100313, 2000, 'linxin', '林鑫', '00', '', '', '2', '', '4eba8486ac9c2c16ee6e0bdb01f9eebd', '21f024', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100314, 1000, 'yangzhe', '杨哲', '00', '', '15810423694', '2', '', '9e4956399e4fb38720471cf30d580aa0', '3b27f4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100315, 1008, 'shenhaijiao', '申海娇', '00', '', '18322011314', '2', '', 'f1123779e591bbc5a824460b32aa6590', '412abf', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100316, 1000, 'lihaibo', '李海波', '00', '', '', '2', '', 'b49929e16fac3b7314f0cd760eafff50', 'ce624e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100317, 1002, 'shaoliang', '邵亮', '00', '', '18601022668', '2', '', '0f3243b2beca563b651fe828d4020a48', 'b68095', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100318, 1019, 'zhanglingda_intern', '张令达', '00', '', '', '2', '', 'b298d130aa935f79de5e2b42cdf81459', '0c339c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100319, 1004, 'yangkun', '杨坤', '00', '', '15901004213', '2', '', '126d02adbf8674c3f54b8fab80042823', '4942d9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100320, 1000, 'xujie', '徐杰', '00', '', '17611240909', '2', '', '225443a810b3a9147841372e3ecc8444', '3a44b8', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100321, 1000, 'huangchengxiang', '黄成祥', '00', '', '15300180759', '2', '', '43de52fb1d43618a9b5f3bfc22bcfcc7', '2db3f2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100322, 1019, 'wuhaoyu_intern', '吴昊宇', '00', '', '18810873155', '2', '', 'deea83f19ec4e6c27dadb91aad088f5a', 'e9f469', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100323, 1003, 'liudenghui', '刘登辉', '00', '', '15366671009', '2', '', 'f3010e96cbf132a382ebd1d2eb83da6c', 'cb4eff', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100324, 2002, 'gaobo', '高博', '00', '', '13910413829', '2', '', '720c5d9dcdc118d58908005db54943ca', 'c901ae', '0', '0', '127.0.0.1', '2019-08-28 17:23:03', 'admin', '2019-08-19 14:11:48', '', '2019-08-28 17:23:03', NULL);
INSERT INTO `sys_user` VALUES (100325, 2001, 'zhengweiliang', '郑维亮', '00', '', '15910892510', '2', '', 'bdab46a6e92082bd480ffecbf01a4c3a', 'd051b2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100326, 1005, 'zhangweishi', '张惟师', '00', '', '15811336720', '2', '', '7a37ee53d3146a3ae7e1b338feffd4d3', '4dccdd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100327, 1000, 'jinxin', '金鑫', '00', '', '18600027428', '2', '', '147c06c27d37c2dfda80712b85af9349', '13d37f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100328, 1000, 'yixiaofeng', '扆骁锋', '00', '', '13552690284', '2', '', 'dcadd69cd3631a8250a4e29e020e7ad2', 'cb24fa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100329, 2001, 'dongmeng', '董萌', '00', '', '13381058787', '2', '', 'b1321dc3d321ec2faa21f52031ac2788', 'e5a874', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100330, 2001, 'houqingqing', '侯青青', '00', '', '18600182885', '2', '', 'efe1c44335df5bb6db45097effe47188', 'b6790e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100331, 1000, 'huangchi', '黄驰', '00', '', '13661399943', '2', '', 'bfb39f18adf69a14ba6e1991af34dab7', '56acab', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100332, 2001, 'lijiajia', '李佳佳', '00', '', '15101091797', '2', '', 'ab5796518e4752b68bed73212e26ec50', '012dbe', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100333, 1019, 'shixiaofeng_intern', '史晓锋', '00', '', '13810432823', '2', '', '5e45f8b57355a8ed33e2203e26fc586c', '4ce728', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100334, 2001, 'houqingchun', '侯青春', '00', '', '18600182885', '2', '', 'e2ca41e68497f9d3c7a9ef9b123c794e', 'ae8fb2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100335, 1019, 'liyuge_intern', '李羽戈', '00', '', '15652915239', '2', '', '3ffd6c95e1e8c842634f141d256bea20', '988285', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100336, 1019, 'liuhuijie_intern', '刘慧杰', '00', '', '13121799055', '2', '', 'bd2d322391b189e6be22f673e29e5cac', '08e9ba', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100337, 2001, 'jipengfei', '冀鹏飞', '00', '', '18222896292', '2', '', '0f8a0ef984fba97bd69f4dd4817843e7', '354794', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100338, 1000, 'tangweijian', '唐伟建', '00', '', '18611907661', '2', '', '7d7e4a17b7e0b66416987538574ec344', '045e1e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100339, 1002, 'wangchen', '王晨', '00', '', '18514587300', '2', '', 'f200db83d77a0483d8741d7c509d1dd4', '94be39', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100340, 1010, 'yinzhihui', '殷智慧', '00', '', '15201562619', '2', '', '8cc4b2a11f918392c545a486a063fb0e', '3fe79f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100341, 1009, 'jinzhenqing', '金臻庆', '00', '', '15901086335', '2', '', 'ea9ab2df1a2734cf9af3a317abd0589e', 'ba3896', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100342, 1000, 'gaoxinpeng', '高昕鹏', '00', '', '1341130742', '2', '', '3e7e7728b56537305d5f4896ba92569c', '019d27', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100343, 1000, 'shutian', '舒甜', '00', '', '13810081246', '2', '', '366d0c331e28f262bfe2f255155ca162', 'ccc97a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100344, 1000, 'dinghongmin', '丁宏敏', '00', '', '18301294375', '2', '', '018b5160b6751d442ace2e39f7615d24', 'af14ef', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100345, 1009, 'zhangmanshi', '张曼诗', '00', '', '18810429776', '2', '', 'be17848ea7cd1b955ed6a7624f3505c8', '989d3e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100346, 1000, 'haoyang', '郝扬', '00', '', '13502107260', '2', '', '18ce7475f2d189a268d9d455e8ca3d3e', '91751a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100347, 1009, 'zhaijiaohan', '翟皎翰', '00', '', '18601113737', '2', '', 'e68e4126ed24b825af790716ba0f3766', '642e84', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100348, 1000, 'taoxingyu', '陶兴禹', '00', '', '13581605359', '2', '', '3b6bce8020a664ec6ba99afb4ecc63bd', '34e5ac', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100349, 2001, 'liudongsheng', '刘东升', '00', '', '15510513371', '2', '', 'bc2c6912ddbbca99ac3b8a654e5a75a3', 'd5dd66', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100350, 1003, 'lixiaoyan', '李笑颜', '00', '', '18031113961', '2', '', '7a3fe0517b47cdeb27b0f5fd7b5e4779', '3c54b0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100351, 1009, 'zhangshuai', '张帅', '00', '', '18810276818', '2', '', 'e2af30b278ca4941f9f403b7cfa388cd', 'f72d6e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100352, 1000, 'zusheng', '马祖胜', '00', '', '18510163161', '2', '', '471b985a6d7c90ae55ace75e324ac18a', '2bf955', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100353, 1003, 'liukun', '刘鲲', '00', '', '18600970085', '2', '', '268d4622f886b20c8d4c58d78a2e518d', 'cee1d7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100354, 1002, 'wangyang1', '王阳', '00', '', '17600553066', '2', '', 'a8914a84328b9abbad77d0e0acf6df2f', 'd4e039', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100355, 1000, 'xiaoyong', '肖勇', '00', '', '18612438601', '2', '', 'a50295e82d080895557012d0d834023e', '0f66a5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100356, 2001, 'linchunxiao', '林春晓', '00', '', '13241083377', '2', '', '83e7711b726875512d91790c142f9497', '846a75', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100357, 1002, 'zhangjizhan', '张继展', '00', '', '15801592454', '2', '', 'd10baa278266c2a7ac331c95ec7b0921', 'b36ad5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100358, 1002, 'lihaixiang', '李海翔', '00', '', '18510341063', '2', '', '79f84f25f144c15f904c059ac8c3f929', '99acc7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100359, 1000, 'mazusheng', '马祖胜', '00', '', '18510163161', '2', '', '6864f04e388e73869b1ce3cb511f1dad', '037aa7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100360, 1005, 'POC', 'POC项目', '00', '', '', '2', '', 'ac70f89c7471787f8ea21558a85026b8', '914933', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100361, 1005, 'shiyusen', '石玉森', '00', '', '18710273386', '2', '', '6da2a02b8616c3971b9996194871b28e', '1fa048', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:49', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100362, 1000, 'xfjrkf', '消费金融开发账号', '00', '', '', '2', '', '2470c673f4c306d16a8229a7ddc0ad09', 'd55fc2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100363, 1000, 'xfjrkf1', '消费金融开发1', '00', '', '', '2', '', 'b1e5f24ade7af1e23c7e7b6a5af52c6d', 'a2d55b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100364, 2001, 'jiangnianheng', '姜年珩', '00', '', '13811124775', '2', '', 'ecdbb90d71fbbef8c47590d2c35f661c', '7e53ba', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100365, 1000, 'huangshengjun', '黄圣钧', '00', '', '', '2', '', '74144b1eecd5a45a49ab85dca24b6683', '6b2c24', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100366, 1003, 'nietaojun', '聂桃军', '00', '', '18612454085', '2', '', '472145388018c844b049af4a29845745', '79fc87', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100367, 1003, 'shichaoqian', '史超前', '00', '', '13439775497', '2', '', '06cf957d0ac5264e8327fc10118e84b7', '5d263c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100368, 1003, 'wanghongwei', '王宏伟', '00', '', '18511266396', '2', '', '15e5b3b61fd99e983a863cd9ce0742a2', '50be07', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100369, 1003, 'renlongjie', '任龙杰', '00', '', '15101087582', '2', '', 'e22de99ebecc6f8f537bfca90e834529', '78bbfd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100370, 1000, 'zhouhuan', '周欢', '00', '', '19920143119', '2', '', 'db6ab62c16e0fd40f98e89103571039f', '7017bc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100371, 2001, 'zhangtianlin', '张天霖', '00', 'zhangtianlin@qa.aibank.com', '13522661184', '2', '', 'aec11de318800882cd5711dfc2b27cd0', '62b3a3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100372, 1000, 'hanxiaoguang', '韩晓光', '00', '', '15600555038', '2', '', '16a42a52408332d5669f1bf83631dcb9', 'fa2d32', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100373, 1000, 'xfjrkf3', '消费金融开发3', '00', '', '', '2', '', '3c292682596a29d30d2eacf068054761', '7359f3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100374, 1000, 'xfjrkf4', '消费金融开发4', '00', '', '', '2', '', '1da73aba096426ea0744154d19e7097a', '8135df', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100375, 2001, 'zhonghao', '钟浩', '00', '', '17600206682', '2', '', '210b29dc709526a4b71d183e502a091e', 'fbaffc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100376, 1003, 'hedeyu', '何得雨', '00', '', '13146748701', '2', '', '799728de53c260a27cc20d888c47654b', '624f16', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100377, 1000, 'zhuhongwei', '朱宏伟', '00', '', '15210390316', '2', '', 'b1e6889d45a8f9546a72ca824df18251', '76b448', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100378, 1000, 'jinsonghao', '金松浩', '00', '', '13001135406', '2', '', '82f9645644d4e1068a6951661bf72649', 'd5db19', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100379, 2001, 'libo', '李博', '00', '', '18210306940', '2', '', '49276006ea2500c532d383f6b4ef8d45', 'daf0b1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100380, 1000, 'liuhuihao', '刘慧昊', '00', '', '15801510882', '2', '', '23021df7cc37d6222e5127479edcc7e9', 'e6aa6d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100381, 1000, 'jianglianjun', '姜连军', '00', '', '13811086033', '2', '', 'd1b043d78505cb800135c1322e893a2b', 'fe66dd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100382, 1000, 'fengxin', '冯鑫', '00', '', '13520026174', '2', '', 'cc1dbbd929946dfafc99ba61c6aedeb7', '77d472', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100383, 1000, 'liuweizhi', '刘伟智', '00', '', '13811003353', '2', '', '5b1fd1277752a7fa923b78ca0c17c982', 'd2c341', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100384, 1005, 'shixiaofeng', '史晓锋', '00', '', '13810432823', '2', '', '1e7f4b27bc9ff0221fc55a6a83dad7d9', 'a6ceab', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100385, 1003, 'dongxiaoxu', '董晓旭', '00', '', '18610881249', '2', '', 'c09f3d7703e15e58d0c090d71c8fcfcb', '476799', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100386, 1002, 'hansiwen', '韩思文', '00', '', '13581722327', '2', '', '9cf40eeed4eda334a10488b14a9fc2a0', 'e0852d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100387, 1003, 'liulianmeng', '刘联盟', '00', '', '18500087337', '2', '', '12b03c8246b3f512fe773d89587c518e', '5fcd78', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100388, 1000, 'qiuhaoyu', '邱浩雨', '00', '', '19910388608', '2', '', 'ed77e16d459f8b9401a3d11726299469', '14239d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100389, 2001, 'cuixin', '崔欣', '00', '', '15120006709', '2', '', '860d8eb1e791e1fcbdc5b33ec79bce09', '538f9b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100390, 1005, 'POCSD', 'POCSD', '00', '', '18622906618', '2', '', 'eb7f142c2189ac267d2eb3c68e73a6e0', '5ca8d6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:50', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100391, 2000, 'libo1', '李波', '00', '', '18612033977', '2', '', '169cac0b211716be19157784ea42d643', '979290', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100392, 1009, 'lizewen', '李泽文', '00', '', '18614089603', '2', '', 'bceeb680062429f3683daf2052f6e7e4', 'e14833', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100393, 1002, 'caozehui', '曹泽辉', '00', '', '13835906871', '2', '', '1d788931184e7fdd4b2c89475455f63e', '5be4d0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100394, 2001, 'jinyi', '金翼', '00', '', '18101324760', '2', '', 'b04802138ad8d6be05536b0f904f5cf6', '7e6f07', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100395, 2001, 'songchunyan', '宋春艳', '00', '', '18236889682', '2', '', 'cadefada5951cc3d04b71831e10f9566', 'cf3608', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100396, 1002, 'wangyang3', '王洋', '00', '', '13389241571', '2', '', 'e881f6302149e6daa8b2b818c7867dcd', '668bcf', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100397, 1002, 'liudongjie', '刘冬杰', '00', '', '15652956594', '2', '', 'ec91755b023a68ad8d399abad57bc884', '2fd743', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100398, 1002, 'zhangpengkun', '张鹏坤', '00', '', '15810848311', '2', '', 'fd5c118f0ef71956c8d512f49c4d7365', '7256b4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100399, 1003, 'kouhaijiao', '寇海蛟', '00', '', '13811858689', '2', '', 'fb20ed3ce9bfe5fe5b55e6f112673a92', '533461', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100400, 1003, 'mofei', '莫菲', '00', '', '15711212091', '2', '', '507858759dcd02831652dca91853f126', '24c5c7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100401, 1018, 'liujian', '刘建', '00', '', '15101032673', '2', '', '5bfc081dafd8fe949a2502f8ff44d667', 'a07511', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100402, 1007, 'yuhaibao', '于海宝', '00', '', '13439993939', '2', '', '6fe93e41debb965fc2c636c6491c11f1', 'f80dd6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100403, 2000, 'gaoshen', '高深', '00', '', '13811316710', '2', '', '0d2e1d54bd3b2a3dafcb8b3ef7501bba', '5e4382', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100404, 1002, 'baixingbin', '白兴斌', '00', '', '13260389216', '2', '', '7fe948417072a6dac540ed4fc5c3de37', '5cef1b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100405, 2001, 'liuzhu', '刘著', '00', '', '18600280901', '2', '', '4b921767138d85823da70f6b2770a3a3', '88930f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100406, 1003, 'zhuhua', '朱华', '00', '', '13810307395', '2', '', '2f4c9d06fb7612ee30a497accde3d266', 'd5014d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100407, 2000, 'liuyang1', '刘杨', '00', '', '18910252849', '2', '', '42f2a634fe40f0fd8a94f23a73e6a6a0', 'be50c4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100408, 1003, 'dingmingliang', '丁明亮', '00', '', '18301529526', '2', '', 'ee2e264902adc5d136e2d7144bed5514', 'e9647e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100409, 1002, 'shensai', '申赛', '00', '', '', '2', '', 'fd8d5aa4d07967ae86233ef4eb39b4d1', '1e583f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100410, 1000, 'zhaohao', '赵昊', '00', '', '18001379396', '2', '', '5f2d7457546f7d3a7f952fe1604e315f', 'b4781f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100411, 1000, 'yukaihui', '于凯惠', '00', '', '15010065780', '2', '', '2a1c7b0d6e9cd99e94436f4ee4db45fc', 'b662c1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100412, 2000, 'wangdong', '王栋', '00', '', '13126711122', '2', '', 'eb9de4a99d119458c56c0b65ffbff3f2', 'c21bba', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100413, 1002, 'bailiming', '白利明', '00', '', '17600100384', '2', '', '03dfd7ab6951ced60a2fad9aaa538ff5', '39066a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100414, 1009, 'qihui', '齐晖', '00', '', '13810515458', '2', '', 'a169fb3282c7e7e42df32241ccc32989', '28c6d6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100415, 1009, 'zhangjiayu', '张佳瑜', '00', '', '15011006809', '2', '', 'd2e625881c820f66e779a8ed102da9c2', '8c7c5a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100416, 1009, 'jingjing', '荆晶', '00', '', '18601126680', '2', '', '6d09d281b0b2da4da8037bd5f1212a0f', '994910', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:51', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100417, 1003, 'liaoyunfa', '廖运发', '00', '', '15573037282', '2', '', 'fa2e68393be628bacae94dba24a86480', '738f9c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100418, 1008, 'lipeipei', '李培培', '00', '', '18513157939', '2', '', 'dbd2bfaf1886ab1932674757f08dfdce', '3e07aa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100419, 2001, 'songxiaoguang', '宋晓光', '00', '', '', '2', '', 'cc907b023f95921203ce65020073978d', 'b57050', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100420, 1015, 'xuhengshan', '徐恒山', '00', '', '18600086792', '2', '', 'c32927816830f25256f734df5cab7cf6', '2df47d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100421, 1002, 'ganshengcong', '甘胜聪', '00', '', '15510400262', '2', '', '11c7fab1503b03e14f07b595b5cf6ef1', '6c38d6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100422, 1000, 'wangxiaolong', '王晓龙', '00', '', '13466619436', '2', '', '95c465164bb2f0cc91b7d32026f46695', 'ee5e93', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100423, 1003, 'zhangyipeng', '张义鹏', '00', '', '18910311044', '2', '', 'a006a31b86e33b4d614fbe11edf4efae', 'e8ff41', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100424, 1008, 'test1', 'test1', '00', '', '', '2', '', 'd558d82676a5542909be6d2cf04a4496', 'e20beb', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100425, 1008, 'test2', 'test2', '00', '', '', '2', '', '7ca732dd03cfbb10ec23902499beff3e', '7d352d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100426, 1008, 'test3', 'test3', '00', '', '', '2', '', 'e64d345d66b1e3ad6254e237880f4373', '8dfc2c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100427, 1008, 'test4', 'test4', '00', '', '', '2', '', 'bf734d9bf44850a647d9739d1e1531f0', 'eba417', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100428, 1008, 'test5', 'test5', '00', '', '', '2', '', '05c0ad213e5d0b60483da223023aab45', '00a653', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100429, 1008, 'test6', 'test6', '00', '', '', '2', '', '80c7c58f82684a786a5559fce4187dca', '3037ec', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100430, 1008, 'test7', 'test7', '00', '', '', '2', '', '69d56ee057db908a8b4206696c8cdb5b', '8f461c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100431, 1008, 'test8', 'test8', '00', '', '', '2', '', 'b45538f11bce4ab803b342ea1299551b', '2e27e2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100432, 1008, 'test9', 'test9', '00', '', '', '2', '', '948ef29d43534dbf5f01130ff8241712', 'a963dd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100433, 1008, 'test10', 'test10', '00', '', '', '2', '', '1c281b8cdc18fab5077380f63387e22f', '831072', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100434, 1011, 'zhangtianming', '张天明', '00', '', '18601922099', '2', '', '879b8b4dddd7035eb09c05783edf3fe7', '8350ba', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100435, 1000, 'caofeng', '曹峰', '00', '', '18101098540', '2', '', '3edd4c45eae86c95a39deb376e9eea76', 'e77583', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100436, 1002, 'gaoxing', '高星', '00', '', '13261778613', '2', '', 'e1c96fdd8f63e1c7d662e9f1d0d77f40', '56d045', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100437, 1000, 'liangshuang', '梁爽', '00', '', '18813089628', '2', '', '797dcaea2d668a66c53f22689a6c1347', '248d3d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100438, 2001, 'liuzhipeng', '刘志鹏', '00', '', '13811769854', '2', '', 'e2e50c49a229064f7632db193b66d563', '12402f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100439, 1000, 'caohong', '曹弘', '00', '', '', '2', '', '45cdea89a164885035d794fe187977e2', 'c4b542', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100440, 1001, 'oatest', 'oatest', '00', 'oatest@qa.aibank.com', '', '2', '', '7b9a9b93cd7ced340cf1385ae3b74b12', '71497e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100441, 1015, 'zhaohonglu', '赵宏璐', '00', '', '15634389844', '2', '', 'ff20ffc2259afc4c132d2a97d9d3ddb1', '5e021f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100442, 1002, 'liuyakui', '刘亚魁', '00', '', '18201680706', '2', '', '344e253485560ab5a12cc2365c5d50a4', '5e0c50', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100443, 2002, 'xulifeng', '许立峰', '00', '', '13811766513', '2', '', 'bba47311bd372f15575aad12b6d97d99', 'f5dd4c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100444, 1002, 'guxiaofang', '古小芳', '00', '', '', '2', '', 'b988e4b52e50f526b3f38d97552da74d', 'c0b577', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100445, 1002, 'zhangrui', '张睿', '00', '', '', '2', '', '8c6e418f26d37a5b046efc4cd1ed0019', '6f8173', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100446, 2004, 'wushuo', '吴硕', '00', '', '', '2', '', 'e89ffe4b9dcb2e0f2b391d4f56c2caf0', '183c37', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:52', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100447, 1008, 'fengyang', '冯扬', '00', '', '', '2', '', '7807b81570b8199f8306b17a93f1028f', '5526ea', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100448, 1013, 'wangfeng1', '王锋1', '00', '', '', '2', '', '392c4f18d0fdf7e0fac449004e2f0a2e', '36c911', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100449, 1013, 'lijianjin', '李健瑾', '00', '', '', '2', '', 'a8316452db540110a9ef02bfbd650200', '44d196', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100450, 1001, 'dongjiaxin', '董佳鑫', '00', '', '', '2', '', 'd5ea9463f658f2b8dd8bfa75e2f9dd37', '49db81', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100451, 2001, 'jipengei', '冀鹏飞', '00', '', '', '2', '', '77ee99fced1df6107fc3fbf83c2460e6', 'f938dc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100452, 1010, 'wangxuyang', '王旭阳', '00', '', '', '2', '', '14b5d2fabbedb696dee03241348696c9', '53290c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100453, 1003, 'wennan', '温楠', '00', '', '', '2', '', 'f5725af68f2e511a03906466c6a2ca1f', 'b7f2ce', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100454, 1003, 'qinwanchao', '秦万超', '00', '', '', '2', '', '94aded8666bce4c12c4a80490920816b', '802238', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100455, 1003, 'maqunying', '马群英', '00', '', '', '2', '', 'd0133bd6281bd31464fd802ffc990538', 'ddd6ad', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100456, 1009, 'hekun', '贺琨', '00', '', '', '2', '', '3acb43d42f5ca3dbbef14a23fd19556d', 'ba84f8', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100457, 1003, 'chenhuanhuan', 'chenhuanhuan', '00', '', '', '2', '', '1e00ded065531b37036b0e5bad08466d', '8a1cb3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100458, 1003, 'jincheng', '金诚', '00', '', '', '2', '', '39b951c06cf6cb8b28b7024af48efc11', 'cc6abf', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100459, 1003, 'zhaileiyang', '翟磊阳', '00', '', '', '2', '', '6363e6289c4c4b3821359763f1359fcb', '5a3781', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100460, 1003, 'tangtian', '唐天', '00', '', '', '2', '', 'bb6934e5ff1510e1e71271bfb47e6048', '272296', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100461, 1000, 'wangdi', '王頔', '00', '', '', '2', '', '005f3a4e7d9d59ed9c34d20aee107795', 'cadb98', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100462, 2001, 'zhujianshe', '朱建设', '00', '', '18618339065', '2', '', 'c391f1a7b24f1ece62e0ab156124c49a', '39a01b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100463, 1003, 'chenglin', '程琳', '00', '', '18610296912', '2', '', 'babca051849efc7a286631119e4d12e6', '13ca09', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100464, 1000, 'yuyunfei', '余运飞', '00', '', '18718528692', '2', '', '8d8e8941ce5544a0034b9c2e7ded6c06', '39f9cb', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100465, 1000, 'liangjie', '梁洁', '00', '', '13717541997', '2', '', 'ab5814c2aff81b77a6e299422a26c0bc', '160de1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100466, 2001, 'zhanghao', '张浩', '00', '', '13691499256', '2', '', '2eaccbe1e3d0a88ddc729c041fe26ff6', '80f38c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100467, 1019, 'zhangqiqi_intern', '张琦琦', '00', '', '18800139633', '2', '', '708730681ccf7ec3bb555013ce8631d0', 'cc81aa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100468, 1000, 'baoyiming_intern', '鲍逸明 ', '00', '', '15810010271', '2', '', 'a6e566461515464e264d6ba2a96a3621', '59e625', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100469, 2002, 'wangqixi', '王奇希', '00', '', '18925060805', '2', '', 'bc079224bab9d5f16f7f75fb3ced9f37', '493086', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100470, 1001, 'liuqingchuan', '刘晴川', '00', '', '13810717426', '2', '', '6f9c20792fb44a94d080545b8b5e66e2', '9bc73f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100471, 1011, 'xuemai', '薛麦', '00', '', '16619757989', '2', '', 'beda89156bf95770d99ae924031c163e', '4ba4b8', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100472, 1002, 'gaorui', '高蕊', '00', '', '18701297900', '2', '', 'a9e7347fb1bd3460b302632106e96129', '21cfeb', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100473, 1019, 'dengyuying_intern', '邓玉颖', '00', '', '18800196063', '2', '', 'f23c30d8956bcaf5450f584c10c0a2ef', 'f65422', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100474, 1017, 'raowei', '饶伟', '00', '', '18610892001', '2', '', '53157205348905963aa859e248fd0156', '678eb1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100475, 1000, 'yinye', '尹晔', '00', '', '18810278561', '2', '', '3274e93dba75b2409fe63f2991feb2cb', 'dcfc46', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100476, 1002, 'wangcheng', '王城', '00', '', '17600206167', '2', '', 'd28d7df9f50af1456776eb7c60c8f992', '659e76', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:53', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100477, 1002, 'shangjiwei', '商基伟', '00', '', '18739974824', '2', '', 'ab266571411b209483f41bf4d70ade30', '2b8567', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100478, 1002, 'douning', '窦宁', '00', '', '18210012108', '2', '', '093192e7a18b07df623991e6e95d0181', 'ee1151', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100479, 2000, 'zhourunxuan', '周润轩', '00', 'zhourunxuan@qa.aibank.com', '15726623956', '2', '', '7b7a54c2402b5efe409da96fd2756ddc', '098522', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100480, 1000, 'supo', '苏坡', '00', '', '18501372043', '2', '', '3be99e691c78f6cae0d3d0006ee7ab05', '6e6dc9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100481, 1002, 'huyang', '胡杨', '00', '', '18515996761', '2', '', '6204833522fa4adb5a6762c58bee6cac', 'c35854', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100482, 1003, 'shenningbo', '沈宁波', '00', '', '15011442169', '2', '', 'd1f277106748d7b72c23809f7d137834', '1a48c9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100483, 1009, 'zhangyulu', '张宇璐', '00', '', '18810589196', '2', '', 'd5081e18f23a7dd6a4206c3df95e27a3', 'e64893', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100484, 1010, 'dujingjing', '杜晶晶', '00', '', '15652955117', '2', '', 'dc117b046242cccd8ecc795aa9305ca0', 'c4da27', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100485, 1010, 'zhanglingda', '张令达', '00', '', '18511249669', '2', '', '9194d75b97212c043a3081682eef4875', '36da39', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100486, 2000, 'chentanbo', '陈探博', '00', '', '18612701603', '2', '', '0fee044dd6f7b1164aba48cc52f898ea', '994465', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100487, 1003, 'changzhigao', '苌志高', '00', '', '18301349872', '2', '', 'e0ccfff2343690cdd8667718cf3895c6', 'f5ad34', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100489, 1010, 'chenmingnan', '陈名楠', '00', '', '18070461', '2', '', 'ef34838eac205deca150b6f9bb84f33e', '12eb28', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100490, 2002, 'zhangzhen', '张真', '00', '', '15801490556', '2', '', '8c26312763078b50103a2f727977bcf8', 'b5636a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100491, 1001, 'lijinyi', '李金怡', '00', '', '13370123556', '2', '', '6fc9a634b0a59736eb802881bb7c910d', '4f194a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100492, 1010, 'guoxialin', '郭夏霖', '00', '', '18566266997', '2', '', '356f9868845d50e11bf32e303afe71ba', 'eff3eb', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100493, 1010, 'liulinlin', '刘琳琳', '00', '', '18810651862', '2', '', '46e866ddfec6234fc61f4e5bd446f4e1', 'f3ac67', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100494, 1003, 'sunhaitao', '孙海涛', '00', '', '18514055207', '2', '', '4508382d5b27e6583e4ed7a4a814e619', 'a59313', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100495, 2001, 'dongcaichao', '董彩超', '00', '', '13752126825', '2', '', '234e8b506491f1c3193cad4b7f622b26', '7dc04a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100496, 2000, 'cuipeng', '崔朋', '00', '', '13810507731', '2', '', 'a2280f1e910ff15fcba4d81a9f2587b5', '9d2a09', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100497, 2000, 'wangtaoran', '王陶冉', '00', '', '13240134352', '2', '', 'c2fb8f707cb972c6e39f18b01b5c5e9e', 'fb307b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100498, 1002, 'linanjie', '李男杰', '00', '', '15301367760', '2', '', 'b26b4ad67691d3de10ef80ebca8e48e7', '0653d5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100499, 1000, 'yangliu', '杨柳', '00', '', '18519378063', '2', '', '6b5bee6b0a9df0c023bac626ce6e06df', 'e0bf1e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100500, 2001, 'yangweitong', '杨位通', '00', '', '18678657923', '2', '', '8a5c8ab40b7cc5d59163cbc116ac9f03', 'fc095c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100501, 1009, 'mawenjie', '马文杰', '00', '', '1884084497', '2', '', '56113df3bd572e7656d740470ba139ad', 'fa78ec', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100502, 1000, 'taoxiaoli', '陶晓丽', '00', '', '18910274993', '2', '', '374f0fd423cffb87b63a15a0bbbfcfe6', 'b0721d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100503, 1000, 'muyunyang', '牟云扬', '00', '', '13521760960', '2', '', '9504e0306063e963901475feec85682a', '7d9519', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100504, 1005, 'haibenben', '海犇犇', '00', '', '18310733054', '2', '', 'd79577ed0d44629598ebb4fd28380c18', '5e3bc3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100505, 1002, 'wangzhanbing', '王战兵', '00', '', '15510258204', '2', '', '4bd008263a086e7fcd946767337de63d', '2a0ab6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:54', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100506, 1002, 'zhangsitian', '张思田', '00', '', '13311018311', '2', '', '292895cc0c902fa9fc99af0093aa4ad4', '085bd2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100507, 1002, 'liyuechao', '李跃超', '00', '', '13811723071', '2', '', 'd3d5ca9f3f2772be2b269a6b18eaba50', 'e06cde', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100508, 1003, 'zhangjiaming', '张家铭', '00', '', '13718399847', '2', '', 'cb6c24f44964862d64a75c293be695bd', '94858b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100509, 1005, 'lijing', '李京', '00', '', '13260155623', '2', '', '2fe1fcdfa3b12d937d2bf2d648eac1c4', '355af5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100510, 1000, 'sunboyang', '孙勃洋', '00', '', '18510412233', '2', '', '261afc9ccb11d3d69e153dfa5fcdc69e', 'bebeaa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100511, 1000, 'hanfeihu', '韩飞虎', '00', '', '13522569989', '2', '', 'e2349100b37a94a263800ff93eeaa3d0', '2f3b85', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100512, 2001, 'jinyachao', '靳亚超', '00', '', '17691071802', '2', '', 'bc345215cfd914dda853510981788120', '9e3f59', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100513, 2000, 'juxiangming', '鞠向明', '00', '', '18612280986', '2', '', '1562c4616b7f2b5c084e3f236ec6ca17', 'd98c10', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100514, 1002, 'goubo', '勾波', '00', '', '15652965523', '2', '', 'a99714be1ded02fb9303c4c83238b8bf', 'b86bd1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100515, 1000, 'qidalei', '齐大雷', '00', '', '18513120130', '2', '', '5e9942a706f6f95b6ed658adeece3511', '7d38fb', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100516, 1000, 'likun', '李琨', '00', '', '18515397129', '2', '', '851779d345d2364a1f5b3da5b15bf00e', 'e747d8', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100517, 1005, 'wanghaiwei', '王海卫', '00', '', '15650713793', '2', '', 'ebfd2838f80a1cbc7e458d1a607b898b', '1fb634', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100518, 1005, 'zhangzhiyong', '张挚庸', '00', '', '13811671016', '2', '', 'e4e07f6bc0ea483201d269c89ec396e6', '3f7977', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100519, 1010, 'shijinsheng', '时金胜', '00', '', '18661681903', '2', '', '57e3c0c65ce978cc5aea4ad6d5c33e2d', '42dd3a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100520, 1003, 'zhangtao', '张涛', '00', '', '18655110853', '2', '', '7979fe4d924027006f2e9791dcc5c78d', '8b964c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100521, 1003, 'zhangyu', '张宇', '00', '', '18601369924', '2', '', 'a4b90b91c4e0460d52581320298c4491', '7cc0f5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100522, 1009, 'zhangwenquan', '张文权', '00', '', '13426295869', '2', '', '00f06397217abb8d6a713fc36ac26977', '7caa7d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100523, 1012, 'chenxin', '陈昕', '00', '', '18519022079', '2', '', '3097a20ebb320bdc73a2c522dc194915', '22ae9b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100524, 1000, 'dongpo', '董坡', '00', '', '18510329063', '2', '', '727b11a1c4860a57738a0da06592bccd', 'c48f35', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100525, 1000, 'shangguangshuo', '商广硕', '00', '', '13031198677', '2', '', '528800daef5d959ff8a79737979c9305', 'a7c5cc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100526, 1000, 'guojiabao', '郭家宝', '00', '', '13031198677', '2', '', '252e5228d7a1ec2e0f903cb6d45f3b53', '2265cf', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100527, 1000, 'suzhibin', '苏志斌', '00', '', '1563603096', '2', '', '968bcb61a5ce61c350be615404599d63', '45e32b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100528, 1002, 'zhangzhongsheng', '张中晟', '00', '', '18611750479', '2', '', '074f059e271a50533e1e91ed01aeaf6f', '30808d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100529, 1004, 'fuxu', '傅勗', '00', '', '13581995241', '2', '', 'a12e932771e2665af3b1fbeba0a53a12', '0a6654', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100530, 1002, 'chenzhenru', '陈贞汝', '00', '', '13910161804', '2', '', '485e0b886b09abf1c64e50f4db8f0298', '39cc8e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100531, 1000, 'bipeng', '毕鹏', '00', '', '13810936376', '2', '', '769ce7d18d69a591a7ec440968197b3d', '550cbd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100532, 1000, 'huangguohai', '黄国海', '00', '', '15511909898', '2', '', 'b1e5d737b251ae5d5cd00e526db3a0bf', '57844f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100533, 1000, 'wangpengchong', '王朋冲', '00', '', '18811738173', '2', '', '0e2bb8a71fa652d3618f98255635cf41', '44b4ac', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100534, 1000, 'luhoujun', '卢厚君', '00', '', '13621032370', '2', '', '5ef0179ce2f79e7b6d68a9f9939b228f', '5a73aa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100535, 1000, 'wangbo', '王博', '00', '', '15122087227', '2', '', 'a8ae97fafcadbf46e531d1ef402aaa2b', 'ef7074', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:55', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100536, 1000, 'zhangqianlin', '张铅临', '00', '', '17665286399', '2', '', '2c4b1b6d569c5b4015ad6f7c85f3ff10', 'be6b15', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100537, 1007, 'wangdongming', '王东明', '00', '', '18519052216', '2', '', 'ea0eace86ee33b0e1f30b9a0ede34905', '7803fa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100538, 1003, 'raodezhang', '饶德彰', '00', '', '13522550865', '2', '', 'a0d588441461e8643de48001302d4375', '484ee4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100539, 2000, 'cuilin', '崔林', '00', '', '18610847474', '2', '', '2bba48be845453e28b38b932a55bb7d9', '40f20b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100540, 2000, 'chenlizhi', '陈立芝', '00', '', '18614048180', '2', '', 'b70b9d13af220cb7945a08e74980f137', 'b2b60f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100541, 1003, 'jianqi', '见琦', '00', '', '13269528595', '2', '', '09b2fc7f52575fe0ad4c5db63814ffcc', '54e61c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100542, 2000, 'baixiaoyu', '白晓宇', '00', '', '18611955379', '2', '', '109e5905d60d95ed15b697a39fec44b6', 'fb12b0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100543, 1000, 'shaoshengmei', '邵胜梅', '00', '', '18519283736', '2', '', 'c5df8c344a99b89b52919ec28906e33d', '0e061c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100544, 1000, 'gaolinlin', '高林林', '00', '', '17600660865', '2', '', '53e28247e87b884c0ea442483c002b1e', 'b491ee', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100545, 1000, 'hanxusheng', '韩旭生', '00', '', '', '2', '', 'bfc5962f568f218ae714dea6fbcefe1b', '74b7c9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100546, 1010, 'chenshuaishuai', '陈帅帅', '00', '', '18299906557', '2', '', '3fa9406a74619e2984fdfb1d4c3cf0c9', '48b33f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100547, 1000, 'liumingzhang', '刘明璋', '00', '', '13333122294', '2', '', '39d84cd7e69e8e419b030a8a0dea1844', '958b5d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100548, 1000, 'linhe', '林贺', '00', '', '18518281025', '2', '', 'deaef001c0e7f7477802eace530646c2', 'd8ac96', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100549, 1010, 'zongxun', '宗迅', '00', '', '13521703989', '2', '', 'c7fc2487e2062d1661c531f8d92895b6', 'af0943', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100550, 1011, 'liuqiang', '刘强', '00', '', '15210604661', '2', '', 'b3bf76e6a42d6557a33da4839c8b979d', 'f92784', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100551, 1003, 'zhangliming', '张立明', '00', '', '18701350771', '2', '', '2e43de952754d1c73cd4c71ec33ba975', 'e12a25', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100552, 1003, 'lipengpeng', '李朋朋', '00', '', '18513208720', '2', '', '3e72de771ec81e67acfea750eda38fec', 'aae868', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100553, 1002, 'guojianwei', '郭建伟', '00', '', '18611223459', '2', '', '3ddad7aaea9bbbd94d49bf7016a84e86', '22ba17', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100554, 1000, 'leichen', '雷晨', '00', '', '18614015560', '2', '', 'c49efb06b2fcdb4ed677fa6b88477349', '21d391', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100556, 1000, 'gexingchen', '葛星辰', '00', '', '18686665633', '2', '', '32b99fa6a97f86c281dee8a2b1ed338a', '52f2fc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100557, 1002, 'wangping', '王萍', '00', '', '13691516930', '2', '', '1841c52137cedc02649c95e4d47f38d4', '260085', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100558, 1011, 'fengxuan', '冯萱', '00', '', '15210102599', '2', '', 'fe2ff103aef4820daa78de475970a845', 'cfbbbe', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100559, 1000, 'chenglong', '程龙', '00', '', '15910629703', '2', '', 'fcb9a4ed22f559f01a8415e5b50e0b51', '432031', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100560, 1000, 'yangbaoan', '杨宝安', '00', '', '18210023001', '2', '', 'eade74fc048f4967252e997334e162f9', 'dfad6e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100561, 1019, 'tongzhaodi_intern', '仝召娣', '00', '', '15510750321', '2', '', '1e2608b8f0b22bf3fbf7f94ee8a1fe48', 'e319d5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100562, 1000, 'zhangyanliang', '张延良', '00', '', '13716803616', '2', '', 'f0bd466cbafe4157ecc119e4ae5f52e3', '6df7ac', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100563, 1002, 'mayanhong', '马岩红', '00', '', '13426083128', '2', '', 'c259eb563ece23b3d47f815610184281', '40cb38', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100566, 1010, 'yutao', '于涛', '00', '', '15153286161', '2', '', '99bea22468a8260d950498b6a52f5d4a', '24022b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100567, 1000, 'liufang', '刘芳', '00', '', '18611392445', '2', '', '8ce26db8bca29a5ef999f4aeb46c6abe', 'd3555b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100568, 1019, 'chenpengyu_intern', '陈鹏宇', '00', '', '13716537600', '2', '', 'cae64082520e17ce48ac023fac108677', 'c832d7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100569, 1000, 'lujiliang', '鲁济良', '00', '', '13611012927', '2', '', '6ee8b6c89a9b54761baaf86a8d86c8d6', '8b8026', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100570, 2001, 'haojianguo', '郝建国', '00', '', '13693537055', '2', '', 'f91baa91608c6d56bb820a3f7daf1258', 'a20e09', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100571, 2001, 'sunzhongan', '孙中安', '00', '', '18662625203', '2', '', 'a1bf9702287ed7ce410ff9b86acd9613', 'a49974', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100572, 1004, 'tengjing', '滕静', '00', '', '18601146155', '2', '', '55ecc3a5d9ef6f072f4fb70d8928cf2a', 'ed50bc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100573, 1019, 'liangxiao_intern', '梁潇', '00', '', '18728191420', '2', '', 'da39b34cf73bf2a834a6cd0e79eecbd0', '5474af', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100574, 1000, 'jiangyilan', '姜一岚', '00', '', '13521793490', '2', '', 'f25e4e7de050cafac75be7225b70911e', '4d4f10', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:56', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100575, 1000, 'liuhewei', '刘贺伟', '00', '', '15011409136', '2', '', 'c628d163eb86770d84d31653a8455dfd', '86ee33', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100576, 1000, 'zhaifangqiong', '翟芳琼', '00', '', '15010926575', '2', '', '43b74d7401db6ffa66cf9d6d6a614746', '53d6af', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100577, 2001, 'chenzhongzhi', '陈重志', '00', '', '18811403515', '2', '', 'e06230b3fe593a713e045685440590ff', 'c16573', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100578, 1005, 'yanghaile', '杨海乐', '00', '', '18600614119', '2', '', 'd355e49c4b29d18f825df5fc659dc944', '773fa6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100579, 2000, 'gaokan', '高瞰', '00', '', '18600285605', '2', '', '100e3a4e5dcd63413d54025ad16c4681', 'dda725', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100580, 1001, 'qiuyi', 'qiuyi', '00', '', '', '2', '', '273e9846766f316e0b5678111f2d23a8', '2eefeb', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100581, 1021, 'hanxu6', 'hanxu', '00', '', '11111111111', '2', '', '86fc55e42f4df38c92ad3d2af32085da', '114b28', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100583, 1000, 'zhanglei', '张雷', '00', '', '', '2', '', 'a0f968ad1dba988bb1bd6f9658b8e064', 'd6de97', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100584, 1003, 'baizefan', '白泽凡', '00', '', '13681371880', '2', '', '6d0248e52efd34959d5654fc462f8718', 'fc9709', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100585, 1005, 'meiyu', '梅玉', '00', '', '', '2', '', '46e411dbeb53c1ba4ebbf119988a3cbd', 'a8d726', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100586, 1002, 'ganfengyu', '干丰雨', '00', '', '', '2', '', '48868919d74e82c186c878cfe8f4a7c9', '802cd1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100587, 1010, 'lixu', '李旭', '00', '', '', '2', '', '1b115d998f917dc0205e4137433b7047', 'a79871', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100588, 1000, 'heshibo', '贺世博', '00', '', '', '2', '', '24bb84fd4bb7fb783e5a75ab6e1d6956', 'fed2aa', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100589, 2002, 'chenghaibing', '程海兵', '00', '', '', '2', '', '1063fb97ca4c3f15703235a2aff328af', '79a7f6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100590, 2001, 'linyixing', '林怡兴', '00', '', '', '2', '', '72d67c5cb57930e1a72ef6a0bda39178', '9e902e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100591, 1008, 'hufuping', '胡福平', '00', '', '', '2', '', '3427224743536b31b3ce782c1d3b67cf', '8321d5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100592, 1009, 'shantisong', '单体松', '00', '', '', '2', '', 'c7fb421718889bd26dd43b9086b0f173', '00f630', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100593, 1009, 'yujizhou', '于济洲', '00', '', '', '2', '', '75f520ac35cf9ba71ad6566914b6c174', 'cf5ebc', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100594, 2001, 'dongna', '董娜', '00', '', '13810894372', '2', '', 'e371d40bb7ea7c1825c9de3ddeac797b', 'ce15f3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100595, 1010, 'qilong', '亓龙', '00', '', '18510682646', '2', '', '06de520bf97dc9e6808ecd9d70095418', 'a6b0d7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100596, 1002, 'liuchao', '刘超', '00', '', '18618499361', '2', '', 'aa76f26bfaf097863ef3f7a937622c55', '69d470', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100597, 2001, 'jinshichao', '靳世超', '00', '', '18132520871', '2', '', 'db94438e6de1c8a03c51af1ed6bfa775', '1380e2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100598, 1005, 'wanghao', '王浩', '00', '', '', '2', '', '4fcc11d2b07fc88bafe22f90c49bd995', '0fe431', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100599, 1003, 'lileifeng', '李雷锋', '00', '', '', '2', '', 'df4d6df17552e801013638363fb47a53', '57f888', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100600, 1002, 'mengnan', '孟楠', '00', '', '18701101258', '2', '', 'd187741376200532a02943d5cd97d2b9', '94671e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100601, 1003, 'wangxuemeng', '王学猛', '00', '', '15600103371', '2', '', '765bc02fda9af16604475194cde32ed5', '1fbab6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100602, 1013, 'shitianqi', '史天奇', '00', '', '18614049398', '2', '', '78eac13b01f2e10b4a9b10eaf0fbd7b2', 'a48712', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100603, 1002, 'zhangxiaofeng', '张小锋', '00', '', '18339219026', '2', '', '49faa9bf91db710534dac30935b33fce', '8c8db3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100604, 1015, 'liyoucheng', '李有铖', '00', '', '18660411516', '2', '', 'c0c4c41a84b35dcbd5185b69666f02a6', '249155', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100605, 1013, 'huangyongbin', '黄永彬', '00', '', '', '2', '', 'f78dc5112fee3031889da79dfbe6605f', '552463', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100606, 1009, 'xutao', '徐涛', '00', '', '18510328832', '2', '', 'e23d43a06bbcd321355342c71d6105c1', '418fd4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100607, 2001, 'zhaoxueliang', '赵学良', '00', '', '18611762442', '2', '', 'd50c3addd5c4ef886803461af9f61b5f', 'ee2eac', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100608, 1011, 'duanfugao', '段福高', '00', '', '13520875946', '2', '', 'b3283e4891a39a31fda237222ef87fbb', 'd8118f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100609, 1005, 'yangjin', '杨晋', '00', '', '13810297852', '2', '', 'ff9f024cc0f4061d55550817db2dde92', 'd6d4ec', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100610, 2002, 'yanshenglun', '闫盛伦', '00', '', '13311382775', '2', '', 'f0f8bb9acda9a5c0f9c79afce137df96', '731571', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100611, 1000, 'songwenchao', '宋文超', '00', '', '13651314824', '2', '', '5404cbc02dd7dac7b659358defb4eae5', 'd6049b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100612, 1002, 'pangzhen', '庞榛', '00', '', '17611169722', '2', '', '4ea2e199e2bfeff3a550f72896605193', '8e70a5', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100613, 1009, 'xuda', '许达', '00', '', '13341127053', '2', '', 'c9ce30e3cabf6ff955b7ae54a23de70a', '554520', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100614, 1003, 'liujindai', '刘金代', '00', '', '18612450680', '2', '', '294e1726b6b2da3132766cef57168763', '385dba', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100615, 1002, 'liulongfei', '刘龙飞', '00', '', '', '2', '', 'af9c171a9da3b3d3da321243967cf3cf', '6c7271', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100616, 1010, 'xuchao', '徐超', '00', '', '17821865145', '2', '', 'cc7e12301f4b14bee868bf373b732e2a', '4cb295', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100617, 2002, 'qiuguoxia', '邱国霞', '00', '', '18705142516', '2', '', '70b2bec7e405d2c62ebccb79beb691e2', '22db0f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100618, 1002, 'qiqinghai', '戚庆海', '00', '', '15116945791', '2', '', '7584659d3c5ec7c17b6a769e0c095528', '49ff5e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100619, 1009, 'zhangwenzhao', '张文昭', '00', '', '17610680299', '2', '', '37e0f116b88b8b55d9a3d4fed31a4c2e', 'bf7ad7', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100620, 2004, 'wangshi', '王石', '00', '', '18616004987', '2', '', '7bda69217f0f7371d6e4abb991b5f90e', '4156bf', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100621, 2001, 'lixiang', '李翔', '00', '', '18310145153', '2', '', '5473d5b57ffb4b37c76c8cfae1d8509c', '838dcd', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100622, 2001, 'zhanglianxiang', '张连祥', '00', '', '13269769001', '2', '', 'd85916098175f4e1618bd1be0cbd8d0c', '0728f0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100623, 1002, 'yupeiyu', '于佩玉', '00', '', '18311423916', '2', '', 'fc40e29f5c09b795bca4405f52fa65bc', '0f2a00', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100624, 1003, 'lijing1', '李靖', '00', '', '13051388388', '2', '', '6208efb0dc84a3c7a3be26f74aaae09d', '936aa6', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100625, 1009, 'guochengfei', '郭承飞', '00', '', '18211025996', '2', '', 'bde852d9d15dab112450363c001a1680', '72723c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100626, 1002, 'zhangshengce', '张升策', '00', '', '15120058653', '2', '', 'd30f04c04422d7b4ec26c20fdc49e043', '39f91f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100627, 1002, 'fanqiang', '范强', '00', '', '13911090546', '2', '', 'a8d7f4080cd896c81e1859b6f09bb51a', 'b38186', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100628, 2001, 'zhangyi', '张毅', '00', '', '15810668437', '2', '', '71e3ef74430bd5ea30cde5b45b344b41', '9f89d9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100629, 1009, 'chenjiayun', '陈佳运', '00', '', '13811689972', '2', '', 'a3f8107a6ffdd8557fba285b251c12a6', '66eceb', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100630, 1002, 'zhangyinsheng', '张寅生', '00', '', '18622993798', '2', '', 'b4d3663aebbb4db698ee0f6efe926afe', '8879ab', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100631, 1002, 'mahongjun', '马鸿军', '00', '', '18600990363', '2', '', '9b3e31cc2d782bd371e98e1e804e69ff', 'ea9e96', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100632, 1000, 'zhaoxiang', '赵翔', '00', '', '13810431191', '2', '', '7d0da9ca367023602122f77137de805d', '3c667e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100633, 2001, 'huruikai', '胡瑞凯', '00', '', '18910347985', '2', '', '0a77ee87e6422800de71cdf8ef9f90cb', '31ec31', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100634, 1000, 'xiaoruiqi', '肖蕊琦', '00', '', '17717239917', '2', '', '296e0bcdcdb9b95868b32aecc2b764c2', '5a5be1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100635, 1003, 'chenhuihui', '陈惠惠', '00', '', '18611601203', '2', '', '04ad1e036f926aa24e3de21d009feeb5', '8855d0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100636, 2002, 'weixin', '魏昕', '00', '', '18810665089', '2', '', 'acdfb1194a8391adffbd41a2f4d8c614', 'd16366', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100638, 1001, 'sunpeng', '孙鹏', '00', '', '', '2', '', '6f063374abac46c9e7d8627557d85c0d', '9df856', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100639, 2001, 'lihanqing', '李汉卿', '00', '', '18506826512', '2', '', '97c5b299e4dcb7ba7b557407632c2e70', '231b86', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100640, 2001, 'dinglaiqing', '丁徕卿', '00', '', '13520313590', '2', '', '864b2120f14c166a9c6bee26068be565', 'dbf2f1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:58', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100641, 1003, 'zhangtiande', '张天得', '00', '', '15901428123', '2', '', '66492b516000243cbc7d230b2c8f7bf5', '02050d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100642, 1000, 'gaochao', '高潮', '00', '', '13426313190', '2', '', '30810331e94fc9aa7839a8b59a5aff35', 'c410e4', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100643, 1003, 'dingyisong', '丁一嵩', '00', '', '18511333039', '2', '', '9491f696095318a2de186260811a5808', '5933c9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100644, 1010, 'liuhongzhang', '刘红长', '00', '', '18801294921', '2', '', '941aa7e61300e9abc290d6fdacdde960', '3a1632', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100645, 1001, 'yaosikun', '姚四昆', '00', '', '13666059147', '2', '', 'b562b8643e7e9dbe536742e535f23cff', '774927', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100646, 1004, 'shanlu', '单鲁', '00', '', '18515661955', '2', '', '7d6072ddf56ffc8c638f517bc3e0731b', '8fed60', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100647, 1004, 'qiaojian', '乔健', '00', '', '13901266634', '2', '', 'eb88cbecd62c8f4a75acf11b7ef35345', 'a4b907', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100648, 2001, 'jiangziwu', '姜自悟', '00', '', '18310177966', '2', '', '444a37697310cbdb31e0b8ee0f010e27', '77739d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100649, 2001, 'yiximin', '伊西民', '00', '', '15600685187', '2', '', 'bf26d07419bfa6dbb9883cc8fa2c0b2f', 'def44f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100650, 1000, 'yangmeijuan', '杨美娟', '00', '', '13530965592', '2', '', '06dc2f6d3a7a9aaec53bf2fd6d8d0d22', '682110', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100651, 2001, 'wangsanhu', '王三虎', '00', '', '15810108528', '2', '', '21121beadd74290d33f1e9126b0c14fb', 'a79b2d', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100652, 1003, 'liangmin', '梁敏', '00', '', '15901450551', '2', '', 'b48bb33b46aae85e94d1058956b7872b', 'a38d7c', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100653, 1001, 'libo3', '李博', '00', '', '13261768857', '2', '', 'ee5951ea79ce6119de220e8eef49bb26', '205d71', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100654, 1000, 'liuyongjun', '刘勇军', '00', '', '13811425148', '2', '', '5ef465c51fb233d496762cb5d801fa13', '0b3dab', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100655, 2001, 'fanxingwen', '范兴文', '00', '', '18380447938', '2', '', '3217edf191be41847fa84e021026073f', '3c02a1', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100656, 2002, 'yuanjiapeng', '原家鹏', '00', '', '15210718316', '2', '', '862b6ab9c9066a07b5d31d54443ee340', '3cb88a', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100657, 2001, 'tianzhaojie', '田兆杰', '00', '', '18301057973', '2', '', '5e1e289bec17ed7bd7b8d74c3a417842', 'f7ef31', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100658, 2002, 'zhaofenglong', '赵凤龙', '00', '', '', '2', '', '868d7aee3838e5f165315fe63cc5f392', '3f1c95', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100659, 2001, 'kouheqiang', '寇合强', '00', '', '18001285920', '2', '', '6846683cf2490462384a00e7dcb56531', '55bbc3', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100660, 2001, 'liuhelei', '刘和雷', '00', '', '18209298683', '2', '', '165803837a37a22f721cd77d9a88efa7', '4e3452', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100661, 1000, 'wangqirui', '王启瑞', '00', '', '18902862206', '2', '', 'ed38e1babe7e72b288b74a6247fcfb4e', '9f8547', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100662, 1000, 'chenxi', '陈羲', '00', '', '18010032199', '2', '', '4ad04bffc0ccde968db2b5a1fbb9ca83', 'a5fd26', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100663, 1000, 'konglingsheng', '孔令生', '00', '', '13581757787', '2', '', 'b6f086b1acf0ada29fab93c0b2fb4f47', '8890c9', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100664, 1002, 'wangxin', '王欣', '00', '', '13522570106', '2', '', '9bb7382e0156399d187ba8c4f4841981', 'a7489f', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100665, 1000, 'guotao', '郭涛', '00', '', '18501955086', '2', '', 'dc98587196449ec2b769f20037386ced', '66a209', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100666, 2000, 'xulong', '许龙', '00', '', '', '2', '', '60b18bade8e77e61d30764492fee1b5b', '40a60e', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100667, 1004, 'suntianli', '孙天立', '00', '', '18811305568', '2', '', '6796f0470ea4c24a7a668a51eb3b6350', '8ed3ee', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100668, 1000, 'yanyiheng', '闫毅恒', '00', '', '18513036705', '2', '', '8119316c6d2f79fba78d5c81e6aefc8f', '39a696', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100669, 1000, 'wangyuexi', '王月茜', '00', '', '13810776336', '2', '', '1b7fc10e08636c52cfdde8bd385ebe7d', '9fed6b', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100670, 1002, 'bisheng', '毕胜', '00', '', '15652382241', '2', '', 'ccd61f8d29271204bd5778f1cea6f423', 'e43bb2', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100671, 1002, 'chengxin', '成鑫', '00', '', '18811577240', '2', '', '471b36ed9e273b9ed29b03c5614bd8de', '75dce0', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100672, 1010, 'guojing', '郭靖', '00', '', '18675590624', '2', '', '30dea012074bec5de0383fc47da138b1', '35c687', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100674, 1003, 'yanyiming', '颜祎名', '00', '', '15658816690', '2', '', '97efe888f72f888781219874ec9d727f', '683dba', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100675, 1000, 'jiajuntong', '贾君桐', '00', '', '18500209109', '2', '', 'a9ab9fe8e6a772ac4e79c0d40ddbc514', '0b6017', '0', '0', '', NULL, 'admin', '2019-08-19 14:11:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100676, 1002, 'zhaoyishuo', '赵一硕', '00', '', '13720016981', '2', '', '551ff888685e87d97b688d595d47d45b', 'd4619f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100677, 1006, 'kangtai', '康泰', '00', '', '13661360738', '2', '', '214b8024602142b62d8c3c5411aeb0d0', '1734ac', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100678, 1019, 'xuranjiao_intern', '徐冉娇', '00', '', '16628806728', '2', '', 'b7591e2463dde0a729f164293edc7624', '17a5bf', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100679, 1019, 'lingjiaxi_intern', '凌嘉希', '00', '', '18810138193', '2', '', 'dadd10177b27ea6941d2c49d7a3748ff', 'a449d4', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100680, 1000, 'changmengke', '常孟可', '00', '', '18210040360', '2', '', 'b422855b35f057e93742b45fd8570420', 'd6320c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100681, 1000, 'zhuyanyan', '朱妍妍', '00', '', '13502165117', '2', '', 'e9ca8eb0de16324a76d6a768da5af5ed', 'c01cd9', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100682, 1011, 'wangzonglin', '王宗琳', '00', '', '15810845395', '2', '', 'dd90daccf94ef762bd3f07096c496acd', 'dff243', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100683, 1000, 'wanghaoran', '王浩然', '00', '', '13717517600', '2', '', 'd8c665e0b87195f254147867e4c3a747', '419316', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100684, 1006, 'qiaojuyi', '乔举义信息技术部', '00', '', '13331027812', '2', '', '2bdc7d9c38c620577cccfc331a364dbe', 'edba34', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100685, 2000, 'zhangjichi', '张骥驰', '00', '', '13651102490', '2', '', 'a299e7516ad679cc6d72a03c28e2495a', 'af6602', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100686, 1002, 'huoweiwei', '霍巍巍', '00', '', '18500021705', '2', '', '6c74c86cdf104494f511cae9b7193223', 'b8b5d9', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100687, 2001, 'liuyun', '刘云', '00', '', '', '2', '', '948f01e4996e1efb73a5d07ccf48bed8', 'f62120', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100688, 2000, 'hanliang', '韩亮', '00', 'hanliang@qa.aibank.com', '13240360827', '2', '', '7c121e46b39f7b3361425a39629ea821', '9a535d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100689, 1010, 'liusiyu', '刘斯雨', '00', '', '13436625102', '2', '', '5712e585a614839deb51a5ee26295210', '0eee9e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100690, 1005, 'douzuoshen', '窦祚深', '00', '', '13691079263', '2', '', 'b1ffeaaf1419b176a8daec9d9b9f168e', '34d26c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100691, 1002, 'zhongxiaohan', '钟霄汉', '00', '', '13340993982', '2', '', '4ead34202b7064fdfbbfb4f86e8463f3', 'd7d7fc', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100692, 1005, 'xuzhiqiang', '徐志强', '00', '', '18502906736', '2', '', '9291d2ddcb092dcc012024fe2bc0c8af', '8b40e8', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100693, 2002, 'zhangxinxing', '张新星', '00', '', '18601023504', '2', '', '72a2e037975ec1d3354eb2b61b98c394', '735b94', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100694, 2000, 'lijinpeng', '李金鹏', '00', '', '17611222199', '2', '', '541804c827248d3c3f565695f63cd2ee', '66d7af', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100695, 1010, 'xuhongbo', '徐红波', '00', '', '15010621752', '2', '', 'c4b1e7fa6ba3839282bf44ec1dd985ef', 'c4509b', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100696, 2002, 'pengyunlong', '彭云龙', '00', '', '18612623442', '2', '', '71a0e7cd234501fb2b5deec8e3c13690', 'eaee95', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100697, 1001, 'qushentong', '曲沈桐', '00', '', '15040168041', '2', '', '08982ce26f222882bb22851a87da4f84', 'b0002d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100698, 1003, 'xudebao', '徐德宝', '00', '', '15210660460', '2', '', 'fb71db7c4ac7ff3b8deb1227b9215261', '26a4d6', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100699, 1001, 'anhe', '安贺', '00', '', '13522697656', '2', '', '89fbc6a8e1b1a103019841765cee0131', 'afb13d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100700, 1002, 'dutengxiao', '杜腾宵', '00', '', '18610077265', '2', '', '4be201650e979fbd944ae20d0f45da86', '125f00', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100701, 1000, 'zhuxingyang', '朱幸炀', '00', '', '18610441324', '2', '', '51a4d6fd10bc4bcf162802348f54da45', '15d01a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100702, 1001, 'wuyibiao', '武翼彪', '00', '', '15210503276', '2', '', '9b297cb292d79b8c5d4e7d6169f3f68f', 'b9338a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100703, 1005, 'cuilixue', '崔立学', '00', '', '18633682086', '2', '', '69aeb938998e6711224c6f947bc07044', '2d0590', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100704, 1000, 'jiangchen', '蒋辰', '00', '', '13621006833', '2', '', 'b04cf10dbd8de4b992ba9bb8d2e03220', 'fb2568', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100705, 1005, 'zhumingxuan', '朱明轩', '00', '', '18210521709', '2', '', '858c1a8b4e267a04da968924c12ec62c', '5b94e2', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:00', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100706, 1003, 'lusiyuan', '陆思远', '00', '', '18800184022', '2', '', 'de893ca6969dcf5c9e5e4a837f3a010e', '2c41c2', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100707, 1002, 'tangyouzhi', '唐有志', '00', '', '13520527183', '2', '', '04c39b1b1f7cbbec1db444d13e5f7886', '2c4cf0', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100708, 2002, 'wangxiangyu', '王翔宇', '00', '', '18210223314', '2', '', 'd2f5281118dd26a836919d1ddf9eb418', '038639', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100709, 1010, 'yangyunke', '杨蕴珂', '00', '', '13693210131', '2', '', 'd6b0d68e512913a8edf501bd7b4a2f49', '3f8934', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100710, 1003, 'huqi', '胡琪', '00', '', '', '2', '', 'e33d43ae11a27389c8d618bdf2c8bf26', 'b9cf1f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100711, 2000, 'chaiwanming', '柴万明', '00', '', '13521840283', '2', '', 'b8e7a5cff720a97097d2b986f4c62a81', '06bb0d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100712, 2001, 'fanyukun', '范钰琨', '00', '', '15201518539', '2', '', '4c8fb400f1a893f4142463f1dc6e0010', '46306e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100713, 2001, 'hongdexiang', '洪德祥', '00', '', '18813185090', '2', '', 'e1fe0fe832afa13a049eaa42b43532d1', '703e5b', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100714, 2001, 'yuxinran', '于昕然', '00', '', '15611333112', '2', '', '08edef3827521237e1886c1b7aade3b4', '0bf767', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100715, 2001, 'kongxiaohui', '孔小辉', '00', '', '13810123226', '2', '', '850bff9f17e57b3a7fd428ba7686e4c3', '7becb4', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100716, 1005, 'huyu', '胡瑜', '00', '', '15901503239', '2', '', '68fd9b288724434f799ba646af71d1be', 'ce73c4', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100717, 1007, 'hanyanhui', '韩彦慧', '00', '', '18301652767', '2', '', '4951eac42c418d1ef7115cefbd118fc2', 'e76538', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100718, 1000, 'shiwenjie', '石文杰', '00', '', '18640375636', '2', '', '8fa8b6d38ca0a3036b4697ad6f6614b1', '923ab4', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100719, 1002, 'wenyuanyuan', '温媛媛', '00', '', '13940376581', '2', '', '67aa902a014c0548a83728c0d93a45fa', '965cd9', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100720, 2001, 'caojianfeng', '曹建锋', '00', '', '15880772759', '2', '', '6a19f5adb3422e8cc56666715f207b0f', '8ab766', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100721, 2001, 'zhaopengcheng', '赵鹏程', '00', '', '18301064494', '2', '', '11d977fef7973f12161e072b47d1e4af', '27e422', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100722, 1002, 'wangzhijun', '王志俊', '00', '', '15011530233', '2', '', '18912899ee9814a670e8075c40a73ab4', '67e308', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100723, 1009, 'yinchao', '殷超', '00', '', '18811006562', '2', '', '7b6f9a1e4330dcf01ab0e45411723867', 'b88a80', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100724, 1010, 'guoxuan', '郭旋', '00', '', '18333610825', '2', '', 'd52b2fbc91f3bc2f14e068a7d649b33f', '6e2ccb', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100725, 1005, 'yanghao1', '杨浩', '00', '', '18010170793', '2', '', '883bed9309afdbe4fabdf5ccfc91a454', 'c0337a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100726, 2001, 'fankangkang', '范康康', '00', '', '17645059960', '2', '', '1e326444a646fd36c3d77404ccc9ddf5', 'd4cb4b', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100727, 1009, 'liuhairuo', '刘海若', '00', '', '13901201074', '2', '', '156f45df8a20af4c984fa08f4a760213', 'ad4cef', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100728, 1010, 'sunchi', '孙驰', '00', '', '18311155069', '2', '', '31d949a4043ab3712fcef9c86bbbe0c9', 'dfcd99', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100729, 1005, 'zhangxiang', '张翔', '00', '', '18962508585', '2', '', '601869cc96a01da674ef944c05dc1cd8', 'f90af1', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100730, 1000, 'yuchenyan', '于晨砚', '00', '', '15101680331', '2', '', '452f98a075ced41b70d4a0878523fc8d', 'b1700d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100731, 1003, 'zhangjiatai', '张家泰', '00', '', '15120041091', '2', '', 'd8703b1abdedd904e802fc9782c76dba', '297048', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100732, 1010, 'wangtianqi', '王天琦', '00', '', '17610173557', '2', '', 'fb43f747006a03ba9bbcb7aef3d20ed3', '3147f0', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100733, 2001, 'lijie', '李杰', '00', '', '18311079927', '2', '', '28eb667232ce5ddd1784f96440a6b552', 'e49fcf', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100734, 2001, 'gaolei', '高磊', '00', '', '15652540061', '2', '', '5dc7bdc87bbe907becf0dba61020779d', '51a840', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100735, 2001, 'yangzengsen', '杨增森', '00', '', '18810598782', '2', '', '61b9eb6120dcabdca5af660f691ba3da', 'd30f60', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100736, 2001, 'hexiaoyan', '贺小艳', '00', '', '15340942622', '2', '', '50937c42cc0df543e9a21c065e5d5897', '3e6b1b', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:01', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100737, 2001, 'duchengyue', '杜承樾', '00', '', '18227692219', '2', '', '0968cfce19478e27572c33b3c6d61fea', '9c1170', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100738, 2000, 'xieshu', '谢舒', '00', '', '18518096189', '2', '', '6db3eab57bb8b8f83224a5fdf7e847ca', '02b020', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100739, 2002, 'wushan', '巫山', '00', '', '18611570129', '2', '', '4bd32d9ce15bd169bf89c8f756c588d5', '5abf15', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100740, 2002, 'guoxiaoyang', '郭潇洋', '00', '', '13810649613', '2', '', '239a79977d872d2cf24745e77af9351b', 'f7336c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100741, 2002, 'zhoujianbing', '周建兵', '00', '', '15270803716', '2', '', 'ad546c5cefd439e0989add152ca2e119', '152096', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100742, 1000, 'wangbin1', '王斌', '00', '', '18703429310', '2', '', 'f2f6b2346faea69d37d51cf6a6a9a8dd', 'b5565c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100743, 1004, 'liusishan', '刘思杉', '00', '', '13810783363', '2', '', 'b8f74f892335d49055995a99304143ca', 'd1ae42', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100744, 1005, 'gaojinshan', '高金山', '00', '', '18518407328', '2', '', '04a0529e87d8a553d751605651b58646', '0b52c4', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100745, 1011, 'lixiao', '李萧', '00', '', '13466688315', '2', '', '2438daa85d658e1713f0a77bad77d52b', '130957', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100746, 2001, 'wangnana', '王娜娜', '00', '', '13521672023', '2', '', 'c9370e4088a8670831854fa3df11e147', 'fd4116', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100747, 2001, 'zhongqiuping', '钟秋萍', '00', '', '15101175279', '2', '', '0113a68a7f8c87a6d74a21dab320d6a1', '4c335a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100748, 1003, 'liweize', '李伟泽', '00', '', '17611253820', '2', '', 'a53e14deffd57b0bca8820abd0c33a4f', 'de881a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100749, 1010, 'heqiang', '何强', '00', '', '19943714371', '2', '', '876ea190063d8f70baed1e3f4a9f59a7', '7b4382', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100750, 1004, 'haojinlin', '郝金琳', '00', '', '15712921211', '2', '', 'a1077b44edac30fbcda0e85a65126bb8', 'd55d50', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100751, 1015, 'xingtong', '刑彤', '00', '', '13403690719', '2', '', '60bdb9fdbdcbcef8c9a6151dc952262f', '2b9995', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100752, 1000, 'wuyizhe', '吴祎哲', '00', '', '18810086687', '2', '', '776dd6971dbe7d7a62d63b0c843cfa6c', '38d37f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100753, 1017, 'liweibo', '李伟博', '00', '', '', '2', '', '385ca87aa85664d28dd1953e6914044d', '563dc6', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100754, 1003, 'liyuanyuan', '李元元', '00', '', '13391933306', '2', '', 'f69370e186f6cc39fe4905bad3c66cfb', 'a0bef1', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100755, 1001, 'gebaoqi', '葛保琦', '00', '', '15004671461', '2', '', '999f6140ec15c25e524a3d504569eaa0', 'ea813b', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100756, 2001, 'liuyintao', '刘银涛', '00', '', '13436871179', '2', '', 'ebf2e19137568b487c167be7cc984080', '294479', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100757, 2001, 'tumin', '涂敏', '00', '', '17501101221', '2', '', '9d83e75093bd351d2180aa99b059504a', '6e10b0', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100758, 1002, 'xuyanbo', '许彦博', '00', '', '18201034825', '2', '', '917f063ba2279a2b2b36eaa6252f2190', '340411', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100759, 1002, 'zhangxin', '张鑫', '00', '', '15664646679', '2', '', 'b3f75b8871c5c2a9e8ed771155dfd746', 'f2c156', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100760, 1002, 'zhenghao', '郑浩', '00', '', '18513774351', '2', '', '10491223d417cbf00bcc7b4b0e056105', 'e4bd3b', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100761, 2000, 'gaoyingzhe', '高英哲', '00', '', '17701129299', '2', '', '3898f62bab57d0adca055df61eb63116', '70950d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100762, 1007, 'dupeng', '杜鹏', '00', '', '18611921673', '2', '', 'e3e6c1ef8a531cac74b0829b50e1ee0b', 'ede1d8', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100763, 2000, 'tianhao', '田昊', '00', '', '13910720819', '2', '', '44e4cf469a88032624d5cf352211ee7d', 'a75616', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100764, 1005, 'zhangjinduo', '张金铎', '00', '', '18612707174', '2', '', '8d0c27a3a37b8bc3ed3f1cc6aa9dabdf', 'aa6cab', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100765, 1009, 'zhengwenwen', '郑文文', '00', '', '18911711192', '2', '', 'e78c5dc4a818909f7b2c6304dd4b2018', 'a98d78', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100766, 1022, 'testoa1', 'testoa1', '00', '', '', '2', '', '2042abf711c216e1ad14c35b5fd86295', '4ff117', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:02', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100767, 1022, 'testoa2', 'testoa2', '00', '', '', '2', '', '02375be2aac682f8af7049aa7f5de724', 'eef572', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100768, 1022, 'testoa3', 'testoa3', '00', '', '', '2', '', '93c051d8ba429e1d884014c69254aee6', '00cf20', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100769, 1022, 'testoa4', 'testoa4', '00', '', '', '2', '', 'a324dbfee12720336fdc174951841a13', '8eee19', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100770, 1000, 'liaoqiang', '廖强', '00', '', '17611596308', '2', '', '302d211cf0efadbec163e188b2dd1eaa', '6c5885', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100771, 1003, 'liuken', '刘恳', '00', '', '15869019780', '2', '', '91faa9da21ad149d4b912db1282038b8', '528646', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100772, 1008, 'yangyixuan', '杨一宣', '00', '', '13161112828', '2', '', '98df17d5bdbb47fa0e9a0de3b1d2cf5b', '9356bc', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100773, 2000, 'libin', '李斌', '00', '', '18201639189', '2', '', 'e88a927f09c22154b7442c83d64802bf', '5eb11c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100774, 1000, 'liuxin', '刘鑫', '00', '', '18201208923', '2', '', '6559a35ecc9337a5b832909a85fd08de', '87b420', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100775, 2000, 'chenchunguang', '陈春光', '00', '', '18801468299', '2', '', '967d76d299e1e1bdc0d53ea8fb68023b', '4fabf0', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100776, 1002, 'pengtong', '彭烔', '00', '', '13717709844', '2', '', '5f33d2822cc3311f3f33eafa7f800a3a', 'ca3837', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100777, 1000, 'lichao1', '李超', '00', '', '15910501653', '2', '', 'ca89cbe6c03edb611c621e57eb43a591', 'ec165f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100778, 2000, 'linyue', '林越', '00', '', '15652385324', '2', '', '28f4aa5dca7c4124ba0b206a5737176a', 'dc4322', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100779, 2001, 'likun1', '李坤', '00', '', '13581773451', '2', '', '733d9c2b45753b97e5b7133b9a58dae1', '520de3', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100780, 1015, 'wangjianghui', '王江辉', '00', '', '15930162871', '2', '', '39761b17d0e0cea991644a48f5859e60', '968ee2', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100781, 1005, 'cuibaoxin', '崔宝馨', '00', '', '18601362787', '2', '', 'dc9bdf6aa85d6d58c27bce16326d3b64', '921b39', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100782, 1010, 'luankai', '栾凯', '00', '', '17600381521', '2', '', 'b124cd1f046543056dad431599474ca6', '79daa1', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100783, 1002, 'zhangzhigang', '张志刚,支付创新发展部', '00', '', '18511792346', '2', '', '42abc5bceb4bc89847510fdd255665c1', '3557eb', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100784, 1000, 'zhangqiao', '璋翘', '00', '', '18600471621', '2', '', 'aef8b275731ea3a5b4f1c4803a3c508a', 'dbabe0', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100785, 1017, 'hanyingying', '韩营营', '00', '', '18911356885', '2', '', '430ee51c50a706d11f0cb9705b24c39f', '7d7eb3', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100786, 2000, 'liushifengliushi', '刘诗峰', '00', '', '15810623901', '2', '', '17daad2499ba14ba5d6cba9e379e38fc', 'd807e0', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100787, 2000, 'guanxiaolong', '关晓龙', '00', '', '13261503794', '2', '', 'bb70a0a7824f7510147492b212a041b8', '906805', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100788, 2001, 'chenxin1', '陈鑫', '00', '', '15933983292', '2', '', '1a1f19088e774a8e1effa9acb816f1ab', 'c9abd0', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100789, 1002, 'lichenglong', '李成龙', '00', '', '18811006562', '2', '', 'b94e11a8bf273bda35af6be11ca554b9', '65eddb', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100790, 1002, 'cuiliqun', '崔立群', '00', '', '13141048556', '2', '', 'b7214af4d73d24bf622f4c1571d90032', 'e8e4d3', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100791, 2000, 'niejunlei', '聂俊雷', '00', '', '18510201509', '2', '', 'df0bab7b253cb37de0a31ec4b4a9db32', '397f6f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100792, 1000, 'chenzhiwu', '陈志五', '00', '', '18010410815', '2', '', '6657d4027d2de8fc5b9be8f506e673f8', '0796d7', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100793, 1000, 'wanggang', '王纲', '00', '', '18724733698', '2', '', '2e2ef4a3749e743befe09ae8782135fa', '2f67b7', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100794, 1000, 'lipengyu', '李鹏宇', '00', '', '17801002827', '2', '', '9b543554239036192cc3f24276561532', 'e87483', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100795, 1000, 'wujiayong', '吴佳用', '00', '', '18811134681', '2', '', 'ceda01ce9df3887679359313abbd9fb3', 'a004eb', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100796, 1004, 'zhongjin', '钟瑾', '00', '', '18611582730', '2', '', '9f2891556b17801072e97129c1e79118', 'a729f1', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100797, 2000, 'menglingqi', '孟令琦', '00', '', '13021950066', '2', '', '87952784cd86bc5aa8615ea4db729643', '10842e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100798, 1005, 'yangmingwei', '杨茗威', '00', '', '18810952086', '2', '', 'a99e12aedc0e655a171ef5c7c067d00a', '3048ff', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100799, 1011, 'lianhuanhuan', '连欢欢', '00', '', '18810952086', '2', '', '3e6579a3ba52d6282715f049132e5a4a', 'e15c50', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100800, 1009, 'limoxiong', '李漠雄', '00', '', '15210850150', '2', '', 'd65b4fa8694c63374993808d0127162c', '4de7b7', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100801, 1009, 'sulei', '苏磊', '00', '', '17600296283', '2', '', '9883ed0b5dff1a6ec9dc5b3f216d2ea7', '785dfd', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100802, 1009, 'liuqing1', '柳青', '00', '', '15810464951', '2', '', '84ead5033c07458baecdceef45602952', '4e209e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100803, 1009, 'mayuchi', '马宇驰', '00', '', '18334534388', '2', '', 'c3dc709b56037cf62f54a63b396b51ac', 'af23ca', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100804, 1009, 'mengziyan', '孟子焱', '00', '', '18911705436', '2', '', '95418f842846c1647a1af466a1372e95', '7a88d1', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100805, 2002, 'sunhangda', '孙航达', '00', '', '15801194017', '2', '', '889b7a6d9c5f501f771a1d794625f205', '3b10a5', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100806, 1005, 'wangman', '王蔓', '00', '', '13269297531', '2', '', '5c53cbf8c884d817db1f379a0bfd7e8f', '5eb3dc', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100807, 1000, 'zhaoyuchong', '赵宇翀', '00', '', '13264181224', '2', '', 'c384da2cdc84dfa7a01fb52dce151883', '84b206', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100808, 2004, 'lirouyi', '李柔熠', '00', '', '15221923523', '2', '', 'ff3fea51ab0541cb0cd43838d5e9a9fb', '16d0c6', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100809, 1002, 'zhangjingye', '张敬业', '00', '', '13466694252', '2', '', 'ee74b16f4fa43c1b4b8f2dbf311b68e1', '73c712', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100810, 1009, 'wangxiulong', '王秀龙', '00', '', '18710161908', '2', '', '7fea8edcfd265afa4430cd595b14d049', 'df7b33', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100811, 1009, 'huajie', '华杰', '00', '', '15801464756', '2', '', 'db2b37b15106296d57562a1f7dff7fca', 'a94276', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100812, 2002, 'zhangxi', '张希', '00', '', '13810828996', '2', '', 'e2f4e0048f9413f48f3f2f673d8cbf2c', 'f155ac', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100813, 1004, 'tangxi', '唐曦', '00', '', '18600188984', '2', '', 'e39506b80341487dc3044c3b65bf1419', 'ace151', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100814, 1000, 'wangyu', '王宇', '00', '', '18501055456', '2', '', 'd5ac18a3a1260dd2a5d44e283d77a562', 'a93525', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100815, 1003, 'tianlei', '田雷', '00', '', '18611975619', '2', '', '1270af7dedbc6ad00c20937a5aa57171', '0b6119', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100816, 1004, 'gongmengmin', '龚梦旻', '00', '', '18311129322', '2', '', '19991b26d468f7150a74a48757d0e16f', '12240f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100817, 1000, 'mengxu', '孟旭', '00', '', '18600947334', '2', '', 'bb32d649940dc43825c4d7693ff829e6', '3a7bb9', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:04', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100818, 1003, 'fanyusong', '樊雨送', '00', '', '18506834638', '2', '', 'ec4937cc7a74191f62cefb07b0e091c0', 'ff752b', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100819, 1005, 'weizheng', '魏征', '00', '', '17610776202', '2', '', '9fd4d504884c4df7493c6c1c67635cea', '060534', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100820, 1005, 'hejun', '何军', '00', '', '13581605834', '2', '', '80c91914ab0567c81ba3291d510b1c7c', '888afc', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100821, 1005, 'wangyi', '王祎', '00', '', '13146979968', '2', '', '8bf9f6a5c5f426845555c7abe174b926', 'bfb24f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100822, 1005, 'zhangchenhui', '张晨晖', '00', '', '15801265819', '2', '', '91abce21ae5cea7909ec84030a688648', 'a9218a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100823, 1005, 'luguangrui', '逯广瑞', '00', '', '15311868325', '2', '', 'e15b3a4c6d352223d30a329d60850ed3', '359c9d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100824, 2000, 'wuyuxin', '吴雨新', '00', '', '15510670090', '2', '', 'a4137b4e5652981e56ce74d99e342685', '9dbc78', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100825, 1002, 'sunyunhui', 'sunyunhui', '00', '', '', '2', '', '405e8328e1ce6256ccee418e4d4e71a7', 'c5c171', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100826, 1011, 'guolian', '郭莲', '00', '', '18602694310', '2', '', '9ef9ecb4c7277709c78dfb3a2d22b420', 'c3434a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100827, 1019, 'fengyaxin_intern', 'f冯雅欣', '00', '', '', '2', '', 'ab4a6e034899be25c7662a5614a151ee', '611978', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100828, 1002, 'yinjiandong', '尹建东', '00', '', '15210538631', '2', '', '245d07773b74905dfe8c0315cecbb6df', 'd72c41', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100829, 1002, 'liuchuanxian', '刘传宪', '00', '', '18561489582', '2', '', '9153ecd800f09da0eaddc77eae560eef', '7b206f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100830, 1002, 'wangyumei', '王玉梅', '00', '', '18801043326', '2', '', '1ef912ce451757bf57985f01c41b60d1', 'f5c63b', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100831, 1005, 'jiahua', '贾华', '00', '', '17610775965', '2', '', '9e5cc59e842d10b3711864652a5bf31d', '265933', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100832, 1000, 'zenghao1', '曾皓', '00', '', '18611762462', '2', '', 'c5bc9a924f1cdc2a4847a0f581de5ccb', '43c6df', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100833, 1003, 'zhaoyongli', '赵勇立', '00', '', '15101117812', '2', '', '3023274bb6b9253e592517d0b30c2315', 'bbc0c1', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100834, 2000, 'zhangxinwen', '张馨文', '00', '', '18513031022', '2', '', '13619ded5e516a91272a02e26c79bf05', 'ded943', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100835, 1017, 'liujunyi', '刘君一', '00', '', '18701644688', '2', '', '4a15dd27ce1fd7f6f590e75ad4d4f4f3', 'a02265', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100836, 1011, 'wanyaquan', '万雅泉', '00', '', '15288289653', '2', '', '3f55fb597f377d8c9bb851aeffcd7354', '62d2b8', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100837, 1009, 'kongyunsheng', '孔云生', '00', '', '15124558566', '2', '', 'b9fe924a7e5ec7f1621afd3d1d6ae433', '4caf48', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100838, 1000, 'cuizhichao', '崔志超', '00', '', '18510489620', '2', '', 'c83d387f7c86ee584609e53164b97643', 'b00872', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100839, 1000, 'wangshixuan', '王仕轩', '00', '', '13810414305', '2', '', '23b5905b09ba9992cc94b0b0823ef15a', '05e055', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100840, 1009, 'xuguangtao', '许光涛', '00', '', '15801129961', '2', '', '74fc8cf58ac59f9b88c350beaed1886b', '982c93', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100841, 1007, 'hanhan', '韩瀚', '00', '', '13759880591', '2', '', '16a56e453de00e4faec30db8aed32834', 'a6299c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100842, 2000, 'xumingmin', '徐明民', '00', '', '13036147081', '2', '', '998623fcdf129e2d70653ac35490b7a4', '60e389', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100843, 2001, 'hehao', '贺浩', '00', '', '18301683800', '2', '', 'fa148fd0efab3ce2d1d042053e1eeba5', 'fb4ab2', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100844, 1002, 'gaomingyang', '高铭阳', '00', '', '13581878963', '2', '', '61f521d54fcd1c608d72cd17ab370ec5', '786363', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100845, 1010, 'lihaotian', '李昊天', '00', '', '17888821945', '2', '', 'c0406edd910a89143f4aa561d06df46d', '91af5f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100846, 1005, 'liudongyang', '刘东洋', '00', '', '13941353681', '2', '', '675904962b89b3532378abb4aedc1dc8', '27f436', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100847, 1002, 'mayu', '马郁', '00', '', '13910782879', '2', '', '0b919e6eade227808f255a9333b23498', '11bb7a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:05', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100848, 1002, 'liyijia', '李怡佳', '00', '', '17600327368', '2', '', '1198abcd812f3433634b6adc26c89db9', '01dd3f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100849, 1000, 'peiyu', '裴钰', '00', '', '13381151920', '2', '', '8224e45e5061b1cdbf45fe142bca448b', 'fc957f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100850, 1001, 'xuguangli', '徐广利', '00', '', '18510583169', '2', '', '2d91af5afe1e2850ff4f279054387c53', 'c6e84e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100851, 1007, 'baokaixuan', '鲍凯旋', '00', '', '18501992830', '2', '', 'b0b3e3efd0b83e6f015ff28946b6ab73', '6c905d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100852, 1009, 'yucong', '于璁', '00', '', '18811595498', '2', '', '3daaa75d8fc854677cdbf3a3592d6b05', 'c247d1', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100853, 1005, 'zhangshu', '张舒', '00', '', '18612928395', '2', '', '952ce238678fbbd3ca40564dbfed910a', '5e441d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100854, 1005, 'sanghongshuai', '桑鸿帅', '00', '', '13998592262', '2', '', '5f4d6d8f4e8756af3d865185031636cc', 'f64f3e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100855, 1000, 'lidong1', '李冬', '00', '', '18647659636', '2', '', '02e58eb21d677582791df591bbf425fa', '3b7174', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100856, 1000, 'dangyoukang', '党有康', '00', '', '15810630419', '2', '', 'e241a094c05949cbd2225a2eee94fa25', '9f5e2b', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100857, 1000, 'danweidong', '淡伟栋', '00', '', '15691740209', '2', '', 'ad1023c58e69bb973504fb0f862ecd1c', '2cea1c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100858, 2001, 'zhangyufei', '张雨飞', '00', '', '15966877290', '2', '', '4e32013d1458536adcce103bb38cd55e', '7aa3e9', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100859, 1009, 'yangchong', '杨冲', '00', '', '17671467027', '2', '', '310e2e249e1903cbe0220620e6010425', '43a1d6', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100860, 1009, 'libaoxin', '李宝新', '00', '', '15311580542', '2', '', 'ab5979d713084287a49fbea3d2c21e9c', 'e48fe1', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100861, 1010, 'huangxiaotong', '黄晓彤', '00', '', '15810677128', '2', '', '73f27ddbe15197b7442e91f30871bf1f', '2a0cd5', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100862, 1000, 'xushaopeng', '许少鹏', '00', '', '13126700278', '2', '', '807ddf3ab647cf9ae7c96402b44378cf', 'af5a17', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100863, 1000, 'linan', '李楠', '00', '', '15822525085', '2', '', 'ab217d2907ce62ab4f10c240e9ba2b6a', '34ff89', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100864, 1002, 'xiaoyujie', '肖玉洁', '00', '', '13552670936', '2', '', '41b97d1a06e2659a790b5bc28e096fbe', 'd251eb', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100865, 1013, 'yangdezhi', '杨德智', '00', '', '18545869616', '2', '', 'f2caeb9bc3c646fc00974da6b7fb4185', '1d6b09', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100866, 2002, 'liumingcan', '刘明璨', '00', '', '15652919445', '2', '', 'a5d545145e91778c098e61bcc27b5827', '72ac67', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100867, 1005, 'yangwen', '杨雯', '00', '', '15910632210', '2', '', 'ef02f65838f5e9ac310bfe0816dc851f', 'f0f3e7', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100868, 2001, 'wangziqian', '王梓谦', '00', '', '13522548777', '2', '', '67e5f25741f5f26ac136ea388800b0f6', '10c4a3', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100869, 2000, 'caowenli', '曹文利', '00', '', '18611688579', '2', '', '12247efc0eeef4ccb7c999f48ac93085', 'e3bffd', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100870, 2001, 'songzhixin', '宋志新', '00', '', '18811326142', '2', '', '0626c90f5e1da257afcfa9576dd1011f', 'b8f09a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100871, 2000, 'wangshibo', '王士博', '00', '', '13611072179', '2', '', '3ece81b120b7b773b1aaacea6e103659', '8d5250', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100872, 1009, 'sunliangxing', '孙亮星', '00', '', '13701334231', '2', '', '5d99b6d17ee63df8c6b9a789784c90a4', '5d2b88', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100873, 1002, 'zhangchunhao', '张纯浩', '00', '', '18701299891', '2', '', '916d20e92b3650ab7c898503c9abbaa0', 'cdff8f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100874, 1000, 'zhangguangmu', '张广牧', '00', '', '18600572547', '2', '', '9a5cb3ff386ebb112a9933a6c84b0aee', 'ec0b49', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100875, 1000, 'weizhenlong', '魏振龙', '00', '', '13810929360', '2', '', '9953f7875d72cec179932dde5814f079', 'e886ce', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:06', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100876, 1007, 'duanding', '段定', '00', '', '13321167877', '2', '', '2a43dbe651fa11791810ce73ed814a22', 'c6e738', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100877, 1010, 'yangpeng', '杨鹏', '00', '', '13811208495', '2', '', '4a1ed1907bb9beef2acdea0266e24d5f', '82ad97', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100878, 1002, 'liuzhenfeng', '刘振丰', '00', '', '13315607009', '2', '', '3bac51056ee226789d094b1258412d16', 'a99947', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100879, 2001, 'lianglijie', '梁丽洁', '00', '', '18810102647', '2', '', '52c5e09ac411617860ebd30ad095c6e8', '63d17c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100880, 1005, 'chentianhua', '陈天华', '00', '', '18612205591', '2', '', 'e92498bbdef97380e5379a41c0977eb4', '0f5c0c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100881, 2001, 'zhoujiangbo', '周江博', '00', '', '18792802156', '2', '', 'dc1329e321249143add29de89382b23f', '9b3c24', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100882, 2001, 'xuyanan', '徐亚楠', '00', '', '13717829612', '2', '', '0874cbdc67159dcdaffd9702995d5734', '0ffdb4', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100883, 2001, 'zhuxiaoguang', '朱晓光', '00', '', '18601921709', '2', '', '865ce17e9b605416f8d709389ae409a6', '273471', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100884, 2001, 'wangqi', '王琪', '00', '', '15001050853', '2', '', '9368c8ca60f3400e7eb430773088ba7f', '8297af', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100885, 2000, 'zhangjun', '张军', '00', '', '', '2', '', 'ada5e9824fdaa8f35b56e6d92ea32120', 'a0844d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100886, 2000, 'zhangzhichao', '张志超', '00', '', '15116968085', '2', '', 'a98682f825fa7b3d0ceff5d5cf44ff21', '9ff4be', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100887, 1002, 'liuchunyan', '刘春彦', '00', '', '15001329387', '2', '', 'c696e8ae8032c4cbc4b450bf07c7dde4', 'ebc79e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100888, 1002, 'zhaixiaofei', '翟小飞', '00', '', '15801589603', '2', '', '069ba01ad64e4081a707f8a58ee6d768', '81af5d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100889, 1008, 'jiaoyang', '焦阳', '00', '', '13683607994', '2', '', 'e56fdbd0ee431d4f5d63cb4febde11f3', '8d2b52', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100890, 1005, 'hurenjie', '胡人杰', '00', '', '18516514628', '2', '', '7e1b6dc4d854e5c35c79b2cf9223ee3c', '3f260a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100891, 1005, 'zhangwenjing', '张文静', '00', '', '18600311312', '2', '', 'c57a8148ab7d60b9b3f71eb9bca3ad24', '0c3597', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100892, 1005, 'wangnan', '王楠', '00', '', '18810900547', '2', '', 'cc718423e9e0d8f20bc8632abe1d8e8e', 'ca7e45', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100893, 1000, 'wangyi1', '王毅', '00', '', '18600368562', '2', '', 'be6eac2681802e27df92fa17c6f6bd1d', '57d5ef', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100894, 1013, 'zhengwei', '政伟', '00', '', '15810309764', '2', '', '7fde3fe326e0c8e4a516498a2ae1a679', '66e982', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100895, 1013, 'shenxufei', '沈旭飞', '00', '', '13261180067', '2', '', '16c426f109ec2239b823151f817625d9', '9136c0', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100896, 1005, 'suyueyue', '苏玥玥', '00', '', '15210352298', '2', '', 'ce66a02a2443e438a0472aba4277c8ff', 'd7d42e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100897, 1005, 'diaobisong', '刁必颂', '00', '', '15210352298', '2', '', 'e01f1653ca49b9a5cc9006b042984dc1', '8eb7d4', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100898, 1005, 'sunlizhe', '孙立喆', '00', '', '18910187570', '2', '', 'ccbbb3ab51157f185134f32c72855c5e', '8cdf41', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100899, 1000, 'wangtianjia', '王天家', '00', '', '15510548877', '2', '', '35e59d3e718a9fad2fe0ec095d3f50a4', '0714e4', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100900, 1007, 'zhanglong', '张龙', '00', '', '18311038626', '2', '', '6ae4de75b0275353da1fe59ae52dba6f', '64addc', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100901, 1013, 'wangyesong', '王晔嵩', '00', '', '18945642129', '2', '', 'e359b22cfd14512866380c5da61f4852', '5eb7c6', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100902, 2001, 'leiyao', '雷遥', '00', '', '15110210093', '2', '', '671801fa46818a30bb1ebd3b854a793c', '5ed84d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100903, 2000, 'xurui', '徐瑞', '00', '', '13716874578', '2', '', '4b895432d83c6f7dbd6204fe84d4f547', 'f73e26', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100905, 1002, 'jinzixi', '金子熙', '00', '', '15910393932', '2', '', '95ef9147050599f004aea620d9e096f0', 'e16b7a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100906, 1002, 'gaojie', '高洁', '00', '', '18600120073', '2', '', 'a14b36558967c62460c3ac1f157e8798', '7136d1', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100907, 1003, 'tangyuchao', '唐裕超', '00', '', '18201083690', '2', '', '66ecf8a222bc22f080ae92bbae16a47b', 'df8d94', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:07', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100908, 1003, 'wangbo1', '王波', '00', '', '15830639885', '2', '', 'c06fbd10d1b633388fb9b1f4a994fa63', 'bb16d6', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100909, 1011, 'muyang', '穆杨', '00', '', '18611005078', '2', '', '9c8bd8e7f564d8c56dd090792166f289', '70f1d8', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100910, 1011, 'suchenjia', '苏晨佳', '00', '', '18551209773', '2', '', '04201628f3580533e1c720f81a53bc68', '4f7ce3', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100911, 1008, 'chenying', '陈盈', '00', '', '', '2', '', '0f7d18eb26e89a1781bfe207575e4ec5', 'f5fa32', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100912, 1000, 'huangyawei', '黄雅威', '00', '', '15600705526', '2', '', 'd62c4660eb738f813f35a9152c25d1fc', '03fab3', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100913, 1000, 'huayawei', 'huayawei', '00', '', '', '2', '', 'e72a4acad7469311e0daf0eaa55d83ba', 'd69185', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100914, 2002, 'luoyazhou', '罗亚州', '00', '', '18610891215', '2', '', '2df100a14b23b602c9bf8fcbb58bba2d', 'ecadc8', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100915, 1003, 'zhaoyanpeng', '赵砚棚', '00', '', '18810058534', '2', '', '4c6ac8ce8eb62cbbf830eec8b824b4e6', '30566c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100916, 2001, 'mengxinghua', '孟兴华', '00', '', '13811281482', '2', '', '2badf5d03fd2eae7b9dc7fabeeef0ace', '9d1e6d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100917, 2001, 'lilongyu', '李龙雨', '00', '', '15201162664', '2', '', '40ba37c37bcede095467bd0812b5e0c6', 'c86f7e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100918, 1002, 'chenshipeng', '陈仕朋', '00', '', '18001253512', '2', '', 'd7e6c37fc0f9ad57157007b27892c026', 'db951f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100919, 1000, 'zhaozhenfang', '赵振方', '00', '', '17611375539', '2', '', 'a8e5b7fd4cd409d7af8464e396c6757f', '413079', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100920, 1005, 'wangshuai', '王帅', '00', '', '18514001332', '2', '', '44413c50c06d2641390df718a9f68fb4', 'e39b03', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100921, 1009, 'ganzeyu', '甘泽郁', '00', '', '13051979927', '2', '', '221498972c1bf38610cca7746b5a2506', '7c6a78', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100922, 1002, 'yangyuxi', '杨宇晰', '00', '', '13289812057', '2', '', 'b194350e9e65cae3815b5a4c0f36bb1c', '1f1d7e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100923, 1003, 'chaixiao', '柴萧', '00', '', '15731217221', '2', '', '5e46344f0ecde958ed4b9a7aa2feaf81', 'f181bd', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100924, 1003, 'yuanxuan', '袁轩', '00', '', '18501289886', '2', '', '30ad2c927cd6c57bd674467a5e36f712', 'dc7cb1', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100925, 1003, 'lixue', '李雪', '00', '', '13426005589', '2', '', '27268bf937b910ebb6569b0c23f4f2fd', '26d5eb', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100926, 1003, 'zhaolemeng', '朝勒蒙', '00', '', '13651081590', '2', '', '26f3ce8d9037b595e296fb2e4968af04', 'c2b747', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100927, 2000, 'liuming', '刘明', '00', '', '18611184310', '2', '', 'a7c9193523240d5d30dcccb55ff404b2', '9256c6', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100928, 1000, 'liuhongyang', '刘弘扬', '00', '', '13674267026', '2', '', 'a91370fdeec5ef8f395b9cd264ae7a09', 'a42d14', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100929, 1000, 'zhangzhe', '张哲', '00', '', '16619805290', '2', '', '73b9df8ea189e9c308a836918486fac9', '56cc04', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100930, 1010, 'gouyuanchen', '勾元辰', '00', '', '15801632378', '2', '', '6fdc375fd31c6f16314747d638671ab6', '70bf70', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100931, 1002, 'dongxudong', '董旭栋', '00', '', '18737575800', '2', '', '33614af75e83a2609a207705f300e0f2', '6030b0', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100932, 1000, 'pangtianyu', '庞天禹', '00', '', '13911761780', '2', '', '861119161086d310e1bbfbade4d6f24b', 'd3b629', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100933, 1000, 'liuchang', '刘畅', '00', '', '13552486648', '2', '', '0953a05261b5a2fef482a226f568b698', 'a987ee', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100934, 1002, 'zhangchu1', '张楚', '00', '', '18810623283', '2', '', '3150c5eed6a28d36d059b7c1504ae6e5', '0bce85', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100935, 2001, 'gaohui', '高辉', '00', '', '15829041778', '2', '', 'dee5440f6fb04c4c59c7de637acaa16a', '5db21d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100936, 1002, 'xuyijian', '徐一健', '00', '', '18910067121', '2', '', '3ef85c9de70f235a319c528de8ea1c49', 'a502e4', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100937, 2001, 'duanshao', '段劭', '00', '', '13381158998', '2', '', 'a95a9cdb26feb17b71bb040e7b41f110', 'a1ebae', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:08', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100938, 2000, 'liguang', '李光', '00', '', '15010800314', '2', '', '0e9ebab03a3df3e635c3abdc3cdf7256', '6d96e5', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100939, 1003, 'yaoping', '姚萍', '00', '', '18910521415', '2', '', 'ac5f2397902d6dc8b6e7b94e1df1a6ff', '08d6e5', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100940, 1003, 'tongjie', '童杰', '00', '', '13120162132', '2', '', '5aaa03a8e4384636d513055a35ffc0f7', 'a52350', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100941, 1009, 'wangjinbao', '王金宝', '00', '', '18210596067', '2', '', 'f38f1ab24a0d111b22138590dd157441', '2d5b5f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100942, 1009, 'zhangming', '张明', '00', '', '13240812460', '2', '', '9fd626ba7e1b21f1fd6fcfdf2ecfaa90', 'ae8146', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100943, 2001, 'yuanguoqiang', '袁国强', '00', '', '13205691083', '2', '', 'f64afcfbd078a6a8a8e7f015811ca42d', '1dad2f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100944, 1003, 'panyingjie', '潘英杰', '00', '', '18232150160', '2', '', 'f56e2ef7b910465ec2a25d89b94ff670', '2ea914', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100945, 1005, 'liliren', '李力仁', '00', '', '13518102675', '2', '', '917db19e65ab29879ba99432760a1bc9', 'f07023', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100946, 1005, 'hushenmin', '胡申民', '00', '', '18611234869', '2', '', 'c28672463b3ea91816529d06bec55833', 'b31f68', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100947, 1005, 'chenjianfeng', '陈建锋', '00', '', '15210875902', '2', '', '2d2f2300b00bbbd1ac06c951541350ba', '84f2ac', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100948, 1002, 'liangxuerong', '梁雪融', '00', '', '13627118211', '2', '', '6daa3d0bdfdcf4e535ceb64851b5c311', '16e847', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100949, 1001, 'fanhehui', '范鹤慧', '00', '', '13718431897', '2', '', '4886bca610d95efabae837d5d6f982a7', 'b178b6', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100950, 1000, 'baining', '白宁', '00', '', '13919903783', '2', '', '84c1f86fdc3b73b7cf722d319727c0e2', '657e42', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100951, 1000, 'fengyaxin', '冯雅欣', '00', '', '13141290100', '2', '', '25d273ccde96b86019d8fd5a2811481b', 'abaf0b', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100952, 2002, 'zhaowenping', '赵文萍', '00', '', '13012258013', '2', '', '0329876fdf7c0cd38268551dff657bd7', '6882ad', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100953, 1000, 'xudonghui', '徐东晖', '00', '', '13502075256', '2', '', '8e5652b732ad41772106257f032d49b7', '8626e1', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100954, 1005, 'sunjingwen', '孙静文', '00', '', '18813168273', '2', '', '48e27c935ba3d7eec426d72e268ce18c', '4d2cad', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100955, 1010, 'limeini', '李美妮', '00', '', '15708427336', '2', '', '8584758b8c41abb2edf1e7ee527ba563', 'e459e5', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100956, 2001, 'zhangdong', '张冬', '00', '', '18305186369', '2', '', 'bf83747e333b0ca26216ce0df2520a9e', '0c778f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100957, 1009, 'wangchao1', '王超', '00', '', '18101035321', '2', '', 'a8763a9b4303be30944a3f57ef53d9d4', 'd977c5', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100958, 1000, 'guojinxin', '郭金鑫', '00', '', '18811718449', '2', '', 'bed4cd6c810d19a67f658a294c72cca8', '139e20', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100959, 1001, 'sunfei', '孙飞', '00', '', '18511538582', '2', '', 'c9d7fc170661b1a256a925ba3fb2a086', '2b39c4', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100960, 1002, 'madepeng', '马德棚', '00', '', '18813095841', '2', '', 'be652ac6fcabb2e090eb7a370d9b9c3e', 'ed6cb5', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100961, 1015, 'huxiaolin', '胡晓琳', '00', '', '15811528085', '2', '', '711cee5f0ac2b41216ece644f915a464', '4f6f77', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100962, 1002, 'liyongshun', '李永顺', '00', '', '13910814619', '2', '', '0e0e784bbd7f5772f070b8c981a25c19', 'fd57fc', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100963, 1000, 'zhangqiqi', '张琦琦', '00', '', '18800139633', '2', '', 'dd4a4177b7902722c214546f8551ae47', '35aa90', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100964, 1000, 'tangxufeng', '唐旭峰', '00', '', '18800115542', '2', '', '31513d3e96bdb1aa192286e32fde4a1e', 'e84b78', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100965, 2002, 'wangruru', '王茹茹', '00', '', '18810628189', '2', '', '4dda3ee0ec831566fdef2fe016d7ecef', '801904', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100966, 1003, 'wangyiyi', '王依依', '00', '', '18810027523', '2', '', '15f7f803f62bc413b651a54cc3eda9d4', '8ea56a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100967, 2002, 'weichen', '魏晨', '00', '', '18511301012', '2', '', '3b9e94c7b45ad7206d91178f662d220c', 'c233a4', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100968, 1003, 'maolulu', '毛路路', '00', '', '13466637405', '2', '', '22a3a904125af0715dbfd9727634c7e6', '3c7c75', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:09', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100969, 1000, 'lizhiwei', '李智伟', '00', '', '15834187781', '2', '', '0b15b5ed25db20cd158945bcd62faed8', 'b7e3b5', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100970, 1005, 'lijia1', '李佳', '00', '', '18811571276', '2', '', '79b1cc56f859f494cdda13ecd46ee14b', '5ab76c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100971, 1002, 'hubo', '胡博', '00', '', '18642085460', '2', '', '2d931dc564099227b7446c19d2ca0208', '442c8f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100972, 1009, 'zhouxianlong', '周先龙', '00', '', '13522803750', '2', '', 'fe816fe42fda2d7760a87bf5215a8183', '1136dd', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100973, 1000, 'qinghongbin', '卿红斌', '00', '', '18210241371', '2', '', '1046306a2799b5d09e365265e23c4bc6', 'acb125', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100974, 1004, 'liujunbang', '刘峻榜', '00', '', '15811273851', '2', '', '53140ffa833b2ddb0981aff8b110e7a8', 'bc5ba3', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100975, 1000, 'yangminghui', '杨明晖', '00', '', '17826029098', '2', '', '132bd5fd252f16edab87ad753f55526c', '0dc76a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100976, 2000, 'liuxin1', '刘鑫', '00', '', '13261919600', '2', '', 'f527fcef982e40a9e40429ed48b649c0', 'e427e6', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100977, 1000, 'shiyan', '石砚', '00', '', '15091772933', '2', '', '935b776ff86bd500dae91158122aae4d', '809a48', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100978, 1003, 'wangxiaochen', '王晓晨', '00', '', '15910773482', '2', '', '20127603c0ad444ab218e60c1d9af524', '05e102', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100979, 1000, 'fengzeqi', '丰泽琪', '00', '', '17611603232', '2', '', 'b77c909caf74a6bb13497c8a1137bc02', '886386', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100980, 1002, 'linxiaodong', '林晓东', '00', '', '18501221388', '2', '', '2bb0b9b2ce9d329040c7f3aa4e5058da', '5d0127', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100981, 1000, 'zhaowencheng', '赵文诚', '00', '', '13772243283', '2', '', '7e9d59f61eb1ba2f4ae376d1a286793f', '1349bc', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100982, 1002, 'xiejinxin', '谢金鑫', '00', '', '18810818661', '2', '', 'cbb3d76c4be411f3ff5dcab2b28d6d82', '33203a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100983, 1005, 'zhangruoyang', '张若杨', '00', '', '13552571232', '2', '', 'd2469c7d982157042507e34216770534', 'fbdf8a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100984, 1009, 'liangxiao', '梁潇', '00', '', '15011032543', '2', '', '8d982788700935f49b2771a571e22e5f', '334857', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100985, 1010, 'caiyutong', '蔡煜桐', '00', '', '15537822313', '2', '', 'bc5b430736fffcd130f7c8b172116f36', '6c894f', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100986, 1000, 'xieyiyi', '谢依依', '00', '', '13247179061', '2', '', '885fc44754ceab12983ce08eb84a2c43', 'a9f9ce', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100987, 2001, 'wubei', '吴蓓', '00', '', '15802983092', '2', '', '046fde9ca79e4f68bd4c301ceac77ca3', '6f755c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100988, 2001, 'wangxu', '王旭', '00', '', '15009181886', '2', '', '6404e2bf67f49d0d3f71eded5dac868b', 'a1bf6d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100989, 2001, 'zhaofanghua', '赵芳华', '00', '', '18366115203', '2', '', '6bbe1dccdcc65bfb469590a300982de6', '674ccf', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100990, 1009, 'wangyuling', '王宇玲', '00', '', '18500198763', '2', '', '589156d7d54172d0a41c61dd1f9e58c3', '45548c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100991, 1011, 'qiaoyu', '乔瑜', '00', '', '18101217705', '2', '', 'b34206340fdf916a31a89c3c42e595ac', '7cf698', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100992, 2001, 'liujunjun', '刘俊君', '00', '', '18810825260', '2', '', '50ad93fa41a95f29ad72d82690baede9', 'c43b5c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100993, 1000, 'huxingwei', '胡星炜', '00', '', '13141469044', '2', '', 'd6715836d5a3e34bc0eb9dfd3c2fbf51', '3f82ef', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100994, 1003, 'chenbing', '陈冰', '00', '', '18610187801', '2', '', '69a29f75fc48b8a87f56f3e50b594b2c', 'fde98e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100995, 1000, 'lijinan', '李金安', '00', '', '18804678888', '2', '', '33dbe84b0dd54e77b6334cb7e3fc712a', 'b713a8', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100996, 1009, 'zhangzhibo', '张志波', '00', '', '15811569306', '2', '', 'e425a12834940d7591b0fa5ade5b1d41', 'f8d4e2', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100997, 1005, 'yuhongkai', '喻宏凯', '00', '', '18231088235', '2', '', 'ca21636c961702b9d0e046992583f566', 'a30f6d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100998, 1000, 'zhangchao', '张超', '00', '', '18811727135', '2', '', '90c3588c186b2c8965249bf925216480', '15453e', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (100999, 1000, 'huli', '胡立', '00', '', '13810489912', '2', '', 'e88dfa957d4c92f6e073b199886ed698', '01f022', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (101000, 2001, 'suwen', '苏文', '00', '', '18618100971', '2', '', 'edafbffaa22a8cfcb901112755355189', '4d3e12', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (101001, 1010, 'lixin', '李欣', '00', '', '18030751002', '2', '', 'a425db2f541ab9c1f8d690883bf0edae', 'd8f37b', '0', '0', '127.0.0.1', '2019-08-19 16:35:23', 'admin', '2019-08-19 14:12:10', '', '2019-08-19 16:35:34', NULL);
INSERT INTO `sys_user` VALUES (101002, 1002, 'liubenwen', '刘本文', '00', '', '13325130063', '2', '', '289716dcb407aa2de1c2c1fd788fcd5b', 'cb4e8d', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:10', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (101003, 1009, 'liufeida', '刘飞达', '00', '', '18601273280', '2', '', '851623ee763369e0f602573e1a6e3fe3', 'ab0050', '0', '0', '127.0.0.1', '2019-08-19 19:48:13', 'admin', '2019-08-19 14:12:11', '', '2019-08-19 19:48:13', NULL);
INSERT INTO `sys_user` VALUES (101004, 1000, 'chenrongquan', '陈荣铨', '00', '', '15510623637', '2', '', '9292622b5b92314c01653f64807834bf', 'f6e1be', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:11', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (101005, 1003, 'liuhuan', '刘欢', '00', '', '15901100690', '2', '', '22411a2c46267a4a1e2afe03c4267171', '6b26d9', '0', '0', '127.0.0.1', '2019-08-20 09:52:31', 'admin', '2019-08-19 14:12:11', '', '2019-08-20 09:52:31', NULL);
INSERT INTO `sys_user` VALUES (101006, 1003, 'liuyuxin', '刘宇昕财富管理事业部', '00', '', '17701095402', '2', '', '2511586870fc757c3f8e97fe57da7dd8', 'e43166', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:11', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (101007, 2002, 'zhaoshuang1', '赵爽', '00', '', '18611757470', '2', '', 'a32ab00bc21a2f92e119dcaf05f728e3', 'e6826a', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:11', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (101008, 1013, 'zhangzhiqiang', '张志强', '00', '', '13120030011', '2', '', '83859d37a2c438aad2217f30874622f8', '2c2914', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:11', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (101009, 1007, 'mengfanyu', '孟繁宇', '00', '', '18515003577', '2', '', 'f5ffbad4e0e7f8168b078ec1c778dadf', 'b270eb', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:11', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (101010, 1005, 'yangrui', '杨瑞', '00', '', '18515325363', '2', '', '348b04205d54b07e011166c1c1f066c1', '80bb33', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:11', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (101011, 1000, 'gongshuai', '公帅', '00', '', '13811865652', '2', '', 'd7c587adf54f10f40ccddd2640166ba4', '91a18f', '0', '0', '127.0.0.1', '2019-08-19 15:23:41', 'admin', '2019-08-19 14:12:11', '', '2019-08-19 15:23:40', NULL);
INSERT INTO `sys_user` VALUES (101012, 1004, 'tiancheng', '田铖', '00', '', '17310151601', '2', '', '5275cd59c6554cfe2f2ffe681f4be8ce', 'c0878c', '0', '0', '', NULL, 'admin', '2019-08-19 14:12:11', '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept` (
  `USER_ID` bigint(20) NOT NULL,
  `DEPT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`USER_ID`,`DEPT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和部门关联表';

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线用户记录';

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_online` VALUES ('50c5c468-c02c-44b3-ae54-ee9d97b7c283', 'admin', NULL, '127.0.0.1', '内网IP', 'Chrome', 'Mac OS X', 'on_line', '2019-09-02 09:33:21', '2019-09-02 09:34:39', 1800000);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (100000, 2);
INSERT INTO `sys_user_role` VALUES (100001, 2);
INSERT INTO `sys_user_role` VALUES (100002, 2);
INSERT INTO `sys_user_role` VALUES (100003, 2);
INSERT INTO `sys_user_role` VALUES (100004, 2);
INSERT INTO `sys_user_role` VALUES (100005, 2);
INSERT INTO `sys_user_role` VALUES (100006, 2);
INSERT INTO `sys_user_role` VALUES (100007, 2);
INSERT INTO `sys_user_role` VALUES (100008, 2);
INSERT INTO `sys_user_role` VALUES (100009, 2);
INSERT INTO `sys_user_role` VALUES (100010, 2);
INSERT INTO `sys_user_role` VALUES (100011, 2);
INSERT INTO `sys_user_role` VALUES (100012, 2);
INSERT INTO `sys_user_role` VALUES (100013, 2);
INSERT INTO `sys_user_role` VALUES (100014, 2);
INSERT INTO `sys_user_role` VALUES (100015, 2);
INSERT INTO `sys_user_role` VALUES (100016, 2);
INSERT INTO `sys_user_role` VALUES (100017, 2);
INSERT INTO `sys_user_role` VALUES (100018, 2);
INSERT INTO `sys_user_role` VALUES (100019, 2);
INSERT INTO `sys_user_role` VALUES (100020, 2);
INSERT INTO `sys_user_role` VALUES (100021, 2);
INSERT INTO `sys_user_role` VALUES (100022, 2);
INSERT INTO `sys_user_role` VALUES (100023, 2);
INSERT INTO `sys_user_role` VALUES (100024, 2);
INSERT INTO `sys_user_role` VALUES (100025, 2);
INSERT INTO `sys_user_role` VALUES (100026, 2);
INSERT INTO `sys_user_role` VALUES (100027, 2);
INSERT INTO `sys_user_role` VALUES (100028, 2);
INSERT INTO `sys_user_role` VALUES (100029, 2);
INSERT INTO `sys_user_role` VALUES (100030, 2);
INSERT INTO `sys_user_role` VALUES (100031, 2);
INSERT INTO `sys_user_role` VALUES (100032, 2);
INSERT INTO `sys_user_role` VALUES (100033, 2);
INSERT INTO `sys_user_role` VALUES (100034, 2);
INSERT INTO `sys_user_role` VALUES (100035, 2);
INSERT INTO `sys_user_role` VALUES (100036, 2);
INSERT INTO `sys_user_role` VALUES (100037, 2);
INSERT INTO `sys_user_role` VALUES (100038, 2);
INSERT INTO `sys_user_role` VALUES (100039, 2);
INSERT INTO `sys_user_role` VALUES (100040, 2);
INSERT INTO `sys_user_role` VALUES (100041, 2);
INSERT INTO `sys_user_role` VALUES (100042, 2);
INSERT INTO `sys_user_role` VALUES (100043, 2);
INSERT INTO `sys_user_role` VALUES (100044, 2);
INSERT INTO `sys_user_role` VALUES (100045, 2);
INSERT INTO `sys_user_role` VALUES (100046, 2);
INSERT INTO `sys_user_role` VALUES (100047, 2);
INSERT INTO `sys_user_role` VALUES (100048, 2);
INSERT INTO `sys_user_role` VALUES (100049, 2);
INSERT INTO `sys_user_role` VALUES (100050, 2);
INSERT INTO `sys_user_role` VALUES (100051, 2);
INSERT INTO `sys_user_role` VALUES (100052, 2);
INSERT INTO `sys_user_role` VALUES (100053, 2);
INSERT INTO `sys_user_role` VALUES (100054, 2);
INSERT INTO `sys_user_role` VALUES (100055, 2);
INSERT INTO `sys_user_role` VALUES (100056, 2);
INSERT INTO `sys_user_role` VALUES (100057, 2);
INSERT INTO `sys_user_role` VALUES (100058, 2);
INSERT INTO `sys_user_role` VALUES (100059, 2);
INSERT INTO `sys_user_role` VALUES (100060, 2);
INSERT INTO `sys_user_role` VALUES (100061, 2);
INSERT INTO `sys_user_role` VALUES (100062, 2);
INSERT INTO `sys_user_role` VALUES (100063, 2);
INSERT INTO `sys_user_role` VALUES (100064, 2);
INSERT INTO `sys_user_role` VALUES (100065, 2);
INSERT INTO `sys_user_role` VALUES (100066, 2);
INSERT INTO `sys_user_role` VALUES (100067, 2);
INSERT INTO `sys_user_role` VALUES (100068, 2);
INSERT INTO `sys_user_role` VALUES (100069, 2);
INSERT INTO `sys_user_role` VALUES (100070, 2);
INSERT INTO `sys_user_role` VALUES (100071, 2);
INSERT INTO `sys_user_role` VALUES (100072, 2);
INSERT INTO `sys_user_role` VALUES (100073, 2);
INSERT INTO `sys_user_role` VALUES (100074, 2);
INSERT INTO `sys_user_role` VALUES (100075, 2);
INSERT INTO `sys_user_role` VALUES (100076, 2);
INSERT INTO `sys_user_role` VALUES (100077, 2);
INSERT INTO `sys_user_role` VALUES (100078, 2);
INSERT INTO `sys_user_role` VALUES (100079, 2);
INSERT INTO `sys_user_role` VALUES (100080, 2);
INSERT INTO `sys_user_role` VALUES (100081, 2);
INSERT INTO `sys_user_role` VALUES (100082, 2);
INSERT INTO `sys_user_role` VALUES (100083, 2);
INSERT INTO `sys_user_role` VALUES (100084, 2);
INSERT INTO `sys_user_role` VALUES (100085, 2);
INSERT INTO `sys_user_role` VALUES (100086, 2);
INSERT INTO `sys_user_role` VALUES (100087, 2);
INSERT INTO `sys_user_role` VALUES (100088, 2);
INSERT INTO `sys_user_role` VALUES (100089, 2);
INSERT INTO `sys_user_role` VALUES (100090, 2);
INSERT INTO `sys_user_role` VALUES (100091, 2);
INSERT INTO `sys_user_role` VALUES (100092, 2);
INSERT INTO `sys_user_role` VALUES (100093, 2);
INSERT INTO `sys_user_role` VALUES (100094, 2);
INSERT INTO `sys_user_role` VALUES (100095, 2);
INSERT INTO `sys_user_role` VALUES (100096, 2);
INSERT INTO `sys_user_role` VALUES (100097, 2);
INSERT INTO `sys_user_role` VALUES (100098, 2);
INSERT INTO `sys_user_role` VALUES (100099, 2);
INSERT INTO `sys_user_role` VALUES (100100, 2);
INSERT INTO `sys_user_role` VALUES (100101, 2);
INSERT INTO `sys_user_role` VALUES (100102, 2);
INSERT INTO `sys_user_role` VALUES (100103, 2);
INSERT INTO `sys_user_role` VALUES (100104, 2);
INSERT INTO `sys_user_role` VALUES (100105, 2);
INSERT INTO `sys_user_role` VALUES (100106, 2);
INSERT INTO `sys_user_role` VALUES (100107, 2);
INSERT INTO `sys_user_role` VALUES (100108, 2);
INSERT INTO `sys_user_role` VALUES (100109, 2);
INSERT INTO `sys_user_role` VALUES (100110, 2);
INSERT INTO `sys_user_role` VALUES (100111, 2);
INSERT INTO `sys_user_role` VALUES (100112, 2);
INSERT INTO `sys_user_role` VALUES (100113, 2);
INSERT INTO `sys_user_role` VALUES (100114, 2);
INSERT INTO `sys_user_role` VALUES (100115, 2);
INSERT INTO `sys_user_role` VALUES (100116, 2);
INSERT INTO `sys_user_role` VALUES (100117, 2);
INSERT INTO `sys_user_role` VALUES (100118, 2);
INSERT INTO `sys_user_role` VALUES (100119, 2);
INSERT INTO `sys_user_role` VALUES (100120, 2);
INSERT INTO `sys_user_role` VALUES (100121, 2);
INSERT INTO `sys_user_role` VALUES (100122, 2);
INSERT INTO `sys_user_role` VALUES (100123, 2);
INSERT INTO `sys_user_role` VALUES (100124, 2);
INSERT INTO `sys_user_role` VALUES (100125, 2);
INSERT INTO `sys_user_role` VALUES (100126, 2);
INSERT INTO `sys_user_role` VALUES (100127, 2);
INSERT INTO `sys_user_role` VALUES (100128, 2);
INSERT INTO `sys_user_role` VALUES (100129, 2);
INSERT INTO `sys_user_role` VALUES (100130, 2);
INSERT INTO `sys_user_role` VALUES (100131, 2);
INSERT INTO `sys_user_role` VALUES (100132, 2);
INSERT INTO `sys_user_role` VALUES (100133, 2);
INSERT INTO `sys_user_role` VALUES (100134, 2);
INSERT INTO `sys_user_role` VALUES (100135, 2);
INSERT INTO `sys_user_role` VALUES (100136, 2);
INSERT INTO `sys_user_role` VALUES (100137, 2);
INSERT INTO `sys_user_role` VALUES (100138, 2);
INSERT INTO `sys_user_role` VALUES (100139, 2);
INSERT INTO `sys_user_role` VALUES (100140, 2);
INSERT INTO `sys_user_role` VALUES (100141, 2);
INSERT INTO `sys_user_role` VALUES (100142, 2);
INSERT INTO `sys_user_role` VALUES (100143, 2);
INSERT INTO `sys_user_role` VALUES (100144, 2);
INSERT INTO `sys_user_role` VALUES (100145, 2);
INSERT INTO `sys_user_role` VALUES (100146, 2);
INSERT INTO `sys_user_role` VALUES (100147, 2);
INSERT INTO `sys_user_role` VALUES (100148, 2);
INSERT INTO `sys_user_role` VALUES (100149, 2);
INSERT INTO `sys_user_role` VALUES (100150, 2);
INSERT INTO `sys_user_role` VALUES (100151, 2);
INSERT INTO `sys_user_role` VALUES (100152, 2);
INSERT INTO `sys_user_role` VALUES (100153, 2);
INSERT INTO `sys_user_role` VALUES (100154, 2);
INSERT INTO `sys_user_role` VALUES (100155, 2);
INSERT INTO `sys_user_role` VALUES (100156, 2);
INSERT INTO `sys_user_role` VALUES (100157, 2);
INSERT INTO `sys_user_role` VALUES (100158, 2);
INSERT INTO `sys_user_role` VALUES (100159, 2);
INSERT INTO `sys_user_role` VALUES (100160, 2);
INSERT INTO `sys_user_role` VALUES (100161, 2);
INSERT INTO `sys_user_role` VALUES (100162, 2);
INSERT INTO `sys_user_role` VALUES (100163, 2);
INSERT INTO `sys_user_role` VALUES (100164, 2);
INSERT INTO `sys_user_role` VALUES (100165, 2);
INSERT INTO `sys_user_role` VALUES (100166, 2);
INSERT INTO `sys_user_role` VALUES (100167, 2);
INSERT INTO `sys_user_role` VALUES (100168, 2);
INSERT INTO `sys_user_role` VALUES (100169, 2);
INSERT INTO `sys_user_role` VALUES (100170, 2);
INSERT INTO `sys_user_role` VALUES (100171, 2);
INSERT INTO `sys_user_role` VALUES (100172, 2);
INSERT INTO `sys_user_role` VALUES (100173, 2);
INSERT INTO `sys_user_role` VALUES (100174, 2);
INSERT INTO `sys_user_role` VALUES (100175, 2);
INSERT INTO `sys_user_role` VALUES (100176, 2);
INSERT INTO `sys_user_role` VALUES (100177, 2);
INSERT INTO `sys_user_role` VALUES (100178, 2);
INSERT INTO `sys_user_role` VALUES (100179, 2);
INSERT INTO `sys_user_role` VALUES (100180, 2);
INSERT INTO `sys_user_role` VALUES (100181, 2);
INSERT INTO `sys_user_role` VALUES (100182, 2);
INSERT INTO `sys_user_role` VALUES (100183, 2);
INSERT INTO `sys_user_role` VALUES (100184, 2);
INSERT INTO `sys_user_role` VALUES (100185, 2);
INSERT INTO `sys_user_role` VALUES (100186, 2);
INSERT INTO `sys_user_role` VALUES (100187, 2);
INSERT INTO `sys_user_role` VALUES (100188, 2);
INSERT INTO `sys_user_role` VALUES (100189, 2);
INSERT INTO `sys_user_role` VALUES (100190, 2);
INSERT INTO `sys_user_role` VALUES (100191, 2);
INSERT INTO `sys_user_role` VALUES (100192, 2);
INSERT INTO `sys_user_role` VALUES (100193, 2);
INSERT INTO `sys_user_role` VALUES (100194, 2);
INSERT INTO `sys_user_role` VALUES (100195, 2);
INSERT INTO `sys_user_role` VALUES (100196, 2);
INSERT INTO `sys_user_role` VALUES (100197, 2);
INSERT INTO `sys_user_role` VALUES (100198, 2);
INSERT INTO `sys_user_role` VALUES (100199, 2);
INSERT INTO `sys_user_role` VALUES (100200, 2);
INSERT INTO `sys_user_role` VALUES (100201, 2);
INSERT INTO `sys_user_role` VALUES (100202, 2);
INSERT INTO `sys_user_role` VALUES (100203, 2);
INSERT INTO `sys_user_role` VALUES (100204, 2);
INSERT INTO `sys_user_role` VALUES (100205, 2);
INSERT INTO `sys_user_role` VALUES (100206, 2);
INSERT INTO `sys_user_role` VALUES (100207, 2);
INSERT INTO `sys_user_role` VALUES (100208, 2);
INSERT INTO `sys_user_role` VALUES (100209, 2);
INSERT INTO `sys_user_role` VALUES (100210, 2);
INSERT INTO `sys_user_role` VALUES (100211, 2);
INSERT INTO `sys_user_role` VALUES (100212, 2);
INSERT INTO `sys_user_role` VALUES (100213, 2);
INSERT INTO `sys_user_role` VALUES (100214, 2);
INSERT INTO `sys_user_role` VALUES (100215, 2);
INSERT INTO `sys_user_role` VALUES (100216, 2);
INSERT INTO `sys_user_role` VALUES (100217, 2);
INSERT INTO `sys_user_role` VALUES (100218, 2);
INSERT INTO `sys_user_role` VALUES (100219, 2);
INSERT INTO `sys_user_role` VALUES (100220, 2);
INSERT INTO `sys_user_role` VALUES (100221, 2);
INSERT INTO `sys_user_role` VALUES (100222, 2);
INSERT INTO `sys_user_role` VALUES (100223, 2);
INSERT INTO `sys_user_role` VALUES (100224, 2);
INSERT INTO `sys_user_role` VALUES (100225, 2);
INSERT INTO `sys_user_role` VALUES (100226, 2);
INSERT INTO `sys_user_role` VALUES (100227, 2);
INSERT INTO `sys_user_role` VALUES (100228, 2);
INSERT INTO `sys_user_role` VALUES (100229, 2);
INSERT INTO `sys_user_role` VALUES (100230, 2);
INSERT INTO `sys_user_role` VALUES (100231, 2);
INSERT INTO `sys_user_role` VALUES (100232, 2);
INSERT INTO `sys_user_role` VALUES (100233, 2);
INSERT INTO `sys_user_role` VALUES (100234, 2);
INSERT INTO `sys_user_role` VALUES (100235, 2);
INSERT INTO `sys_user_role` VALUES (100236, 2);
INSERT INTO `sys_user_role` VALUES (100237, 2);
INSERT INTO `sys_user_role` VALUES (100238, 2);
INSERT INTO `sys_user_role` VALUES (100239, 2);
INSERT INTO `sys_user_role` VALUES (100240, 2);
INSERT INTO `sys_user_role` VALUES (100241, 2);
INSERT INTO `sys_user_role` VALUES (100242, 2);
INSERT INTO `sys_user_role` VALUES (100243, 2);
INSERT INTO `sys_user_role` VALUES (100244, 2);
INSERT INTO `sys_user_role` VALUES (100245, 2);
INSERT INTO `sys_user_role` VALUES (100246, 2);
INSERT INTO `sys_user_role` VALUES (100247, 2);
INSERT INTO `sys_user_role` VALUES (100248, 2);
INSERT INTO `sys_user_role` VALUES (100249, 2);
INSERT INTO `sys_user_role` VALUES (100250, 2);
INSERT INTO `sys_user_role` VALUES (100251, 2);
INSERT INTO `sys_user_role` VALUES (100252, 2);
INSERT INTO `sys_user_role` VALUES (100253, 2);
INSERT INTO `sys_user_role` VALUES (100254, 2);
INSERT INTO `sys_user_role` VALUES (100255, 2);
INSERT INTO `sys_user_role` VALUES (100256, 2);
INSERT INTO `sys_user_role` VALUES (100258, 2);
INSERT INTO `sys_user_role` VALUES (100259, 2);
INSERT INTO `sys_user_role` VALUES (100260, 2);
INSERT INTO `sys_user_role` VALUES (100261, 2);
INSERT INTO `sys_user_role` VALUES (100262, 2);
INSERT INTO `sys_user_role` VALUES (100263, 2);
INSERT INTO `sys_user_role` VALUES (100264, 2);
INSERT INTO `sys_user_role` VALUES (100265, 2);
INSERT INTO `sys_user_role` VALUES (100266, 2);
INSERT INTO `sys_user_role` VALUES (100267, 2);
INSERT INTO `sys_user_role` VALUES (100268, 2);
INSERT INTO `sys_user_role` VALUES (100269, 2);
INSERT INTO `sys_user_role` VALUES (100270, 2);
INSERT INTO `sys_user_role` VALUES (100271, 2);
INSERT INTO `sys_user_role` VALUES (100272, 2);
INSERT INTO `sys_user_role` VALUES (100273, 2);
INSERT INTO `sys_user_role` VALUES (100274, 2);
INSERT INTO `sys_user_role` VALUES (100275, 2);
INSERT INTO `sys_user_role` VALUES (100276, 2);
INSERT INTO `sys_user_role` VALUES (100277, 2);
INSERT INTO `sys_user_role` VALUES (100278, 2);
INSERT INTO `sys_user_role` VALUES (100279, 2);
INSERT INTO `sys_user_role` VALUES (100280, 2);
INSERT INTO `sys_user_role` VALUES (100282, 2);
INSERT INTO `sys_user_role` VALUES (100283, 2);
INSERT INTO `sys_user_role` VALUES (100284, 2);
INSERT INTO `sys_user_role` VALUES (100285, 2);
INSERT INTO `sys_user_role` VALUES (100286, 2);
INSERT INTO `sys_user_role` VALUES (100287, 2);
INSERT INTO `sys_user_role` VALUES (100288, 2);
INSERT INTO `sys_user_role` VALUES (100289, 2);
INSERT INTO `sys_user_role` VALUES (100290, 2);
INSERT INTO `sys_user_role` VALUES (100291, 2);
INSERT INTO `sys_user_role` VALUES (100292, 2);
INSERT INTO `sys_user_role` VALUES (100293, 2);
INSERT INTO `sys_user_role` VALUES (100294, 2);
INSERT INTO `sys_user_role` VALUES (100295, 2);
INSERT INTO `sys_user_role` VALUES (100296, 2);
INSERT INTO `sys_user_role` VALUES (100297, 2);
INSERT INTO `sys_user_role` VALUES (100298, 2);
INSERT INTO `sys_user_role` VALUES (100299, 2);
INSERT INTO `sys_user_role` VALUES (100300, 2);
INSERT INTO `sys_user_role` VALUES (100301, 2);
INSERT INTO `sys_user_role` VALUES (100302, 2);
INSERT INTO `sys_user_role` VALUES (100303, 2);
INSERT INTO `sys_user_role` VALUES (100304, 2);
INSERT INTO `sys_user_role` VALUES (100305, 2);
INSERT INTO `sys_user_role` VALUES (100306, 2);
INSERT INTO `sys_user_role` VALUES (100307, 2);
INSERT INTO `sys_user_role` VALUES (100309, 2);
INSERT INTO `sys_user_role` VALUES (100310, 2);
INSERT INTO `sys_user_role` VALUES (100311, 2);
INSERT INTO `sys_user_role` VALUES (100312, 2);
INSERT INTO `sys_user_role` VALUES (100313, 2);
INSERT INTO `sys_user_role` VALUES (100314, 2);
INSERT INTO `sys_user_role` VALUES (100315, 2);
INSERT INTO `sys_user_role` VALUES (100316, 2);
INSERT INTO `sys_user_role` VALUES (100317, 2);
INSERT INTO `sys_user_role` VALUES (100318, 2);
INSERT INTO `sys_user_role` VALUES (100319, 2);
INSERT INTO `sys_user_role` VALUES (100320, 2);
INSERT INTO `sys_user_role` VALUES (100321, 2);
INSERT INTO `sys_user_role` VALUES (100322, 2);
INSERT INTO `sys_user_role` VALUES (100323, 2);
INSERT INTO `sys_user_role` VALUES (100324, 2);
INSERT INTO `sys_user_role` VALUES (100324, 3);
INSERT INTO `sys_user_role` VALUES (100325, 2);
INSERT INTO `sys_user_role` VALUES (100326, 2);
INSERT INTO `sys_user_role` VALUES (100327, 2);
INSERT INTO `sys_user_role` VALUES (100328, 2);
INSERT INTO `sys_user_role` VALUES (100329, 2);
INSERT INTO `sys_user_role` VALUES (100330, 2);
INSERT INTO `sys_user_role` VALUES (100331, 2);
INSERT INTO `sys_user_role` VALUES (100332, 2);
INSERT INTO `sys_user_role` VALUES (100333, 2);
INSERT INTO `sys_user_role` VALUES (100334, 2);
INSERT INTO `sys_user_role` VALUES (100335, 2);
INSERT INTO `sys_user_role` VALUES (100336, 2);
INSERT INTO `sys_user_role` VALUES (100337, 2);
INSERT INTO `sys_user_role` VALUES (100338, 2);
INSERT INTO `sys_user_role` VALUES (100339, 2);
INSERT INTO `sys_user_role` VALUES (100340, 2);
INSERT INTO `sys_user_role` VALUES (100341, 2);
INSERT INTO `sys_user_role` VALUES (100342, 2);
INSERT INTO `sys_user_role` VALUES (100343, 2);
INSERT INTO `sys_user_role` VALUES (100344, 2);
INSERT INTO `sys_user_role` VALUES (100345, 2);
INSERT INTO `sys_user_role` VALUES (100346, 2);
INSERT INTO `sys_user_role` VALUES (100347, 2);
INSERT INTO `sys_user_role` VALUES (100348, 2);
INSERT INTO `sys_user_role` VALUES (100349, 2);
INSERT INTO `sys_user_role` VALUES (100350, 2);
INSERT INTO `sys_user_role` VALUES (100351, 2);
INSERT INTO `sys_user_role` VALUES (100352, 2);
INSERT INTO `sys_user_role` VALUES (100353, 2);
INSERT INTO `sys_user_role` VALUES (100354, 2);
INSERT INTO `sys_user_role` VALUES (100355, 2);
INSERT INTO `sys_user_role` VALUES (100356, 2);
INSERT INTO `sys_user_role` VALUES (100357, 2);
INSERT INTO `sys_user_role` VALUES (100358, 2);
INSERT INTO `sys_user_role` VALUES (100359, 2);
INSERT INTO `sys_user_role` VALUES (100360, 2);
INSERT INTO `sys_user_role` VALUES (100361, 2);
INSERT INTO `sys_user_role` VALUES (100362, 2);
INSERT INTO `sys_user_role` VALUES (100363, 2);
INSERT INTO `sys_user_role` VALUES (100364, 2);
INSERT INTO `sys_user_role` VALUES (100365, 2);
INSERT INTO `sys_user_role` VALUES (100366, 2);
INSERT INTO `sys_user_role` VALUES (100367, 2);
INSERT INTO `sys_user_role` VALUES (100368, 2);
INSERT INTO `sys_user_role` VALUES (100369, 2);
INSERT INTO `sys_user_role` VALUES (100370, 2);
INSERT INTO `sys_user_role` VALUES (100371, 2);
INSERT INTO `sys_user_role` VALUES (100372, 2);
INSERT INTO `sys_user_role` VALUES (100373, 2);
INSERT INTO `sys_user_role` VALUES (100374, 2);
INSERT INTO `sys_user_role` VALUES (100375, 2);
INSERT INTO `sys_user_role` VALUES (100376, 2);
INSERT INTO `sys_user_role` VALUES (100377, 2);
INSERT INTO `sys_user_role` VALUES (100378, 2);
INSERT INTO `sys_user_role` VALUES (100379, 2);
INSERT INTO `sys_user_role` VALUES (100380, 2);
INSERT INTO `sys_user_role` VALUES (100381, 2);
INSERT INTO `sys_user_role` VALUES (100382, 2);
INSERT INTO `sys_user_role` VALUES (100383, 2);
INSERT INTO `sys_user_role` VALUES (100384, 2);
INSERT INTO `sys_user_role` VALUES (100385, 2);
INSERT INTO `sys_user_role` VALUES (100386, 2);
INSERT INTO `sys_user_role` VALUES (100387, 2);
INSERT INTO `sys_user_role` VALUES (100388, 2);
INSERT INTO `sys_user_role` VALUES (100389, 2);
INSERT INTO `sys_user_role` VALUES (100390, 2);
INSERT INTO `sys_user_role` VALUES (100391, 2);
INSERT INTO `sys_user_role` VALUES (100392, 2);
INSERT INTO `sys_user_role` VALUES (100393, 2);
INSERT INTO `sys_user_role` VALUES (100394, 2);
INSERT INTO `sys_user_role` VALUES (100395, 2);
INSERT INTO `sys_user_role` VALUES (100396, 2);
INSERT INTO `sys_user_role` VALUES (100397, 2);
INSERT INTO `sys_user_role` VALUES (100398, 2);
INSERT INTO `sys_user_role` VALUES (100399, 2);
INSERT INTO `sys_user_role` VALUES (100400, 2);
INSERT INTO `sys_user_role` VALUES (100401, 2);
INSERT INTO `sys_user_role` VALUES (100402, 2);
INSERT INTO `sys_user_role` VALUES (100403, 2);
INSERT INTO `sys_user_role` VALUES (100404, 2);
INSERT INTO `sys_user_role` VALUES (100405, 2);
INSERT INTO `sys_user_role` VALUES (100406, 2);
INSERT INTO `sys_user_role` VALUES (100407, 2);
INSERT INTO `sys_user_role` VALUES (100408, 2);
INSERT INTO `sys_user_role` VALUES (100409, 2);
INSERT INTO `sys_user_role` VALUES (100410, 2);
INSERT INTO `sys_user_role` VALUES (100411, 2);
INSERT INTO `sys_user_role` VALUES (100412, 2);
INSERT INTO `sys_user_role` VALUES (100413, 2);
INSERT INTO `sys_user_role` VALUES (100414, 2);
INSERT INTO `sys_user_role` VALUES (100415, 2);
INSERT INTO `sys_user_role` VALUES (100416, 2);
INSERT INTO `sys_user_role` VALUES (100417, 2);
INSERT INTO `sys_user_role` VALUES (100418, 2);
INSERT INTO `sys_user_role` VALUES (100419, 2);
INSERT INTO `sys_user_role` VALUES (100420, 2);
INSERT INTO `sys_user_role` VALUES (100421, 2);
INSERT INTO `sys_user_role` VALUES (100422, 2);
INSERT INTO `sys_user_role` VALUES (100423, 2);
INSERT INTO `sys_user_role` VALUES (100424, 2);
INSERT INTO `sys_user_role` VALUES (100425, 2);
INSERT INTO `sys_user_role` VALUES (100426, 2);
INSERT INTO `sys_user_role` VALUES (100427, 2);
INSERT INTO `sys_user_role` VALUES (100428, 2);
INSERT INTO `sys_user_role` VALUES (100429, 2);
INSERT INTO `sys_user_role` VALUES (100430, 2);
INSERT INTO `sys_user_role` VALUES (100431, 2);
INSERT INTO `sys_user_role` VALUES (100432, 2);
INSERT INTO `sys_user_role` VALUES (100433, 2);
INSERT INTO `sys_user_role` VALUES (100434, 2);
INSERT INTO `sys_user_role` VALUES (100435, 2);
INSERT INTO `sys_user_role` VALUES (100436, 2);
INSERT INTO `sys_user_role` VALUES (100437, 2);
INSERT INTO `sys_user_role` VALUES (100438, 2);
INSERT INTO `sys_user_role` VALUES (100439, 2);
INSERT INTO `sys_user_role` VALUES (100440, 2);
INSERT INTO `sys_user_role` VALUES (100441, 2);
INSERT INTO `sys_user_role` VALUES (100442, 2);
INSERT INTO `sys_user_role` VALUES (100443, 2);
INSERT INTO `sys_user_role` VALUES (100444, 2);
INSERT INTO `sys_user_role` VALUES (100445, 2);
INSERT INTO `sys_user_role` VALUES (100446, 2);
INSERT INTO `sys_user_role` VALUES (100447, 2);
INSERT INTO `sys_user_role` VALUES (100448, 2);
INSERT INTO `sys_user_role` VALUES (100449, 2);
INSERT INTO `sys_user_role` VALUES (100450, 2);
INSERT INTO `sys_user_role` VALUES (100451, 2);
INSERT INTO `sys_user_role` VALUES (100452, 2);
INSERT INTO `sys_user_role` VALUES (100453, 2);
INSERT INTO `sys_user_role` VALUES (100454, 2);
INSERT INTO `sys_user_role` VALUES (100455, 2);
INSERT INTO `sys_user_role` VALUES (100456, 2);
INSERT INTO `sys_user_role` VALUES (100457, 2);
INSERT INTO `sys_user_role` VALUES (100458, 2);
INSERT INTO `sys_user_role` VALUES (100459, 2);
INSERT INTO `sys_user_role` VALUES (100460, 2);
INSERT INTO `sys_user_role` VALUES (100461, 2);
INSERT INTO `sys_user_role` VALUES (100462, 2);
INSERT INTO `sys_user_role` VALUES (100463, 2);
INSERT INTO `sys_user_role` VALUES (100464, 2);
INSERT INTO `sys_user_role` VALUES (100465, 2);
INSERT INTO `sys_user_role` VALUES (100466, 2);
INSERT INTO `sys_user_role` VALUES (100467, 2);
INSERT INTO `sys_user_role` VALUES (100468, 2);
INSERT INTO `sys_user_role` VALUES (100469, 2);
INSERT INTO `sys_user_role` VALUES (100470, 2);
INSERT INTO `sys_user_role` VALUES (100471, 2);
INSERT INTO `sys_user_role` VALUES (100472, 2);
INSERT INTO `sys_user_role` VALUES (100473, 2);
INSERT INTO `sys_user_role` VALUES (100474, 2);
INSERT INTO `sys_user_role` VALUES (100475, 2);
INSERT INTO `sys_user_role` VALUES (100476, 2);
INSERT INTO `sys_user_role` VALUES (100477, 2);
INSERT INTO `sys_user_role` VALUES (100478, 2);
INSERT INTO `sys_user_role` VALUES (100479, 2);
INSERT INTO `sys_user_role` VALUES (100480, 2);
INSERT INTO `sys_user_role` VALUES (100481, 2);
INSERT INTO `sys_user_role` VALUES (100482, 2);
INSERT INTO `sys_user_role` VALUES (100483, 2);
INSERT INTO `sys_user_role` VALUES (100484, 2);
INSERT INTO `sys_user_role` VALUES (100485, 2);
INSERT INTO `sys_user_role` VALUES (100486, 2);
INSERT INTO `sys_user_role` VALUES (100487, 2);
INSERT INTO `sys_user_role` VALUES (100489, 2);
INSERT INTO `sys_user_role` VALUES (100490, 2);
INSERT INTO `sys_user_role` VALUES (100491, 2);
INSERT INTO `sys_user_role` VALUES (100492, 2);
INSERT INTO `sys_user_role` VALUES (100493, 2);
INSERT INTO `sys_user_role` VALUES (100494, 2);
INSERT INTO `sys_user_role` VALUES (100495, 2);
INSERT INTO `sys_user_role` VALUES (100496, 2);
INSERT INTO `sys_user_role` VALUES (100497, 2);
INSERT INTO `sys_user_role` VALUES (100498, 2);
INSERT INTO `sys_user_role` VALUES (100499, 2);
INSERT INTO `sys_user_role` VALUES (100500, 2);
INSERT INTO `sys_user_role` VALUES (100501, 2);
INSERT INTO `sys_user_role` VALUES (100502, 2);
INSERT INTO `sys_user_role` VALUES (100503, 2);
INSERT INTO `sys_user_role` VALUES (100504, 2);
INSERT INTO `sys_user_role` VALUES (100505, 2);
INSERT INTO `sys_user_role` VALUES (100506, 2);
INSERT INTO `sys_user_role` VALUES (100507, 2);
INSERT INTO `sys_user_role` VALUES (100508, 2);
INSERT INTO `sys_user_role` VALUES (100509, 2);
INSERT INTO `sys_user_role` VALUES (100510, 2);
INSERT INTO `sys_user_role` VALUES (100511, 2);
INSERT INTO `sys_user_role` VALUES (100512, 2);
INSERT INTO `sys_user_role` VALUES (100513, 2);
INSERT INTO `sys_user_role` VALUES (100514, 2);
INSERT INTO `sys_user_role` VALUES (100515, 2);
INSERT INTO `sys_user_role` VALUES (100516, 2);
INSERT INTO `sys_user_role` VALUES (100517, 2);
INSERT INTO `sys_user_role` VALUES (100518, 2);
INSERT INTO `sys_user_role` VALUES (100519, 2);
INSERT INTO `sys_user_role` VALUES (100520, 2);
INSERT INTO `sys_user_role` VALUES (100521, 2);
INSERT INTO `sys_user_role` VALUES (100522, 2);
INSERT INTO `sys_user_role` VALUES (100523, 2);
INSERT INTO `sys_user_role` VALUES (100524, 2);
INSERT INTO `sys_user_role` VALUES (100525, 2);
INSERT INTO `sys_user_role` VALUES (100526, 2);
INSERT INTO `sys_user_role` VALUES (100527, 2);
INSERT INTO `sys_user_role` VALUES (100528, 2);
INSERT INTO `sys_user_role` VALUES (100529, 2);
INSERT INTO `sys_user_role` VALUES (100530, 2);
INSERT INTO `sys_user_role` VALUES (100531, 2);
INSERT INTO `sys_user_role` VALUES (100532, 2);
INSERT INTO `sys_user_role` VALUES (100533, 2);
INSERT INTO `sys_user_role` VALUES (100534, 2);
INSERT INTO `sys_user_role` VALUES (100535, 2);
INSERT INTO `sys_user_role` VALUES (100536, 2);
INSERT INTO `sys_user_role` VALUES (100537, 2);
INSERT INTO `sys_user_role` VALUES (100538, 2);
INSERT INTO `sys_user_role` VALUES (100539, 2);
INSERT INTO `sys_user_role` VALUES (100540, 2);
INSERT INTO `sys_user_role` VALUES (100541, 2);
INSERT INTO `sys_user_role` VALUES (100542, 2);
INSERT INTO `sys_user_role` VALUES (100543, 2);
INSERT INTO `sys_user_role` VALUES (100544, 2);
INSERT INTO `sys_user_role` VALUES (100545, 2);
INSERT INTO `sys_user_role` VALUES (100546, 2);
INSERT INTO `sys_user_role` VALUES (100547, 2);
INSERT INTO `sys_user_role` VALUES (100548, 2);
INSERT INTO `sys_user_role` VALUES (100549, 2);
INSERT INTO `sys_user_role` VALUES (100550, 2);
INSERT INTO `sys_user_role` VALUES (100551, 2);
INSERT INTO `sys_user_role` VALUES (100552, 2);
INSERT INTO `sys_user_role` VALUES (100553, 2);
INSERT INTO `sys_user_role` VALUES (100554, 2);
INSERT INTO `sys_user_role` VALUES (100556, 2);
INSERT INTO `sys_user_role` VALUES (100557, 2);
INSERT INTO `sys_user_role` VALUES (100558, 2);
INSERT INTO `sys_user_role` VALUES (100559, 2);
INSERT INTO `sys_user_role` VALUES (100560, 2);
INSERT INTO `sys_user_role` VALUES (100561, 2);
INSERT INTO `sys_user_role` VALUES (100562, 2);
INSERT INTO `sys_user_role` VALUES (100563, 2);
INSERT INTO `sys_user_role` VALUES (100566, 2);
INSERT INTO `sys_user_role` VALUES (100567, 2);
INSERT INTO `sys_user_role` VALUES (100568, 2);
INSERT INTO `sys_user_role` VALUES (100569, 2);
INSERT INTO `sys_user_role` VALUES (100570, 2);
INSERT INTO `sys_user_role` VALUES (100571, 2);
INSERT INTO `sys_user_role` VALUES (100572, 2);
INSERT INTO `sys_user_role` VALUES (100573, 2);
INSERT INTO `sys_user_role` VALUES (100574, 2);
INSERT INTO `sys_user_role` VALUES (100575, 2);
INSERT INTO `sys_user_role` VALUES (100576, 2);
INSERT INTO `sys_user_role` VALUES (100577, 2);
INSERT INTO `sys_user_role` VALUES (100578, 2);
INSERT INTO `sys_user_role` VALUES (100579, 2);
INSERT INTO `sys_user_role` VALUES (100580, 2);
INSERT INTO `sys_user_role` VALUES (100581, 2);
INSERT INTO `sys_user_role` VALUES (100583, 2);
INSERT INTO `sys_user_role` VALUES (100584, 2);
INSERT INTO `sys_user_role` VALUES (100585, 2);
INSERT INTO `sys_user_role` VALUES (100586, 2);
INSERT INTO `sys_user_role` VALUES (100587, 2);
INSERT INTO `sys_user_role` VALUES (100588, 2);
INSERT INTO `sys_user_role` VALUES (100589, 2);
INSERT INTO `sys_user_role` VALUES (100590, 2);
INSERT INTO `sys_user_role` VALUES (100591, 2);
INSERT INTO `sys_user_role` VALUES (100592, 2);
INSERT INTO `sys_user_role` VALUES (100593, 2);
INSERT INTO `sys_user_role` VALUES (100594, 2);
INSERT INTO `sys_user_role` VALUES (100595, 2);
INSERT INTO `sys_user_role` VALUES (100596, 2);
INSERT INTO `sys_user_role` VALUES (100597, 2);
INSERT INTO `sys_user_role` VALUES (100598, 2);
INSERT INTO `sys_user_role` VALUES (100599, 2);
INSERT INTO `sys_user_role` VALUES (100600, 2);
INSERT INTO `sys_user_role` VALUES (100601, 2);
INSERT INTO `sys_user_role` VALUES (100602, 2);
INSERT INTO `sys_user_role` VALUES (100603, 2);
INSERT INTO `sys_user_role` VALUES (100604, 2);
INSERT INTO `sys_user_role` VALUES (100605, 2);
INSERT INTO `sys_user_role` VALUES (100606, 2);
INSERT INTO `sys_user_role` VALUES (100607, 2);
INSERT INTO `sys_user_role` VALUES (100608, 2);
INSERT INTO `sys_user_role` VALUES (100609, 2);
INSERT INTO `sys_user_role` VALUES (100610, 2);
INSERT INTO `sys_user_role` VALUES (100611, 2);
INSERT INTO `sys_user_role` VALUES (100612, 2);
INSERT INTO `sys_user_role` VALUES (100613, 2);
INSERT INTO `sys_user_role` VALUES (100614, 2);
INSERT INTO `sys_user_role` VALUES (100615, 2);
INSERT INTO `sys_user_role` VALUES (100616, 2);
INSERT INTO `sys_user_role` VALUES (100617, 2);
INSERT INTO `sys_user_role` VALUES (100618, 2);
INSERT INTO `sys_user_role` VALUES (100619, 2);
INSERT INTO `sys_user_role` VALUES (100620, 2);
INSERT INTO `sys_user_role` VALUES (100621, 2);
INSERT INTO `sys_user_role` VALUES (100622, 2);
INSERT INTO `sys_user_role` VALUES (100623, 2);
INSERT INTO `sys_user_role` VALUES (100624, 2);
INSERT INTO `sys_user_role` VALUES (100625, 2);
INSERT INTO `sys_user_role` VALUES (100626, 2);
INSERT INTO `sys_user_role` VALUES (100627, 2);
INSERT INTO `sys_user_role` VALUES (100628, 2);
INSERT INTO `sys_user_role` VALUES (100629, 2);
INSERT INTO `sys_user_role` VALUES (100630, 2);
INSERT INTO `sys_user_role` VALUES (100631, 2);
INSERT INTO `sys_user_role` VALUES (100632, 2);
INSERT INTO `sys_user_role` VALUES (100633, 2);
INSERT INTO `sys_user_role` VALUES (100634, 2);
INSERT INTO `sys_user_role` VALUES (100635, 2);
INSERT INTO `sys_user_role` VALUES (100636, 2);
INSERT INTO `sys_user_role` VALUES (100638, 2);
INSERT INTO `sys_user_role` VALUES (100639, 2);
INSERT INTO `sys_user_role` VALUES (100640, 2);
INSERT INTO `sys_user_role` VALUES (100641, 2);
INSERT INTO `sys_user_role` VALUES (100642, 2);
INSERT INTO `sys_user_role` VALUES (100643, 2);
INSERT INTO `sys_user_role` VALUES (100644, 2);
INSERT INTO `sys_user_role` VALUES (100645, 2);
INSERT INTO `sys_user_role` VALUES (100646, 2);
INSERT INTO `sys_user_role` VALUES (100647, 2);
INSERT INTO `sys_user_role` VALUES (100648, 2);
INSERT INTO `sys_user_role` VALUES (100649, 2);
INSERT INTO `sys_user_role` VALUES (100650, 2);
INSERT INTO `sys_user_role` VALUES (100651, 2);
INSERT INTO `sys_user_role` VALUES (100652, 2);
INSERT INTO `sys_user_role` VALUES (100653, 2);
INSERT INTO `sys_user_role` VALUES (100654, 2);
INSERT INTO `sys_user_role` VALUES (100655, 2);
INSERT INTO `sys_user_role` VALUES (100656, 2);
INSERT INTO `sys_user_role` VALUES (100657, 2);
INSERT INTO `sys_user_role` VALUES (100658, 2);
INSERT INTO `sys_user_role` VALUES (100659, 2);
INSERT INTO `sys_user_role` VALUES (100660, 2);
INSERT INTO `sys_user_role` VALUES (100661, 2);
INSERT INTO `sys_user_role` VALUES (100662, 2);
INSERT INTO `sys_user_role` VALUES (100663, 2);
INSERT INTO `sys_user_role` VALUES (100664, 2);
INSERT INTO `sys_user_role` VALUES (100665, 2);
INSERT INTO `sys_user_role` VALUES (100666, 2);
INSERT INTO `sys_user_role` VALUES (100667, 2);
INSERT INTO `sys_user_role` VALUES (100668, 2);
INSERT INTO `sys_user_role` VALUES (100669, 2);
INSERT INTO `sys_user_role` VALUES (100670, 2);
INSERT INTO `sys_user_role` VALUES (100671, 2);
INSERT INTO `sys_user_role` VALUES (100672, 2);
INSERT INTO `sys_user_role` VALUES (100674, 2);
INSERT INTO `sys_user_role` VALUES (100675, 2);
INSERT INTO `sys_user_role` VALUES (100676, 2);
INSERT INTO `sys_user_role` VALUES (100677, 2);
INSERT INTO `sys_user_role` VALUES (100678, 2);
INSERT INTO `sys_user_role` VALUES (100679, 2);
INSERT INTO `sys_user_role` VALUES (100680, 2);
INSERT INTO `sys_user_role` VALUES (100681, 2);
INSERT INTO `sys_user_role` VALUES (100682, 2);
INSERT INTO `sys_user_role` VALUES (100683, 2);
INSERT INTO `sys_user_role` VALUES (100684, 2);
INSERT INTO `sys_user_role` VALUES (100685, 2);
INSERT INTO `sys_user_role` VALUES (100686, 2);
INSERT INTO `sys_user_role` VALUES (100687, 2);
INSERT INTO `sys_user_role` VALUES (100688, 2);
INSERT INTO `sys_user_role` VALUES (100689, 2);
INSERT INTO `sys_user_role` VALUES (100690, 2);
INSERT INTO `sys_user_role` VALUES (100691, 2);
INSERT INTO `sys_user_role` VALUES (100692, 2);
INSERT INTO `sys_user_role` VALUES (100693, 2);
INSERT INTO `sys_user_role` VALUES (100694, 2);
INSERT INTO `sys_user_role` VALUES (100695, 2);
INSERT INTO `sys_user_role` VALUES (100696, 2);
INSERT INTO `sys_user_role` VALUES (100697, 2);
INSERT INTO `sys_user_role` VALUES (100698, 2);
INSERT INTO `sys_user_role` VALUES (100699, 2);
INSERT INTO `sys_user_role` VALUES (100700, 2);
INSERT INTO `sys_user_role` VALUES (100701, 2);
INSERT INTO `sys_user_role` VALUES (100702, 2);
INSERT INTO `sys_user_role` VALUES (100703, 2);
INSERT INTO `sys_user_role` VALUES (100704, 2);
INSERT INTO `sys_user_role` VALUES (100705, 2);
INSERT INTO `sys_user_role` VALUES (100706, 2);
INSERT INTO `sys_user_role` VALUES (100707, 2);
INSERT INTO `sys_user_role` VALUES (100708, 2);
INSERT INTO `sys_user_role` VALUES (100709, 2);
INSERT INTO `sys_user_role` VALUES (100710, 2);
INSERT INTO `sys_user_role` VALUES (100711, 2);
INSERT INTO `sys_user_role` VALUES (100712, 2);
INSERT INTO `sys_user_role` VALUES (100713, 2);
INSERT INTO `sys_user_role` VALUES (100714, 2);
INSERT INTO `sys_user_role` VALUES (100715, 2);
INSERT INTO `sys_user_role` VALUES (100716, 2);
INSERT INTO `sys_user_role` VALUES (100717, 2);
INSERT INTO `sys_user_role` VALUES (100718, 2);
INSERT INTO `sys_user_role` VALUES (100719, 2);
INSERT INTO `sys_user_role` VALUES (100720, 2);
INSERT INTO `sys_user_role` VALUES (100721, 2);
INSERT INTO `sys_user_role` VALUES (100722, 2);
INSERT INTO `sys_user_role` VALUES (100723, 2);
INSERT INTO `sys_user_role` VALUES (100724, 2);
INSERT INTO `sys_user_role` VALUES (100725, 2);
INSERT INTO `sys_user_role` VALUES (100726, 2);
INSERT INTO `sys_user_role` VALUES (100727, 2);
INSERT INTO `sys_user_role` VALUES (100728, 2);
INSERT INTO `sys_user_role` VALUES (100729, 2);
INSERT INTO `sys_user_role` VALUES (100730, 2);
INSERT INTO `sys_user_role` VALUES (100731, 2);
INSERT INTO `sys_user_role` VALUES (100732, 2);
INSERT INTO `sys_user_role` VALUES (100733, 2);
INSERT INTO `sys_user_role` VALUES (100734, 2);
INSERT INTO `sys_user_role` VALUES (100735, 2);
INSERT INTO `sys_user_role` VALUES (100736, 2);
INSERT INTO `sys_user_role` VALUES (100737, 2);
INSERT INTO `sys_user_role` VALUES (100738, 2);
INSERT INTO `sys_user_role` VALUES (100739, 2);
INSERT INTO `sys_user_role` VALUES (100740, 2);
INSERT INTO `sys_user_role` VALUES (100741, 2);
INSERT INTO `sys_user_role` VALUES (100742, 2);
INSERT INTO `sys_user_role` VALUES (100743, 2);
INSERT INTO `sys_user_role` VALUES (100744, 2);
INSERT INTO `sys_user_role` VALUES (100745, 2);
INSERT INTO `sys_user_role` VALUES (100746, 2);
INSERT INTO `sys_user_role` VALUES (100747, 2);
INSERT INTO `sys_user_role` VALUES (100748, 2);
INSERT INTO `sys_user_role` VALUES (100749, 2);
INSERT INTO `sys_user_role` VALUES (100750, 2);
INSERT INTO `sys_user_role` VALUES (100751, 2);
INSERT INTO `sys_user_role` VALUES (100752, 2);
INSERT INTO `sys_user_role` VALUES (100753, 2);
INSERT INTO `sys_user_role` VALUES (100754, 2);
INSERT INTO `sys_user_role` VALUES (100755, 2);
INSERT INTO `sys_user_role` VALUES (100756, 2);
INSERT INTO `sys_user_role` VALUES (100757, 2);
INSERT INTO `sys_user_role` VALUES (100758, 2);
INSERT INTO `sys_user_role` VALUES (100759, 2);
INSERT INTO `sys_user_role` VALUES (100760, 2);
INSERT INTO `sys_user_role` VALUES (100761, 2);
INSERT INTO `sys_user_role` VALUES (100762, 2);
INSERT INTO `sys_user_role` VALUES (100763, 2);
INSERT INTO `sys_user_role` VALUES (100764, 2);
INSERT INTO `sys_user_role` VALUES (100765, 2);
INSERT INTO `sys_user_role` VALUES (100766, 2);
INSERT INTO `sys_user_role` VALUES (100767, 2);
INSERT INTO `sys_user_role` VALUES (100768, 2);
INSERT INTO `sys_user_role` VALUES (100769, 2);
INSERT INTO `sys_user_role` VALUES (100770, 2);
INSERT INTO `sys_user_role` VALUES (100771, 2);
INSERT INTO `sys_user_role` VALUES (100772, 2);
INSERT INTO `sys_user_role` VALUES (100773, 2);
INSERT INTO `sys_user_role` VALUES (100774, 2);
INSERT INTO `sys_user_role` VALUES (100775, 2);
INSERT INTO `sys_user_role` VALUES (100776, 2);
INSERT INTO `sys_user_role` VALUES (100777, 2);
INSERT INTO `sys_user_role` VALUES (100778, 2);
INSERT INTO `sys_user_role` VALUES (100779, 2);
INSERT INTO `sys_user_role` VALUES (100780, 2);
INSERT INTO `sys_user_role` VALUES (100781, 2);
INSERT INTO `sys_user_role` VALUES (100782, 2);
INSERT INTO `sys_user_role` VALUES (100783, 2);
INSERT INTO `sys_user_role` VALUES (100784, 2);
INSERT INTO `sys_user_role` VALUES (100785, 2);
INSERT INTO `sys_user_role` VALUES (100786, 2);
INSERT INTO `sys_user_role` VALUES (100787, 2);
INSERT INTO `sys_user_role` VALUES (100788, 2);
INSERT INTO `sys_user_role` VALUES (100789, 2);
INSERT INTO `sys_user_role` VALUES (100790, 2);
INSERT INTO `sys_user_role` VALUES (100791, 2);
INSERT INTO `sys_user_role` VALUES (100792, 2);
INSERT INTO `sys_user_role` VALUES (100793, 2);
INSERT INTO `sys_user_role` VALUES (100794, 2);
INSERT INTO `sys_user_role` VALUES (100795, 2);
INSERT INTO `sys_user_role` VALUES (100796, 2);
INSERT INTO `sys_user_role` VALUES (100797, 2);
INSERT INTO `sys_user_role` VALUES (100798, 2);
INSERT INTO `sys_user_role` VALUES (100799, 2);
INSERT INTO `sys_user_role` VALUES (100800, 2);
INSERT INTO `sys_user_role` VALUES (100801, 2);
INSERT INTO `sys_user_role` VALUES (100802, 2);
INSERT INTO `sys_user_role` VALUES (100803, 2);
INSERT INTO `sys_user_role` VALUES (100804, 2);
INSERT INTO `sys_user_role` VALUES (100805, 2);
INSERT INTO `sys_user_role` VALUES (100806, 2);
INSERT INTO `sys_user_role` VALUES (100807, 2);
INSERT INTO `sys_user_role` VALUES (100808, 2);
INSERT INTO `sys_user_role` VALUES (100809, 2);
INSERT INTO `sys_user_role` VALUES (100810, 2);
INSERT INTO `sys_user_role` VALUES (100811, 2);
INSERT INTO `sys_user_role` VALUES (100812, 2);
INSERT INTO `sys_user_role` VALUES (100813, 2);
INSERT INTO `sys_user_role` VALUES (100814, 2);
INSERT INTO `sys_user_role` VALUES (100815, 2);
INSERT INTO `sys_user_role` VALUES (100816, 2);
INSERT INTO `sys_user_role` VALUES (100817, 2);
INSERT INTO `sys_user_role` VALUES (100818, 2);
INSERT INTO `sys_user_role` VALUES (100819, 2);
INSERT INTO `sys_user_role` VALUES (100820, 2);
INSERT INTO `sys_user_role` VALUES (100821, 2);
INSERT INTO `sys_user_role` VALUES (100822, 2);
INSERT INTO `sys_user_role` VALUES (100823, 2);
INSERT INTO `sys_user_role` VALUES (100824, 2);
INSERT INTO `sys_user_role` VALUES (100825, 2);
INSERT INTO `sys_user_role` VALUES (100826, 2);
INSERT INTO `sys_user_role` VALUES (100827, 2);
INSERT INTO `sys_user_role` VALUES (100828, 2);
INSERT INTO `sys_user_role` VALUES (100829, 2);
INSERT INTO `sys_user_role` VALUES (100830, 2);
INSERT INTO `sys_user_role` VALUES (100831, 2);
INSERT INTO `sys_user_role` VALUES (100832, 2);
INSERT INTO `sys_user_role` VALUES (100833, 2);
INSERT INTO `sys_user_role` VALUES (100834, 2);
INSERT INTO `sys_user_role` VALUES (100835, 2);
INSERT INTO `sys_user_role` VALUES (100836, 2);
INSERT INTO `sys_user_role` VALUES (100837, 2);
INSERT INTO `sys_user_role` VALUES (100838, 2);
INSERT INTO `sys_user_role` VALUES (100839, 2);
INSERT INTO `sys_user_role` VALUES (100840, 2);
INSERT INTO `sys_user_role` VALUES (100841, 2);
INSERT INTO `sys_user_role` VALUES (100842, 2);
INSERT INTO `sys_user_role` VALUES (100843, 2);
INSERT INTO `sys_user_role` VALUES (100844, 2);
INSERT INTO `sys_user_role` VALUES (100845, 2);
INSERT INTO `sys_user_role` VALUES (100846, 2);
INSERT INTO `sys_user_role` VALUES (100847, 2);
INSERT INTO `sys_user_role` VALUES (100848, 2);
INSERT INTO `sys_user_role` VALUES (100849, 2);
INSERT INTO `sys_user_role` VALUES (100850, 2);
INSERT INTO `sys_user_role` VALUES (100851, 2);
INSERT INTO `sys_user_role` VALUES (100852, 2);
INSERT INTO `sys_user_role` VALUES (100853, 2);
INSERT INTO `sys_user_role` VALUES (100854, 2);
INSERT INTO `sys_user_role` VALUES (100855, 2);
INSERT INTO `sys_user_role` VALUES (100856, 2);
INSERT INTO `sys_user_role` VALUES (100857, 2);
INSERT INTO `sys_user_role` VALUES (100858, 2);
INSERT INTO `sys_user_role` VALUES (100859, 2);
INSERT INTO `sys_user_role` VALUES (100860, 2);
INSERT INTO `sys_user_role` VALUES (100861, 2);
INSERT INTO `sys_user_role` VALUES (100862, 2);
INSERT INTO `sys_user_role` VALUES (100863, 2);
INSERT INTO `sys_user_role` VALUES (100864, 2);
INSERT INTO `sys_user_role` VALUES (100865, 2);
INSERT INTO `sys_user_role` VALUES (100866, 2);
INSERT INTO `sys_user_role` VALUES (100867, 2);
INSERT INTO `sys_user_role` VALUES (100868, 2);
INSERT INTO `sys_user_role` VALUES (100869, 2);
INSERT INTO `sys_user_role` VALUES (100870, 2);
INSERT INTO `sys_user_role` VALUES (100871, 2);
INSERT INTO `sys_user_role` VALUES (100872, 2);
INSERT INTO `sys_user_role` VALUES (100873, 2);
INSERT INTO `sys_user_role` VALUES (100874, 2);
INSERT INTO `sys_user_role` VALUES (100875, 2);
INSERT INTO `sys_user_role` VALUES (100876, 2);
INSERT INTO `sys_user_role` VALUES (100877, 2);
INSERT INTO `sys_user_role` VALUES (100878, 2);
INSERT INTO `sys_user_role` VALUES (100879, 2);
INSERT INTO `sys_user_role` VALUES (100880, 2);
INSERT INTO `sys_user_role` VALUES (100881, 2);
INSERT INTO `sys_user_role` VALUES (100882, 2);
INSERT INTO `sys_user_role` VALUES (100883, 2);
INSERT INTO `sys_user_role` VALUES (100884, 2);
INSERT INTO `sys_user_role` VALUES (100885, 2);
INSERT INTO `sys_user_role` VALUES (100886, 2);
INSERT INTO `sys_user_role` VALUES (100887, 2);
INSERT INTO `sys_user_role` VALUES (100888, 2);
INSERT INTO `sys_user_role` VALUES (100889, 2);
INSERT INTO `sys_user_role` VALUES (100890, 2);
INSERT INTO `sys_user_role` VALUES (100891, 2);
INSERT INTO `sys_user_role` VALUES (100892, 2);
INSERT INTO `sys_user_role` VALUES (100893, 2);
INSERT INTO `sys_user_role` VALUES (100894, 2);
INSERT INTO `sys_user_role` VALUES (100895, 2);
INSERT INTO `sys_user_role` VALUES (100896, 2);
INSERT INTO `sys_user_role` VALUES (100897, 2);
INSERT INTO `sys_user_role` VALUES (100898, 2);
INSERT INTO `sys_user_role` VALUES (100899, 2);
INSERT INTO `sys_user_role` VALUES (100900, 2);
INSERT INTO `sys_user_role` VALUES (100901, 2);
INSERT INTO `sys_user_role` VALUES (100902, 2);
INSERT INTO `sys_user_role` VALUES (100903, 2);
INSERT INTO `sys_user_role` VALUES (100905, 2);
INSERT INTO `sys_user_role` VALUES (100906, 2);
INSERT INTO `sys_user_role` VALUES (100907, 2);
INSERT INTO `sys_user_role` VALUES (100908, 2);
INSERT INTO `sys_user_role` VALUES (100909, 2);
INSERT INTO `sys_user_role` VALUES (100910, 2);
INSERT INTO `sys_user_role` VALUES (100911, 2);
INSERT INTO `sys_user_role` VALUES (100912, 2);
INSERT INTO `sys_user_role` VALUES (100913, 2);
INSERT INTO `sys_user_role` VALUES (100914, 2);
INSERT INTO `sys_user_role` VALUES (100915, 2);
INSERT INTO `sys_user_role` VALUES (100916, 2);
INSERT INTO `sys_user_role` VALUES (100917, 2);
INSERT INTO `sys_user_role` VALUES (100918, 2);
INSERT INTO `sys_user_role` VALUES (100919, 2);
INSERT INTO `sys_user_role` VALUES (100920, 2);
INSERT INTO `sys_user_role` VALUES (100921, 2);
INSERT INTO `sys_user_role` VALUES (100922, 2);
INSERT INTO `sys_user_role` VALUES (100923, 2);
INSERT INTO `sys_user_role` VALUES (100924, 2);
INSERT INTO `sys_user_role` VALUES (100925, 2);
INSERT INTO `sys_user_role` VALUES (100926, 2);
INSERT INTO `sys_user_role` VALUES (100927, 2);
INSERT INTO `sys_user_role` VALUES (100928, 2);
INSERT INTO `sys_user_role` VALUES (100929, 2);
INSERT INTO `sys_user_role` VALUES (100930, 2);
INSERT INTO `sys_user_role` VALUES (100931, 2);
INSERT INTO `sys_user_role` VALUES (100932, 2);
INSERT INTO `sys_user_role` VALUES (100933, 2);
INSERT INTO `sys_user_role` VALUES (100934, 2);
INSERT INTO `sys_user_role` VALUES (100935, 2);
INSERT INTO `sys_user_role` VALUES (100936, 2);
INSERT INTO `sys_user_role` VALUES (100937, 2);
INSERT INTO `sys_user_role` VALUES (100938, 2);
INSERT INTO `sys_user_role` VALUES (100939, 2);
INSERT INTO `sys_user_role` VALUES (100940, 2);
INSERT INTO `sys_user_role` VALUES (100941, 2);
INSERT INTO `sys_user_role` VALUES (100942, 2);
INSERT INTO `sys_user_role` VALUES (100943, 2);
INSERT INTO `sys_user_role` VALUES (100944, 2);
INSERT INTO `sys_user_role` VALUES (100945, 2);
INSERT INTO `sys_user_role` VALUES (100946, 2);
INSERT INTO `sys_user_role` VALUES (100947, 2);
INSERT INTO `sys_user_role` VALUES (100948, 2);
INSERT INTO `sys_user_role` VALUES (100949, 2);
INSERT INTO `sys_user_role` VALUES (100950, 2);
INSERT INTO `sys_user_role` VALUES (100951, 2);
INSERT INTO `sys_user_role` VALUES (100952, 2);
INSERT INTO `sys_user_role` VALUES (100953, 2);
INSERT INTO `sys_user_role` VALUES (100954, 2);
INSERT INTO `sys_user_role` VALUES (100955, 2);
INSERT INTO `sys_user_role` VALUES (100956, 2);
INSERT INTO `sys_user_role` VALUES (100957, 2);
INSERT INTO `sys_user_role` VALUES (100958, 2);
INSERT INTO `sys_user_role` VALUES (100959, 2);
INSERT INTO `sys_user_role` VALUES (100960, 2);
INSERT INTO `sys_user_role` VALUES (100961, 2);
INSERT INTO `sys_user_role` VALUES (100962, 2);
INSERT INTO `sys_user_role` VALUES (100963, 2);
INSERT INTO `sys_user_role` VALUES (100964, 2);
INSERT INTO `sys_user_role` VALUES (100965, 2);
INSERT INTO `sys_user_role` VALUES (100966, 2);
INSERT INTO `sys_user_role` VALUES (100967, 2);
INSERT INTO `sys_user_role` VALUES (100968, 2);
INSERT INTO `sys_user_role` VALUES (100969, 2);
INSERT INTO `sys_user_role` VALUES (100970, 2);
INSERT INTO `sys_user_role` VALUES (100971, 2);
INSERT INTO `sys_user_role` VALUES (100972, 2);
INSERT INTO `sys_user_role` VALUES (100973, 2);
INSERT INTO `sys_user_role` VALUES (100974, 2);
INSERT INTO `sys_user_role` VALUES (100975, 2);
INSERT INTO `sys_user_role` VALUES (100976, 2);
INSERT INTO `sys_user_role` VALUES (100977, 2);
INSERT INTO `sys_user_role` VALUES (100978, 2);
INSERT INTO `sys_user_role` VALUES (100979, 2);
INSERT INTO `sys_user_role` VALUES (100980, 2);
INSERT INTO `sys_user_role` VALUES (100981, 2);
INSERT INTO `sys_user_role` VALUES (100982, 2);
INSERT INTO `sys_user_role` VALUES (100983, 2);
INSERT INTO `sys_user_role` VALUES (100984, 2);
INSERT INTO `sys_user_role` VALUES (100985, 2);
INSERT INTO `sys_user_role` VALUES (100986, 2);
INSERT INTO `sys_user_role` VALUES (100987, 2);
INSERT INTO `sys_user_role` VALUES (100988, 2);
INSERT INTO `sys_user_role` VALUES (100989, 2);
INSERT INTO `sys_user_role` VALUES (100990, 2);
INSERT INTO `sys_user_role` VALUES (100991, 2);
INSERT INTO `sys_user_role` VALUES (100992, 2);
INSERT INTO `sys_user_role` VALUES (100993, 2);
INSERT INTO `sys_user_role` VALUES (100994, 2);
INSERT INTO `sys_user_role` VALUES (100995, 2);
INSERT INTO `sys_user_role` VALUES (100996, 2);
INSERT INTO `sys_user_role` VALUES (100997, 2);
INSERT INTO `sys_user_role` VALUES (100998, 2);
INSERT INTO `sys_user_role` VALUES (100999, 2);
INSERT INTO `sys_user_role` VALUES (101000, 2);
INSERT INTO `sys_user_role` VALUES (101001, 2);
INSERT INTO `sys_user_role` VALUES (101002, 2);
INSERT INTO `sys_user_role` VALUES (101003, 2);
INSERT INTO `sys_user_role` VALUES (101004, 1);
INSERT INTO `sys_user_role` VALUES (101004, 2);
INSERT INTO `sys_user_role` VALUES (101005, 2);
INSERT INTO `sys_user_role` VALUES (101005, 4);
INSERT INTO `sys_user_role` VALUES (101006, 2);
INSERT INTO `sys_user_role` VALUES (101007, 2);
INSERT INTO `sys_user_role` VALUES (101008, 2);
INSERT INTO `sys_user_role` VALUES (101009, 2);
INSERT INTO `sys_user_role` VALUES (101010, 2);
INSERT INTO `sys_user_role` VALUES (101011, 2);
INSERT INTO `sys_user_role` VALUES (101012, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
