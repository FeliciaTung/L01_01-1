# This script is for testing purpose. Please initialize database using this script before running tests.

DROP DATABASE IF EXISTS C01ProjectDB;
CREATE DATABASE C01ProjectDB;
USE C01ProjectDB;

CREATE TABLE IF NOT EXISTS course(
	cid INT AUTO_INCREMENT,
	course VARCHAR(255),
	PRIMARY KEY(cid)
);

CREATE TABLE IF NOT EXISTS user_type(
    typeid INT AUTO_INCREMENT,
    user_type VARCHAR(255) NOT NULL,
    PRIMARY KEY (typeid)
);

CREATE TABLE IF NOT EXISTS users(
	uid INT AUTO_INCREMENT,
	uname VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	cid INT,
	type INT,
	PRIMARY KEY (uid),
	FOREIGN KEY(cid) REFERENCES course(cid) ON DELETE CASCADE,
	FOREIGN KEY(type) REFERENCES user_type(typeid) 

);


CREATE TABLE IF NOT EXISTS question_type(
	qtid INT AUTO_INCREMENT,
	question_type VARCHAR(255),
	PRIMARY KEY(qtid)
);
CREATE TABLE IF NOT EXISTS question(
	qid INT AUTO_INCREMENT,
	question LONGTEXT,
	answer VARCHAR(255),
	course INT,
	qtype INT,
	PRIMARY KEY(qid),
	FOREIGN KEY(course) REFERENCES course(cid) ON DELETE CASCADE,
	FOREIGN KEY(qtype) REFERENCES question_type(qtid)
);
CREATE TABLE IF NOT EXISTS mc(
	qid INT,
	choice LONGTEXT,
	FOREIGN KEY(qid) REFERENCES question(qid) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS assignment(
	aid INT AUTO_INCREMENT,
	aname VARCHAR(255),
	cid INT,
	due_date DATETIME,
	PRIMARY KEY(aid),
	FOREIGN KEY(cid) REFERENCES course(cid) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS related_question(
	aid INT ,
	qid INT,
	PRIMARY KEY(aid, qid),
	FOREIGN KEY(qid) REFERENCES question(qid) ON DELETE CASCADE,
	FOREIGN KEY(aid) REFERENCES assignment(aid) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS marks(
	student INT,
	aid INT, 
	cid INT,
	mark INT,
	PRIMARY KEY(student, aid),
	FOREIGN KEY(aid) REFERENCES assignment(aid) ON DELETE CASCADE,
	FOREIGN KEY(cid) REFERENCES course(cid) ON DELETE CASCADE,
	FOREIGN KEY(student) REFERENCES users(uid) ON DELETE CASCADE
);

INSERT INTO question_type(question_type) VALUE("multiple choice");
INSERT INTO question_type(question_type) VALUE ("short answerwer");

INSERT INTO user_type(user_type) VALUE("instructor");
INSERT INTO user_type(user_type) VALUE("ta");
INSERT INTO user_type(user_type) VALUE("student");

INSERT INTO course(course) VALUES ("cscc01");
INSERT INTO course(course) VALUES ("cscd27");
INSERT INTO course(course) VALUES ("stab52");
