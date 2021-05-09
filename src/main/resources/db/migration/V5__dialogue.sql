SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS dialogue;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE dialogue(
	talk_room_id INT PRIMARY KEY,
	last_talk_index INT NOT NULL,
	
	CONSTRAINT fk_talk_room_id1
	    FOREIGN KEY (talk_room_id) 
	    REFERENCES talk_room (talk_room_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);