INSERT INTO users VALUES(1);
INSERT INTO users VALUES(2);
INSERT INTO users VALUES(3);
INSERT INTO users VALUES(4);

INSERT INTO users_details VALUES(1,'1','1','1',1);
INSERT INTO users_details VALUES(2,'2','1','1',1);
INSERT INTO users_details VALUES(3,'3','1','1',1);
INSERT INTO users_details VALUES(4,'4','1','1',0);

INSERT INTO talk_room VALUES(1);
INSERT INTO talk_room VALUES(2);
INSERT INTO talk_room VALUES(3);
INSERT INTO talk_room VALUES(4);
INSERT INTO talk_room VALUES(5);
INSERT INTO talk_room VALUES(6);
INSERT INTO talk_room VALUES(7);

INSERT INTO groups VALUES(4,'test_group2',1,0);
INSERT INTO groups VALUES(5,'test_group1',7,1);
INSERT INTO groups VALUES(6,'test_group3',2,1);
INSERT INTO groups VALUES(7,'test_group4',5,1);

INSERT INTO dialogue VALUES(1,0,1);
INSERT INTO dialogue VALUES(2,5,1);
INSERT INTO dialogue VALUES(3,1,1);

INSERT INTO have_user_list VALUES(1,2,1,0);
INSERT INTO have_user_list VALUES(1,4,2,5);
INSERT INTO have_user_list VALUES(2,1,1,0);
INSERT INTO have_user_list VALUES(4,1,2,4);
INSERT INTO have_user_list VALUES(1,3,3,1);

INSERT INTO have_user_desire_list VALUES(3,1,3,1);

INSERT INTO user_in_group VALUES(4,2,1);
INSERT INTO user_in_group VALUES(5,1,2);
INSERT INTO user_in_group VALUES(5,4,5);
INSERT INTO user_in_group VALUES(5,3,7);
INSERT INTO user_in_group VALUES(6,2,2);
INSERT INTO user_in_group VALUES(6,3,2);
INSERT INTO user_in_group VALUES(7,1,5);

INSERT INTO group_desire VALUES(1,4,0);
INSERT INTO group_desire VALUES(1,6,1);
INSERT INTO group_desire VALUES(2,7,3);
INSERT INTO group_desire VALUES(3,7,4);

INSERT INTO talk VALUES(2,1,1,'test1','2020-9-9');
INSERT INTO talk VALUES(2,2,1,'test2','2020-9-9');
INSERT INTO talk VALUES(2,3,4,'test3','2020-9-9');
INSERT INTO talk VALUES(2,4,4,'test4','2020-9-9');
INSERT INTO talk VALUES(2,5,1,'test5','2020-9-9');
INSERT INTO talk VALUES(3,1,1,'test1','2020-9-9');
INSERT INTO talk VALUES(4,1,2,'test1','2020-9-9');
INSERT INTO talk VALUES(5,1,1,'test1','2020-9-9');
INSERT INTO talk VALUES(5,2,4,'test2','2020-9-9');
INSERT INTO talk VALUES(5,3,4,'test3','2020-9-9');
INSERT INTO talk VALUES(5,4,3,'test4','2020-9-9');
INSERT INTO talk VALUES(5,5,3,'test5','2020-9-9');
INSERT INTO talk VALUES(5,6,3,'test6','2020-9-9');
INSERT INTO talk VALUES(5,7,3,'test7','2020-9-9');
INSERT INTO talk VALUES(6,1,3,'test1','2020-9-9');
INSERT INTO talk VALUES(6,2,3,'test2','2020-9-9');
INSERT INTO talk VALUES(7,1,1,'test1','2020-9-9');
INSERT INTO talk VALUES(7,2,1,'test2','2020-9-9');
INSERT INTO talk VALUES(7,3,1,'test3','2020-9-9');
INSERT INTO talk VALUES(7,4,1,'test4','2020-9-9');
INSERT INTO talk VALUES(7,5,1,'test5','2020-9-9');