USE C01ProjectDB;

INSERT INTO course(course) VALUES ("cscc01");
INSERT INTO course(course) VALUES ("cscd27");
INSERT INTO course(course) VALUES ("stab52");

INSERT INTO question(question, answer, course, qtype) VALUES ("What is the probability that the survey will show a greater percentage of Republican voters in the second state than in the first stateWhat is the probability that the survey will show a greater percentage ?",
 "0.04", 1, 1);
INSERT INTO question(question, answer, course, qtype) VALUES ("question2", "answer2", 1, 2);
INSERT INTO question(question, answer, course, qtype) VALUES ("question3", "answer3", 1, 2);
INSERT INTO question(question, answer, course, qtype) VALUES ("question4", "answer4", 1, 2);
INSERT INTO question(question, answer, course, qtype) VALUES ("question5", "answer5", 1, 1);
INSERT INTO question(question, answer, course, qtype) VALUES ("question6", "answer6", 2, 1);
INSERT INTO question(question, answer, course, qtype) VALUES ("question7", "answer7", 2, 2);
INSERT INTO question(question, answer, course, qtype) VALUES ("question8", "answer8", 3, 2);
INSERT INTO question(question, answer, course, qtype) VALUES ("question9", "answer9", 3, 2);
INSERT INTO question(question, answer, course, qtype) VALUES ("question10", "answer10", 3, 1);

INSERT INTO mc(qid, choice) VALUES (1, "choice 1.1");
INSERT INTO mc(qid, choice) VALUES (1, "choice 1.2");
INSERT INTO mc(qid, choice) VALUES (1, "choice 1.3");
INSERT INTO mc(qid, choice) VALUES (5, "choice 5.1");
INSERT INTO mc(qid, choice) VALUES (5, "choice 5.2");
INSERT INTO mc(qid, choice) VALUES (5, "choice 5.3");
INSERT INTO mc(qid, choice) VALUES (6, "choice 6.1");
INSERT INTO mc(qid, choice) VALUES (6, "choice 6.2");
INSERT INTO mc(qid, choice) VALUES (6, "choice 6.3");
INSERT INTO mc(qid, choice) VALUES (10, "choice 10.1");
INSERT INTO mc(qid, choice) VALUES (10, "choice 10.2");
INSERT INTO mc(qid, choice) VALUES (10, "choice 10.3");


INSERT INTO assignment(aname, cid) VALUES ("a1", 1);
INSERT INTO assignment(aname, cid) VALUES ("a2", 1);
INSERT INTO assignment(aname, cid) VALUES ("a3", 1);
INSERT INTO assignment(aname, cid) VALUES ("a4", 2);
INSERT INTO assignment(aname, cid) VALUES ("a5", 3);

INSERT INTO related_question(aid, qid) VALUES (1, 1);
INSERT INTO related_question(aid, qid) VALUES (1, 5);
INSERT INTO related_question(aid, qid) VALUES (1, 6);
INSERT INTO related_question(aid, qid) VALUES (2, 4);
INSERT INTO related_question(aid, qid) VALUES (2, 5);
INSERT INTO related_question(aid, qid) VALUES (2, 6);
INSERT INTO related_question(aid, qid) VALUES (3, 7);
INSERT INTO related_question(aid, qid) VALUES (4, 8);
INSERT INTO related_question(aid, qid) VALUES (4, 9);
INSERT INTO related_question(aid, qid) VALUES (5, 10);


INSERT INTO users(uname, email, password, cid, type) VALUES("Felicia Tung", "useremail1@mail.utoronto.ca","test", 1, 3);
INSERT INTO users(uname, email, password, cid, type) VALUES("Rene Piperi", "useremail2@mail.utoronto.ca", "test", 1, 3);
INSERT INTO users(uname, email, password, cid, type) VALUES("Adrian Ensan", "useremail3@mail.utoronto.ca", "test", 2, 3);
INSERT INTO users(uname, email, password, cid, type) VALUES("Talha Khatri", "useremail4@mail.utoronto.ca", "test", 3, 3);
INSERT INTO users(uname, email, password, cid, type) VALUES("Thierry Sans", "useremail5@mail.utoronto.ca", "test", 2, 1);
INSERT INTO users(uname, email, password, cid, type) VALUES("Brandon", "useremail6@mail.utoronto.ca", "test", 1, 2);
