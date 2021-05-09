SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS talk_room_deleted;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE talk_room_deleted(
	talk_room_id INT PRIMARY KEY,
	
	CONSTRAINT fk_talk_room_id
	    FOREIGN KEY (talk_room_id) 
	    REFERENCES talk_room (talk_room_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);