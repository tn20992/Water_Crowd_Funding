CREATE TABLE tb_account_type (
    account_type integer primary key,
    name         varchar unique not null
);

INSERT INTO tb_account_type
(
    account_type,
    name
)
VALUES
(
    1,
    'User'
),
(
    2,
    'Worker'
),
(
    3,
    'Manager'
),
(
    4,
    'Admin'
);

ALTER TABLE tb_entity
 ADD COLUMN account_type INTEGER NOT NULL REFERENCES tb_account_type;
