#修改客户密码
UPDATE card set `password` = 123456 WHERE cid = (SELECT cid from customer where cName= '张三');
UPDATE card set `password` = 123123 WHERE cid = (SELECT cid from customer where cName= '李四');
#办理银行卡挂失
UPDATE card set loss = 1 WHERE cid = (SELECT cid from customer where cName= '李四');
#统计银行总存入金额和总支取金额
SELECT (SELECT typeName from tstype WHERE tstype.typeId = transactions.typeId) 资金流向,SUM(Tmoney) 总金额
FROM transactions 
GROUP BY 资金流向
#查询本周开户信息
SELECT card.cardId 卡号,customer.cName 姓名,card.currency 货币,deposit.dName 存款类型,card.openDate 开户日期,card.openMoney 开户金额,card.money 存款余额,card.loss 账户状态
FROM card INNER JOIN customer on card.cid = customer.cid 
INNER JOIN deposit on card.dId = deposit.did
WHERE WEEK(card.openDate) = WEEK(CURRENT_DATE) AND YEAR(card.openDate) = YEAR(CURRENT_DATE)
#查询本月交易金额最高的卡号
	#单次交易最高
SELECT DISTINCT transactions.cardid  卡号
FROM transactions 
WHERE transactions.Tmoney = (SELECT MAX(transactions.Tmoney) FROM transactions) AND YEAR(transactions.Tdate) = YEAR(CURRENT_DATE) AND MONTH(transactions.Tdate) = MONTH(CURRENT_DATE);
#查询挂失客户
SELECT customer.cName 客户姓名,customer.phone 联系电话
FROM card INNER JOIN customer on card.cid = customer.cid
WHERE card.loss = 1;
#催款提醒业务
SELECT customer.cName 客户姓名,customer.phone 联系电话,card.money 存款余额
from customer INNER JOIN card on customer.cid = card.cid
WHERE card.money<200