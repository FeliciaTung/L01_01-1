DROP DATABASE IF EXISTS C01ProjectDB;
CREATE DATABASE C01ProjectDB;
USE C01ProjectDB;

CREATE TABLE IF NOT EXISTS course(
	cid INT AUTO_INCREMENT,
	course VARCHAR(255),
	PRIMARY KEY(cid)
);

CREATE TABLE IF NOT EXISTS users(
	uid INT AUTO_INCREMENT,
	uname VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	cid INT,
	type VARCHAR(255),
	PRIMARY KEY (uid),
	FOREIGN KEY(cid) REFERENCES course(cid)
);
CREATE TABLE IF NOT EXISTS questionType(
	qtid INT AUTO_INCREMENT,
	questionType VARCHAR(255),
	PRIMARY KEY(qtid)
);
CREATE TABLE IF NOT EXISTS question(
	qid INT AUTO_INCREMENT,
	question LONGTEXT,
	answer VARCHAR(255),
	course INT,
	qtype INT,
	PRIMARY KEY(qid),
	FOREIGN KEY(course) REFERENCES course(cid),
	FOREIGN KEY(qtype) REFERENCES questionType(qtid)
);
CREATE TABLE IF NOT EXISTS mc(
	qid INT,
	choice LONGTEXT,
	FOREIGN KEY(qid) REFERENCES question(qid)
);
CREATE TABLE IF NOT EXISTS assignment(
	aid INT AUTO_INCREMENT,
	aname VARCHAR(255),
	cid INT,
	PRIMARY KEY(aid),
	FOREIGN KEY(cid) REFERENCES course(cid)
);

CREATE TABLE IF NOT EXISTS related_question(
	aid INT ,
	qid INT,
	PRIMARY KEY(aid, qid),
	FOREIGN KEY(qid) REFERENCES question(qid),
	FOREIGN KEY(aid) REFERENCES assignment(aid)
);


CREATE TABLE IF NOT EXISTS marks(
	student INT,
	aid INT, 
	cid INT,
	mark INT,
	PRIMARY KEY(student, aid),
	FOREIGN KEY(aid) REFERENCES assignment(aid),
	FOREIGN KEY(cid) REFERENCES course(cid),
	FOREIGN KEY(student) REFERENCES users(uid)
);

INSERT INTO questionType(questionType) VALUE("multiple choice");
INSERT INTO questionType(questionType) VALUE ("short answerwer");