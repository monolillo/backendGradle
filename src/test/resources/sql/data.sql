INSERT INTO PLACES (id,namee) VALUES (1,'Rojas');
INSERT INTO PLACES (id,namee) VALUES (2,'Mooresville');

INSERT INTO COMPANY (id,name) VALUES (1,'HD Supply');
INSERT INTO COMPANY (id,name) VALUES (2,'Marriot');

INSERT INTO SITE (id,name,companyId) VALUES (1,'Headquarters',1);
INSERT INTO SITE (id,name,companyId) VALUES (2,'Courtyard by Marriott Atlanta Cumberland/Galleria',2);

INSERT INTO SHOP (id,name,siteId) VALUES (2,'Maintenance',2);
INSERT INTO SHOP (id,name,siteId) VALUES (3,'Storage',2);

INSERT INTO LOCATION (id,name,shopId) VALUES (2,'A1',2);
INSERT INTO LOCATION (id,name,shopId) VALUES (3,'A2',2);
INSERT INTO LOCATION (id,name,shopId) VALUES (4,'B1',2);
INSERT INTO LOCATION (id,name,shopId) VALUES (5,'B2',2);
INSERT INTO LOCATION (id,name,shopId) VALUES (6,'ROW1',3);
INSERT INTO LOCATION (id,name,shopId) VALUES (7,'ROW2',3);

INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (1,'A Bulb 40W A15 Frost',307692,12.35,'/img/item-image-lightbulb.png');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (2,'A Bulb 40W A15 Clear',307693,11.35,'/img/item-image-lightbulb.png');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (3,'T8 Bulb 40W Intermediate',305682,10.35,'/img/item-image-lightbulb.png');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (4,'A Bulb 25W A15 Frost 130V',327825,9.35,'/img/item-image-lightbulb.png');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (5,'Battery Smoke Alarm',307193,5.50,'/img/item-image-lightbulb.png');
INSERT INTO PRODUCT (id,name,itemNumber,price,imageUrl) VALUES (6,'HP Disposer 1-Year Warranty',317692,40.25,'/img/item-image-lightbulb.png');

INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,30,1,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,30,2,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,2,15,3,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,10,20,5,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,3,10,6,2);

INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (10,2,1,2);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (4,2,2,3);
INSERT INTO INVENTORY (qty,shopId,productId,locationId) VALUES (2,3,5,6);

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

INSERT INTO GROUP_MEMBERS (username,group_id) VALUES ('admin',0);
INSERT INTO GROUP_MEMBERS (username,group_id) VALUES ('admin',1);
INSERT INTO GROUP_MEMBERS (username,group_id) VALUES ('mgr_usr',2);
INSERT INTO GROUP_MEMBERS (username,group_id) VALUES ('superv_usr',3);
INSERT INTO GROUP_MEMBERS (username,group_id) VALUES ('tech_usr',4);

INSERT INTO CHECKIN (id, qty, username, timestamp, shopId, locationId, productId) VALUES (5,5,'admin','2018-04-03 17:45:55.38',2,2,1);