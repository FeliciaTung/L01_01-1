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

INSERT INTO mc(qid, choice) VALUES (1, "choice 5.1");
INSERT INTO mc(qid, choice) VALUES (1, "choice 5.2");
INSERT INTO mc(qid, choice) VALUES (1, "choice 5.3");
INSERT INTO mc(qid, choice) VALUES (6, "choice 6.1");
INSERT INTO mc(qid, choice) VALUES (6, "choice 6.2");
INSERT INTO mc(qid, choice) VALUES (6, "choice 6.3");


INSERT INTO assignment(aname, cid) VALUES ("a1", 1);
INSERT INTO assignment(aname, cid) VALUES ("a2", 1);
INSERT INTO assignment(aname, cid) VALUES ("a3", 1);

INSERT INTO related_question(aid, qid) VALUES (1, 1);
INSERT INTO related_question(aid, qid) VALUES (1, 2);
INSERT INTO related_question(aid, qid) VALUES (1, 3);
INSERT INTO related_question(aid, qid) VALUES (2, 4);
INSERT INTO related_question(aid, qid) VALUES (2, 5);