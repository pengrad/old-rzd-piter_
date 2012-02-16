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

#Абонементы
INSERT INTO `temp_ab` (`a_type`) VALUES (101);
INSERT INTO `temp_ab` (`a_type`) VALUES (102);
INSERT INTO `temp_ab` (`a_type`) VALUES (103);
INSERT INTO `temp_ab` (`a_type`) VALUES (104);
INSERT INTO `temp_ab` (`a_type`) VALUES (105);
INSERT INTO `temp_ab` (`a_type`) VALUES (106);
INSERT INTO `temp_ab` (`a_type`) VALUES (107);
INSERT INTO `temp_ab` (`a_type`) VALUES (108);
INSERT INTO `temp_ab` (`a_type`) VALUES (109);
INSERT INTO `temp_ab` (`a_type`) VALUES (110);
INSERT INTO `temp_ab` (`a_type`) VALUES (111);
INSERT INTO `temp_ab` (`a_type`) VALUES (112);
INSERT INTO `temp_ab` (`a_type`) VALUES (301);
INSERT INTO `temp_ab` (`a_type`) VALUES (302);
INSERT INTO `temp_ab` (`a_type`) VALUES (303);
INSERT INTO `temp_ab` (`a_type`) VALUES (304);
INSERT INTO `temp_ab` (`a_type`) VALUES (305);
INSERT INTO `temp_ab` (`a_type`) VALUES (306);
INSERT INTO `temp_ab` (`a_type`) VALUES (307);
INSERT INTO `temp_ab` (`a_type`) VALUES (309);
INSERT INTO `temp_ab` (`a_type`) VALUES (310);
INSERT INTO `temp_ab` (`a_type`) VALUES (311);
INSERT INTO `temp_ab` (`a_type`) VALUES (312);
INSERT INTO `temp_ab` (`a_type`) VALUES (401);
INSERT INTO `temp_ab` (`a_type`) VALUES (402);
INSERT INTO `temp_ab` (`a_type`) VALUES (403);
INSERT INTO `temp_ab` (`a_type`) VALUES (404);
INSERT INTO `temp_ab` (`a_type`) VALUES (405);
INSERT INTO `temp_ab` (`a_type`) VALUES (406);
INSERT INTO `temp_ab` (`a_type`) VALUES (407);
INSERT INTO `temp_ab` (`a_type`) VALUES (408);
INSERT INTO `temp_ab` (`a_type`) VALUES (409);
INSERT INTO `temp_ab` (`a_type`) VALUES (410);
INSERT INTO `temp_ab` (`a_type`) VALUES (411);
INSERT INTO `temp_ab` (`a_type`) VALUES (412);
INSERT INTO `temp_ab` (`a_type`) VALUES (501);
INSERT INTO `temp_ab` (`a_type`) VALUES (502);
INSERT INTO `temp_ab` (`a_type`) VALUES (503);
INSERT INTO `temp_ab` (`a_type`) VALUES (504);