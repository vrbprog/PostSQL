CREATE DATABASE airport;

CREATE TABLE model(
	id serial PRIMARY KEY,
	model_name varchar(128) NOT NULL,
	num_seats smallint NOT NULL,
	num_pilots smallint NOT NULL
);

INSERT INTO model(model_name, num_seats,num_pilots) VALUES
('Boing 747',467,2),
('Airbus A320',186,2);

CREATE TABLE airpark(
	id serial PRIMARY KEY,
	sernum varchar(128) NOT NULL,
	model_id int REFERENCES model(id)
);

INSERT INTO airpark(sernum,model_id) VALUES
('B747-237632-0816',1),
('B747-477639-0318',1),
('A320-983473-0717',2),
('A320-385422-0219',2),
('A320-383491-0120',2);

CREATE TABLE pilot(
	id serial PRIMARY KEY,
	name varchar(32) NOT NULL,
	age smallint NOT NULL,
	driving smallint NOT NULL
);

INSERT INTO pilot(name,age,driving) VALUES
('Michael',45,2),
('Richard',29,2),
('John',34,1),
('Steven',37,1),
('Brian',31,1),
('Kevin',42,1),
('Andrew',38,1),
('Bruce',35,1);

CREATE TABLE pilot_driving(
	id serial PRIMARY KEY,
	pilot_id int REFERENCES pilot(id),
	model_id int REFERENCES model(id)
);

INSERT INTO pilot_driving(pilot_id,model_id) VALUES
(1,1),
(1,2),
(2,1),
(2,2),
(3,1),
(4,2),
(5,2),
(6,2),
(7,2),
(8,2);
