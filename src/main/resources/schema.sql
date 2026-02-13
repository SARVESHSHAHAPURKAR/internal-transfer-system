DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
                          account_id BIGINT PRIMARY KEY,
                          balance NUMERIC(19, 5) NOT NULL
);