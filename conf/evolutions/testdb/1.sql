# Tasks schema

# --- !Ups

CREATE TABLE clients(
    id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    discount TINYINT(3) unsigned,
    PRIMARY KEY ( id )
);

CREATE TABLE equipment(
    id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL ,
    price INT(255) NOT NULL ,
    available TINYINT(4) ,
    description VARCHAR(255),
    PRIMARY KEY (id)
);


# --- !Downs

DROP TABLE clients;
DROP TABLE equipment;
