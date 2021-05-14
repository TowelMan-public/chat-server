SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS users_details;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE users_details(
	user_id INT PRIMARY KEY,
	user_id_name VARCHAR(100) NOT NULL UNIQUE,
	user_name VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	is_enabled TINYINT(1) DEFAULT 1,
	
	CONSTRAINT fk_user_id1
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);