-- ----------------------------
-- Table structure for wf_operator
-- ----------------------------
DROP TABLE IF EXISTS `wf_operator`;
CREATE TABLE `wf_operator` (
  `CUID` varchar(80) NOT NULL,
  `LABEL_CN` varchar(100) NOT NULL,
  `BM_CLASS_ID` varchar(30) NOT NULL,
  `USER_ID` varchar(255) NOT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  `STATUS` varchar(10) NOT NULL,
  `LAST_LOGIN_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `LOGIN_COUNT` int(8) DEFAULT NULL,
  `LAST_CHANGE_PWD_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `CREATOR` varchar(80) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `UPDATER` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`CUID`),
  UNIQUE KEY `I_WF_OPERATOR_01` (`USER_ID`),
  KEY `I_WF_OPERATOR_02` (`LABEL_CN`),
  KEY `I_WF_OPERATOR_03` (`STATUS`) USING BTREE
);