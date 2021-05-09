SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS users_deleted;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE users_deleted(
	user_id INT PRIMARY KEY,
	
	CONSTRAINT fk_user_id2
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);