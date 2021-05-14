SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS user_in_group;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE user_in_group(
	talk_room_id INT,
	user_id INT,
	last_talk_index INT NOT NULL,
	
	PRIMARY KEY(
		talk_room_id,
		user_id
	),
	
	CONSTRAINT fk_user_id3
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_talk_room_id3
	    FOREIGN KEY (talk_room_id) 
	    REFERENCES talk_room (talk_room_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);