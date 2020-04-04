#创建数据库
CREATE DATABASE mybankDB;

#创建用户并赋予权限
CREATE USER 'bankMaster'@'localhost' IDENTIFIED by '1234';
GRANT ALL privileges ON mybankDB TO 'bankMaster'@'localhost';

