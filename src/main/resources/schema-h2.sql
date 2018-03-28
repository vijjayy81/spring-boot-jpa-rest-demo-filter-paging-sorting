DROP schema testdb IF EXISTS;
CREATE SCHEMA testdb AUTHORIZATION SA;


DROP TABLE EMPLOYEE IF EXISTS;

CREATE TABLE EMPLOYEE (
	EMPLOYEE_NO int(11) IDENTITY(1, 1) PRIMARY KEY,
	firstName varchar(100),
	lastName varchar(100),
	gender char(1),
	dob DATE,
	salary int(11),
	joiningDate DATE
);