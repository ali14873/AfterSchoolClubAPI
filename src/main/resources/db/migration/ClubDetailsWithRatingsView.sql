CREATE VIEW ClubDetailsView AS
SELECT
    c.ClubID,
    c.ClubName,
    c.Description,
    c.Location,
    AVG(sc.Rating) as AverageRating
FROM
    Clubs c
        LEFT JOIN
    Sessions s ON c.ClubID = s.ClubID
        LEFT JOIN
    SessionComments sc ON s.SessionID = sc.SessionID
GROUP BY
    c.ClubID, c.ClubName, c.Description, c.Location;
