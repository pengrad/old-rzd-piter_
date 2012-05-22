select t1.cash_fio, 
	t2.date,
	t2.route_number,
	t2.plan_base as plan,
	ifnull(sum(case when t4.TicketTypeL not in (select disc_id from discount_rzd) then round(t4.S,1) else 0 end),0) fact
from cashier t1
	join plan_cashier t2 on t1.cash_id=t2.plan_cash_id
	join file t3 on t1.cash_id=t3.CashierId
	join ticket t4 on t4.FileId=t3.FileId and date(t4.T) = t2.date
where t1.cash_sect_id=1
	and t2.date between '20120505' and '20120507'
	and (t2.plan_base > 0)
