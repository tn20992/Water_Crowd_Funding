INSERT INTO tb_source_report
(
    created,
    reporter,
    longitude,
    latitude,
    type_of_water,
    condition_of_water
)
VALUES
(
    timestamp '2016-06-30',
    2,
    40,
    70,
    0,
    2
),
(
    timestamp '2016-04-12',
    4,
    22,
    62,
    4,
    3
),
(
    timestamp '2016-08-10',
    1,
    50,
    70,
    2,
    3
);
