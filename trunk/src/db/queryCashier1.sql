select t1.cash_fio,
	t1.cnt,
	t1.pb,
	ifnull(t2.sumAll,0),
	ifnull(round(t2.sumAll/t1.pb*100,1),0),
	t1.pr,
	ifnull(t2.sumRzd,0),
	ifnull(round(t2.sumRzd/t1.pr*100,1),0)
from (
select t1.cash_id,
	t1.cash_fio,
	count(distinct t2.plan_id) as cnt,
	sum(t2.plan_base) as pb,
	sum(t2.plan_rzd) as pr
from cashier t1
	join plan_cashier t2 on t1.cash_id=t2.plan_cash_id
where t1.cash_sect_id=1
	and t2.date between '20120505' and '20120506'
	and (t2.plan_base > 0 or t2.plan_rzd > 0)
group by t1.cash_id
) t1
left join (
select t1.cash_id,
	ifnull(sum(case when t4.TicketTypeL not in (select disc_id from discount_rzd) then round(t4.S,1) else 0 end),0) sumAll, 
	ifnull(sum(case when t4.TicketTypeL in (select disc_id from discount_rzd) then round(t4.S,1) else 0 end),0) sumRzd
from cashier t1
	join plan_cashier t2 on t1.cash_id=t2.plan_cash_id
	join file t3 on t1.cash_id=t3.CashierId
	join ticket t4 on t4.FileId=t3.FileId and date(t4.T) = t2.date
where t1.cash_sect_id=1
	and t2.date between '20120505' and '20120506'
	and (t2.plan_base > 0 or t2.plan_rzd > 0)
) t2 on t1.cash_id=t2.cash_id