CREATE TABLE IF NOT EXISTS balance_registry
(
    id                 bigserial PRIMARY KEY,
    balance_id         bigint NOT NULL,
    value              decimal NOT NULL,
    date               timestamp NOT NULL
);

ALTER TABLE balance_registry
    ADD CONSTRAINT fk_balance_id FOREIGN KEY (balance_id)
        REFERENCES balance (id);