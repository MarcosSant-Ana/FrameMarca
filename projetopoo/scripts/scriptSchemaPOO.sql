create database if not exists projetopoo;
use projetopoo;
create table if not exists marca(
    idMarca int not null AUTO_INCREMENT,
    nomeMarca VARCHAR(100) not null,
    logo mediumblob,
    PRIMARY KEY (idMarca)
);