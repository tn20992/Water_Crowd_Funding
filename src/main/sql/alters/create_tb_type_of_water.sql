CREATE TABLE tb_type_of_water (
    type_of_water integer primary key,
    name          varchar unique not null
);

INSERT INTO tb_type_of_water
(
    type_of_water,
    name
)
VALUES
(
    0,
    'Bottled'
),
(
    1,
    'Well'
),
(
    2,
    'Stream'
),
(
    3,
    'Lake'
),
(
    4,
    'Spring'
),
(
    5,
    'Other'
);
ALTER TABLE tb_source_report
 ADD COLUMN type_of_water INTEGER NOT NULL REFERENCES tb_type_of_water;
