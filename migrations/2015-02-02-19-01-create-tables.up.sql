CREATE TABLE players (
	id INT PRIMARY KEY auto_increment,
   	name VARCHAR(255));

CREATE TABLE characters (
	id INT PRIMARY KEY auto_increment,
	name varchar(32) not null,
	allignment varchar(32) not null,
	race varchar(32) not null,
	diety varchar(32),
	gender char,
	total_hitpoints int,
	damage int default 0,
	str int,
	dex int,
	con int,
	"INT" int,
	wis int,
	cha int,
	active boolean default false,
	player int references players(id));

CREATE TABLE classes (
	class varchar(32) not null,
	level int,
	character int references characters(id));

CREATE TABLE skills (
	skill varchar(32) not null,
	rank int default 0,
	trained boolean default false,
	modifier int default 0,
	character int references characters(id));

CREATE TABLE adjustments (
	str int default 0,
	dex int default 0,
	con int default 0,
	"INT" int default 0,
	wis int default 0,
	cha int default 0,
	character int references characters(id));


