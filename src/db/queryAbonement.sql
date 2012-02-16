select 'ab-all' type, 
		count(t1.TicketId) as sum,
		ifnull(sum(case when t2.L is null then 1 else 0 end),0) as sumPay,
		ifnull(sum(case when t2.L is not null then 1 else 0 end),0) as sumRzdWork
from ticket t1
	join file tf on t1.FileId=tf.FileId
	left join (select disc_id as L from discount_rzdwork) t2 on t1.TicketTypeL=t2.L
where t1.TimeCalcReport >= '20010101' and t1.TimeCalcReport < '20130101'
	and tf.PlaceTerm in (0)
	and t1.TicketTypeID in (select a_type from temp_ab)

union all 

select concat('ab-',cast(ta.a_type as char)) type,
	ifnull(t1.sum,0) as sum,
	ifnull(t1.sumPay,0) as sumPay,
	ifnull(t1.sumRzdWork,0) as sumRzdWork
from temp_ab ta left join 
(select t1.TicketTypeId,
		count(t1.TicketId) as sum,
		ifnull(sum(case when t2.L is null then 1 else 0 end),0) as sumPay,
		ifnull(sum(case when t2.L is not null then 1 else 0 end),0) as sumRzdWork
from ticket t1 
	join file tf on t1.FileId=tf.FileId
	left join (select disc_id as L from discount_rzdwork) t2 on t1.TicketTypeL=t2.L
where t1.TimeCalcReport >= '20010101' and t1.TimeCalcReport < '20130101'
	and tf.PlaceTerm in (0)
	and t1.TicketTypeID in (select a_type from temp_ab)
group by t1.TicketTypeId
) t1 on ta.a_type=t1.TicketTypeId;