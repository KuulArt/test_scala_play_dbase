# Tasks schema

# --- !Ups

CREATE TABLE clients(
    ID INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    discount TINYINT(3) unsigned,
    PRIMARY KEY ( ID )
);

INSERT INTO clients values (null, 'prosound', 20);

# --- !Downs

DROP TABLE clients;
