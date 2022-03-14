USE `dbtest`;
DROP TABLE IF EXISTS `name_history`;
DROP TABLE IF EXISTS `players`;
DROP TABLE IF EXISTS `suggestions`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `clans`;

CREATE TABLE `clans`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL,
    `war_id`      BIGINT,
    `alliance_id` BIGINT,

    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_clan_name` (`name`),
    CONSTRAINT `FK_war_id` FOREIGN KEY (`war_id`) REFERENCES `clans` (`id`),
    CONSTRAINT `FK_alliance_id` FOREIGN KEY (`alliance_id`) REFERENCES `clans` (`id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `players`
(
    `id`             BIGINT       NOT NULL,
    `army`           INT          NOT NULL DEFAULT '0',
    `is_clan_leader` INT                   DEFAULT '0',
    `is_twink`       INT                   DEFAULT '0',
    `name`           VARCHAR(255) NOT NULL,
    `profile_link`   VARCHAR(255) NOT NULL,
    `clan`           BIGINT                DEFAULT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT FK_players_clans FOREIGN KEY (`clan`) REFERENCES `clans` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `name_history`
(
    `id`           BIGINT NOT NULL,
    `name_history` VARCHAR(255) DEFAULT NULL,
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