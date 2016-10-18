INSERT INTO tb_purity_report
(
    created,
    reporter,
    longitude,
    latitude,
    virus_ppm,
    contaminant_ppm,
    overall_condition
)
VALUES
(
    timestamp '2016-06-30',
    2,
    30,
    50,
    0.0,
    0.95,
    1
),
(
    timestamp '2016-04-12',
    2,
    42,
    12,
    1.02,
    0.95,
    2
),
(
    timestamp '2016-08-10',
    2,
    60,
    30,
    .41,
    1.2,
    0
);
