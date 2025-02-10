CREATE TABLE money_transaction
(
    id           UUID PRIMARY KEY,
    sender_id    UUID                     NOT NULL,
    receiver_id  UUID                     NOT NULL,
    amount       DECIMAL(10, 2)           NOT NULL CHECK (amount >= 0),
    status       VARCHAR(20)              NOT NULL CHECK (status IN ('PENDING', 'FAILED', 'SUCCEEDED')),
    initiated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    finished_at  TIMESTAMP WITH TIME ZONE,
    version      INTEGER DEFAULT 0        NOT NULL,
    CONSTRAINT fk_sender_bank_account FOREIGN KEY (sender_id) REFERENCES bank_account (id),
    CONSTRAINT fk_receiver_bank_account FOREIGN KEY (receiver_id) REFERENCES bank_account (id)
);
