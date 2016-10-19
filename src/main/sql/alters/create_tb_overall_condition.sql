CREATE TABLE tb_overall_condition (
    overall_condition integer primary key,
    name              varchar unique not null
);

INSERT INTO tb_overall_condition
(
    overall_condition,
    name
)
VALUES
(
    0,
    'Safe'
),
(
    1,
    'Treatable'
),
(
    2,
    'Unsafe'
);
ALTER TABLE tb_purity_report
 ADD COLUMN overall_condition INTEGER NOT NULL REFERENCES tb_overall_condition;
