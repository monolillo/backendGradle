INSERT INTO PLACES (id,namee) VALUES (1,'Rojas');
INSERT INTO PLACES (id,namee) VALUES (2,'Mooresville');
insert into company(id, name) values (1, 'HD Supply');
insert into site(id, name, companyId) values (2,'Courtyard by Marriott Atlanta Cumberland/Galleria',1);
insert into product(id, name, itemNumber) values (1, 'A Bulb 40W A15 Frost', 307692);
insert into product(id, name, itemNumber) values (2, 'T8 Bulb 40W Intermediate', 305682);
insert into catalog(critical, min, max, productId, siteId) values (false, 5, 30, 1, 2);
insert into catalog(critical, min, max, productId, siteId) values (false, 5, 30, 2, 2);