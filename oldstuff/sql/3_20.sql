DROP DATABASE IF EXISTS StudentGrades;
CREATE DATABASE StudentGrades;
#not required but helpful, like 'using namespace std'
-- also a comment
/*also a comment*/
USE StudentGrades;
CREATE TABLE Class(
	classID INT(11) PRIMARY KEY AUTO_INCREMENT,
    #not null means this field is required
    prefix VARCHAR(4) NOT NULL,
    num INT(3) NOT NULL
    
    
);
#mysql is whitespace insensitive, so space as you want!
INSERT INTO Class (prefix, num)
	VALUES ('CSCI', 103),
					('CSCI', 104),
                    ('CSCI', 201),
                    ('EE', 101),
                    ('EE', 102);
                    
CREATE TABLE Student (
	studentID INT(11) PRIMARY KEY AUTO_INCREMENT,
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL
);

INSERT INTO Student (fname, lname)
	VALUES ('Sheldon', 'Cooper'),
					('Leonard', 'Hofstadter'),
                    ('Howard', 'Wolowitz'),
                    ('Rajesh', 'Koothrappali');

