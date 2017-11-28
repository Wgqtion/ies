-- Drop existing database link 
drop database link TJ_SCHEMA0011_DBLINK;
-- Create database link 
create database link TJ_SCHEMA0011_DBLINK
  connect to SCHEMA0011 identified by DHCzAYhPQa
  using '192.168.132.10:1521/tjdb';