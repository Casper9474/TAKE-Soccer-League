INSERT INTO club (id, name, city, founded_year, stadium_name) VALUES (1, 'Real Madrid CF', 'Madrid', 1902, 'Santiago Bernabéu');
INSERT INTO club (id, name, city, founded_year, stadium_name) VALUES (2, 'FC Barcelona', 'Barcelona', 1899, 'Camp Nou');
INSERT INTO club (id, name, city, founded_year, stadium_name) VALUES (3, 'Atlético Madrid', 'Madrid', 1903, 'Cívitas Metropolitano');

INSERT INTO season (id, name, start_date, end_date, is_active) VALUES (1, 'La Liga 2025/2026', '2025-08-15', '2026-05-24', false);
INSERT INTO season (id, name, start_date, end_date, is_active) VALUES (2, 'La Liga 2026/2027', '2026-08-15', '2027-05-24', true);

INSERT INTO participant (id, season_id, club_id, points) VALUES (1, 1, 1, 86);
INSERT INTO participant (id, season_id, club_id, points) VALUES (2, 1, 2, 90);
INSERT INTO participant (id, season_id, club_id, points) VALUES (3, 2, 1, 0);
INSERT INTO participant (id, season_id, club_id, points) VALUES (4, 2, 2, 0);

INSERT INTO player (id, club_id, first_name, last_name, number, birth_date, position) VALUES (1, 1, 'Kylian', 'Mbappé', 9, '1998-12-20', 'FORWARD');
INSERT INTO player (id, club_id, first_name, last_name, number, birth_date, position) VALUES (2, 1, 'Jude', 'Bellingham', 5, '2003-06-29', 'MIDFIELDER');
INSERT INTO player (id, club_id, first_name, last_name, number, birth_date, position) VALUES (3, 2, 'Robert', 'Lewandowski', 9, '1988-08-21', 'FORWARD');
INSERT INTO player (id, club_id, first_name, last_name, number, birth_date, position) VALUES (4, 2, 'Lamine', 'Yamal', 19, '2007-07-13', 'FORWARD');
INSERT INTO player (id, club_id, first_name, last_name, number, birth_date, position) VALUES (5, 3, 'Antoine', 'Griezmann', 7, '1991-03-21', 'FORWARD');
INSERT INTO player (id, club_id, first_name, last_name, number, birth_date, position) VALUES (6, 3, 'Jan', 'Oblak', 13, '1993-01-07', 'GOALKEEPER');

INSERT INTO match (id, season_id, home_club_id, away_club_id, match_date, match_week, home_score, away_score, status) VALUES (1, 1, 2, 1, '2025-10-26 21:00:00', 11, 2, 2, 'DRAW');
INSERT INTO match (id, season_id, home_club_id, away_club_id, match_date, match_week, home_score, away_score, status) VALUES (2, 1, 1, 3, '2026-03-01 20:00:00', 26, 3, 1, 'HOMEWIN');

INSERT INTO goal (id, match_id, player_id, "minute", goal_type) VALUES (1, 1, 3, 15, 'NORMAL');
INSERT INTO goal (id, match_id, player_id, "minute", goal_type) VALUES (2, 1, 1, 42, 'NORMAL');
INSERT INTO goal (id, match_id, player_id, "minute", goal_type) VALUES (3, 1, 4, 60, 'NORMAL');
INSERT INTO goal (id, match_id, player_id, "minute", goal_type) VALUES (4, 1, 2, 89, 'PENALTY');

ALTER SEQUENCE club_seq RESTART WITH (select max(id) + 1 from club);
ALTER SEQUENCE season_seq RESTART WITH (select max(id) + 1 from season);
ALTER SEQUENCE participant_seq RESTART WITH (select max(id) + 1 from participant);
ALTER SEQUENCE player_seq RESTART WITH (select max(id) + 1 from player);
ALTER SEQUENCE match_seq RESTART WITH (select max(id) + 1 from match);
ALTER SEQUENCE goal_seq RESTART WITH (select max(id) + 1 from goal);
