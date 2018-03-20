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

INSERT INTO PRODUCT (id,name,itemNumber) VALUES (1,'A Bulb 40W A15 Frost',307692);
INSERT INTO PRODUCT (id,name,itemNumber) VALUES (2,'A Bulb 40W A15 Clear',307693);
INSERT INTO PRODUCT (id,name,itemNumber) VALUES (3,'T8 Bulb 40W Intermediate',305682);
INSERT INTO PRODUCT (id,name,itemNumber) VALUES (4,'A Bulb 25W A15 Frost 130V',327825);
INSERT INTO PRODUCT (id,name,itemNumber) VALUES (5,'Battery Smoke Alarm',307193);
INSERT INTO PRODUCT (id,name,itemNumber) VALUES (6,'HP Disposer 1-Year Warranty',317692);

INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,30,1,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,5,30,2,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,2,15,3,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (1,10,20,5,2);
INSERT INTO CATALOG (critical,min,max,productId,siteId) VALUES (0,3,10,6,2);

INSERT INTO INVENTORY (qty,checkedOutQty,shopId,productId,locationId) VALUES (10,0,2,1,2);
INSERT INTO INVENTORY (qty,checkedOutQty,shopId,productId,locationId) VALUES (4,0,2,2,3);
INSERT INTO INVENTORY (qty,checkedOutQty,shopId,productId,locationId) VALUES (2,0,3,5,6);
