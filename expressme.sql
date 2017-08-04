/*
Navicat MySQL Data Transfer

Source Server         : cs
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : expressme

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-07-08 10:39:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` varchar(255) NOT NULL,
  `phone` varchar(18) DEFAULT NULL COMMENT '手机号',
  `wechat` varchar(255) DEFAULT NULL COMMENT '微信帐号',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `street` varchar(255) DEFAULT NULL COMMENT '街道',
  `credit` float DEFAULT '10' COMMENT '信用等级',
  `registerTime` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('2c90a4485a5fdc07015a5fdc0b2d0000', '13423433456', 'wec', '张三', '1a1dc91c907325c69271ddf0c944bc72', '/expimgs/client/2c90a4485a5fdc07015a5fdc0b2d0000.jpg', '北京东城', '石头街', '10', '2017-03-14 17:15:24');

-- ----------------------------
-- Table structure for clientevaluate
-- ----------------------------
DROP TABLE IF EXISTS `clientevaluate`;
CREATE TABLE `clientevaluate` (
  `id` varchar(255) NOT NULL,
  `mark` float(3,1) DEFAULT '10.0' COMMENT '评分',
  `text` varchar(255) DEFAULT NULL COMMENT '评论',
  `time` date DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `clientevaluate_ibfk_1` FOREIGN KEY (`id`) REFERENCES `orderbill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clientevaluate
-- ----------------------------

-- ----------------------------
-- Table structure for clientverify
-- ----------------------------
DROP TABLE IF EXISTS `clientverify`;
CREATE TABLE `clientverify` (
  `id` varchar(255) NOT NULL,
  `cardID` varchar(18) DEFAULT '' COMMENT '身份证号码',
  `realName` varchar(255) DEFAULT '' COMMENT '真名',
  `location` varchar(255) DEFAULT NULL COMMENT '户籍地',
  `cardPhoto0` varchar(255) DEFAULT NULL COMMENT '身份证照片',
  `cardPhoto1` varchar(255) DEFAULT NULL COMMENT '身份证照片',
  `PACPhoto` varchar(255) DEFAULT NULL COMMENT '人与身份证合照',
  `verifyTime` datetime DEFAULT NULL COMMENT '验证时间',
  `verifyState` enum('NONE','VERIFYING','VERIFYED') DEFAULT 'NONE' COMMENT '验证状态',
  PRIMARY KEY (`id`),
  CONSTRAINT `clientverify_ibfk_1` FOREIGN KEY (`id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clientverify
-- ----------------------------
INSERT INTO `clientverify` VALUES ('2c90a4485a5fdc07015a5fdc0b2d0000', '440123199401102343', '李思', '广东广州', null, null, null, '2017-03-16 19:33:14', 'VERIFYING');

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `id` varchar(255) NOT NULL,
  `phone` varchar(18) NOT NULL COMMENT '手机号',
  `wechat` varchar(255) DEFAULT NULL COMMENT '微信帐号',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `street` varchar(255) DEFAULT NULL COMMENT '街道',
  `credit` float(4,1) DEFAULT '10.0' COMMENT '信用等级',
  `registerTime` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('2c90a4485a5fe30a015a5fe30faa0001', '13488393933', 'wechat', '唐僧', '1a1dc91c907325c69271ddf0c944bc72', '/expimgs/client/2c90a4485a5fdc07015a5fdc0b2d0000.jpg', '上海市', '浦东新区', '10.0', '2017-03-14 17:18:19');

-- ----------------------------
-- Table structure for driverevaluate
-- ----------------------------
DROP TABLE IF EXISTS `driverevaluate`;
CREATE TABLE `driverevaluate` (
  `id` varchar(255) NOT NULL,
  `mark` float(3,1) DEFAULT '10.0' COMMENT '评价',
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `driverevaluate_ibfk_1` FOREIGN KEY (`id`) REFERENCES `orderbill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driverevaluate
-- ----------------------------

-- ----------------------------
-- Table structure for driververify
-- ----------------------------
DROP TABLE IF EXISTS `driververify`;
CREATE TABLE `driververify` (
  `id` varchar(255) NOT NULL,
  `cardID` varchar(18) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `cardPhoto0` varchar(255) DEFAULT NULL,
  `cardPhoto1` varchar(255) DEFAULT NULL,
  `PACPhoto` varchar(255) DEFAULT NULL,
  `carNumber` varchar(255) DEFAULT NULL,
  `carOwner` varchar(255) DEFAULT NULL,
  `driverPhoto0` varchar(255) DEFAULT NULL COMMENT '行驶证',
  `driverPhoto1` varchar(255) DEFAULT NULL COMMENT '驶证行',
  `drivingPhoto0` varchar(255) DEFAULT NULL COMMENT '驾驶证',
  `drivingPhoto1` varchar(255) DEFAULT NULL COMMENT '驾驶证',
  `verifyTime` datetime DEFAULT NULL COMMENT '验证时间',
  `verifyState` enum('NONE','VERIFYING','VERIFYED') DEFAULT 'NONE' COMMENT '验证状态',
  PRIMARY KEY (`id`),
  CONSTRAINT `driververify_ibfk_1` FOREIGN KEY (`id`) REFERENCES `driver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driververify
-- ----------------------------
INSERT INTO `driververify` VALUES ('2c90a4485a5fe30a015a5fe30faa0001', null, null, null, null, null, null, null, null, null, null, null, null, 'NONE');

-- ----------------------------
-- Table structure for joinorder
-- ----------------------------
DROP TABLE IF EXISTS `joinorder`;
CREATE TABLE `joinorder` (
  `id` varchar(255) NOT NULL,
  `orderID` varchar(255) DEFAULT NULL COMMENT '订单号',
  `driverID` varchar(255) DEFAULT NULL COMMENT '配送员',
  `joinState` enum('FINISH_EVALUATE','FINISH','DD_CANCEL','DR_CANCEL','CD_CANCEL','CR_CANCEL','RECIEVE','DELIVERY') DEFAULT 'RECIEVE' COMMENT '运送状态',
  `receiveTime` timestamp(6) NULL DEFAULT NULL COMMENT '司机接受时间',
  `deliverTime` timestamp(6) NULL DEFAULT NULL COMMENT '司机运送时间',
  `finishTime` timestamp(6) NULL DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `driverID` (`driverID`),
  KEY `joinorder_ibfk_1` (`orderID`),
  CONSTRAINT `joinorder_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `orderbill` (`id`),
  CONSTRAINT `joinorder_ibfk_2` FOREIGN KEY (`driverID`) REFERENCES `driver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of joinorder
-- ----------------------------

-- ----------------------------
-- Table structure for orderbill
-- ----------------------------
DROP TABLE IF EXISTS `orderbill`;
CREATE TABLE `orderbill` (
  `id` varchar(255) NOT NULL,
  `verifyCode` varchar(255) DEFAULT NULL COMMENT '验证码',
  `orderState` enum('FINISH_EVALUATE','FINISH','PC_FINISH','CANCEL_CLOSE','CLOSE','DD_CANCELED','DD_CANCELING','CD_CANCELED','CD_CANCELING','DELIVERY','RECIEVE','PAY_CANCEL','PAYED','NONE') DEFAULT 'NONE' COMMENT '支付状态',
  `clientID` varchar(255) DEFAULT NULL COMMENT '客户',
  `payID` varchar(255) DEFAULT NULL,
  `joinOrderID` varchar(255) DEFAULT NULL COMMENT '司机接单号',
  `placeTime` timestamp(6) NULL DEFAULT NULL COMMENT '下单时间',
  `finishTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `clientID` (`clientID`) USING BTREE,
  KEY `joinOrderID` (`joinOrderID`) USING BTREE,
  KEY `payID` (`payID`) USING BTREE,
  CONSTRAINT `orderbill_ibfk_1` FOREIGN KEY (`clientID`) REFERENCES `client` (`id`),
  CONSTRAINT `orderbill_ibfk_2` FOREIGN KEY (`joinOrderID`) REFERENCES `joinorder` (`id`),
  CONSTRAINT `orderbill_ibfk_3` FOREIGN KEY (`payID`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of orderbill
-- ----------------------------
INSERT INTO `orderbill` VALUES ('342081b2-e6c5-41ed-b504-541e8f908d57', null, 'PAYED', '2c90a4485a5fdc07015a5fdc0b2d0000', '551dda56-d761-4cc3-bfd1-f677f67e544d', null, '2017-06-25 12:40:08.418000', null);

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `id` varchar(255) NOT NULL,
  `startAddress` varchar(255) DEFAULT NULL COMMENT '起始地址',
  `startPX` double(12,8) DEFAULT NULL COMMENT '开始地址经度',
  `startPY` double(12,8) DEFAULT NULL COMMENT '开始地址纬度',
  `endAddress` varchar(255) DEFAULT NULL COMMENT '结束地址',
  `endPX` double(12,8) DEFAULT NULL COMMENT '结束地址经度',
  `endPY` double(12,8) DEFAULT NULL COMMENT '结束地址纬度',
  `shipper` varchar(255) DEFAULT NULL COMMENT '发货人',
  `shipperPhone` varchar(255) DEFAULT NULL COMMENT '发货人手机号',
  `receiver` varchar(255) DEFAULT NULL COMMENT '收货人',
  `receiverPhone` varchar(255) DEFAULT NULL COMMENT '收货人手机号',
  `goodsName` varchar(255) DEFAULT NULL COMMENT '货物类别',
  `goodsValue` decimal(6,2) DEFAULT '0.00' COMMENT '货物价值',
  `goodsWeight` decimal(6,2) DEFAULT '1.00' COMMENT '货物重量',
  `goodsLength` decimal(6,2) DEFAULT '0.00' COMMENT '货物长度',
  `goodsWidth` decimal(6,2) DEFAULT '0.00' COMMENT '货物宽度',
  `goodsHeight` decimal(6,2) DEFAULT '0.00' COMMENT '货物高度',
  `goodsPhoto` varchar(255) DEFAULT NULL COMMENT '货物图片',
  `deliveryType` enum('NON_MOTOR','CAR','NONE') DEFAULT 'NONE' COMMENT '运送类型',
  `SETime` timestamp(6) NULL DEFAULT NULL COMMENT '期待开始时间',
  `EETime` timestamp(6) NULL DEFAULT NULL COMMENT '期待结止时间',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '小费',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  PRIMARY KEY (`id`),
  CONSTRAINT `orderinfo_ibfk_1` FOREIGN KEY (`id`) REFERENCES `orderbill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES ('342081b2-e6c5-41ed-b504-541e8f908d57', '广州', '120.00000000', '20.00000000', '深圳', '120.00000000', '20.50000000', '1', '13423433421', '2', '13423433422', '香蕉', '1.00', '2.00', '20.00', '20.00', '20.00', '/expimgs/goods/a1a9b1b7cff449089ddda264ebf7437c.jpg', null, null, null, '1.00', '', '15.00');

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` varchar(255) NOT NULL,
  `orderID` varchar(255) DEFAULT NULL COMMENT '订单号',
  `payType` varchar(255) DEFAULT NULL COMMENT '支付类型',
  `payID` varchar(255) DEFAULT NULL COMMENT '支付号',
  `state` varchar(255) DEFAULT NULL,
  `payTime` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`),
  KEY `orderID` (`orderID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `orderbill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES ('551dda56-d761-4cc3-bfd1-f677f67e544d', '342081b2-e6c5-41ed-b504-541e8f908d57', null, null, null, '2017-06-25 12:40:24');
