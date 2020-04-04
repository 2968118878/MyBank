#创建客户信息表
drop table if exists customer;
CREATE TABLE customer(
	cid int(4) PRIMARY key auto_increment COMMENT '客户id',
	cName VARCHAR(10) not null COMMENT '客户姓名',
	identity VARCHAR(20) not null UNIQUE COMMENT '身份证号',
	phone VARCHAR(20) not null COMMENT '联系电话',
	address VARCHAR(255) COMMENT '居住地址'
)COMMENT '客户信息表';
alter table customer auto_increment = 1;

#创建银行卡表
drop table if exists card;
CREATE table card(
	cid int not null COMMENT '客户编号 外键',
	cardId CHAR(16) PRIMARY key COMMENT '银行卡号',
	`password` int(6) DEFAULT '888888' COMMENT '密码',
	currency VARCHAR(20) DEFAULT 'RMB' COMMENT '币种',
	dId int not null COMMENT '存款类型 外键',
	openDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '开户日期', 
	openMoney DECIMAL(10,2) DEFAULT 1 COMMENT '开户金额',
	money DECIMAL(10,2) DEFAULT 1 COMMENT '余额',
	loss int(2) DEFAULT 0 COMMENT '是否挂失，0表示否，1表示是'
)COMMENT '银行卡表';

ALTER TABLE card add CONSTRAINT fk_card_customer FOREIGN key(cid) REFERENCES customer(cid); 


#创建存款类型表
drop table if exists deposit;
CREATE table deposit(
	did int(4) PRIMARY key auto_increment COMMENT '存款类型编号',
	dName VARCHAR(10) not null COMMENT '存款类型'
)COMMENT '存款类型表';
alter TABLE deposit auto_increment = 1;
ALTER TABLE card add CONSTRAINT fk_card_deposit FOREIGN KEY(dId) REFERENCES deposit(did);

#创建账目交易表
DROP table if exists transactions;
CREATE TABLE transactions(
	tsid int PRIMARY key auto_increment COMMENT '交易编号',
	cardid char(16) COMMENT '卡号',
	Tdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '交易日期',
	Tmoney DECIMAL(10,2) COMMENT '交易金额',
	typeId int not null COMMENT '交易类型 外键',
	note VARCHAR(255) COMMENT '备注'
)COMMENT '账目交易表';
ALTER TABLE transactions auto_increment = 1;

alter table transactions add CONSTRAINT fk_ts_card FOREIGN key(cardid) REFERENCES card(cardId);

#创建交易类型表
drop table if exists tsType;
CREATE table tsType(
	typeId int(4) PRIMARY key auto_increment COMMENT '类型编号',
	typeName VARCHAR(20) not null COMMENT '交易类型'
)COMMENT '交易类型表';
alter table tsType auto_increment = 1;
alter table transactions add CONSTRAINT fk_ts_type FOREIGN KEY(typeId) REFERENCES tsType(typeId);

#创建挂失表
DROP TABLE if EXISTS lost;
CREATE TABLE lost(
loss int PRIMARY KEY auto_increment COMMENT '挂失编号',
lossName VARCHAR(20) not null COMMENT '挂失类型'
)COMMENT '挂失信息表';
ALTER TABLE lost auto_increment = 0;
ALTER TABLE card ADD CONSTRAINT fk_card_lost FOREIGN KEY(loss) REFERENCES lost(loss);