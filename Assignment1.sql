DROP DATABASE IF EXISTS javaAssignment1;
CREATE DATABASE javaAssignment1;

USE javaAssignment1;

CREATE TABLE contacts
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(30),
    birthday DATE,
    address VARCHAR(50),
    phone VARCHAR(12),
    imageFile VARCHAR(100)
);

INSERT INTO contacts (first_name, last_name, birthday, address, phone, imageFile) VALUES
('Dylan', 'Sprague', '1998-09-19', '26 Twinkle Lane, Halifax', '902-385-7217', 'DylanSprague.jpg'),
('Joe', 'Bang', '1976-04-23', '226 Camel Dr, Lambville', '822-472-1859', 'Default.jpg'),
('Alec', 'Mason', '1996-11-23', '4 Jones St, Kelljin', '473-583-2955', 'Default.jpg');