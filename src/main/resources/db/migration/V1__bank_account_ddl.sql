CREATE TABLE bank_account
(
    id          UUID PRIMARY KEY,
    owner_name  VARCHAR(100)      NOT NULL,
    owner_email VARCHAR(100)      NOT NULL UNIQUE,
    balance     DECIMAL(10, 2)    NOT NULL CHECK (balance >= 0),
    version     INTEGER DEFAULT 0 NOT NULL
);
