/*
 Navicat Premium Dump SQL

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : sg_blog

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 20/12/2024 11:27:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sg_article
-- ----------------------------
DROP TABLE IF EXISTS `sg_article`;
CREATE TABLE `sg_article`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文章内容',
  `summary` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章摘要',
  `category_id` bigint NULL DEFAULT NULL COMMENT '所属分类id',
  `thumbnail` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缩略图',
  `is_top` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否置顶（0否，1是）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '状态（0已发布，1草稿）',
  `view_count` bigint NULL DEFAULT 0 COMMENT '访问量',
  `is_comment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '是否允许评论 1是，0否',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sg_article
-- ----------------------------
INSERT INTO `sg_article` VALUES (1, 'SpringSecurity从入门到精通', '## 课程介绍\n![image20211219121555979.png](https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/e7131718e9e64faeaf3fe16404186eb4.png)\n\n## 0. 简介1\n\n​	**Spring Security** 是 Spring 家族中的一个安全管理框架。相比与另外一个安全框架**Shiro**，它提供了更丰富的功能，社区资源也比Shiro丰富。\n\n​	一般来说中大型的项目都是使用**SpringSecurity** 来做安全框架。小项目有Shiro的比较多，因为相比与SpringSecurity，Shiro的上手更加的简单。\n\n​	 一般Web应用的需要进行**认证**和**授权**。\n\n​		**认证：验证当前访问系统的是不是本系统的用户，并且要确认具体是哪个用户**\n\n​		**授权：经过认证后判断当前用户是否有权限进行某个操作**\n\n​	而认证和授权也是SpringSecurity作为安全框架的核心功能。\n\n\n\n## 1. 快速入门\n\n### 1.1 准备工作\n\n​	我们先要搭建一个简单的SpringBoot工程\n\n① 设置父工程 添加依赖\n\n~~~~\n    <parent>\n        <groupId>org.springframework.boot</groupId>\n        <artifactId>spring-boot-starter-parent</artifactId>\n        <version>2.5.0</version>\n    </parent>\n    <dependencies>\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-web</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>org.projectlombok</groupId>\n            <artifactId>lombok</artifactId>\n            <optional>true</optional>\n        </dependency>\n    </dependencies>\n~~~~\n\n② 创建启动类\n\n~~~~\n@SpringBootApplication\npublic class SecurityApplication {\n\n    public static void main(String[] args) {\n        SpringApplication.run(SecurityApplication.class,args);\n    }\n}\n\n~~~~\n\n③ 创建Controller\n\n~~~~java\n\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@RestController\npublic class HelloController {\n\n    @RequestMapping(\"/hello\")\n    public String hello(){\n        return \"hello\";\n    }\n}\n\n~~~~\n\n\n\n### 1.2 引入SpringSecurity\n\n​	在SpringBoot项目中使用SpringSecurity我们只需要引入依赖即可实现入门案例。\n\n~~~~xml\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-security</artifactId>\n        </dependency>\n~~~~\n\n​	引入依赖后我们在尝试去访问之前的接口就会自动跳转到一个SpringSecurity的默认登陆页面，默认用户名是user,密码会输出在控制台。\n\n​	必须登陆之后才能对接口进行访问。\n\n\n\n## 2. 认证\n\n### 2.1 登陆校验流程\n![image20211215094003288.png](https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/414a87eeed344828b5b00ffa80178958.png)', 'SpringSecurity框架教程-Spring Security+JWT实现项目级前端分离认证授权', 1, 'https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/948597e164614902ab1662ba8452e106.png', '1', '0', 110, '0', NULL, '2022-01-23 23:20:11', NULL, NULL, 0);
INSERT INTO `sg_article` VALUES (2, 'weq', 'adadaeqe', 'adad', 2, 'https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/fd2e9460c58a4af3bbeae5d9ed581688.png', '1', '0', 22, '0', NULL, '2022-01-21 14:58:30', NULL, NULL, 1);
INSERT INTO `sg_article` VALUES (3, 'dad', 'asdasda', 'sadad', 1, 'https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/737a0ed0b8ea430d8700a12e76aa1cd1.png', '1', '0', 33, '0', NULL, '2022-01-18 14:58:34', NULL, NULL, 1);
INSERT INTO `sg_article` VALUES (5, 'sdad', '![Snipaste_20220115_165812.png](https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/1d9d283f5d874b468078b183e4b98b71.png)\r\n\r\n## sda \r\n\r\n222\r\n### sdasd newnewnew', NULL, 2, '', '1', '0', 45, '0', NULL, '2022-01-17 14:58:37', NULL, NULL, 0);
INSERT INTO `sg_article` VALUES (8, 'cnmcnm', '到处都是超市菜市场的菜', 'dcdc', 1, 'http://so9rqxn4p.hn-bkt.clouddn.com/2024/12/16/ce3981555805433ea8a8577c6a0d1bc4.png', '0', '0', 0, '0', 1, '2024-12-16 17:23:08', NULL, '2024-12-17 11:31:45', 0);
INSERT INTO `sg_article` VALUES (9, 'dsdfsfdfsfdsfdsfdsfdsfdsfdsf', 'fsdfdsfdsfsfdsfdsfsf', 'fsdfdsfdsfdsfdsfdsf', NULL, '', '1', '0', 0, '0', 1, '2024-12-17 11:34:20', NULL, '2024-12-17 11:34:26', 1);
INSERT INTO `sg_article` VALUES (10, '', '', NULL, NULL, '', '1', '1', 0, '0', 1, '2024-12-17 15:04:05', NULL, '2024-12-19 09:05:29', 1);

-- ----------------------------
-- Table structure for sg_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `sg_article_tag`;
CREATE TABLE `sg_article_tag`  (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `tag_id` bigint NOT NULL DEFAULT 0 COMMENT '标签id',
  PRIMARY KEY (`article_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章标签关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sg_article_tag
-- ----------------------------
INSERT INTO `sg_article_tag` VALUES (1, 4);
INSERT INTO `sg_article_tag` VALUES (2, 1);
INSERT INTO `sg_article_tag` VALUES (2, 4);
INSERT INTO `sg_article_tag` VALUES (3, 4);
INSERT INTO `sg_article_tag` VALUES (3, 5);
INSERT INTO `sg_article_tag` VALUES (8, 1);

-- ----------------------------
-- Table structure for sg_category
-- ----------------------------
DROP TABLE IF EXISTS `sg_category`;
CREATE TABLE `sg_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名',
  `pid` bigint NULL DEFAULT -1 COMMENT '父分类id，如果没有父分类为-1',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sg_category
-- ----------------------------
INSERT INTO `sg_category` VALUES (1, 'java', -1, 'wsd', '0', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sg_category` VALUES (2, 'PHP', -1, 'wsd', '0', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sg_category` VALUES (15, 'C++', -1, '草泥马', '0', NULL, '2024-12-18 16:03:27', NULL, NULL, 0);
INSERT INTO `sg_category` VALUES (16, 'fuck', -1, NULL, '0', NULL, '2024-12-18 16:13:15', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sg_comment
-- ----------------------------
DROP TABLE IF EXISTS `sg_comment`;
CREATE TABLE `sg_comment`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '评论类型（0代表文章评论，1代表友链评论）',
  `article_id` bigint NULL DEFAULT NULL COMMENT '文章id',
  `root_id` bigint NULL DEFAULT -1 COMMENT '根评论id',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  `to_comment_user_id` bigint NULL DEFAULT -1 COMMENT '所回复的目标评论的userid',
  `to_comment_id` bigint NULL DEFAULT -1 COMMENT '回复目标评论id',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sg_comment
-- ----------------------------
INSERT INTO `sg_comment` VALUES (1, '0', 1, -1, 'asS', -1, -1, 1, '2022-01-29 07:59:22', 1, '2022-01-29 07:59:22', 0);
INSERT INTO `sg_comment` VALUES (2, '0', 1, -1, '[哈哈]SDAS', -1, -1, 1, '2022-01-29 08:01:24', 1, '2022-01-29 08:01:24', 0);
INSERT INTO `sg_comment` VALUES (3, '0', 1, -1, '是大多数', -1, -1, 1, '2022-01-29 16:07:24', 1, '2022-01-29 16:07:24', 0);
INSERT INTO `sg_comment` VALUES (4, '0', 1, -1, '撒大声地', -1, -1, 1, '2022-01-29 16:12:09', 1, '2022-01-29 16:12:09', 0);
INSERT INTO `sg_comment` VALUES (5, '0', 1, -1, '你再说什么', -1, -1, 1, '2022-01-29 18:19:56', 1, '2022-01-29 18:19:56', 0);
INSERT INTO `sg_comment` VALUES (6, '0', 1, -1, 'hffd', -1, -1, 1, '2022-01-29 22:13:52', 1, '2022-01-29 22:13:52', 0);
INSERT INTO `sg_comment` VALUES (9, '0', 1, 2, '你说什么', 1, 2, 1, '2022-01-29 22:18:40', 1, '2022-01-29 22:18:40', 0);
INSERT INTO `sg_comment` VALUES (10, '0', 1, 2, '哈哈哈哈[哈哈]', 1, 9, 1, '2022-01-29 22:29:15', 1, '2022-01-29 22:29:15', 0);
INSERT INTO `sg_comment` VALUES (11, '0', 1, 2, 'we全文', 1, 10, 3, '2022-01-29 22:29:55', 1, '2022-01-29 22:29:55', 0);
INSERT INTO `sg_comment` VALUES (12, '0', 1, -1, '王企鹅', -1, -1, 1, '2022-01-29 22:30:20', 1, '2022-01-29 22:30:20', 0);
INSERT INTO `sg_comment` VALUES (13, '0', 1, -1, '什么阿是', -1, -1, 1, '2022-01-29 22:30:56', 1, '2022-01-29 22:30:56', 0);
INSERT INTO `sg_comment` VALUES (14, '0', 1, -1, '新平顶山', -1, -1, 1, '2022-01-29 22:32:51', 1, '2022-01-29 22:32:51', 0);
INSERT INTO `sg_comment` VALUES (15, '0', 1, -1, '2222', -1, -1, 1, '2022-01-29 22:34:38', 1, '2022-01-29 22:34:38', 0);
INSERT INTO `sg_comment` VALUES (16, '0', 1, 2, '3333', 1, 11, 1, '2022-01-29 22:34:47', 1, '2022-01-29 22:34:47', 0);
INSERT INTO `sg_comment` VALUES (17, '0', 1, 2, '回复weqedadsd', 3, 11, 1, '2022-01-29 22:38:00', 1, '2022-01-29 22:38:00', 0);
INSERT INTO `sg_comment` VALUES (18, '0', 1, -1, 'sdasd', -1, -1, 1, '2022-01-29 23:18:19', 1, '2022-01-29 23:18:19', 0);
INSERT INTO `sg_comment` VALUES (19, '0', 1, -1, '111', -1, -1, 1, '2022-01-29 23:22:23', 1, '2022-01-29 23:22:23', 0);
INSERT INTO `sg_comment` VALUES (20, '0', 1, 1, '你说啥？', 1, 1, 1, '2022-01-30 10:06:21', 1, '2022-01-30 10:06:21', 0);
INSERT INTO `sg_comment` VALUES (21, '0', 1, -1, '友链添加个呗', -1, -1, 1, '2022-01-30 10:06:50', 1, '2022-01-30 10:06:50', 0);
INSERT INTO `sg_comment` VALUES (22, '1', 1, -1, '友链评论2', -1, -1, 1, '2022-01-30 10:08:28', 1, '2022-01-30 10:08:28', 0);
INSERT INTO `sg_comment` VALUES (23, '1', 1, 22, '回复友链评论3', 1, 22, 1, '2022-01-30 10:08:50', 1, '2022-01-30 10:08:50', 0);
INSERT INTO `sg_comment` VALUES (24, '1', 1, -1, '友链评论4444', -1, -1, 1, '2022-01-30 10:09:03', 1, '2022-01-30 10:09:03', 0);
INSERT INTO `sg_comment` VALUES (25, '1', 1, 22, '收到的', 1, 22, 1, '2022-01-30 10:13:28', 1, '2022-01-30 10:13:28', 0);
INSERT INTO `sg_comment` VALUES (26, '0', 1, -1, 'sda', -1, -1, 1, '2022-01-30 10:39:05', 1, '2022-01-30 10:39:05', 0);
INSERT INTO `sg_comment` VALUES (41, '0', 1, -1, 'xxxx', -1, -1, 6, '2024-12-17 09:35:03', 6, '2024-12-17 09:35:03', 0);
INSERT INTO `sg_comment` VALUES (42, '1', 1, -1, '333', -1, -1, 6, '2024-12-19 15:12:34', 6, '2024-12-19 15:12:34', 0);
INSERT INTO `sg_comment` VALUES (43, '0', 1, -1, '11', -1, -1, -1, '2024-12-20 09:50:06', -1, '2024-12-20 09:50:06', 0);
INSERT INTO `sg_comment` VALUES (44, '0', 1, -1, '111111', -1, -1, -1, '2024-12-20 10:57:42', 1, '2024-12-20 10:57:42', 0);

-- ----------------------------
-- Table structure for sg_link
-- ----------------------------
DROP TABLE IF EXISTS `sg_link`;
CREATE TABLE `sg_link`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `logo` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站地址',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '2' COMMENT '审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '友链' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sg_link
-- ----------------------------
INSERT INTO `sg_link` VALUES (1, 'sda', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975', 'sda', 'https://www.baidu.com', '0', NULL, '2022-01-13 08:25:47', NULL, '2022-01-13 08:36:14', 0);
INSERT INTO `sg_link` VALUES (2, 'sda', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975', 'dada', 'https://www.qq.com', '0', NULL, '2022-01-13 09:06:10', NULL, '2022-01-13 09:07:09', 0);
INSERT INTO `sg_link` VALUES (3, 'sa', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975', 'da', 'https://www.taobao.com', '0', NULL, '2022-01-13 09:23:01', NULL, '2022-01-13 09:23:01', 1);
INSERT INTO `sg_link` VALUES (4, 'myGithub', NULL, NULL, 'https://github.com/yusuaois', '0', NULL, '2024-12-18 16:37:26', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sg_tag
-- ----------------------------
DROP TABLE IF EXISTS `sg_tag`;
CREATE TABLE `sg_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签名',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sg_tag
-- ----------------------------
INSERT INTO `sg_tag` VALUES (1, 'Mybatis', NULL, NULL, NULL, '2022-01-11 09:20:50', 0, 'weqw');
INSERT INTO `sg_tag` VALUES (4, 'Java', NULL, '2022-01-13 15:22:43', NULL, '2022-01-13 15:22:43', 1, 'sdad');
INSERT INTO `sg_tag` VALUES (15, '666', 1, '2024-12-13 17:13:22', 1, '2024-12-13 17:13:22', 1, 'ccc');
INSERT INTO `sg_tag` VALUES (16, 'CCC', NULL, '2024-12-13 17:21:16', NULL, NULL, 1, '');
INSERT INTO `sg_tag` VALUES (17, 'C++', 1, '2024-12-17 15:04:38', NULL, NULL, 0, 'c');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2036 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 'M', '0', '0', '', 'system', 0, '2021-11-12 10:46:19', 0, NULL, '系统管理目录', '0');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 'C', '0', '0', 'system:user:list', 'user', 0, '2021-11-12 10:46:19', 1, '2022-07-31 15:47:58', '用户管理菜单', '0');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 'C', '0', '0', 'system:role:list', 'peoples', 0, '2021-11-12 10:46:19', 0, NULL, '角色管理菜单', '0');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 'C', '0', '0', 'system:menu:list', 'tree-table', 0, '2021-11-12 10:46:19', 0, NULL, '菜单管理菜单', '0');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 'F', '0', '0', 'system:user:query', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 'F', '0', '0', 'system:user:add', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 'F', '0', '0', 'system:user:edit', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 'F', '0', '0', 'system:user:remove', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 'F', '0', '0', 'system:user:export', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 'F', '0', '0', 'system:user:import', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 'F', '0', '0', 'system:user:resetPwd', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 'F', '0', '0', 'system:role:query', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 'F', '0', '0', 'system:role:add', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 'F', '0', '0', 'system:role:edit', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 'F', '0', '0', 'system:role:remove', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 'F', '0', '0', 'system:role:export', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 'F', '0', '0', 'system:menu:query', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 'F', '0', '0', 'system:menu:add', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 'F', '0', '0', 'system:menu:edit', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 'F', '0', '0', 'system:menu:remove', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (2017, '内容管理', 0, 4, 'content', NULL, 1, 'M', '0', '0', NULL, 'table', NULL, '2022-01-08 02:44:38', 1, '2022-07-31 12:34:23', '', '0');
INSERT INTO `sys_menu` VALUES (2018, '分类管理', 2017, 1, 'category', 'content/category/index', 1, 'C', '0', '0', 'content:category:list', 'example', NULL, '2022-01-08 02:51:45', NULL, '2022-01-08 02:51:45', '', '0');
INSERT INTO `sys_menu` VALUES (2019, '文章管理', 2017, 0, 'article', 'content/article/index', 1, 'C', '0', '0', 'content:article:list', 'build', NULL, '2022-01-08 02:53:10', NULL, '2022-01-08 02:53:10', '', '0');
INSERT INTO `sys_menu` VALUES (2021, '标签管理', 2017, 6, 'tag', 'content/tag/index', 1, 'C', '0', '0', 'content:tag:index', 'button', NULL, '2022-01-08 02:55:37', NULL, '2022-01-08 02:55:50', '', '0');
INSERT INTO `sys_menu` VALUES (2022, '友链管理', 2017, 4, 'link', 'content/link/index', 1, 'C', '0', '0', 'content:link:list', '404', NULL, '2022-01-08 02:56:50', NULL, '2022-01-08 02:56:50', '', '0');
INSERT INTO `sys_menu` VALUES (2023, '写博文', 0, 0, 'write', 'content/article/write/index', 1, 'C', '0', '0', 'content:article:writer', 'build', NULL, '2022-01-08 03:39:58', 1, '2022-07-31 22:07:05', '', '0');
INSERT INTO `sys_menu` VALUES (2024, '友链新增', 2022, 0, '', NULL, 1, 'F', '0', '0', 'content:link:add', '#', NULL, '2022-01-16 07:59:17', NULL, '2022-01-16 07:59:17', '', '0');
INSERT INTO `sys_menu` VALUES (2025, '友链修改', 2022, 1, '', NULL, 1, 'F', '0', '0', 'content:link:edit', '#', NULL, '2022-01-16 07:59:44', NULL, '2022-01-16 07:59:44', '', '0');
INSERT INTO `sys_menu` VALUES (2026, '友链删除', 2022, 1, '', NULL, 1, 'F', '0', '0', 'content:link:remove', '#', NULL, '2022-01-16 08:00:05', NULL, '2022-01-16 08:00:05', '', '0');
INSERT INTO `sys_menu` VALUES (2027, '友链查询', 2022, 2, '', NULL, 1, 'F', '0', '0', 'content:link:query', '#', NULL, '2022-01-16 08:04:09', NULL, '2022-01-16 08:04:09', '', '0');
INSERT INTO `sys_menu` VALUES (2028, '导出分类', 2018, 1, '', NULL, 1, 'F', '0', '0', 'content:category:export', '#', NULL, '2022-01-21 07:06:59', NULL, '2022-01-21 07:06:59', '', '0');
INSERT INTO `sys_menu` VALUES (2034, '你好', 0, 0, '/user', NULL, 1, 'M', '0', '0', NULL, '404', NULL, '2024-12-17 15:14:00', NULL, NULL, '', '1');
INSERT INTO `sys_menu` VALUES (2035, 'dsf', 0, 1, 'sdf', NULL, 1, 'M', '0', '0', NULL, 'cascader', NULL, '2024-12-19 14:52:42', NULL, NULL, '', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '0', '0', 0, '2021-11-12 10:46:19', NULL, '2024-12-19 14:55:43', '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '0', '0', 0, '2021-11-12 10:46:19', 0, '2022-01-01 22:32:58', '普通角色');
INSERT INTO `sys_role` VALUES (11, '嘎嘎嘎', 'aggag', 5, '0', '0', NULL, '2022-01-06 14:07:40', NULL, '2024-12-18 11:34:03', '嘎嘎嘎');
INSERT INTO `sys_role` VALUES (12, '友链审核员', 'link', 1, '0', '0', NULL, '2022-01-16 06:49:30', NULL, '2022-01-16 08:05:09', NULL);
INSERT INTO `sys_role` VALUES (25, '超管', 'super_admin', 0, '0', '0', 1, '2024-12-17 21:50:15', NULL, '2024-12-18 11:01:20', NULL);
INSERT INTO `sys_role` VALUES (26, '111', '111', 1, '0', '1', 1, '2024-12-18 11:03:17', NULL, '2024-12-18 11:06:46', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (0, 0);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 2000);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (3, 4);
INSERT INTO `sys_role_menu` VALUES (3, 100);
INSERT INTO `sys_role_menu` VALUES (3, 101);
INSERT INTO `sys_role_menu` VALUES (3, 103);
INSERT INTO `sys_role_menu` VALUES (3, 104);
INSERT INTO `sys_role_menu` VALUES (3, 105);
INSERT INTO `sys_role_menu` VALUES (3, 106);
INSERT INTO `sys_role_menu` VALUES (3, 107);
INSERT INTO `sys_role_menu` VALUES (3, 108);
INSERT INTO `sys_role_menu` VALUES (3, 109);
INSERT INTO `sys_role_menu` VALUES (3, 110);
INSERT INTO `sys_role_menu` VALUES (3, 111);
INSERT INTO `sys_role_menu` VALUES (3, 112);
INSERT INTO `sys_role_menu` VALUES (3, 113);
INSERT INTO `sys_role_menu` VALUES (3, 114);
INSERT INTO `sys_role_menu` VALUES (3, 115);
INSERT INTO `sys_role_menu` VALUES (3, 116);
INSERT INTO `sys_role_menu` VALUES (3, 500);
INSERT INTO `sys_role_menu` VALUES (3, 501);
INSERT INTO `sys_role_menu` VALUES (3, 1001);
INSERT INTO `sys_role_menu` VALUES (3, 1002);
INSERT INTO `sys_role_menu` VALUES (3, 1003);
INSERT INTO `sys_role_menu` VALUES (3, 1004);
INSERT INTO `sys_role_menu` VALUES (3, 1005);
INSERT INTO `sys_role_menu` VALUES (3, 1006);
INSERT INTO `sys_role_menu` VALUES (3, 1007);
INSERT INTO `sys_role_menu` VALUES (3, 1008);
INSERT INTO `sys_role_menu` VALUES (3, 1009);
INSERT INTO `sys_role_menu` VALUES (3, 1010);
INSERT INTO `sys_role_menu` VALUES (3, 1011);
INSERT INTO `sys_role_menu` VALUES (3, 1012);
INSERT INTO `sys_role_menu` VALUES (3, 1017);
INSERT INTO `sys_role_menu` VALUES (3, 1018);
INSERT INTO `sys_role_menu` VALUES (3, 1019);
INSERT INTO `sys_role_menu` VALUES (3, 1020);
INSERT INTO `sys_role_menu` VALUES (3, 1021);
INSERT INTO `sys_role_menu` VALUES (3, 1022);
INSERT INTO `sys_role_menu` VALUES (3, 1023);
INSERT INTO `sys_role_menu` VALUES (3, 1024);
INSERT INTO `sys_role_menu` VALUES (3, 1025);
INSERT INTO `sys_role_menu` VALUES (3, 1026);
INSERT INTO `sys_role_menu` VALUES (3, 1027);
INSERT INTO `sys_role_menu` VALUES (3, 1028);
INSERT INTO `sys_role_menu` VALUES (3, 1029);
INSERT INTO `sys_role_menu` VALUES (3, 1030);
INSERT INTO `sys_role_menu` VALUES (3, 1031);
INSERT INTO `sys_role_menu` VALUES (3, 1032);
INSERT INTO `sys_role_menu` VALUES (3, 1033);
INSERT INTO `sys_role_menu` VALUES (3, 1034);
INSERT INTO `sys_role_menu` VALUES (3, 1035);
INSERT INTO `sys_role_menu` VALUES (3, 1036);
INSERT INTO `sys_role_menu` VALUES (3, 1037);
INSERT INTO `sys_role_menu` VALUES (3, 1038);
INSERT INTO `sys_role_menu` VALUES (3, 1039);
INSERT INTO `sys_role_menu` VALUES (3, 1040);
INSERT INTO `sys_role_menu` VALUES (3, 1041);
INSERT INTO `sys_role_menu` VALUES (3, 1042);
INSERT INTO `sys_role_menu` VALUES (3, 1043);
INSERT INTO `sys_role_menu` VALUES (3, 1044);
INSERT INTO `sys_role_menu` VALUES (3, 1045);
INSERT INTO `sys_role_menu` VALUES (3, 1046);
INSERT INTO `sys_role_menu` VALUES (3, 1047);
INSERT INTO `sys_role_menu` VALUES (3, 1048);
INSERT INTO `sys_role_menu` VALUES (3, 1049);
INSERT INTO `sys_role_menu` VALUES (3, 1050);
INSERT INTO `sys_role_menu` VALUES (3, 1051);
INSERT INTO `sys_role_menu` VALUES (3, 1052);
INSERT INTO `sys_role_menu` VALUES (3, 1053);
INSERT INTO `sys_role_menu` VALUES (3, 1054);
INSERT INTO `sys_role_menu` VALUES (3, 1055);
INSERT INTO `sys_role_menu` VALUES (3, 1056);
INSERT INTO `sys_role_menu` VALUES (3, 1057);
INSERT INTO `sys_role_menu` VALUES (3, 1058);
INSERT INTO `sys_role_menu` VALUES (3, 1059);
INSERT INTO `sys_role_menu` VALUES (3, 1060);
INSERT INTO `sys_role_menu` VALUES (3, 2000);
INSERT INTO `sys_role_menu` VALUES (11, 1);
INSERT INTO `sys_role_menu` VALUES (11, 100);
INSERT INTO `sys_role_menu` VALUES (11, 101);
INSERT INTO `sys_role_menu` VALUES (11, 102);
INSERT INTO `sys_role_menu` VALUES (11, 103);
INSERT INTO `sys_role_menu` VALUES (11, 104);
INSERT INTO `sys_role_menu` VALUES (11, 105);
INSERT INTO `sys_role_menu` VALUES (11, 106);
INSERT INTO `sys_role_menu` VALUES (11, 107);
INSERT INTO `sys_role_menu` VALUES (11, 108);
INSERT INTO `sys_role_menu` VALUES (11, 500);
INSERT INTO `sys_role_menu` VALUES (11, 501);
INSERT INTO `sys_role_menu` VALUES (11, 1001);
INSERT INTO `sys_role_menu` VALUES (11, 1002);
INSERT INTO `sys_role_menu` VALUES (11, 1003);
INSERT INTO `sys_role_menu` VALUES (11, 1004);
INSERT INTO `sys_role_menu` VALUES (11, 1005);
INSERT INTO `sys_role_menu` VALUES (11, 1006);
INSERT INTO `sys_role_menu` VALUES (11, 1007);
INSERT INTO `sys_role_menu` VALUES (11, 1008);
INSERT INTO `sys_role_menu` VALUES (11, 1009);
INSERT INTO `sys_role_menu` VALUES (11, 1010);
INSERT INTO `sys_role_menu` VALUES (11, 1011);
INSERT INTO `sys_role_menu` VALUES (11, 1012);
INSERT INTO `sys_role_menu` VALUES (11, 1013);
INSERT INTO `sys_role_menu` VALUES (11, 1014);
INSERT INTO `sys_role_menu` VALUES (11, 1015);
INSERT INTO `sys_role_menu` VALUES (11, 1016);
INSERT INTO `sys_role_menu` VALUES (11, 1017);
INSERT INTO `sys_role_menu` VALUES (11, 1018);
INSERT INTO `sys_role_menu` VALUES (11, 1019);
INSERT INTO `sys_role_menu` VALUES (11, 1020);
INSERT INTO `sys_role_menu` VALUES (11, 1021);
INSERT INTO `sys_role_menu` VALUES (11, 1022);
INSERT INTO `sys_role_menu` VALUES (11, 1023);
INSERT INTO `sys_role_menu` VALUES (11, 1024);
INSERT INTO `sys_role_menu` VALUES (11, 1025);
INSERT INTO `sys_role_menu` VALUES (11, 1026);
INSERT INTO `sys_role_menu` VALUES (11, 1027);
INSERT INTO `sys_role_menu` VALUES (11, 1028);
INSERT INTO `sys_role_menu` VALUES (11, 1029);
INSERT INTO `sys_role_menu` VALUES (11, 1030);
INSERT INTO `sys_role_menu` VALUES (11, 1031);
INSERT INTO `sys_role_menu` VALUES (11, 1032);
INSERT INTO `sys_role_menu` VALUES (11, 1033);
INSERT INTO `sys_role_menu` VALUES (11, 1034);
INSERT INTO `sys_role_menu` VALUES (11, 1035);
INSERT INTO `sys_role_menu` VALUES (11, 1036);
INSERT INTO `sys_role_menu` VALUES (11, 1037);
INSERT INTO `sys_role_menu` VALUES (11, 1038);
INSERT INTO `sys_role_menu` VALUES (11, 1039);
INSERT INTO `sys_role_menu` VALUES (11, 1040);
INSERT INTO `sys_role_menu` VALUES (11, 1041);
INSERT INTO `sys_role_menu` VALUES (11, 1042);
INSERT INTO `sys_role_menu` VALUES (11, 1043);
INSERT INTO `sys_role_menu` VALUES (11, 1044);
INSERT INTO `sys_role_menu` VALUES (11, 1045);
INSERT INTO `sys_role_menu` VALUES (11, 2000);
INSERT INTO `sys_role_menu` VALUES (11, 2003);
INSERT INTO `sys_role_menu` VALUES (11, 2004);
INSERT INTO `sys_role_menu` VALUES (11, 2005);
INSERT INTO `sys_role_menu` VALUES (11, 2006);
INSERT INTO `sys_role_menu` VALUES (11, 2007);
INSERT INTO `sys_role_menu` VALUES (11, 2008);
INSERT INTO `sys_role_menu` VALUES (11, 2009);
INSERT INTO `sys_role_menu` VALUES (11, 2010);
INSERT INTO `sys_role_menu` VALUES (11, 2011);
INSERT INTO `sys_role_menu` VALUES (11, 2012);
INSERT INTO `sys_role_menu` VALUES (11, 2013);
INSERT INTO `sys_role_menu` VALUES (11, 2014);
INSERT INTO `sys_role_menu` VALUES (12, 2017);
INSERT INTO `sys_role_menu` VALUES (12, 2022);
INSERT INTO `sys_role_menu` VALUES (12, 2024);
INSERT INTO `sys_role_menu` VALUES (12, 2025);
INSERT INTO `sys_role_menu` VALUES (12, 2026);
INSERT INTO `sys_role_menu` VALUES (12, 2027);
INSERT INTO `sys_role_menu` VALUES (25, 1);
INSERT INTO `sys_role_menu` VALUES (25, 100);
INSERT INTO `sys_role_menu` VALUES (25, 101);
INSERT INTO `sys_role_menu` VALUES (25, 102);
INSERT INTO `sys_role_menu` VALUES (25, 1001);
INSERT INTO `sys_role_menu` VALUES (25, 1002);
INSERT INTO `sys_role_menu` VALUES (25, 1003);
INSERT INTO `sys_role_menu` VALUES (25, 1004);
INSERT INTO `sys_role_menu` VALUES (25, 1005);
INSERT INTO `sys_role_menu` VALUES (25, 1006);
INSERT INTO `sys_role_menu` VALUES (25, 1007);
INSERT INTO `sys_role_menu` VALUES (25, 1008);
INSERT INTO `sys_role_menu` VALUES (25, 1009);
INSERT INTO `sys_role_menu` VALUES (25, 1010);
INSERT INTO `sys_role_menu` VALUES (25, 1011);
INSERT INTO `sys_role_menu` VALUES (25, 1012);
INSERT INTO `sys_role_menu` VALUES (25, 1013);
INSERT INTO `sys_role_menu` VALUES (25, 1014);
INSERT INTO `sys_role_menu` VALUES (25, 1015);
INSERT INTO `sys_role_menu` VALUES (25, 1016);
INSERT INTO `sys_role_menu` VALUES (25, 2017);
INSERT INTO `sys_role_menu` VALUES (25, 2018);
INSERT INTO `sys_role_menu` VALUES (25, 2019);
INSERT INTO `sys_role_menu` VALUES (25, 2021);
INSERT INTO `sys_role_menu` VALUES (25, 2022);
INSERT INTO `sys_role_menu` VALUES (25, 2023);
INSERT INTO `sys_role_menu` VALUES (25, 2024);
INSERT INTO `sys_role_menu` VALUES (25, 2025);
INSERT INTO `sys_role_menu` VALUES (25, 2026);
INSERT INTO `sys_role_menu` VALUES (25, 2027);
INSERT INTO `sys_role_menu` VALUES (25, 2028);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '用户类型：0代表普通用户，1代表管理员',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'sg', 'sg', '$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy', '1', '0', '23412332@qq.com', '18888888888', '0', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F3bf9c263bc0f2ac5c3a7feb9e218d07475573ec8.gi', NULL, '2022-01-05 09:01:56', 1, '2022-01-30 15:37:03', 0);
INSERT INTO `sys_user` VALUES (3, 'sg3', 'weqe', '$2a$10$ydv3rLkteFnRx9xelQ7elOiVhFvXOooA98xCqk/omh7G94R.K/E3O', '1', '0', NULL, NULL, '0', NULL, NULL, '2022-01-05 13:28:43', NULL, '2022-01-05 13:28:43', 0);
INSERT INTO `sys_user` VALUES (4, 'sg2', 'dsadd', '$2a$10$kY4T3SN7i4muBccZppd2OOkhxMN6yt8tND1sF89hXOaFylhY2T3he', '1', '0', '23412332@qq.com', '19098790742', '0', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (5, 'sg2233', 'tteqe', '', '1', '0', NULL, '18246845873', '1', NULL, NULL, '2022-01-06 03:51:13', NULL, '2022-01-06 07:00:50', 0);
INSERT INTO `sys_user` VALUES (6, 'sangeng', 'sangeng', '$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy', '1', '0', '2312321', '17777777777', '0', 'http://so9rqxn4p.hn-bkt.clouddn.com/2024/12/16/634bcc08fc4847acbc045d918aeaf06f.png', NULL, '2022-01-16 06:54:26', NULL, '2022-01-16 07:06:34', 0);
INSERT INTO `sys_user` VALUES (10, '222', '222', '222', '1', '0', '222', '22', '1', '222', 222, NULL, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (11, '测试', '测试', '$2a$10$TgXglYr5NLu3npxsGBPkAOcYlsotAdUTNdoszMl9QKyjyN3CzfDme', '0', '0', '2222@qq.com', '17620093717', '1', NULL, NULL, '2024-12-18 14:47:51', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (12, '123', '123', '$2a$10$LRtNnk6ci2grlCokwRiVfOSeWWXGxfyUVbQHEcXjzBOBkrLq2E5f2', '0', '0', '2222@qq.com', '17620093717', '0', NULL, NULL, '2024-12-19 15:04:36', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (13, '3123213213', 'eeqeq213132', '$2a$10$9ShmLPQmDh6ZXj/5Zxz4lu0hzSc7h2tHoUUuQzHaNj2D8aMzp6eAK', '0', '0', 'sangeng@qq.com', NULL, NULL, NULL, NULL, '2024-12-19 16:26:12', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (14, '2313123', '123423121312', '$2a$10$SczTHSoUgjY0dTgkiP3irOQwWU1eOvGwXXlNyhFmE0FR37QNXB8oO', '0', '0', '21321321312@qq.com', NULL, NULL, NULL, NULL, '2024-12-19 16:44:13', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (5, 2);
INSERT INTO `sys_user_role` VALUES (6, 12);
INSERT INTO `sys_user_role` VALUES (11, 1);
INSERT INTO `sys_user_role` VALUES (11, 2);
INSERT INTO `sys_user_role` VALUES (11, 11);
INSERT INTO `sys_user_role` VALUES (11, 12);
INSERT INTO `sys_user_role` VALUES (11, 25);
INSERT INTO `sys_user_role` VALUES (12, 25);

-- ----------------------------
-- Table structure for word_deny
-- ----------------------------
DROP TABLE IF EXISTS `word_deny`;
CREATE TABLE `word_deny`  (
  `word` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`word`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of word_deny
-- ----------------------------
INSERT INTO `word_deny` VALUES ('1790650009059581954');
INSERT INTO `word_deny` VALUES ('64事件');
INSERT INTO `word_deny` VALUES ('64惨案');
INSERT INTO `word_deny` VALUES ('64时期');
INSERT INTO `word_deny` VALUES ('64运动');
INSERT INTO `word_deny` VALUES ('6四');
INSERT INTO `word_deny` VALUES ('89年春夏之交');
INSERT INTO `word_deny` VALUES ('adrenaline');
INSERT INTO `word_deny` VALUES ('androst');
INSERT INTO `word_deny` VALUES ('benzodiazepines');
INSERT INTO `word_deny` VALUES ('cannabis');
INSERT INTO `word_deny` VALUES ('diacetylmorphine');
INSERT INTO `word_deny` VALUES ('diamorphine');
INSERT INTO `word_deny` VALUES ('erythropoietin');
INSERT INTO `word_deny` VALUES ('heroin');
INSERT INTO `word_deny` VALUES ('ketamine');
INSERT INTO `word_deny` VALUES ('k粉');
INSERT INTO `word_deny` VALUES ('pollinations.ai');
INSERT INTO `word_deny` VALUES ('sb');
INSERT INTO `word_deny` VALUES ('sm用具');
INSERT INTO `word_deny` VALUES ('sm电击器');
INSERT INTO `word_deny` VALUES ('strychnine');
INSERT INTO `word_deny` VALUES ('tamoxifen');
INSERT INTO `word_deny` VALUES ('testosterone');
INSERT INTO `word_deny` VALUES ('一ye情');
INSERT INTO `word_deny` VALUES ('一中一台');
INSERT INTO `word_deny` VALUES ('一党专制');
INSERT INTO `word_deny` VALUES ('一党专政');
INSERT INTO `word_deny` VALUES ('一夜欢');
INSERT INTO `word_deny` VALUES ('丁一平');
INSERT INTO `word_deny` VALUES ('专制政权');
INSERT INTO `word_deny` VALUES ('丝诱');
INSERT INTO `word_deny` VALUES ('中华帝国');
INSERT INTO `word_deny` VALUES ('习仲勋');
INSERT INTO `word_deny` VALUES ('习近平');
INSERT INTO `word_deny` VALUES ('乱交');
INSERT INTO `word_deny` VALUES ('乱伦');
INSERT INTO `word_deny` VALUES ('乳交');
INSERT INTO `word_deny` VALUES ('乳房');
INSERT INTO `word_deny` VALUES ('乳沟');
INSERT INTO `word_deny` VALUES ('乳爆');
INSERT INTO `word_deny` VALUES ('于幼军');
INSERT INTO `word_deny` VALUES ('亡党亡国');
INSERT INTO `word_deny` VALUES ('人兽');
INSERT INTO `word_deny` VALUES ('代血浆');
INSERT INTO `word_deny` VALUES ('令计划');
INSERT INTO `word_deny` VALUES ('体奸');
INSERT INTO `word_deny` VALUES ('俞正声');
INSERT INTO `word_deny` VALUES ('傅锐');
INSERT INTO `word_deny` VALUES ('傻吊');
INSERT INTO `word_deny` VALUES ('光复民国');
INSERT INTO `word_deny` VALUES ('八九年');
INSERT INTO `word_deny` VALUES ('六4');
INSERT INTO `word_deny` VALUES ('六四');
INSERT INTO `word_deny` VALUES ('六四事件');
INSERT INTO `word_deny` VALUES ('六四运动');
INSERT INTO `word_deny` VALUES ('共产专制');
INSERT INTO `word_deny` VALUES ('共产王朝');
INSERT INTO `word_deny` VALUES ('兽交');
INSERT INTO `word_deny` VALUES ('兽奸');
INSERT INTO `word_deny` VALUES ('兽欲');
INSERT INTO `word_deny` VALUES ('冰毒');
INSERT INTO `word_deny` VALUES ('凯他敏');
INSERT INTO `word_deny` VALUES ('刘奇葆');
INSERT INTO `word_deny` VALUES ('刘少奇');
INSERT INTO `word_deny` VALUES ('刘延东');
INSERT INTO `word_deny` VALUES ('刘永清');
INSERT INTO `word_deny` VALUES ('劣等民族');
INSERT INTO `word_deny` VALUES ('助勃乳液');
INSERT INTO `word_deny` VALUES ('包夜');
INSERT INTO `word_deny` VALUES ('北京政权');
INSERT INTO `word_deny` VALUES ('华主席');
INSERT INTO `word_deny` VALUES ('华建敏');
INSERT INTO `word_deny` VALUES ('厕奴');
INSERT INTO `word_deny` VALUES ('口交');
INSERT INTO `word_deny` VALUES ('口吹器');
INSERT INTO `word_deny` VALUES ('口射');
INSERT INTO `word_deny` VALUES ('口枷');
INSERT INTO `word_deny` VALUES ('口淫');
INSERT INTO `word_deny` VALUES ('口爆');
INSERT INTO `word_deny` VALUES ('台海大战');
INSERT INTO `word_deny` VALUES ('台湾共和国');
INSERT INTO `word_deny` VALUES ('台湾国');
INSERT INTO `word_deny` VALUES ('台湾应该独立');
INSERT INTO `word_deny` VALUES ('台湾有权独立');
INSERT INTO `word_deny` VALUES ('台湾独立');
INSERT INTO `word_deny` VALUES ('台独');
INSERT INTO `word_deny` VALUES ('叶剑英');
INSERT INTO `word_deny` VALUES ('后穴');
INSERT INTO `word_deny` VALUES ('吕祖善');
INSERT INTO `word_deny` VALUES ('吗啡');
INSERT INTO `word_deny` VALUES ('吞精');
INSERT INTO `word_deny` VALUES ('吴仪');
INSERT INTO `word_deny` VALUES ('吴官正');
INSERT INTO `word_deny` VALUES ('吴定富');
INSERT INTO `word_deny` VALUES ('吴胜利');
INSERT INTO `word_deny` VALUES ('吴邦国');
INSERT INTO `word_deny` VALUES ('吸精');
INSERT INTO `word_deny` VALUES ('咪咪');
INSERT INTO `word_deny` VALUES ('喝血社会');
INSERT INTO `word_deny` VALUES ('喷精');
INSERT INTO `word_deny` VALUES ('四二六社论');
INSERT INTO `word_deny` VALUES ('回良玉');
INSERT INTO `word_deny` VALUES ('国产av');
INSERT INTO `word_deny` VALUES ('国家主席');
INSERT INTO `word_deny` VALUES ('国家主要部委');
INSERT INTO `word_deny` VALUES ('国家民委');
INSERT INTO `word_deny` VALUES ('国民党万岁');
INSERT INTO `word_deny` VALUES ('国管局');
INSERT INTO `word_deny` VALUES ('国防科工委');
INSERT INTO `word_deny` VALUES ('坚硬持久膏');
INSERT INTO `word_deny` VALUES ('坦克压大学生');
INSERT INTO `word_deny` VALUES ('壮阳啫喱');
INSERT INTO `word_deny` VALUES ('壮阳喷剂');
INSERT INTO `word_deny` VALUES ('壮阳增大胶囊');
INSERT INTO `word_deny` VALUES ('壮阳软膏');
INSERT INTO `word_deny` VALUES ('夜勤病栋');
INSERT INTO `word_deny` VALUES ('大力抽送');
INSERT INTO `word_deny` VALUES ('大陆官方');
INSERT INTO `word_deny` VALUES ('大麻');
INSERT INTO `word_deny` VALUES ('太子党');
INSERT INTO `word_deny` VALUES ('失身粉');
INSERT INTO `word_deny` VALUES ('奚国华');
INSERT INTO `word_deny` VALUES ('女优');
INSERT INTO `word_deny` VALUES ('奶子');
INSERT INTO `word_deny` VALUES ('奸情');
INSERT INTO `word_deny` VALUES ('妓女');
INSERT INTO `word_deny` VALUES ('婊子');
INSERT INTO `word_deny` VALUES ('嫩女');
INSERT INTO `word_deny` VALUES ('嫩穴');
INSERT INTO `word_deny` VALUES ('嫩逼');
INSERT INTO `word_deny` VALUES ('孙家正');
INSERT INTO `word_deny` VALUES ('孟建柱');
INSERT INTO `word_deny` VALUES ('安眠酮');
INSERT INTO `word_deny` VALUES ('宋平顺');
INSERT INTO `word_deny` VALUES ('射爽');
INSERT INTO `word_deny` VALUES ('射颜');
INSERT INTO `word_deny` VALUES ('小穴');
INSERT INTO `word_deny` VALUES ('小逼');
INSERT INTO `word_deny` VALUES ('屠光绍');
INSERT INTO `word_deny` VALUES ('巨乳');
INSERT INTO `word_deny` VALUES ('巨屌');
INSERT INTO `word_deny` VALUES ('干死你');
INSERT INTO `word_deny` VALUES ('干穴');
INSERT INTO `word_deny` VALUES ('幼交');
INSERT INTO `word_deny` VALUES ('廖锡龙');
INSERT INTO `word_deny` VALUES ('延时乳液');
INSERT INTO `word_deny` VALUES ('延时胶囊');
INSERT INTO `word_deny` VALUES ('张定发');
INSERT INTO `word_deny` VALUES ('张德江');
INSERT INTO `word_deny` VALUES ('张志国');
INSERT INTO `word_deny` VALUES ('张立昌');
INSERT INTO `word_deny` VALUES ('张荣坤');
INSERT INTO `word_deny` VALUES ('张高丽');
INSERT INTO `word_deny` VALUES ('强奸处女');
INSERT INTO `word_deny` VALUES ('强暴');
INSERT INTO `word_deny` VALUES ('彭丽媛');
INSERT INTO `word_deny` VALUES ('徐才厚');
INSERT INTO `word_deny` VALUES ('徐绍史');
INSERT INTO `word_deny` VALUES ('德国魔棒');
INSERT INTO `word_deny` VALUES ('性交');
INSERT INTO `word_deny` VALUES ('性奴');
INSERT INTO `word_deny` VALUES ('性技巧');
INSERT INTO `word_deny` VALUES ('性虎');
INSERT INTO `word_deny` VALUES ('性虐');
INSERT INTO `word_deny` VALUES ('性饥渴');
INSERT INTO `word_deny` VALUES ('情色');
INSERT INTO `word_deny` VALUES ('情趣用品');
INSERT INTO `word_deny` VALUES ('成人小说');
INSERT INTO `word_deny` VALUES ('成人文学');
INSERT INTO `word_deny` VALUES ('成人游戏');
INSERT INTO `word_deny` VALUES ('成人电影');
INSERT INTO `word_deny` VALUES ('成人网站');
INSERT INTO `word_deny` VALUES ('成人色情');
INSERT INTO `word_deny` VALUES ('成人论坛');
INSERT INTO `word_deny` VALUES ('戴秉国');
INSERT INTO `word_deny` VALUES ('打倒中共');
INSERT INTO `word_deny` VALUES ('打倒中国');
INSERT INTO `word_deny` VALUES ('打倒共产主义');
INSERT INTO `word_deny` VALUES ('打倒共产党');
INSERT INTO `word_deny` VALUES ('打倒江主席');
INSERT INTO `word_deny` VALUES ('打倒江泽民');
INSERT INTO `word_deny` VALUES ('打倒胡锦涛');
INSERT INTO `word_deny` VALUES ('抵制中共');
INSERT INTO `word_deny` VALUES ('抵制共产主义');
INSERT INTO `word_deny` VALUES ('抵制共产党');
INSERT INTO `word_deny` VALUES ('抵制江主席');
INSERT INTO `word_deny` VALUES ('抵制江泽民');
INSERT INTO `word_deny` VALUES ('抵制胡锦涛');
INSERT INTO `word_deny` VALUES ('拉拉用具');
INSERT INTO `word_deny` VALUES ('招妓');
INSERT INTO `word_deny` VALUES ('招鸡');
INSERT INTO `word_deny` VALUES ('挺趣蛋');
INSERT INTO `word_deny` VALUES ('换妻俱乐部');
INSERT INTO `word_deny` VALUES ('插你');
INSERT INTO `word_deny` VALUES ('插我');
INSERT INTO `word_deny` VALUES ('插暴');
INSERT INTO `word_deny` VALUES ('插比');
INSERT INTO `word_deny` VALUES ('插逼');
INSERT INTO `word_deny` VALUES ('插阴');
INSERT INTO `word_deny` VALUES ('援交');
INSERT INTO `word_deny` VALUES ('摸奶');
INSERT INTO `word_deny` VALUES ('摸胸');
INSERT INTO `word_deny` VALUES ('操我');
INSERT INTO `word_deny` VALUES ('操死');
INSERT INTO `word_deny` VALUES ('操烂');
INSERT INTO `word_deny` VALUES ('操逼');
INSERT INTO `word_deny` VALUES ('操黑');
INSERT INTO `word_deny` VALUES ('新型毒品');
INSERT INTO `word_deny` VALUES ('新疆7.5事件');
INSERT INTO `word_deny` VALUES ('新疆骚乱');
INSERT INTO `word_deny` VALUES ('无官正');
INSERT INTO `word_deny` VALUES ('日本万岁');
INSERT INTO `word_deny` VALUES ('日烂');
INSERT INTO `word_deny` VALUES ('日逼');
INSERT INTO `word_deny` VALUES ('暴乳');
INSERT INTO `word_deny` VALUES ('暴奸');
INSERT INTO `word_deny` VALUES ('暴淫');
INSERT INTO `word_deny` VALUES ('曾培炎');
INSERT INTO `word_deny` VALUES ('曾宪梓');
INSERT INTO `word_deny` VALUES ('曾庆红');
INSERT INTO `word_deny` VALUES ('曾荫权');
INSERT INTO `word_deny` VALUES ('朱容基');
INSERT INTO `word_deny` VALUES ('朱容鸡');
INSERT INTO `word_deny` VALUES ('朱镕基');
INSERT INTO `word_deny` VALUES ('朱镕鸡');
INSERT INTO `word_deny` VALUES ('李先念');
INSERT INTO `word_deny` VALUES ('李克强');
INSERT INTO `word_deny` VALUES ('李学举');
INSERT INTO `word_deny` VALUES ('李岚清');
INSERT INTO `word_deny` VALUES ('李建国');
INSERT INTO `word_deny` VALUES ('李春城');
INSERT INTO `word_deny` VALUES ('李沛瑶');
INSERT INTO `word_deny` VALUES ('李源潮');
INSERT INTO `word_deny` VALUES ('李瑞环');
INSERT INTO `word_deny` VALUES ('李荣融');
INSERT INTO `word_deny` VALUES ('李铁映');
INSERT INTO `word_deny` VALUES ('李长春');
INSERT INTO `word_deny` VALUES ('杜世成');
INSERT INTO `word_deny` VALUES ('杜冷丁');
INSERT INTO `word_deny` VALUES ('杜德印');
INSERT INTO `word_deny` VALUES ('杨洁篪');
INSERT INTO `word_deny` VALUES ('林左鸣');
INSERT INTO `word_deny` VALUES ('林树森');
INSERT INTO `word_deny` VALUES ('林炎志');
INSERT INTO `word_deny` VALUES ('柳斌杰');
INSERT INTO `word_deny` VALUES ('校鸡');
INSERT INTO `word_deny` VALUES ('梁光烈');
INSERT INTO `word_deny` VALUES ('楼凤');
INSERT INTO `word_deny` VALUES ('欧广源');
INSERT INTO `word_deny` VALUES ('母奸');
INSERT INTO `word_deny` VALUES ('毒龙');
INSERT INTO `word_deny` VALUES ('毛太祖');
INSERT INTO `word_deny` VALUES ('毛泽东');
INSERT INTO `word_deny` VALUES ('氯胺酮');
INSERT INTO `word_deny` VALUES ('江主席');
INSERT INTO `word_deny` VALUES ('江书记');
INSERT INTO `word_deny` VALUES ('江泽慧');
INSERT INTO `word_deny` VALUES ('江泽民');
INSERT INTO `word_deny` VALUES ('江绵康');
INSERT INTO `word_deny` VALUES ('江绵恒');
INSERT INTO `word_deny` VALUES ('江蛤蟆');
INSERT INTO `word_deny` VALUES ('汪东兴');
INSERT INTO `word_deny` VALUES ('沈跃跃');
INSERT INTO `word_deny` VALUES ('法轮');
INSERT INTO `word_deny` VALUES ('法轮功');
INSERT INTO `word_deny` VALUES ('浪叫');
INSERT INTO `word_deny` VALUES ('浪妇');
INSERT INTO `word_deny` VALUES ('浪逼');
INSERT INTO `word_deny` VALUES ('海luo因');
INSERT INTO `word_deny` VALUES ('海洛因');
INSERT INTO `word_deny` VALUES ('淫书');
INSERT INTO `word_deny` VALUES ('淫亵');
INSERT INTO `word_deny` VALUES ('淫兽学园');
INSERT INTO `word_deny` VALUES ('淫叫');
INSERT INTO `word_deny` VALUES ('淫声浪语');
INSERT INTO `word_deny` VALUES ('淫女');
INSERT INTO `word_deny` VALUES ('淫妇');
INSERT INTO `word_deny` VALUES ('淫妻');
INSERT INTO `word_deny` VALUES ('淫威');
INSERT INTO `word_deny` VALUES ('淫娃');
INSERT INTO `word_deny` VALUES ('淫媚');
INSERT INTO `word_deny` VALUES ('淫情');
INSERT INTO `word_deny` VALUES ('淫教师');
INSERT INTO `word_deny` VALUES ('淫术炼金士');
INSERT INTO `word_deny` VALUES ('淫样');
INSERT INTO `word_deny` VALUES ('淫母');
INSERT INTO `word_deny` VALUES ('淫水');
INSERT INTO `word_deny` VALUES ('淫河');
INSERT INTO `word_deny` VALUES ('淫浪');
INSERT INTO `word_deny` VALUES ('淫液');
INSERT INTO `word_deny` VALUES ('淫照');
INSERT INTO `word_deny` VALUES ('淫电影');
INSERT INTO `word_deny` VALUES ('淫糜');
INSERT INTO `word_deny` VALUES ('淫色');
INSERT INTO `word_deny` VALUES ('淫荡');
INSERT INTO `word_deny` VALUES ('淫荡自慰器');
INSERT INTO `word_deny` VALUES ('淫虐');
INSERT INTO `word_deny` VALUES ('淫虫');
INSERT INTO `word_deny` VALUES ('淫贱');
INSERT INTO `word_deny` VALUES ('淫魔');
INSERT INTO `word_deny` VALUES ('满洲第三帝国');
INSERT INTO `word_deny` VALUES ('漏乳');
INSERT INTO `word_deny` VALUES ('潮吹');
INSERT INTO `word_deny` VALUES ('潮喷');
INSERT INTO `word_deny` VALUES ('灭亡中国');
INSERT INTO `word_deny` VALUES ('焚烧中国国旗');
INSERT INTO `word_deny` VALUES ('爆乳');
INSERT INTO `word_deny` VALUES ('爆草');
INSERT INTO `word_deny` VALUES ('爱液');
INSERT INTO `word_deny` VALUES ('狂插');
INSERT INTO `word_deny` VALUES ('狂操');
INSERT INTO `word_deny` VALUES ('玉穴');
INSERT INTO `word_deny` VALUES ('王东明');
INSERT INTO `word_deny` VALUES ('王乐泉');
INSERT INTO `word_deny` VALUES ('王兆国');
INSERT INTO `word_deny` VALUES ('王太华');
INSERT INTO `word_deny` VALUES ('王学军');
INSERT INTO `word_deny` VALUES ('王岐山');
INSERT INTO `word_deny` VALUES ('王振华');
INSERT INTO `word_deny` VALUES ('王沪宁');
INSERT INTO `word_deny` VALUES ('王洛林');
INSERT INTO `word_deny` VALUES ('王胜俊');
INSERT INTO `word_deny` VALUES ('王鸿举');
INSERT INTO `word_deny` VALUES ('由喜贵');
INSERT INTO `word_deny` VALUES ('甲基安非他明');
INSERT INTO `word_deny` VALUES ('甲睾酮');
INSERT INTO `word_deny` VALUES ('男性延时');
INSERT INTO `word_deny` VALUES ('疆独');
INSERT INTO `word_deny` VALUES ('社会主义灭亡');
INSERT INTO `word_deny` VALUES ('粉碎四人帮');
INSERT INTO `word_deny` VALUES ('粉穴');
INSERT INTO `word_deny` VALUES ('粟戎生');
INSERT INTO `word_deny` VALUES ('精品激情');
INSERT INTO `word_deny` VALUES ('精品至爱');
INSERT INTO `word_deny` VALUES ('美乳');
INSERT INTO `word_deny` VALUES ('美穴');
INSERT INTO `word_deny` VALUES ('美逼');
INSERT INTO `word_deny` VALUES ('肉棒');
INSERT INTO `word_deny` VALUES ('肉茎');
INSERT INTO `word_deny` VALUES ('肏你');
INSERT INTO `word_deny` VALUES ('肏死');
INSERT INTO `word_deny` VALUES ('肛交');
INSERT INTO `word_deny` VALUES ('肥逼');
INSERT INTO `word_deny` VALUES ('胡主席');
INSERT INTO `word_deny` VALUES ('胡春华');
INSERT INTO `word_deny` VALUES ('胡景涛');
INSERT INTO `word_deny` VALUES ('胡海峰');
INSERT INTO `word_deny` VALUES ('胡海清');
INSERT INTO `word_deny` VALUES ('腐败中国');
INSERT INTO `word_deny` VALUES ('舔阴');
INSERT INTO `word_deny` VALUES ('色情网站');
INSERT INTO `word_deny` VALUES ('色欲');
INSERT INTO `word_deny` VALUES ('色逼');
INSERT INTO `word_deny` VALUES ('艳情小说');
INSERT INTO `word_deny` VALUES ('苏树林');
INSERT INTO `word_deny` VALUES ('苯丙胺');
INSERT INTO `word_deny` VALUES ('荡女');
INSERT INTO `word_deny` VALUES ('董建华');
INSERT INTO `word_deny` VALUES ('蒙古分裂分子');
INSERT INTO `word_deny` VALUES ('蔡赴朝');
INSERT INTO `word_deny` VALUES ('薄一波');
INSERT INTO `word_deny` VALUES ('薄熙来');
INSERT INTO `word_deny` VALUES ('藏独');
INSERT INTO `word_deny` VALUES ('藏独立');
INSERT INTO `word_deny` VALUES ('蜜穴');
INSERT INTO `word_deny` VALUES ('血洗京城');
INSERT INTO `word_deny` VALUES ('袁纯清');
INSERT INTO `word_deny` VALUES ('裹本');
INSERT INTO `word_deny` VALUES ('西藏314事件');
INSERT INTO `word_deny` VALUES ('要射了');
INSERT INTO `word_deny` VALUES ('许其亮');
INSERT INTO `word_deny` VALUES ('贺国强');
INSERT INTO `word_deny` VALUES ('贾庆林');
INSERT INTO `word_deny` VALUES ('贾廷安');
INSERT INTO `word_deny` VALUES ('足交');
INSERT INTO `word_deny` VALUES ('路甬祥');
INSERT INTO `word_deny` VALUES ('轮奸');
INSERT INTO `word_deny` VALUES ('轮操');
INSERT INTO `word_deny` VALUES ('轮暴');
INSERT INTO `word_deny` VALUES ('轮法功');
INSERT INTO `word_deny` VALUES ('迷奸');
INSERT INTO `word_deny` VALUES ('逼奸');
INSERT INTO `word_deny` VALUES ('邓小平');
INSERT INTO `word_deny` VALUES ('邓晓平');
INSERT INTO `word_deny` VALUES ('邓朴方');
INSERT INTO `word_deny` VALUES ('邓榕');
INSERT INTO `word_deny` VALUES ('邓质方');
INSERT INTO `word_deny` VALUES ('郭伯雄');
INSERT INTO `word_deny` VALUES ('郭金龙');
INSERT INTO `word_deny` VALUES ('铁凝');
INSERT INTO `word_deny` VALUES ('锋同志');
INSERT INTO `word_deny` VALUES ('防泄膏');
INSERT INTO `word_deny` VALUES ('阳具');
INSERT INTO `word_deny` VALUES ('阿芙蓉');
INSERT INTO `word_deny` VALUES ('陈同海');
INSERT INTO `word_deny` VALUES ('陈建国');
INSERT INTO `word_deny` VALUES ('陈德铭');
INSERT INTO `word_deny` VALUES ('陈炳德');
INSERT INTO `word_deny` VALUES ('陈绍基');
INSERT INTO `word_deny` VALUES ('陈至立');
INSERT INTO `word_deny` VALUES ('陈良宇');
INSERT INTO `word_deny` VALUES ('靖志远');
INSERT INTO `word_deny` VALUES ('颜射');
INSERT INTO `word_deny` VALUES ('马飚');
INSERT INTO `word_deny` VALUES ('骚水');
INSERT INTO `word_deny` VALUES ('骚穴');
INSERT INTO `word_deny` VALUES ('鸡奸');
INSERT INTO `word_deny` VALUES ('鸡巴');
INSERT INTO `word_deny` VALUES ('鸦片');
INSERT INTO `word_deny` VALUES ('麻古');
INSERT INTO `word_deny` VALUES ('黄丽满');
INSERT INTO `word_deny` VALUES ('黄兴国');
INSERT INTO `word_deny` VALUES ('黄华华');
INSERT INTO `word_deny` VALUES ('黄片');
INSERT INTO `word_deny` VALUES ('黑逼');
INSERT INTO `word_deny` VALUES ('龟头');

SET FOREIGN_KEY_CHECKS = 1;
