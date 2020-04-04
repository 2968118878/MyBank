#插入数据
#插入存款类型信息
INSERT INTO deposit(dName) VALUES('活期'),('定期一年');
#插入交易类型信息
INSERT INTO tstype(typeName) VALUES('支取'),('存入');
#插入客户信息表记录
INSERT into customer(cName,identity,phone,address) VALUES
('丁六','567891321242345618','0752-43345543','北京市西城区'),
('王五','56789123453212467x','010-44443333','河北石家庄市'),
('张三','123456789012345789','010-67898978','北京海淀区'),
('丁一','334456889012678','2222-63598978','河南新乡'),
('李四','321245678912345678','2478-44443333','山东济南市');
#插入银行卡号记录
INSERT INTO card(cid,cardId,`password`,dId,openDate,openMoney,money,loss)
VALUES(1,'1010357612121004',DEFAULT,2,'2016-7-25',DEFAULT,1001,DEFAULT),
(2,'1010357612121130',DEFAULT,2,'2016-7-25',DEFAULT,DEFAULT,DEFAULT),
(3,'1010357612345678',123456,1,'2016-7-25',1000,6100,DEFAULT),
(4,'1010357619148284',DEFAULT,1,'2016-7-25',1000,1000,DEFAULT),
(5,'1010357612121134',123123,2,'2016-7-25',DEFAULT,1501,1);
#插入交易记录
INSERT into transactions(cardid,Tdate,Tmoney,typeId,note)
VALUES('1010357612345678','2016-7-25',900,1,'2016-7-25'),
('1010357612121130','2016-7-25',300,2,'2016-7-25'),
('1010357612121004','2016-7-25',1000,2,'2016-7-25'),
('1010357612121130','2016-7-25',1900,2,'2016-7-25'),
('1010357612121134','2016-7-25',5000,2,'2016-7-25'),
('1010357612121134','2016-7-25',500,2,'2016-7-25'),
('1010357612121134','2016-7-25',2000,1,'2016-7-25'),
('1010357612345678','2016-7-25',2000,2,'2016-7-25'),
('1010357612121134','2016-7-25',2000,1,'2016-7-25'),
('1010357612345678','2016-7-25',2000,2,'2016-7-25'),
('1010357612345678','2016-7-25',2000,2,'2016-7-25');
#插入挂失信息
INSERT INTO lost(lossName) VALUES('正常'),('挂失');

#张三取款900元
BEGIN;
UPDATE card set money = money-900 WHERE cid = (SELECT cid FROM customer WHERE cName = '张三');
INSERT INTO transactions(cardid,Tdate,Tmoney,typeId,note) VALUES(
#卡号
(SELECT cardId FROM card WHERE cid = (SELECT cid FROM customer WHERE cName = '张三')),
CURRENT_TIMESTAMP,900,
#交易类型
(SELECT typeId FROM tstype WHERE typeName = '支取'),
CURRENT_DATE
);
COMMIT;

#李四存款5000元
BEGIN;
UPDATE card set money = money+5000 WHERE cid = (SELECT cid FROM customer WHERE cName = '李四');
INSERT INTO transactions(cardid,Tdate,Tmoney,typeId,note) VALUES(
#卡号
(SELECT cardId FROM card WHERE cid = (SELECT cid FROM customer WHERE cName = '李四')),
CURRENT_TIMESTAMP,5000,
#交易类型
(SELECT typeId FROM tstype WHERE typeName = '存入'),
CURRENT_DATE
);
ROLLBACK;
COMMIT;