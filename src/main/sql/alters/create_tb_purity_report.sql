CREATE SEQUENCE sq_purity_report;

CREATE TABLE tb_purity_report(
    purity_report   integer primary key default nextval('sq_purity_report'),
    created         timestamp default now() not null,
    reporter        integer references tb_entity not null,
    longitude       decimal not null,
    latitude        decimal not null,
    virus_ppm       decimal not null,
    contaminant_ppm decimal not null
);
