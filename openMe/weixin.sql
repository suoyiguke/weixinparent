/*
Navicat MySQL Data Transfer

Source Server         : 阿里云
Source Server Version : 50635
Source Host           : 120.79.64.12:3306
Source Database       : weixin

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-02-02 21:01:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum` (
  `forum_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NOT NULL,
  `description` mediumtext,
  `name` varchar(100) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `topiccount` int(11) unsigned zerofill DEFAULT NULL COMMENT '帖子数',
  `replycount` int(11) unsigned zerofill DEFAULT NULL COMMENT '回复数',
  PRIMARY KEY (`forum_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of forum
-- ----------------------------
INSERT INTO `forum` VALUES ('0', '1', '校内', '校内', null, null, null, null);
INSERT INTO `forum` VALUES ('1', '1', 'JAVA学习', 'JAVA学习', null, null, null, null);
INSERT INTO `forum` VALUES ('3', '1', '学习', '学习', null, null, null, null);
INSERT INTO `forum` VALUES ('4', '1', '死亡骑士', '多撒多', null, null, null, null);
INSERT INTO `forum` VALUES ('5', '1', 'MySQL是一个关系型数据库管理系统，由瑞典MySQL AB 公司开发，目前属于 Oracle 旗下产品。MySQL 是最流行的关系型数据库管理系统之一，在 WEB 应用方面，MySQL是最好的 RDBMS (Relational Database Management System，关系数据库管理系统) 应用软件。\nMySQL是一种关系数据库管理系统，关系数据库将数据保存在不同的表中，而不是将所有数据放在一个大仓库内，这样就增加了速度并提高了灵活性。\nMySQL所使用的 SQL 语言是用于访问数据库的最常用标准化语言。MySQL 软件采用了双授权政策，分为社区版和商业版，由于其体积小、速度快、总体拥有成本低，尤其是开放源码这一特点，一般中小型网站的开发都选择 MySQL 作为网站数据库。\n由于其社区版的性能卓越，搭配 PHP 和 Apache 可组成良好的开发环境。[1]', 'mysql', null, '2017-12-15 13:18:46', null, null);
INSERT INTO `forum` VALUES ('6', '1', '', '', null, '2017-12-27 16:31:31', null, null);

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `wj8` (`topic_id`),
  CONSTRAINT `wj8` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES ('0', '1', 'defualt.jpg');
INSERT INTO `image` VALUES ('2', '1', 'xxx.jpg');
INSERT INTO `image` VALUES ('35', null, '1511873689329789.jpg');
INSERT INTO `image` VALUES ('37', null, '1512885674216511.jpg');
INSERT INTO `image` VALUES ('38', null, '1512891322286364.jpg');
INSERT INTO `image` VALUES ('39', '23', '1512898633047596.jpg');
INSERT INTO `image` VALUES ('40', '23', '1512898636091118.jpg');
INSERT INTO `image` VALUES ('41', '24', '1512899427072128.jpg');
INSERT INTO `image` VALUES ('42', '24', '1512899451987051.jpg');
INSERT INTO `image` VALUES ('43', null, '1513004254580881.png');
INSERT INTO `image` VALUES ('44', null, '1513004497470096.png');
INSERT INTO `image` VALUES ('45', null, '1513004588207043.png');
INSERT INTO `image` VALUES ('46', null, '1513005226117319.png');
INSERT INTO `image` VALUES ('47', null, '1513005537330446.png');
INSERT INTO `image` VALUES ('48', null, '1513005672635285.png');
INSERT INTO `image` VALUES ('49', null, '1513005734971904.png');
INSERT INTO `image` VALUES ('50', null, '1513005925940456.png');
INSERT INTO `image` VALUES ('51', null, '1513006482753349.png');
INSERT INTO `image` VALUES ('52', null, '1513006592328344.png');
INSERT INTO `image` VALUES ('53', null, '1513007380271795.png');
INSERT INTO `image` VALUES ('54', null, '1513007509080005.png');
INSERT INTO `image` VALUES ('55', null, '1513007623327885.png');
INSERT INTO `image` VALUES ('59', null, '1513045901447405.jpg');
INSERT INTO `image` VALUES ('60', null, '1513046899862899.jpg');
INSERT INTO `image` VALUES ('61', null, '1513046989243680.jpg');
INSERT INTO `image` VALUES ('62', null, '1513047724407310.png');
INSERT INTO `image` VALUES ('63', null, '1513048462068479.png');
INSERT INTO `image` VALUES ('64', null, '1513054456141843.jpg');
INSERT INTO `image` VALUES ('65', null, '1513054793009867.jpg');
INSERT INTO `image` VALUES ('66', null, '1513055256253596.jpg');
INSERT INTO `image` VALUES ('67', null, '1513055420396655.jpg');
INSERT INTO `image` VALUES ('68', null, '1513055648092357.jpg');
INSERT INTO `image` VALUES ('69', null, '1513056435365173.jpg');
INSERT INTO `image` VALUES ('70', null, '1513056470020977.jpg');
INSERT INTO `image` VALUES ('71', null, '1513057366511763.jpg');
INSERT INTO `image` VALUES ('72', null, '1513058749716479.jpg');
INSERT INTO `image` VALUES ('73', null, '1513058914242541.jpg');
INSERT INTO `image` VALUES ('74', null, '1513059294044242.jpg');
INSERT INTO `image` VALUES ('75', null, '1513060395960734.jpg');
INSERT INTO `image` VALUES ('76', null, '1513065894870460.jpg');
INSERT INTO `image` VALUES ('77', null, '1513066675773526.jpg');
INSERT INTO `image` VALUES ('79', '57', '1513073032079739.jpg');
INSERT INTO `image` VALUES ('80', '58', '1513073177125839.png');
INSERT INTO `image` VALUES ('81', '59', '1513073372919061.png');
INSERT INTO `image` VALUES ('82', '64', '1513080104917765.png');
INSERT INTO `image` VALUES ('83', null, '1513083766637311.png');
INSERT INTO `image` VALUES ('84', null, '1513083933661331.png');
INSERT INTO `image` VALUES ('85', null, '1513083960687441.png');
INSERT INTO `image` VALUES ('86', '65', '1513084017264090.png');
INSERT INTO `image` VALUES ('87', null, '1513084104044416.jpg');
INSERT INTO `image` VALUES ('88', '66', '1513145225420061.jpg');
INSERT INTO `image` VALUES ('90', '68', '1513159486418595.jpg');
INSERT INTO `image` VALUES ('91', '69', '1513159878438533.jpg');
INSERT INTO `image` VALUES ('92', '70', '1513160615227616.jpg');
INSERT INTO `image` VALUES ('93', null, '1513161263037789.jpg');
INSERT INTO `image` VALUES ('94', '71', '1513162707095426.jpg');
INSERT INTO `image` VALUES ('95', '72', '1513164566400618.jpg');
INSERT INTO `image` VALUES ('96', null, '1513164640611087.jpg');
INSERT INTO `image` VALUES ('97', '73', '1513167088834159.jpg');
INSERT INTO `image` VALUES ('98', '74', '1513167430783399.jpg');
INSERT INTO `image` VALUES ('99', null, '1513168574783277.png');
INSERT INTO `image` VALUES ('100', null, '1513169611394855.png');
INSERT INTO `image` VALUES ('101', null, '1513169970506973.png');
INSERT INTO `image` VALUES ('102', null, '1513169971104351.png');
INSERT INTO `image` VALUES ('103', null, '1513319346858537.jpg');
INSERT INTO `image` VALUES ('104', null, '1513319448917717.jpg');
INSERT INTO `image` VALUES ('105', '101', '1513345409705431.jpg');
INSERT INTO `image` VALUES ('106', null, '1513493422289556.jpg');
INSERT INTO `image` VALUES ('107', '102', '1513495485145308.jpg');
INSERT INTO `image` VALUES ('108', '103', '1513496316742333.jpg');
INSERT INTO `image` VALUES ('109', '104', '1513497036867030.png');
INSERT INTO `image` VALUES ('110', null, '1513497305439310.jpg');
INSERT INTO `image` VALUES ('111', null, '1513497387129541.jpg');
INSERT INTO `image` VALUES ('112', '105', '1513497483498366.png');
INSERT INTO `image` VALUES ('113', '106', '1513498164129648.png');
INSERT INTO `image` VALUES ('115', '108', '1513515239117342.jpg');
INSERT INTO `image` VALUES ('116', null, '1513516990593151.jpg');
INSERT INTO `image` VALUES ('117', '109', '1513517929688299.jpg');
INSERT INTO `image` VALUES ('118', null, '1514793155134688.jpg');
INSERT INTO `image` VALUES ('119', '110', '1514793358726388.jpg');
INSERT INTO `image` VALUES ('120', null, '1514797127411289.jpg');
INSERT INTO `image` VALUES ('121', null, '1514797198243214.jpg');
INSERT INTO `image` VALUES ('122', null, '1514797222254809.jpg');
INSERT INTO `image` VALUES ('123', '111', '1514797911041712.jpg');
INSERT INTO `image` VALUES ('124', '112', '1514797966747893.jpg');
INSERT INTO `image` VALUES ('125', null, '1514900521065197.jpg');
INSERT INTO `image` VALUES ('126', null, '1514900541954535.jpg');
INSERT INTO `image` VALUES ('127', null, '1514904320836103.jpg');
INSERT INTO `image` VALUES ('128', '113', '1514904488799993.jpg');
INSERT INTO `image` VALUES ('129', null, '1515033615316638.jpg');
INSERT INTO `image` VALUES ('130', '114', '1515033653065699.jpg');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` int(255) NOT NULL AUTO_INCREMENT,
  `fromId` int(11) NOT NULL,
  `toId` int(11) NOT NULL,
  `messageText` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `messageDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`message_id`),
  KEY `wg10` (`fromId`),
  KEY `wg11` (`toId`),
  CONSTRAINT `wg10` FOREIGN KEY (`fromId`) REFERENCES `user` (`user_id`),
  CONSTRAINT `wg11` FOREIGN KEY (`toId`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '38', '35', '撸赢了吗？', '2017-12-18 15:24:28', '0');
INSERT INTO `message` VALUES ('2', '35', '38', '刚撸完，不知道赢了没', '2017-12-18 15:25:32', '0');
INSERT INTO `message` VALUES ('3', '38', '35', '撸赢了吗？', '2017-12-18 16:00:25', '0');
INSERT INTO `message` VALUES ('4', '38', '35', '撸赢了吗？', '2017-12-18 16:00:51', '0');
INSERT INTO `message` VALUES ('5', '38', '35', '撸赢了吗？', '2017-12-20 16:29:41', '0');
INSERT INTO `message` VALUES ('6', '38', '35', '撸赢了吗？', '2017-12-20 20:10:55', '0');
INSERT INTO `message` VALUES ('7', '38', '35', '撸赢了吗？', '2017-12-20 20:20:52', '0');
INSERT INTO `message` VALUES ('8', '38', '35', '撸赢了吗？', '2017-12-20 20:21:55', '0');
INSERT INTO `message` VALUES ('9', '38', '35', '撸赢了吗？', '2017-12-23 13:52:28', '0');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `Ref22` (`topic_id`),
  KEY `Ref63` (`user_id`),
  CONSTRAINT `wj2` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`),
  CONSTRAINT `wj3` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '56', '9', '呵呵，你骗人的吧？', '2017-12-13 13:34:44');
INSERT INTO `reply` VALUES ('2', '73', '35', 'test', '2017-12-15 15:50:29');
INSERT INTO `reply` VALUES ('3', '71', '35', 'mouse', '2017-12-15 15:55:37');
INSERT INTO `reply` VALUES ('4', '71', '35', 'mouse_2', '2017-12-15 15:59:06');
INSERT INTO `reply` VALUES ('5', '71', '35', 'haha', '2017-12-15 16:17:11');
INSERT INTO `reply` VALUES ('6', '71', '35', 'haha', '2017-12-15 16:17:23');
INSERT INTO `reply` VALUES ('7', '71', '35', 'haha', '2017-12-15 16:17:24');
INSERT INTO `reply` VALUES ('8', '71', '35', 'haha', '2017-12-15 16:17:25');
INSERT INTO `reply` VALUES ('9', '71', '35', '小喇叭', '2017-12-15 16:19:48');
INSERT INTO `reply` VALUES ('10', '71', '35', '哈哈哈', '2017-12-15 16:33:25');
INSERT INTO `reply` VALUES ('11', '71', '35', 'null', '2017-12-15 16:35:35');
INSERT INTO `reply` VALUES ('12', '71', '35', '你无野啊哇(⊙o⊙)', '2017-12-15 21:06:00');
INSERT INTO `reply` VALUES ('13', '71', '35', 'null', '2017-12-15 21:34:01');
INSERT INTO `reply` VALUES ('14', '71', '35', 'null', '2017-12-15 21:34:54');
INSERT INTO `reply` VALUES ('15', '71', '35', 'null', '2017-12-15 21:35:06');
INSERT INTO `reply` VALUES ('16', '71', '35', 'haha', '2017-12-15 21:37:54');
INSERT INTO `reply` VALUES ('17', '71', '35', 'haha\nhahha\nhahahha', '2017-12-15 21:38:19');
INSERT INTO `reply` VALUES ('18', '71', '35', '哈哈哈\n哈哈哈\n回家', '2017-12-15 21:39:09');
INSERT INTO `reply` VALUES ('19', '71', '35', '好2哈poorguy小喇叭poorguy小喇叭poorguy广州哈哈哈广州哈哈哈广州小喇叭广州小喇叭广州哈哈哈哈哈哈哈哈哈广州小喇叭poorguy广州哈哈哈哈哈哈广州小喇叭广州哈哈哈哈哈哈广州小喇叭poorguy小喇叭‘’‘’‘’啊啊啊啊poorguy小喇叭poorguy小喇叭poorguy小喇叭poorguy小喇叭poorguy小喇叭poorguy小喇叭poorguy小喇叭poorguy小喇叭poorguy小喇叭poorguy小喇叭poorguy小喇叭poorguy小喇叭', '2017-12-15 21:40:21');
INSERT INTO `reply` VALUES ('20', '101', '35', 'hahaha', '2017-12-15 21:47:38');
INSERT INTO `reply` VALUES ('21', '101', '35', 'test', '2017-12-15 21:48:35');
INSERT INTO `reply` VALUES ('22', '101', '35', 'xlx\nhahaha', '2017-12-15 21:50:15');
INSERT INTO `reply` VALUES ('23', '71', '35', 'test', '2017-12-15 21:56:13');
INSERT INTO `reply` VALUES ('24', '71', '35', 'this is ，\nwhy we play。', '2017-12-15 21:58:24');
INSERT INTO `reply` VALUES ('25', '71', '35', '丢那星', '2017-12-15 22:34:07');
INSERT INTO `reply` VALUES ('26', '71', '35', '测试时间', '2017-12-15 23:46:07');
INSERT INTO `reply` VALUES ('27', '71', '35', 'test\ntst', '2017-12-17 11:29:33');
INSERT INTO `reply` VALUES ('28', '101', '35', '评论', '2017-12-17 11:49:12');
INSERT INTO `reply` VALUES ('29', '74', '35', '沙发', '2017-12-17 12:07:53');
INSERT INTO `reply` VALUES ('30', '74', '35', '沙发', '2017-12-17 12:08:15');
INSERT INTO `reply` VALUES ('31', '56', '38', 'are you kidme', '2017-12-17 14:49:25');
INSERT INTO `reply` VALUES ('32', '71', '38', '老鼠？', '2017-12-17 14:51:05');
INSERT INTO `reply` VALUES ('33', '104', '35', 'hahah', '2017-12-17 15:52:27');
INSERT INTO `reply` VALUES ('34', '105', '35', 'test', '2017-12-17 15:58:19');
INSERT INTO `reply` VALUES ('63', '108', '35', '小喇叭', '2017-12-17 21:37:02');
INSERT INTO `reply` VALUES ('64', '109', '35', '妖刀', '2017-12-17 21:40:10');
INSERT INTO `reply` VALUES ('65', '109', '35', '哈哈', '2017-12-26 22:29:03');
INSERT INTO `reply` VALUES ('66', '108', '38', 'sm', '2018-01-02 22:44:09');
INSERT INTO `reply` VALUES ('67', '57', '35', '小喇叭', '2018-01-04 10:31:59');
INSERT INTO `reply` VALUES ('68', '57', '35', '🤓🤓🤓', '2018-01-04 10:33:48');
INSERT INTO `reply` VALUES ('69', '114', '35', '😍😍😍', '2018-01-04 10:41:03');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=628 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('403', '广东实验中学');
INSERT INTO `school` VALUES ('404', '从化第二中学（从化二中）');
INSERT INTO `school` VALUES ('405', '广州市铁一中学');
INSERT INTO `school` VALUES ('406', '广州康复实验学校');
INSERT INTO `school` VALUES ('407', '广州市天河外国语学校');
INSERT INTO `school` VALUES ('408', '广州市育才中学');
INSERT INTO `school` VALUES ('409', '广州高级中学');
INSERT INTO `school` VALUES ('410', '广州真光中学高中部');
INSERT INTO `school` VALUES ('411', '广州岭南画派纪念中学');
INSERT INTO `school` VALUES ('412', '广州长堤真光中学');
INSERT INTO `school` VALUES ('413', '广州第十中学');
INSERT INTO `school` VALUES ('414', '广州旅游商务职业学校');
INSERT INTO `school` VALUES ('415', '广州南海中学');
INSERT INTO `school` VALUES ('416', '广州科学城中学');
INSERT INTO `school` VALUES ('417', '广州第六十六中学');
INSERT INTO `school` VALUES ('418', '广州兴农中学');
INSERT INTO `school` VALUES ('419', '白云区第二中学');
INSERT INTO `school` VALUES ('420', '广州市番禺区市桥第二中学(高中部)');
INSERT INTO `school` VALUES ('421', '广州华师附中学校(高中部)');
INSERT INTO `school` VALUES ('422', '广州流花中学');
INSERT INTO `school` VALUES ('423', '广州第一中学');
INSERT INTO `school` VALUES ('424', '从化第三中学（从化三中）');
INSERT INTO `school` VALUES ('425', '广州海珠实验中学');
INSERT INTO `school` VALUES ('426', '广州市越秀外国语学校');
INSERT INTO `school` VALUES ('427', '广州西关外国语学校');
INSERT INTO `school` VALUES ('428', '广州禺山高级中学');
INSERT INTO `school` VALUES ('429', '广州华侨中学');
INSERT INTO `school` VALUES ('430', '广州第十三中学');
INSERT INTO `school` VALUES ('431', '广州第九十四中学');
INSERT INTO `school` VALUES ('432', '广州第五中学');
INSERT INTO `school` VALUES ('433', '广州美术中学');
INSERT INTO `school` VALUES ('434', '广州广雅中学');
INSERT INTO `school` VALUES ('435', '广州华南师范大学附属中学');
INSERT INTO `school` VALUES ('436', '广州增城市荔城中学');
INSERT INTO `school` VALUES ('437', '广州伟伦体育学校');
INSERT INTO `school` VALUES ('438', '广州第八十三中学');
INSERT INTO `school` VALUES ('439', '广州广东中学(高中部)');
INSERT INTO `school` VALUES ('440', '广州中学(高中部)');
INSERT INTO `school` VALUES ('441', '广州知用中学');
INSERT INTO `school` VALUES ('442', '广州执信中学');
INSERT INTO `school` VALUES ('443', '广州协和中学');
INSERT INTO `school` VALUES ('444', '广州南武中学');
INSERT INTO `school` VALUES ('445', '广州第六十三中学');
INSERT INTO `school` VALUES ('446', '广州广东实验中学');
INSERT INTO `school` VALUES ('447', '广州东风实验中学');
INSERT INTO `school` VALUES ('448', '广州第八十二中学');
INSERT INTO `school` VALUES ('449', '广州第六十七中学');
INSERT INTO `school` VALUES ('450', '广州第七十五中学');
INSERT INTO `school` VALUES ('451', '广州第九十五中学');
INSERT INTO `school` VALUES ('452', '广州第四十四中学');
INSERT INTO `school` VALUES ('453', '广州第七十三中学');
INSERT INTO `school` VALUES ('454', '广州广东第二师范学院附属中学');
INSERT INTO `school` VALUES ('455', '广州第九十三中学');
INSERT INTO `school` VALUES ('456', '广州中学(原第四十三中学)');
INSERT INTO `school` VALUES ('457', '广州第九十六中学');
INSERT INTO `school` VALUES ('458', '广州天翔职业高级中学');
INSERT INTO `school` VALUES ('459', '广州财经职业高级中学');
INSERT INTO `school` VALUES ('460', '广州贸易职业高级中学');
INSERT INTO `school` VALUES ('461', '广州金鹰职业高级中学');
INSERT INTO `school` VALUES ('462', '广州纪元职业高级中学');
INSERT INTO `school` VALUES ('463', '广州恒福中学');
INSERT INTO `school` VALUES ('464', '广州第四中学');
INSERT INTO `school` VALUES ('465', '广州培英中学');
INSERT INTO `school` VALUES ('466', '广州大学实验中学');
INSERT INTO `school` VALUES ('467', '广州第十六中学');
INSERT INTO `school` VALUES ('468', '广州第九十七中学');
INSERT INTO `school` VALUES ('469', '广州第八十六中学');
INSERT INTO `school` VALUES ('470', '广州第七十二中学');
INSERT INTO `school` VALUES ('471', '广州第八十九中学');
INSERT INTO `school` VALUES ('472', '广州第八十四中学');
INSERT INTO `school` VALUES ('473', '广州第一〇〇中学');
INSERT INTO `school` VALUES ('474', '广州花地中学(原第一一九中学)');
INSERT INTO `school` VALUES ('475', '广州第四十六中学');
INSERT INTO `school` VALUES ('476', '广州陈嘉庚纪念中学(原第三十中学)');
INSERT INTO `school` VALUES ('477', '广州第九十中学');
INSERT INTO `school` VALUES ('478', '广州光明职业高级中学');
INSERT INTO `school` VALUES ('479', '广州大学附属中学');
INSERT INTO `school` VALUES ('480', '广州职业高级中学');
INSERT INTO `school` VALUES ('481', '广州浩今职业高级中学');
INSERT INTO `school` VALUES ('482', '广州实用职业高级中学');
INSERT INTO `school` VALUES ('483', '广州韦涌华侨中学');
INSERT INTO `school` VALUES ('484', '钟落潭中学');
INSERT INTO `school` VALUES ('485', '广州江南中学');
INSERT INTO `school` VALUES ('486', '广州星海中学');
INSERT INTO `school` VALUES ('487', '广州五羊中学');
INSERT INTO `school` VALUES ('488', '广州东环中学');
INSERT INTO `school` VALUES ('489', '广州番禺中学');
INSERT INTO `school` VALUES ('490', '广州豪贤中学');
INSERT INTO `school` VALUES ('491', '广州天秀中学');
INSERT INTO `school` VALUES ('492', '广州钟村中学');
INSERT INTO `school` VALUES ('493', '广州联安中学');
INSERT INTO `school` VALUES ('494', '广州文冲中学');
INSERT INTO `school` VALUES ('495', '广州东圃中学');
INSERT INTO `school` VALUES ('496', '广州广园中学');
INSERT INTO `school` VALUES ('497', '广州榄核中学');
INSERT INTO `school` VALUES ('498', '广州南石中学');
INSERT INTO `school` VALUES ('499', '广州石基中学');
INSERT INTO `school` VALUES ('500', '广州长兴中学');
INSERT INTO `school` VALUES ('501', '广州聚德中学');
INSERT INTO `school` VALUES ('502', '广州骏景中学');
INSERT INTO `school` VALUES ('503', '广州启明中学');
INSERT INTO `school` VALUES ('504', '广州金源中学');
INSERT INTO `school` VALUES ('505', '广州仲元中学');
INSERT INTO `school` VALUES ('506', '广州新市中学');
INSERT INTO `school` VALUES ('507', '广州象贤中学');
INSERT INTO `school` VALUES ('508', '广州东漖中学');
INSERT INTO `school` VALUES ('509', '广州培正中学');
INSERT INTO `school` VALUES ('510', '广州灵山中学');
INSERT INTO `school` VALUES ('511', '广州明德中学');
INSERT INTO `school` VALUES ('512', '广州晓园中学');
INSERT INTO `school` VALUES ('513', '广州石井中学');
INSERT INTO `school` VALUES ('514', '广州龙岗中学');
INSERT INTO `school` VALUES ('515', '广州穗华中学');
INSERT INTO `school` VALUES ('516', '广州同和中学');
INSERT INTO `school` VALUES ('517', '广州海鸥中学');
INSERT INTO `school` VALUES ('518', '广州新华中学');
INSERT INTO `school` VALUES ('519', '广州洛溪新城中学');
INSERT INTO `school` VALUES ('520', '广州沙头中学');
INSERT INTO `school` VALUES ('521', '广州市白云中学');
INSERT INTO `school` VALUES ('522', '广州花山中学');
INSERT INTO `school` VALUES ('523', '广州新和中学');
INSERT INTO `school` VALUES ('524', '广州太和中学');
INSERT INTO `school` VALUES ('525', '广州泰安中学');
INSERT INTO `school` VALUES ('526', '广州培新中学');
INSERT INTO `school` VALUES ('527', '万顷沙中学');
INSERT INTO `school` VALUES ('528', '广州汇侨中学');
INSERT INTO `school` VALUES ('529', '广州黄埔中学');
INSERT INTO `school` VALUES ('530', '广州石化中学');
INSERT INTO `school` VALUES ('531', '广州象圣中学');
INSERT INTO `school` VALUES ('532', '广州大岗中学');
INSERT INTO `school` VALUES ('533', '广州成龙中学');
INSERT INTO `school` VALUES ('534', '广州汾水中学');
INSERT INTO `school` VALUES ('535', '广州狮岭中学');
INSERT INTO `school` VALUES ('536', '广州赤岗中学');
INSERT INTO `school` VALUES ('537', '广州侨联中学');
INSERT INTO `school` VALUES ('538', '广州七十六中学');
INSERT INTO `school` VALUES ('539', '广州开发区中学');
INSERT INTO `school` VALUES ('540', '广州邝维煜纪念中学');
INSERT INTO `school` VALUES ('541', '广州四中聚贤中学');
INSERT INTO `school` VALUES ('542', '广州六中珠江中学');
INSERT INTO `school` VALUES ('543', '广州市番禺区南沙中学');
INSERT INTO `school` VALUES ('544', '广州何贵荣纪念中学');
INSERT INTO `school` VALUES ('545', '广州桥城中学');
INSERT INTO `school` VALUES ('546', '广州盘龙中学');
INSERT INTO `school` VALUES ('547', '广州英东中学');
INSERT INTO `school` VALUES ('548', '广州新港中学');
INSERT INTO `school` VALUES ('549', '广州颜乐天纪念中学');
INSERT INTO `school` VALUES ('550', '广州双桥中学');
INSERT INTO `school` VALUES ('551', '广州绿翠中学');
INSERT INTO `school` VALUES ('552', '广州市白云区雅瑶中学');
INSERT INTO `school` VALUES ('553', '广州劬劳中学');
INSERT INTO `school` VALUES ('554', '广州华颖中学');
INSERT INTO `school` VALUES ('555', '广州秀全中学');
INSERT INTO `school` VALUES ('556', '广州九潭中学');
INSERT INTO `school` VALUES ('557', '广州麒麟中学');
INSERT INTO `school` VALUES ('558', '广州四十八中学');
INSERT INTO `school` VALUES ('559', '广州民航子弟学校中学部');
INSERT INTO `school` VALUES ('560', '广州信孚泽德中学');
INSERT INTO `school` VALUES ('561', '广州龙江中学');
INSERT INTO `school` VALUES ('562', '广州康乐中学');
INSERT INTO `school` VALUES ('563', '广州迳口中学');
INSERT INTO `school` VALUES ('564', '广州永新中学');
INSERT INTO `school` VALUES ('565', '广州造船厂中学');
INSERT INTO `school` VALUES ('566', '广州黄石中学');
INSERT INTO `school` VALUES ('567', '从化市太平中学');
INSERT INTO `school` VALUES ('568', '广州金沙中学');
INSERT INTO `school` VALUES ('569', '广州南洋英文中学');
INSERT INTO `school` VALUES ('570', '广州嘉福中学');
INSERT INTO `school` VALUES ('571', '广州宁西中学');
INSERT INTO `school` VALUES ('572', '广州象达中学');
INSERT INTO `school` VALUES ('573', '广州南国学校中学部');
INSERT INTO `school` VALUES ('574', '广州长江中学');
INSERT INTO `school` VALUES ('575', '广州圆玄中学');
INSERT INTO `school` VALUES ('576', '广州桥兴中学');
INSERT INTO `school` VALUES ('577', '广州西洲中学');
INSERT INTO `school` VALUES ('578', '广州南岗中学');
INSERT INTO `school` VALUES ('579', '广州环城中学');
INSERT INTO `school` VALUES ('580', '广州东涌中学');
INSERT INTO `school` VALUES ('581', '广州北兴中学');
INSERT INTO `school` VALUES ('582', '增城市派潭中学');
INSERT INTO `school` VALUES ('583', '广州棋杆中学');
INSERT INTO `school` VALUES ('584', '广州象骏中学');
INSERT INTO `school` VALUES ('585', '广州文船中学');
INSERT INTO `school` VALUES ('586', '广州江村中学');
INSERT INTO `school` VALUES ('587', '广州珠江中学');
INSERT INTO `school` VALUES ('588', '广州九十九中学');
INSERT INTO `school` VALUES ('589', '广州新元中学');
INSERT INTO `school` VALUES ('590', '广州潭山中学');
INSERT INTO `school` VALUES ('591', '广州芙蓉中学');
INSERT INTO `school` VALUES ('592', '广州天泽中学');
INSERT INTO `school` VALUES ('593', '广州信孚教育集团黄石中学');
INSERT INTO `school` VALUES ('594', '广州城郊中学');
INSERT INTO `school` VALUES ('595', '广州大敦中学');
INSERT INTO `school` VALUES ('596', '广州沙埔镇沙埔中学');
INSERT INTO `school` VALUES ('597', '广州长岗中学');
INSERT INTO `school` VALUES ('598', '广州神岗中学');
INSERT INTO `school` VALUES ('599', '从化市良口中学');
INSERT INTO `school` VALUES ('600', '广州沙滘中学');
INSERT INTO `school` VALUES ('601', '广州玉岩中学');
INSERT INTO `school` VALUES ('602', '从化中学');
INSERT INTO `school` VALUES ('603', '广州第六中学');
INSERT INTO `school` VALUES ('604', '广州第四十二中学');
INSERT INTO `school` VALUES ('605', '广州思源学校');
INSERT INTO `school` VALUES ('606', '广州第三十一中学');
INSERT INTO `school` VALUES ('607', '广州第一〇九中学');
INSERT INTO `school` VALUES ('608', '广州第二十一中学');
INSERT INTO `school` VALUES ('609', '广州第七中学');
INSERT INTO `school` VALUES ('610', '广州第三中学');
INSERT INTO `school` VALUES ('611', '广州第一中学(高中部)');
INSERT INTO `school` VALUES ('612', '从化第五中学');
INSERT INTO `school` VALUES ('613', '广州第八十中学');
INSERT INTO `school` VALUES ('614', '广州达德综合高级中学');
INSERT INTO `school` VALUES ('615', '广州第八中学');
INSERT INTO `school` VALUES ('616', '从化第四中学');
INSERT INTO `school` VALUES ('617', '从化第三中学');
INSERT INTO `school` VALUES ('618', '广州第十一中学');
INSERT INTO `school` VALUES ('619', '从化第二中学');
INSERT INTO `school` VALUES ('620', '广州市第二中学');
INSERT INTO `school` VALUES ('621', '广州第四十一中学');
INSERT INTO `school` VALUES ('622', '广州市天河中学');
INSERT INTO `school` VALUES ('623', '增城市新塘镇永和中学');
INSERT INTO `school` VALUES ('624', '广州第六十五中学');
INSERT INTO `school` VALUES ('625', '广州第九十五中学');
INSERT INTO `school` VALUES ('626', '增城中学');
INSERT INTO `school` VALUES ('627', '广州第八十一中学');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `forum_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` mediumtext,
  `createtime` datetime DEFAULT NULL,
  `type` int(11) unsigned NOT NULL DEFAULT '0',
  `replycount` int(11) unsigned NOT NULL DEFAULT '0',
  `lastreply` varchar(100) DEFAULT NULL,
  `lastupdatetime` date DEFAULT NULL,
  `likenum` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`topic_id`),
  KEY `Ref11` (`forum_id`),
  KEY `wj4` (`user_id`),
  CONSTRAINT `wj1` FOREIGN KEY (`forum_id`) REFERENCES `forum` (`forum_id`),
  CONSTRAINT `wj4` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', '0', '35', '谈谈spring', 'Spring是一个开放源代码的设计层面框架，他解决的是业务逻辑层和其他各层的松耦合问题，因此它将面向接口的编程思想贯穿整个系统应用。Spring是于2003 年兴起的一个轻量级的Java 开发框架，由Rod Johnson创建。简单来说，Spring是一个分层的JavaSE/EEfull-stack(一站式) 轻量级开源框架。', '2017-11-21 00:00:00', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('2', '0', '35', 'ejb为什么淘汰了', '     \r\n要说到为什么使用EJB，不得不先问一个问题，为什么使用J2EE规范？   \r\nJ2EE是一种利用java2平台来简化企业解决方案的开发，部署和管理相关复杂问题的体系结构，可以用于开发大型的多层的以及分布式的企业级应用系统。作为一种规范，技术框架和技术的集合，J2EE主要是用于分布式企业级应用的框架。\r\n企业级应用是指那些为商业组织，大型企业而创建并部署的解决方案及应用，这些大型企业级应用结构复杂，涉及到的外部资源众多，事务密集，数据量大，用户数量多，有较强的安全性考虑。通俗的理解企业级应用就想是一个城市的规划，我们需要考虑和解决的事情太多，之间的复杂性也太多，而我们要自己解决这个问题需要耗费大量的人力物力，也不一定可以很好的解决，而j2ee就是为了规范城市规划，规范解决这些复杂问题而产生的。\r\n所以如果是一个企业级的复杂的分布式系统应用，j2ee是我们很好的选择，而实现j2ee规范最好的EJB当然是更好的现成的框架选择。当然小型应用，不需要分布式，不需要复杂事务处理就可以考虑不适用J2EE,当然EJB也不用考虑了。\r\nEJB可以帮助我们解决企业级开发分布式，分布式事务等这些问题，让程序员从复杂的关系中抽离出来，专心开发企业业务逻辑。', '2017-11-22 00:00:00', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('3', '0', '35', 'adas', 'xxasdasdsadasdsadsadsadasd', '2017-11-27 00:00:00', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('4', '0', '35', '大飒飒', '阿萨德撒多撒多撒多', '2017-11-27 00:00:00', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('5', '0', '35', 'jdbcxx', 'jdbcxxjdbcxxjdbcxxjdbcxxjdbcxx', '2017-11-28 00:00:00', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('23', '0', '35', 'c++学习', 'c++是c的加强版！！！', '2017-12-10 00:00:00', '1', '0', null, null, '0');
INSERT INTO `topic` VALUES ('24', '0', '35', 'python学习', 'python学习python学习python学习python学习', '2017-12-10 00:00:00', '1', '0', null, null, '0');
INSERT INTO `topic` VALUES ('55', '0', '35', '', '我的天啊啊啊', '2017-12-12 00:00:00', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('56', '0', '35', '大数据', '进入2012年，大数据（big data）一词越来越多地被提及，人们用它来描述和定义信息爆炸时代产生的海量数 大数据时代来临 大数据时代来临 据，并命名与之相关的技术发展与创新。它已经上过《纽约时报》《华尔街日报》的专栏封面，进入美国白宫官网的新闻，现身在国内一些互联网主题的讲座沙龙中，甚至被嗅觉灵敏的国金证券、国泰君安、银河证券等写进了投资推荐报告。[1]  数据正在迅速膨胀并变大，它决定着企业的未来发展，虽然很多企业可能并没有意识到数据爆炸性增长带来问题的隐患，但是随着时间的推移，人们将越来越多的意识到数据对企业的重要性。 正如《纽约时报》2012年2月的一篇专栏中所称，“大数据”时代已经降临，在商业、经济及其他领域中，决策将日益基于数据和分析而作出，而并非基于经验和直觉。 哈佛大学社会学教授加里·金说：“这是一场革命，庞大的数据资源使得各个领域开始了量化进程，无论学术界、商界还是政府，所有领域都将开始这种进程。”[2]', '2017-12-12 00:00:00', '0', '1', null, null, '1');
INSERT INTO `topic` VALUES ('57', '0', '35', '大数据', '进入2012年，大数据（big data）一词越来越多地被提及，人们用它来描述和定义信息爆炸时代产生的海量数 大数据时代来临 大数据时代来临 据，并命名与之相关的技术发展与创新。它已经上过《纽约时报》《华尔街日报》的专栏封面，进入美国白宫官网的新闻，现身在国内一些互联网主题的讲座沙龙中，甚至被嗅觉灵敏的国金证券、国泰君安、银河证券等写进了投资推荐报告。[1]  数据正在迅速膨胀并变大，它决定着企业的未来发展，虽然很多企业可能并没有意识到数据爆炸性增长带来问题的隐患，但是随着时间的推移，人们将越来越多的意识到数据对企业的重要性。 正如《纽约时报》2012年2月的一篇专栏中所称，“大数据”时代已经降临，在商业、经济及其他领域中，决策将日益基于数据和分析而作出，而并非基于经验和直觉。 哈佛大学社会学教授加里·金说：“这是一场革命，庞大的数据资源使得各个领域开始了量化进程，无论学术界、商界还是政府，所有领域都将开始这种进程。”[2]', '2017-12-12 00:00:00', '0', '2', null, null, '0');
INSERT INTO `topic` VALUES ('58', '0', '35', null, 'test', '2017-12-12 00:00:00', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('59', '0', '35', null, 'kijjiioj', '2017-12-12 00:00:00', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('63', '0', '35', '大', '大加索尔111111', '2017-12-12 19:49:04', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('64', '0', '35', null, 'aaaa', '2017-12-12 20:01:43', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('65', '0', '35', null, '小喇叭', '2017-12-12 21:06:57', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('66', '0', '38', null, '细说阿里云', '2017-12-13 14:07:05', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('68', '0', '38', null, 'helloword', '2017-12-13 18:04:46', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('69', '0', '36', null, '老板，打包个鸡腿给我', '2017-12-13 18:11:18', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('70', '0', '38', null, '叫我大神凯', '2017-12-13 18:23:35', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('71', '0', '39', null, '猜猜这是啥', '2017-12-13 18:58:27', '0', '1', null, null, '0');
INSERT INTO `topic` VALUES ('72', '0', '35', null, '肾仔', '2017-12-13 19:29:26', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('73', '0', '35', null, 'test', '2017-12-13 20:11:28', '0', '0', null, null, '1');
INSERT INTO `topic` VALUES ('74', '0', '35', null, '', '2017-12-13 20:17:10', '0', '2', null, null, '1');
INSERT INTO `topic` VALUES ('101', '0', '35', null, '不识庐山真面目，\n只缘身在此山中。', '2017-12-15 21:43:29', '0', '1', null, null, '1');
INSERT INTO `topic` VALUES ('102', '0', '38', null, '坐看运气', '2017-12-17 15:24:42', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('103', '0', '38', null, '第一', '2017-12-17 15:38:36', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('104', '5', '35', null, 'mysql', '2017-12-17 15:50:36', '1', '1', null, null, '0');
INSERT INTO `topic` VALUES ('105', '5', '35', null, 'twst', '2017-12-17 15:58:03', '1', '1', null, null, '0');
INSERT INTO `topic` VALUES ('106', '3', '35', null, '学习', '2017-12-17 16:09:24', '1', '0', null, null, '0');
INSERT INTO `topic` VALUES ('108', '1', '35', null, 'jawbreaker', '2017-12-17 20:53:59', '1', '2', null, null, '1');
INSERT INTO `topic` VALUES ('109', '1', '38', null, 'nsnns', '2017-12-17 21:38:50', '1', '2', null, null, '2');
INSERT INTO `topic` VALUES ('110', '0', '35', null, '下拉啊', '2018-01-01 15:55:59', '0', '0', null, null, '1');
INSERT INTO `topic` VALUES ('111', '0', '35', null, '', '2018-01-01 17:11:51', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('112', '0', '35', null, '', '2018-01-01 17:12:47', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('113', '0', '38', null, '战斗，战斗', '2018-01-02 22:48:09', '0', '0', null, null, '0');
INSERT INTO `topic` VALUES ('114', '1', '35', null, 'test', '2018-01-04 10:40:53', '1', '1', null, null, '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(100) NOT NULL,
  `school_id` int(11) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `sex` char(10) DEFAULT NULL,
  `autograph` varchar(100) DEFAULT NULL,
  `headImage_id` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`,`open_id`),
  UNIQUE KEY `weione` (`nickname`),
  UNIQUE KEY `wopenweiyi` (`open_id`),
  KEY `Ref84` (`school_id`),
  KEY `wj9` (`headImage_id`),
  KEY `user_id` (`user_id`),
  KEY `user_id_2` (`user_id`),
  CONSTRAINT `Refschool4` FOREIGN KEY (`school_id`) REFERENCES `school` (`school_id`),
  CONSTRAINT `wj9` FOREIGN KEY (`headImage_id`) REFERENCES `image` (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'asdasda', '405', '凯凯', '男', '我就是我颜色不一样的烟火！', '0');
INSERT INTO `user` VALUES ('2', '124321ewq', '405', '尹璇', '女', '喔喔喔', '0');
INSERT INTO `user` VALUES ('3', 'dasda', '405', '凯凯1', '男', '我就是我颜色不一样的烟火！', '0');
INSERT INTO `user` VALUES ('4', '21321eda', '405', '凯2', '男', '我就是我颜色不一样的烟火！', '0');
INSERT INTO `user` VALUES ('5', 'adas12', '405', '凯凯3', '男', '我就是我颜色不一样的烟火！', '0');
INSERT INTO `user` VALUES ('6', 'a12e2', '405', '凯4', '男', '我就是我颜色不一样的烟火！', '0');
INSERT INTO `user` VALUES ('7', 'dasdqwe', '405', '凯凯3xx', '男', '我就是我颜色不一样的烟火！', '0');
INSERT INTO `user` VALUES ('8', 'das12rfca', '403', '凯凯dsa', '男', '我就是我颜色不一样的烟火！', '0');
INSERT INTO `user` VALUES ('9', '1qedqw', '403', '凯f', '男', '我就是我颜色不一样的烟火！', '0');
INSERT INTO `user` VALUES ('35', 'oiO0e0Sa1f0t1csnLxd5TlNsEHQ0', '405', 'PoorGuy', '男', '错过了春天，可会再花开', '129');
INSERT INTO `user` VALUES ('36', 'oiO0e0dMItRyuqpHbeoji-mp5r64', '403', '没有脚的鞋', '男', '啦啦啦', '126');
INSERT INTO `user` VALUES ('38', 'oiO0e0a6ra6fYNFVlyYLDLNN7-vY', '408', '卡门', '男', '战斗！战斗', '127');
INSERT INTO `user` VALUES ('39', 'oiO0e0dAKBjbYiu4uVqcLA853xCI', '408', '夏忆', '女', '多说不如多做', '0');

-- ----------------------------
-- Table structure for user_topic
-- ----------------------------
DROP TABLE IF EXISTS `user_topic`;
CREATE TABLE `user_topic` (
  `user_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`topic_id`),
  KEY `wj6` (`topic_id`),
  CONSTRAINT `wj5` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `wj6` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_topic
-- ----------------------------
INSERT INTO `user_topic` VALUES ('38', '56');
INSERT INTO `user_topic` VALUES ('35', '73');
INSERT INTO `user_topic` VALUES ('35', '74');
INSERT INTO `user_topic` VALUES ('35', '101');
INSERT INTO `user_topic` VALUES ('38', '108');
INSERT INTO `user_topic` VALUES ('35', '109');
INSERT INTO `user_topic` VALUES ('36', '109');
INSERT INTO `user_topic` VALUES ('35', '110');
INSERT INTO `user_topic` VALUES ('35', '114');
