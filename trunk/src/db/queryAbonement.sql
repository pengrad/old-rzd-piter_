select 'ab-all' type, 
		count(t1.TicketId) as sum,
		ifnull(sum(case when tL.L is null then 1 else 0 end),0) as sumPay,
		ifnull(sum(case when tL.L is not null then 1 else 0 end),0) as sumRzdWork
from ticket t1
	join file t2 on t1.FileId=t2.FileId
	left join (select disc_id as L from discount_rzdwork) tL on t1.TicketTypeL=tL.L
where t1.TimeCalcReport >= '20010101' and t1.TimeCalcReport < '20130101'
	and t2.PlaceTerm in (0)
	and (null is null or t2.TypeTerm = null)
	and t1.TicketTypeID in (select a_type from temp_ab)

union all 

select concat('ab-',cast(ta.a_type as char)) type,
	ifnull(t1.sum,0) as sum,
	ifnull(t1.sumPay,0) as sumPay,
	ifnull(t1.sumRzdWork,0) as sumRzdWork
from temp_ab ta left join 
(select t1.TicketTypeId,
		count(t1.TicketId) as sum,
		ifnull(sum(case when tL.L is null then 1 else 0 end),0) as sumPay,
		ifnull(sum(case when tL.L is not null then 1 else 0 end),0) as sumRzdWork
from ticket t1 
	join file t2 on t1.FileId=t2.FileId
	left join (select disc_id as L from discount_rzdwork) tL on t1.TicketTypeL=tL.L
where t1.TimeCalcReport >= '20010101' and t1.TimeCalcReport < '20130101'
	and t2.PlaceTerm in (0)
	and (null is null or t2.TypeTerm = null)
	and t1.TicketTypeID in (select a_type from temp_ab)
group by t1.TicketTypeId
) t1 on ta.a_type=t1.TicketTypeId;