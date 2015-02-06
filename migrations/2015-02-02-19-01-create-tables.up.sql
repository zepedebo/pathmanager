CREATE TABLE players (
	id INT PRIMARY KEY auto_increment,
   	name VARCHAR(255));

CREATE TABLE characters (
	id INT PRIMARY KEY auto_increment,
	name varchar(32),
	str int,
	dex int,
	con int,
	"INT" int,
	wis int,
	cha int,
	active boolean default false,
	player int references players(id))