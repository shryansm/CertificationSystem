CREATE TABLE USERS(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	USERNAME VARCHAR(20) NOT NULL,
	PASSWORD VARCHAR(20) NOT NULL,
	ROLE INT
);

CREATE TABLE COURSE(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(20) NOT NULL,
	COST INT NOT NULL,
	DATE_CREATED DATE DEFAULT GETDATE(),
	DATE_MODIFIED DATE DEFAULT GETDATE(),
	CREATOR INT,
	FOREIGN KEY (CREATOR) REFERENCES USERS(ID)
);

CREATE TABLE STUDENT(
	ID INT PRIMARY KEY,
	FOREIGN KEY (ID) REFERENCES USERS(ID)
);

CREATE TABLE REGISTRATIONS(
	USER_ID INT,
	COURSE_ID INT,
	DATE_REGISTERED DATE DEFAULT GETDATE(),
	DATE_ATTEMPTED DATE DEFAULT GETDATE(),
	STATUS INT,
	FOREIGN KEY(USER_ID) REFERENCES STUDENT(ID),
	FOREIGN KEY(COURSE_ID) REFERENCES COURSE(ID),
	PRIMARY KEY(USER_ID, COURSE_ID)
);
