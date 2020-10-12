CREATE TABLE IF NOT EXISTS SCHEMA1.MOVIE
(ID SERIAL PRIMARY KEY,
 DESCRIPTION VARCHAR(255),
 GENRE VARCHAR(255),
 IMG_URL VARCHAR(255),
 NAME VARCHAR(255),
 PRODUCER VARCHAR(255),
 DURATION INTEGER,
 YEAR INTEGER,
 RATING FLOAT NOT NULL);

CREATE TABLE IF NOT EXISTS SCHEMA1.COMMENT
(ID SERIAL PRIMARY KEY,
 MOVIE_ID INTEGER,
 USERNAME VARCHAR(255),
 TEXT VARCHAR(255),
 FOREIGN KEY (MOVIE_ID) REFERENCES SCHEMA1.MOVIE(ID));