DROP DATABASE IF EXISTS kaimakis_cinemate;
CREATE DATABASE kaimakis_cinemate;

USE kaimakis_cinemate;
CREATE TABLE Users(
    username VARCHAR(50) BINARY PRIMARY KEY NOT NULL,
    password VARCHAR(50) BINARY NOT NULL,
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    imageURL VARCHAR(1000) NOT NULL
);

INSERT INTO Users (username, password, fname, lname, fullname, imageURL)
	VALUES ('test', 'test', 'Nick', 'kaimakis', 'nick kaimakis', 'http://www-scf.usc.edu/~csci201/images/jeffrey_vaudrin-mclean.jpg'),
			('test1', 'test1', 'ben', 'franklin', 'ben franklin', 'http://www-bcf.usc.edu/~dkempe/images/photo.jpg'),
            ('test2', 'test2', 'hello', 'motto', 'hello motto', 'http://www-bcf.usc.edu/~dkempe/images/photo.jpg');

CREATE TABLE Follows(
	followID INT(11) PRIMARY KEY AUTO_INCREMENT,
	follower VARCHAR(50) BINARY NOT NULL,
    #not null means this field is required
    following VARCHAR(50) BINARY NOT NULL
);
INSERT INTO Follows (follower, following)
	VALUES ('test','test1'),
					('test1','test');

CREATE TABLE Movies(
	imdbID VARCHAR(50) PRIMARY KEY NOT NULL,
    sumRatings INT(11) DEFAULT 0,
    countRatings INT(11) DEFAULT 0
);

INSERT INTO Movies (imdbID, sumRatings, countRatings)
	VALUES ('tt0779982',16, 2);


CREATE TABLE Events(
	eventID INT(11) PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(50) BINARY NOT NULL,
    imdbID VARCHAR(50) NOT NULL,
	actionTitle VARCHAR(20) NOT NULL,
    rating INT(5) DEFAULT -1,
    createTime DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (username) REFERENCES Users(username)
);

INSERT INTO Events (username, imdbID, actionTitle, rating)
	VALUES('test', 'tt0779982', 'watched', 5),
				('test', 'tt0779982', 'disliked', 3),
                ('test1', 'tt0779982', 'liked', 20);
