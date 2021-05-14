SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS group_desire;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE group_desire(
	user_id INT,
	talk_room_id INT,
	last_talk_index INT NOT NULL,
	
	PRIMARY KEY(
		user_id,
		talk_room_id
	),
	
	CONSTRAINT fk_user_id8
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_talk_room_id6
	    FOREIGN KEY (talk_room_id) 
	    REFERENCES talk_room (talk_room_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);