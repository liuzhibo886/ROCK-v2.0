/*
Navicat MySQL Data Transfer

Source Server         : 开发环境
Source Server Version : 50723
Source Host           : 10.15.6.16:3306
Source Database       : rock-system

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-06-18 10:27:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_authz
-- ----------------------------
DROP TABLE IF EXISTS `sys_authz`;
CREATE TABLE `sys_authz` (
  `authz_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `authz_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限编码',
  `authz_pcode` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限父编码',
  `authz_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限名称',
  `authz_icon` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限图片，菜单权限专用',
  `authz_url` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限url地址，菜单权限专用',
  `authz_sort` int(65) DEFAULT NULL COMMENT '权限排序，升序',
  `authz_type` int(11) DEFAULT NULL COMMENT '权限类型，0菜单，1按钮;2字段',
  `authz_tips` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  `authz_status` int(65) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `authz_is_open` int(11) DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开；菜单权限专用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改人',
  `last_user` json DEFAULT NULL COMMENT '最后修改人',
  `is_del` int(2) DEFAULT '0' COMMENT '是否删除0正常 1 删除',
  PRIMARY KEY (`authz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_authz
-- ----------------------------
INSERT INTO `sys_authz` VALUES ('105', 'system', '0', '系统管理', 'fa-user', '#', '4', '1', null, '1', '1', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('106', 'mgr', 'system', '用户管理', '', '/mgr', '1', '1', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('107', 'mgr_add', 'mgr', '添加用户', null, '/mgr/add', '1', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('108', 'mgr_edit', 'mgr', '修改用户', null, '/mgr/edit', '2', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('109', 'mgr_delete', 'mgr', '删除用户', null, '/mgr/delete', '3', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('110', 'mgr_reset', 'mgr', '重置密码', null, '/mgr/reset', '4', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('111', 'mgr_freeze', 'mgr', '冻结用户', null, '/mgr/freeze', '5', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('112', 'mgr_unfreeze', 'mgr', '解除冻结用户', null, '/mgr/unfreeze', '6', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('113', 'mgr_setRole', 'mgr', '分配角色', null, '/mgr/setRole', '7', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('114', 'role', 'system', '角色管理', null, '/role', '2', '1', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('115', 'role_add', 'role', '添加角色', null, '/role/add', '1', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('116', 'role_edit', 'role', '修改角色', null, '/role/edit', '2', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('117', 'role_remove', 'role', '删除角色', null, '/role/remove', '3', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('118', 'role_setAuthority', 'role', '配置权限', null, '/role/setAuthority', '4', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('119', 'menu', 'system', '菜单管理', null, '/menu', '4', '1', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('120', 'menu_add', 'menu', '添加菜单', null, '/menu/add', '1', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('121', 'menu_edit', 'menu', '修改菜单', null, '/menu/edit', '2', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('122', 'menu_remove', 'menu', '删除菜单', null, '/menu/remove', '3', '0', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('128', 'log', 'system', '业务日志', null, '/log', '6', '1', null, '1', '0', '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('130', 'druid', 'system', '监控管理', null, '/druid', '7', '1', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('131', 'dept', 'system', '部门管理', null, '/dept', '3', '1', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('132', 'dict', 'system', '字典管理', null, '/dict', '4', '1', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('133', 'loginLog', 'system', '登录日志', null, '/loginLog', '6', '1', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('134', 'log_clean', 'log', '清空日志', null, '/log/delLog', '3', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('135', 'dept_add', 'dept', '添加部门', null, '/dept/add', '1', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('136', 'dept_update', 'dept', '修改部门', null, '/dept/update', '1', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('137', 'dept_delete', 'dept', '删除部门', null, '/dept/delete', '1', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('138', 'dict_add', 'dict', '添加字典', null, '/dict/add', '1', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('139', 'dict_update', 'dict', '修改字典', null, '/dict/update', '1', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('140', 'dict_delete', 'dict', '删除字典', null, '/dict/delete', '1', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('141', 'notice', 'system', '通知管理', null, '/notice', '9', '1', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('142', 'notice_add', 'notice', '添加通知', null, '/notice/add', '1', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('143', 'notice_update', 'notice', '修改通知', null, '/notice/update', '2', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('144', 'notice_delete', 'notice', '删除通知', null, '/notice/delete', '3', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('145', 'hello', '0', '通知', 'fa-rocket', '/notice/hello', '1', '1', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('148', 'code', '0', '代码生成', 'fa-code', '/code', '3', '1', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('149', 'api_mgr', '0', '接口文档', 'fa-leaf', '/swagger-ui.html', '2', '1', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('150', 'to_menu_edit', 'menu', '菜单编辑跳转', '', '/menu/menu_edit', '4', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('151', 'menu_list', 'menu', '菜单列表', '', '/menu/list', '5', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('152', 'to_dept_update', 'dept', '修改部门跳转', '', '/dept/dept_update', '4', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('153', 'dept_list', 'dept', '部门列表', '', '/dept/list', '5', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('154', 'dept_detail', 'dept', '部门详情', '', '/dept/detail', '6', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('155', 'to_dict_edit', 'dict', '修改菜单跳转', '', '/dict/dict_edit', '4', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('156', 'dict_list', 'dict', '字典列表', '', '/dict/list', '5', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('157', 'dict_detail', 'dict', '字典详情', '', '/dict/detail', '6', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('158', 'log_list', 'log', '日志列表', '', '/log/list', '2', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('159', 'log_detail', 'log', '日志详情', '', '/log/detail', '3', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('160', 'del_login_log', 'loginLog', '清空登录日志', '', '/loginLog/delLoginLog', '1', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('161', 'login_log_list', 'loginLog', '登录日志列表', '', '/loginLog/list', '2', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('162', 'to_role_edit', 'role', '修改角色跳转', '', '/role/role_edit', '5', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('163', 'to_role_assign', 'role', '角色分配跳转', '', '/role/role_assign', '6', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('164', 'role_list', 'role', '角色列表', '', '/role/list', '7', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('165', 'to_assign_role', 'mgr', '分配角色跳转', '', '/mgr/role_assign', '8', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('166', 'to_user_edit', 'mgr', '编辑用户跳转', '', '/mgr/user_edit', '9', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('167', 'mgr_list', 'mgr', '用户列表', '', '/mgr/list', '10', '0', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('168', 'expense', '0', '报销管理', 'fa-clone', '#', '5', '1', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('169', 'expense_fill', 'expense', '报销申请', '', '/expense', '1', '1', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');
INSERT INTO `sys_authz` VALUES ('170', 'expense_progress', 'expense', '报销审批', '', '/process', '2', '1', null, '1', null, '2019-05-16 09:57:26', '2019-05-16 09:57:26', null, '0');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '排序',
  `pid` int(11) DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) DEFAULT NULL COMMENT '父级ids',
  `simplename` varchar(45) DEFAULT NULL COMMENT '简称',
  `fullname` varchar(255) DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `version` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('24', '1', '0', '[0],', '总公司', '总公司', '', null);
INSERT INTO `sys_dept` VALUES ('25', '2', '24', '[0],[24],', '开发部', '开发部', '', null);
INSERT INTO `sys_dept` VALUES ('26', '3', '24', '[0],[24],', '运营部', '运营部', '', null);
INSERT INTO `sys_dept` VALUES ('27', '4', '24', '[0],[24],', '战略部', '战略部', '', null);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_id` bigint(17) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dict_code` varchar(20) DEFAULT NULL COMMENT '字典编码',
  `dict_pkey` bigint(17) DEFAULT '0' COMMENT 'pid,父节点为0',
  `dict_key` bigint(17) DEFAULT NULL COMMENT '字典key值',
  `dict_value` varchar(50) DEFAULT NULL COMMENT '字典value值',
  `dict_level` int(10) DEFAULT NULL COMMENT '字典级别，0 开始',
  `dict_max_level` int(10) DEFAULT NULL COMMENT '最大等级',
  `dict_text` text COMMENT '文本内容，根据需要填写',
  `sort` int(10) DEFAULT '0' COMMENT '排序字段，升序',
  `is_del` int(2) DEFAULT '0' COMMENT '是否删除0正常 1 删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改人',
  `last_user` json DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19268 DEFAULT CHARSET=utf8mb4 COMMENT='系统字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('19258', 'SEX', '-2', '-1', '性别', '0', '1', null, '0', '0', '2019-06-10 22:13:54', '2019-06-10 22:13:54', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}');
INSERT INTO `sys_dict` VALUES ('19259', 'SEX', '-1', '0', '男', '1', '1', null, '0', '0', '2019-06-10 22:14:06', '2019-06-10 22:14:06', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}');
INSERT INTO `sys_dict` VALUES ('19260', 'SEX', '-1', '1', '女', '1', '1', null, '0', '0', '2019-06-10 22:14:12', '2019-06-10 22:14:12', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}');
INSERT INTO `sys_dict` VALUES ('19261', 'MSG_TYPE', '-2', '-1', '消息类型', '0', '1', null, '0', '0', '2019-06-10 22:17:51', '2019-06-10 22:17:51', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}');
INSERT INTO `sys_dict` VALUES ('19262', 'MSG_TYPE', '-1', '0', '警告消息', '1', '1', null, '0', '0', '2019-06-10 22:18:20', '2019-06-10 22:18:20', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}');
INSERT INTO `sys_dict` VALUES ('19263', 'MSG_TYPE', '-1', '1', '普通消息', '1', '1', null, '0', '0', '2019-06-10 22:18:29', '2019-06-10 22:18:29', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}');
INSERT INTO `sys_dict` VALUES ('19264', 'MSG_STATUS', '-2', '-1', '消息状态', '0', '1', null, '0', '0', '2019-06-10 22:18:59', '2019-06-10 22:18:59', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}');
INSERT INTO `sys_dict` VALUES ('19265', 'MSG_STATUS', '-1', '0', 'ding', '1', '1', null, '0', '0', '2019-06-10 22:19:17', '2019-06-10 22:25:53', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}');
INSERT INTO `sys_dict` VALUES ('19266', 'MSG_STATUS', '-1', '1', '未读', '1', '1', null, '0', '0', '2019-06-10 22:19:35', '2019-06-10 22:19:35', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}');
INSERT INTO `sys_dict` VALUES ('19267', 'MSG_STATUS', '-1', '2', '已读', '1', '1', null, '0', '0', '2019-06-10 22:19:41', '2019-06-10 22:19:41', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}');

-- ----------------------------
-- Table structure for sys_expense
-- ----------------------------
DROP TABLE IF EXISTS `sys_expense`;
CREATE TABLE `sys_expense` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` decimal(20,2) DEFAULT NULL COMMENT '报销金额',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT NULL COMMENT '状态: 1.待提交  2:待审核   3.审核通过 4:驳回',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `processId` varchar(255) DEFAULT NULL COMMENT '流程定义id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='报销表';

-- ----------------------------
-- Records of sys_expense
-- ----------------------------

-- ----------------------------
-- Table structure for sys_flow_cfg
-- ----------------------------
DROP TABLE IF EXISTS `sys_flow_cfg`;
CREATE TABLE `sys_flow_cfg` (
  `flow_cfg_id` bigint(17) NOT NULL AUTO_INCREMENT COMMENT '流ID',
  `flow_cfg_code` varchar(30) DEFAULT NULL COMMENT '流编码，不可修改',
  `flow_cfg_name` varchar(30) DEFAULT NULL COMMENT '流名称',
  `flow_cfg_tips` varchar(30) DEFAULT NULL COMMENT '提示',
  `flow_cfg_text` text COMMENT '额外文本数据',
  `user_id` bigint(17) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户姓名',
  `user_p_id` bigint(17) DEFAULT NULL COMMENT '用户上级用户ID',
  `user_p_name` varchar(30) DEFAULT NULL COMMENT '用户父级姓名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改人',
  `last_user` json DEFAULT NULL COMMENT '最后修改人',
  `is_del` int(2) DEFAULT '0' COMMENT '是否删除0正常 1 删除',
  PRIMARY KEY (`flow_cfg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='流配置表（全局表）';

-- ----------------------------
-- Records of sys_flow_cfg
-- ----------------------------
INSERT INTO `sys_flow_cfg` VALUES ('3', 'test', '测试', '', '', '-1', '顶级', null, null, '2019-06-17 16:41:25', '2019-06-17 16:41:25', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}', '0');
INSERT INTO `sys_flow_cfg` VALUES ('4', 'cs', 'aa', '提示', '文本数据', '-1', '顶级', null, null, '2019-06-17 16:44:35', '2019-06-17 16:44:35', '{\"userId\": 1, \"userName\": \"张三\", \"userAccount\": \"admin\"}', '0');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logname` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) DEFAULT NULL COMMENT '管理员id',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL COMMENT '是否执行成功',
  `message` text COMMENT '具体消息',
  `ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='登录记录';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('1', '登陆日志', '1', '2019-06-10 17:11:56', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('2', '登陆日志', '1', '2019-06-10 17:21:59', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('3', '登陆日志', '1', '2019-06-10 17:50:25', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('4', '登陆日志', '1', '2019-06-10 17:54:49', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('5', '登陆日志', '1', '2019-06-10 17:59:47', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('6', '登陆日志', '1', '2019-06-10 18:12:15', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('7', '登陆日志', '1', '2019-06-11 09:38:06', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('8', '登陆日志', '1', '2019-06-11 10:18:07', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('9', '登陆日志', '1', '2019-06-11 10:34:37', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('10', '登陆日志', '1', '2019-06-11 10:36:04', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('11', '登陆日志', '1', '2019-06-11 10:43:13', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('12', '登陆日志', '1', '2019-06-11 11:20:31', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('13', '登陆日志', '1', '2019-06-11 13:44:11', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('14', '登陆日志', '1', '2019-06-11 16:10:05', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('15', '登陆日志', '1', '2019-06-11 16:23:56', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('16', '登陆日志', '1', '2019-06-11 16:26:23', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('17', '登陆日志', '1', '2019-06-11 16:52:13', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('18', '登陆日志', '1', '2019-06-11 17:02:54', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('19', '登陆日志', '1', '2019-06-11 18:16:35', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('20', '登陆日志', '1', '2019-06-11 18:52:51', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('21', '登陆日志', '1', '2019-06-11 19:04:17', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('22', '登陆日志', '1', '2019-06-12 11:07:41', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('23', '登陆日志', '1', '2019-06-12 11:09:07', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('24', '登陆日志', '1', '2019-06-12 11:10:30', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('25', '退出日志', '1', '2019-06-12 11:10:33', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('26', '登陆日志', '1', '2019-06-12 11:10:38', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('27', '登陆日志', '1', '2019-06-12 19:12:59', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('28', '登陆日志', '1', '2019-06-17 11:21:48', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('29', '登陆日志', '1', '2019-06-17 11:26:14', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('30', '登陆日志', '1', '2019-06-17 11:29:50', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('31', '登陆日志', '1', '2019-06-17 11:31:53', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('32', '登陆日志', '1', '2019-06-17 13:42:32', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('33', '退出日志', '1', '2019-06-17 13:49:16', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('34', '登陆日志', '1', '2019-06-17 13:49:20', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('35', '退出日志', '1', '2019-06-17 13:52:29', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('36', '登陆日志', '1', '2019-06-17 13:52:33', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('37', '退出日志', '1', '2019-06-17 14:15:39', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('38', '登陆日志', '1', '2019-06-17 14:15:43', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('39', '登陆日志', '1', '2019-06-17 14:19:23', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('40', '退出日志', '1', '2019-06-17 14:19:42', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('41', '登陆日志', '1', '2019-06-17 14:19:46', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('42', '登陆日志', '1', '2019-06-17 14:34:11', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('43', '退出日志', '1', '2019-06-17 14:44:20', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('44', '登陆日志', '1', '2019-06-17 14:44:24', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('45', '退出日志', '1', '2019-06-17 15:23:45', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('46', '登陆日志', '1', '2019-06-17 15:23:49', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('47', '退出日志', '1', '2019-06-17 15:25:34', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('48', '登陆日志', '1', '2019-06-17 15:25:38', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('49', '登陆日志', '1', '2019-06-17 15:31:39', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('50', '退出日志', '1', '2019-06-17 15:32:00', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('51', '登陆日志', '1', '2019-06-17 15:32:05', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('52', '登陆日志', '1', '2019-06-17 16:35:16', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('53', '登陆日志', '1', '2019-06-17 17:10:14', '成功', '', '127.0.0.1');
INSERT INTO `sys_login_log` VALUES ('54', '登陆日志', '1', '2019-06-17 18:26:16', '成功', '', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `num` int(65) DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(65) DEFAULT NULL COMMENT '菜单层级',
  `ismenu` int(11) DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `tips` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(65) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `isopen` int(11) DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('105', 'system', '0', '[0],', '系统管理', 'fa-user', '#', '4', '1', '1', null, '1', '1');
INSERT INTO `sys_menu` VALUES ('106', 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', '1', '2', '1', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('107', 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', '', '/mgr/add', '1', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('108', 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', null, '/mgr/edit', '2', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('109', 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', null, '/mgr/delete', '3', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('110', 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', null, '/mgr/reset', '4', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('111', 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', null, '/mgr/freeze', '5', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('112', 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', null, '/mgr/unfreeze', '6', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('113', 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', null, '/mgr/setRole', '7', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('114', 'role', 'system', '[0],[system],', '角色管理', null, '/role', '2', '2', '1', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('115', 'role_add', 'role', '[0],[system],[role],', '添加角色', null, '/role/add', '1', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('116', 'role_edit', 'role', '[0],[system],[role],', '修改角色', null, '/role/edit', '2', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('117', 'role_remove', 'role', '[0],[system],[role],', '删除角色', null, '/role/remove', '3', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('118', 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', null, '/role/setAuthority', '4', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('119', 'menu', 'system', '[0],[system],', '菜单管理', null, '/menu', '4', '2', '1', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('120', 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', null, '/menu/add', '1', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('121', 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', null, '/menu/edit', '2', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('122', 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', null, '/menu/remove', '3', '3', '0', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('128', 'log', 'system', '[0],[system],', '业务日志', null, '/log', '6', '2', '1', null, '1', '0');
INSERT INTO `sys_menu` VALUES ('130', 'druid', 'system', '[0],[system],', '监控管理', null, '/druid', '7', '2', '1', null, '1', null);
INSERT INTO `sys_menu` VALUES ('131', 'dept', 'system', '[0],[system],', '部门管理', null, '/dept', '3', '2', '1', null, '1', null);
INSERT INTO `sys_menu` VALUES ('132', 'dict', 'system', '[0],[system],', '字典管理', '', '/rock/sys/dict', '4', '2', '1', null, '1', null);
INSERT INTO `sys_menu` VALUES ('133', 'loginLog', 'system', '[0],[system],', '登录日志', null, '/loginLog', '6', '2', '1', null, '1', null);
INSERT INTO `sys_menu` VALUES ('134', 'log_clean', 'log', '[0],[system],[log],', '清空日志', null, '/log/delLog', '3', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('135', 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', null, '/dept/add', '1', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('136', 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', null, '/dept/update', '1', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('137', 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', null, '/dept/delete', '1', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('138', '/rock/sys/dict/dict_add', 'dict', '[0],[system],[dict],', '添加字典', '', '/rock/sys/dict/add', '1', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('139', '/rock/sys/dict/dict_update', 'dict', '[0],[system],[dict],', '修改字典', '', '/rock/sys/dict/update', '1', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('140', '/rock/sys/dict/dict_delete', 'dict', '[0],[system],[dict],', '删除字典', '', '/rock/sys/dict/delete', '1', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('141', '/rock/sys/notice', 'system', '[0],[system],', '通知管理', '', '/rock/sys/notice', '9', '2', '1', null, '1', null);
INSERT INTO `sys_menu` VALUES ('142', '/rock/sys/notice/notice_add', '/rock/sys/notice', '[0],[system],[/rock/sys/notice],', '添加通知', '', '/rock/sys/notice/add', '1', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('143', '/rock/sys/notice/notice_update', '/rock/sys/notice', '[0],[system],[/rock/sys/notice],', '修改通知', '', '/rock/sys/notice/update', '2', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('144', '/rock/sys/notice/notice_delete', '/rock/sys/notice', '[0],[system],[/rock/sys/notice],', '删除通知', '', '/rock/sys/notice/delete', '3', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('145', '/rock/sys/notice/hello', '0', '[0],', '通知', 'fa-rocket', '/rock/sys/notice/hello', '1', '1', '1', null, '1', null);
INSERT INTO `sys_menu` VALUES ('150', 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', '4', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('151', 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', '5', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('152', 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', '4', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('153', 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', '5', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('154', 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', '6', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('155', 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', '4', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('156', 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', '5', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('157', 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', '6', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('158', 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', '2', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('159', 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', '3', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('160', 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', '1', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('161', 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', '2', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('162', 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', '5', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('163', 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', '6', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('164', 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', '7', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('165', 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', '8', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('166', 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', '9', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('167', 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', '10', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('171', '/rock/sys/flowCfg', 'system', '[0],[system],', '上下级管理', '', '/rock/sys/flowCfg', '0', '2', '1', null, '1', null);
INSERT INTO `sys_menu` VALUES ('172', '/rock/sys/flowCfg/add', '/rock/sys/flowCfg', '[0],[system],[/rock/sys/flowCfg],', '新增上下级管理', '', '/rock/sys/flowCfg/add', '0', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('173', '/rock/sys/flowCfg/update', '/rock/sys/flowCfg', '[0],[system],[/rock/sys/flowCfg],', '修改上下级管理', '', '/rock/sys/flowCfg/update', '0', '3', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('174', '/rock/sys/flowCfg/delete', '/rock/sys/flowCfg', '[0],[system],[/rock/sys/flowCfg],', '删除上下级管理', '', '/rock/sys/flowCfg/delete', '0', '3', '0', null, '1', null);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` bigint(17) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(17) DEFAULT NULL COMMENT '所属用户ID',
  `user_name` varchar(45) DEFAULT NULL COMMENT '名字',
  `notice_title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标题',
  `notice_type` int(2) DEFAULT NULL COMMENT '消息类型，0警告消息，1普通消息',
  `notice_status` int(2) DEFAULT NULL COMMENT '消息类型，0ding住，1未读，2已读',
  `notice_text` text CHARACTER SET utf8mb4 COMMENT '内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改人',
  `last_user` json DEFAULT NULL COMMENT '最后修改人',
  `is_del` int(2) DEFAULT '0' COMMENT '是否删除0正常 1 删除',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 COMMENT='通知表(user_id分库)';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logtype` varchar(255) DEFAULT NULL COMMENT '日志类型',
  `logname` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) DEFAULT NULL COMMENT '用户id',
  `classname` varchar(255) DEFAULT NULL COMMENT '类名称',
  `method` text COMMENT '方法名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL COMMENT '是否成功',
  `message` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log` VALUES ('1', '1', '清空业务日志', '1', 'com.lzb.rock.system.admin.controller.LogController', 'delLog', null, null, null);
INSERT INTO `sys_operation_log` VALUES ('2', '1', '配置权限', '1', 'com.lzb.rock.system.admin.controller.RoleController', 'setAuthority', null, null, null);

-- ----------------------------
-- Table structure for sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` bigint(11) DEFAULT NULL COMMENT '菜单id',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改人',
  `last_user` json DEFAULT NULL COMMENT '最后修改人',
  `is_del` int(2) DEFAULT '0' COMMENT '是否删除0正常 1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4055 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_relation
-- ----------------------------
INSERT INTO `sys_relation` VALUES ('3815', '105', '5', '2019-06-09 23:19:29', '2019-06-09 23:19:29', null, '0');
INSERT INTO `sys_relation` VALUES ('3816', '106', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3817', '107', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3818', '108', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3819', '109', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3820', '110', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3821', '111', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3822', '112', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3823', '113', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3824', '114', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3825', '115', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3826', '116', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3827', '117', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3828', '118', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3829', '119', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3830', '120', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3831', '121', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3832', '122', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3833', '150', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3834', '151', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3835', '145', '5', '2019-06-09 23:19:30', '2019-06-09 23:19:30', null, '0');
INSERT INTO `sys_relation` VALUES ('3998', '105', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('3999', '106', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4000', '107', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4001', '108', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4002', '109', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4003', '110', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4004', '111', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4005', '112', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4006', '113', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4007', '165', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4008', '166', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4009', '167', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4010', '114', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4011', '115', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4012', '116', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4013', '117', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4014', '118', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4015', '162', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4016', '163', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4017', '164', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4018', '119', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4019', '120', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4020', '121', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4021', '122', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4022', '150', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4023', '151', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4024', '128', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4025', '134', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4026', '158', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4027', '159', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4028', '130', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4029', '131', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4030', '135', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4031', '136', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4032', '137', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4033', '152', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4034', '153', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4035', '154', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4036', '132', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4037', '138', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4038', '139', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4039', '140', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4040', '155', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4041', '156', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4042', '157', '1', '2019-06-17 15:31:56', '2019-06-17 15:31:56', null, '0');
INSERT INTO `sys_relation` VALUES ('4043', '133', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');
INSERT INTO `sys_relation` VALUES ('4044', '160', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');
INSERT INTO `sys_relation` VALUES ('4045', '161', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');
INSERT INTO `sys_relation` VALUES ('4046', '141', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');
INSERT INTO `sys_relation` VALUES ('4047', '142', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');
INSERT INTO `sys_relation` VALUES ('4048', '143', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');
INSERT INTO `sys_relation` VALUES ('4049', '144', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');
INSERT INTO `sys_relation` VALUES ('4050', '171', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');
INSERT INTO `sys_relation` VALUES ('4051', '172', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');
INSERT INTO `sys_relation` VALUES ('4052', '173', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');
INSERT INTO `sys_relation` VALUES ('4053', '174', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');
INSERT INTO `sys_relation` VALUES ('4054', '145', '1', '2019-06-17 15:31:57', '2019-06-17 15:31:57', null, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `pid` int(11) DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `deptid` int(11) DEFAULT NULL COMMENT '部门名称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `version` int(11) DEFAULT NULL COMMENT '保留字段(暂时没用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '1', '0', '超级管理员', '24', 'administrator', '1');
INSERT INTO `sys_role` VALUES ('5', '2', '1', '临时', '26', 'temp', null);

-- ----------------------------
-- Table structure for sys_session
-- ----------------------------
DROP TABLE IF EXISTS `sys_session`;
CREATE TABLE `sys_session` (
  `session_id` varchar(200) NOT NULL COMMENT 'Sessoin的id',
  `session_text` text COMMENT 'Session的序列化对象',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改人',
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='session 记录表';

-- ----------------------------
-- Records of sys_session
-- ----------------------------
INSERT INTO `sys_session` VALUES ('shiro-session-8d1dc136-276d-4c82-a952-f03ee0bdb4e0', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIA23QAJDhkMWRjMTM2LTI3NmQtNGM4Mi1hOTUyLWYwM2VlMGJkYjRlMHNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAWrFU699eHNxAH4AA3cIAAABasVTvwR4dxMAAAAAABt3QAAJMTI3LjAuMC4xc3IAEWphdmEudXRpbC5IYXNoTWFwBQfawcMWYNEDAAJGAApsb2FkRmFjdG9ySQAJdGhyZXNob2xkeHA/QAAAAAAADHcIAAAAEAAAAAF0ABFzaGlyb1NhdmVkUmVxdWVzdHNyACZvcmcuYXBhY2hlLnNoaXJvLndlYi51dGlsLlNhdmVkUmVxdWVzdK/OPK15gsq6AgADTAAGbWV0aG9kdAASTGphdmEvbGFuZy9TdHJpbmc7TAALcXVlcnlTdHJpbmdxAH4ACkwACnJlcXVlc3RVUklxAH4ACnhwdAADR0VUcHQAAS94eA==', '2019-05-17 18:32:57', '2019-05-17 18:33:01');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) DEFAULT NULL COMMENT '名字',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `roleid` varchar(255) DEFAULT NULL COMMENT '角色id',
  `deptid` int(11) DEFAULT NULL COMMENT '部门id',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'girl.gif', 'admin', 'ecfadcde9305f8891bcfe5a1e28c253e', '8pgby', '张三', '2017-05-05 00:00:00', '0', 'sn93@qq.com', '18200000000', '1,5', '24', '1', '2016-01-29 08:49:53', '25');
INSERT INTO `sys_user` VALUES ('44', null, 'test', '45abb7879f6a8268f1ef600e6038ac73', 'ssts3', 'test', '2017-05-01 00:00:00', '0', 'abc@123.com', '', '5', '26', '1', '2017-05-16 20:33:37', null);
INSERT INTO `sys_user` VALUES ('45', null, 'boss', '71887a5ad666a18f709e1d4e693d5a35', '1f7bf', '老板', '2017-12-04 00:00:00', '1', '', '', '1,5', '24', '1', '2017-12-04 22:24:02', null);
INSERT INTO `sys_user` VALUES ('46', null, 'manager', 'b53cac62e7175637d4beb3b16b2f7915', 'j3cs9', '经理', '2017-12-04 00:00:00', '1', '', '', '1', '24', '1', '2017-12-04 22:24:24', null);
SET FOREIGN_KEY_CHECKS=1;
