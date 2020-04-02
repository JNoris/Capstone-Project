/*insert into Order (order_no)
	values (0000);*/
insert into Orders (order_no)
	values (0001);
insert into Orders (order_no)
	values (0002);
insert into Orders (order_no)
	values (0003);
insert into Orders (order_no)
	values (0004);
insert into Orders (order_no)
	values (0005);
insert into Orders (order_no)
	values (0006);
insert into Orders (order_no)
	values (0007);
insert into Orders (order_no)
	values (0008);
									
/*insert into Orders (order_no)
	values (0000,2020-01-19);*/
insert into Orders (order_no, orderDate, arrivalDate, VendorID)
	values (0001,STR_TO_DATE('2019-12-10','%Y-%m-%d'),STR_TO_DATE('2020-01-10', '%Y-%m-%d'), 1);
insert into Orders (order_no, orderDate, arrivalDate, VendorID)
	values (0002,STR_TO_DATE('2019-12-10','%Y-%m-%d'),STR_TO_DATE('2019-01-10', '%Y-%m-%d'), 2);
insert into Orders (order_no, orderDate, arrivalDate, VendorID)
	values (0003,STR_TO_DATE('2019-12-08','%Y-%m-%d'),STR_TO_DATE('2019-01-12', '%Y-%m-%d'), 3);
insert into Orders (order_no, orderDate, arrivalDate, VendorID)
	values (0004,STR_TO_DATE('2019-12-07','%Y-%m-%d'),STR_TO_DATE('2019-01-12', '%Y-%m-%d'), 4);
insert into Orders (order_no, orderDate, arrivalDate, VendorID)
	values (0005,STR_TO_DATE('2019-12-05','%Y-%m-%d'),STR_TO_DATE('2019-01-12', '%Y-%m-%d'), 5);
insert into Orders (order_no, orderDate, arrivalDate, VendorID)
	values (0006,STR_TO_DATE('2019-12-05','%Y-%m-%d'),STR_TO_DATE('2019-01-15', '%Y-%m-%d'), 6);
insert into Orders (order_no, orderDate, arrivalDate, VendorID)
	values (0007,STR_TO_DATE('2019-12-03','%Y-%m-%d'),STR_TO_DATE('2019-01-15', '%Y-%m-%d'), 7);
insert into Orders (order_no, orderDate, arrivalDate, VendorID)
	values (0008,STR_TO_DATE('2019-12-01','%Y-%m-%d'),STR_TO_DATE('2019-01-15', '%Y-%m-%d'), 8);
