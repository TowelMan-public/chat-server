SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS groups;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE groups(
	talk_room_id INT PRIMARY KEY,
	group_name VARCHAR(100) NOT NULL,
	last_talk_index INT NOT NULL,
	is_enabled TINYINT(1) DEFAULT 1,
	
	CONSTRAINT fk_talk_room_id2
	    FOREIGN KEY (talk_room_id) 
	    REFERENCES talk_room (talk_room_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);