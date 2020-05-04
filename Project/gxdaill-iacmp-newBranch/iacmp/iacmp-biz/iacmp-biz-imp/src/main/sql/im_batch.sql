CREATE TABLE `im_batch` (
  `ID` varchar(32) NOT NULL COMMENT '批次id',
  `REGBILLGLIDENO` varchar(64) DEFAULT NULL COMMENT '单据登记流水号',
  `OPERATIONCODE` varchar(64) NOT NULL DEFAULT '' COMMENT '业务编号',
  `TELLERNO` varchar(500) DEFAULT NULL COMMENT '操作柜员编号',
  `TELLERNAME` varchar(500) DEFAULT NULL COMMENT '操作柜员姓名',
  `BRANCHNO` varchar(500) DEFAULT NULL COMMENT '机构号',
  `BRANCHNAME` varchar(500) DEFAULT NULL COMMENT '机构名称',
  `SERIALNO` varchar(500) DEFAULT NULL COMMENT '顺序码',
  `RCVNUM` varchar(500) DEFAULT NULL COMMENT '正本数量',
  `TOTALNUM` varchar(500) DEFAULT '0' COMMENT '副本数量',
  `BILLPACKAGES` varchar(2000) DEFAULT NULL COMMENT '套数',
  `SYSTEMFLAG` varchar(100) DEFAULT NULL COMMENT '系统标识',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '批次创建时间',
  `OCR_FLAG` varchar(10) DEFAULT NULL COMMENT '识别标示(0-不识别;1-识别;2-已识别;)',
  `DEFECT_TYPE` varchar(2000) DEFAULT NULL COMMENT '类型缺失登记',
  `ORDER_NUM` varchar(500) DEFAULT NULL COMMENT '订单号',
  `b_hour` int(11) DEFAULT NULL COMMENT '小时',
  `b_year` int(11) DEFAULT NULL COMMENT '年份',
  `b_month` int(11) DEFAULT NULL COMMENT '月份',
  `b_day` int(11) DEFAULT NULL COMMENT '日期',
  `SYS_FLAG_INT` int(4) NOT NULL DEFAULT '0' COMMENT '系统标识状态',
  `USER_ASSCIATION_FLAG` int(4) DEFAULT '0' COMMENT '用户管理标识',
  `BATCH_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`BATCH_ID`),
  UNIQUE KEY `OPERATIONCODE` (`OPERATIONCODE`),
  UNIQUE KEY `ID` (`ID`),
  KEY `index01` (`SYSTEMFLAG`,`CREATE_TIME`) USING BTREE,
  KEY `IM_BATCH_INDEX1` (`REGBILLGLIDENO`) USING BTREE,
  KEY `IM_BATCH_INDEX2` (`CREATE_TIME`) USING BTREE,
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;