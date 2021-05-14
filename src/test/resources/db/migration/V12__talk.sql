SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS talk;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE talk(
	talk_room_id INT,
	talk_index INT,
	user_id INT,
	content VARCHAR(2000) NOT NULL,
	timestamp DATETIME NOT NULL COMMENT 'Timestamp when the talk table was made.',
	
	PRIMARY KEY(
		talk_room_id,
		talk_index
	),
	
	CONSTRAINT fk_talk_room_id7
	    FOREIGN KEY (talk_room_id) 
	    REFERENCES talk_room (talk_room_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	
	CONSTRAINT fk_user_id9
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);