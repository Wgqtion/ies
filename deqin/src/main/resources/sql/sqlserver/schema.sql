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
  
drop table  t_announcement_send;
drop table  t_announcement;
 

create table t_user (
	id bigint IDENTITY(1,1) NOT NULL,
	login_name nvarchar(64) not null unique,
	name nvarchar(64) not null,
	password  nvarchar(255) not null,
	salt  nvarchar(64),
	roles  nvarchar(255),
	create_time datetime DEFAULT getdate() not null,
	user_type int DEFAULT(1),
	create_user_id bigint ,	
	admin_user_id bigint ,	
	is_enabled int DEFAULT(1),
	update_time datetime,
	photo_attach_id bigint,
	gender nvarchar(2),
	id_number nvarchar(64),
	date_of_birth nvarchar(64),
	mobile_phone nvarchar(64),
	email nvarchar(64),
    date_of_entry datetime,
    departure_date datetime,
    base_salary nvarchar(64),
    marital int,
    has_children  int,
    current_address  nvarchar(255),
	office_address  nvarchar(255),
	degree nvarchar(255),
	professional nvarchar(255),
	bank_card nvarchar(255),
	bank_name nvarchar(255),
	primary key (id)
);


CREATE TABLE t_hospital(
	id bigint IDENTITY(1,1) NOT NULL,
	name nvarchar(255) NOT NULL,
	telephone nvarchar(20) ,
	address nvarchar(255) ,	
	picked_flag int,
	admin_user_id bigint ,
	area nvarchar(255),
	is_open_idq int,
	is_open_eckardt int,
	is_open_sf36 int,
	PRIMARY KEY  (id)           
);	 

 create table t_hospital_nurse (
    nurse_id bigint not null,
    hospital_id bigint not null
 );


create table t_authority (
        id bigint IDENTITY(1,1) NOT NULL,
        display_name  nvarchar(255),
        name  nvarchar(255) not null unique,
        primary key (id)
);


  
create table t_resource (
        id bigint IDENTITY(1,1) NOT NULL,
        position float not null,
        resource_type  nvarchar(255) not null,
        val  nvarchar(255) not null unique,
        primary key (id)
) ;


create table t_resource_authority (
        resource_id bigint not null,
        authority_id bigint not null
);


create table t_role (
        id bigint IDENTITY(1,1) NOT NULL,
        name  nvarchar(255) not null unique,
        primary key (id)
);

create table t_role_authority (
        role_id bigint not null,
        authority_id bigint not null
);

create table t_user_role (
        user_id bigint not null,
        role_id bigint not null
) ;
    

create table t_system_log (
     id bigint IDENTITY(1,1) NOT NULL,
     log_type  nvarchar(255),
  	 subtype  nvarchar(255),
     create_time datetime,
     user_id bigint ,
     user_ip  nvarchar(255) ,
     log_content  nvarchar(512) ,
     user_name  nvarchar(255) ,
     PRIMARY KEY (id)
);


CREATE TABLE t_attach (
  id bigint IDENTITY(1,1) NOT NULL,
  name  nvarchar(255),
  create_time datetime,
  url_path  nvarchar(1024),
  file_size bigint ,
  file_type  nvarchar(255),
  user_id bigint,
  upload_session_id  nvarchar(1024),
  file_key  nvarchar(255) not null unique,
  PRIMARY KEY (id)
) ;
 
CREATE TABLE t_login_log (
	 id bigint IDENTITY(1,1) NOT NULL,
	 create_time datetime,
	 user_id bigint,
	 user_ip  nvarchar(255),
	 log_content  nvarchar(512),
	 user_name  nvarchar(255),
	 PRIMARY KEY (id)
);
 
 
CREATE TABLE t_dict (                                          
   id bigint IDENTITY(1,1) NOT NULL,               
   type_name  nvarchar(255),         
   name  nvarchar(255),         
   code  nvarchar(255),         
   description  nvarchar(255),  
   sort int,                                      
   PRIMARY KEY  (id)                                               
);
 
 
CREATE TABLE t_announcement(
	id bigint IDENTITY(1,1) NOT NULL,
	title nvarchar(255),
	context nvarchar(2000),
	create_user_id bigint,
	start_date datetime, 
	create_time datetime,
	state int,
	to_all_user int,
	PRIMARY KEY  (id)  
 );
 
 CREATE TABLE t_announcement_send(
	id bigint IDENTITY(1,1) NOT NULL, 
	announcement_id bigint,
	send_user_id bigint,
	send_date datetime,  
	is_read int,
	read_time datetime,
	is_close int,
	PRIMARY KEY (id)  
 );
 
  
CREATE TABLE t_system_notice(
	id bigint IDENTITY(1,1) NOT NULL, 
	title nvarchar(255),
	context nvarchar(2000),
	to_user_id bigint,
	send_time datetime,
	is_read int,
	read_time datetime,
	is_close int,
	link_url nvarchar(255),
	PRIMARY KEY (id)
 );
 

CREATE TABLE t_patient(
	id bigint IDENTITY(1,1) NOT NULL,
	name nvarchar(255) ,
	sex nvarchar(4) ,
	phone nvarchar(100) ,
	age int ,
	year_of_birth int ,
	height float ,
	weight int ,
	serial_number nvarchar(50) ,
	patient_no nvarchar(50) ,
	is_policy int ,
	policy_no nvarchar(50) ,
	hospital_id bigint ,
	mobile nvarchar(50) ,
	address nvarchar(255) ,
	zip_code nvarchar(50) ,
	is_accept_zip int ,
	call_time_val int ,
	check_in_date datetime ,
	degree int ,
	job int ,
	doctor_name nvarchar(200) ,
	doctor_phone nvarchar(200) ,
	doctor_mobile nvarchar(200) ,
	hospital_name nvarchar(255) ,
	picked_flag int ,
	create_user_id bigint ,
	create_time datetime ,
	update_time datetime ,
	nation	nvarchar(255),
	is_smoking int,
	smoking_num int,
	is_drinking int,
	drinking_num int,
	PRIMARY KEY (id)
);  
CREATE TABLE  t_symptom(
	id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	symptom_1 nvarchar(4),
	symptom_1_days nvarchar(50),
	symptom_2 nvarchar(4),
	symptom_2_days nvarchar(50),
	symptom_3 nvarchar(4),
	symptom_3_days nvarchar(50),
	symptom_4 nvarchar(4),
	symptom_4_days nvarchar(50),
	symptom_5 nvarchar(4),
	symptom_5_days nvarchar(50),
	symptom_6 nvarchar(4),
	symptom_6_days nvarchar(50),
	symptom_7 nvarchar(4),
	symptom_7_days nvarchar(50),
	symptom_8 nvarchar(4),
	symptom_8_days nvarchar(50),
	symptom_9 nvarchar(4),
	symptom_9_days nvarchar(50),
	symptom_10 nvarchar(4),
	symptom_10_days nvarchar(50),
	symptom_11 nvarchar(4),
	symptom_11_days nvarchar(50),
	patient_id bigint,
	symptom_10_desc nvarchar(255),
	PRIMARY KEY (id)
);

CREATE TABLE  t_medical_history(
	id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	description ntext,
	attach_id bigint,
	is_before_know int,
	patient_id bigint,
	mh_1	int, 		
	mh_2	int,		
	mh_3	int, 		
	mh_4	int, 		
	mh_5	int, 		
	drug	nvarchar(255),
	PRIMARY KEY (id)
);

CREATE TABLE  t_gerdq_scores(
	id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	scores int,
	score_a1 int,
	score_a2 int,
	score_b1 int,
	score_b2 int,
	score_c1 int,
	score_c2 int,
	attach_id bigint,
	val_a1 int,
	val_a2 int,
	val_b1 int,
	val_b2 int,
	val_c1 int,
	val_c2 int, 
	patient_id bigint,
	PRIMARY KEY (id)
);

CREATE TABLE  t_endoscopy(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	doctor_name nvarchar(50),
	description ntext,
	attach_id bigint,
	detail_A nvarchar(50),
	detail_B nvarchar(50),
	detail_C nvarchar(50),
	detail_D nvarchar(50),
	detail_E nvarchar(50),
	patient_id bigint,
	is_nerd	int,		
    is_ee	int,	
    is_be	int,	
    is_other int,	
    other_desc	nvarchar(255),
	PRIMARY KEY (id)
);
CREATE TABLE  t_dynamic_ph(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	reporter_name nvarchar(50),
	attach_id bigint,
	report ntext,	 
	other_name nvarchar(255),
	other_check_in_date datetime,
	other_reporter_name nvarchar(50),
	other_report ntext,
	other_attach_id bigint,
	patient_id bigint,
	PRIMARY KEY (id)
);



CREATE TABLE  t_barium_meal(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	reporter_name nvarchar(50),
	attach_id bigint,
	report ntext, 
	patient_id bigint,
	PRIMARY KEY (id)
);
  
CREATE TABLE  t_esophageal_pressure(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	reporter_name nvarchar(50),
	attach_id bigint,
	report ntext, 
	patient_id bigint,
	PRIMARY KEY (id)
);

CREATE TABLE  t_idq(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	option_1  int,
	option_2  int,
	option_3  int,
	option_4  int,
	option_5  int,
	option_6  int,
	option_7  int,
	option_9  int,
	option_10  int,
	option_11  int,	 
	patient_id bigint,
	PRIMARY KEY (id)
);
 

CREATE TABLE  t_eckardt(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	option_1  int,
	option_2  int,
	option_3  int,	 
	option_4  int,
	patient_id bigint,
	PRIMARY KEY (id)
);

CREATE TABLE  t_SF36(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	option_1  int,
	option_2  int,
	option_3detal_1  int,	 
	option_3detal_2  int,	
	option_3detal_3  int,	
	option_3detal_4  int,	
	option_3detal_5  int,	
	option_3detal_6  int,	
	option_3detal_7  int,	
	option_3detal_8  int,	
	option_3detal_9  int,	
	option_3detal_10  int, 
	option_4detal_1  int,	 
	option_4detal_2  int,	
	option_4detal_3  int,	
	option_4detal_4  int,	
	option_5detal_1  int,	 
	option_5detal_2  int,	
	option_5detal_3  int, 
	option_6  int,	 
	option_7  int,
	option_8  int,
	option_9detal_1  int,	 
	option_9detal_2  int,	
	option_9detal_3  int,	
	option_9detal_4  int,	
	option_9detal_5  int,	
	option_9detal_6  int,	
	option_9detal_7  int,	
	option_9detal_8  int,	
	option_9detal_9  int,	
	option_10  int,
	option_11detal_1  int,	 
	option_11detal_2  int,	
	option_11detal_3  int,	
	option_11detal_4  int,	
	 
	patient_id bigint,
	PRIMARY KEY (id)
);


 


CREATE TABLE  t_patient_visit(
	id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	visit_date datetime,
	scores int,
	score_a1 int,
	score_a2 int,
	score_b1 int,
	score_b2 int,
	score_c1 int,
	score_c2 int,
	attach_id bigint,
	val_a1 int,
	val_a2 int,
	val_b1 int,
	val_b2 int,
	val_c1 int,
	val_c2 int, 
	patient_id bigint,
	create_user_id bigint ,
	create_time datetime ,
	update_time datetime ,
	other_drug_name nvarchar(255),
	other_usage nvarchar(50),   --次每天	
	other_dosage nvarchar(50),    --片每次
	other_stop_drug_name nvarchar(255),	
	other_stop_why nvarchar(50),
	other_stop_why_desc nvarchar(255),
	is_remind int,
	is_better int,
	PRIMARY KEY (id)
);
CREATE TABLE  t_patient_visit_pharmacy(
	id bigint IDENTITY(1,1) NOT NULL,
	drug_type_id bigint,
	drug_id bigint,
	usage nvarchar(50),
	usage_unit nvarchar(50),
	dosage nvarchar(50),
	period nvarchar(50),	  
	patient_visit_id bigint, 
	PRIMARY KEY (id)
);

CREATE TABLE  t_patient_pharmacy(
	id bigint IDENTITY(1,1) NOT NULL,
	drug_type_id bigint,
	drug_id bigint,
	usage nvarchar(50),
	usage_unit nvarchar(50),
	dosage nvarchar(50),
	period nvarchar(50),	  
	patient_id bigint, 	
	PRIMARY KEY (id)
);



CREATE TABLE  t_patient_review(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	satisfaction int,
	visit_date datetime,
	scores int,
	score_a1 int,
	score_a2 int,
	score_b1 int,
	score_b2 int,
	score_c1 int,
	score_c2 int, 
	val_a1 int,
	val_a2 int,
	val_b1 int,
	val_b2 int,
	val_c1 int,
	val_c2 int, 
	create_user_id bigint ,
	create_time datetime ,
	update_time datetime ,
	patient_id bigint,
	is_better int,
	PRIMARY KEY (id)
);

CREATE TABLE  t_patient_review_pharmacy(
	id bigint IDENTITY(1,1) NOT NULL,
	drug_type_id bigint,
	drug_id bigint,
	usage nvarchar(50),
	usage_unit nvarchar(50),
	dosage nvarchar(50),
	period nvarchar(50),	  
	patient_review_id bigint, 
	PRIMARY KEY (id)
);


CREATE TABLE  t_patient_review_endoscopy(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	doctor_name nvarchar(50),
	description ntext,
	attach_id bigint,
	detail_A nvarchar(50),
	detail_B nvarchar(50),
	detail_C nvarchar(50),
	detail_D nvarchar(50),
	detail_E nvarchar(50),
	patient_review_id bigint,
	is_nerd	int,		
    is_ee	int,	
    is_be	int,	
    is_other int,	
    other_desc	nvarchar(255),
	PRIMARY KEY (id)
);

CREATE TABLE  t_patient_review_dynamic_ph(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	reporter_name nvarchar(50),
	attach_id bigint,
	report ntext,	 
	other_name nvarchar(255),
	other_check_in_date datetime,
	other_reporter_name nvarchar(50),
	other_report ntext,
	other_attach_id bigint,
	patient_review_id bigint,
	PRIMARY KEY (id)
);


CREATE TABLE  t_patient_review_barium_meal(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	reporter_name nvarchar(50),
	attach_id bigint,
	report ntext, 
	patient_review_id bigint,
	PRIMARY KEY (id)
);

 
CREATE TABLE  t_patient_review_esophageal_pressure(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	reporter_name nvarchar(50),
	attach_id bigint,
	report ntext, 
	patient_review_id bigint,
	PRIMARY KEY (id)
);



CREATE TABLE  t_patient_review_idq(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	option_1  int,
	option_2  int,
	option_3  int,
	option_4  int,
	option_5  int,
	option_6  int,
	option_7  int,
	option_9  int,
	option_10  int,
	option_11  int,
	patient_review_id bigint,
	PRIMARY KEY (id)
); 

CREATE TABLE  t_patient_review_eckardt(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	option_1  int,
	option_2  int,
	option_3  int,	 
	option_4  int,
	patient_review_id bigint,
	PRIMARY KEY (id)
);

CREATE TABLE  t_patient_review_SF36(
    id bigint IDENTITY(1,1) NOT NULL,
	check_in_date datetime,
	option_1  int,
	option_2  int,
	option_3detal_1  int,	 
	option_3detal_2  int,	
	option_3detal_3  int,	
	option_3detal_4  int,	
	option_3detal_5  int,	
	option_3detal_6  int,	
	option_3detal_7  int,	
	option_3detal_8  int,	
	option_3detal_9  int,	
	option_3detal_10  int, 
	option_4detal_1  int,	 
	option_4detal_2  int,	
	option_4detal_3  int,	
	option_4detal_4  int,	
	option_5detal_1  int,	 
	option_5detal_2  int,	
	option_5detal_3  int, 
	option_6  int,	 
	option_7  int,
	option_8  int,
	option_9detal_1  int,	 
	option_9detal_2  int,	
	option_9detal_3  int,	
	option_9detal_4  int,	
	option_9detal_5  int,	
	option_9detal_6  int,	
	option_9detal_7  int,	
	option_9detal_8  int,	
	option_9detal_9  int,	
	option_10  int,
	option_11detal_1  int,	 
	option_11detal_2  int,	
	option_11detal_3  int,	
	option_11detal_4  int,		
	 
	patient_review_id bigint,
	PRIMARY KEY (id)
);


CREATE TABLE  t_sports(
   id bigint IDENTITY(1,1) NOT NULL,
   city nvarchar(50),
   hospital_id bigint,
   hospital_name nvarchar(50),
   start_time datetime,
   end_time  datetime,
   title nvarchar(255),
   notice_time  datetime,
   sports_type  nvarchar(50),
   patient_type  nvarchar(50),
   sports_date datetime,
   speaker nvarchar(50),
   address nvarchar(50),
   content ntext,
   create_user_id bigint,
   create_time datetime,
   PRIMARY KEY (id)
);


CREATE TABLE  t_material_apply(
    id bigint IDENTITY(1,1) NOT NULL,	
    material_type nvarchar(255),
	need_date  datetime,
	address  nvarchar(255),
	apply_tel  nvarchar(255),
	apply_date datetime,
	create_time datetime,
	hospital_id bigint,
	apply_user_id bigint,
	apply_user_name nvarchar(255),
	create_user_id bigint,
	approval_user_id bigint,
	approval_time datetime,
	approval_state int,
	approval_remark nvarchar(1000),
	send_date datetime,
	send_no nvarchar(255),
	sign_state int,
	sign_date datetime,
	sign_user_id bigint,
	sign_sys_time datetime,
	PRIMARY KEY (id)
);




CREATE TABLE  t_sms_log(
    id bigint IDENTITY(1,1) NOT NULL,	
    patient_id bigint,
	patient_name  nvarchar(255),	 
	send_time datetime, 
	send_state int,
	content nvarchar(512),
	results  nvarchar(512) 
	PRIMARY KEY (id)
);



delete from t_attach;
delete from t_barium_meal;
delete from t_dynamic_ph;
delete from t_eckardt;
delete from t_endoscopy;
delete from t_esophageal_pressure;
delete from t_gerdq_scores;
delete from t_idq;
delete from t_medical_history;
delete from t_patient;
delete from t_patient_pharmacy;
delete from t_patient_review;
delete from t_patient_review_barium_meal;
delete from t_patient_review_dynamic_ph;
delete from t_patient_review_eckardt;
delete from t_patient_review_endoscopy;
delete from t_patient_review_esophageal_pressure;
delete from t_patient_review_idq;
delete from t_patient_review_pharmacy;
delete from t_patient_review_SF36;
 
delete from t_patient_visit;
delete from t_patient_visit_pharmacy;
delete from t_SF36;
delete from t_symptom;
 