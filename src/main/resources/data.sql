DELETE FROM name_history;
DELETE FROM players;
DELETE FROM war_list;
DELETE FROM clans;
DELETE FROM suggestions;
DELETE FROM users;

INSERT INTO `clans`
VALUES
    (1,'DEV'),
    (2,'HUN'),
    (3,'FMC'),
    (4,'GAL'),
    (5,'LS'),
    (6,'RS');

INSERT INTO `players`
VALUES
    (1,0,0,0,'Storm','https://bannerlord-online.com/forum/index.php?members/storm.1/',1),
    (2,0,0,0,'Slender','https://bannerlord-online.com/forum/index.php?members/slender.2/',1),
    (3,0,0,0,'moonlight.exe','https://bannerlord-online.com/forum/index.php?members/moonlight-exe.3/',1),
    (4,0,0,0,'Вячеслав','https://bannerlord-online.com/forum/index.php?members/%D0%92%D1%8F%D1%87%D0%B5%D1%81%D0%BB%D0%B0%D0%B2.4/',NULL),
    (5,0,0,0,'Vyacheslav','https://bannerlord-online.com/forum/index.php?members/vyacheslav.5/',NULL),
    (6,0,0,0,'Линбоу','https://bannerlord-online.com/forum/index.php?members/%D0%9B%D0%B8%D0%BD%D0%B1%D0%BE%D1%83.6/',NULL),
    (7,0,0,0,'Nekroch','https://bannerlord-online.com/forum/index.php?members/nekroch.7/',NULL),
    (10,0,0,0,'klimsat','https://bannerlord-online.com/forum/index.php?members/klimsat.10/',NULL);

INSERT INTO `name_history`
VALUES
       (1,'SStorm'),
       (1,'STOr'),
       (2,'Tester'),
       (2,'Best Slender'),
       (3,'Moonchik');

INSERT INTO `war_list`
VALUES
    (5,4),
    (5,3);
