# ALTER TABLE name_history
#     DROP FOREIGN KEY FK_name_history_players;
#
# ALTER TABLE players
#     DROP FOREIGN KEY FK_players_clans;
#
# ALTER TABLE suggestions
#     DROP FOREIGN KEY FK_suggestions_users;
#
# ALTER TABLE war_list
#     DROP FOREIGN KEY FK_war_list_clans;

DROP TABLE IF EXISTS `name_history`;
DROP TABLE IF EXISTS `players`;
DROP TABLE IF EXISTS `suggestions`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `war_list`;
DROP TABLE IF EXISTS `clans`;

CREATE TABLE `clans`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) NOT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_clan_name` (`name`)

) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `players`
(
    `id`             bigint       NOT NULL,
    `army`           int          NOT NULL DEFAULT '0',
    `is_clan_leader` int                   DEFAULT '0',
    `is_twink`       int                   DEFAULT '0',
    `name`           varchar(255) NOT NULL,
    `profile_link`   varchar(255) NOT NULL,
    `clan`           BIGINT                DEFAULT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT FK_players_clans FOREIGN KEY (`clan`) REFERENCES `clans` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `name_history`
(
    `id`           BIGINT NOT NULL,
    `name_history` varchar(255) DEFAULT NULL,
    CONSTRAINT `FK_name_history_players` FOREIGN KEY (`id`) REFERENCES `players` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `users`
(
    id              BIGINT          NOT NULL AUTO_INCREMENT,
    blocked         BOOL                                        DEFAULT false,
    login           VARCHAR(100)    NOT NULL,
    password        VARCHAR(20)     NOT NULL,
    role            VARCHAR(255),
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_users_login` (`login`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `suggestions`
(
    id              BIGINT NOT NULL AUTO_INCREMENT,
    user_id         BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT FK_suggestions_users FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `war_list`
(
    `id`            BIGINT NOT NULL,
    `war_list`      BIGINT              DEFAULT NULL,

    CONSTRAINT FK_war_list_clans FOREIGN KEY (`id`) REFERENCES `clans` (`id`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;