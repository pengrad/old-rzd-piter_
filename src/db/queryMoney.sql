select 'zd' type, ifnull(sum(t1.S),0) sum, ifnull(sum(t1.S),0) sumPay, null sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, 0 sumService
from ticket t1
	join file t2 on t1.FileId=t2.FileId
where t1.TimeCalcReport >= '20010101' and t1.TimeCalcReport < '20130101'
	and t2.PlaceTerm in (0)
	and (null is null or t2.TypeTerm = null)
	and ifnull(t1.a,0) <> 1
union all	

select 'zn' type, ifnull(sum(t1.S),0) sum, null sumPay, 
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_fedsoc) then t1.S else 0 end),0) sumFedSoc, 
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_fednonsoc) then t1.S else 0 end),0) sumFedNonSoc, 
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_region) then t1.S else 0 end),0) sumRegion, 
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_war) then t1.S else 0 end),0) sumWar,
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_study) then t1.S else 0 end),0) sumStudy,
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdpersonal) then t1.S else 0 end),0) sumRZDPersonal,
	ifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdwork) then t1.S else 0 end),0) sumRZDWork,	
	null sumRZDService, null sumService
from ticket t1
	join file t2 on t1.FileId=t2.FileId
where t1.TimeCalcReport >= '20010101' and t1.TimeCalcReport < '20130101'
	and t2.PlaceTerm in (0)
	and (null is null or t2.TypeTerm = null)	
	and ifnull(t1.a,0) <> 1
	and ifnull(t1.TicketTypeL,0) not in (select disc_id from discount_rzdservice)
	
union all	

select 'zp' type, ifnull(sum(t1.S),0) sum, null sumPay, null sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, 
	ifnull(sum(t1.S),0) sumRZDService, 
	null sumService
from ticket t1
	join file t2 on t1.FileId=t2.FileId
where t1.TimeCalcReport >= '20010101' and t1.TimeCalcReport < '20130101'
	and t2.PlaceTerm in (0)
	and (null is null or t2.TypeTerm = null)	
	and ifnull(a,0) <> 1
	and ifnull(t1.TicketTypeL,0) in (select disc_id from discount_rzdservice);