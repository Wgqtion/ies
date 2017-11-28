SET IDENTITY_INSERT t_user ON
insert into t_user (id, login_name, name, password, salt, roles, create_time) values(1,'admin','Admin','21232f297a57a5a743894a0e4a801fc3','7efbd59d9741d34f','admin','2012-06-04 01:00:00');
insert into t_user (id, login_name, name, password, salt, roles, create_time) values(2,'user','Calvin','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','user','2012-06-04 02:00:00');
SET IDENTITY_INSERT t_user OFF

SET IDENTITY_INSERT t_role ON
insert into t_role (id,name) values (1,'admin');
insert into t_role (id,name) values (2,'user');
SET IDENTITY_INSERT t_role OFF

insert into t_user_role (user_id,role_id) values (1,1);
insert into t_user_role (user_id,role_id) values (2,2);