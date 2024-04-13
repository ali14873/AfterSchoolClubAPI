CREATE VIEW ClubDetailsView4 AS
SELECT
    c.ClubID,
    c.club_name,
    c.Description,
    c.Location,
    AVG(sc.Rating) as AverageRating
FROM
    Clubs c
        LEFT JOIN
        [dbo].[session_table] s ON c.ClubID = s.ClubID
        LEFT JOIN
        [dbo].[session_comments] sc ON s.SessionID = sc.SessionID
        GROUP BY
        c.ClubID, c.club_name, c.Description, c.Location;
go

