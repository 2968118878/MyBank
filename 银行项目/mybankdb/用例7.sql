#转账
BEGIN;
UPDATE card set money = money-300 WHERE cid = (SELECT cid FROM customer WHERE cName = '李四');
UPDATE card set money = money+300 WHERE cid = (SELECT cid FROM customer WHERE cName = '张三');

INSERT INTO transactions(cardid,Tdate,Tmoney,typeId,note) VALUES(
#卡号
(SELECT cardId FROM card WHERE cid = (SELECT cid FROM customer WHERE cName = '李四')),
CURRENT_TIMESTAMP,300,
#交易类型
(SELECT typeId FROM tstype WHERE typeName = '支取'),
CURRENT_DATE
);
INSERT INTO transactions(cardid,Tdate,Tmoney,typeId,note) VALUES(
#卡号
(SELECT cardId FROM card WHERE cid = (SELECT cid FROM customer WHERE cName = '张三')),
CURRENT_TIMESTAMP,300,
#交易类型
(SELECT typeId FROM tstype WHERE typeName = '存入'),
CURRENT_DATE
);

COMMIT;