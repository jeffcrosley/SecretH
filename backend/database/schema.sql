BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS users_game;

CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user')
);

CREATE TABLE game
(
	game_id serial PRIMARY KEY,
	name varchar(64) NOT NULL,
	number_of_players int NOT NULL,
	sheep_policies int NOT NULL default(0),
	wolf_policies int NOT NULL default(0),
  president varchar(255),
  chancellor varchar(255)
);

CREATE TABLE users_game
(
  user_id int not null,
  game_id int not null,
  secret_role varchar(15),
  alive boolean not null default(true),
  
  constraint fk_users_game_users foreign key (user_id) references users (id),
  constraint fk_users_game_game foreign key (game_id) references game (game_id)
);

COMMIT TRANSACTION;
