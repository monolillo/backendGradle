CREATE TABLE PLACES (
    id int,
    namee varchar(255)
);

CREATE TABLE product
(
  id INT NOT NULL,
  name VARCHAR(256) NOT NULL,
  itemNumber INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE company
(
  id INT NOT NULL,
  name VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE notification_type
(
  id INT NOT NULL,
  name VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users
(
  id INT NOT NULL,
  firstName VARCHAR(256) NOT NULL,
  lastName VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE site
(
  id INT NOT NULL,
  name VARCHAR(256) NOT NULL,
  companyId INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (companyId) REFERENCES company(id)
);

CREATE TABLE notification
(
  id INT NOT NULL,
  timestamp DATETIME NOT NULL,
  notificationQty INT NOT NULL,
  active BIT NOT NULL,
  siteId INT NOT NULL,
  typeId INT NOT NULL,
  productId INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (siteId) REFERENCES site(id),
  FOREIGN KEY (typeId) REFERENCES notification_type(id),
  FOREIGN KEY (productId) REFERENCES product(id)
);

CREATE TABLE site_users
(
  userId INT NOT NULL,
  siteId INT NOT NULL,
  PRIMARY KEY (userId, siteId),
  FOREIGN KEY (userId) REFERENCES users(id),
  FOREIGN KEY (siteId) REFERENCES site(id)
);

CREATE TABLE shop
(
  id INT NOT NULL,
  name VARCHAR(256) NOT NULL,
  siteId INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (siteId) REFERENCES site(id)
);

CREATE TABLE catalog
(
  critical BIT NOT NULL,
  min INT NOT NULL,
  max INT NOT NULL,
  productId INT NOT NULL,
  siteId INT NOT NULL,
  PRIMARY KEY (productId, siteId),
  FOREIGN KEY (productId) REFERENCES product(id),
  FOREIGN KEY (siteId) REFERENCES site(id)
);

CREATE TABLE location
(
  id INT NOT NULL,
  name VARCHAR(256) NOT NULL,
  shopId INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (shopId) REFERENCES shop(id)
);

CREATE TABLE inventory
(
  qty INT NOT NULL,
  checkedOutQty INT NOT NULL,
  shopId INT NOT NULL,
  productId INT NOT NULL,
  locationId INT NOT NULL,
  PRIMARY KEY (shopId, productId),
  FOREIGN KEY (shopId) REFERENCES shop(id),
  FOREIGN KEY (productId) REFERENCES product(id),
  FOREIGN KEY (locationId) REFERENCES location(id)
);
