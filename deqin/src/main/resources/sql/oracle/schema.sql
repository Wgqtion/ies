CREATE SEQUENCE hibernate_sequence

drop table t_resource_authority;
drop table t_role_authority;
drop table t_user_role;
drop table t_authority;
drop table t_resource;
drop table t_role;
drop table t_user;
drop table t_system_log;
drop table t_attach;
drop table t_login_log;
drop table t_dict;  
drop table t_announcement_send;
drop table t_announcement;
drop table t_system_notice;
drop table t_member;
drop table t_parking_lot;
drop table t_parking_lot_area;
drop table t_parking_garage;
drop table t_parking_lock;
drop table t_parking_lock_operation_event;
drop table t_parking_lock_event_log;
drop table t_parking_order;
drop table t_campus;
drop table t_shoufei;
drop table t_card_type;
drop table c_campus;
drop table c_card_type;
drop table c_shoufei;
drop table t_shoufei;
drop table c_card_info;
drop table  c_card_temp;
drop table  t_card_info;
drop table  t_sync_log;
drop table t_relation_cardinfo_campus;
drop table  t_relation_cardtype_campus;

create table t_user (
	id number(19,0),
	login_name varchar2(64) not null unique,
	name varchar2(64) not null,
	password  varchar2(255) not null,
	salt  varchar2(64),
	roles  varchar2(255),
	create_time timestamp DEFAULT sysdate not null,
	user_type int DEFAULT 1,
	create_user_id number(19,0) ,	
	is_enabled int DEFAULT 1,
	update_time timestamp,
	photo_attach_id number(19,0),
	gender varchar2(2),
	id_number varchar2(64),
	date_of_birth varchar2(64),
	mobile_phone varchar2(64),
	email varchar2(64),
    nickname varchar2(255),
    school_card_type varchar2(64),
	expiry_num int,
	expiry_date date,	
	registration_date date,
	last_login_time timestamp,
	contact_address varchar2(255),
	contact_phone_number varchar2(255),
	source_type varchar2(64),
	primary key (id)
);

alter table T_USER add (N_USER_TYPE varchar2(30));
alter table T_USER add (CARD_TYPE_CODE varchar2(2));
alter table T_USER add (CARD_TYPE_NAME varchar2(100));
alter table T_USER add (DEPT varchar2(20));
alter table T_USER add (DEPT_NAME varchar2(180));
alter table T_USER add (N_IS_ENABLE varchar2(60));

COMMENT ON COLUMN T_USER.N_USER_TYPE IS 'N_人员类型';
COMMENT ON COLUMN T_USER.CARD_TYPE_CODE IS '证件类型编码';
COMMENT ON COLUMN T_USER.CARD_TYPE_NAME IS '证件类型';
COMMENT ON COLUMN T_USER.DEPT IS '部门编码';
COMMENT ON COLUMN T_USER.DEPT_NAME IS '部门名称';
COMMENT ON COLUMN T_USER.N_IS_ENABLE IS 'N_人员状态'; 

create table t_authority (
        id number(19,0),
        display_name  varchar2(255),
        name  varchar2(255) not null unique,
        primary key (id)
);


  
create table t_resource (
        id number(19,0),
        position number(10,2) not null,
        resource_type  varchar2(255) not null,
        val  varchar2(255) not null unique,
        primary key (id)
) ;


create table t_resource_authority (
        resource_id number(19,0) not null,
        authority_id number(19,0) not null
);


create table t_role (
        id number(19,0),
        name  varchar2(255) not null unique,
        primary key (id)
);

create table t_role_authority (
        role_id number(19,0) not null,
        authority_id number(19,0) not null
);

create table t_user_role (
        user_id number(19,0) not null,
        role_id number(19,0) not null
) ;
    

create table t_system_log (
     id number(19,0),
     log_type  varchar2(255),
  	 subtype  varchar2(255),
     create_time timestamp,
     user_id number(19,0) ,
     user_ip  varchar2(255) ,
     log_content  varchar2(512) ,
     user_name  varchar2(255) ,
     PRIMARY KEY (id)
);

CREATE TABLE t_attach (
  id number(19,0),
  name  varchar2(255),
  create_time timestamp,
  url_path  varchar2(1024),
  file_size number(19,0) ,
  file_type  varchar2(255),
  user_id number(19,0),
  upload_session_id  varchar2(1024),
  file_key  varchar2(255) not null unique,
  PRIMARY KEY (id)
) ;
 
CREATE TABLE t_login_log (
	 id number(19,0),
	 create_time timestamp,
	 user_id number(19,0),
	 user_ip  varchar2(255),
	 log_content  varchar2(512),
	 user_name  varchar2(255),
	 SESSION_ID varchar2(255),
	 PRIMARY KEY (id)
);
 

CREATE TABLE t_route_path_log(
	 id number(19,0),
	 create_time timestamp,
	 user_id number(19,0),
	 subtype  varchar2(255),
	 log_content  varchar2(512),	
	 PRIMARY KEY (id)
);

 
CREATE TABLE t_dict (                                          
   id number(19,0),               
   type_name  varchar2(255),         
   name  varchar2(255),         
   code  varchar2(255),         
   description  varchar2(255),  
   sort int,                                      
   PRIMARY KEY  (id)                                               
);
 
 
CREATE TABLE t_announcement(
	id number(19,0),
	title varchar2(255),
	context varchar2(2000),
	create_user_id number(19,0),
	start_date timestamp, 
	create_time timestamp,
	state int,
	to_all_user int,
	PRIMARY KEY  (id)  
 );
 
 CREATE TABLE t_announcement_send(
	id number(19,0), 
	announcement_id number(19,0),
	send_user_id number(19,0),
	send_date timestamp,  
	is_read int,
	read_time timestamp,
	is_close int,
	PRIMARY KEY (id)  
 );
 
  
CREATE TABLE t_system_notice(
	id number(19,0), 
	title varchar2(255),
	context varchar2(2000),
	to_user_id number(19,0),
	send_time timestamp,
	is_read int,
	read_time timestamp,
	is_close int,
	link_url varchar2(255),
	PRIMARY KEY (id)
 );
 

--会员信息
create table t_member (
	id number(19,0),
	nickname varchar2(255),
	birth_day varchar2(64) not null unique,
	gender varchar2(64) not null,
	school_card_type varchar2(64),
	expiry_num int,
	expiry_date date,
	create_time timestamp DEFAULT sysdate not null, 
	registration_date date,
	last_login_time timestamp,
	contact_address varchar2(255),
	contact_phone_number varchar2(255),
	source varchar2(64),
	primary key (id)
);

--校区停车场 
create table t_parking_lot(
	id number(19,0), 
	name varchar2(255),  
	create_time timestamp DEFAULT sysdate not null, 
	is_enabled int DEFAULT 1,
	baidu_latitude_lng varchar2(255),
	baidu_latitude_lat varchar2(255),
	address varchar2(255),
	description varchar2(4000),
	photo_attach_id number(19,0),
	car_number int,
	code varchar2(255), 
	can_get_card int ,
	lasttime number(19,0),
	campus_id number(19,0),
	primary key (id)
);

COMMENT ON COLUMN t_parking_lot.NAME IS '校区停车场名称';
COMMENT ON COLUMN T_PARKING_LOT.can_get_card IS '是否可以领进校证';
COMMENT ON COLUMN T_PARKING_LOT.lasttime IS '最后更新时间';
COMMENT ON COLUMN T_PARKING_LOT.campus_id IS '进校证数据库表中的原始ID';




--停车区域
create table t_parking_lot_area(
	id number(19,0), 
	name varchar2(255),  
	create_time timestamp DEFAULT sysdate not null, 
	is_enabled int DEFAULT 1,
	baidu_latitude_lng varchar2(255) DEFAULT '',
	baidu_latitude_lat varchar2(255) DEFAULT '',	
	description varchar2(4000),
	photo_attach_id number(19,0),
	car_number int,
    parking_lot_id number(19,0),
    code varchar2(255) ,
    pid number(19,0), 
    full_index_name varchar2(255),
	primary key (id)
);

--停车位
create table t_parking_garage(
	id number(19,0), 
	code varchar2(255),
	name varchar2(255),  
	ip_address varchar2(255),
	create_time timestamp DEFAULT sysdate not null, 
	is_enabled int DEFAULT 1,	
    parking_lot_area_id number(19,0),    
    garage_type int,
    description varchar2(255),
    car_no varchar2(50),
    intime timestamp,  
    is_online int default 0,
	primary key (id)
);

--地锁信息
create table t_parking_lock(
	id number(19,0), 
	lock_num varchar2(255),
	device_num varchar2(255),
	ip_address varchar2(255),
	create_time timestamp DEFAULT sysdate not null, 
	is_enabled int DEFAULT 1,	
    parking_garage_id number(19,0),    
    is_car_on int,
    is_car_on_time timestamp, --是否有车状态最后刷新时间
    is_ok int,
    is_ok_time timestamp,    --是否正常状态最后刷新时间
    is_online int,
    is_online_time timestamp, --是否在线状态最后刷新时间
    is_open int,
    is_forever_open_close int, --0、无 1、永久开 2、永久关
  
    description varchar2(1024),
	primary key (id)
);



--地锁下发操作日志
create table t_parking_lock_operation_event(
   id number(19,0),
   event_type  int, --事件类型  1.开锁 2.关锁 3.永久关 4、永久开 5.取消永久状态
   source_type int,  --1.服务器 2.手机端 
   parking_lock_id  number(19,0),
   reported_time timestamp,  --发生时间
   create_time timestamp DEFAULT sysdate not null, 
   message varchar2(512), --事件说明
   lock_num varchar2(255),
   device_num varchar2(255),
   ip_address varchar2(255),
   result_type  varchar2(255), --返回结果
   user_id  number(19,0),  --操作人
   primary key (id)
);

--地锁上报事件日志
create table t_parking_lock_event_log(
   id number(19,0),
   event_type  int, --事件类型 
   source_type int, --事件发生源 3.地锁
   parking_lock_id  number(19,0),
   reported_time timestamp,
   create_time timestamp DEFAULT sysdate not null, 
   message varchar2(512), --事件说明
   lock_num varchar2(255),
   device_num varchar2(255),
   ip_address varchar2(255),
  
   primary key (id)
);

--车辆信息
create table t_carinfo (
	id number(19,0),
	car_no varchar2(64) not null unique,
	car_type varchar2(64),
	name varchar2(64) not null,  
	create_time timestamp DEFAULT sysdate not null, 
	is_enabled int DEFAULT 1,
	remark varchar2(1024),
	primary key (id)
);


--停车单
create table t_parking_order(
	id number(19,0),
	in_camera_ip varchar2(64), --进校摄像头ID
	in_plate_no varchar2(64),  --车牌号
	in_time timestamp,         --进校时间
	in_charge_user varchar2(64),  --进校收费员 (预留)
	in_charge_starttime varchar2(64), --进校收费员上班时间 (预留)
	in_pic_name varchar2(255),   --进校图片
	in_findStr varchar2(255),    --备用字段
	in_school_door_name varchar2(255), --进校校门
	in_source_ip varchar2(64),         --进校接口调用源IP
	in_source_name varchar2(64),        --进校接口调用系统名
	create_time timestamp DEFAULT sysdate not null, --创建时间（用于记录进校系统事件）
	update_time timestamp, --修改时间（用于记录出校系统事件）
	is_enabled int DEFAULT 1, --是否有效记录
	car_gategory int,  --收费类型
	car_type int,      --车辆类型
	out_time timestamp, --出校时间
	staytime number(19,2), --停车时长(单位分钟)
	out_school_door varchar2(255), --出校校门
	parking_time_type int,   --费率类型
	amounts_receivable number(19,2),  --应收金额
	amounts_paid number(19,2),   --实收金额
	is_amount_success int,       --门禁确认支付成功
	fail_reason varchar2(255),   --收费失败原因
	out_pic_name varchar2(255),  --出校图片
	operate_flag  int,   --门禁上报操作类型
	amount_type   int, --付款方式 现金 手机
	amount_online_ok int,     --是否已经在线付款
	amount_time timestamp, --在线付款时间
	order_number varchar2(255),  --停车单客户可见编号
	payment_order_number varchar2(255),  --付款单成功编号
	primary key (id)
);

--校门表
create table t_campus (
	id number(19,0),
	name varchar2(255) not null unique,
	parking_lot_id number(19,0), 
	create_time timestamp DEFAULT sysdate not null, 
	lasttime number(19,0),
	is_enabled int DEFAULT 1,
	remark varchar2(1024),
	campus_id int,
	primary key (id)
);

COMMENT ON COLUMN t_campus.name IS '校门名称';
COMMENT ON COLUMN t_campus.pid IS '校区ID';
COMMENT ON COLUMN t_campus.lasttime IS '最后更新时间';
COMMENT ON COLUMN t_campus.campus_id IS '进校证数据库表中的原始ID';




CREATE TABLE t_shoufei (
   id number(19,0),
   card_type_id number(19,0),
   parking_lot_id number(19,0),
   day_time number(19,2),
   night_time number(19,2),
   day_hour_money number(19,2),
   night_hour_money  number(19,2),
   day_max_money number(19,2),
   night_max_money number(19,2),
   everyday_mianfei_time number(19,2),
   lasttime number(19,0),
   c_shoufei_id number(19,0),
   primary key (id)
 );
 
COMMENT ON COLUMN t_shoufei.card_type_id IS '进校证类型';
COMMENT ON COLUMN t_shoufei.parking_lot_id IS '校区ID';
COMMENT ON COLUMN t_shoufei.day_time IS '白天免费停车时长，单位小时';
COMMENT ON COLUMN t_shoufei.night_time IS '晚上免费停车时长，单位小时';
COMMENT ON COLUMN t_shoufei.day_hour_money IS '白天停车超过免费时限后，每小时的收费标准，单位：元/时';
COMMENT ON COLUMN t_shoufei.night_hour_money  IS '夜晚停车超过免费时限后，每小时的收费标准，单位：元/时';
   COMMENT ON COLUMN t_shoufei.day_max_money IS '白天单次停车，封顶收费金额';
   COMMENT ON COLUMN t_shoufei.night_max_money IS '夜晚单次停车，封顶收费金额';
   COMMENT ON COLUMN t_shoufei.everyday_mianfei_time IS '白天每天免费时长，针对饮食配送车辆和快递车辆等，可能对此类车辆每天免费0.5小时，单位小时';

COMMENT ON COLUMN t_shoufei.lasttime IS '最后更新时间';
COMMENT ON COLUMN t_shoufei.c_shoufei_id IS '进校证数据库表中c_shoufei的原始ID';


CREATE TABLE t_card_type (
  id number(19,0),
  card_type varchar2(1), 
  cname varchar2(255),
  card_number varchar2(1),
  cmod varchar2(1),
  need_rel varchar2(1),
  d_car varchar2(1),
  can_search varchar2(1),
  day_stop_mf int,
  night_stop_mf int,
  cyear int,
  fee number(6,2),
  xm_ids  varchar2(1024),
  buban number(6,2),
  biangeng number(6,2),
  lasttime number(19,0),
  status varchar2(1),
  old_card_type_id number(19,0),
   primary key (id)
   )  ;
 COMMENT ON COLUMN t_card_type.card_type IS '1:年证,2:月证';
COMMENT ON COLUMN t_card_type.cname IS '进校证类型名称';
 COMMENT ON COLUMN t_card_type.card_number IS '进校证首字母编号';
COMMENT ON COLUMN t_card_type.cmod IS '进校证人群1校内，2校外';
 COMMENT ON COLUMN t_card_type.need_rel IS '是否需要关系证明 0不需要 1需要';
 COMMENT ON COLUMN t_card_type.d_car IS '1:默认,0:非默认 忽略字段';
 COMMENT ON COLUMN t_card_type.can_search IS '1:可以，0不可以 忽略字段';
 COMMENT ON COLUMN t_card_type.day_stop_mf IS '白天免费停车小时，作废字段';
 COMMENT ON COLUMN t_card_type.night_stop_mf IS '晚上免费停车小时，作废字段';
 COMMENT ON COLUMN t_card_type.cyear IS '进校证类型的适用年份';
 COMMENT ON COLUMN t_card_type.fee IS '年费收费标准(单位元)';
 COMMENT ON COLUMN t_card_type.xm_ids IS '允许通行校门id，与campus表关联';
 COMMENT ON COLUMN t_card_type.buban IS '补办进校证时收费标准倍数';
 COMMENT ON COLUMN t_card_type.biangeng IS '变更进校证时的收费标准倍数';
 COMMENT ON COLUMN t_card_type.lasttime IS '最后更新时间';
 COMMENT ON COLUMN t_card_type.status IS '是否为默认类型  0：不是   1：是';
 COMMENT ON COLUMN t_card_type.old_card_type_id IS '原进校证数据库ID';
 
 
--原样获取进校证原始数据 campus
CREATE TABLE c_campus (
  id number(19,0),
  c_name varchar2(255),
  can_get_card int,
  pid int,
  lasttime number(19,0),
  PRIMARY KEY (id)
); 
    


--原始进校证数据库c_card_type数据
CREATE TABLE c_card_type (
  id number(19,0),
  ctype varchar2(1) ,
  cname varchar2(100),
  cnumber varchar2(1),
  cmod varchar2(1) ,
  need_rel varchar2(1)   ,
  d_car varchar2(1)   ,
  can_search varchar2(1),
  day_stop_mf int   ,
  night_stop_mf int   ,
  cyear int   ,
  fee number(8,2) ,
  xm_ids varchar2(1024) ,
  buban number(3,2)  ,
  biangeng number(3,2)   ,
  lasttime number(19,0) ,
  status varchar2(1) ,
  PRIMARY KEY (id)
) ;
 COMMENT ON COLUMN c_card_type.ctype IS '是否为默认类型  0：不是   1：是';
 COMMENT ON COLUMN c_card_type.cname IS '进校证类型名称';  
 COMMENT ON COLUMN c_card_type.cnumber IS '进校证首字母编号';
 COMMENT ON COLUMN c_card_type.cmod IS '1校内，2校外';
COMMENT  ON COLUMN c_card_type.need_rel IS '是否需要关系证明';
COMMENT ON COLUMN c_card_type.d_car IS '1:默认,0:非默认';
  COMMENT ON COLUMN c_card_type.can_search IS '1:可以，0不可以';
  COMMENT ON COLUMN c_card_type.day_stop_mf IS '白天免费停车小时，作废字段';
  COMMENT ON COLUMN c_card_type.night_stop_mf IS '晚上免费停车小时，作废字段';
  COMMENT ON COLUMN c_card_type.cyear IS '进校证类型的适用年份';
  COMMENT ON COLUMN c_card_type.fee IS '年费收费标准';
  COMMENT ON COLUMN c_card_type.xm_ids IS '允许通行校门id，与campus表关联';
   COMMENT ON COLUMN c_card_type.buban IS '补办进校证时收费标准倍数';
   COMMENT ON COLUMN c_card_type.biangeng IS '变更进校证时的收费标准倍数';
   COMMENT ON COLUMN c_card_type.lasttime IS '最后更新时间';
      COMMENT ON COLUMN c_card_type.status IS '是否为默认类型  0：不是   1：是';

--原始进校证数据库c_shoufei数据
CREATE TABLE c_shoufei (
  id number(19,0),
  type_id number(19,0) ,
  cid number(19,0),
  day_time number(7,2)  ,
  night_time number(7,2),
  day_hour_money number(7,2),
  night_hour_money number(7,2),
  day_max_money number(7,2),
  night_max_money number(7,2),
  everyday_mianfei_time number(6,2),
  lasttime number(19,0),
  PRIMARY KEY (id) 
) ;    
  COMMENT ON COLUMN c_shoufei.type_id IS '关联进校证类型id，关联表c_card_type';      
  COMMENT ON COLUMN c_shoufei.cid IS '关联校区id，关联表campus'  ;   
  COMMENT ON COLUMN c_shoufei.day_time IS '白天免费停车时长，单位小时';
  COMMENT ON COLUMN c_shoufei.night_time IS '晚上免费停车时长，单位小时';
  COMMENT ON COLUMN c_shoufei.day_hour_money IS '白天停车超过免费时限后，每小时的收费标准，单位：元/时';
  COMMENT ON COLUMN c_shoufei.night_hour_money IS '夜晚停车超过免费时限后，每小时的收费标准，单位：元/时' ;
  COMMENT ON COLUMN c_shoufei.day_max_money IS '白天单次停车，封顶收费金额';
  COMMENT ON COLUMN c_shoufei.night_max_money IS '夜晚单次停车，封顶收费金额'  ; 
  COMMENT ON COLUMN c_shoufei.everyday_mianfei_time IS '白天每天免费时长，针对饮食配送车辆和快递车辆等，可能对此类车辆每天免费0.5小时，单位小时'  ;  
  COMMENT ON COLUMN c_shoufei.lasttime IS '最后更新时间';
  

--本系统收费规则管理t_shoufei数据
CREATE TABLE t_shoufei (
  id number(19,0),
  card_type_id number(19,0) ,
  parking_lot_id number(19,0),
  day_time number(7,2)  ,
  night_time number(7,2),
  day_hour_money number(7,2),
  night_hour_money number(7,2),
  day_max_money number(7,2),
  night_max_money number(7,2),
  everyday_mianfei_time number(6,2),
  lasttime number(19,0),
  old_shoufei_id number(19,0),
  PRIMARY KEY (id) 
);    
  COMMENT ON COLUMN t_shoufei.card_type_id IS '关联进校证类型id，关联表t_card_type';      
  COMMENT ON COLUMN t_shoufei.parking_lot_id IS '关联校区id，关联表T_PARKING_LOT'  ;   
  COMMENT ON COLUMN t_shoufei.day_time IS '白天免费停车时长，单位小时';
  COMMENT ON COLUMN t_shoufei.night_time IS '晚上免费停车时长，单位小时';
  COMMENT ON COLUMN t_shoufei.day_hour_money IS '白天停车超过免费时限后，每小时的收费标准，单位：元/时';
  COMMENT ON COLUMN t_shoufei.night_hour_money IS '夜晚停车超过免费时限后，每小时的收费标准，单位：元/时' ;
  COMMENT ON COLUMN t_shoufei.day_max_money IS '白天单次停车，封顶收费金额';
  COMMENT ON COLUMN t_shoufei.night_max_money IS '夜晚单次停车，封顶收费金额'  ; 
  COMMENT ON COLUMN t_shoufei.everyday_mianfei_time IS '白天每天免费时长，针对饮食配送车辆和快递车辆等，可能对此类车辆每天免费0.5小时，单位小时'  ;  
  COMMENT ON COLUMN t_shoufei.lasttime IS '最后更新时间';  
  COMMENT ON COLUMN t_shoufei.old_shoufei_id IS '原始进校证系统数据ID';  
  

  
--原始进校证数据库card_info数据
CREATE TABLE  c_card_info  (
 id  number(19) not null ,
 card_no  varchar2(64)  ,
 card_type_id  number(19)  ,
 status  varchar2(2)  ,
 owner  varchar2(64)  ,
 car_no  varchar2(64)  ,
 expire_date  varchar2(4)  ,
 y_name  varchar2(4)  ,
 xm_ids  varchar2(1024)  ,
 lasttime  number(19,0)  ,
 type_name  varchar2(64)  ,
 type_id  number(19)  ,
 user_no  varchar2(64)  ,
 user_name  varchar2(64)  ,
 front_prints  int  ,
 bg_prints  int  ,
 user_type_id  number(19)  ,
 is_pay  varchar2(2)  ,
 car_type  varchar2(2)  ,
 day_stop_mf  int  ,
 night_stop_mf  int  ,
 use_status  varchar2(2)  ,
 primary key (id)
);
COMMENT ON COLUMN  C_CARD_INFO.CARD_NO  IS '卡号';
COMMENT ON COLUMN  C_CARD_INFO.CARD_TYPE_ID  IS '卡类型';
COMMENT ON COLUMN  C_CARD_INFO.STATUS  IS '审核状态：1：未审；2：已审；3:退回';
COMMENT ON COLUMN  C_CARD_INFO.OWNER  IS '车主姓名';
COMMENT ON COLUMN  C_CARD_INFO.CAR_NO  IS '车牌';
COMMENT ON COLUMN  C_CARD_INFO.EXPIRE_DATE  IS '进校证有效时间';
COMMENT ON COLUMN  C_CARD_INFO.XM_IDS  IS '可进出校门id';
COMMENT ON COLUMN  C_CARD_INFO.LASTTIME  IS '最后更新时间';
COMMENT ON COLUMN  C_CARD_INFO.TYPE_NAME  IS '车类型说明';
COMMENT ON COLUMN  C_CARD_INFO.TYPE_ID  IS '车类型关联c_card_type';
COMMENT ON COLUMN  C_CARD_INFO.USER_NO  IS '申请人工号';
COMMENT ON COLUMN  C_CARD_INFO.USER_NAME  IS '申请人姓名';
COMMENT ON COLUMN  C_CARD_INFO.FRONT_PRINTS  IS '进校证正面打印次数';
COMMENT ON COLUMN  C_CARD_INFO.BG_PRINTS  IS '进校证正面打印次数';
COMMENT ON COLUMN  C_CARD_INFO.USER_TYPE_ID  IS '申请人类型';
COMMENT ON COLUMN  C_CARD_INFO.IS_PAY  IS '支付类型';
COMMENT ON COLUMN  C_CARD_INFO.CAR_TYPE  IS '车辆类型：1：大型，2：中型，3：小型';
COMMENT ON COLUMN  C_CARD_INFO.DAY_STOP_MF  IS '白天免费时长';
COMMENT ON COLUMN  C_CARD_INFO.NIGHT_STOP_MF  IS '夜晚免费时长';
COMMENT ON COLUMN  C_CARD_INFO.USE_STATUS  IS '进校证是否有效';



--原始进校证数据库c_card_temp数据
CREATE TABLE  c_card_temp(
 id  number(19) not null ,
 year  number(4)  ,
 month  number(2)   ,
 dept_no  varchar2(64)  ,
 dept_name  varchar2(64)  ,
 owner  varchar2(64)  ,
 mobile  varchar2(64)  ,
 car_no  varchar2(64)  ,
 use_date  varchar2(10)  ,
 note  varchar2(1000)  ,
 user_id  number(19)  ,
 status  int  ,
 car_type  int  ,
 temp_id  number(19)  ,
 xm_ids  varchar2(1024),
 primary key (id)
);
COMMENT ON COLUMN  C_CARD_TEMP.YEAR  IS '年份';
COMMENT ON COLUMN  C_CARD_TEMP.MONTH  IS '月份（作废字段）';
COMMENT ON COLUMN  C_CARD_TEMP.DEPT_NO  IS '办证单位编号';
COMMENT ON COLUMN  C_CARD_TEMP.DEPT_NAME  IS '办证单位名称';
COMMENT ON COLUMN  C_CARD_TEMP.OWNER  IS '申请人姓名';
COMMENT ON COLUMN  C_CARD_TEMP.MOBILE  IS '申请人手机';
COMMENT ON COLUMN  C_CARD_TEMP.CAR_NO  IS '车牌';
COMMENT ON COLUMN  C_CARD_TEMP.USE_DATE  IS '有效时间';
COMMENT ON COLUMN  C_CARD_TEMP.NOTE  IS '备注';
COMMENT ON COLUMN  C_CARD_TEMP.USER_ID  IS '添加人id';
COMMENT ON COLUMN  C_CARD_TEMP.STATUS  IS '审核状态：1：未审；2：已审；3:退回';
COMMENT ON COLUMN  C_CARD_TEMP.CAR_TYPE  IS '车辆类型：1：大型，2：中型，3：小型';
COMMENT ON COLUMN  C_CARD_TEMP.TEMP_ID  IS '临时部门中的关联id';

--本数据进校证数据


CREATE TABLE  t_card_info  (
 id  number(19) not null ,
 card_no  varchar2(64)  ,
 card_type  int  ,
 card_type_id  number(19)  ,
 status  int  ,
 owner  varchar2(64)  ,
 car_no  varchar2(64)  ,
 expire_date  timestamp  ,
 y_name  int  ,
 xm_ids  varchar2(1024)  ,
 lasttime  number(19,0)  ,
 type_name  varchar2(64)  ,
 type_id  number(19)   ,
 user_no  varchar2(64)  ,
 user_name  varchar2(64)  ,
 front_prints int  ,
 bg_prints  int  ,
 user_type_id  number(19)  ,
 is_pay  varchar2(2)  ,
 car_type  varchar2(2)  ,
 day_stop_mf  number(10,1)  ,
 night_stop_mf  number(10,1)  ,
 use_status  varchar2(2)  ,
 dept_no  varchar2(64)  ,
 dept_name  varchar2(64)  ,
 mobile  varchar2(64)  ,
 note  varchar2(1024)  ,
 user_id  number(19)  ,
 temp_id  number(19)  ,
 old_card_info_id  number(19)  ,
 create_time  timestamp  not null,
 primary key (id)
);
COMMENT ON COLUMN  T_CARD_INFO.CARD_NO  IS '卡号';
COMMENT ON COLUMN  T_CARD_INFO.CARD_TYPE  IS '1.进校证  2.临时进校证 3.evcard 4.vip自建';
COMMENT ON COLUMN  T_CARD_INFO.CARD_TYPE_ID  IS '卡类型';
COMMENT ON COLUMN  T_CARD_INFO.STATUS  IS '审核状态：1：未审；2：已审；3:退回';
COMMENT ON COLUMN  T_CARD_INFO.OWNER  IS '车主姓名';
COMMENT ON COLUMN  T_CARD_INFO.CAR_NO  IS '车牌';
COMMENT ON COLUMN  T_CARD_INFO.EXPIRE_DATE  IS '进校证有效时间';
COMMENT ON COLUMN  T_CARD_INFO.Y_NAME  IS '申办年度';
COMMENT ON COLUMN  T_CARD_INFO.XM_IDS  IS '可进出校门id';
COMMENT ON COLUMN  T_CARD_INFO.LASTTIME  IS '最后更新时间';
COMMENT ON COLUMN  T_CARD_INFO.TYPE_NAME  IS '车类型说明';
COMMENT ON COLUMN  T_CARD_INFO.TYPE_ID  IS '车类型关联t_card_type';
COMMENT ON COLUMN  T_CARD_INFO.USER_NO  IS '申请人工号';
COMMENT ON COLUMN  T_CARD_INFO.USER_NAME  IS '申请人姓名 , 临时进校证 申请人姓名';
COMMENT ON COLUMN  T_CARD_INFO.FRONT_PRINTS  IS '进校证正面打印次数';
COMMENT ON COLUMN  T_CARD_INFO.BG_PRINTS  IS '进校证正面打印次数';
COMMENT ON COLUMN  T_CARD_INFO.USER_TYPE_ID  IS '申请人类型';
COMMENT ON COLUMN  T_CARD_INFO.IS_PAY  IS '支付类型';
COMMENT ON COLUMN  T_CARD_INFO.CAR_TYPE  IS '车辆类型：1：大型，2：中型，3：小型';
COMMENT ON COLUMN  T_CARD_INFO.DAY_STOP_MF  IS '白天免费时长';
COMMENT ON COLUMN  T_CARD_INFO.NIGHT_STOP_MF  IS '夜晚免费时长';
COMMENT ON COLUMN  T_CARD_INFO.USE_STATUS  IS '进校证是否有效';
COMMENT ON COLUMN  T_CARD_INFO.DEPT_NO  IS '办证单位编号';
COMMENT ON COLUMN  T_CARD_INFO.DEPT_NAME  IS '办证单位名称';
COMMENT ON COLUMN  T_CARD_INFO.MOBILE  IS '申请人电话';
COMMENT ON COLUMN  T_CARD_INFO.NOTE  IS '备注';
COMMENT ON COLUMN  T_CARD_INFO.USER_ID  IS '添加人id';
COMMENT ON COLUMN  T_CARD_INFO.TEMP_ID  IS '临时部门中的关联id';
COMMENT ON COLUMN  T_CARD_INFO.OLD_CARD_INFO_ID  IS '原始进校证的ID,根据类型不同关联不同表';
COMMENT ON COLUMN  T_CARD_INFO.CREATE_TIME  IS '创建时间';


--停车位上的停车数据更新日志
CREATE TABLE t_parking_garage_carno_log(
   id  number(19,0),
   parking_name varchar2(255),
   camera_ip  varchar2(255),
   status int,
   car_no varchar2(50),
   intime timestamp,  
   create_time timestamp DEFAULT sysdate not null,
   PRIMARY KEY (id)
);
 COMMENT ON COLUMN t_parking_garage_carno_log.parking_name IS '车位编号';      
 COMMENT ON COLUMN t_parking_garage_carno_log.camera_ip IS '相机IP';      
 COMMENT ON COLUMN t_parking_garage_carno_log.status IS '车位状态';      
 COMMENT ON COLUMN t_parking_garage_carno_log.car_no IS '占位车牌号';      
 COMMENT ON COLUMN t_parking_garage_carno_log.intime IS '上报时间';      
 COMMENT ON COLUMN t_parking_garage_carno_log.create_time IS '创建时间';      
 
 --停车证与校门关系表
CREATE TABLE t_relation_cardinfo_campus(
   cardinfo_id  number(19,0),
   campus_id number(19,0)
);

 --进校证类型与校门关系表
CREATE TABLE t_relation_cardtype_campus(
   cardtype_id  number(19,0),
   campus_id number(19,0)
);

CREATE TABLE  t_sync_log  (
	 id number(19,0),
     log_type  varchar2(255),
     log_content  varchar2(512) ,
     create_time timestamp,
     user_name  varchar2(255) ,
     PRIMARY KEY (id)
);
COMMENT ON COLUMN t_sync_log.log_type IS '日志类型';
COMMENT ON COLUMN t_sync_log.log_content IS '日志内容';   
COMMENT ON COLUMN t_sync_log.create_time IS '创建时间';   
COMMENT ON COLUMN t_sync_log.user_name IS '操作人名称';   


CREATE TABLE  t_yuding_setting  (
	id number(19,0),
    monday_start_time varchar2(255),
	tuesday_start_time  varchar2(255),
	wednesday_start_time  varchar2(255),
	thursday_start_time  varchar2(255),
	friday_start_time  varchar2(255),
	saturday_start_time  varchar2(255),
	sunday_start_time  varchar2(255),
	monday_end_time varchar2(255),
	tuesday_end_time  varchar2(255),
	wednesday_end_time  varchar2(255),
	thursday_end_time  varchar2(255),
	friday_end_time  varchar2(255),
	saturday_end_time  varchar2(255),
	sunday_end_time  varchar2(255),
	start_add_minutes  int,
	end_add_minutes  int,
	locked_minutes int,
	locked_cost number(19,2),
	locked_hour_cost  number(19,2),    
    create_time timestamp DEFAULT sysdate,
    lasttime number(19,0),
    
    PRIMARY KEY (id)
);
COMMENT ON COLUMN t_yuding_setting.monday_start_time  IS '周一预约开始时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.tuesday_start_time   IS '周二预约开始时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.wednesday_start_time   IS '周三预约开始时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.thursday_start_time   IS '周四预约开始时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.friday_start_time   IS '周五预约开始时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.saturday_start_time   IS '周六预约开始时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.sunday_start_time   IS '周日预约开始时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.monday_end_time  IS '周一预约结束时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.tuesday_end_time   IS '周二预约结束时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.wednesday_end_time   IS '周三预约结束时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.thursday_end_time   IS '周四预约结束时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.friday_end_time   IS '周五预约结束时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.saturday_end_time   IS '周六预约结束时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.sunday_end_time   IS '周日预约结束时间 00:00';
	COMMENT ON COLUMN t_yuding_setting.start_add_minutes   IS '预约开始时间偏移';
	COMMENT ON COLUMN t_yuding_setting.end_add_minutes   IS '预约结束时间偏移';
	COMMENT ON COLUMN t_yuding_setting.locked_minutes   IS '提前保留时长';
	COMMENT ON COLUMN t_yuding_setting.locked_cost   IS '保留时长费用';
	COMMENT ON COLUMN t_yuding_setting.locked_hour_cost IS '预约车位使用计费';
 

CREATE TABLE  t_yuding (
	id number(19,0),     
	locked_start_time timestamp,
	yuyue_time timestamp,
	locked_minutes int,
	locked_cost number(19,2),
	locked_hour_cost  number(19,2),  
    create_time timestamp  DEFAULT sysdate , 
    lasttime number(19,0),
    is_locked_ok int DEFAULT 0,
    is_enabled int DEFAULT 1,
    user_id number(19,0), 
    car_no varchar2(255),
    car_info_id number(19,0),
    parking_lot_area_id number(19,0),
    PRIMARY KEY (id)
);
COMMENT ON COLUMN t_yuding.is_locked_ok   IS '预留车位成功';
COMMENT ON COLUMN t_yuding.is_locked_ok   IS '预约有效';
  



CREATE TABLE  t_video_device (
	id number(19,0),     
	device_no varchar2(255),
	name varchar2(255),
	device_ip varchar2(255),
    is_enabled int DEFAULT 1,    
    create_time timestamp  DEFAULT sysdate , 
    online_url varchar2(1024),
    PRIMARY KEY (id)
);
COMMENT ON COLUMN t_video_device.device_no   IS '设备编号';
COMMENT ON COLUMN t_video_device.name   IS '设备名称';
COMMENT ON COLUMN t_video_device.device_ip   IS '设备IP地址';

CREATE TABLE  t_video_log (
	id number(19,0),     
	device_id number(19,0),    
	start_time timestamp,
	end_time timestamp,
    video_url varchar2(1024),
    create_time timestamp  DEFAULT sysdate , 
    PRIMARY KEY (id)
);
COMMENT ON COLUMN t_video_device.device_no   IS '设备编号';
COMMENT ON COLUMN t_video_device.name   IS '设备名称';
COMMENT ON COLUMN t_video_device.device_ip   IS '设备IP地址';


insert into t_user (id, login_name, name, password, salt, roles, create_time,user_type) values(1,'admin','管理员','111111','7efbd59d9741d34f','admin',SYSDATE,0);
insert into t_user (id, login_name, name, password, salt, roles, create_time,user_type) values(2,'user','Calvin','111111','6d65d24122c30500','user',sysdate,1);


insert into t_role (id,name) values (1,'管理员');
insert into t_role (id,name) values (2,'会员');


insert into t_user_role (user_id,role_id) values (1,1);
insert into t_user_role (user_id,role_id) values (2,2);

--停车位添加XY坐标字段
ALTER TABLE T_PARKING_GARAGE ADD XCOORDINATE VARCHAR2(255);
ALTER TABLE T_PARKING_GARAGE ADD YCOORDINATE VARCHAR2(255);




