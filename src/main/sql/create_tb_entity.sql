CREATE SEQUENCE sq_entity;

CREATE TABLE tb_entity(
    entity   integer primary key default nextval('sq_entity'),
    username varchar unique not null,
    password varchar        not null
);
