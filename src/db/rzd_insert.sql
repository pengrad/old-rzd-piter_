use rzd;

truncate table discount;
truncate table direction;
truncate table sector;
truncate table temp_ab;

#Льготы
insert discount(disc_id, disc_type_name, disc_type_id)values
(2407,'FedSoc', 1),
(2408,'FedSoc', 1),
(2414,'FedSoc', 1),
(2415,'FedSoc', 1),
(2416,'FedSoc', 1),
(2418,'FedSoc', 1),
(2419,'FedSoc', 1),
(2420,'FedSoc', 1),
(2421,'FedSoc', 1),
(2422,'FedSoc', 1),
(2423,'FedSoc', 1),
(2424,'FedSoc', 1),
(2425,'FedSoc', 1),

(2304,'FedNonSoc', 2),

(2704,'Region', 3),
(2705,'Region', 3),
(2706,'Region', 3),
(2810,'Region', 3),
(2811,'Region', 3),
(2812,'Region', 3),
(2813,'Region', 3),
(2836,'Region', 3),
(2837,'Region', 3),
(2838,'Region', 3),
(2839,'Region', 3),
(2840,'Region', 3),
(2918,'Region', 3),
(2919,'Region', 3),
(2920,'Region', 3),
(2924,'Region', 3),
(3002,'Region', 3),
(3003,'Region', 3),
(3004,'Region', 3),
(3005,'Region', 3),
(3019,'Region', 3),
(3058,'Region', 3),

(2101,'War', 4),
(2105,'War', 4),
(2114,'War', 4),

(2605,'Study', 5),
(2606,'Study', 5),
(2608,'Study', 5),

(2206,'RZDPersonal', 6),
(2208,'RZDPersonal', 6),
(2209,'RZDPersonal', 6),
(2210,'RZDPersonal', 6),
(2212,'RZDPersonal', 6),
(2213,'RZDPersonal', 6),

(2204,'RZDWork', 7),
(2205,'RZDWork', 7),

(2201,'RZDService', 8),
(2202,'RZDService', 8),
(2203,'RZDService', 8),
(2207,'RZDService', 8),
(2216,'RZDService', 8),
(2217,'RZDService', 8),
(2218,'RZDService', 8);


#Направления
insert direction(dir_id, dir_name) values
(1,'Московское направление'),
(2,'Финляндское направление'),
(3,'Витебское направление'),
(4,'Балтийское направление'),
(5,'Псковское направление'),
(6,'Волховское направление'),
(7,'Петрозаводское направление');

#Участки
insert sector(sect_id, sect_name, sect_dir_id, sect_parent_id) values
(1,'УП Витебский',3,null),
(2,'УП Детское Село',3,null);

#Станции УП Детское Село
insert into station (stat_id,stat_name) values (2007889,'АНТРОПШИНО ККБР');
insert into station (stat_id,stat_name) values (2007850,'ВЫРИЦА ККБР');
insert into station (stat_id,stat_name) values (2007804,'Д.СЕЛО-ДОР');
insert into station (stat_id,stat_name) values (2007843,'Д.СЕЛО-ППК (РБК)');
insert into station (stat_id,stat_name) values (2007802,'КУПЧИН-ДОР');
insert into station (stat_id,stat_name) values (2007806,'ОРЕДЕЖ ККБР');
insert into station (stat_id,stat_name) values (2007805,'ПАВЛОВСК ККБР');
insert into station (stat_id,stat_name) values (2004780,'ПАРОВОЗНЫЙ МУЗЕЙ');
insert into station (stat_id,stat_name) values (2004923,'ОП 21 КМ');
insert into station (stat_id,stat_name) values (2004182,'ДЕТСКОЕ СЕЛО');
insert into station (stat_id,stat_name) values (2004181,'ПАВЛОВСК');
insert into station (stat_id,stat_name) values (2005046,'АНТРОПШИНО');
insert into station (stat_id,stat_name) values (2005096,'КОБРАЛОВО');
insert into station (stat_id,stat_name) values (2005236,'СЕМРИНО');
insert into station (stat_id,stat_name) values (2004781,'ОП 46 КМ');
insert into station (stat_id,stat_name) values (2005454,'СУСАНИНО');
insert into station (stat_id,stat_name) values (2005369,'КРАСНИЦЫ');
insert into station (stat_id,stat_name) values (2005296,'МИХАЙЛОВКА');
insert into station (stat_id,stat_name) values (2004180,'ВЫРИЦА');
insert into station (stat_id,stat_name) values (2004803,'ПЛ 63 КМ');
insert into station (stat_id,stat_name) values (2004179,'СЛУДИЦЫ');
insert into station (stat_id,stat_name) values (2004804,'ПЛ 78 КМ');
insert into station (stat_id,stat_name) values (2004805,'ПЛ 80 КМ');
insert into station (stat_id,stat_name) values (2004178,'НОВИНКА');
insert into station (stat_id,stat_name) values (2004807,'ПЛ 92 КМ');
insert into station (stat_id,stat_name) values (2004806,'ПЛ 90 КМ');
insert into station (stat_id,stat_name) values (2004177,'ЧАЩА');
insert into station (stat_id,stat_name) values (2005431,'ОП 100 КМ');
insert into station (stat_id,stat_name) values (2005445,'ОП 101 КМ');
insert into station (stat_id,stat_name) values (2004176,'ЧОЛОВО');
insert into station (stat_id,stat_name) values (2004808,'ПЛ 117 КМ');
insert into station (stat_id,stat_name) values (2004175,'ТАРКОВИЧИ');
insert into station (stat_id,stat_name) values (2005448,'ОП 125 КМ');
insert into station (stat_id,stat_name) values (2004641,'ОРЕДЕЖ');
insert into station (stat_id,stat_name) values (2005295,'ПЛ 34 КМ');
insert into station (stat_id,stat_name) values (2005940,'ПЛ 36 КМ');
insert into station (stat_id,stat_name) values (2004802,'ПЛ 40 КМ');
insert into station (stat_id,stat_name) values (2004240,'НОВОЛИСИНО');
insert into station (stat_id,stat_name) values (2005421,'ВЛАДИМИРСКАЯ');
insert into station (stat_id,stat_name) values (2005358,'1 ПЛАТФОРМА');
insert into station (stat_id,stat_name) values (2004401,'2 ПЛАТФОРМА');
insert into station (stat_id,stat_name) values (2004434,'3 ПЛАТФОРМА');
insert into station (stat_id,stat_name) values (2004436,'4 ПЛАТФОРМА');
insert into station (stat_id,stat_name) values (2005026,'ПОСЕЛОК');


insert into station_sector_cross (stat_id,sect_id) values (2007889,2);
insert into station_sector_cross (stat_id,sect_id) values (2007850,2);
insert into station_sector_cross (stat_id,sect_id) values (2007804,2);
insert into station_sector_cross (stat_id,sect_id) values (2007843,2);
insert into station_sector_cross (stat_id,sect_id) values (2007802,2);
insert into station_sector_cross (stat_id,sect_id) values (2007806,2);
insert into station_sector_cross (stat_id,sect_id) values (2004780,2);
insert into station_sector_cross (stat_id,sect_id) values (2004923,2);
insert into station_sector_cross (stat_id,sect_id) values (2004182,2);
insert into station_sector_cross (stat_id,sect_id) values (2004181,2);
insert into station_sector_cross (stat_id,sect_id) values (2005046,2);
insert into station_sector_cross (stat_id,sect_id) values (2005096,2);
insert into station_sector_cross (stat_id,sect_id) values (2005236,2);
insert into station_sector_cross (stat_id,sect_id) values (2005454,2);
insert into station_sector_cross (stat_id,sect_id) values (2005369,2);
insert into station_sector_cross (stat_id,sect_id) values (2005296,2);
insert into station_sector_cross (stat_id,sect_id) values (2004180,2);
insert into station_sector_cross (stat_id,sect_id) values (2004803,2);
insert into station_sector_cross (stat_id,sect_id) values (2004179,2);
insert into station_sector_cross (stat_id,sect_id) values (2004804,2);
insert into station_sector_cross (stat_id,sect_id) values (2004805,2);
insert into station_sector_cross (stat_id,sect_id) values (2004178,2);
insert into station_sector_cross (stat_id,sect_id) values (2004807,2);
insert into station_sector_cross (stat_id,sect_id) values (2004806,2);
insert into station_sector_cross (stat_id,sect_id) values (2004177,2);
insert into station_sector_cross (stat_id,sect_id) values (2005431,2);
insert into station_sector_cross (stat_id,sect_id) values (2005445,2);
insert into station_sector_cross (stat_id,sect_id) values (2004176,2);
insert into station_sector_cross (stat_id,sect_id) values (2004808,2);
insert into station_sector_cross (stat_id,sect_id) values (2004175,2);
insert into station_sector_cross (stat_id,sect_id) values (2005448,2);
insert into station_sector_cross (stat_id,sect_id) values (2004641,2);
insert into station_sector_cross (stat_id,sect_id) values (2005295,2);
insert into station_sector_cross (stat_id,sect_id) values (2005940,2);
insert into station_sector_cross (stat_id,sect_id) values (2004802,2);
insert into station_sector_cross (stat_id,sect_id) values (2004240,2);
insert into station_sector_cross (stat_id,sect_id) values (2005421,2);
insert into station_sector_cross (stat_id,sect_id) values (2005358,2);
insert into station_sector_cross (stat_id,sect_id) values (2004401,2);
insert into station_sector_cross (stat_id,sect_id) values (2004434,2);
insert into station_sector_cross (stat_id,sect_id) values (2004436,2);
insert into station_sector_cross (stat_id,sect_id) values (2005026,2);

#Станции УП Витебский
insert into station (stat_id,stat_name) values (2007883,'ВИТ ППК');
insert into station (stat_id,stat_name) values (2007803,'С-ПЕТ-ВИТ ККБР');
insert into station (stat_id,stat_name) values (2004003,'С-ПЕТ-ВИТ');
insert into station (stat_id,stat_name) values (2004801,'БОРОВАЯ');
insert into station (stat_id,stat_name) values (2005315,'ВОЗДУХОПЛАВАТЕЛЬНЫЙ ПАРК');
insert into station (stat_id,stat_name) values (2005020,'ПРОСПЕКТ СЛАВЫ');
insert into station (stat_id,stat_name) values (2005001,'КУПЧИНО');
insert into station (stat_id,stat_name) values (2005166,'ШУШАРЫ');
insert into station (stat_id,stat_name) values (2007884,'СЗППК СПб ВИТЕБСКИЙ РЕВИЗОРЫ');


insert into station_sector_cross (stat_id,sect_id) values (2007883,1);
insert into station_sector_cross (stat_id,sect_id) values (2007803,1);
insert into station_sector_cross (stat_id,sect_id) values (2004003,1);
insert into station_sector_cross (stat_id,sect_id) values (2004801,1);
insert into station_sector_cross (stat_id,sect_id) values (2005315,1);
insert into station_sector_cross (stat_id,sect_id) values (2005020,1);
insert into station_sector_cross (stat_id,sect_id) values (2005001,1);
insert into station_sector_cross (stat_id,sect_id) values (2005166,1);
insert into station_sector_cross (stat_id,sect_id) values (2007884,1);


#Абонементы
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (101, 'em01');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (102, 'em02');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (103, 'em03');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (104, 'em04');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (105, 'em05');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (106, 'em06');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (107, 'em12');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (108, 'ed05');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (109, 'ed10');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (110, 'ed15');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (111, 'ed20');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (112, 'ed25');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (301, 'rm01');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (302, 'rm02');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (303, 'rm03');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (304, 'rm04');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (305, 'rm05');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (306, 'rm06');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (307, 'rm12');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (309, 'rd10');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (310, 'rd15');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (311, 'rd20');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (312, 'rd25');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (401, 'dd05');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (402, 'dd06');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (403, 'dd07');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (404, 'dd08');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (405, 'dd09');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (406, 'dd10');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (407, 'dd11');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (408, 'dd12');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (409, 'dd13');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (410, 'dd14');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (411, 'dd15');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (412, 'dd16');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (501, 'dm10');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (502, 'dm20');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (503, 'dm60');
INSERT INTO `temp_ab` (`a_type`, `a_name`) VALUES (504, 'dm90');