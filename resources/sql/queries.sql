--name: get-players
-- select all  players

SELECT * FROM players

--name: get-player
-- gets a player by id
SELECT * FROM players where id = :id

--name: add-player<!
-- adds a player to the database
INSERT INTO players (name) values (:name)

--name: delete-player!
-- deletes a player
DELETE FROM players WHERE id = :id


--name: get-character
-- gets a character by id
SELECT * FROM characters where id = :id

--name: get-characters-for-player
-- gets characters for a player
SELECT * from characters where player = :id

--name: add-character<!
-- add a character to the database

INSERT INTO characters (race, name, alignment, diety, gender, str, dex, con, wis, int, cha, hitpoints, class, player)
  values ( :race, :name, :alignment, :diety, :gender, :int, :cha, :str, :con, :dex, :wis, :hitpoints, :class, :player)



--name: clear-active-for-player!
-- set all active fields to false for a player
UPDATE characters SET active=false WHERE player=:playerid

--name: rest-party!
-- reset damage, spells, and such
UPDATE characters SET damage = 0 WHERE active = true

--name: set-character-active!
-- set the active character
UPDATE characters SET active=true WHERE id=:id

--name: get-active-characters
SELECT * FROM characters WHERE active = true

--name: get-info-for-character-id
SELECT * FROM characters WHERE id = :id
