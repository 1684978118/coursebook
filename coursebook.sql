/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 8.0.36 : Database - coursebook
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create database if not exists coursebook;
-- ----------------------------
-- Table structure for admin
use coursebook;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理员表';

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`,`name`,`avatar`,`role`,`phone`,`email`) values 
(1,'admin','admin','管理员','http://localhost:9090/files/download/1749050242731-favicon.ico','ADMIN','18899990011','admin2@xm.com');

/*Table structure for table `class_info` */

DROP TABLE IF EXISTS `class_info`;

CREATE TABLE `class_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='班级信息';

/*Data for the table `class_info` */

insert  into `class_info`(`id`,`name`) values 
(1,'计算机1班'),
(2,'计算机2班'),
(3,'网络1班'),
(4,'自动化1班'),
(5,'英语1班'),
(6,'机械1班'),
(8,'人工智能1班');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_id` int DEFAULT NULL COMMENT '学生id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '反馈内容',
  `reply_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '回复内容',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '回复状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='反馈信息';

/*Data for the table `feedback` */

insert  into `feedback`(`id`,`student_id`,`content`,`reply_content`,`status`) values 
(2,2,'我没领取到 人工智能（第3版），申请补发','好的，我再来申请一下，你明天再看看','已回复'),
(3,3,'这本书非常nice，我很喜欢','那你要好好学习','已回复');

/*Table structure for table `in_stock` */

DROP TABLE IF EXISTS `in_stock`;

CREATE TABLE `in_stock` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `textbook_id` int DEFAULT NULL COMMENT '教材id',
  `num` int DEFAULT NULL COMMENT '入库数量',
  `price` double(10,2) DEFAULT NULL COMMENT '单价',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '单位',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='入库信息';

/*Data for the table `in_stock` */

insert  into `in_stock`(`id`,`textbook_id`,`num`,`price`,`unit`,`time`) values 
(1,12,10,38.00,'本','2024-11-01 10:55:35'),
(2,12,5,38.00,'本','2024-11-01 10:56:42'),
(3,12,1,38.00,'本','2024-11-01 10:56:51'),
(4,11,10,56.00,'本','2024-11-01 10:57:02'),
(5,10,10,46.00,'本','2024-11-01 10:58:46'),
(6,9,1,36.00,'本','2024-11-04 15:43:10'),
(7,4,9,55.00,'本','2024-11-04 16:25:21'),
(8,9,7,36.00,'本','2024-11-04 16:25:26'),
(9,7,13,29.00,'本','2024-11-04 16:25:35');

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `textbook_id` int DEFAULT NULL COMMENT '教材id',
  `store` int DEFAULT NULL COMMENT '库存数量',
  `price` double(10,2) DEFAULT NULL COMMENT '价格',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '库存单位',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '库存位置',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='库存信息表';

/*Data for the table `inventory` */

insert  into `inventory`(`id`,`textbook_id`,`store`,`price`,`unit`,`location`) values 
(5,12,14,38.00,'本','图书馆1层A区#1001'),
(6,11,10,56.00,'本','图书馆1层A区#1002'),
(7,10,9,46.00,'本','图书馆1层A区#1003'),
(8,9,5,36.00,'本','图书馆1层A区#1004'),
(9,4,8,55.00,'本',NULL),
(10,7,12,29.00,'本',NULL);

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公告标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '公告内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='系统公告表';

/*Data for the table `notice` */

insert  into `notice`(`id`,`title`,`content`,`time`) values 
(1,'2024-2025学年第一学期教材发放通知','本学期各班级教材已到货入库，请各班教务员尽快在系统中提交发放申请，学生按领取地点按时领取。','2024-07-16 15:51:17'),
(2,'教材库存盘点完成','本学期教材库存已全部盘点入库，库存信息可在系统中实时查询，如有短缺请及时联系管理员。','2024-07-16 15:52:22'),
(3,'系统使用说明','请各位用户妥善保管账号密码，教材领取情况及问题反馈请通过系统反馈模块提交，教务员会及时回复。','2024-07-16 15:52:56');

/*Table structure for table `out_stock` */

DROP TABLE IF EXISTS `out_stock`;

CREATE TABLE `out_stock` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `textbook_id` int DEFAULT NULL COMMENT '教材id',
  `class_id` int DEFAULT NULL COMMENT '班级id',
  `number` int DEFAULT NULL COMMENT '数量',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '领取地点',
  `out_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '申请时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `get_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '领取时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='教材发放信息';

/*Data for the table `out_stock` */

insert  into `out_stock`(`id`,`textbook_id`,`class_id`,`number`,`address`,`out_time`,`status`,`remark`,`get_time`) values 
(2,11,3,1,'图书馆1层','2024-11-04 12:02:12','拒绝','11月5日之前领取',NULL),
(3,12,1,2,'图书馆1层','2024-11-04 12:04:21','已领取','11月10日之前领取','2024-11-04 15:36:07'),
(4,10,2,1,'图书馆1层','2024-11-04 15:41:27','待领取','11月5日之前领取',NULL),
(5,9,5,1,'图书馆1层','2024-11-04 15:42:34','待领取','11月10号之前领取',NULL),
(6,9,4,1,'图书馆1层','2024-11-04 15:43:57','拒绝','11月10号之前领取',NULL);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `class_id` int DEFAULT NULL COMMENT '班级ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='学生信息';

/*Data for the table `student` */

insert  into `student`(`id`,`username`,`password`,`name`,`avatar`,`role`,`phone`,`email`,`class_id`) values 
(3,'20201002','123','李小明','http://localhost:9090/files/download/1749050193654-学生.png','STUDENT',NULL,NULL,2),
(4,'20201003','123','张三丰','http://localhost:9090/files/download/1749050193654-学生.png','STUDENT',NULL,NULL,3),
(5,'20201004','123','赵子龙','http://localhost:9090/files/download/1749050193654-学生.png','STUDENT',NULL,NULL,4),
(6,'20201005','123','孙斌','http://localhost:9090/files/download/1749050193654-学生.png','STUDENT',NULL,NULL,8),
(7,'20201006','123','李梦','http://localhost:9090/files/download/1749050193654-学生.png','STUDENT',NULL,NULL,5),
(8,'20201007','123','王菲','http://localhost:9090/files/download/1749050193654-学生.png','STUDENT',NULL,NULL,6),
(9,'20201009','123','春花','http://localhost:9090/files/download/1749050193654-学生.png','STUDENT','13988776699','ch@xm.com',3),
(123,'20201001','123','王大锤','http://localhost:9090/files/download/1749050193654-学生.png','STUDENT','13988997788','wdc@xm.com',1);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='教务员信息';

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`username`,`password`,`name`,`avatar`,`role`,`phone`,`email`) values 
(9,'llls','123','莉莉','http://localhost:9090/files/download/1749049911890-学校_教师.png','TEACHER','13988778877','lili@xm.com'),
(10,'zls','123','张峰','http://localhost:9090/files/download/1749049902017-学校_教师.png','TEACHER','13899888899','zf@xm.com'),
(11,'qls','123','齐硕','http://localhost:9090/files/download/1749049881920-学校_教师.png','TEACHER','13677889988','qs@xm.com'),
(12,'lls','123','李小曼','http://localhost:9090/files/download/1749049864176-学校_教师.png','TEACHER','13788998877','lxm@xm.com');

/*Table structure for table `textbook` */

DROP TABLE IF EXISTS `textbook`;

CREATE TABLE `textbook` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教材名称',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作者名称',
  `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '出版社',
  `publish_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '出版日期',
  `isbn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ISBN',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教材封面',
  `attribute` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教材属性',
  `type_id` int DEFAULT NULL COMMENT '教材类型id',
  `price` double(10,2) DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='教材信息';

/*Data for the table `textbook` */

insert  into `textbook`(`id`,`name`,`author`,`publisher`,`publish_date`,`isbn`,`cover`,`attribute`,`type_id`,`price`) values 
(5,'Java从入门到精通','张三','人民邮电出版社','2022-10-01','1234567892','http://localhost:9090/files/download/1749050339147-584e3d86fd6d7399.jpg!q80.jpg','课程教材',6,58.00),
(8,'中国近代史','张三','人民邮电出版社','2022-10-01','1234567895','http://localhost:9090/files/download/1749050326848-20210927171711734_bf8d97619fdb0afad2e6184107d37a0a.jpg','课外教材',10,58.00),
(9,'美国历史','张三','人民邮电出版社','2022-10-01','1232132131','http://localhost:9090/files/download/1749050320464-O1CN01Q8qNgh1YqslaJ7GhS_!!2210963111.jpg','课外教材',10,36.00),
(10,'C++ Primer Plus 第6版','史密斯','人民邮电出版社','2022-10-01','1232132133','http://localhost:9090/files/download/1749050302816-9fd6b55390cc20cc.jpg','课程教材',6,46.00),
(11,'计算机网络：自顶向下方法','史密斯','人民邮电出版社','2022-10-01','1232132134','http://localhost:9090/files/download/1749050281826-9fd6b55390cc20cc.jpg','课程教材',7,56.00),
(12,'人工智能（第3版）','王尔德','人民邮电出版社','2022-10-01','1232132138','http://localhost:9090/files/download/1749050272991-584e3d86fd6d7399.jpg!q80.jpg','课程教材',8,38.00);

/*Table structure for table `textbook_type` */

DROP TABLE IF EXISTS `textbook_type`;

CREATE TABLE `textbook_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='教材类型信息';

/*Data for the table `textbook_type` */

insert  into `textbook_type`(`id`,`name`) values 
(5,'电子与信息工程'),
(6,'计算机科学与技术'),
(7,'网络信息科学'),
(8,'机械与人工智能'),
(9,'英语'),
(10,'汉语言文学');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
