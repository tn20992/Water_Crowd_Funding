CREATE SEQUENCE sq_source_report;

CREATE TABLE tb_source_report(
    source_report   integer primary key default nextval('sq_source_report'),
    created         timestamp default now() not null,
    reporter        integer references tb_entity not null,
    longitude       decimal not null,
    latitude        decimal not null
);
