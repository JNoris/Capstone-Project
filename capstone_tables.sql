SET ECHO ON

DROP TABLE Transaction_Item;
DROP TABLE Order_Item;
DROP TABLE Comic;
DROP TABLE Item;
DROP TABLE Transaction;
DROP TABLE Refund;
DROP TABLE Orders;
DROP TABLE Type;
DROP TABLE Vendor;

CREATE TABLE Vendor(
	vendorID NUMBER(10),
	vendor_name VARCHAR2(30) CONSTRAINT SYS_VENDORNAME_NN NOT NULL,
	CONSTRAINT SYS_VENDORID_PK PRIMARY KEY(vendorID)
);

CREATE TABLE Type(
	item_type VARCHAR2(10),
	CONSTRAINT SYS_ITEMTYPE_PK PRIMARY KEY(item_type)
);

CREATE TABLE Orders(
	order_no NUMBER(10), 
	CONSTRAINT SYS_ORDERNO_PK PRIMARY KEY(order_no)
);

CREATE TABLE Refund(
	refundID NUMBER(10),
	transactionID NUMBER(10),
	itemID NUMBER(10),
	CONSTRAINT SYS_REFUNDID_PK PRIMARY KEY(refundID)
);

CREATE TABLE Transaction(
	transactionID NUMBER(10),
	transaction_date DATE,
	CONSTRAINT SYS_TRANSACTIONID_PK PRIMARY KEY(transactionID)
);

CREATE TABLE Item(
	itemID NUMBER(10),
	item_type VARCHAR2(10) CONSTRAINT SYS_ITEMTYPE_NN NOT NULL,
	vendorID NUMBER(10) CONSTRAINT SYS_VENDORID_NN NOT NULL,
	description VARCHAR2(50),
	price NUMBER(6,2) CONSTRAINT SYS_ITEMPRICE_NN NOT NULL,
	name VARCHAR2(50) CONSTRAINT SYS_NAME_NN NOT NULL,
	quantity NUMBER(10),
	upc NUMBER(17),
	CONSTRAINT SYS_ITEMID_PK PRIMARY KEY(itemID),
	CONSTRAINT SYS_ITEMTYPE_FK1 FOREIGN KEY(item_type) REFERENCES Type(item_type),
	CONSTRAINT SYS_VENDORTYPE_FK2 FOREIGN KEY(vendorID) REFERENCES Vendor(vendorID)
);

CREATE TABLE Comic(
	itemID NUMBER(10),
	author VARCHAR2(20) CONSTRAINT SYS_AUTHOR_NN NOT NULL,
	volume NUMBER(10) CONSTRAINT SYS_VOLUME_NN NOT NULL,
	isbn VARCHAR2(13) CONSTRAINT SYS_ISBN_NN NOT NULL,
	CONSTRAINT SYS_COMICITEMID_FK FOREIGN KEY(itemID) REFERENCES Item(itemID)
);

CREATE TABLE Order_Item(
	itemID NUMBER(10),
	order_no NUMBER(10) CONSTRAINT SYS_ORDERNO_NN NOT NULL,
	purchase_price NUMBER(6,2) CONSTRAINT SYS_PURCHASEPRICE_NN NOT NULL,
	CONSTRAINT SYS_ORDERITEMID_FK FOREIGN KEY(itemID) REFERENCES Item(itemID),
	CONSTRAINT SYS_ORDERNO_FK FOREIGN KEY(order_no) REFERENCES Orders(order_no)
);

CREATE TABLE Transaction_Item(
	itemID NUMBER(10) CONSTRAINT SYS_ITEMID_NN NOT NULL,
	transactionID NUMBER(10) CONSTRAINT SYS_TRANSACTIONID_NN NOT NULL,
	price NUMBER(6,2) CONSTRAINT SYS_TIPRICE_NN NOT NULL,
	CONSTRAINT SYS_TRANSACTIONITEMID_FK FOREIGN KEY(itemID) REFERENCES Item(itemID),
	CONSTRAINT SYS_TRANSACTIONID_FK FOREIGN KEY(transactionID) REFERENCES Transaction(TransactionID)
);

