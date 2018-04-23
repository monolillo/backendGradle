INSERT INTO COMPANY (id,name) VALUES (1,'HD Supply');
INSERT INTO COMPANY (id,name) VALUES (2,'Marriot');
INSERT INTO COMPANY (id, name) VALUES (3,'Howard Johnson');

INSERT INTO SITE (id,name,companyId) VALUES (1,'Headquarters',1);
INSERT INTO SITE (id,name,companyId) VALUES (2,'Courtyard by Marriott Atlanta Cumberland/Galleria',2);
INSERT INTO SITE (id, name, companyId) VALUES (3,'Rojas',3);

INSERT INTO SHOP (id,name,siteId) VALUES (2,'Maintenance',2);
INSERT INTO SHOP (id,name,siteId) VALUES (3,'Storage',2);
INSERT INTO SHOP (id, name, siteId) VALUES (4,'Storage',3);

INSERT INTO LOCATION (id,name,shopId) VALUES (2,'A1',2);
INSERT INTO LOCATION (id,name,shopId) VALUES (3,'A2',2);
INSERT INTO LOCATION (id,name,shopId) VALUES (4,'B1',2);
INSERT INTO LOCATION (id,name,shopId) VALUES (5,'B2',2);
INSERT INTO LOCATION (id,name,shopId) VALUES (6,'ROW1',3);
INSERT INTO LOCATION (id,name,shopId) VALUES (7,'ROW2',3);
INSERT INTO LOCATION (id,name,shopId) VALUES (8,'Location 1',4);
INSERT INTO LOCATION (id,name,shopId) VALUES (9,'Location 2',4);

INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1,'A Bulb 40W A15 Frost',307692,12.35,'/img/item-image-lightbulb.png');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2,'A Bulb 40W A15 Clear',307693,11.35,'/img/item-image-lightbulb.png');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (3,'T8 Bulb 40W Intermediate',305682,10.35,'/img/item-image-lightbulb.png');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (4,'A Bulb 25W A15 Frost 130V',327825,9.35,'/img/item-image-lightbulb.png');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (5,'Battery Smoke Alarm',307193,5.50,'/img/item-image-lightbulb.png');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (6,'HP Disposer 1-Year Warranty',317692,40.25,'/img/item-image-lightbulb.png');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1090,'Avery Door Hangers With Tear Away Cards, 2 Cards P',186335,15,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1091,'Echo 2.6 Ounce 50-1 2-Cycle Engine Oil With Fuel S',130946,19,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1092,'Poulan Pro SAE 30 4-Cycle Engine Oil',130254,6,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1093,'Mi-T-M 16 Ounce Pressure Washer Pump Oil',131088,5,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p'); 
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1094,'11 Oz Liquid Wrench Industrial Chain Lubricant',111593,6,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1095,'Tanaka Perfect Mix 16 Oz 2-Cycle Engine Oil With F',130818,7,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1096,'12" Flexible Grease Gun Hose',111265,8,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1097,'10.25 Oz Liquid Wrench White Lithium Grease With C',111255,6,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1098,'14 Ounce High Temperature Grease Cartridge',111267,9,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1099,'14 Oz Multipurpose Grease Cartridge',111364,4,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2000,'Certified Safety 4-Shelf Class B First Aid Cabinet',113644,253,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2001,'Medifirst® Zika Outdoor Protection, Package Of 2 K',112286,160,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2002,'Legionella Bacterium Water Testing Kit',112287,440,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2003,'2 Oz Burn Spray',132064,5.99,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2004,'200 Mg Medi-First Ibuprofen 2 Tablets, Box Of 250',132075,14,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2005,'Medi-First Antacid Box Of 50',132078,9,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2006,'Medi-First Cherry Cough Drops Box Of 125',132079,8,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2007,'Triple Antibiotic Ointment Package Of 25',132067,4,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2008,'Burn Aid Burn Gel Package Of 25',132068,15,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2009,'1/2 Oz Eyedrops',132069,4,'https://hdsupplysolutions.com/wcsstore/ThdsMroUs/p');

INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,30,1,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,30,2,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,2,15,3,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,10,20,5,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,3,10,6,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,5,10,1090,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,5,10,1091,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,5,10,1092,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,5,10,1093,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,5,10,1094,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,5,10,1095,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,5,10,1096,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,5,10,1097,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,5,10,1098,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,5,10,1099,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,10,2000,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,10,2001,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,10,2002,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,10,2003,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,10,2004,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,10,2005,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,10,2006,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,10,2007,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,10,2008,3);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,10,2009,3);

INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (10,2,1,2);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (4,2,2,3);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (2,3,5,6);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (15,3,6,7);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (0,4,1090,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (0,4,1091,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (2,4,1092,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (3,4,1093,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (5,4,1094,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (5,4,1095,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (7,4,1096,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (8,4,1097,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (12,4,1098,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (13,4,1099,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (0,4,2000,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (0,4,2001,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (2,4,2002,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (3,4,2003,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (5,4,2004,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (5,4,2005,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (7,4,2006,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (8,4,2007,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (12,4,2008,8);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (13,4,2009,8);

INSERT INTO USERS (username,password,enabled) VALUES ('admin','{noop}admin_pwd',1);
INSERT INTO USERS (username,password,enabled) VALUES ('mgr_usr','{noop}pass',1);
INSERT INTO USERS (username,password,enabled) VALUES ('tech_usr','{noop}pass',1);
INSERT INTO USERS (username,password,enabled) VALUES ('superv_usr','{noop}pass',1);

INSERT INTO SITE_USERS (username,siteId) VALUES ('admin',2);
INSERT INTO SITE_USERS (username,siteId) VALUES ('mgr_usr',2);
INSERT INTO SITE_USERS (username,siteId) VALUES ('superv_usr',2);
INSERT INTO SITE_USERS (username,siteId) VALUES ('tech_usr',2);

INSERT INTO GROUPS (group_name) VALUES ('SITE_ADMIN');
INSERT INTO GROUPS (group_name) VALUES ('ADMIN');
INSERT INTO GROUPS (group_name) VALUES ('PROPERTY_MGR');
INSERT INTO GROUPS (group_name) VALUES ('MNT_SUPERVISOR');
INSERT INTO GROUPS (group_name) VALUES ('MNT_TECH');

INSERT INTO GROUP_AUTHORITIES (group_id,authority) VALUES (0,'GET_PLACES');
INSERT INTO GROUP_AUTHORITIES (group_id,authority) VALUES (0,'READ_SITE_CATALOG');
INSERT INTO GROUP_AUTHORITIES (group_id,authority) VALUES (1,'READ_SITE_CATALOG');
INSERT INTO GROUP_AUTHORITIES (group_id,authority) VALUES (2,'READ_SITE_CATALOG');
INSERT INTO GROUP_AUTHORITIES (group_id,authority) VALUES (3,'READ_SITE_CATALOG');
INSERT INTO GROUP_AUTHORITIES (group_id,authority) VALUES (4,'READ_SITE_CATALOG');
INSERT INTO GROUP_AUTHORITIES (group_id,authority) VALUES (0,'CHECK_IN_PRODUCT');
INSERT INTO GROUP_AUTHORITIES (group_id,authority) VALUES (0,'CHECK_OUT_PRODUCT');
INSERT INTO GROUP_AUTHORITIES (group_id,authority) VALUES (0,'UNDO_CHECK_IN');
INSERT INTO GROUP_AUTHORITIES (group_id,authority) VALUES (0,'UNDO_CHECK_OUT');
 
INSERT INTO GROUP_MEMBERS (username,group_id) VALUES ('admin',0);
INSERT INTO GROUP_MEMBERS (username,group_id) VALUES ('admin',1);
INSERT INTO GROUP_MEMBERS (username,group_id) VALUES ('mgr_usr',2);
INSERT INTO GROUP_MEMBERS (username,group_id) VALUES ('superv_usr',3);
INSERT INTO GROUP_MEMBERS (username,group_id) VALUES ('tech_usr',4);

INSERT INTO CHECKIN (id, qty, username, timestamp, shopId, locationId, productId) VALUES (5,5,'admin','2018-04-03 17:45:55.38',2,2,1);
INSERT INTO CHECKIN (id, qty, username, timestamp, shopId, locationId, productId) VALUES (6,5,'admin','2018-04-03 17:45:55.38',2,2,1);
INSERT INTO CHECKIN (id, qty, username, timestamp, shopId, locationId, productId) VALUES (7,1,'admin','2018-04-03 17:45:55.38',3,2,5);

INSERT INTO CHECKOUT (id, qty, username, timestamp, shopId, locationId, productId) VALUES (5,5,'admin','2018-04-03 17:45:55.38',2,2,2);
INSERT INTO CHECKOUT (id, qty, username, timestamp, shopId, locationId, productId) VALUES (6,5,'admin','2018-04-03 17:45:55.38',2,2,1);
INSERT INTO CHECKOUT (id, qty, username, timestamp, shopId, locationId, productId) VALUES (7,2,'admin','2018-04-03 17:45:55.38',3,7,6);