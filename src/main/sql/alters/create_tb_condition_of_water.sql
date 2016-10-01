CREATE TABLE tb_condition_of_water (
    condition_of_water integer primary key,
    name          varchar unique not null
);

INSERT INTO tb_condition_of_water
(
    condition_of_water,
    name
)
VALUES
(
    0,
    'Waste'
),
(
    1,
    'Treatable-Clear'
),
(
    2,
    'Treatable-Muddy'
),
(
    3,
    'Potable'
);
ALTER TABLE tb_source_report
 ADD COLUMN condition_of_water INTEGER NOT NULL REFERENCES tb_condition_of_water;
