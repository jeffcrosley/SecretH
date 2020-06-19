------------------------------
-- USERS
------------------------------

-- Password for admin user is 'greatwall'
INSERT INTO users ("username", "password", "salt", "role") VALUES
('jeffcrosley',
'FjZDm+sndmsdEDwNtfr6NA==',
'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=',
'admin');

-- Password for all base users is 'martha'
INSERT INTO users ("username", "password", "salt", "role") VALUES
('Superman',
'WqN0hUN+Kc07qa1w7Faq5g==',
'K8TwXYMKMle2heOvEcx7EI3loqwK5k5vA+ePLTCsV8mgPIBDxcKtdrtalf8xGWB3yvdvwarEJO1HCCTHU9SdK2jMlPYfPhtHe8bhaeGmkuwuwyQY817KuHi/jJeNrbWL12uleZw8s9D8E5naM2XGTfu7MAfiqpf+7lgdk8dqGNE=',
'user');

INSERT INTO users ("username", "password", "salt", "role") VALUES
('Batman',
'8nunoEiu2SLjvhwPnZgTCw==',
'HW9u1UTxJrWPjfSktXtCWs1RjnlpDaF9RvN/RYe95jyFkozxDY8dvBTWyGi+Bvn01Qji1NkDf0eGpy/4U+V595AfyZyOf90Ix65VzJWIJjISHsjQglZVgjslJMgczKz3ebMQvCCV9sYN5ZHwQcwmSyG8abjYTf4USRoioRkoZrs=',
'user');

INSERT INTO users ("username", "password", "salt", "role") VALUES
('Aquaman',
'Rlsof6DM8lQ4K4QxkNbfKg==',
'y3XKS2WSiNXToihhOAn9+QCINssarmnqi3pdxjFeilorhPebfW8WoFF25ej7+SLgnSN2zyn2X9NA0wkasMgVnZmp6ftJzlGcoukU4OrCc18SfvXgzcqVhz1faqMp1B0Yt5mu9HEAmPI+dsaf9KvZjDloRjEi0Z+4ooPjuPXiZ/U=',
'user');

INSERT INTO users ("username", "password", "salt", "role") VALUES
('Kanye',
'ihzSXcoD8FJTx4bPCvhPMA==',
'1NFDayh/28/WIUTXTtNP9FBsYu3XyKoknXIovxTa0th6TN3AMui6zARmMpGTwJcxQOowOznF5Q/P2rafodL+bzQi6SrjtzvMmcDJ9VVn+izjdKVZx5AXoDBMOuyR+ONb1PFq8VyPjGvY/QpaQHOLGZYSv+ehTs4nlhg2/Nc7OFM=',
'user');

------------------------------
-- GAMES
------------------------------

-- TEST GAME: NO WOLVES ALLOWED
INSERT INTO game ("name", "number_of_players") VALUES
('No Wolves Allowed', 5);

INSERT INTO users_game ("user_id", "game_id") VALUES
(1, 1);

INSERT INTO users_game ("user_id", "game_id") VALUES
(2, 1);

INSERT INTO users_game ("user_id", "game_id") VALUES
(3, 1);

INSERT INTO users_game ("user_id", "game_id") VALUES
(4, 1);