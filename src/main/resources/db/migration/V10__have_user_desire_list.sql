SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS have_user_desire_list;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE have_user_desire_list(
	user_id INT,
	have_user_id INT,
	talk_room_id INT,
	last_talk_index INT NOT NULL,
	
	PRIMARY KEY(
		user_id,
		have_user_id
	),
	
	CONSTRAINT fk_user_id6
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_user_id7
	    FOREIGN KEY (have_user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_talk_room_id5
	    FOREIGN KEY (talk_room_id) 
	    REFERENCES talk_room (talk_room_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);