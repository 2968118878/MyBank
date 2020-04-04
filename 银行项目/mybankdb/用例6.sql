#创建银行客户记录
DROP VIEW if EXISTS view_userInfo;
CREATE VIEW view_userInfo
as 
	SELECT customer.cid 客户编号,customer.cName 开户名,customer.identity 身份证号,customer.phone 电话号码,customer.address 居住地址
	FROM customer;
	
SELECT * FROM view_userInfo;

#创建银行卡记录
DROP view if exists view_cardInfo;
CREATE view view_cardInfo
as 
	SELECT card.cardId 卡号,customer.cName 客户,card.currency 货币种类,deposit.dName 存款类型,card.openDate 开户日期,card.money 余额,card.`password` 密码,lost.lossName 是否挂失
	FROM customer INNER JOIN card on customer.cid = card.cid INNER JOIN deposit on card.dId = deposit.did INNER JOIN lost on card.loss = lost.loss;
	
SELECT * FROM view_cardInfo;

#创建银行卡交易记录
DROP view if EXISTS view_transInfo;
CREATE VIEW view_transInfo
AS 
	SELECT transactions.Tdate 交易日期,tstype.typeName 交易类型,transactions.cardid 卡号,transactions.Tmoney 交易金额,transactions.note 备注
	FROM transactions INNER JOIN tstype on transactions.typeId = tstype.typeId
	ORDER BY transactions.Tdate;
	
SELECT * FROM view_transInfo;