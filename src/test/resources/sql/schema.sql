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

create table users(
	username varchar(50) not null primary key,
	password varchar(50) not null,
	enabled bit not null
);

CREATE TABLE site_users
(
  username VARCHAR(50) NOT NULL,
  siteId INT NOT NULL,
  PRIMARY KEY (username, siteId),
  FOREIGN KEY (username) REFERENCES users(username),
  FOREIGN KEY (siteId) REFERENCES site(id)
);

create table groups (
	id bigint not null identity(0,1) primary key,
	group_name varchar(50) not null
);

create table group_authorities (
	group_id bigint not null,
	authority varchar(50) not null,
	constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

create table group_members (
	id bigint not null identity(0,1) primary key,
	username varchar(50) not null,
	group_id bigint not null,
	constraint fk_group_members_group foreign key(group_id) references groups(id)
);

CREATE SEQUENCE checkin_seq AS INT
  START WITH 1 INCREMENT BY 1;

CREATE TABLE checkin
(
  id INT NOT NULL,
  qty INT NOT NULL,
  username VARCHAR(50) NOT NULL,
  timestamp DATETIME NOT NULL,
  shopId INT NOT NULL,
  locationId INT NOT NULL,
  productId INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (shopId) REFERENCES shop(id),
  FOREIGN KEY (locationId) REFERENCES location(id),
  FOREIGN KEY (productId) REFERENCES product(id)
);
