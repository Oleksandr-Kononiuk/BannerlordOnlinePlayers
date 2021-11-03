DROP TABLE IF EXISTS clans;
DROP TABLE IF EXISTS name_history;
DROP TABLE IF EXISTS players;

CREATE TABLE players
(
    id             BIGINT                       NOT NULL,
    name           VARCHAR                      NOT NULL,
    army           INTEGER(10)  DEFAULT 0       NOT NULL,
    is_clan_leader BOOLEAN      DEFAULT FALSE,
    is_twink       BOOLEAN      DEFAULT FALSE,
    profile_link   VARCHAR                      NOT NULL,
    clan           VARCHAR                          NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (clan) REFERENCES clans (name)
);

CREATE TABLE name_history
(
    id             BIGINT       NOT NULL,
    name           VARCHAR      NOT NULL,
    CONSTRAINT player_name_idx UNIQUE (id, name),
    FOREIGN KEY (id) REFERENCES players (id) ON DELETE CASCADE
);

CREATE TABLE clans
(
    id             BIGINT           AUTO_INCREMENT  NOT NULL,
    name           VARCHAR          UNIQUE          NOT NULL,
    relation       INTEGER(10)      DEFAULT 0       NOT NULL,
);
CREATE UNIQUE INDEX IF NOT EXISTS clans_unique_name_idx ON clans (id, name);


