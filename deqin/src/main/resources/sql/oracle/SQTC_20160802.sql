/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.5.0

Source Server         : 192.168.56.10_SQTC
Source Server Version : 110200
Source Host           : 192.168.56.10:1521
Source Schema         : SQTC

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2016-08-02 23:14:18
*/


-- ----------------------------
-- Table structure for C_CAMPUS
-- ----------------------------
DROP TABLE "SQTC"."C_CAMPUS";
CREATE TABLE "SQTC"."C_CAMPUS" (
"ID" NUMBER(19) NOT NULL ,
"C_NAME" VARCHAR2(255 BYTE) NULL ,
"CAN_GET_CARD" NUMBER NULL ,
"PID" NUMBER NULL ,
"LASTTIME" NUMBER(19) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of C_CAMPUS
-- ----------------------------

-- ----------------------------
-- Table structure for C_CARD_INFO
-- ----------------------------
DROP TABLE "SQTC"."C_CARD_INFO";
CREATE TABLE "SQTC"."C_CARD_INFO" (
"ID" NUMBER(19) NOT NULL ,
"CARD_NO" VARCHAR2(64 BYTE) NULL ,
"CARD_TYPE_ID" NUMBER(19) NULL ,
"STATUS" VARCHAR2(2 BYTE) NULL ,
"OWNER" VARCHAR2(64 BYTE) NULL ,
"CAR_NO" VARCHAR2(64 BYTE) NULL ,
"EXPIRE_DATE" VARCHAR2(4 BYTE) NULL ,
"Y_NAME" VARCHAR2(4 BYTE) NULL ,
"XM_IDS" VARCHAR2(1024 BYTE) NULL ,
"LASTTIME" NUMBER(19) NULL ,
"TYPE_NAME" VARCHAR2(64 BYTE) NULL ,
"TYPE_ID" NUMBER(19) NULL ,
"USER_NO" VARCHAR2(64 BYTE) NULL ,
"USER_NAME" VARCHAR2(64 BYTE) NULL ,
"FRONT_PRINTS" NUMBER NULL ,
"BG_PRINTS" NUMBER NULL ,
"USER_TYPE_ID" NUMBER(19) NULL ,
"IS_PAY" VARCHAR2(2 BYTE) NULL ,
"CAR_TYPE" VARCHAR2(2 BYTE) NULL ,
"DAY_STOP_MF" NUMBER NULL ,
"NIGHT_STOP_MF" NUMBER NULL ,
"USE_STATUS" VARCHAR2(2 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."CARD_NO" IS '卡号';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."CARD_TYPE_ID" IS '卡类型';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."STATUS" IS '审核状态：1：未审；2：已审；3:退回';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."OWNER" IS '车主姓名';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."CAR_NO" IS '车牌';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."EXPIRE_DATE" IS '进校证有效时间';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."XM_IDS" IS '可进出校门id';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."LASTTIME" IS '最后更新时间';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."TYPE_NAME" IS '车类型说明';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."TYPE_ID" IS '车类型关联c_card_type';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."USER_NO" IS '申请人工号';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."USER_NAME" IS '申请人姓名';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."FRONT_PRINTS" IS '进校证正面打印次数';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."BG_PRINTS" IS '进校证正面打印次数';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."USER_TYPE_ID" IS '申请人类型';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."IS_PAY" IS '支付类型';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."CAR_TYPE" IS '车辆类型：1：大型，2：中型，3：小型';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."DAY_STOP_MF" IS '白天免费时长';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."NIGHT_STOP_MF" IS '夜晚免费时长';
COMMENT ON COLUMN "SQTC"."C_CARD_INFO"."USE_STATUS" IS '进校证是否有效';

-- ----------------------------
-- Records of C_CARD_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for C_CARD_TEMP
-- ----------------------------
DROP TABLE "SQTC"."C_CARD_TEMP";
CREATE TABLE "SQTC"."C_CARD_TEMP" (
"ID" NUMBER(19) NOT NULL ,
"YEAR" NUMBER(4) NULL ,
"MONTH" NUMBER(2) NULL ,
"DEPT_NO" VARCHAR2(64 BYTE) NULL ,
"DEPT_NAME" VARCHAR2(64 BYTE) NULL ,
"OWNER" VARCHAR2(64 BYTE) NULL ,
"MOBILE" VARCHAR2(64 BYTE) NULL ,
"CAR_NO" VARCHAR2(64 BYTE) NULL ,
"USE_DATE" VARCHAR2(10 BYTE) NULL ,
"NOTE" VARCHAR2(1000 BYTE) NULL ,
"USER_ID" NUMBER(19) NULL ,
"STATUS" NUMBER NULL ,
"CAR_TYPE" NUMBER NULL ,
"TEMP_ID" NUMBER(19) NULL ,
"XM_IDS" VARCHAR2(1024 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."YEAR" IS '年份';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."MONTH" IS '月份（作废字段）';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."DEPT_NO" IS '办证单位编号';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."DEPT_NAME" IS '办证单位名称';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."OWNER" IS '申请人姓名';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."MOBILE" IS '申请人手机';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."CAR_NO" IS '车牌';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."USE_DATE" IS '有效时间';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."NOTE" IS '备注';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."USER_ID" IS '添加人id';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."STATUS" IS '审核状态：1：未审；2：已审；3:退回';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."CAR_TYPE" IS '车辆类型：1：大型，2：中型，3：小型';
COMMENT ON COLUMN "SQTC"."C_CARD_TEMP"."TEMP_ID" IS '临时部门中的关联id';

-- ----------------------------
-- Records of C_CARD_TEMP
-- ----------------------------

-- ----------------------------
-- Table structure for C_CARD_TYPE
-- ----------------------------
DROP TABLE "SQTC"."C_CARD_TYPE";
CREATE TABLE "SQTC"."C_CARD_TYPE" (
"ID" NUMBER(19) NOT NULL ,
"CTYPE" VARCHAR2(1 BYTE) NULL ,
"CNAME" VARCHAR2(100 BYTE) NULL ,
"CNUMBER" VARCHAR2(1 BYTE) NULL ,
"CMOD" VARCHAR2(1 BYTE) NULL ,
"NEED_REL" VARCHAR2(1 BYTE) NULL ,
"D_CAR" VARCHAR2(1 BYTE) NULL ,
"CAN_SEARCH" VARCHAR2(1 BYTE) NULL ,
"DAY_STOP_MF" NUMBER NULL ,
"NIGHT_STOP_MF" NUMBER NULL ,
"CYEAR" NUMBER NULL ,
"FEE" NUMBER(8,2) NULL ,
"XM_IDS" VARCHAR2(1024 BYTE) NULL ,
"BUBAN" NUMBER(3,2) NULL ,
"BIANGENG" NUMBER(3,2) NULL ,
"LASTTIME" NUMBER(19) NULL ,
"STATUS" VARCHAR2(1 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."CTYPE" IS '是否为默认类型  0：不是   1：是';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."CNAME" IS '进校证类型名称';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."CNUMBER" IS '进校证首字母编号';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."CMOD" IS '1校内，2校外';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."NEED_REL" IS '是否需要关系证明';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."D_CAR" IS '1:默认,0:非默认';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."CAN_SEARCH" IS '1:可以，0不可以';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."DAY_STOP_MF" IS '白天免费停车小时，作废字段';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."NIGHT_STOP_MF" IS '晚上免费停车小时，作废字段';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."CYEAR" IS '进校证类型的适用年份';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."FEE" IS '年费收费标准';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."XM_IDS" IS '允许通行校门id，与campus表关联';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."BUBAN" IS '补办进校证时收费标准倍数';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."BIANGENG" IS '变更进校证时的收费标准倍数';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."LASTTIME" IS '最后更新时间';
COMMENT ON COLUMN "SQTC"."C_CARD_TYPE"."STATUS" IS '是否为默认类型  0：不是   1：是';

-- ----------------------------
-- Records of C_CARD_TYPE
-- ----------------------------

-- ----------------------------
-- Table structure for C_SHOUFEI
-- ----------------------------
DROP TABLE "SQTC"."C_SHOUFEI";
CREATE TABLE "SQTC"."C_SHOUFEI" (
"ID" NUMBER(19) NOT NULL ,
"TYPE_ID" NUMBER(19) NULL ,
"CID" NUMBER(19) NULL ,
"DAY_TIME" NUMBER(7,2) NULL ,
"NIGHT_TIME" NUMBER(7,2) NULL ,
"DAY_HOUR_MONEY" NUMBER(7,2) NULL ,
"NIGHT_HOUR_MONEY" NUMBER(7,2) NULL ,
"DAY_MAX_MONEY" NUMBER(7,2) NULL ,
"NIGHT_MAX_MONEY" NUMBER(7,2) NULL ,
"EVERYDAY_MIANFEI_TIME" NUMBER(6,2) NULL ,
"LASTTIME" NUMBER(19) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."C_SHOUFEI"."TYPE_ID" IS '关联进校证类型id，关联表c_card_type';
COMMENT ON COLUMN "SQTC"."C_SHOUFEI"."CID" IS '关联校区id，关联表campus';
COMMENT ON COLUMN "SQTC"."C_SHOUFEI"."DAY_TIME" IS '白天免费停车时长，单位小时';
COMMENT ON COLUMN "SQTC"."C_SHOUFEI"."NIGHT_TIME" IS '晚上免费停车时长，单位小时';
COMMENT ON COLUMN "SQTC"."C_SHOUFEI"."DAY_HOUR_MONEY" IS '白天停车超过免费时限后，每小时的收费标准，单位：元/时';
COMMENT ON COLUMN "SQTC"."C_SHOUFEI"."NIGHT_HOUR_MONEY" IS '夜晚停车超过免费时限后，每小时的收费标准，单位：元/时';
COMMENT ON COLUMN "SQTC"."C_SHOUFEI"."DAY_MAX_MONEY" IS '白天单次停车，封顶收费金额';
COMMENT ON COLUMN "SQTC"."C_SHOUFEI"."NIGHT_MAX_MONEY" IS '夜晚单次停车，封顶收费金额';
COMMENT ON COLUMN "SQTC"."C_SHOUFEI"."EVERYDAY_MIANFEI_TIME" IS '白天每天免费时长，针对饮食配送车辆和快递车辆等，可能对此类车辆每天免费0.5小时，单位小时';
COMMENT ON COLUMN "SQTC"."C_SHOUFEI"."LASTTIME" IS '最后更新时间';

-- ----------------------------
-- Records of C_SHOUFEI
-- ----------------------------

-- ----------------------------
-- Table structure for T_ANNOUNCEMENT
-- ----------------------------
DROP TABLE "SQTC"."T_ANNOUNCEMENT";
CREATE TABLE "SQTC"."T_ANNOUNCEMENT" (
"ID" NUMBER(19) NOT NULL ,
"TITLE" VARCHAR2(255 BYTE) NULL ,
"CONTEXT" VARCHAR2(2000 BYTE) NULL ,
"CREATE_USER_ID" NUMBER(19) NULL ,
"START_DATE" TIMESTAMP(6)  NULL ,
"CREATE_TIME" TIMESTAMP(6)  NULL ,
"STATE" NUMBER NULL ,
"TO_ALL_USER" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_ANNOUNCEMENT
-- ----------------------------

-- ----------------------------
-- Table structure for T_ANNOUNCEMENT_SEND
-- ----------------------------
DROP TABLE "SQTC"."T_ANNOUNCEMENT_SEND";
CREATE TABLE "SQTC"."T_ANNOUNCEMENT_SEND" (
"ID" NUMBER(19) NOT NULL ,
"ANNOUNCEMENT_ID" NUMBER(19) NULL ,
"SEND_USER_ID" NUMBER(19) NULL ,
"SEND_DATE" TIMESTAMP(6)  NULL ,
"IS_READ" NUMBER NULL ,
"READ_TIME" TIMESTAMP(6)  NULL ,
"IS_CLOSE" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_ANNOUNCEMENT_SEND
-- ----------------------------

-- ----------------------------
-- Table structure for T_ATTACH
-- ----------------------------
DROP TABLE "SQTC"."T_ATTACH";
CREATE TABLE "SQTC"."T_ATTACH" (
"ID" NUMBER(19) NOT NULL ,
"NAME" VARCHAR2(255 BYTE) NULL ,
"CREATE_TIME" TIMESTAMP(6)  NULL ,
"URL_PATH" VARCHAR2(1024 BYTE) NULL ,
"FILE_SIZE" NUMBER(19) NULL ,
"FILE_TYPE" VARCHAR2(255 BYTE) NULL ,
"USER_ID" NUMBER(19) NULL ,
"UPLOAD_SESSION_ID" VARCHAR2(1024 BYTE) NULL ,
"FILE_KEY" VARCHAR2(255 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_ATTACH
-- ----------------------------

-- ----------------------------
-- Table structure for T_AUTHORITY
-- ----------------------------
DROP TABLE "SQTC"."T_AUTHORITY";
CREATE TABLE "SQTC"."T_AUTHORITY" (
"ID" NUMBER(19) NOT NULL ,
"DISPLAY_NAME" VARCHAR2(255 BYTE) NULL ,
"NAME" VARCHAR2(255 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_AUTHORITY
-- ----------------------------

-- ----------------------------
-- Table structure for T_CAMPUS
-- ----------------------------
DROP TABLE "SQTC"."T_CAMPUS";
CREATE TABLE "SQTC"."T_CAMPUS" (
"ID" NUMBER(19) NOT NULL ,
"NAME" VARCHAR2(255 BYTE) NOT NULL ,
"PARKING_LOT_ID" NUMBER(19) NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL ,
"LASTTIME" NUMBER(19) NULL ,
"IS_ENABLED" NUMBER DEFAULT 1  NULL ,
"REMARK" VARCHAR2(1024 BYTE) NULL ,
"CAMPUS_ID" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_CAMPUS"."NAME" IS '校门名称';
COMMENT ON COLUMN "SQTC"."T_CAMPUS"."LASTTIME" IS '最后更新时间';

-- ----------------------------
-- Records of T_CAMPUS
-- ----------------------------
INSERT INTO "SQTC"."T_CAMPUS" VALUES ('1975', '3号门', '1941', TO_TIMESTAMP(' 2016-04-28 01:01:10:746000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1386638067', '1', '1', '53');
INSERT INTO "SQTC"."T_CAMPUS" VALUES ('1976', '2号门', '1941', TO_TIMESTAMP(' 2016-04-28 01:01:10:746000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1386638053', '1', '1', '52');
INSERT INTO "SQTC"."T_CAMPUS" VALUES ('1977', '嘉定区曹安公路4800号门 ', '1941', TO_TIMESTAMP(' 2016-04-28 01:01:10:746000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1386229173', '1', '1', '38');

-- ----------------------------
-- Table structure for T_CARD_INFO
-- ----------------------------
DROP TABLE "SQTC"."T_CARD_INFO";
CREATE TABLE "SQTC"."T_CARD_INFO" (
"ID" NUMBER(19) NOT NULL ,
"CARD_NO" VARCHAR2(64 BYTE) NULL ,
"CARD_TYPE" NUMBER NULL ,
"CARD_TYPE_ID" NUMBER(19) NULL ,
"STATUS" NUMBER NULL ,
"OWNER" VARCHAR2(64 BYTE) NULL ,
"CAR_NO" VARCHAR2(64 BYTE) NULL ,
"EXPIRE_DATE" TIMESTAMP(7)  NULL ,
"Y_NAME" NUMBER NULL ,
"XM_IDS" VARCHAR2(1024 BYTE) NULL ,
"LASTTIME" NUMBER(19) NULL ,
"TYPE_NAME" VARCHAR2(64 BYTE) NULL ,
"TYPE_ID" NUMBER(19) NULL ,
"USER_NO" VARCHAR2(64 BYTE) NULL ,
"USER_NAME" VARCHAR2(64 BYTE) NULL ,
"FRONT_PRINTS" NUMBER NULL ,
"BG_PRINTS" NUMBER NULL ,
"USER_TYPE_ID" NUMBER(19) NULL ,
"IS_PAY" VARCHAR2(2 BYTE) NULL ,
"CAR_TYPE" VARCHAR2(2 BYTE) NULL ,
"DAY_STOP_MF" NUMBER(10,1) NULL ,
"NIGHT_STOP_MF" NUMBER(10,1) NULL ,
"USE_STATUS" VARCHAR2(2 BYTE) NULL ,
"DEPT_NO" VARCHAR2(64 BYTE) NULL ,
"DEPT_NAME" VARCHAR2(64 BYTE) NULL ,
"MOBILE" VARCHAR2(64 BYTE) NULL ,
"NOTE" VARCHAR2(1024 BYTE) NULL ,
"USER_ID" NUMBER(19) NULL ,
"TEMP_ID" NUMBER(19) NULL ,
"OLD_CARD_INFO_ID" NUMBER(19) NULL ,
"CREATE_TIME" TIMESTAMP(6)  NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."CARD_NO" IS '卡号';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."CARD_TYPE" IS '1.进校证  2.临时进校证 3.evcard 4.vip自建';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."CARD_TYPE_ID" IS '卡类型';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."STATUS" IS '审核状态：1：未审；2：已审；3:退回';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."OWNER" IS '车主姓名';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."CAR_NO" IS '车牌';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."EXPIRE_DATE" IS '进校证有效时间';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."Y_NAME" IS '申办年度';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."XM_IDS" IS '可进出校门id';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."LASTTIME" IS '最后更新时间';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."TYPE_NAME" IS '车类型说明';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."TYPE_ID" IS '车类型关联c_card_type';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."USER_NO" IS '申请人工号';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."USER_NAME" IS '申请人姓名 , 临时进校证 申请人姓名';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."FRONT_PRINTS" IS '进校证正面打印次数';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."BG_PRINTS" IS '进校证正面打印次数';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."USER_TYPE_ID" IS '申请人类型';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."IS_PAY" IS '支付类型';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."CAR_TYPE" IS '车辆类型：1：大型，2：中型，3：小型';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."DAY_STOP_MF" IS '白天免费时长';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."NIGHT_STOP_MF" IS '夜晚免费时长';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."USE_STATUS" IS '进校证是否有效';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."DEPT_NO" IS '办证单位编号';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."DEPT_NAME" IS '办证单位名称';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."MOBILE" IS '申请人电话';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."NOTE" IS '备注';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."USER_ID" IS '添加人id';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."TEMP_ID" IS '临时部门中的关联id';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."OLD_CARD_INFO_ID" IS '原始进校证的ID,根据类型不同关联不同表';
COMMENT ON COLUMN "SQTC"."T_CARD_INFO"."CREATE_TIME" IS '创建时间';

-- ----------------------------
-- Records of T_CARD_INFO
-- ----------------------------
INSERT INTO "SQTC"."T_CARD_INFO" VALUES ('414550', null, '1', null, null, '11', '1', TO_TIMESTAMP(' 2016-08-02 00:00:00:0000000', 'YYYY-MM-DD HH24:MI:SS:FF7'), null, null, null, null, null, '1', null, null, null, null, '1', '3', null, null, null, '1', '1', '1', null, null, null, null, TO_TIMESTAMP(' 2016-08-02 22:38:27:918000', 'YYYY-MM-DD HH24:MI:SS:FF6'));

-- ----------------------------
-- Table structure for T_CARD_TPYE_TEST
-- ----------------------------
DROP TABLE "SQTC"."T_CARD_TPYE_TEST";
CREATE TABLE "SQTC"."T_CARD_TPYE_TEST" (
"CARD_TPYE_CODE" VARCHAR2(2 BYTE) NOT NULL ,
"CARD_TPYE" VARCHAR2(100 BYTE) NULL ,
"USER_TYPE" VARCHAR2(30 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_CARD_TPYE_TEST"."CARD_TPYE_CODE" IS '证件类型代码';
COMMENT ON COLUMN "SQTC"."T_CARD_TPYE_TEST"."CARD_TPYE" IS '证件类型';
COMMENT ON COLUMN "SQTC"."T_CARD_TPYE_TEST"."USER_TYPE" IS '人员类型(教职工、本科、研究生)';

-- ----------------------------
-- Records of T_CARD_TPYE_TEST
-- ----------------------------
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('1', '身份证', '教职工');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('2', '军官证', '教职工');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('3', '护照', '教职工');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('4', '台胞证', '教职工');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('5', '香港永久性居民身份证', '教职工');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('6', '港澳通行证', '教职工');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('01', '身份证', '研究生');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('02', '护照', '研究生');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('03', '军官证', '研究生');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('04', '士兵证', '研究生');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('05', '回乡证', '研究生');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('06', '旅行证', '研究生');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('09', '警官证', '研究生');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('07', '港澳台身份证', '研究生');
INSERT INTO "SQTC"."T_CARD_TPYE_TEST" VALUES ('99', '其他', '研究生');

-- ----------------------------
-- Table structure for T_CARD_TYPE
-- ----------------------------
DROP TABLE "SQTC"."T_CARD_TYPE";
CREATE TABLE "SQTC"."T_CARD_TYPE" (
"ID" NUMBER(19) NOT NULL ,
"CARD_TYPE" VARCHAR2(1 BYTE) NULL ,
"CNAME" VARCHAR2(255 BYTE) NULL ,
"CARD_NUMBER" VARCHAR2(1 BYTE) NULL ,
"CMOD" VARCHAR2(1 BYTE) NULL ,
"NEED_REL" VARCHAR2(1 BYTE) NULL ,
"D_CAR" VARCHAR2(1 BYTE) NULL ,
"CAN_SEARCH" VARCHAR2(1 BYTE) NULL ,
"DAY_STOP_MF" NUMBER NULL ,
"NIGHT_STOP_MF" NUMBER NULL ,
"CYEAR" NUMBER NULL ,
"FEE" NUMBER(6,2) NULL ,
"XM_IDS" VARCHAR2(1024 BYTE) NULL ,
"BUBAN" NUMBER(6,2) NULL ,
"BIANGENG" NUMBER(6,2) NULL ,
"LASTTIME" NUMBER(19) NULL ,
"STATUS" VARCHAR2(1 BYTE) NULL ,
"OLD_CARD_TYPE_ID" NUMBER(19) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."CARD_TYPE" IS '1:年证,2:月证';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."CNAME" IS '进校证类型名称';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."CARD_NUMBER" IS '进校证首字母编号';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."CMOD" IS '进校证人群1校内，2校外';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."NEED_REL" IS '是否需要关系证明 0不需要 1需要';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."D_CAR" IS '1:默认,0:非默认 忽略字段';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."CAN_SEARCH" IS '1:可以，0不可以 忽略字段';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."DAY_STOP_MF" IS '白天免费停车小时，作废字段';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."NIGHT_STOP_MF" IS '晚上免费停车小时，作废字段';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."CYEAR" IS '进校证类型的适用年份';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."FEE" IS '年费收费标准(单位元)';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."XM_IDS" IS '允许通行校门id，与campus表关联';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."BUBAN" IS '补办进校证时收费标准倍数';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."BIANGENG" IS '变更进校证时的收费标准倍数';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."LASTTIME" IS '最后更新时间';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."STATUS" IS '是否为默认类型  0：不是   1：是';
COMMENT ON COLUMN "SQTC"."T_CARD_TYPE"."OLD_CARD_TYPE_ID" IS '原进校证数据库ID';

-- ----------------------------
-- Records of T_CARD_TYPE
-- ----------------------------
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111593', '1', 'VIP(院士、长江学者、千人计划)', 'A', '1', '1', '0', '0', '8888', '8888', '2016', '0', '66,65,64,61,60,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '0', '0', '1460080916000', null, '130');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111594', '1', '同济大学（学校公车）', 'A', '1', '1', '0', '0', '6666', '2200', '2016', '0', '66,65,64,61,60,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '0', '1460080929000', '0', '131');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111595', '1', '教工自备车', 'A', '1', '0', '1', '0', '6666', '140', '2016', '200', '66,65,64,61,60,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1460449236000', '1', '132');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111596', '1', '校聘教职工聘自备车', 'A', '2', '0', '1', '0', '6666', '140', '2016', '200', '64,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1459217948000', null, '134');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111597', '1', '产业职工自备车（一人一车）', 'B', '2', '0', '1', '0', '600', '140', '2016', '600', '64,45,43,41,21', '1', '1', '1459218110000', null, '135');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111598', '1', '学院、部门聘用的教职工自备车', 'B', '2', '0', '1', '0', '600', '140', '2016', '600', '64,45,43,41,21', '1', '1', '1459218077000', null, '136');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111599', '1', '学校附属单位车辆', 'A', '2', '0', '1', '0', '6666', '140', '2016', '600', '64,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1459217928000', null, '137');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111600', '1', '产业公车（校内停放包月）', 'B', '2', '1', '0', '0', '6666', '3333', '2016', '1200', '64,45,43,42,41,21', '1', '1', '1459299902000', '0', '138');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111601', '1', '沪西校区', 'B', '2', '1', '0', '0', '600', '140', '2016', '600', '37', '1', '1', '1459218185000', '0', '140');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111602', '1', '夜大学生、EMBA、工程硕士、研修班等', 'B', '2', '0', '1', '0', '600', '140', '2016', '1200', '65,64,45,41,37,21', '1', '1', '1460081010000', null, '141');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111603', '1', '饮食中心配送车辆', 'C', '2', '0', '1', '0', '600', '140', '2016', '1200', '41', '1', '1', '1459218474000', null, '143');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111604', '1', '产业公车', 'A', '1', '1', '0', '0', '6666', '140', '2016', '300', '61,60,57,56,53,52,46,45,43,42,41,40,39,38,37,22,21', '1', '1', '1421733687000', '0', '144');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111605', '1', '快递车辆', 'C', '2', '0', '1', '0', '600', '140', '2016', '1200', null, '1', '1', '1453076473000', null, '145');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111606', '1', '校内商店、超市、店铺租赁车辆', 'C', '2', '0', '1', '0', '600', '140', '2016', '1200', '64,45,43,42,41,21', '1', '1', '1459218495000', null, '146');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111607', '1', '班车', 'A', '2', '1', '0', '0', '6666', '3333', '2016', '0', '64,61,60,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1459217935000', '0', '147');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111608', '1', '校外长期科研单位', 'D', '2', '0', '1', '0', '600', '140', '2016', '1200', '64,45,41,21', '1', '1', '1459218562000', null, '148');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111609', '1', '同济科技园租赁公司（公司限办二张）', 'D', '2', '0', '1', '0', '600', '140', '2016', '1200', '64,45,21', '1', '1', '1459218576000', null, '149');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111610', '1', '资产处签协议、后勤集团管理房屋租赁', 'D', '2', '0', '1', '0', '600', '140', '2016', '1200', '64,45,41,21', '1', '1', '1459218605000', null, '150');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111611', '1', '校外长期驻校施工单位', 'C', '2', '0', '1', '0', '600', '140', '2016', '1200', '64,41,21', '1', '1', '1459218533000', null, '151');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111612', '1', '领导证 ', 'A', '1', '1', '0', '0', '8888', '8888', '2016', '0', '66,65,64,61,60,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1460080958000', '0', '154');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111613', '1', '博士生自备车', 'B', '2', '1', '0', '0', '600', '140', '2016', '600', '65,64,45,43,42,41,39,38,37,21', '1', '1', '1460081028000', null, '155');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111614', '1', '嘉定、沪西合作单位', 'D', '2', '1', '0', '0', '6666', '0', '2016', '300', '38,37', '1', '1', '1459218684000', '0', '156');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111615', '1', '试验室私聘人员自备车', 'D', '2', '1', '0', '0', '600', '140', '2016', '1200', '64,41,21', '1', '1', '1459218709000', '0', '157');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111616', '1', '学校班车', 'A', '1', '1', '0', '0', '6666', '0', '2016', '0', '57,56,53,52,46,45,43,42,41,40,39,38,37,22,21', '1', '1', '1452916996000', '0', '158');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111617', '1', '校外产业公车（每个单位仅限办理2张）', 'B', '2', '1', '0', '0', '600', '140', '2016', '600', null, '1', '1', '1421985864000', '0', '159');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111618', '1', '校外产业员工（每个单位仅限办理2张）', 'B', '2', '1', '0', '0', '600', '140', '2016', '600', null, '1', '1', '1421985942000', '0', '160');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111619', '1', '教工自备车(含退休返聘人员)　', 'A', '2', '1', '0', '0', '6666', '140', '2016', '200', '66,65,64,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1462593436000', '0', '161');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111620', '1', '后勤产业职工', 'B', '2', '1', '0', '0', '1000', '140', '2016', '600', '64,45,43,42,41,21', '1', '1', '1459218388000', '0', '163');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111621', '1', '产业公车', 'B', '1', '1', '0', '0', '6666', '3333', '2016', '600', '65,64,61,60,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1460080982000', '0', '168');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111622', '1', '教工（本部）', 'B', '2', '1', '0', '0', '6666', '140', '2016', '200', null, '1', '1', '1427339893000', '0', '169');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111623', '1', '领导证（A）', 'A', '2', '1', '0', '0', '6666', '3333', '2016', '0', '64,61,60,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1459217968000', '0', '170');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111624', '1', '公车', 'A', '2', '1', '0', '0', '6666', '3333', '2016', '0', '66,65,64,61,60,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1460353443000', '0', '171');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111625', '1', '其它等合作单位', 'D', '2', '1', '0', '0', '600', '140', '2016', '1200', '64,41,21', '1', '1', '1459218729000', '0', '173');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111626', '1', '住校教工和学院单位（年费包月）', 'D', '2', '1', '0', '0', '6666', '3333', '2016', '1200', '64,41,21', '1', '1', '1459218750000', '0', '174');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111627', '1', '住校教工和学院单位（年费包月） ', 'A', '2', '1', '0', '0', '6666', '3333', '2016', '1200', '64,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1459218020000', '0', '175');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111628', '1', '住校教工和学院单位（年费包月）校内　', 'A', '1', '1', '0', '0', '6666', '3333', '2016', '1200', '65,64,61,60,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1460080995000', '0', '176');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111629', '1', '校办', 'A', '1', '1', '0', '0', '6666', '3333', '2016', '200', '62,61,60,57,56,53,52,48,47,46,45,43,42,41,40,39,38,37,22,21', '0', '0', '1457064402000', '0', '177');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111630', '1', '嘉定后勤', 'D', '2', '1', '0', '0', '6666', '0', '2016', '300', '38', '1', '1', '1459218776000', '0', '178');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111631', '1', '产业公车', 'D', '1', '1', '0', '0', '6666', '3333', '2016', '1200', '64,61,60,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1458872665000', '0', '179');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111632', '1', '校办', 'A', '2', '1', '0', '0', '6666', '140', '2016', '200', '66,65,64,61,60,57,56,53,52,48,47,46,45,43,42,41,39,38,37,22,21', '1', '1', '1466735928000', '0', '181');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111510', '1', '公车证', 'B', '1', '0', '0', '0', '6666', '2200', '2012', '0', '56,53,52,48,47,46,45,44,43,42,41,40,39,38,37,22,21,20,19', '1', '1', '1387524936000', null, '3');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111511', '1', '教师普通证', 'C', '1', '1', '1', '1', '6666', '2200', '2012', '100', '56,53,52,48,47,46,45,44,43,42,41,40,39,38,37,21,20,19', '1', '1', '1387524902000', null, '4');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111512', '1', '领导证', 'A', '1', '1', '0', '0', '6666', '2200', '2012', '0', '56,53,52,48,47,46,45,44,43,42,41,40,39,38,37,22,21,20,19', '1', '1', '1387524912000', null, '8');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111513', '2', '校外临时证', 'T', '2', '1', '0', '0', '0', '0', '2012', '0', null, '1', '1', null, null, '6');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111514', '1', '校外普通证', 'H', '2', '1', '0', '1', '0', '0', '2012', '500', null, '1', '1', null, null, '7');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111515', '1', '产业职工自备车（一人一车）', 'B', '2', '0', '1', '0', '600', '140', '2014', '600', null, '1', '1', '1426754956000', null, '9');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111516', '1', '校长聘用的教师自备车', 'T', '2', '0', '1', '0', '0', '0', '2014', '300', null, '1', '1', '1387855593000', null, '10');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111517', '1', '学院聘用的教师自备车', 'T', '2', '0', '1', '0', '0', '0', '2014', '300', null, '1', '1', '1387855597000', null, '11');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111518', '1', '退休教工', 'T', '2', '0', '1', '0', '0', '0', '2014', '300', null, '1', '1', '1387855561000', null, '12');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111519', '1', '学校附属单位员工', 'T', '2', '0', '1', '0', '0', '0', '2014', '300', null, '1', '1', '1387855603000', null, '14');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111520', '1', '安亭镇政府、汽车城公车', 'T', '2', '0', '1', '0', '0', '0', '2014', '300', null, '1', '1', '1387855609000', null, '15');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111521', '1', '安亭镇政府、汽车城领导自备车', 'T', '2', '0', '1', '0', '0', '0', '2014', '300', null, '1', '1', '1387855615000', null, '16');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111522', '1', '安亭镇政府、汽车城员工自备车', 'T', '2', '0', '1', '0', '0', '0', '2014', '300', null, '1', '1', '1387855619000', null, '17');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111523', '1', '嘉实集团领导自备车', 'T', '2', '0', '1', '0', '0', '0', '2014', '300', null, '1', '1', '1387855633000', null, '18');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111524', '1', '嘉实集团员工自备车', 'T', '2', '0', '1', '0', '0', '0', '2014', '300', null, '1', '1', '1387855638000', null, '19');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111525', '1', 'EMBA班', 'T', '2', '0', '1', '0', '0', '0', '2014', '600', null, '1', '1', '1387855647000', null, '20');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111526', '1', '夜大学生', 'T', '2', '0', '1', '0', '0', '0', '2014', '600', null, '1', '1', '1387855654000', null, '21');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111527', '1', '体育运动自备车', 'T', '2', '0', '1', '0', '0', '0', '2014', '600', null, '1', '1', '1387855664000', null, '22');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111528', '1', '饮食中心配送车辆', 'T', '2', '0', '1', '0', '0', '0', '2014', '600', null, '1', '1', '1387855670000', null, '23');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111529', '1', '同济大学产业部分控股公车', 'T', '2', '0', '1', '0', '0', '0', '2014', '600', null, '1', '1', '1387855675000', null, '24');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111530', '1', '送报车辆', 'T', '2', '0', '1', '0', '0', '0', '2014', '600', null, '1', '1', '1387855686000', null, '25');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111531', '1', '校园内店铺租赁', 'T', '2', '0', '1', '0', '0', '0', '2014', '1200', null, '1', '1', '1387855697000', null, '26');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111532', '1', '商店、超市、学生宿舍快递等配送车', 'T', '2', '0', '1', '0', '0', '0', '2014', '1200', null, '1', '1', '1387855703000', null, '27');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111533', '1', '校外长期科研单位', 'T', '2', '0', '1', '0', '0', '0', '2014', '1200', null, '1', '1', '1387855709000', null, '28');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111534', '1', '同济科技园租赁公司（公司限办二张）', 'T', '2', '0', '1', '0', '0', '0', '2014', '1200', null, '1', '1', '1387855729000', null, '29');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111535', '1', '试验室私聘人员自备车', 'T', '2', '0', '1', '0', '0', '0', '2014', '1200', null, '1', '1', '1387855734000', null, '30');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111536', '1', '资产处签协议、后勤集团管理房屋租赁', 'T', '2', '0', '1', '0', '0', '0', '2014', '1200', null, '1', '1', '1387855738000', null, '31');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111537', '1', '校外长期驻校施工单位', 'T', '2', '0', '1', '0', '0', '0', '2014', '1200', null, '1', '1', '1387855769000', null, '32');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111538', '1', '其他等单位', 'T', '2', '0', '1', '0', '0', '0', '2014', '1200', null, '1', '1', '1387855773000', null, '33');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111539', '1', 'VIP(院士、长江学者、千人计划)', 'A', '1', '1', '0', '0', '8888', '8888', '2015', '0', '61,60,57,56,53,52,48,47,46,45,43,42,41,40,39,38,37,22,21', '0', '0', '1419904066000', null, '56');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111540', '1', '学校附属单位公车', 'T', '2', '1', '0', '0', '0', '0', '2014', '300', null, '1', '1', '1387855778000', null, '54');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111541', '1', '同济大学公车', 'A', '1', '1', '0', '0', '6666', '2200', '2015', '0', '61,60,57,56,53,52,48,47,46,45,43,42,41,40,39,38,37,22,21', '1', '0', '1419904077000', '0', '55');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111542', '1', '同济大学产业全控股公车', 'T', '2', '1', '0', '0', '0', '0', '2014', '300', null, '1', '1', '1387855787000', null, '53');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111543', '1', '博士生自备车', 'T', '2', '1', '0', '0', '0', '0', '2014', '200', null, '1', '1', '1387774151000', null, '52');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111544', '1', '教工自备车', 'A', '1', '0', '1', '0', '6666', '140', '2015', '200', '61,60,57,56,53,52,48,47,46,45,43,42,41,40,39,38,37,22,21', '1', '1', '1427098759000', '1', '50');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111545', '2', '测试用临时证', 'C', '2', '1', '0', '0', '0', '0', '2012', '200', null, '3', '5', '1387374869000', null, '57');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111546', '1', '其它合作单位', 'T', '2', '1', '0', '0', '0', '0', '2014', '0', '56,53,52,48,47,46,45,44,43,42,41,40,39,38,37,22,21,20,19', '1', '1', '1387855845000', '0', '58');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111547', '1', '沪西', 'T', '2', '1', '0', '0', '0', '0', '2014', '0', null, '1', '1', '1387855852000', '0', '59');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111548', '1', '嘉定', 'T', '2', '1', '0', '0', '0', '0', '2014', '0', null, '1', '1', '1387923633000', '0', '60');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111549', '1', '本部校区', 'B', '2', '1', '0', '0', '600', '140', '2015', '1200', null, '1', '1', '1426754791000', '0', '61');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111550', '1', 'MBA', 'T', '2', '1', '0', '0', '0', '0', '2014', '600', null, '1', '1', '1389685045000', '0', '62');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111551', '1', '学校附属单位', 'T', '2', '1', '0', '0', '0', '0', '2014', '100', null, '1', '1', '1389766908000', '0', '63');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111552', '1', '产业', 'T', '2', '1', '0', '0', '0', '0', '2014', '300', null, '1', '1', '1390362631000', '0', '64');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111553', '1', '教工', 'C', '1', '1', '0', '0', '600', '140', '2014', '200', '61,60,57,56,53,52,46,45,43,42,41,40,39,38,37,22,21', '1', '1', '1419230353000', '0', '66');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111554', '1', '校聘教职工聘自备车', 'B', '2', '0', '1', '0', '6666', '140', '2015', '200', null, '1', '1', '1419905937000', null, '70');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111555', '1', '产业职工自备车（一人一车）', 'B', '2', '0', '1', '0', '600', '140', '2015', '600', null, '1', '1', '1422838442000', null, '69');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111556', '1', '学院、部门聘用的教职工自备车', 'B', '2', '0', '1', '0', '1000', '140', '2015', '600', null, '1', '1', '1421985571000', null, '71');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111557', '1', '学校附属单位员工', 'B', '2', '0', '1', '0', '600', '140', '2015', '600', null, '1', '1', '1419904900000', null, '73');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111558', '1', '产业公车（校内停放包月）', 'B', '2', '1', '0', '0', '6666', '3333', '2015', '1200', null, '1', '1', '1421985663000', '0', '116');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111559', '1', '产业公车', 'B', '2', '1', '0', '0', '6666', '3333', '2015', '600', null, '1', '1', '1421977323000', '0', '115');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111560', '1', '本部校区', 'B', '2', '1', '0', '0', '600', '140', '2014', '1200', null, '1', '1', '1426753945000', '0', '114');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111561', '1', '沪西校区', 'B', '2', '1', '0', '0', '600', '140', '2015', '600', null, '1', '1', '1421908930000', '0', '113');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111562', '1', '夜大学生、EMBA、工程硕士、研修班等', 'B', '2', '0', '1', '0', '600', '0', '2015', '600', null, '1', '1', '1419905618000', null, '79');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111563', '1', '体育运动自备车', 'B', '2', '0', '1', '0', '600', '0', '2015', '1200', null, '1', '1', '1419905572000', null, '81');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111564', '1', '饮食中心配送车辆', 'B', '2', '0', '1', '0', '600', '0', '2015', '600', null, '1', '1', '1419905013000', null, '82');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111565', '1', '产业公车', 'A', '1', '1', '0', '0', '6666', '140', '2015', '300', '61,60,57,56,53,52,46,45,43,42,41,40,39,38,37,22,21', '1', '1', '1421733687000', '0', '111');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111566', '1', '快递车辆', 'B', '2', '0', '1', '0', '600', '0', '2015', '600', null, '1', '1', '1419905656000', null, '84');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111567', '1', '校内商店、超市、店铺租赁车辆', 'B', '2', '0', '1', '0', '600', '0', '2015', '1200', null, '1', '1', '1419905364000', null, '85');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111568', '1', '班车', 'A', '2', '1', '0', '0', '6666', '3333', '2015', '0', null, '1', '1', '1421827610000', '0', '112');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111569', '1', '校外长期科研单位', 'B', '2', '0', '1', '0', '600', '0', '2015', '1200', null, '1', '1', '1419905490000', null, '87');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111570', '1', '同济科技园租赁公司（公司限办二张）', 'B', '2', '0', '1', '0', '600', '0', '2015', '1200', null, '1', '1', '1419905443000', null, '88');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111571', '1', '资产处签协议、后勤集团管理房屋租赁', 'B', '2', '0', '1', '0', '600', '0', '2015', '1200', null, '1', '1', '1419905212000', null, '90');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111572', '1', '校外长期驻校施工单位', 'B', '2', '0', '1', '0', '600', '0', '2015', '1200', null, '1', '1', '1419905794000', null, '91');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111573', '1', '其它等单位', 'B', '2', '0', '1', '0', '600', '0', '2015', '1200', null, '1', '1', '1419905774000', null, '92');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111574', '1', '退休返聘人员', 'B', '2', '1', '0', '0', '6666', '140', '2015', '200', '61,60,57,56,53,52,46,45,43,42,41,40,39,38,37,22,21', '1', '1', '1421637500000', '0', '110');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111575', '1', '领导证 ', 'A', '1', '1', '0', '0', '8888', '8888', '2015', '0', '61,60,57,56,53,52,48,47,46,45,43,42,41,40,39,38,37,22,21', '1', '1', '1421202984000', '0', '109');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111576', '1', '博士生自备车', 'B', '2', '1', '0', '0', '600', '140', '2015', '600', null, '1', '1', '1419904712000', null, '97');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111577', '1', '嘉定、沪西合作单位', 'B', '2', '1', '0', '0', '0', '0', '2015', '300', '56,53,52,48,47,46,45,44,43,42,41,40,39,38,37,22,21,20,19', '1', '1', '1419905710000', '0', '99');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111578', '1', '试验室私聘人员自备车', 'B', '2', '1', '0', '0', '600', '0', '2015', '1200', null, '1', '1', '1419905324000', '0', '108');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111579', '1', '学校班车', 'A', '1', '1', '0', '0', '6666', '0', '2015', '0', '57,56,53,52,46,45,43,42,41,40,39,38,37,22,21', '1', '1', '1419904536000', '0', '107');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111580', '1', '校外产业公车（每个单位仅限办理2张）', 'B', '2', '1', '0', '0', '600', '140', '2015', '600', null, '1', '1', '1421985864000', '0', '117');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111581', '1', '校外产业员工（每个单位仅限办理2张）', 'B', '2', '1', '0', '0', '600', '140', '2015', '600', null, '1', '1', '1421985942000', '0', '118');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111582', '1', '退休返聘员工', 'B', '2', '1', '0', '0', '6666', '140', '2015', '200', null, '1', '1', '1421986071000', '0', '119');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111583', '1', '嘉定校区', 'B', '2', '1', '0', '0', '0', '0', '2015', '1200', null, '1', '1', '1422323790000', '0', '120');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111584', '1', '后勤产业职工', 'B', '2', '1', '0', '0', '1000', '140', '2015', '600', null, '1', '1', '1422838493000', '0', '121');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111585', '1', '单位（校内停放包月）', 'B', '2', '1', '0', '0', '6666', '3333', '2015', '1200', null, '1', '1', '1424913072000', '0', '122');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111586', '1', '本部校区　（2）', 'B', '2', '1', '0', '0', '600', '0', '2015', '600', null, '1', '1', '1425089799000', '0', '123');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111587', '1', '嘉定校区', 'B', '2', '1', '0', '0', '600', '0', '2015', '600', null, '1', '1', '1425953511000', '0', '124');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111588', '1', '本部校区　（3）', 'B', '2', '1', '0', '0', '600', '0', '2015', '300', null, '1', '1', '1425089908000', '0', '125');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111589', '1', '产业公车', 'B', '1', '1', '0', '0', '6666', '3333', '2015', '600', '61,60,57,56,53,52,48,47,46,45,43,42,41,40,39,38,37,22,21', '1', '1', '1426824902000', '0', '126');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111590', '1', '教工（本部）', 'B', '2', '1', '0', '0', '6666', '140', '2015', '200', null, '1', '1', '1427339893000', '0', '127');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111591', '1', '领导证（A）', 'A', '2', '1', '0', '0', '6666', '3333', '2015', '0', null, '1', '1', '1430718588000', '0', '128');
INSERT INTO "SQTC"."T_CARD_TYPE" VALUES ('111592', '1', '公车', 'B', '2', '1', '0', '0', '6666', '3333', '2015', '0', null, '1', '1', '1431327490000', '0', '129');

-- ----------------------------
-- Table structure for T_CARINFO
-- ----------------------------
DROP TABLE "SQTC"."T_CARINFO";
CREATE TABLE "SQTC"."T_CARINFO" (
"ID" NUMBER(19) NOT NULL ,
"CAR_NO" VARCHAR2(64 BYTE) NOT NULL ,
"CAR_TYPE" VARCHAR2(64 BYTE) NULL ,
"NAME" VARCHAR2(64 BYTE) NOT NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL ,
"IS_ENABLED" NUMBER DEFAULT 1  NULL ,
"REMARK" VARCHAR2(1024 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_CARINFO
-- ----------------------------

-- ----------------------------
-- Table structure for T_DEPT
-- ----------------------------
DROP TABLE "SQTC"."T_DEPT";
CREATE TABLE "SQTC"."T_DEPT" (
"DWDM" VARCHAR2(20 BYTE) NOT NULL ,
"DWBZMC" VARCHAR2(180 BYTE) NULL ,
"USER_TYPE" VARCHAR2(30 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_DEPT"."DWDM" IS '单位代码';
COMMENT ON COLUMN "SQTC"."T_DEPT"."DWBZMC" IS '单位标准名称';
COMMENT ON COLUMN "SQTC"."T_DEPT"."USER_TYPE" IS '人员类型';

-- ----------------------------
-- Records of T_DEPT
-- ----------------------------
INSERT INTO "SQTC"."T_DEPT" VALUES ('000192', '经济与管理学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000193', '工商管理系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000194', '管理科学与工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000195', '会计系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000196', '建筑管理与房地产系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000197', '经济金融系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000198', '公共管理系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000199', 'MPA教学中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000200', 'MBA教学中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000201', '学院办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000202', '环境科学与工程学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000203', '市政工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000204', '环境科学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000205', '环境工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000206', '水环境综合治理研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000207', '中心实验室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000208', '环评室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000209', '质量检测中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000210', '亚同公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000211', '水处理公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000212', '设计分院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000213', '工程中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000214', '学院办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000215', '材料科学与工程学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000216', '水泥基材料研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000217', '无机非金属材料研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000218', '金属基材料研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000219', '高分子材料研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000220', '建筑材料研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000221', '环境材料研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000222', '材料化学研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000223', '重点实验室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000224', '学报编辑室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000225', '培训中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000226', '学院办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000227', '交通运输工程学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000228', '道路与机场工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000229', '运输管理工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000230', '交通工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000231', '交通信息工程控制研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000232', '交通信息通信联合实验中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000233', '铁建工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000234', 'ITS研究中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000235', '道路与交通工程部门开放实验室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000236', '学院办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000237', '法政学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000238', '思想理论教育系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000239', '法律系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000240', '政治学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000241', '社会学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000242', '知识产权', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000243', '学院办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000244', '电子与信息工程学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000245', '计算机科学与工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000246', '电气工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000247', '控制科学与工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000248', '信息与通信工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000249', '电子科学与技术系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000250', '实验中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000251', 'CIMS中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000252', 'CAD中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000253', '半导体与信息技术研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000254', '学院办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000255', '医学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000256', '基础医学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000257', '护理系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000258', '临床一系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000259', '临床二系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000260', '动物实验中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000261', '药物研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000262', '医学期刊编辑部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000263', '医院管理处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000264', '学院办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000265', '理学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000266', '工程力学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000267', '学院办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000268', '外国语学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000269', '英语系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000270', '德语系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000271', '日语系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000272', '留德预备部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000273', '科技德语中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000274', '外国语言学与应用语言学研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000275', '德国研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000276', '日本研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000277', '法语系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000278', '学院办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000279', '理学部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000280', '数学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000281', '物理科学与工程学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000282', '化学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000283', '航空航天与力学学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000284', '声学研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000285', '艺术与传媒学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000286', '汽车学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000287', '软件学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000288', '生命科学与技术学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000289', '海洋与地球科学学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000290', '人文学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000291', '电影学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000292', '设计创意学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000293', '体育教学部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000294', '音乐系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000295', '马克思主义学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000296', '防灾研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000297', '海纳战略研究中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000298', '现代农业工程研究院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000299', '大众-同济汽车研究院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000300', '功能材料研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000301', '特种土木工程技术研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000302', '铁道与城市轨道交通研究院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000303', '城市研究中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000304', '亚洲太平洋研究中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000305', '儿童口腔医学研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000306', '城市污染控制国家工程研究中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000307', '中意学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000308', '待分配', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000309', '人才交流中心待岗人员', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000310', '产业人才交流中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000311', '柔性引进人才', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000313', '同济医院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000314', '口腔医院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000317', '停薪人员', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000322', '同济大学生物医学工程与纳米科学研究院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000324', '武装部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000325', '政治与国际关系学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000326', '法学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000327', '出国培训学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000331', '上海地面交通工具风洞中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000332', '绿色建筑及新能源研究中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000333', '文法联合党委', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000334', '《城市规划汇刊》编辑部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000335', '《时代建筑》编辑部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000336', '医学院再生医学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000337', '医学院病理学与病理生理学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000338', '医学院免疫学与病原生物学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000339', '生物信息学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000340', '分子与细胞生物学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000341', '生物医药与技术系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000342', '同济大学蛋白质研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000343', '同济大学中医研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000344', '同济大学高等研究院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000345', '附属东方医院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000553', '第十人民医院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000554', '肺科医院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000555', '第一妇婴保健院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000556', '招生办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000557', '房屋管理部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000558', '中芬中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000559', '测绘与地理信息学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000560', '同济大学磁浮交通工程技术研究中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000561', '联合国环境规划署-同济大学环境与可持续发展学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000562', '工程实践中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000563', '沪北校区管理办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000564', '同济大学国有资产管理委员会办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000565', '同济大学博士后管理办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000566', '同济大学人才中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000567', '科学技术研究院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000568', '项目办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000569', '军工办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000570', '成果办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000571', '基地办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000572', '综合办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000573', '信息化办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000574', '港澳台事务办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000575', '留学生办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000576', '文科办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000577', '同济大学人才工作领导小组办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000578', '同济大学德国研究中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000579', '公共英语教学部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000580', '同济大学新农村发展研究院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000581', '学报编辑部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000582', '网络管理中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000583', '同济大学中西学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000584', '结构减灾工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000585', '附属杨浦医院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000586', '采购与招标管理办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000587', '上海同济资产经营有限公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000588', '产业党工委', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000589', '化学科学与工程学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000590', '数学科学学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('270', '设计创意学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('150', '职业技术教育学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('280', '设计与艺术学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('010', '建筑与城市规划学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('020', '土木工程学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('030', '机械与能源工程学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('040', '经济与管理学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('050', '环境科学与工程学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('060', '材料科学与工程学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('070', '文法学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('080', '电子与信息工程学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('090', '外国语学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('100', '理学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('101', '航空航天与力学学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('102', '数学系', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('103', '物理科学与工程学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('104', '化学科学与工程学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('290', '测绘与地理信息学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('106', '声学研究所', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('110', '医学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('114', '口腔医学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('120', '交通运输工程学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('130', '中德学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('160', '生命科学与技术学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('170', '软件学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('180', '汽车学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('190', '海洋与地球科学学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('200', '艺术与传媒学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('140', '铁道与城市轨道交通研究院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('250', '政治与国际关系学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('210', '人文学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('220', '法政学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('89', '研究生院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('98', '相关学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('230', '体育教学部', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('240', '法学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('260', '马克思主义学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('300', '国际文化交流学院', '研究生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('132', '国内交流生', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('540', '马克思主义学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('142', '口腔医学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('590', '高等技术学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('002', '行政', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('470', '知识产权学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('001', '教务处', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('006', '选科与学籍管理中心', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('131', '国际交流生', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('010', '经济与管理学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('011', '经济与管理学院建筑管理与房地产', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('012', '经济与管理学院经济信息系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('013', '经济与管理学院国际工程营造与估', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('014', '经济与管理学院工商管理系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('015', '经济与管理学院管理科学与信息系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('020', '建筑与城市规划学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('021', '建筑与城市规划学院建筑系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('022', '建筑与城市规划学院城市规划系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('023', '建筑与城市规划学院工业设计系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('024', '建筑与城市规划学院风景旅游系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('029', '建筑与城市规划学院艺术设计专', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('030', '土木工程学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('031', '土木工程学院建筑工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('032', '土木工程学院桥梁工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('033', '土木工程学院地下建筑与工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('034', '土木工程学院道路与交通工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('035', '土木工程学院测量与国土信息工程', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('040', '机械与能源工程学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('041', '机械工程学院机械工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('042', '机械工程学院热能工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('043', '机械工程学院汽车工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('050', '环境科学与工程学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('051', '环境工程学院环境工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('060', '计算机学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('061', '计算机学院计算机科学与工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('070', '文法学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('071', '文法学院社会科学系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('072', '文法学院文化艺术系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('073', '文法学院外语系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('074', '文法学院德语系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('075', '文法学院法律系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('076', '文法学院经济贸易系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('077', '文法学院日语系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('080', '材料科学与工程学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('081', '无机材料科学与工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('082', '高分子材料科学与工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('100', '电子与信息工程学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('101', '电子与信息工程学院计算机科学与', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('102', '电子与信息工程学院电气工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('104', '电子与信息工程学院电气工程专', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('105', '电子与信息工程学院通信专业', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('110', '外国语学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('111', '外国语学院德语系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('112', '外国语学院英语系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('113', '外国语学院日语系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('120', '理学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('121', '理学院海洋地质与地球物理系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('122', '数学系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('123', '化学系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('124', '物理系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('125', '理学院工程力学与技术系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('130', '国际文化交流学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('140', '医学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('150', '交通运输工程学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('151', '交通运输学院道路与交通工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('152', '交通运输学院运输管理工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('160', '女子学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('170', '生命科学与技术学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('180', '传播与艺术学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('190', '汽车学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('200', '道路与交通工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('210', '地下建筑与工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('220', '测量与国土信息工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('230', '电气工程系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('240', '材料科学与工程系(旧)', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('241', '工程实践中心', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('250', '海洋地质与地球物理系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('260', '应用数学系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('270', '物理系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('280', '化学系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('290', '工程力学与技术系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('300', '新生院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('310', '海洋与地球科学学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('320', '体育教学部', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('330', '德育', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('340', '图书馆', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('350', '声学研究所', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('360', '武装部', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('380', '绿化组', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('390', '电教中心', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('400', '外聘', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('410', '计算中心', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('420', '软件学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('43', '航空航天与力学学院(废)', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('440', '电影学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('450', '航空航天与力学学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('500', '网络学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('999', '全校', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('490', '职业技术教育学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('480', '传播与艺术学院音乐系', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('900', '国际文化交流学院(900)', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('460', '现代农业科学与工程研究院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('430', '铁道与城市轨道交通研究院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('078', '法政学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('510', '中德工程学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('079', '人文学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('520', '法学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('530', '政治与国际关系学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('700', '环境与可持续发展学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('550', '设计创意学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('370', '工结所', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('560', '中芬中心', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('133', '国际交流生2', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('800', '校外课程平台', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('570', '测绘与地理信息学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('580', '物理科学与工程学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('600', '艺术与传媒学院', '本科生');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000002', '党委办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000003', '纪委', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000004', '组织部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000005', '宣传部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000006', '统战部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000007', '研究生工作部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000008', '机关党委', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000009', '妇委', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000010', '工会', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000011', '团委', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000013', '校长办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000014', '研究生院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000015', '教务处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000016', '科学技术处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000017', '学生工作处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000018', '人事处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000019', '发展规划研究中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000020', '外事办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000021', '保卫处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000022', '离退休办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000023', '财务处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000024', '审计处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000025', '基建处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000026', '资产管理处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000027', '产业管理办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000028', '实验室与设备管理处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000029', '对外联络与发展办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000030', '教学质量管理办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000032', '出版社', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000033', '继续教育学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000034', '职业技术教育学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000035', '高等技术学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000036', '楼宇设备系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000037', '图书馆', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000038', '档案馆', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000039', '国际文化交流学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000040', '研究院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000041', '中德学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000042', '高教研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000043', '网络学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000044', '女子学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000045', '德国学术中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000046', '世界银行研究中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000047', '艺术中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000048', '逸夫楼管理中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000049', '中国村社会发展促进会同济培训中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000050', '沪东管委会', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000051', '沪西管委会', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000052', '沪北管委会', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000053', '嘉定管委会', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000054', '教育技术与计算中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000055', '能源管理中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000056', '校医院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000057', '超大规模集成电路研发中心(微电子中心)', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000058', '中法工程和管理学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000059', '超大规模集成电路研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000060', '同济大学浙江学院人员', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000061', '中德友好医院（挂人才中心）', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000062', '医学与生命科学部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000063', '文科办公室(现挂靠科技处)', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000064', '中德工程学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000065', '办学质量评估院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000066', '中国科技管理研究院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000068', '后勤集团办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000069', '饮食中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000070', '生活中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000071', '物资中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000072', '社区中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000073', '产业中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000074', '物业中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000075', '沪西中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000076', '扬子公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000077', '其他', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000078', '干训楼', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000079', '会务中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000080', '工程中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000081', '直管企业', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000082', '逸夫楼中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000083', '环保公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000084', '水处理中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000085', '飞尔达装潢', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000086', '沪东工厂', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000087', '科技开发', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000088', '环保集团', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000089', '沪西工厂', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000090', '建设工程质量检测站', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000091', '沪西印刷厂', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000092', '电信设备公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000093', '上海天佑工程咨询有限公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000094', '实业公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000095', '牙材材料厂', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000096', '科技园有限公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000097', '同济建设项目代理公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000098', '凌风公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000099', '同捷科技公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000100', '大众维修', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000101', '光泉实业', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000102', '产业驻西安办事处', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000103', '污染控制中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000104', '航机公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000105', '航机在岗人员', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000106', '航机公司待退休', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000107', '监理咨询公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000108', '印刷厂', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000109', '城市规划设计院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000110', '同济新产业公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000111', '公司办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000112', '联合发展公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000113', '经济开发', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000114', '建筑科技', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000115', '华育公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000116', '宏城房产', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000117', '金建房产', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000118', '诚见装饰', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000119', '城汇装潢', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000120', '城汇工程部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000121', '音响照明', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000122', '双姆公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000123', '高分子材料', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000124', '新产业发展', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000125', '申建办公', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000126', '其他', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000127', '建筑设计院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000128', '院部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000129', '设计一所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000130', '设计二所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000131', '设计三所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000132', '住宅所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000133', '市政设计院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000134', '勘察研究院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000135', '岩土勘察分院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000136', '桥梁设计院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000137', '轨道交通分院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000138', '浦东分院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000139', '深圳分院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000140', '协力', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000141', '建科', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000142', '膜结构', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000143', '同艺图文', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000144', '开元', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000145', '铁大设计', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000146', '工程设计院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000147', '其他1', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000148', '其他2', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000149', '其他3', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000150', '高薪所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000151', '同悦', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000152', '环境工程设计分院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000153', '同济科技股份公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000154', '科实总公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000155', '开发公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000156', '建设开发部', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000157', '机电厂', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000158', '放免所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000159', '专服中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000160', '室内公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000161', '爆破公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000162', '房地产公司', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000163', '建筑与城市规划学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000164', '建筑系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000165', '城市规划系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000166', '城规研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000167', '艺术设计系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000168', '景观学系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000169', '学院办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000170', '机械与能源工程学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000171', '机车车辆研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000172', '机械设计与理论研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000173', '现代制造技术研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000174', '机械电子工程研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000175', '工业工程教研室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000176', '机械工程图学教研室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000177', '热能与环境研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000178', '制冷与热工程研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000179', '暖通空调及燃气研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000180', '机械基础实验中心', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000181', '学院办公室', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000182', '土木工程学院', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000183', '建筑工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000184', '桥梁工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000185', '地下建筑系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000186', '测量系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000187', '结构工程与防灾研究所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000188', '预应力所', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000190', '水利工程系', '教职工');
INSERT INTO "SQTC"."T_DEPT" VALUES ('000191', '学院办公室', '教职工');

-- ----------------------------
-- Table structure for T_DICT
-- ----------------------------
DROP TABLE "SQTC"."T_DICT";
CREATE TABLE "SQTC"."T_DICT" (
"ID" NUMBER(19) NOT NULL ,
"TYPE_NAME" VARCHAR2(255 BYTE) NULL ,
"NAME" VARCHAR2(255 BYTE) NULL ,
"CODE" VARCHAR2(255 BYTE) NULL ,
"DESCRIPTION" VARCHAR2(255 BYTE) NULL ,
"SORT" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_DICT
-- ----------------------------

-- ----------------------------
-- Table structure for T_LOGIN_LOG
-- ----------------------------
DROP TABLE "SQTC"."T_LOGIN_LOG";
CREATE TABLE "SQTC"."T_LOGIN_LOG" (
"ID" NUMBER(19) NOT NULL ,
"CREATE_TIME" TIMESTAMP(6)  NULL ,
"USER_ID" NUMBER(19) NULL ,
"USER_IP" VARCHAR2(255 BYTE) NULL ,
"LOG_CONTENT" VARCHAR2(512 BYTE) NULL ,
"USER_NAME" VARCHAR2(255 BYTE) NULL ,
"SESSION_ID" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_LOGIN_LOG
-- ----------------------------
INSERT INTO "SQTC"."T_LOGIN_LOG" VALUES ('414450', TO_TIMESTAMP(' 2016-08-01 21:51:57:534000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '127.0.0.1', '登录成功', '管理员', '537F6FEFB7B40622DAEAEAD0137793DA');
INSERT INTO "SQTC"."T_LOGIN_LOG" VALUES ('414470', TO_TIMESTAMP(' 2016-08-01 23:28:47:786000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '127.0.0.1', '登录成功', '管理员', '3F79A058769CD4163E4CC4091EC0E21F');
INSERT INTO "SQTC"."T_LOGIN_LOG" VALUES ('414500', TO_TIMESTAMP(' 2016-08-02 00:33:06:879000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '127.0.0.1', '登录成功', '管理员', '8C40229FF22CE8EC248232EA364568CD');
INSERT INTO "SQTC"."T_LOGIN_LOG" VALUES ('414501', TO_TIMESTAMP(' 2016-08-02 00:38:55:088000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '127.0.0.1', '登录成功', '管理员', 'D835686C35A7BF1E3409E46266CE8E84');
INSERT INTO "SQTC"."T_LOGIN_LOG" VALUES ('414260', TO_TIMESTAMP(' 2016-07-30 00:19:02:338000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '127.0.0.1', '退出', '管理员', 'A4ACAB42BA36FE1BF389DF5014379145');
INSERT INTO "SQTC"."T_LOGIN_LOG" VALUES ('414430', TO_TIMESTAMP(' 2016-07-31 02:56:51:442000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '127.0.0.1', '登录成功', '管理员', '7B999E9FD96E706785EC7EA9434F31F6');
INSERT INTO "SQTC"."T_LOGIN_LOG" VALUES ('414510', TO_TIMESTAMP(' 2016-08-02 21:56:44:010000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, '127.0.0.1', '登录失败-密码错误', 'admin', '394A50D69ED88DB7BB8161FED66F04BE');
INSERT INTO "SQTC"."T_LOGIN_LOG" VALUES ('414511', TO_TIMESTAMP(' 2016-08-02 21:56:50:556000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '127.0.0.1', '登录成功', '管理员', '394A50D69ED88DB7BB8161FED66F04BE');
INSERT INTO "SQTC"."T_LOGIN_LOG" VALUES ('414520', TO_TIMESTAMP(' 2016-08-02 22:26:16:852000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '127.0.0.1', '登录成功', '管理员', '7805245A106E8E75A0A0EDC73165FCEC');
INSERT INTO "SQTC"."T_LOGIN_LOG" VALUES ('414440', TO_TIMESTAMP(' 2016-08-01 10:01:16:102000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '127.0.0.1', '登录成功', '管理员', '6CFA0453F5E302DC33C00EAF3B2CEFCB');
INSERT INTO "SQTC"."T_LOGIN_LOG" VALUES ('414540', TO_TIMESTAMP(' 2016-08-02 22:36:55:819000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '127.0.0.1', '登录成功', '管理员', '192055EF71A0DA250D6FE93C844BE9B7');

-- ----------------------------
-- Table structure for T_MEMBER
-- ----------------------------
DROP TABLE "SQTC"."T_MEMBER";
CREATE TABLE "SQTC"."T_MEMBER" (
"ID" NUMBER(19) NOT NULL ,
"NICKNAME" VARCHAR2(255 BYTE) NULL ,
"BIRTH_DAY" VARCHAR2(64 BYTE) NOT NULL ,
"GENDER" VARCHAR2(64 BYTE) NOT NULL ,
"SCHOOL_CARD_TYPE" VARCHAR2(64 BYTE) NULL ,
"EXPIRY_NUM" NUMBER NULL ,
"EXPIRY_DATE" DATE NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL ,
"REGISTRATION_DATE" DATE NULL ,
"LAST_LOGIN_TIME" TIMESTAMP(6)  NULL ,
"CONTACT_ADDRESS" VARCHAR2(255 BYTE) NULL ,
"CONTACT_PHONE_NUMBER" VARCHAR2(255 BYTE) NULL ,
"SOURCE" VARCHAR2(64 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_MEMBER
-- ----------------------------

-- ----------------------------
-- Table structure for T_PARKING_GARAGE
-- ----------------------------
DROP TABLE "SQTC"."T_PARKING_GARAGE";
CREATE TABLE "SQTC"."T_PARKING_GARAGE" (
"ID" NUMBER(19) NOT NULL ,
"CODE" VARCHAR2(255 BYTE) NULL ,
"NAME" VARCHAR2(255 BYTE) NULL ,
"IP_ADDRESS" VARCHAR2(255 BYTE) NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL ,
"IS_ENABLED" NUMBER DEFAULT 1  NULL ,
"PARKING_LOT_AREA_ID" NUMBER(19) NULL ,
"GARAGE_TYPE" NUMBER NULL ,
"DESCRIPTION" VARCHAR2(255 BYTE) NULL ,
"CAR_NO" VARCHAR2(50 BYTE) NULL ,
"INTIME" TIMESTAMP(6)  NULL ,
"IS_ONLINE" NUMBER DEFAULT 0  NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_PARKING_GARAGE
-- ----------------------------
INSERT INTO "SQTC"."T_PARKING_GARAGE" VALUES ('414560', null, 'C001', null, TO_TIMESTAMP(' 2016-08-02 22:42:32:038000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '111640', '0', null, null, null, null);

-- ----------------------------
-- Table structure for T_PARKING_GARAGE_CARNO_LOG
-- ----------------------------
DROP TABLE "SQTC"."T_PARKING_GARAGE_CARNO_LOG";
CREATE TABLE "SQTC"."T_PARKING_GARAGE_CARNO_LOG" (
"ID" NUMBER(19) NOT NULL ,
"PARKING_NAME" VARCHAR2(255 BYTE) NULL ,
"CAMERA_IP" VARCHAR2(255 BYTE) NULL ,
"STATUS" NUMBER NULL ,
"CAR_NO" VARCHAR2(50 BYTE) NULL ,
"INTIME" TIMESTAMP(6)  NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_PARKING_GARAGE_CARNO_LOG"."PARKING_NAME" IS '车位编号';
COMMENT ON COLUMN "SQTC"."T_PARKING_GARAGE_CARNO_LOG"."CAMERA_IP" IS '相机IP';
COMMENT ON COLUMN "SQTC"."T_PARKING_GARAGE_CARNO_LOG"."STATUS" IS '车位状态';
COMMENT ON COLUMN "SQTC"."T_PARKING_GARAGE_CARNO_LOG"."CAR_NO" IS '占位车牌号';
COMMENT ON COLUMN "SQTC"."T_PARKING_GARAGE_CARNO_LOG"."INTIME" IS '上报时间';
COMMENT ON COLUMN "SQTC"."T_PARKING_GARAGE_CARNO_LOG"."CREATE_TIME" IS '创建时间';

-- ----------------------------
-- Records of T_PARKING_GARAGE_CARNO_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for T_PARKING_LOCK
-- ----------------------------
DROP TABLE "SQTC"."T_PARKING_LOCK";
CREATE TABLE "SQTC"."T_PARKING_LOCK" (
"ID" NUMBER(19) NOT NULL ,
"LOCK_NUM" VARCHAR2(255 BYTE) NULL ,
"DEVICE_NUM" VARCHAR2(255 BYTE) NULL ,
"IP_ADDRESS" VARCHAR2(255 BYTE) NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL ,
"IS_ENABLED" NUMBER DEFAULT 1  NULL ,
"PARKING_GARAGE_ID" NUMBER(19) NULL ,
"IS_CAR_ON" NUMBER NULL ,
"IS_CAR_ON_TIME" TIMESTAMP(6)  NULL ,
"IS_OK" NUMBER NULL ,
"IS_OK_TIME" TIMESTAMP(6)  NULL ,
"IS_ONLINE" NUMBER NULL ,
"IS_ONLINE_TIME" TIMESTAMP(6)  NULL ,
"IS_OPEN" NUMBER NULL ,
"IS_FOREVER_OPEN_CLOSE" NUMBER NULL ,
"DESCRIPTION" VARCHAR2(1024 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_PARKING_LOCK
-- ----------------------------

-- ----------------------------
-- Table structure for T_PARKING_LOCK_EVENT_LOG
-- ----------------------------
DROP TABLE "SQTC"."T_PARKING_LOCK_EVENT_LOG";
CREATE TABLE "SQTC"."T_PARKING_LOCK_EVENT_LOG" (
"ID" NUMBER(19) NOT NULL ,
"EVENT_TYPE" NUMBER NULL ,
"SOURCE_TYPE" NUMBER NULL ,
"PARKING_LOCK_ID" NUMBER(19) NULL ,
"REPORTED_TIME" TIMESTAMP(6)  NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL ,
"MESSAGE" VARCHAR2(512 BYTE) NULL ,
"LOCK_NUM" VARCHAR2(255 BYTE) NULL ,
"DEVICE_NUM" VARCHAR2(255 BYTE) NULL ,
"IP_ADDRESS" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_PARKING_LOCK_EVENT_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for T_PARKING_LOCK_OPERATION_EVENT
-- ----------------------------
DROP TABLE "SQTC"."T_PARKING_LOCK_OPERATION_EVENT";
CREATE TABLE "SQTC"."T_PARKING_LOCK_OPERATION_EVENT" (
"ID" NUMBER(19) NOT NULL ,
"EVENT_TYPE" NUMBER NULL ,
"SOURCE_TYPE" NUMBER NULL ,
"PARKING_LOCK_ID" NUMBER(19) NULL ,
"REPORTED_TIME" TIMESTAMP(6)  NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL ,
"MESSAGE" VARCHAR2(512 BYTE) NULL ,
"LOCK_NUM" VARCHAR2(255 BYTE) NULL ,
"DEVICE_NUM" VARCHAR2(255 BYTE) NULL ,
"IP_ADDRESS" VARCHAR2(255 BYTE) NULL ,
"RESULT_TYPE" VARCHAR2(255 BYTE) NULL ,
"USER_ID" NUMBER(19) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_PARKING_LOCK_OPERATION_EVENT
-- ----------------------------

-- ----------------------------
-- Table structure for T_PARKING_LOT
-- ----------------------------
DROP TABLE "SQTC"."T_PARKING_LOT";
CREATE TABLE "SQTC"."T_PARKING_LOT" (
"ID" NUMBER(19) NOT NULL ,
"NAME" VARCHAR2(255 BYTE) NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL ,
"IS_ENABLED" NUMBER DEFAULT 1  NULL ,
"BAIDU_LATITUDE_LNG" VARCHAR2(255 BYTE) NULL ,
"BAIDU_LATITUDE_LAT" VARCHAR2(255 BYTE) NULL ,
"ADDRESS" VARCHAR2(255 BYTE) NULL ,
"DESCRIPTION" VARCHAR2(4000 BYTE) NULL ,
"PHOTO_ATTACH_ID" NUMBER(19) NULL ,
"CAR_NUMBER" NUMBER NULL ,
"CODE" VARCHAR2(255 BYTE) NULL ,
"CAN_GET_CARD" NUMBER NULL ,
"LASTTIME" NUMBER(19) NULL ,
"CAMPUS_ID" NUMBER(19) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_PARKING_LOT"."NAME" IS '校区停车场名称';
COMMENT ON COLUMN "SQTC"."T_PARKING_LOT"."CAN_GET_CARD" IS '是否可以领进校证';
COMMENT ON COLUMN "SQTC"."T_PARKING_LOT"."LASTTIME" IS '最后更新时间';
COMMENT ON COLUMN "SQTC"."T_PARKING_LOT"."CAMPUS_ID" IS '进校证数据库表中的原始ID';

-- ----------------------------
-- Records of T_PARKING_LOT
-- ----------------------------
INSERT INTO "SQTC"."T_PARKING_LOT" VALUES ('1941', '嘉定校区', TO_TIMESTAMP(' 2016-04-28 00:23:11:281000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '1', '11', '11', '1', null, null, '1', '0', '1420522488', '2');

-- ----------------------------
-- Table structure for T_PARKING_LOT_AREA
-- ----------------------------
DROP TABLE "SQTC"."T_PARKING_LOT_AREA";
CREATE TABLE "SQTC"."T_PARKING_LOT_AREA" (
"ID" NUMBER(19) NOT NULL ,
"NAME" VARCHAR2(255 BYTE) NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL ,
"IS_ENABLED" NUMBER DEFAULT 1  NULL ,
"BAIDU_LATITUDE_LNG" VARCHAR2(255 BYTE) DEFAULT ''  NULL ,
"BAIDU_LATITUDE_LAT" VARCHAR2(255 BYTE) DEFAULT ''  NULL ,
"DESCRIPTION" VARCHAR2(4000 BYTE) NULL ,
"PHOTO_ATTACH_ID" NUMBER(19) NULL ,
"CAR_NUMBER" NUMBER NULL ,
"PARKING_LOT_ID" NUMBER(19) NULL ,
"CODE" VARCHAR2(255 BYTE) NULL ,
"PID" NUMBER(19) NULL ,
"FULL_INDEX_NAME" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_PARKING_LOT_AREA
-- ----------------------------
INSERT INTO "SQTC"."T_PARKING_LOT_AREA" VALUES ('111640', '通达馆地下车库', TO_TIMESTAMP(' 2016-07-17 13:21:40:511000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', null, null, '东东', null, '36', '1941', 'T001', null, '通达馆地下车库');

-- ----------------------------
-- Table structure for T_PARKING_ORDER
-- ----------------------------
DROP TABLE "SQTC"."T_PARKING_ORDER";
CREATE TABLE "SQTC"."T_PARKING_ORDER" (
"ID" NUMBER(19) NOT NULL ,
"IN_CAMERA_IP" VARCHAR2(64 BYTE) NULL ,
"IN_PLATE_NO" VARCHAR2(64 BYTE) NULL ,
"IN_TIME" TIMESTAMP(6)  NULL ,
"IN_CHARGE_USER" VARCHAR2(64 BYTE) NULL ,
"IN_CHARGE_STARTTIME" VARCHAR2(64 BYTE) NULL ,
"IN_PIC_NAME" VARCHAR2(255 BYTE) NULL ,
"IN_FINDSTR" VARCHAR2(255 BYTE) NULL ,
"IN_SCHOOL_DOOR_NAME" VARCHAR2(255 BYTE) NULL ,
"IN_SOURCE_IP" VARCHAR2(64 BYTE) NULL ,
"IN_SOURCE_NAME" VARCHAR2(64 BYTE) NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL ,
"UPDATE_TIME" TIMESTAMP(6)  NULL ,
"IS_ENABLED" NUMBER DEFAULT 1  NULL ,
"CAR_GATEGORY" NUMBER NULL ,
"CAR_TYPE" NUMBER NULL ,
"OUT_TIME" TIMESTAMP(6)  NULL ,
"STAYTIME" NUMBER(19,2) NULL ,
"OUT_SCHOOL_DOOR" VARCHAR2(255 BYTE) NULL ,
"PARKING_TIME_TYPE" NUMBER NULL ,
"AMOUNTS_RECEIVABLE" NUMBER(19,2) NULL ,
"AMOUNTS_PAID" NUMBER(19,2) NULL ,
"IS_AMOUNT_SUCCESS" NUMBER NULL ,
"FAIL_REASON" VARCHAR2(255 BYTE) NULL ,
"OUT_PIC_NAME" VARCHAR2(255 BYTE) NULL ,
"OPERATE_FLAG" NUMBER NULL ,
"AMOUNT_TYPE" NUMBER NULL ,
"AMOUNT_ONLINE_OK" NUMBER NULL ,
"AMOUNT_TIME" TIMESTAMP(6)  NULL ,
"ORDER_NUMBER" VARCHAR2(255 BYTE) NULL ,
"PAYMENT_ORDER_NUMBER" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_PARKING_ORDER
-- ----------------------------
INSERT INTO "SQTC"."T_PARKING_ORDER" VALUES ('414480', '1', '1', TO_TIMESTAMP(' 2016-08-01 00:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', null, '1', null, null, null, null, TO_TIMESTAMP(' 2016-08-01 23:29:05:952000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, null, null, TO_TIMESTAMP(' 2016-08-17 00:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for T_RELATION_CARDINFO_CAMPUS
-- ----------------------------
DROP TABLE "SQTC"."T_RELATION_CARDINFO_CAMPUS";
CREATE TABLE "SQTC"."T_RELATION_CARDINFO_CAMPUS" (
"CARDINFO_ID" NUMBER(19) NULL ,
"CAMPUS_ID" NUMBER(19) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_RELATION_CARDINFO_CAMPUS
-- ----------------------------

-- ----------------------------
-- Table structure for T_RELATION_CARDTYPE_CAMPUS
-- ----------------------------
DROP TABLE "SQTC"."T_RELATION_CARDTYPE_CAMPUS";
CREATE TABLE "SQTC"."T_RELATION_CARDTYPE_CAMPUS" (
"CARDTYPE_ID" NUMBER(19) NULL ,
"CAMPUS_ID" NUMBER(19) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_RELATION_CARDTYPE_CAMPUS
-- ----------------------------
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111600', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111600', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111601', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111602', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111602', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111602', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111602', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111602', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111602', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111603', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111604', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111606', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111606', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111606', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111606', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111606', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111606', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111607', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111608', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111608', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111608', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111608', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111609', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111609', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111609', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111610', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111610', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111610', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111610', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111611', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111611', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111611', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1988');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111612', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111613', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111613', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111613', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111613', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111613', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111613', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111613', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111613', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111613', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111613', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111614', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111614', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111615', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111615', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111615', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111616', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1988');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111619', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111620', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111620', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111620', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111620', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111620', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111620', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111621', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111625', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111625', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111625', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111626', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111626', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111626', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111627', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111628', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111629', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111630', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111631', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1988');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111632', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110240', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110241', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1988');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110242', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1988');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110243', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110244', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110245', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1988');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110246', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110247', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110247', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110247', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1988');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('110248', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111510', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111511', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111512', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111539', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111541', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111544', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111623', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1988');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111624', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111546', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111553', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111565', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111574', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111575', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111577', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111579', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111589', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1988');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111593', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1988');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111594', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1988');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1989');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1986');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1987');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111595', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111596', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111597', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111597', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111597', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111597', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111597', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111598', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111598', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111598', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111598', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111598', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1981');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1985');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1975');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1976');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1983');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1984');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1982');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1971');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1972');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1979');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1977');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1978');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1973');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111599', '1974');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111600', '1990');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111600', '1980');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111600', '1970');
INSERT INTO "SQTC"."T_RELATION_CARDTYPE_CAMPUS" VALUES ('111600', '1971');

-- ----------------------------
-- Table structure for T_RESOURCE
-- ----------------------------
DROP TABLE "SQTC"."T_RESOURCE";
CREATE TABLE "SQTC"."T_RESOURCE" (
"ID" NUMBER(19) NOT NULL ,
"POSITION" NUMBER(10,2) NOT NULL ,
"RESOURCE_TYPE" VARCHAR2(255 BYTE) NOT NULL ,
"VAL" VARCHAR2(255 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_RESOURCE
-- ----------------------------

-- ----------------------------
-- Table structure for T_RESOURCE_AUTHORITY
-- ----------------------------
DROP TABLE "SQTC"."T_RESOURCE_AUTHORITY";
CREATE TABLE "SQTC"."T_RESOURCE_AUTHORITY" (
"RESOURCE_ID" NUMBER(19) NOT NULL ,
"AUTHORITY_ID" NUMBER(19) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_RESOURCE_AUTHORITY
-- ----------------------------

-- ----------------------------
-- Table structure for T_ROLE
-- ----------------------------
DROP TABLE "SQTC"."T_ROLE";
CREATE TABLE "SQTC"."T_ROLE" (
"ID" NUMBER(19) NOT NULL ,
"NAME" VARCHAR2(255 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_ROLE
-- ----------------------------
INSERT INTO "SQTC"."T_ROLE" VALUES ('1', '管理员');
INSERT INTO "SQTC"."T_ROLE" VALUES ('2', '会员');

-- ----------------------------
-- Table structure for T_ROLE_AUTHORITY
-- ----------------------------
DROP TABLE "SQTC"."T_ROLE_AUTHORITY";
CREATE TABLE "SQTC"."T_ROLE_AUTHORITY" (
"ROLE_ID" NUMBER(19) NOT NULL ,
"AUTHORITY_ID" NUMBER(19) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_ROLE_AUTHORITY
-- ----------------------------

-- ----------------------------
-- Table structure for T_SHOUFEI
-- ----------------------------
DROP TABLE "SQTC"."T_SHOUFEI";
CREATE TABLE "SQTC"."T_SHOUFEI" (
"ID" NUMBER(19) NOT NULL ,
"CARD_TYPE_ID" NUMBER(19) NULL ,
"PARKING_LOT_ID" NUMBER(19) NULL ,
"DAY_TIME" NUMBER(7,2) NULL ,
"NIGHT_TIME" NUMBER(7,2) NULL ,
"DAY_HOUR_MONEY" NUMBER(7,2) NULL ,
"NIGHT_HOUR_MONEY" NUMBER(7,2) NULL ,
"DAY_MAX_MONEY" NUMBER(7,2) NULL ,
"NIGHT_MAX_MONEY" NUMBER(7,2) NULL ,
"EVERYDAY_MIANFEI_TIME" NUMBER(6,2) NULL ,
"LASTTIME" NUMBER(19) NULL ,
"OLD_SHOUFEI_ID" NUMBER(19) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_SHOUFEI"."CARD_TYPE_ID" IS '关联进校证类型id，关联表t_card_type';
COMMENT ON COLUMN "SQTC"."T_SHOUFEI"."PARKING_LOT_ID" IS '关联校区id，关联表T_PARKING_LOT';
COMMENT ON COLUMN "SQTC"."T_SHOUFEI"."DAY_TIME" IS '白天免费停车时长，单位小时';
COMMENT ON COLUMN "SQTC"."T_SHOUFEI"."NIGHT_TIME" IS '晚上免费停车时长，单位小时';
COMMENT ON COLUMN "SQTC"."T_SHOUFEI"."DAY_HOUR_MONEY" IS '白天停车超过免费时限后，每小时的收费标准，单位：元/时';
COMMENT ON COLUMN "SQTC"."T_SHOUFEI"."NIGHT_HOUR_MONEY" IS '夜晚停车超过免费时限后，每小时的收费标准，单位：元/时';
COMMENT ON COLUMN "SQTC"."T_SHOUFEI"."DAY_MAX_MONEY" IS '白天单次停车，封顶收费金额';
COMMENT ON COLUMN "SQTC"."T_SHOUFEI"."NIGHT_MAX_MONEY" IS '夜晚单次停车，封顶收费金额';
COMMENT ON COLUMN "SQTC"."T_SHOUFEI"."EVERYDAY_MIANFEI_TIME" IS '白天每天免费时长，针对饮食配送车辆和快递车辆等，可能对此类车辆每天免费0.5小时，单位小时';
COMMENT ON COLUMN "SQTC"."T_SHOUFEI"."LASTTIME" IS '最后更新时间';
COMMENT ON COLUMN "SQTC"."T_SHOUFEI"."OLD_SHOUFEI_ID" IS '原始进校证系统数据ID';

-- ----------------------------
-- Records of T_SHOUFEI
-- ----------------------------
INSERT INTO "SQTC"."T_SHOUFEI" VALUES ('111490', '111510', '1946', '9999', '9999', '0', '0', '0', '0', '0', '1460973105000', '1');
INSERT INTO "SQTC"."T_SHOUFEI" VALUES ('111491', '111519', '1949', '9999', '9999', '0', '0', '0', '0', '0', '1460973105000', '2');
INSERT INTO "SQTC"."T_SHOUFEI" VALUES ('111492', '111532', '1940', '9999', '0', '0', '20', '0', '20', '0', '1460973105000', '3');

-- ----------------------------
-- Table structure for T_SYNC_LOG
-- ----------------------------
DROP TABLE "SQTC"."T_SYNC_LOG";
CREATE TABLE "SQTC"."T_SYNC_LOG" (
"ID" NUMBER(19) NOT NULL ,
"LOG_TYPE" VARCHAR2(255 BYTE) NULL ,
"LOG_CONTENT" VARCHAR2(2000 BYTE) NULL ,
"CREATE_TIME" TIMESTAMP(6)  NULL ,
"USER_NAME" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_SYNC_LOG"."LOG_TYPE" IS '日志类型';
COMMENT ON COLUMN "SQTC"."T_SYNC_LOG"."LOG_CONTENT" IS '日志内容';
COMMENT ON COLUMN "SQTC"."T_SYNC_LOG"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SQTC"."T_SYNC_LOG"."USER_NAME" IS '操作人名称';

-- ----------------------------
-- Records of T_SYNC_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for T_SYSTEM_LOG
-- ----------------------------
DROP TABLE "SQTC"."T_SYSTEM_LOG";
CREATE TABLE "SQTC"."T_SYSTEM_LOG" (
"ID" NUMBER(19) NOT NULL ,
"LOG_TYPE" VARCHAR2(255 BYTE) NULL ,
"SUBTYPE" VARCHAR2(255 BYTE) NULL ,
"CREATE_TIME" TIMESTAMP(6)  NULL ,
"USER_ID" NUMBER(19) NULL ,
"USER_IP" VARCHAR2(255 BYTE) NULL ,
"LOG_CONTENT" VARCHAR2(512 BYTE) NULL ,
"USER_NAME" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_SYSTEM_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for T_SYSTEM_NOTICE
-- ----------------------------
DROP TABLE "SQTC"."T_SYSTEM_NOTICE";
CREATE TABLE "SQTC"."T_SYSTEM_NOTICE" (
"ID" NUMBER(19) NOT NULL ,
"TITLE" VARCHAR2(255 BYTE) NULL ,
"CONTEXT" VARCHAR2(2000 BYTE) NULL ,
"TO_USER_ID" NUMBER(19) NULL ,
"SEND_TIME" TIMESTAMP(6)  NULL ,
"IS_READ" NUMBER NULL ,
"READ_TIME" TIMESTAMP(6)  NULL ,
"IS_CLOSE" NUMBER NULL ,
"LINK_URL" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_SYSTEM_NOTICE
-- ----------------------------

-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
DROP TABLE "SQTC"."T_USER";
CREATE TABLE "SQTC"."T_USER" (
"ID" NUMBER(19) NOT NULL ,
"LOGIN_NAME" VARCHAR2(64 BYTE) NOT NULL ,
"NAME" VARCHAR2(64 BYTE) NOT NULL ,
"PASSWORD" VARCHAR2(255 BYTE) NOT NULL ,
"SALT" VARCHAR2(64 BYTE) NULL ,
"ROLES" VARCHAR2(255 BYTE) NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NOT NULL ,
"USER_TYPE" NUMBER DEFAULT 1  NULL ,
"CREATE_USER_ID" NUMBER(19) NULL ,
"IS_ENABLED" NUMBER DEFAULT 1  NULL ,
"UPDATE_TIME" TIMESTAMP(6)  NULL ,
"PHOTO_ATTACH_ID" NUMBER(19) NULL ,
"GENDER" VARCHAR2(2 BYTE) NULL ,
"ID_NUMBER" VARCHAR2(64 BYTE) NULL ,
"DATE_OF_BIRTH" VARCHAR2(64 BYTE) NULL ,
"MOBILE_PHONE" VARCHAR2(64 BYTE) NULL ,
"EMAIL" VARCHAR2(64 BYTE) NULL ,
"NICKNAME" VARCHAR2(255 BYTE) NULL ,
"SCHOOL_CARD_TYPE" VARCHAR2(64 BYTE) NULL ,
"EXPIRY_NUM" NUMBER NULL ,
"EXPIRY_DATE" DATE NULL ,
"REGISTRATION_DATE" DATE NULL ,
"LAST_LOGIN_TIME" TIMESTAMP(6)  NULL ,
"CONTACT_ADDRESS" VARCHAR2(255 BYTE) NULL ,
"CONTACT_PHONE_NUMBER" VARCHAR2(255 BYTE) NULL ,
"SOURCE_TYPE" VARCHAR2(64 BYTE) NULL ,
"N_USER_TYPE" VARCHAR2(30 BYTE) NULL ,
"DEPT" VARCHAR2(20 BYTE) NULL ,
"DEPT_NAME" VARCHAR2(180 BYTE) NULL ,
"N_IS_ENABLE" VARCHAR2(60 BYTE) NULL ,
"CARD_TYPE_CODE" VARCHAR2(2 BYTE) NULL ,
"CARD_TYPE_NAME" VARCHAR2(100 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_USER"."LOGIN_NAME" IS '登录名';
COMMENT ON COLUMN "SQTC"."T_USER"."NAME" IS '姓名';
COMMENT ON COLUMN "SQTC"."T_USER"."PASSWORD" IS '密码';
COMMENT ON COLUMN "SQTC"."T_USER"."SALT" IS '性别';
COMMENT ON COLUMN "SQTC"."T_USER"."ROLES" IS '角色';
COMMENT ON COLUMN "SQTC"."T_USER"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "SQTC"."T_USER"."USER_TYPE" IS '用户类型';
COMMENT ON COLUMN "SQTC"."T_USER"."CREATE_USER_ID" IS '创建人';
COMMENT ON COLUMN "SQTC"."T_USER"."IS_ENABLED" IS '状态';
COMMENT ON COLUMN "SQTC"."T_USER"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "SQTC"."T_USER"."PHOTO_ATTACH_ID" IS '头像';
COMMENT ON COLUMN "SQTC"."T_USER"."GENDER" IS '性别';
COMMENT ON COLUMN "SQTC"."T_USER"."N_USER_TYPE" IS 'N_人员类型';
COMMENT ON COLUMN "SQTC"."T_USER"."DEPT" IS '部门编码';
COMMENT ON COLUMN "SQTC"."T_USER"."DEPT_NAME" IS '部门名称';
COMMENT ON COLUMN "SQTC"."T_USER"."N_IS_ENABLE" IS 'N_人员状态';
COMMENT ON COLUMN "SQTC"."T_USER"."CARD_TYPE_CODE" IS '证件类型编码';
COMMENT ON COLUMN "SQTC"."T_USER"."CARD_TYPE_NAME" IS '证件类型';

-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO "SQTC"."T_USER" VALUES ('1', 'admin', '管理员', '111111', '7efbd59d9741d34f', 'admin', TO_TIMESTAMP(' 2016-05-20 11:35:58:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '0', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO "SQTC"."T_USER" VALUES ('410370', 'zhangsan', '张三', '857282', null, null, TO_TIMESTAMP(' 2016-07-22 11:14:49:111000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', null, '1', TO_TIMESTAMP(' 2016-07-30 00:07:01:713000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, '1', 'L8857282', null, '13812345678', null, null, null, null, null, null, null, null, null, null, '教职工', '000182', '土木工程学院', '06', null, '护照');

-- ----------------------------
-- Table structure for T_USER_ROLE
-- ----------------------------
DROP TABLE "SQTC"."T_USER_ROLE";
CREATE TABLE "SQTC"."T_USER_ROLE" (
"USER_ID" NUMBER(19) NOT NULL ,
"ROLE_ID" NUMBER(19) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_USER_ROLE
-- ----------------------------
INSERT INTO "SQTC"."T_USER_ROLE" VALUES ('410370', '1');
INSERT INTO "SQTC"."T_USER_ROLE" VALUES ('1', '1');

-- ----------------------------
-- Table structure for T_USER_TEST
-- ----------------------------
DROP TABLE "SQTC"."T_USER_TEST";
CREATE TABLE "SQTC"."T_USER_TEST" (
"USER_NO" VARCHAR2(20 BYTE) NOT NULL ,
"NAME" VARCHAR2(200 BYTE) NULL ,
"DEPT" VARCHAR2(20 BYTE) NULL ,
"USER_TYPE" VARCHAR2(30 BYTE) NOT NULL ,
"ID_CARD" VARCHAR2(30 BYTE) NULL ,
"MOBILE" VARCHAR2(100 BYTE) NULL ,
"IS_ENABLE" VARCHAR2(60 BYTE) NULL ,
"EVENT_TIME" VARCHAR2(50 BYTE) NULL ,
"CARD_TPYE_CODE" VARCHAR2(2 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_USER_TEST"."USER_NO" IS '职工号/学号';
COMMENT ON COLUMN "SQTC"."T_USER_TEST"."NAME" IS '姓名';
COMMENT ON COLUMN "SQTC"."T_USER_TEST"."DEPT" IS '所在院系部处';
COMMENT ON COLUMN "SQTC"."T_USER_TEST"."USER_TYPE" IS '人员类型';
COMMENT ON COLUMN "SQTC"."T_USER_TEST"."ID_CARD" IS '证件号码';
COMMENT ON COLUMN "SQTC"."T_USER_TEST"."MOBILE" IS '手机';
COMMENT ON COLUMN "SQTC"."T_USER_TEST"."IS_ENABLE" IS '有效状态';
COMMENT ON COLUMN "SQTC"."T_USER_TEST"."EVENT_TIME" IS '数据操作时间戳';
COMMENT ON COLUMN "SQTC"."T_USER_TEST"."CARD_TPYE_CODE" IS '证件类型(教职工和研究生有此信息，教务无)';

-- ----------------------------
-- Records of T_USER_TEST
-- ----------------------------

-- ----------------------------
-- Table structure for T_VIDEO_DEVICE
-- ----------------------------
DROP TABLE "SQTC"."T_VIDEO_DEVICE";
CREATE TABLE "SQTC"."T_VIDEO_DEVICE" (
"ID" NUMBER(19) NOT NULL ,
"DEVICE_NO" VARCHAR2(255 BYTE) NULL ,
"NAME" VARCHAR2(255 BYTE) NULL ,
"DEVICE_IP" VARCHAR2(255 BYTE) NULL ,
"IS_ENABLED" NUMBER DEFAULT 1  NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NULL ,
"ONLINE_URL" VARCHAR2(1024 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_VIDEO_DEVICE
-- ----------------------------
INSERT INTO "SQTC"."T_VIDEO_DEVICE" VALUES ('414491', '1', '1', '1', '1', TO_TIMESTAMP(' 2016-08-01 00:00:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null);

-- ----------------------------
-- Table structure for T_YUDING
-- ----------------------------
DROP TABLE "SQTC"."T_YUDING";
CREATE TABLE "SQTC"."T_YUDING" (
"ID" NUMBER(19) NOT NULL ,
"LOCKED_START_TIME" TIMESTAMP(6)  NULL ,
"YUYUE_TIME" TIMESTAMP(6)  NULL ,
"LOCKED_MINUTES" NUMBER NULL ,
"LOCKED_COST" NUMBER(19,2) NULL ,
"LOCKED_HOUR_COST" NUMBER(19,2) NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NULL ,
"LASTTIME" NUMBER(19) NULL ,
"IS_LOCKED_OK" NUMBER DEFAULT 0  NULL ,
"IS_ENABLED" NUMBER DEFAULT 1  NULL ,
"USER_ID" NUMBER(19) NULL ,
"CAR_NO" VARCHAR2(255 BYTE) NULL ,
"CAR_INFO_ID" NUMBER(19) NULL ,
"PARKING_LOT_AREA_ID" NUMBER(19) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_YUDING"."IS_LOCKED_OK" IS '预约有效';

-- ----------------------------
-- Records of T_YUDING
-- ----------------------------
INSERT INTO "SQTC"."T_YUDING" VALUES ('414250', TO_TIMESTAMP(' 2016-07-30 00:02:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), TO_TIMESTAMP(' 2016-07-30 00:18:00:000000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '16', '1', '1', TO_TIMESTAMP(' 2016-07-30 00:18:13:550000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1469809093550', '0', '1', '410370', '沪1234567', null, '111640');

-- ----------------------------
-- Table structure for T_YUDING_SETTING
-- ----------------------------
DROP TABLE "SQTC"."T_YUDING_SETTING";
CREATE TABLE "SQTC"."T_YUDING_SETTING" (
"ID" NUMBER(19) NOT NULL ,
"MONDAY_START_TIME" VARCHAR2(255 BYTE) NULL ,
"TUESDAY_START_TIME" VARCHAR2(255 BYTE) NULL ,
"WEDNESDAY_START_TIME" VARCHAR2(255 BYTE) NULL ,
"THURSDAY_START_TIME" VARCHAR2(255 BYTE) NULL ,
"FRIDAY_START_TIME" VARCHAR2(255 BYTE) NULL ,
"SATURDAY_START_TIME" VARCHAR2(255 BYTE) NULL ,
"SUNDAY_START_TIME" VARCHAR2(255 BYTE) NULL ,
"MONDAY_END_TIME" VARCHAR2(255 BYTE) NULL ,
"TUESDAY_END_TIME" VARCHAR2(255 BYTE) NULL ,
"WEDNESDAY_END_TIME" VARCHAR2(255 BYTE) NULL ,
"THURSDAY_END_TIME" VARCHAR2(255 BYTE) NULL ,
"FRIDAY_END_TIME" VARCHAR2(255 BYTE) NULL ,
"SATURDAY_END_TIME" VARCHAR2(255 BYTE) NULL ,
"SUNDAY_END_TIME" VARCHAR2(255 BYTE) NULL ,
"START_ADD_MINUTES" NUMBER NULL ,
"END_ADD_MINUTES" NUMBER NULL ,
"LOCKED_MINUTES" NUMBER NULL ,
"LOCKED_COST" NUMBER(19,2) NULL ,
"LOCKED_HOUR_COST" NUMBER(19,2) NULL ,
"CREATE_TIME" TIMESTAMP(6)  DEFAULT sysdate  NULL ,
"LASTTIME" NUMBER(19) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."MONDAY_START_TIME" IS '周一预约开始时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."TUESDAY_START_TIME" IS '周二预约开始时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."WEDNESDAY_START_TIME" IS '周三预约开始时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."THURSDAY_START_TIME" IS '周四预约开始时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."FRIDAY_START_TIME" IS '周五预约开始时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."SATURDAY_START_TIME" IS '周六预约开始时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."SUNDAY_START_TIME" IS '周日预约开始时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."MONDAY_END_TIME" IS '周一预约结束时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."TUESDAY_END_TIME" IS '周二预约结束时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."WEDNESDAY_END_TIME" IS '周三预约结束时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."THURSDAY_END_TIME" IS '周四预约结束时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."FRIDAY_END_TIME" IS '周五预约结束时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."SATURDAY_END_TIME" IS '周六预约结束时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."SUNDAY_END_TIME" IS '周日预约结束时间 00:00';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."START_ADD_MINUTES" IS '预约开始时间偏移';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."END_ADD_MINUTES" IS '预约结束时间偏移';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."LOCKED_MINUTES" IS '提前保留时长';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."LOCKED_COST" IS '保留时长费用';
COMMENT ON COLUMN "SQTC"."T_YUDING_SETTING"."LOCKED_HOUR_COST" IS '预约车位使用计费';

-- ----------------------------
-- Records of T_YUDING_SETTING
-- ----------------------------

-- ----------------------------
-- Sequence structure for HIBERNATE_SEQUENCE
-- ----------------------------
DROP SEQUENCE "SQTC"."HIBERNATE_SEQUENCE";
CREATE SEQUENCE "SQTC"."HIBERNATE_SEQUENCE"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999999999999999
 START WITH 41463
 CACHE 20;

-- ----------------------------
-- Indexes structure for table C_CAMPUS
-- ----------------------------

-- ----------------------------
-- Checks structure for table C_CAMPUS
-- ----------------------------
ALTER TABLE "SQTC"."C_CAMPUS" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."C_CAMPUS" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table C_CAMPUS
-- ----------------------------
ALTER TABLE "SQTC"."C_CAMPUS" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table C_CARD_INFO
-- ----------------------------

-- ----------------------------
-- Checks structure for table C_CARD_INFO
-- ----------------------------
ALTER TABLE "SQTC"."C_CARD_INFO" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."C_CARD_INFO" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."C_CARD_INFO" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table C_CARD_INFO
-- ----------------------------
ALTER TABLE "SQTC"."C_CARD_INFO" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table C_CARD_TEMP
-- ----------------------------

-- ----------------------------
-- Checks structure for table C_CARD_TEMP
-- ----------------------------
ALTER TABLE "SQTC"."C_CARD_TEMP" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."C_CARD_TEMP" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."C_CARD_TEMP" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table C_CARD_TEMP
-- ----------------------------
ALTER TABLE "SQTC"."C_CARD_TEMP" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table C_CARD_TYPE
-- ----------------------------

-- ----------------------------
-- Checks structure for table C_CARD_TYPE
-- ----------------------------
ALTER TABLE "SQTC"."C_CARD_TYPE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."C_CARD_TYPE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table C_CARD_TYPE
-- ----------------------------
ALTER TABLE "SQTC"."C_CARD_TYPE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table C_SHOUFEI
-- ----------------------------

-- ----------------------------
-- Checks structure for table C_SHOUFEI
-- ----------------------------
ALTER TABLE "SQTC"."C_SHOUFEI" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."C_SHOUFEI" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table C_SHOUFEI
-- ----------------------------
ALTER TABLE "SQTC"."C_SHOUFEI" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_ANNOUNCEMENT
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_ANNOUNCEMENT
-- ----------------------------
ALTER TABLE "SQTC"."T_ANNOUNCEMENT" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_ANNOUNCEMENT" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_ANNOUNCEMENT
-- ----------------------------
ALTER TABLE "SQTC"."T_ANNOUNCEMENT" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_ANNOUNCEMENT_SEND
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_ANNOUNCEMENT_SEND
-- ----------------------------
ALTER TABLE "SQTC"."T_ANNOUNCEMENT_SEND" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_ANNOUNCEMENT_SEND" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_ANNOUNCEMENT_SEND
-- ----------------------------
ALTER TABLE "SQTC"."T_ANNOUNCEMENT_SEND" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_ATTACH
-- ----------------------------

-- ----------------------------
-- Uniques structure for table T_ATTACH
-- ----------------------------
ALTER TABLE "SQTC"."T_ATTACH" ADD UNIQUE ("FILE_KEY");

-- ----------------------------
-- Checks structure for table T_ATTACH
-- ----------------------------
ALTER TABLE "SQTC"."T_ATTACH" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_ATTACH" ADD CHECK ("FILE_KEY" IS NOT NULL);
ALTER TABLE "SQTC"."T_ATTACH" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_ATTACH" ADD CHECK ("FILE_KEY" IS NOT NULL);
ALTER TABLE "SQTC"."T_ATTACH" ADD CHECK ("FILE_KEY" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_ATTACH
-- ----------------------------
ALTER TABLE "SQTC"."T_ATTACH" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_AUTHORITY
-- ----------------------------

-- ----------------------------
-- Uniques structure for table T_AUTHORITY
-- ----------------------------
ALTER TABLE "SQTC"."T_AUTHORITY" ADD UNIQUE ("NAME");

-- ----------------------------
-- Checks structure for table T_AUTHORITY
-- ----------------------------
ALTER TABLE "SQTC"."T_AUTHORITY" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_AUTHORITY" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_AUTHORITY" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_AUTHORITY" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_AUTHORITY" ADD CHECK ("NAME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_AUTHORITY
-- ----------------------------
ALTER TABLE "SQTC"."T_AUTHORITY" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_CAMPUS
-- ----------------------------

-- ----------------------------
-- Uniques structure for table T_CAMPUS
-- ----------------------------
ALTER TABLE "SQTC"."T_CAMPUS" ADD UNIQUE ("NAME");

-- ----------------------------
-- Checks structure for table T_CAMPUS
-- ----------------------------
ALTER TABLE "SQTC"."T_CAMPUS" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_CAMPUS" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CAMPUS" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CAMPUS" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_CAMPUS" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CAMPUS" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CAMPUS" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CAMPUS" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_CAMPUS
-- ----------------------------
ALTER TABLE "SQTC"."T_CAMPUS" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_CARD_INFO
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_CARD_INFO
-- ----------------------------
ALTER TABLE "SQTC"."T_CARD_INFO" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_INFO" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_INFO" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_INFO" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_INFO" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_INFO" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_CARD_INFO
-- ----------------------------
ALTER TABLE "SQTC"."T_CARD_INFO" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_CARD_TPYE_TEST
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_CARD_TPYE_TEST
-- ----------------------------
ALTER TABLE "SQTC"."T_CARD_TPYE_TEST" ADD CHECK ("CARD_TPYE_CODE" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_TPYE_TEST" ADD CHECK ("USER_TYPE" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_TPYE_TEST" ADD CHECK ("CARD_TPYE_CODE" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_TPYE_TEST" ADD CHECK ("USER_TYPE" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_TPYE_TEST" ADD CHECK ("CARD_TPYE_CODE" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_TPYE_TEST" ADD CHECK ("USER_TYPE" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_TPYE_TEST" ADD CHECK ("CARD_TPYE_CODE" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_CARD_TPYE_TEST
-- ----------------------------
ALTER TABLE "SQTC"."T_CARD_TPYE_TEST" ADD PRIMARY KEY ("CARD_TPYE_CODE", "USER_TYPE");

-- ----------------------------
-- Indexes structure for table T_CARD_TYPE
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_CARD_TYPE
-- ----------------------------
ALTER TABLE "SQTC"."T_CARD_TYPE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARD_TYPE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_CARD_TYPE
-- ----------------------------
ALTER TABLE "SQTC"."T_CARD_TYPE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_CARINFO
-- ----------------------------

-- ----------------------------
-- Uniques structure for table T_CARINFO
-- ----------------------------
ALTER TABLE "SQTC"."T_CARINFO" ADD UNIQUE ("CAR_NO");

-- ----------------------------
-- Checks structure for table T_CARINFO
-- ----------------------------
ALTER TABLE "SQTC"."T_CARINFO" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARINFO" ADD CHECK ("CAR_NO" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARINFO" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARINFO" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARINFO" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARINFO" ADD CHECK ("CAR_NO" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARINFO" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARINFO" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARINFO" ADD CHECK ("CAR_NO" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARINFO" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_CARINFO" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_CARINFO
-- ----------------------------
ALTER TABLE "SQTC"."T_CARINFO" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_DEPT
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_DEPT
-- ----------------------------
ALTER TABLE "SQTC"."T_DEPT" ADD CHECK ("DWDM" IS NOT NULL);
ALTER TABLE "SQTC"."T_DEPT" ADD CHECK ("USER_TYPE" IS NOT NULL);
ALTER TABLE "SQTC"."T_DEPT" ADD CHECK ("DWDM" IS NOT NULL);
ALTER TABLE "SQTC"."T_DEPT" ADD CHECK ("USER_TYPE" IS NOT NULL);
ALTER TABLE "SQTC"."T_DEPT" ADD CHECK ("DWDM" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_DEPT
-- ----------------------------
ALTER TABLE "SQTC"."T_DEPT" ADD PRIMARY KEY ("DWDM", "USER_TYPE");

-- ----------------------------
-- Indexes structure for table T_DICT
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_DICT
-- ----------------------------
ALTER TABLE "SQTC"."T_DICT" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_DICT" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_DICT
-- ----------------------------
ALTER TABLE "SQTC"."T_DICT" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_LOGIN_LOG
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_LOGIN_LOG
-- ----------------------------
ALTER TABLE "SQTC"."T_LOGIN_LOG" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_LOGIN_LOG" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_LOGIN_LOG
-- ----------------------------
ALTER TABLE "SQTC"."T_LOGIN_LOG" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_MEMBER
-- ----------------------------

-- ----------------------------
-- Uniques structure for table T_MEMBER
-- ----------------------------
ALTER TABLE "SQTC"."T_MEMBER" ADD UNIQUE ("BIRTH_DAY");

-- ----------------------------
-- Checks structure for table T_MEMBER
-- ----------------------------
ALTER TABLE "SQTC"."T_MEMBER" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_MEMBER" ADD CHECK ("BIRTH_DAY" IS NOT NULL);
ALTER TABLE "SQTC"."T_MEMBER" ADD CHECK ("GENDER" IS NOT NULL);
ALTER TABLE "SQTC"."T_MEMBER" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_MEMBER" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_MEMBER" ADD CHECK ("BIRTH_DAY" IS NOT NULL);
ALTER TABLE "SQTC"."T_MEMBER" ADD CHECK ("GENDER" IS NOT NULL);
ALTER TABLE "SQTC"."T_MEMBER" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_MEMBER" ADD CHECK ("BIRTH_DAY" IS NOT NULL);
ALTER TABLE "SQTC"."T_MEMBER" ADD CHECK ("GENDER" IS NOT NULL);
ALTER TABLE "SQTC"."T_MEMBER" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_MEMBER
-- ----------------------------
ALTER TABLE "SQTC"."T_MEMBER" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_PARKING_GARAGE
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_PARKING_GARAGE
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_GARAGE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_GARAGE" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_GARAGE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_GARAGE" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_GARAGE" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_PARKING_GARAGE
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_GARAGE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_PARKING_GARAGE_CARNO_LOG
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_PARKING_GARAGE_CARNO_LOG
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_GARAGE_CARNO_LOG" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_GARAGE_CARNO_LOG" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_GARAGE_CARNO_LOG" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_GARAGE_CARNO_LOG" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_GARAGE_CARNO_LOG" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_PARKING_GARAGE_CARNO_LOG
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_GARAGE_CARNO_LOG" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_PARKING_LOCK
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_PARKING_LOCK
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_LOCK" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_PARKING_LOCK
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_LOCK" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_PARKING_LOCK_EVENT_LOG
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_PARKING_LOCK_EVENT_LOG
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_LOCK_EVENT_LOG" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK_EVENT_LOG" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK_EVENT_LOG" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK_EVENT_LOG" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK_EVENT_LOG" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_PARKING_LOCK_EVENT_LOG
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_LOCK_EVENT_LOG" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_PARKING_LOCK_OPERATION_EVENT
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_PARKING_LOCK_OPERATION_EVENT
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_LOCK_OPERATION_EVENT" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK_OPERATION_EVENT" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK_OPERATION_EVENT" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK_OPERATION_EVENT" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOCK_OPERATION_EVENT" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_PARKING_LOCK_OPERATION_EVENT
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_LOCK_OPERATION_EVENT" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_PARKING_LOT
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_PARKING_LOT
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_LOT" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOT" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOT" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOT" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOT" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_PARKING_LOT
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_LOT" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_PARKING_LOT_AREA
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_PARKING_LOT_AREA
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_LOT_AREA" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOT_AREA" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOT_AREA" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOT_AREA" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_LOT_AREA" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_PARKING_LOT_AREA
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_LOT_AREA" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_PARKING_ORDER
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_PARKING_ORDER
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_ORDER" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_ORDER" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_ORDER" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_ORDER" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_PARKING_ORDER" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_PARKING_ORDER
-- ----------------------------
ALTER TABLE "SQTC"."T_PARKING_ORDER" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_RESOURCE
-- ----------------------------

-- ----------------------------
-- Uniques structure for table T_RESOURCE
-- ----------------------------
ALTER TABLE "SQTC"."T_RESOURCE" ADD UNIQUE ("VAL");

-- ----------------------------
-- Checks structure for table T_RESOURCE
-- ----------------------------
ALTER TABLE "SQTC"."T_RESOURCE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE" ADD CHECK ("POSITION" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE" ADD CHECK ("RESOURCE_TYPE" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE" ADD CHECK ("VAL" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE" ADD CHECK ("POSITION" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE" ADD CHECK ("RESOURCE_TYPE" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE" ADD CHECK ("VAL" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE" ADD CHECK ("POSITION" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE" ADD CHECK ("RESOURCE_TYPE" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE" ADD CHECK ("VAL" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_RESOURCE
-- ----------------------------
ALTER TABLE "SQTC"."T_RESOURCE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table T_RESOURCE_AUTHORITY
-- ----------------------------
ALTER TABLE "SQTC"."T_RESOURCE_AUTHORITY" ADD CHECK ("RESOURCE_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE_AUTHORITY" ADD CHECK ("AUTHORITY_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE_AUTHORITY" ADD CHECK ("RESOURCE_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE_AUTHORITY" ADD CHECK ("AUTHORITY_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE_AUTHORITY" ADD CHECK ("RESOURCE_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_RESOURCE_AUTHORITY" ADD CHECK ("AUTHORITY_ID" IS NOT NULL);

-- ----------------------------
-- Indexes structure for table T_ROLE
-- ----------------------------

-- ----------------------------
-- Uniques structure for table T_ROLE
-- ----------------------------
ALTER TABLE "SQTC"."T_ROLE" ADD UNIQUE ("NAME");

-- ----------------------------
-- Checks structure for table T_ROLE
-- ----------------------------
ALTER TABLE "SQTC"."T_ROLE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_ROLE" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_ROLE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_ROLE" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_ROLE" ADD CHECK ("NAME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_ROLE
-- ----------------------------
ALTER TABLE "SQTC"."T_ROLE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table T_ROLE_AUTHORITY
-- ----------------------------
ALTER TABLE "SQTC"."T_ROLE_AUTHORITY" ADD CHECK ("ROLE_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_ROLE_AUTHORITY" ADD CHECK ("AUTHORITY_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_ROLE_AUTHORITY" ADD CHECK ("ROLE_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_ROLE_AUTHORITY" ADD CHECK ("AUTHORITY_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_ROLE_AUTHORITY" ADD CHECK ("ROLE_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_ROLE_AUTHORITY" ADD CHECK ("AUTHORITY_ID" IS NOT NULL);

-- ----------------------------
-- Indexes structure for table T_SHOUFEI
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_SHOUFEI
-- ----------------------------
ALTER TABLE "SQTC"."T_SHOUFEI" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_SHOUFEI" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_SHOUFEI
-- ----------------------------
ALTER TABLE "SQTC"."T_SHOUFEI" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_SYNC_LOG
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_SYNC_LOG
-- ----------------------------
ALTER TABLE "SQTC"."T_SYNC_LOG" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_SYNC_LOG" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_SYNC_LOG
-- ----------------------------
ALTER TABLE "SQTC"."T_SYNC_LOG" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_SYSTEM_LOG
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_SYSTEM_LOG
-- ----------------------------
ALTER TABLE "SQTC"."T_SYSTEM_LOG" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_SYSTEM_LOG" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_SYSTEM_LOG
-- ----------------------------
ALTER TABLE "SQTC"."T_SYSTEM_LOG" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_SYSTEM_NOTICE
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_SYSTEM_NOTICE
-- ----------------------------
ALTER TABLE "SQTC"."T_SYSTEM_NOTICE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_SYSTEM_NOTICE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_SYSTEM_NOTICE
-- ----------------------------
ALTER TABLE "SQTC"."T_SYSTEM_NOTICE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_USER
-- ----------------------------

-- ----------------------------
-- Uniques structure for table T_USER
-- ----------------------------
ALTER TABLE "SQTC"."T_USER" ADD UNIQUE ("LOGIN_NAME");

-- ----------------------------
-- Checks structure for table T_USER
-- ----------------------------
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("LOGIN_NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("PASSWORD" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("LOGIN_NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("PASSWORD" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("LOGIN_NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("PASSWORD" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER" ADD CHECK ("CREATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_USER
-- ----------------------------
ALTER TABLE "SQTC"."T_USER" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table T_USER_ROLE
-- ----------------------------
ALTER TABLE "SQTC"."T_USER_ROLE" ADD CHECK ("USER_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER_ROLE" ADD CHECK ("ROLE_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER_ROLE" ADD CHECK ("USER_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER_ROLE" ADD CHECK ("ROLE_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER_ROLE" ADD CHECK ("USER_ID" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER_ROLE" ADD CHECK ("ROLE_ID" IS NOT NULL);

-- ----------------------------
-- Checks structure for table T_USER_TEST
-- ----------------------------
ALTER TABLE "SQTC"."T_USER_TEST" ADD CHECK ("USER_NO" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER_TEST" ADD CHECK ("USER_TYPE" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER_TEST" ADD CHECK ("USER_NO" IS NOT NULL);
ALTER TABLE "SQTC"."T_USER_TEST" ADD CHECK ("USER_TYPE" IS NOT NULL);

-- ----------------------------
-- Indexes structure for table T_VIDEO_DEVICE
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_VIDEO_DEVICE
-- ----------------------------
ALTER TABLE "SQTC"."T_VIDEO_DEVICE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_YUDING
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_YUDING
-- ----------------------------
ALTER TABLE "SQTC"."T_YUDING" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_YUDING_SETTING
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_YUDING_SETTING
-- ----------------------------
ALTER TABLE "SQTC"."T_YUDING_SETTING" ADD PRIMARY KEY ("ID");


-- ----------------------------
-- create table T_PASSAGES
-- ----------------------------
CREATE TABLE T_PASSAGES(
ID NUMBER(19) NOT NULL,
PARKINGLOTAREA_ID NUMBER(19) NOT NULL,
PASSAGES_NAME VARCHAR2(255) NOT NULL,
XCOORDINATE VARCHAR2(255) NOT NULL,
YCOORDINATE VARCHAR2(255) NOT NULL,
IS_ENABLED NUMBER NOT NULL,
MARK VARCHAR2(255),
PRIMARY KEY (ID)
);

COMMENT ON COLUMN T_PASSAGES.ID IS '表ID';
COMMENT ON COLUMN T_PASSAGES.PARKINGLOTAREA_ID IS '车库ID';
COMMENT ON COLUMN T_PASSAGES.PASSAGES_NAME IS '出入口名字';
COMMENT ON COLUMN T_PASSAGES.XCOORDINATE IS 'X坐标';
COMMENT ON COLUMN T_PASSAGES.YCOORDINATE IS 'Y坐标';
COMMENT ON COLUMN T_PASSAGES.IS_ENABLED IS '是否启用';
COMMENT ON COLUMN T_PASSAGES.MARK IS '备注';


-- ----------------------------
-- table T_PASSAGES add column
-- ----------------------------
ALTER TABLE T_PARKING_GARAGE ADD XCOORDINATE VARCHAR2(255);
ALTER TABLE T_PARKING_GARAGE ADD YCOORDINATE VARCHAR2(255);


-- ----------------------------
-- create table T_ACCESS_APPLY
-- ----------------------------
CREATE TABLE T_ACCESS_APPLY(
	ID NUMBER(19,0) NOT NULL ,
	CAR_INFO_ID NUMBER(19,0) NOT NULL,
	CAR_NO VARCHAR2(64) NOT NULL,
	ACCESS_STATUS NUMBER NOT NULL,
	CREATE_TIME TIMESTAMP(6) NOT NULL,
	CREATE_USER_ID NUMBER(19,0) NOT NULL,
	APPLY_TIME TIMESTAMP(6) NOT NULL,
	APPLY_USER_ID NUMBER(19,0) NOT NULL,
	APPLY_STATE NUMBER NOT NULL,
	PRIMARY KEY (ID)
);
COMMENT ON COLUMN T_ACCESS_APPLY.ID IS '表ID';
COMMENT ON COLUMN T_ACCESS_APPLY.CAR_INFO_ID IS '车辆ID';
COMMENT ON COLUMN T_ACCESS_APPLY.CAR_NO IS '车牌号';
COMMENT ON COLUMN T_ACCESS_APPLY.ACCESS_STATUS IS '出入状态';
COMMENT ON COLUMN T_ACCESS_APPLY.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN T_ACCESS_APPLY.CREATE_USER_ID IS '创建人';
COMMENT ON COLUMN T_ACCESS_APPLY.APPLY_TIME IS '申请时间';
COMMENT ON COLUMN T_ACCESS_APPLY.APPLY_USER_ID IS '申请人';
COMMENT ON COLUMN T_ACCESS_APPLY.APPLY_STATE IS '申请状态';

