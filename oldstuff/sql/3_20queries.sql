USE StudentGrades;

SELECT *
		FROM Class;
        
SELECT fname, lname
		FROM Student;
        
SELECT s.fname, s.lname, c.prefix, c.num, g.letterGrade
		FROM Student s, Class c, Grades g
        WHERE s.studentID = g.studentID
			AND c.classID = g.classID
		ORDER BY lname, fname, prefix, num ASC;
	
SELECT c.prefix, c.num, g.letterGrade
		FROM  Student s, Class c, Grades g
		WHERE s.studentID=g.studentID
			AND  c.classID=g.classID
            AND s.fname='Howard';
        
INSERT INTO Class (prefix, num)
		VALUES ('CSCI', 170);
        
UPDATE Grades g, Class c, Student s
		SET g.letterGrade = 'C+'
        WHERE g.classID = c.classID
			AND c.prefic='CSCI'
            AND c.num=201
            AND s.fname = 'Leonard'
            AND  s.lname = 'Hofstadter';