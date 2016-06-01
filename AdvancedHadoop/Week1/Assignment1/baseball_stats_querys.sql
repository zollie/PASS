USE baseball_stats;
SELECT m.playerID, m.nameFirst, m.nameLast, SUM(b.hr) AS max_hr, b.yeardID
FROM master m JOIN batting b ON m.playerID = b.playerID
GROUP BY  m.playerID, m.nameFirst, m.nameLast, b.hr, b.yeardID
ORDER BY max_hr DESC
LIMIT 50;

SELECT t.teamID, t.lgid, t.name, AVG(s.salary) AS avgsal
FROM teams t JOIN salaries s ON t.teamID = s.teamID
WHERE t.yearID = 2012
GROUP BY t.teamID, t.lgid, t.name
ORDER BY avgsal DESC;
