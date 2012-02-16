select 't-all' type,
	count(t1.TicketId) sum, 
	ifnull(sum(case when t1.TicketTypeL not in (select disc_id from discount) then 1 else 0 end),0) sumPay, 
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_fedsoc) then 1 else 0 end),0) sumFedSoc, 
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_fednonsoc) then 1 else 0 end),0) sumFedNonSoc, 
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_region) then 1 else 0 end),0) sumRegion, 
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_war) then 1 else 0 end),0) sumWar,
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_study) then 1 else 0 end),0) sumStudy,
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdpersonal) then 1 else 0 end),0) sumRZDPersonal,
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdwork) then 1 else 0 end),0) sumRZDWork,	
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdservice) then 1 else 0 end),0) sumRZDService, 
	0 sumService
from ticket t1
	join file t2 on t1.FileId=t2.FileId
where t1.TimeCalcReport >= '20010101' and t1.TimeCalcReport < '20130101'
	and t2.PlaceTerm in (0)
	and ifnull(t1.A,0) <> 1
	
union all	

select't-one-way' type,
	count(t1.TicketId) sum, 
	ifnull(sum(case when t1.TicketTypeL not in (select disc_id from discount) then 1 else 0 end),0) sumPay, 
	null sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, null sumService
from ticket t1
	join file t2 on t1.FileId=t2.FileId
where t1.TimeCalcReport >= '20010101' and t1.TimeCalcReport < '20130101'
	and t2.PlaceTerm in (0)
	and ifnull(t1.A,0) <> 1
	and ifnull(t1.R,0) = 0
	
union all

select 't-round' type,
	count(t1.TicketId) sum, 
	ifnull(sum(case when t1.TicketTypeL not in (select disc_id from discount) then 1 else 0 end),0) sumPay, 
	null sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, null sumService
from ticket t1
	join file t2 on t1.FileId=t2.FileId
where t1.TimeCalcReport >= '20010101' and t1.TimeCalcReport < '20130101'
	and t2.PlaceTerm in (0)
	and ifnull(t1.A,0) <> 1
	and ifnull(t1.R,0) = 1;